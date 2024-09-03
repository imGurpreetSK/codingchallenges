package com.gurpreetsk.sudoku.shared

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class SudokuViewModel(
    repository: SudokuRepository,
    dispatcher: CoroutineDispatcher
) { // Make internal - needs DI framework.

    private val scope = CoroutineScope(
        SupervisorJob() + dispatcher + CoroutineName("main-coroutine-sudoku-view-model")
    )

    private val initialGrid by lazy { repository.getUnsolved() }

    private val _state: MutableStateFlow<SudokuState> = MutableStateFlow(SudokuState(initialGrid, false))
    val state: StateFlow<SudokuState> = _state
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), SudokuState(initialGrid, false))

    fun updateValue(
        updatedValue: UInt,
        subGridCoordinates: Coordinates,
        cellCoordinates: Coordinates
    ) {
        _state.update {
            println("Value updated for ($subGridCoordinates, $cellCoordinates) to $updatedValue.")
            val initialState = _state.value
            val updatedGrid = initialState.grid.getUpdatedGrid(updatedValue, subGridCoordinates, cellCoordinates)

            return@update SudokuState(updatedGrid, updatedGrid.isSolutionValid(subGridCoordinates, cellCoordinates))
        }
    }

    private fun Grid.isSolutionValid(subGridCoordinates: Coordinates, cellCoordinates: Coordinates): Boolean {
        val subGridItems = this.items[subGridCoordinates.x][subGridCoordinates.y].items.flatten()
        val isSubGridValid = subGridItems.isNotEmpty() && subGridItems.all { it != null } && subGridItems.toSet().count() == 9
        if (!isSubGridValid) {
            return false
        }

        val row = buildList {
            items[subGridCoordinates.y].forEachIndexed { index, subGrid ->
                addAll(subGrid.items[cellCoordinates.y])
            }
        }
        val isRowValid = row.isNotEmpty() && row.all { it != null } && row.toSet().count() == 9
        if (!isRowValid) {
            return false
        }

        val column = buildList {
            items[subGridCoordinates.x].forEachIndexed { index, subGrid ->
                addAll(subGrid.items[cellCoordinates.x])
            }
        }
        val isColumnValid = column.isNotEmpty() && column.all { it != null } && column.toSet().count() == 9
        return isColumnValid
    }

    private fun Grid.getUpdatedGrid(
        updatedValue: UInt,
        subGridCoordinates: Coordinates,
        cellCoordinates: Coordinates
    ): Grid {
        if (items[subGridCoordinates.y][subGridCoordinates.x].items[cellCoordinates.y][cellCoordinates.x]?.isEditable == false) {
            return this
        }

        val subGridToUpdate: SubGrid = items[subGridCoordinates.y][subGridCoordinates.x]

        val updatedRow = subGridToUpdate.items[cellCoordinates.y].toMutableList()
        updatedRow[cellCoordinates.x] = Cell(updatedValue)
        val updatedSubGrid = subGridToUpdate.items.toMutableList()
        updatedSubGrid[cellCoordinates.y] = updatedRow
        val updatedGridRow = items[subGridCoordinates.y].toMutableList()
        updatedGridRow[subGridCoordinates.x] = SubGrid(updatedSubGrid)

        val updatedGrid = items.toMutableList()
        updatedGrid[subGridCoordinates.y] = updatedGridRow

        return Grid(updatedGrid)
    }
}

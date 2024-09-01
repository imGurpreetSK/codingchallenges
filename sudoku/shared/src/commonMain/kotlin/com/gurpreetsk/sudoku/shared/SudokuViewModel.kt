package com.gurpreetsk.sudoku.shared

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class SudokuViewModel(dispatcher: CoroutineDispatcher) { // Make internal - needs DI framework.

    private val scope = CoroutineScope(
        SupervisorJob() + dispatcher + CoroutineName("main-coroutine-sudoku-view-model")
    )

    private val initialGrid = Grid(
        listOf(
            listOf(
                SubGrid(
                    listOf(
                        listOf(null, Cell(5u, false), null),
                        listOf(null, null, null),
                        listOf(null, Cell(1u, false), Cell(9u, false)),
                    )
                ),
                SubGrid(
                    listOf(
                        listOf(Cell(1u, false), Cell(7u, false), Cell(9u, false)),
                        listOf(null, null, Cell(4u, false)),
                        listOf(null, null, null),
                    )
                ),
                SubGrid(
                    listOf(
                        listOf(Cell(3u, false), Cell(8u, false), null),
                        listOf(null, Cell(1u, false), null),
                        listOf(Cell(7u, false), Cell(2u, false), null),
                    )
                )
            ),
            listOf(
                SubGrid(
                    listOf(
                        listOf(null, null, null),
                        listOf(Cell(2u, false), Cell(6u, false), null),
                        listOf(Cell(8u, false), Cell(3u, false), null),
                    )
                ),
                SubGrid(
                    listOf(
                        listOf(null, null, Cell(8u, false)),
                        listOf(null, Cell(4u, false), null),
                        listOf(Cell(2u, false), Cell(9u, false), null),
                    )
                ),
                SubGrid(
                    listOf(
                        listOf(Cell(2u, false), null, null),
                        listOf(null, null, Cell(5u, false)),
                        listOf(null, null, Cell(7u, false)),
                    )
                )
            ),
            listOf(
                SubGrid(
                    listOf(
                        listOf(null, Cell(4u, false), Cell(3u, false)),
                        listOf(Cell(6u, false), null, null),
                        listOf(Cell(1u, false), Cell(9u, false), null),
                    )
                ),
                SubGrid(
                    listOf(
                        listOf(null, Cell(6u, false), null),
                        listOf(Cell(8u, false), null, Cell(5u, false)),
                        listOf(Cell(4u, false), Cell(3u, false), null),
                    )
                ),
                SubGrid(
                    listOf(
                        listOf(null, Cell(7u, false), Cell(8u, false)),
                        listOf(null, Cell(4u, false), Cell(3u, false)),
                        listOf(null, null, Cell(2u, false)),
                    )
                )
            ),
        )
    )

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

            return@update SudokuState(updatedGrid, updatedGrid.isSolutionValid())
        }
    }

    private fun Grid.isSolutionValid(): Boolean {
        return false
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

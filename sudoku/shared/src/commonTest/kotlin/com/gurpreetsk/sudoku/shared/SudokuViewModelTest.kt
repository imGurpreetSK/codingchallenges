@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package com.gurpreetsk.sudoku.shared

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SudokuViewModelTest {

    @Test
    fun `initial grid is received on subscribe`() = runTest {
        SudokuViewModel(UnconfinedTestDispatcher(testScheduler)).state.test {
            assertEquals(initialState, awaitItem())
        }
    }

    @Test
    fun `receive updated grid - (0,0) item at (0,0) is updated`() = runTest {
        val updatedCell = Cell(1u)
        val expectedGrid = Grid(
            listOf(
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(updatedCell, Cell(5u, false), null),
                            mutableListOf(null, null, null),
                            mutableListOf(null, Cell(1u, false), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(1u, false), Cell(7u, false), Cell(9u, false)),
                            mutableListOf(null, null, Cell(4u, false)),
                            mutableListOf(null, null, null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(3u, false), Cell(8u, false), null),
                            mutableListOf(null, Cell(1u, false), null),
                            mutableListOf(Cell(7u, false), Cell(2u, false), null),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, null, null),
                            mutableListOf(Cell(2u, false), Cell(6u, false), null),
                            mutableListOf(Cell(8u, false), Cell(3u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, null, Cell(8u, false)),
                            mutableListOf(null, Cell(4u, false), null),
                            mutableListOf(Cell(2u, false), Cell(9u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u, false), null, null),
                            mutableListOf(null, null, Cell(5u, false)),
                            mutableListOf(null, null, Cell(7u, false)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(4u, false), Cell(3u, false)),
                            mutableListOf(Cell(6u, false), null, null),
                            mutableListOf(Cell(1u, false), Cell(9u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(6u, false), null),
                            mutableListOf(Cell(8u, false), null, Cell(5u, false)),
                            mutableListOf(Cell(4u, false), Cell(3u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(7u, false), Cell(8u, false)),
                            mutableListOf(null, Cell(4u, false), Cell(3u, false)),
                            mutableListOf(null, null, Cell(2u, false)),
                        )
                    )
                ),
            )
        )

        val sudokuViewModel = SudokuViewModel(UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(initialState, awaitItem())

            sudokuViewModel.updateValue(1u, Coordinates(0, 0), Coordinates(0, 0))
            assertEquals(SudokuState(expectedGrid, false), awaitItem())
        }
    }

    @Test
    fun `never receive updated grid when item is uneditable - (1,1) item at (1,1) is updated`() = runTest {
        val sudokuViewModel = SudokuViewModel(UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(initialState, awaitItem())

            sudokuViewModel.updateValue(9u, Coordinates(1, 1), Coordinates(1, 1))
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `receive updated grid - (2,1) item at (0,0) is updated`() = runTest {
        val updatedCell = Cell(6u)
        val expectedGrid = Grid(
            listOf(
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(5u, false), null),
                            mutableListOf(null, null, updatedCell),
                            mutableListOf(null, Cell(1u, false), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(1u, false), Cell(7u, false), Cell(9u, false)),
                            mutableListOf(null, null, Cell(4u, false)),
                            mutableListOf(null, null, null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(3u, false), Cell(8u, false), null),
                            mutableListOf(null, Cell(1u, false), null),
                            mutableListOf(Cell(7u, false), Cell(2u, false), null),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, null, null),
                            mutableListOf(Cell(2u, false), Cell(6u, false), null),
                            mutableListOf(Cell(8u, false), Cell(3u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, null, Cell(8u, false)),
                            mutableListOf(null, Cell(4u, false), null),
                            mutableListOf(Cell(2u, false), Cell(9u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u, false), null, null),
                            mutableListOf(null, null, Cell(5u, false)),
                            mutableListOf(null, null, Cell(7u, false)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(4u, false), Cell(3u, false)),
                            mutableListOf(Cell(6u, false), null, null),
                            mutableListOf(Cell(1u, false), Cell(9u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(6u, false), null),
                            mutableListOf(Cell(8u, false), null, Cell(5u, false)),
                            mutableListOf(Cell(4u, false), Cell(3u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(7u, false), Cell(8u, false)),
                            mutableListOf(null, Cell(4u, false), Cell(3u, false)),
                            mutableListOf(null, null, Cell(2u, false)),
                        )
                    )
                ),
            )
        )

        val sudokuViewModel = SudokuViewModel(UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(initialState, awaitItem())

            sudokuViewModel.updateValue(6u, Coordinates(0, 0), Coordinates(2, 1))
            assertEquals(SudokuState(expectedGrid, false), awaitItem())
        }
    }

    @Test
    fun `receive updated grid - (2,1) item at (1,1) is updated`() = runTest {
        val updatedCell = Cell(9u)
        val expectedGrid = Grid(
            listOf(
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(5u, false), null),
                            mutableListOf(null, null, null),
                            mutableListOf(null, Cell(1u, false), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(1u, false), Cell(7u, false), Cell(9u, false)),
                            mutableListOf(null, null, Cell(4u, false)),
                            mutableListOf(null, null, null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(3u, false), Cell(8u, false), null),
                            mutableListOf(null, Cell(1u, false), null),
                            mutableListOf(Cell(7u, false), Cell(2u, false), null),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, null, null),
                            mutableListOf(Cell(2u, false), Cell(6u, false), null),
                            mutableListOf(Cell(8u, false), Cell(3u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, null, Cell(8u, false)),
                            mutableListOf(null, Cell(4u, false), updatedCell),
                            mutableListOf(Cell(2u, false), Cell(9u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u, false), null, null),
                            mutableListOf(null, null, Cell(5u, false)),
                            mutableListOf(null, null, Cell(7u, false)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(4u, false), Cell(3u, false)),
                            mutableListOf(Cell(6u, false), null, null),
                            mutableListOf(Cell(1u, false), Cell(9u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(6u, false), null),
                            mutableListOf(Cell(8u, false), null, Cell(5u, false)),
                            mutableListOf(Cell(4u, false), Cell(3u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(7u, false), Cell(8u, false)),
                            mutableListOf(null, Cell(4u, false), Cell(3u, false)),
                            mutableListOf(null, null, Cell(2u, false)),
                        )
                    )
                ),
            )
        )

        val sudokuViewModel = SudokuViewModel(UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(initialState, awaitItem())

            sudokuViewModel.updateValue(9u, Coordinates(1, 1), Coordinates(2, 1))
            assertEquals(SudokuState(expectedGrid, false), awaitItem())
        }
    }

    @Test
    fun `receive updated grid - (2,0) item at (1,2) is updated`() = runTest {
        val updatedCell = Cell(5u)
        val expectedGrid = Grid(
            listOf(
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(5u, false), null),
                            mutableListOf(null, null, null),
                            mutableListOf(null, Cell(1u, false), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(1u, false), Cell(7u, false), Cell(9u, false)),
                            mutableListOf(null, null, Cell(4u, false)),
                            mutableListOf(null, null, null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(3u, false), Cell(8u, false), null),
                            mutableListOf(null, Cell(1u, false), null),
                            mutableListOf(Cell(7u, false), Cell(2u, false), null),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, null, null),
                            mutableListOf(Cell(2u, false), Cell(6u, false), null),
                            mutableListOf(Cell(8u, false), Cell(3u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, null, Cell(8u, false)),
                            mutableListOf(null, Cell(4u, false), null),
                            mutableListOf(Cell(2u, false), Cell(9u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u, false), null, null),
                            mutableListOf(null, null, Cell(5u, false)),
                            mutableListOf(null, null, Cell(7u, false)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(4u, false), Cell(3u, false)),
                            mutableListOf(Cell(6u, false), null, null),
                            mutableListOf(Cell(1u, false), Cell(9u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(6u, false), updatedCell),
                            mutableListOf(Cell(8u, false), null, Cell(5u, false)),
                            mutableListOf(Cell(4u, false), Cell(3u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(7u, false), Cell(8u, false)),
                            mutableListOf(null, Cell(4u, false), Cell(3u, false)),
                            mutableListOf(null, null, Cell(2u, false)),
                        )
                    )
                ),
            )
        )

        val sudokuViewModel = SudokuViewModel(UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(initialState, awaitItem())

            sudokuViewModel.updateValue(5u, Coordinates(1, 2), Coordinates(2, 0))
            assertEquals(SudokuState(expectedGrid, false), awaitItem())
        }
    }

    private val initialState = SudokuState(
        Grid(
            listOf(
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(5u, false), null),
                            mutableListOf(null, null, null),
                            mutableListOf(null, Cell(1u, false), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(1u, false), Cell(7u, false), Cell(9u, false)),
                            mutableListOf(null, null, Cell(4u, false)),
                            mutableListOf(null, null, null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(3u, false), Cell(8u, false), null),
                            mutableListOf(null, Cell(1u, false), null),
                            mutableListOf(Cell(7u, false), Cell(2u, false), null),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, null, null),
                            mutableListOf(Cell(2u, false), Cell(6u, false), null),
                            mutableListOf(Cell(8u, false), Cell(3u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, null, Cell(8u, false)),
                            mutableListOf(null, Cell(4u, false), null),
                            mutableListOf(Cell(2u, false), Cell(9u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u, false), null, null),
                            mutableListOf(null, null, Cell(5u, false)),
                            mutableListOf(null, null, Cell(7u, false)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(4u, false), Cell(3u, false)),
                            mutableListOf(Cell(6u, false), null, null),
                            mutableListOf(Cell(1u, false), Cell(9u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(6u, false), null),
                            mutableListOf(Cell(8u, false), null, Cell(5u, false)),
                            mutableListOf(Cell(4u, false), Cell(3u, false), null),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(null, Cell(7u, false), Cell(8u, false)),
                            mutableListOf(null, Cell(4u, false), Cell(3u, false)),
                            mutableListOf(null, null, Cell(2u, false)),
                        )
                    )
                ),
            )
        ),
        false
    )
}

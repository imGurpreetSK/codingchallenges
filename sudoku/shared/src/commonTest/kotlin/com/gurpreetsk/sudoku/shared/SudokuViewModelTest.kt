@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package com.gurpreetsk.sudoku.shared

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.*

internal class SudokuViewModelTest {

    private val repository = FakeSudokuRepository()

    @Test
    fun `initial grid is received on subscribe`() = runTest {
        SudokuViewModel(repository, UnconfinedTestDispatcher(testScheduler)).state.test {
            assertEquals(repository.getState(), awaitItem())
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

        val sudokuViewModel = SudokuViewModel(repository, UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(repository.getState(), awaitItem())

            sudokuViewModel.updateValue(1u, Coordinates(0, 0), Coordinates(0, 0))
            assertEquals(SudokuState(expectedGrid, false), awaitItem())
        }
    }

    @Test
    fun `never receive updated grid when item is uneditable - (1,1) item at (1,1) is updated`() = runTest {
        val sudokuViewModel = SudokuViewModel(repository, UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(repository.getState(), awaitItem())

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

        val sudokuViewModel = SudokuViewModel(repository, UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(repository.getState(), awaitItem())

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

        val sudokuViewModel = SudokuViewModel(repository, UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(repository.getState(), awaitItem())

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

        val sudokuViewModel = SudokuViewModel(repository, UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(repository.getState(), awaitItem())

            sudokuViewModel.updateValue(5u, Coordinates(1, 2), Coordinates(2, 0))
            assertEquals(SudokuState(expectedGrid, false), awaitItem())
        }
    }

    @Test
    fun `sudoku remains unsolved when a cell in subgrid has null value`() = runTest {
        val updatedCell = Cell(5u)
        val solvedGrid = Grid(
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

        val sudokuViewModel = SudokuViewModel(repository, UnconfinedTestDispatcher(testScheduler))

        sudokuViewModel.state.test {
            assertEquals(repository.getState(), awaitItem())

            sudokuViewModel.updateValue(5u, Coordinates(1, 2), Coordinates(2, 0))
            assertEquals(SudokuState(solvedGrid, false), awaitItem())
        }
    }

    @Test
    fun `sudoku remains unsolved when a cell in subgrid has duplicate value`() = runTest {
        val updatedCell = Cell(5u)
        val almostSolvedGrid = Grid(
            listOf(
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(8u), Cell(2u, false), Cell(7u)),
                            mutableListOf(Cell(9u, true), Cell(6u), Cell(5u)),
                            mutableListOf(Cell(3u), Cell(4u), Cell(1u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(1u), Cell(5u), Cell(4u, false)),
                            mutableListOf(Cell(3u), Cell(2u, false), Cell(7u)),
                            mutableListOf(Cell(6u, false), Cell(8u), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(3u, false), Cell(9u), Cell(6u)),
                            mutableListOf(Cell(1u), Cell(4u), Cell(8u, false)),
                            mutableListOf(Cell(7u), Cell(5u, false), Cell(2u)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(5u), Cell(9u), Cell(3u)),
                            mutableListOf(Cell(4u), Cell(7u, false), Cell(2u, false)),
                            mutableListOf(Cell(6u, false), Cell(1u), Cell(8u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(4u), Cell(6u), Cell(8u)),
                            mutableListOf(Cell(5u, false), Cell(1u), Cell(3u, false)),
                            mutableListOf(Cell(9u), Cell(7u), Cell(2u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u), Cell(7u), Cell(1u, false)),
                            mutableListOf(Cell(6u, false), Cell(8u, false), Cell(9u)),
                            mutableListOf(Cell(4u), Cell(3u), Cell(5u)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(7u), Cell(8u, false), Cell(6u)),
                            mutableListOf(Cell(1u, false), Cell(5u), Cell(4u)),
                            mutableListOf(Cell(2u), Cell(3u), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u, false), Cell(3u), Cell(5u, false)),
                            mutableListOf(Cell(7u), Cell(9u, false), Cell(6u)),
                            mutableListOf(Cell(8u, false), Cell(4u), Cell(1u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(9u), Cell(1u), Cell(4u)),
                            mutableListOf(Cell(8u), Cell(2u), Cell(3u, false)),
                            mutableListOf(Cell(5u), Cell(6u, false), Cell(7u)),
                        )
                    )
                ),
            )
        )
        val incorrectlySolvedGrid = Grid(
            listOf(
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(8u), Cell(2u, false), Cell(7u)),
                            mutableListOf(Cell(9u, true), Cell(6u), Cell(5u)),
                            mutableListOf(Cell(3u), Cell(4u), Cell(1u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(1u), Cell(5u), Cell(4u, false)),
                            mutableListOf(Cell(3u), Cell(2u, false), Cell(7u)),
                            mutableListOf(Cell(6u, false), Cell(8u), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(3u, false), Cell(9u), Cell(6u)),
                            mutableListOf(Cell(1u), Cell(4u), Cell(8u, false)),
                            mutableListOf(Cell(7u), Cell(5u, false), Cell(2u)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(5u), Cell(9u), Cell(3u)),
                            mutableListOf(Cell(4u), Cell(7u, false), Cell(2u, false)),
                            mutableListOf(Cell(6u, false), Cell(1u), Cell(8u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(4u), Cell(6u), Cell(8u)),
                            mutableListOf(Cell(5u, false), Cell(1u), Cell(3u, false)),
                            mutableListOf(Cell(9u), Cell(7u), Cell(2u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u), Cell(7u), Cell(1u, false)),
                            mutableListOf(Cell(6u, false), Cell(8u, false), Cell(9u)),
                            mutableListOf(Cell(4u), Cell(3u), Cell(5u)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(7u), Cell(8u, false), Cell(6u)),
                            mutableListOf(Cell(1u, false), Cell(5u), Cell(4u)),
                            mutableListOf(Cell(2u), Cell(3u), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u, false), Cell(3u), Cell(5u, false)),
                            mutableListOf(Cell(7u), Cell(9u, false), updatedCell),
                            mutableListOf(Cell(8u, false), Cell(4u), Cell(1u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(9u), Cell(1u), Cell(4u)),
                            mutableListOf(Cell(8u), Cell(2u), Cell(3u, false)),
                            mutableListOf(Cell(5u), Cell(6u, false), Cell(7u)),
                        )
                    )
                ),
            )
        )

        val sudokuViewModel = SudokuViewModel(
            FakeSudokuRepository(SudokuState(almostSolvedGrid, false)),
            UnconfinedTestDispatcher(testScheduler)
        )

        sudokuViewModel.state.test {
            assertEquals(SudokuState(almostSolvedGrid, false), awaitItem())

            sudokuViewModel.updateValue(updatedCell.data, Coordinates(1, 2), Coordinates(2, 1))
            assertEquals(SudokuState(incorrectlySolvedGrid, false), awaitItem())
        }
    }

    @Test
    fun `sudoku is solved when subgrid, column and row constraints are satisfied`() = runTest {
        val updatedCell = Cell(6u)
        val almostSolvedGrid = Grid(
            listOf(
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(8u), Cell(2u, false), Cell(7u)),
                            mutableListOf(Cell(9u, true), Cell(6u), Cell(5u)),
                            mutableListOf(Cell(3u), Cell(4u), Cell(1u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(1u), Cell(5u), Cell(4u, false)),
                            mutableListOf(Cell(3u), Cell(2u, false), Cell(7u)),
                            mutableListOf(Cell(6u, false), Cell(8u), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(3u, false), Cell(9u), Cell(6u)),
                            mutableListOf(Cell(1u), Cell(4u), Cell(8u, false)),
                            mutableListOf(Cell(7u), Cell(5u, false), Cell(2u)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(5u), Cell(9u), Cell(3u)),
                            mutableListOf(Cell(4u), Cell(7u, false), Cell(2u, false)),
                            mutableListOf(Cell(6u, false), Cell(1u), Cell(8u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(4u), Cell(6u), Cell(8u)),
                            mutableListOf(Cell(5u, false), Cell(1u), Cell(3u, false)),
                            mutableListOf(Cell(9u), Cell(7u), Cell(2u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u), Cell(7u), Cell(1u, false)),
                            mutableListOf(Cell(6u, false), Cell(8u, false), Cell(9u)),
                            mutableListOf(Cell(4u), Cell(3u), Cell(5u)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(7u), Cell(8u, false), Cell(6u)),
                            mutableListOf(Cell(1u, false), Cell(5u), Cell(4u)),
                            mutableListOf(Cell(2u), Cell(3u), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u, false), Cell(3u), Cell(5u, false)),
                            mutableListOf(Cell(7u), Cell(9u, false), Cell(6u)),
                            mutableListOf(Cell(8u, false), Cell(4u), Cell(1u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(9u), Cell(1u), Cell(4u)),
                            mutableListOf(Cell(8u), Cell(2u), Cell(3u, false)),
                            mutableListOf(Cell(5u), Cell(6u, false), Cell(7u)),
                        )
                    )
                ),
            )
        )
        val correctlySolvedGrid = Grid(
            listOf(
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(8u), Cell(2u, false), Cell(7u)),
                            mutableListOf(Cell(9u, true), Cell(6u), Cell(5u)),
                            mutableListOf(Cell(3u), Cell(4u), Cell(1u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(1u), Cell(5u), Cell(4u, false)),
                            mutableListOf(Cell(3u), Cell(2u, false), Cell(7u)),
                            mutableListOf(Cell(6u, false), Cell(8u), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(3u, false), Cell(9u), Cell(6u)),
                            mutableListOf(Cell(1u), Cell(4u), Cell(8u, false)),
                            mutableListOf(Cell(7u), Cell(5u, false), Cell(2u)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(5u), Cell(9u), Cell(3u)),
                            mutableListOf(Cell(4u), Cell(7u, false), Cell(2u, false)),
                            mutableListOf(Cell(6u, false), Cell(1u), Cell(8u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(4u), Cell(6u), Cell(8u)),
                            mutableListOf(Cell(5u, false), Cell(1u), Cell(3u, false)),
                            mutableListOf(Cell(9u), Cell(7u), Cell(2u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u), Cell(7u), Cell(1u, false)),
                            mutableListOf(Cell(6u, false), Cell(8u, false), Cell(9u)),
                            mutableListOf(Cell(4u), Cell(3u), Cell(5u)),
                        )
                    )
                ),
                listOf(
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(7u), Cell(8u, false), Cell(6u)),
                            mutableListOf(Cell(1u, false), Cell(5u), Cell(4u)),
                            mutableListOf(Cell(2u), Cell(3u), Cell(9u, false)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(2u, false), Cell(3u), Cell(5u, false)),
                            mutableListOf(Cell(7u), Cell(9u, false), updatedCell),
                            mutableListOf(Cell(8u, false), Cell(4u), Cell(1u)),
                        )
                    ),
                    SubGrid(
                        listOf(
                            mutableListOf(Cell(9u), Cell(1u), Cell(4u)),
                            mutableListOf(Cell(8u), Cell(2u), Cell(3u, false)),
                            mutableListOf(Cell(5u), Cell(6u, false), Cell(7u)),
                        )
                    )
                ),
            )
        )

        val sudokuViewModel = SudokuViewModel(
            FakeSudokuRepository(SudokuState(almostSolvedGrid, false)),
            UnconfinedTestDispatcher(testScheduler)
        )

        sudokuViewModel.state.test {
            assertEquals(SudokuState(almostSolvedGrid, false), awaitItem())

            sudokuViewModel.updateValue(updatedCell.data, Coordinates(1, 2), Coordinates(2, 1))
            assertEquals(SudokuState(correctlySolvedGrid, true), awaitItem())
        }
    }

    @AfterTest
    fun teardown() {
        repository.reset()
    }

    private class FakeSudokuRepository(
        initialState: SudokuState = SudokuState(sparseGrid, false)
    ) : SudokuRepository {

        companion object {
            val sparseGrid = Grid(
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
            )
        }

        private var state = initialState

        override fun getUnsolved(): Grid = state.grid

        fun getState() = state

        fun overrideInitialState(state: SudokuState) {
            this.state = state
        }

        fun reset() {
            state = SudokuState(sparseGrid, false)
        }
    }
}

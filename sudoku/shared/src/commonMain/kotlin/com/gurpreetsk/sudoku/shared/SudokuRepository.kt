package com.gurpreetsk.sudoku.shared

interface SudokuRepository {
    fun getUnsolved(): Grid
}

class SingleSudokuRepository : SudokuRepository {

    private val sparseGrid by lazy(LazyThreadSafetyMode.NONE) {
        Grid(
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
    }

    override fun getUnsolved(): Grid = sparseGrid
}

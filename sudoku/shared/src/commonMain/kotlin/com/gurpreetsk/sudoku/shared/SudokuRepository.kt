package com.gurpreetsk.sudoku.shared

interface SudokuRepository {
    fun getUnsolved(): Grid
}

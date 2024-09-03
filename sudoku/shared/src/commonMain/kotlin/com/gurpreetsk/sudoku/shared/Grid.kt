package com.gurpreetsk.sudoku.shared

data class Grid(
    val items: List<List<SubGrid>>
)

data class SubGrid(
    val items: List<List<Cell?>>
)

data class Cell(
    val data: UInt,
    val isEditable: Boolean = true
)

data class Coordinates(val x: Int, val y: Int)

package com.gurpreetsk.sudoku.shared

data class Grid(
    val items: Array<Array<SubGrid>>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Grid

        return items.contentDeepEquals(other.items)
    }

    override fun hashCode(): Int {
        return items.contentDeepHashCode()
    }

    override fun toString(): String {
        return "GridData(items=${items.contentToString()})"
    }
}

data class SubGrid(
    val items: Array<Array<Cell?>>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SubGrid

        return items.contentDeepEquals(other.items)
    }

    override fun hashCode(): Int {
        return items.contentDeepHashCode()
    }

    override fun toString(): String {
        return "Cell(items=${items.contentToString()})"
    }
}

data class Cell(
    val data: UInt?,
    val isEditable: Boolean = true
)

data class Coordinates(val x: Int, val y: Int)

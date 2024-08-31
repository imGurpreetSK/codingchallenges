package com.gurpreetsk.sudoku.shared

data class GridData(
    val items: Array<Array<Cell>>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GridData

        return items.contentDeepEquals(other.items)
    }

    override fun hashCode(): Int {
        return items.contentDeepHashCode()
    }
}

data class Cell(
    val items: Array<Array<UInt?>>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cell

        return items.contentDeepEquals(other.items)
    }

    override fun hashCode(): Int {
        return items.contentDeepHashCode()
    }
}

data class Coordinates(val x: Int, val y: Int)

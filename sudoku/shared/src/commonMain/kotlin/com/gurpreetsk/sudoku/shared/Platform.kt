package com.gurpreetsk.sudoku.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform



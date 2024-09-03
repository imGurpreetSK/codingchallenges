package com.gurpreetsk.sudoku.composeApp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.gurpreetsk.sudoku.shared.SudokuViewModel
import kotlinx.coroutines.Dispatchers

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "sudoku",
    ) {
        App(SudokuViewModel(TODO(), Dispatchers.Main))
    }
}

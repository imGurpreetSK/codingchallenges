package com.gurpreetsk.sudoku.composeApp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.gurpreetsk.sudoku.shared.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    viewModel: SudokuViewModel,
    onClose: () -> Unit
) {
    MaterialTheme {
        val state by viewModel.state.collectAsState()

        Grid(
            state.grid,
            { value, cellCoordinates, valueCoordinates -> viewModel.updateValue(value.toUInt(), cellCoordinates, valueCoordinates) },
            { println("Unsupported key clicked") },
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
        )

        if (state.isSolved) {
            Dialog(onDismissRequest = onClose) {
                Text(
                    "Sudoku solved successfully!",
                    modifier = Modifier
                        .background(Color.White)
                        .padding(32.dp)
                )
            }
        }
    }
}

@Composable
private fun Grid(
    data: Grid,
    onValueUpdated: (value: Int, Coordinates, Coordinates) -> Unit,
    onUnsupportedKeyPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(0.5.dp, Color.Black)
            .defaultMinSize(300.dp, 300.dp)
    ) {
        for (i in 0 until 3) {
            Row(modifier = Modifier.weight(0.33f)) {
                for (j in 0 until 3) {
                    SubGrid(
                        data.items[i][j],
                        { value, valueCoordinates -> onValueUpdated(value, Coordinates(j, i), valueCoordinates) },
                        onUnsupportedKeyPressed,
                        Modifier.weight(0.33f)
                    )
                }
            }
        }
    }
}

@Composable
private fun SubGrid(
    data: SubGrid,
    onValueUpdated: (value: Int, Coordinates) -> Unit,
    onUnsupportedKeyPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(0.36.dp, Color.Black)
    ) {
        for (i in 0..2) {
            Row(
                modifier = Modifier
                    .weight(0.33f)
                    .wrapContentSize()
            ) {
                for (j in 0..2) {
                    Text(
                        data.items[i][j]?.data?.toString() ?: "",
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .border(0.3.dp, Color.Gray)
                            .clickable {  }
                            .onPreviewKeyEvent {
                                if (data.items[i][j]?.isEditable == false) {
                                    return@onPreviewKeyEvent false
                                }

                                if (it.type == KeyEventType.KeyUp) {
                                    if (it.isSupportedKey()) {
                                        onValueUpdated(it.key.nativeKeyCode - 48, Coordinates(j, i))
                                        true
                                    } else {
                                        if (it.key != Key.Tab) {
                                            onUnsupportedKeyPressed()
                                        }
                                        false
                                    }
                                } else {
                                    false
                                }
                            }
                    )
                }
            }
        }
    }
}

private fun KeyEvent.isSupportedKey(): Boolean {
    return key == Key.One ||
            key == Key.Two ||
            key == Key.Three ||
            key == Key.Four ||
            key == Key.Five ||
            key == Key.Six ||
            key == Key.Seven ||
            key == Key.Eight ||
            key == Key.Nine
}

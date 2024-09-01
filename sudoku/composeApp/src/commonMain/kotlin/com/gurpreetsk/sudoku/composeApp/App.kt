package com.gurpreetsk.sudoku.composeApp

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gurpreetsk.sudoku.shared.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val gridState by remember {
            mutableStateOf(
                GridData(
                    arrayOf(
                        arrayOf(
                            Cell(
                                arrayOf(
                                    arrayOf(1u, 1u, 1u),
                                    arrayOf(1u, 1u, 1u),
                                    arrayOf(1u, 1u, 1u),
                                )
                            ),
                            Cell(
                                arrayOf(
                                    arrayOf(2u, 2u, 2u),
                                    arrayOf(2u, 2u, 2u),
                                    arrayOf(2u, 2u, 2u),
                                )
                            ),
                            Cell(
                                arrayOf(
                                    arrayOf(3u, 3u, 3u),
                                    arrayOf(3u, 3u, 3u),
                                    arrayOf(3u, 3u, 3u),
                                )
                            )
                        ),
                        arrayOf(
                            Cell(
                                arrayOf(
                                    arrayOf(4u, 4u, 4u),
                                    arrayOf(4u, 4u, 4u),
                                    arrayOf(4u, 4u, 4u),
                                )
                            ),
                            Cell(
                                arrayOf(
                                    arrayOf(5u, 5u, 5u),
                                    arrayOf(5u, 5u, 5u),
                                    arrayOf(5u, 5u, 5u),
                                )
                            ),
                            Cell(
                                arrayOf(
                                    arrayOf(6u, 6u, 6u),
                                    arrayOf(6u, 6u, 6u),
                                    arrayOf(6u, 6u, 6u),
                                )
                            )
                        ),
                        arrayOf(
                            Cell(
                                arrayOf(
                                    arrayOf(7u, 7u, 7u),
                                    arrayOf(7u, 7u, 7u),
                                    arrayOf(7u, 7u, 7u),
                                )
                            ),
                            Cell(
                                arrayOf(
                                    arrayOf(8u, 8u, 8u),
                                    arrayOf(8u, 8u, 8u),
                                    arrayOf(8u, 8u, 8u),
                                )
                            ),
                            Cell(
                                arrayOf(
                                    arrayOf(9u, 9u, 9u),
                                    arrayOf(9u, 9u, 9u),
                                    arrayOf(9u, 9u, 9u),
                                )
                            )
                        ),
                    )
                )
            )
        }

        Grid(
            gridState,
            { value, cellCoordinates, valueCoordinates -> onValueUpdated(value, cellCoordinates, valueCoordinates) },
            { println("Unsupported key clicked") },
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
        )
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
                        { value, valueCoordinates -> onValueUpdated(value, Coordinates(i, j), valueCoordinates) },
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
                                        onValueUpdated(it.key.nativeKeyCode - 48, Coordinates(i, j))
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

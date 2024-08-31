package com.gurpreetsk.sudoku.composeApp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Grid(
            Modifier
                .aspectRatio(1f)
                .fillMaxSize()
        )
    }
}

@Composable
private fun Grid(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .border(0.5.dp, Color.Black)
            .defaultMinSize(300.dp, 300.dp)
    ) {
        for (i in 0 until 3) {
            Row(modifier = Modifier.weight(0.33f)) {
                for (j in 0 until 3) {
                    UnitBox(Modifier.weight(0.33f))
                }
            }
        }
    }
}

@Composable
private fun UnitBox(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .border(0.36.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier
                .weight(0.33f)
                .wrapContentSize()
        ) {
            Text(
                "A",
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(0.3.dp, Color.Gray)
            )
            Text(
                "B",
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(0.3.dp, Color.Gray)
            )
            Text(
                "C",
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(0.3.dp, Color.Gray)
            )
        }

        Row(
            modifier = Modifier
                .weight(0.33f)
                .wrapContentSize()
        ) {
            Text(
                "D",
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(0.3.dp, Color.Gray)
            )
            Text(
                "E",
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(0.3.dp, Color.Gray)
            )
            Text(
                "F",
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(0.3.dp, Color.Gray)
            )
        }

        Row(
            modifier = Modifier
                .weight(0.33f)
                .wrapContentSize()
        ) {
            Text(
                "G",
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(0.3.dp, Color.Gray)
            )
            Text(
                "H",
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(0.3.dp, Color.Gray)
            )
            Text(
                "I",
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(0.3.dp, Color.Gray)
            )
        }
    }
}

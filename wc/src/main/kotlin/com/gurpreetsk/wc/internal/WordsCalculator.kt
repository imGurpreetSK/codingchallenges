package com.gurpreetsk.wc.internal

import java.io.File

object WordsCalculator : FilePropertyCalculator {

    override fun calculate(path: File): Long {
        var words = 0L
        path.useLines { lines ->
            lines.forEach { line ->
                if (line.isNotBlank()) {
                    words += 1
                }
                words += line.trim().count { it.isWhitespace() }
            }
        }

        return words
    }
}

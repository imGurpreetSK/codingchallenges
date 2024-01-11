package com.gurpreetsk.wc.internal

import java.io.File

internal object CharactersCalculator : FilePropertyCalculator {

    override fun calculate(path: File): Long {
        var count = 0L
        path.bufferedReader().use {
            while (it.read() != -1) {
                count++
            }
        }

        return count
    }
}

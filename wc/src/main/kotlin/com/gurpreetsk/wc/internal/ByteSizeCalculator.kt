package com.gurpreetsk.wc.internal

import java.io.File

internal object ByteSizeCalculator : FilePropertyCalculator {
    override fun calculate(path: File): Long {
        var size = 0L
        path.forEachBlock { _, bytesRead: Int -> size += bytesRead }

        return size
    }
}

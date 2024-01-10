package com.gurpreetsk.wc.internal

import java.io.File

internal object LinesCalculator : FilePropertyCalculator {
    override fun calculate(path: File): Long = path.useLines { it.count() }.toLong()
}

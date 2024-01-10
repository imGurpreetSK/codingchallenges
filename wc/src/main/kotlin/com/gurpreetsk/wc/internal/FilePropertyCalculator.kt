package com.gurpreetsk.wc.internal

import java.io.File

internal interface FilePropertyCalculator {
    fun calculate(path: File): Long
}

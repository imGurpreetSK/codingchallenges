package com.gurpreetsk.wc

import java.io.File

object Ccwc {

    fun run(arg: SupportedArgs, path: File): Pair<Long, String> {
        return if (arg == SupportedArgs.BYTES) {
            getByteCountFor(path)
        } else {
            getLineCountFor(path)
        }
    }

    private fun getLineCountFor(path: File): Pair<Long, String> {
        return path.useLines { it.count() }.toLong() to path.name
    }

    private fun getByteCountFor(path: File): Pair<Long, String> {
        var size = 0L
        path.forEachBlock { _, bytesRead: Int -> size += bytesRead }

        return Pair(size, path.name)
    }

    enum class SupportedArgs(val arg: String) {
        BYTES("-c"),
        LINES("-l"),
    }
}

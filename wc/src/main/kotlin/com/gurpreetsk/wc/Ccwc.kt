package com.gurpreetsk.wc

import java.io.File

object Ccwc {

    fun run(arg: SupportedArgs, path: File): Pair<Long, String> {
        return getByteCountFor(path)
    }

    private fun getByteCountFor(path: File): Pair<Long, String> {
        var size = 0L
        path.forEachBlock { _, bytesRead: Int -> size += bytesRead }

        return Pair(size, path.name)
    }

    enum class SupportedArgs(val arg: String) {
        BYTES("-c"),
    }
}

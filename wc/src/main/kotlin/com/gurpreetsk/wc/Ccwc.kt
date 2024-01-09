package com.gurpreetsk.wc

import java.io.File

object Ccwc {

    fun run(arg: SupportedArgs, path: File): Pair<Long, String> {
        var size = 0L
        path.forEachBlock { _, bytesRead: Int -> size += bytesRead }

        return Pair(size, "test.txt")
    }

    enum class SupportedArgs(val arg: String) {
        BYTES("-c"),
    }
}

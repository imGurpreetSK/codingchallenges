package com.gurpreetsk.wc

import java.io.File

object Ccwc {

    fun run(arg: SupportedArgs, path: File): Pair<Long, String> {
        val size = path.length()

        return Pair(size, "test.txt")
    }

    enum class SupportedArgs(val arg: String) {
        BYTES("-c"),
    }
}

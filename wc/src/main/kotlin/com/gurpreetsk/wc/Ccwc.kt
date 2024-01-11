package com.gurpreetsk.wc

import com.gurpreetsk.wc.internal.*
import java.io.File

object Ccwc {

    private val commandProcessor = mapOf(
        SupportedArgs.BYTES to ByteSizeCalculator,
        SupportedArgs.LINES to LinesCalculator,
        SupportedArgs.WORDS to WordsCalculator,
        SupportedArgs.CHARACTERS to CharactersCalculator
    )

    fun run(arg: SupportedArgs, path: File): Pair<Long, String> {
        if (commandProcessor[arg] == null) {
            throw UnsupportedOperationException("Unknown argument $arg")
        }

        return commandProcessor[arg]!!.calculate(path) to path.name
    }

    enum class SupportedArgs(val arg: String) {
        BYTES("-c"),
        LINES("-l"),
        WORDS("-w"),
        CHARACTERS("-m"),
    }
}

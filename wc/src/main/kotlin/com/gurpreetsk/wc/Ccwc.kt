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

    fun run(arg: SupportedArgs?, path: File): Pair<String, String> {
        if (arg == null) {
            return "${commandProcessor[SupportedArgs.LINES]!!.calculate(path)}  ${commandProcessor[SupportedArgs.WORDS]!!.calculate(path)}  ${commandProcessor[SupportedArgs.BYTES]!!.calculate(path)}" to path.name
        }

        if (commandProcessor[arg] == null) {
            throw UnsupportedOperationException("Unknown argument $arg")
        }

        return commandProcessor[arg]!!.calculate(path).toString() to path.name
    }

    enum class SupportedArgs(val arg: String) {
        BYTES("-c"),
        LINES("-l"),
        WORDS("-w"),
        CHARACTERS("-m"),
    }
}

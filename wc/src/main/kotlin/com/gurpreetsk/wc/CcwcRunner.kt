package com.gurpreetsk.wc

import com.gurpreetsk.wc.internal.*
import java.io.File

object CcwcRunner {

    private val commandProcessor = mapOf(
        SupportedArgs.BYTES to ByteSizeCalculator,
        SupportedArgs.LINES to LinesCalculator,
        SupportedArgs.WORDS to WordsCalculator,
        SupportedArgs.CHARACTERS to CharactersCalculator
    )

    @JvmStatic
    fun main(arguments: Array<String>) {
        val filePath: String
        val arg: String?
        if (arguments.size == 1) {
            arg = null
            filePath = arguments[0]
        } else {
            arg = arguments[0]
            filePath = arguments[1]
        }

        println(
            run(
                SupportedArgs.entries.find { it.arg == arg },
                File(filePath)
            )
        )
    }

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

package com.gurpreetsk.wc

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class CcwcTest {

    @Test
    fun `return number of bytes in file`() {
        val result = CcwcRunner.run(
            CcwcRunner.SupportedArgs.BYTES,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/test.txt")
        )

        assertEquals("342190", result.first)
        assertEquals("test.txt", result.second)
    }

    @Test
    fun `return number of lines in file`() {
        val result = CcwcRunner.run(
            CcwcRunner.SupportedArgs.LINES,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/test.txt")
        )

        assertEquals("7145", result.first)
        assertEquals("test.txt", result.second)
    }

    @Test
    fun `return number of words in file`() {
        val result = CcwcRunner.run(
            CcwcRunner.SupportedArgs.WORDS,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/test.txt")
        )

        assertEquals("58164", result.first)
        assertEquals("test.txt", result.second)
    }

    @Test
    fun `return number of characters in file`() {
        val result = CcwcRunner.run(
            CcwcRunner.SupportedArgs.CHARACTERS,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/test.txt")
        )

        assertEquals("339292", result.first)
        assertEquals("test.txt", result.second)
    }

    @Test
    fun `return line, word and bytes count when no args are passed`() {
        val result = CcwcRunner.run(
            null,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/test.txt")
        )

        assertEquals("7145  58164  342190", result.first)
        assertEquals("test.txt", result.second)
    }
}

package com.gurpreetsk.wc

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class CcwcTest {

    @Test
    fun `return number of bytes in file`() {
        val result = Ccwc.run(
            Ccwc.SupportedArgs.BYTES,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/src/test/resources/test.txt")
        )

        assertEquals("342190", result.first)
        assertEquals("test.txt", result.second)
    }

    @Test
    fun `return number of lines in file`() {
        val result = Ccwc.run(
            Ccwc.SupportedArgs.LINES,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/src/test/resources/test.txt")
        )

        assertEquals("7145", result.first)
        assertEquals("test.txt", result.second)
    }

    @Test
    fun `return number of words in file`() {
        val result = Ccwc.run(
            Ccwc.SupportedArgs.WORDS,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/src/test/resources/test.txt")
        )

        assertEquals("58164", result.first)
        assertEquals("test.txt", result.second)
    }

    @Test
    fun `return number of characters in file`() {
        val result = Ccwc.run(
            Ccwc.SupportedArgs.CHARACTERS,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/src/test/resources/test.txt")
        )

        assertEquals("339292", result.first)
        assertEquals("test.txt", result.second)
    }

    @Test
    fun `return line, word and bytes count when no args are passed`() {
        val result = Ccwc.run(
            null,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/src/test/resources/test.txt")
        )

        assertEquals("7145  58164  342190", result.first)
        assertEquals("test.txt", result.second)
    }
}

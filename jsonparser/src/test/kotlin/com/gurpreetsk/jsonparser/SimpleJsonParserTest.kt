package com.gurpreetsk.jsonparser

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class SimpleJsonParserTest {

    @Test
    fun `return 1 if parsing fails - simple JSON`() {
        val input = javaClass.classLoader.getResourceAsStream("testdata/step1/invalid.json").use {
            it.bufferedReader().readText()
        }

        val result = SimpleJsonParser().parse(input)

        assertThat(result).isEqualTo(1)
    }
}

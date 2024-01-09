package com.gurpreetsk

import com.github.ajalt.clikt.testing.test
import kotlin.test.Test
import kotlin.test.assertEquals

class CanaryTest {

    @Test
    fun `canary test - this will fail if environment setup is incorrect`() {
        val result = CanaryCommand().test()
        assertEquals(result.output, "Success!")
        assertEquals(result.statusCode, 0)
    }
}
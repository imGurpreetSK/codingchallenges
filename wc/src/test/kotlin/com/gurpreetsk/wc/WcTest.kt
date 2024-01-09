package com.gurpreetsk.wc

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class WcTest {

    @Test
    fun `return number of bytes in file`() {
        val result = Ccwc.run(
            Ccwc.SupportedArgs.BYTES,
            File("/Users/gurpreet/IdeaProjects/codingchallenges/wc/src/test/resources/test.txt")
        )

        assertEquals(342190, result.first)
        assertEquals("test.txt", result.second)
    }
}

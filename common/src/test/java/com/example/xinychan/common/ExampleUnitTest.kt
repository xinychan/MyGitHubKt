package com.example.xinychan.common

import com.example.xinychan.common.ext.no
import com.example.xinychan.common.ext.otherwise
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //assertEquals(4, 2 + 2)
        val result1 = true.no {
            1
        }.otherwise {
            2
        }
        assertEquals(result1, 2)
        val result2 = false.no {
            1
        }.otherwise {
            2
        }
        assertEquals(result2, 1)
    }
}

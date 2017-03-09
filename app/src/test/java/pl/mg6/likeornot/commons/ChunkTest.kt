package pl.mg6.likeornot.commons

import org.junit.Assert.assertEquals
import org.junit.Test

class ChunkTest {

    @Test
    fun shouldReturnListOfLists() {
        assertEquals(emptyList<List<Unit>>(), emptyList<Unit>().chunk(6))
        assertEquals(listOf(listOf(Unit)), listOf(Unit).chunk(6))
        assertEquals(listOf(listOf(0), listOf(1)), listOf(0, 1).chunk(1))
        assertEquals(listOf(listOf(3, 2), listOf(1)), listOf(3, 2, 1).chunk(2))
        assertEquals(listOf(listOf(6, 5, 4), listOf(1, 2, 3), listOf(-7, -6)), listOf(6, 5, 4, 1, 2, 3, -7, -6).chunk(3))
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowException() {
        listOf(Unit).chunk(0)
    }
}

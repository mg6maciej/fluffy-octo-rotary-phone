package pl.mg6.likeornot

import org.junit.Test

class LikableServiceTest {

    @Test
    fun shouldReturnEmptyList() {
        val observer = getLikables().test()
        observer.assertValue(emptyList())
    }
}

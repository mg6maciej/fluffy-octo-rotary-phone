package pl.mg6.likeornot

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single.just
import org.junit.Test

class LikableServiceTest {

    private val api: LikableApi = mock()

    @Test
    fun shouldReturnEmptyList() {
        stubApiToReturn(emptyList())
        assertServiceReturns(emptyList())
    }

    @Test
    fun shouldReturnListWithLikable() {
        stubApiToReturn(listOf(michaelJacksonLikableFromApi))
        assertServiceReturns(listOf(michaelJacksonLikable))
    }

    private fun stubApiToReturn(value: List<LikableFromApi>) {
        whenever(api.call()).thenReturn(just(value))
    }

    private fun assertServiceReturns(expected: List<Likable>) {
        getLikables(api).test().assertValue(expected)
    }
}

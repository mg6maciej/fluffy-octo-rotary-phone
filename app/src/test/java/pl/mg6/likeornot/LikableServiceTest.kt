package pl.mg6.likeornot

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.Single.just
import org.junit.Test

class LikableServiceTest {

    private val api: LikableApi = mock()
    private var localLikes: () -> Single<LikableToStatus> = { just(emptyMap()) }

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

    @Test
    fun shouldReturnListWithLikedLikable() {
        stubApiToReturn(listOf(michaelJacksonLikableFromApi))
        localLikes = { just(mapOf(michaelJacksonLikableFromApi.uuid to Status.LIKE)) }
        assertServiceReturns(listOf(michaelJacksonLikable.copy(status = Status.LIKE)))
    }

    private fun stubApiToReturn(value: List<LikableFromApi>) {
        whenever(api.call()).thenReturn(just(value))
    }

    private fun assertServiceReturns(expected: List<Likable>) {
        getLikables(api, localLikes).test().assertValue(expected)
    }
}

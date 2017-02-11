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
        stubApiToReturn(listOf(LikableFromApi(
                uuid = "ba43bbb1-a904-11e1-9412-005056900141",
                name = "Michael Jackson",
                image = listOf("https://d93golxnkabrk.cloudfront.net/things/ba43bbb1-a904-11e1-9412-005056900141.jpg?w=200")
        )))
        assertServiceReturns(listOf(Likable(
                uuid = "ba43bbb1-a904-11e1-9412-005056900141",
                name = "Michael Jackson",
                image = "https://d93golxnkabrk.cloudfront.net/things/ba43bbb1-a904-11e1-9412-005056900141.jpg?w=200"
        )))
    }

    private fun stubApiToReturn(value: List<LikableFromApi>) {
        whenever(api.call()).thenReturn(just(value))
    }

    private fun assertServiceReturns(expected: List<Likable>) {
        getLikables(api).test().assertValue(expected)
    }
}

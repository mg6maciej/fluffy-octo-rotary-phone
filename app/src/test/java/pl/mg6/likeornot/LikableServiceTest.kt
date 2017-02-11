package pl.mg6.likeornot

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single.just
import org.junit.Test

class LikableServiceTest {

    private val api = mock<LikableApi>()

    @Test
    fun shouldReturnEmptyList() {
        whenever(api.call()).thenReturn(just(emptyList()))
        getLikables(api).test().assertValue(emptyList())
    }

    @Test
    fun shouldReturnListWithLikable() {
        whenever(api.call()).thenReturn(just(listOf(LikableFromApi(
                uuid = "ba43bbb1-a904-11e1-9412-005056900141",
                name = "Michael Jackson",
                image = listOf("https://d93golxnkabrk.cloudfront.net/things/ba43bbb1-a904-11e1-9412-005056900141.jpg?w=200")
        ))))
        getLikables(api).test().assertValue(listOf(Likable(
                uuid = "ba43bbb1-a904-11e1-9412-005056900141",
                name = "Michael Jackson",
                image = "https://d93golxnkabrk.cloudfront.net/things/ba43bbb1-a904-11e1-9412-005056900141.jpg?w=200"
        )))
    }
}

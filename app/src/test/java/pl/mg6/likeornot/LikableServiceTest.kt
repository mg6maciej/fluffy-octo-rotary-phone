package pl.mg6.likeornot

import io.reactivex.Single.just
import org.junit.Test

class LikableServiceTest {

    @Test
    fun shouldReturnEmptyList() {
        val api = object : LikableApi {
            override fun call() = just(emptyList<LikableFromApi>())
        }
        val observer = getLikables(api).test()
        observer.assertValue(emptyList())
    }

    @Test
    fun shouldReturnListWithLikable() {
        val api = object : LikableApi {
            override fun call() = just(listOf(LikableFromApi(
                    uuid = "ba43bbb1-a904-11e1-9412-005056900141",
                    name = "Michael Jackson",
                    image = listOf("https://d93golxnkabrk.cloudfront.net/things/ba43bbb1-a904-11e1-9412-005056900141.jpg?w=200")
            )))
        }
        val observer = getLikables(api).test()
        observer.assertValue(listOf(Likable(
                uuid = "ba43bbb1-a904-11e1-9412-005056900141",
                name = "Michael Jackson",
                image = "https://d93golxnkabrk.cloudfront.net/things/ba43bbb1-a904-11e1-9412-005056900141.jpg?w=200"
        )))
    }
}

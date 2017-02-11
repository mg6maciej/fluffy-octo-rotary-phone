package pl.mg6.likeornot

val michaelJacksonLikableFromApi by lazy {
    LikableFromApi(
            uuid = "ba43bbb1-a904-11e1-9412-005056900141",
            name = "Michael Jackson",
            image = listOf("https://d93golxnkabrk.cloudfront.net/things/ba43bbb1-a904-11e1-9412-005056900141.jpg?w=200")
    )
}

val wayneRooneyLikableFromApi by lazy {
    LikableFromApi(
            uuid = "bb526c98-a904-11e1-9412-005056900141",
            name = "Wayne Rooney",
            image = listOf("https://d93golxnkabrk.cloudfront.net/things/bb526c98-a904-11e1-9412-005056900141.jpg?w=200")
    )
}

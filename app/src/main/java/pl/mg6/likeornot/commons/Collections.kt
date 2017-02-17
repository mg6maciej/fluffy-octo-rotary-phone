package pl.mg6.likeornot.commons

fun <T> List<T>.batch(chunkSize: Int): List<List<T>> {
    if (chunkSize <= 0) {
        throw IllegalArgumentException("chunkSize must be greater than 0")
    }
    val list = mutableListOf<MutableList<T>>()
    for (i in 0 until size) {
        if (i % chunkSize == 0) {
            list.add(ArrayList(chunkSize))
        }
        list.last().add(get(i))
    }
    return list
}

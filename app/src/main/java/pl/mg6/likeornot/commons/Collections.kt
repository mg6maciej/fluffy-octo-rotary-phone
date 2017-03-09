package pl.mg6.likeornot.commons

fun <T> List<T>.chunk(chunkSize: Int): List<List<T>> {
    require(chunkSize > 0) { "chunkSize must be greater than 0" }
    val chunksCount = (size + chunkSize - 1) / chunkSize
    val chunkedList = ArrayList<List<T>>(chunksCount)
    for (i in 0 until size step chunkSize) {
        chunkedList.add(subList(i, minOf(i + chunkSize, size)))
    }
    return chunkedList
}

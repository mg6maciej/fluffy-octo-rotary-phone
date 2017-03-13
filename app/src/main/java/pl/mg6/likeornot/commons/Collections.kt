package pl.mg6.likeornot.commons

fun <T> List<T>.chunk(chunkSize: Int): List<List<T>> {
    require(chunkSize > 0) { "chunkSize must be greater than 0" }
    val chunksCount = (size + chunkSize - 1) / chunkSize
    val chunkedList = ArrayList<List<T>>(chunksCount)
    for (fromIndex in 0 until size step chunkSize) {
        val toIndex = minOf(fromIndex + chunkSize, size)
        chunkedList.add(subList(fromIndex, toIndex))
    }
    return chunkedList
}

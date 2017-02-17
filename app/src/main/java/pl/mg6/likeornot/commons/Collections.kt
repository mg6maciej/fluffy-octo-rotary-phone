package pl.mg6.likeornot.commons

fun <T> List<T>.batch(chunkSize: Int): List<List<T>> {
    if (chunkSize <= 0) {
        throw IllegalArgumentException("chunkSize must be greater than 0")
    }
    return mapIndexed { index, item -> IndexedItem(index, item) }
            .groupBy { it.index / chunkSize }
            .map { it.value.map { it.item } }
}

private class IndexedItem<T>(val index: Int, val item: T)

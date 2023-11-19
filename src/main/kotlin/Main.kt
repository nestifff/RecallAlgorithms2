fun main() {
    val list = listOf(1, 1, 2, 3, 3)
    val array: IntArray = IntArray(list.size)
    list.forEachIndexed { index, el ->
        array[index] = el
    }
    val result = Problems2().removeDuplicates_80(array)
    println(array.toList())
    println(result)
}

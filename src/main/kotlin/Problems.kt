import java.util.*
import java.util.ArrayDeque

// number of k combinations from n elements
fun getResult(n: Int, k: Int): Int {
    val m: MutableList<MutableList<Int>> = mutableListOf()
    for (i in 0..n) {
        m.add(MutableList(k + 1) { 0 })
    }
    m[0][0] = 1
    for (i in 0..n) {
        if (i > k) {
            break
        }
        m[i][i] = 1
        m[i][0] = 1
    }
    for (ni in 1..n) {
        for (ki in 1..k) {
            if (ki >= ni) {
                continue
            }
            m[ni][ki] = m[ni - 1][ki] + m[ni - 1][ki - 1]
        }
    }
    print(m)
    return m[n][k]
}

fun bfs(graph: List<Set<Int>>, first: Int) {
    val processed: MutableSet<Int> = mutableSetOf()
    val queue: Queue<Int> = LinkedList()
    queue.add(first)
    processed.add(first)
    while (queue.isNotEmpty()) {
        val currNode = queue.poll()
        doSomeAction(currNode)
        for (neigh in graph[currNode]) {
            if (!processed.contains(neigh)) {
                queue.add(neigh)
                processed.add(neigh)
            }
        }
    }
}

fun dfs(graph: List<Set<Int>>, first: Int) {
    doSomeAction(first)
    dfsRecursion(graph, first, mutableSetOf(first))
}

fun doSomeAction(node: Int) {
    println(node)
}

fun dfsRecursion(graph: List<Set<Int>>, current: Int, processed: MutableSet<Int>) {
    for (neigh in graph[current]) {
        if (!processed.contains(neigh)) {
            doSomeAction(neigh)
            processed.add(neigh)
            dfsRecursion(graph, neigh, processed)
        }
    }
}

fun dfsNoRecursion(graph: List<Set<Int>>, first: Int) {
    val stack = ArrayDeque<Int>()
    stack.addLast(first)
    val processed: MutableSet<Int> = mutableSetOf()
    while(stack.isNotEmpty()) {
        val current = stack.removeLast()
        doSomeAction(current)
        for (neigh in graph[current]) {
            if (!processed.contains(neigh)) {
                stack.addLast(neigh)
                processed.add(neigh)
            }
        }
    }
}

fun getShortestWay(graph: List<Set<Int>>, start: Int, end: Int): Pair<Int, List<Int>> {
    // distance, prev
    val results: MutableList<Pair<Int, Int>> =
            MutableList(graph.size) { Pair(Float.POSITIVE_INFINITY.toInt(), -1) }
    val visited: MutableSet<Int> = mutableSetOf()
    // curr, prev
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(Pair(start, -1))
    while (queue.isNotEmpty()) {
        val current = queue.poll()
        if (current.second < 0 ||
                (results[current.first].first > results[current.second].first + 1)
        ) {
            results[current.first] = if (current.second < 0) {
                Pair(0, current.second)
            } else {
                Pair(results[current.second].first + 1, current.second)
            }
        }
        for (neigh in graph[current.first]) {
            if (!visited.contains(neigh)) {
                queue.add(Pair(neigh, current.first))
            }
        }
        visited.add(current.first)
    }

    val way = mutableListOf<Int>()
    way.add(end)
    var prev = results[end].second
    while (prev >= 0) {
        way.add(prev)
        prev = results[prev].second
    }
    return Pair(results[end].first, way)
}

fun getShortestWaySimple(graph: List<Set<Int>>, start: Int, end: Int): Int {
    val dist: MutableList<Int> = MutableList(graph.size) { Float.POSITIVE_INFINITY.toInt() }
    val visited: MutableSet<Int> = mutableSetOf()
    val queue: Queue<Int> = LinkedList()
    queue.add(start)
    dist[start] = 0
    while (queue.isNotEmpty()) {
        val current = queue.poll()
        for (neigh in graph[current]) {
            if (!visited.contains(neigh)) {
                visited.add(neigh)
                queue.add(neigh)
                dist[neigh] = dist[current] + 1
            }
        }
    }
    return dist[end]
}

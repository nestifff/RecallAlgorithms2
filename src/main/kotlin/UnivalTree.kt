class UnivalTree {

// Part1: A "tree" is "unival" if every node in the tree has the same value. Write a function that given a tree returns whether or not the tree is unival (e.g. true or false)

    fun detectIsTreeUnival(root: Node): Boolean {
        return detectIsTreeUnivalRecursive(root, root.value)
    }

    fun detectIsTreeUnivalRecursive(currNode: Node?, value: Int): Boolean {
        if (currNode == null) {
            return true
        }
        if (currNode.value == value) {
            return detectIsTreeUnivalRecursive(currNode.left, value) &&
                    detectIsTreeUnivalRecursive(currNode.right, value)
        } else {
            return false
        }
    }

    // Part2: Write a function that returns an integer count of how many subtrees within a given tree are unival. (note - the `entire` tree is considered a subtree)

    fun countUnivalTrees(root: Node): Int {
        return countUnivalTreesRecursive(root, root.value).second
    }

    fun countUnivalTreesRecursive(currNode: Node?, value: Int?): Pair<Boolean, Int> {
        if (currNode == null || value == null) {
            return Pair(true, 0)
        }
        val left = countUnivalTreesRecursive(currNode.left, currNode.left?.value)
        val right = countUnivalTreesRecursive(currNode.right, currNode.right?.value)

        val isUnival = (left.first && currNode.value == (currNode.left?.value ?: currNode.value)) &&
                (right.first && currNode.value == (currNode.right?.value ?: currNode.value))

        val newNumOfUnivals = if (isUnival) {
            left.second + right.second + 1
        } else {
            left.second + right.second
        }
        return Pair(isUnival, newNumOfUnivals)
    }
}

data class Node(
    val left: Node?,
    val right: Node?,
    val value: Int
)

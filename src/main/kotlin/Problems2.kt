import java.util.SortedSet

class Problems2 {

    // 26
    fun removeDuplicates_26(nums: IntArray): Int {
        var toInsert = 1
        var numberUnique = 1
        for (i in 1 until nums.size) {
            if (nums[i] != nums[i - 1]) {
                nums[toInsert] = nums[i]
                ++toInsert
                ++numberUnique
            }
        }
        return numberUnique
    }

    fun removeDuplicates_80(nums: IntArray): Int {

        var toInsert = 1
        var currValue = nums[0]
        var valuesNum = 1
        for (i in 1 until nums.size) {
            if (nums[i] == currValue) {
                ++valuesNum
                if (valuesNum <= 2) {
                    nums[toInsert] = nums[i]
                    ++toInsert
                }
            } else if (nums[i] != currValue) {
                nums[toInsert] = nums[i]
                ++toInsert
                currValue = nums[i]
                valuesNum = 1
            }
        }
        return toInsert
    }

    fun groupAnagrams_49(strs: Array<String>): List<List<String>> {
        val map: MutableMap<List<Char>, MutableList<Int>> = mutableMapOf()
        strs.forEachIndexed { i, str ->
            val sorted = str.toCharArray().sorted()
            if (!map.containsKey(sorted)) {
                map[sorted] = mutableListOf()
            }
            map[sorted]!!.add(i)
        }
        val list: MutableList<List<String>> = mutableListOf()
        map.forEach { (_, indexes) ->
            list.add(indexes.map { strs[it] })
        }
        return list
    }

    fun lengthOfLIS_300(nums: IntArray): Int {
        val results = MutableList(nums.size) { 0 }
        results[0] = 1
        for (i in 1 until nums.size) {
            var lengthMax = -1
            for (j in 0 until i) {
                if (results[j] > lengthMax && nums[j] < nums[i]) {
                    lengthMax = results[j]
                }
            }
            if (lengthMax > 0) {
                results[i] = lengthMax + 1
            } else {
                results[i] = 1
            }
        }
        return results.max()
    }

    // [1,2,3,4]
    // [1,2,1,4] 3 2 1
    // [3,4,1,2]
    fun rotate_189(nums: IntArray, k: Int): Unit {
        val savedPart = mutableListOf<Int>()
        val n = nums.size
        if (k >= nums.size) {
            for (i in 0 until n) {
                val temp = nums[(n - 1) - 1]
                nums[(n - 1) - 1] = nums[i]
                nums[i] = temp
            }
            return
        }
        for (i in 0 until (n - k)) {
            savedPart.add(nums[i])
        }
        for (i in 0 until k) {
            nums[i] = nums[(n - k) + i]
        }
        for (i in k until nums.size) {
            nums[i] = savedPart[i - k]
        }
    }

    // n = 4, k = 2
    // [ 1,2,3,4]
    // [ 3,4,1,2]
    // [-1,2,1,4]
    fun getCorrectPosition(n: Int, k: Int, ind: Int): Int {
        val isInK = ind >= (n - k)
        return if (isInK) {
            ind - (n - k)
        } else {
            ind + k
        }
    }
}

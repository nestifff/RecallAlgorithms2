import java.lang.StringBuilder

class ProblemsTemp {


    // Given a string s, reverse the order of characters in each word within a sentence
    // while still preserving whitespace and initial word order.
    //
    //Example 1:
    //Input: s = "Let's take LeetCode contest"
    //Output: "s'teL ekat edoCteeL tsetnoc"
    //Example 2:
    //Input: s = "God Ding"
    //Output: "doG gniD"
    //
    //Constraints:
    //1 <= s.length <= 5 * 104
    //s contains printable ASCII characters.
    //s does not contain any leading or trailing spaces.
    //There is at least one word in s.
    //All the words in s are separated by a single space.

    fun solution1_1(str: String): String {
        val words: MutableList<String> = str.split(" ").toMutableList()
        return words.joinToString(separator = " ") { it.reversed() }
    }

    fun solution1_2(str: String): String {
        val result = StringBuilder(str.length)
        var wordStart = 0
        for (i in str.indices) {
            if (str[i] != ' ') {
                result.insert(wordStart, str[i])
            } else {
                result.insert(i, ' ')
                wordStart = i + 1
            }
        }
        return result.toString()
    }

    // Нужно реализовать такой алгоритм сжатия:

    // AAAABBBCC -> 4A3B2C
    // ABCDDDDDEEE -> ABC5D3E
    // "" -> ""
    // A -> A
    // AABBAA -> 2A2B2A

    fun solve2(str: String): String {
        if (str.isEmpty()) {
            return ""
        }
        val result = StringBuilder()
        var currentLetter: Char = str[0]
        var letterCounter = 0
        for (i in str.indices) {
            if (str[i] == currentLetter) {
                ++letterCounter
            }
            if (str[i] != currentLetter || i == str.length - 1){
                val toAppend = if (letterCounter == 1) {
                    currentLetter.toString()
                } else {
                    "$letterCounter$currentLetter"
                }
                result.append(toAppend)
                currentLetter = str[i]
                letterCounter = 1
            }
        }
        return result.toString()
    }
}

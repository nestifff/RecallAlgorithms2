class PinterestInterviewProblem {

//    private val grid = listOf(
//        listOf('C', 'X', '0', '0', '0', '0', '0'),
//        listOf('0', 'X', '0', '0', '0', '0', '0'),
//        listOf('X', '0', '0', '0', '0', '0', '0'),
//        listOf('0', 'C', 'S', '0', '0', '0', '0'),
//        listOf('0', 'X', '0', '0', '0', '0', '0'),
//        listOf('0', '0', '0', '0', '0', '0', '0'),
//        listOf('0', '0', '0', '0', '0', '0', 'C'),
//    )

    private val grid = listOf(
        listOf('0', '0', '0'),
        listOf('C', 'S', '0'),
        listOf('0', '0', '0'),
    )

    private val start = Pair(1, 1)

    fun solve() {

        val results: MutableList<MutableList<Int>> = mutableListOf()
        val columns = grid.size
        val rows = grid[0].size
        for (i in 0 until columns) {
            results.add(MutableList(rows) { 0 })
        }

        var stepsLeft = 3
        // row, column
        val currentCells: MutableList<Pair<Int, Int>> = mutableListOf()
        val nextCellsToVisit: MutableList<Pair<Int, Int>> = mutableListOf()
        nextCellsToVisit.add(start)

        while (stepsLeft > 0) {

            currentCells.clear()
            currentCells.addAll(nextCellsToVisit)
            nextCellsToVisit.clear()

            for (cell in currentCells) {
                val row = cell.first
                val column = cell.second
                if (grid[row][column] != 'C') {
                    if (row + 1 < rows && grid[row + 1][column] != 'X') {
                        nextCellsToVisit.add(Pair(row + 1, column))
                    }
                    if (column + 1 < columns && grid[row][column + 1] != 'X') {
                        nextCellsToVisit.add(Pair(row, column + 1))
                    }
                    if (row - 1 >= 0 && grid[row - 1][column] != 'X') {
                        nextCellsToVisit.add(Pair(row - 1, column))
                    }
                    if (column - 1 >= 0 && grid[row][column - 1] != 'X') {
                        nextCellsToVisit.add(Pair(row, column - 1))
                    }
                }
            }
            println("Steps left: $stepsLeft")
            println("Next cells: $nextCellsToVisit")
            for (r in results) {
                println(r)
            }
            println("--------")
            for (cell in nextCellsToVisit) {
                results[cell.first][cell.second] += 1
            }
            stepsLeft--
        }
        for (r in results) {
            println(r)
        }
    }
}

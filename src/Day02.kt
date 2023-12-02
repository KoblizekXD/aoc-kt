fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            val id = line.substring(0, line.indexOf(": ")).split(" ")[1].toInt()
            val pairs: List<List<Pair<Int, String>>> = line.substring(line.indexOf(": ")+2, line.length)
                .split("; ").map {
                    it.split(", ")
                }.map {
                    it.map { data ->
                        val d = data.split(" ")
                        Pair(d[0].toInt(), d[1])
                    }
                }
            var possible = true
            // only 12 red cubes, 13 green cubes, and 14 blue cubes
            pairs.forEach {
                it.forEach {
                    if (it.second == "red" && it.first > 12 || it.second == "green" && it.first > 13 || it.second == "blue" && it.first > 14) {
                        possible = false
                    }
                }
            }
            if (possible)
                sum += id
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            val id = line.substring(0, line.indexOf(": ")).split(" ")[1].toInt()
            val pairs: List<List<Pair<Int, String>>> = line.substring(line.indexOf(": ")+2, line.length)
                .split("; ").map {
                    it.split(", ")
                }.map {
                    it.map { data ->
                        val d = data.split(" ")
                        Pair(d[0].toInt(), d[1])
                    }
                }
            var maxGreen = 0
            var maxBlue = 0
            var maxRed = 0
            pairs.forEach { list ->
                list.forEach {
                    if (it.second == "red" && it.first > maxRed) {
                        maxRed = it.first
                    } else if (it.second == "green" && it.first > maxGreen) {
                        maxGreen = it.first
                    } else if (it.second == "blue" && it.first > maxBlue) {
                        maxBlue = it.first
                    }
                }
            }
            sum += maxGreen*maxBlue*maxRed
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    //part1(input).println()
    part2(input).println()
}
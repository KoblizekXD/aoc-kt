fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach { line ->
            var firstDigit: Char? = null
            var lastDigit: Char? = null
            line.forEach {
                if (it.isDigit()) {
                    if (firstDigit == null)
                        firstDigit = it
                    else
                        lastDigit = it
                }
            }
            sum += "$firstDigit${if (lastDigit == null) firstDigit else lastDigit}".toInt()

        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val values = arrayOf(
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
        )
        val regex = "(one|zero|two|three|four|five|six|seven|eight|nine|\\d)\\w*(one|zero|two|three|four|five|six|seven|eight|nine|\\d)\\w*".toRegex()
        val regexAlt = "\\w*(one|zero|two|three|four|five|six|seven|eight|nine|\\d)\\w*".toRegex()
        input.forEach { line ->
            var match = regex.find(line)
            if (match == null) {
                match = regexAlt.find(line)
                if (match == null) {
                    println("error: match not found for $line")
                } else {
                    var first = match.groupValues[1]
                    if (first.toIntOrNull() == null) {
                        first = (values.indexOf(match.groupValues[1])).toString()
                    }
                    sum += "$first$first".toInt()
                }
            } else {
                var first = match.groupValues[1]
                var second = match.groupValues[2]
                if (first.toIntOrNull() == null) {
                    first = (values.indexOf(match.groupValues[1])).toString()
                }
                if (second.toIntOrNull() == null) {
                    second = (values.indexOf(match.groupValues[2])).toString()
                }
                sum += "$first$second".toInt()
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEachIndexed { index, line ->
            val numbers = line.substring(line.indexOf(": ")+2).split(" | ")
                .map {
                    it.chunked(3).map { chunked ->
                        chunked.replace(" ", "")
                    }
                }
            val winning = numbers[0].map { it.toInt() }
            val ticket = numbers[1].map { it.toInt() }
            var amount = 0
            winning.forEach {
                if (ticket.contains(it)) {
                    amount++
                }
            }
            // 4 cisla
            // 1 * 2 * 2 * 2
            // 1 * (amount-1 * 2)
            var total = 1
            for (i in 2..amount) {
                total *= 2
            }
            if (total >= 1 && amount != 0)
                sum += total
        }
        return sum
    }

    // P2

    /**
     * @return Amount of winning numbers in a game
     */
    fun processCard(line: String): Int {
        val numbers = line.substring(line.indexOf(": ")+2).split(" | ")
            .map {
                it.chunked(3).map { chunked ->
                    chunked.replace(" ", "")
                }
            }
        val winning = numbers[0].map { it.toInt() } // Required winning numbers
        val ticket = numbers[1].map { it.toInt() } // Actual ticker numbers
        var amount = 0
        winning.forEach {
            if (ticket.contains(it)) {
                amount++
            }
        }
        return amount
    }

    fun part2(input: List<String>): Int {
        var amount = 0

        fun getCopyCards(line: String, gameNumber: Int): List<Pair<String, Int>> {
            val toRet = ArrayList<Pair<String, Int>>()
            for (i in gameNumber+1..processCard(line)+gameNumber) {
                toRet.add(Pair(input[i-1], i))
            }
            // println("Game $gameNumber is getting ${toRet.size} copy cards!")
            return toRet
        }

        fun calculateCardAmount(line: String, gameNumber: Int): Int {
            var cam = 0
            val copyCards = getCopyCards(line, gameNumber)
            cam += copyCards.size
            copyCards.forEach {
                cam += calculateCardAmount(it.first, it.second)
            }
            return cam
        }

        input.forEachIndexed { index, line ->
            val gameNumber = index+1
            amount++
            amount += calculateCardAmount(line, gameNumber)
        }
        return amount
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    //part1(input).println()
    part2(input).println()
}
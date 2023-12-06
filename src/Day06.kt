fun main() {

    data class Race(val raceTime: Int, val recordDist: Int)

    fun part1(lines: List<String>): Int {
        val mapped: List<Sequence<MatchResult>> = lines.map {
            val regex = "\\d+".toRegex()
            regex.findAll(it)
        }
        val times = mapped[0].map {
            it.value.toInt()
        }.toMutableList()
        val dist = mapped[1].map {
            it.value.toInt()
        }.toMutableList()
        val races = ArrayList<Race>()
        for (i in 0..<times.size) {
            races.add(Race(times[i], dist[i]))
        }
        var beatings = 1
        races.forEach { race ->
            var raceBeats = 0
            for (i in 1..race.raceTime) {
                val travelFor = race.raceTime-i
                if (travelFor*i > race.recordDist)
                    raceBeats++
            }
            beatings *= raceBeats
        }

        return beatings
    }

    fun part2(lines: List<String>): Long {
        val mapped: List<Sequence<MatchResult>> = lines.map {
            val regex = "\\d+".toRegex()
            regex.findAll(it)
        }
        val time = mapped[0].map {
            it.value
        }.toList().joinToString("").toLong()
        val record = mapped[1].map {
            it.value
        }.toList().joinToString("").toLong()
        var beats: Long = 0
        for (i in 1..time) {
            val travelFor = time-i
            if (travelFor*i > record)
                beats++
        }
        return beats
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part2(testInput) == 71503L)

    val input = readInput("Day06")
    //part1(input).println()
    part2(input).println()
}
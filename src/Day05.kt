import java.math.BigInteger
import kotlin.concurrent.thread

fun main() {

    data class Mapping(val source: Long, val dest: Long, val len: Long) {
        fun has(i: Long): Boolean {
            for (j in 0..<len) {
                if (i == dest+j)
                    return true
            }
            return false
        }

        fun get(i: Long): Long {
            for (j in 0..<len) {
                if (i == dest+j)
                    return source+j
            }
            return -1
        }
    }

    fun part1(lines: List<String>): Long {
        val seeds = lines[0].substring(7)
            .split(" ")
            .map { it.toLong() }.toMutableList()
        println("Seeds: $seeds")
        val map: List<List<Mapping>> = lines.subList(2, lines.size)
            .splitIfIs("").map {
                it.subList(1, it.size)
            }.map { abc ->
                abc.map { str ->
                    val l = str.split(" ").map { it.toLong() }
                    Mapping(l[0], l[1], l[2])
                }
            }
        map.forEach { it ->
            // println("===")
            val wasChanged =
            MutableList(seeds.size) {
                false
            }
            it.forEach { map ->
                if (!(wasChanged.all { c -> c })) {
                    seeds.forEachIndexed { index, seed ->
                        val has = map.get(seed)
                        if (has != -1L && !wasChanged[index]) {
                            // println("Changing $seed to $has")
                            seeds[index] = has
                            wasChanged[index] = true
                        }
                    }
                }
            }
        }
        println(seeds)
        return seeds.min()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 35L)

    val input = readInput("Day05")
    part1(input).println()
    //part2(input).println()
}
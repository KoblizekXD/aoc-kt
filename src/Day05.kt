import java.math.BigInteger
import kotlin.concurrent.thread

fun main() {

    class Mapping {
        val maps: ArrayList<HashMap<Long, Long>> = ArrayList()

        override fun toString(): String {
            return maps.toString()
        }
    }

    fun part1(lines: List<String>): Long {
        val seeds = lines[0].substring(7)
            .split(" ")
            .map { it.toLong() }.toMutableList()
        println("Seeds: $seeds")
        val list = ArrayList<Mapping>()
        var mapping: Mapping? = null
        lines.subList(2, lines.size-1).forEach {
            if (it.endsWith(":")) {
                mapping = Mapping()
            } else if (it.isEmpty()) {
                // list.add(mapping!!)
                mapping = null
            } else {
                val split = it.split(" ")
                val source = split[0].toLong()
                val dest = split[1].toLong()
                val len = split[2].toLong()
                val map = HashMap<Long, Long>()
                for (i in 0..<len) {
                    // map[dest+i] = source+i
                    seeds.forEachIndexed { ind, seed ->
                        if (dest+i == seed) {
                            seeds[ind] = source+i
                        }
                    }
                }
                // mapping!!.maps.add(map)
            }
        }
        /*list.add(mapping!!)
        list.forEachIndexed { ind, it ->
            println("$ind)")
            seeds.forEachIndexed { index, i ->
                it.maps.forEach { map ->
                    if (map.containsKey(i)) {
                        if (index==2)
                            println("Changing seed $i to ${map[i]}")
                        seeds[index] = map[i]!!
                        return@forEach
                    }
                }
            }
        }*/
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
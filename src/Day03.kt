fun main() {
    val LINE_LENGTH = 140

    data class Number(val indexes: Pair<Int, Int>, val lineIndex: Int, val number: Int) {
        val possibleSpecial: ArrayList<Pair<Int, Int>> = ArrayList() // Line Index, Index

        init {
            // *NUM*
            if (indexes.first-1 != -1)
                possibleSpecial.add(Pair(lineIndex, indexes.first-1))
            if (indexes.second+1 != LINE_LENGTH)
                possibleSpecial.add(Pair(lineIndex, indexes.second+1))
            if (lineIndex != 0) {
                // ***
                // NUM
                for (i in indexes.first..indexes.second) {
                    possibleSpecial.add(Pair(lineIndex-1, i))
                }
                //*   *
                // NUM
                if (indexes.first-1 != -1)
                    possibleSpecial.add(Pair(lineIndex-1, indexes.first-1))
                if (indexes.second+1 != LINE_LENGTH)
                    possibleSpecial.add(Pair(lineIndex-1, indexes.second+1))
            }
            if (lineIndex < LINE_LENGTH-1) {
                // NUM
                // ***
                for (i in indexes.first..indexes.second) {
                    possibleSpecial.add(Pair(lineIndex+1, i))
                }
                // NUM
                //*   *
                if (indexes.first-1 != -1)
                    possibleSpecial.add(Pair(lineIndex+1, indexes.first-1))
                if (indexes.second+1 != LINE_LENGTH)
                    possibleSpecial.add(Pair(lineIndex+1, indexes.second+1))
            }
        }

        fun hasPossibleLocation(pos: Pair<Int, Int>): Boolean {
            possibleSpecial.forEach {
                if (pos.second == it.second && pos.first == it.first) {
                    return true
                }
            }
            return false
        }
    }

    fun part1(input: List<String>): Int {
        val numbers = ArrayList<Number>()
        val special = ArrayList<Pair<Int, Int>>() // List of dimensions for special chars[lineI, charI]
        // val numbers: ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = ArrayList() // Pair<BeginIndex, EndIndex> , line index, Number

        input.forEachIndexed { lineIndex,  line ->
            val array = line.toCharArray()
            var buffer = ""
            var pairBuf: Pair<Int, Int>? = null
            array.forEachIndexed { index, it ->
                if (it.isDigit()) {
                    buffer += it

                    pairBuf = if (pairBuf == null) {
                        Pair(index, index)
                    } else {
                        Pair(pairBuf!!.first, pairBuf!!.second+1)
                    }
                } else {
                    if (pairBuf != null) {
                        numbers.add(Number(pairBuf!!, lineIndex, buffer.toInt()))
                        // numbers.add(Pair(pairBuf!!, Pair(index, buffer.toInt())))
                        pairBuf = null
                        buffer = ""
                    }
                    if (it != '.') {
                        special.add(Pair(lineIndex, index))
                    }
                }
                if (index == LINE_LENGTH-1 && pairBuf != null) {
                    numbers.add(Number(pairBuf!!, lineIndex, buffer.toInt()))
                    pairBuf = null
                    buffer = ""
                }
            }
        }
        println(numbers.size)
        val foundNumbers = ArrayList<Int>()
        numbers.forEach { number ->
            var found = false
            for (possibleDim in number.possibleSpecial) {
                for (it in special) {
                    if (possibleDim.first == it.first && possibleDim.second == it.second) {
                        // println("Found suitable number: $number")
                        foundNumbers.add(number.number)
                        found = true
                        break
                    }
                }
                if (found) break
            }
        }
        return foundNumbers.sum()
    }

    fun part2(input: List<String>): Int {
        val numbers = ArrayList<Number>()
        val special = ArrayList<Pair<Int, Int>>() // List of dimensions for special chars[lineI, charI]
        // val numbers: ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = ArrayList() // Pair<BeginIndex, EndIndex> , line index, Number

        input.forEachIndexed { lineIndex,  line ->
            val array = line.toCharArray()
            var buffer = ""
            var pairBuf: Pair<Int, Int>? = null
            array.forEachIndexed { index, it ->
                if (it.isDigit()) {
                    buffer += it

                    pairBuf = if (pairBuf == null) {
                        Pair(index, index)
                    } else {
                        Pair(pairBuf!!.first, pairBuf!!.second+1)
                    }
                } else {
                    if (pairBuf != null) {
                        numbers.add(Number(pairBuf!!, lineIndex, buffer.toInt()))
                        // numbers.add(Pair(pairBuf!!, Pair(index, buffer.toInt())))
                        pairBuf = null
                        buffer = ""
                    }
                    if (it == '*') {
                        special.add(Pair(lineIndex, index))
                    }
                }
                if (index == LINE_LENGTH-1 && pairBuf != null) {
                    numbers.add(Number(pairBuf!!, lineIndex, buffer.toInt()))
                    pairBuf = null
                    buffer = ""
                }
            }
        }
        val foundNumbers = ArrayList<Int>()
        special.forEach { pair ->
            var num1: Int? = null
            var num2: Int? = null
            numbers.forEach {
                if (it.hasPossibleLocation(pair)) {
                    if (num1 == null)
                        num1 = it.number
                    else if (num2 == null)
                        num2 = it.number
                }
            }
            if (num1 != null && num2 != null) {
                foundNumbers.add(num2!! * num1!!)
            }
        }
        return foundNumbers.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    //part1(input).println()
    part2(input).println()
}
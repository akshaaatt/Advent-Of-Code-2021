package day3

import readInput

fun main() {
    fun part1(input: List<String>): UInt {
        val middle = input.size / 2
        val gamma = input
            .flatMap {
                    word -> word.mapIndexed {
                    index, char -> Pair(index, char.digitToInt())
            }
            }
            .groupingBy { it.first }
            .fold(0) { acc, pair -> acc + pair.second }
            .mapValues { if (it.value > middle) 1 else 0 }
            .toSortedMap()
            .values.joinToString("")

        val epsilon = gamma
            .map { char -> if (char == '0') '1' else '0' }
            .joinToString("")

        return gamma.toUInt(2) * epsilon.toUInt(2)
    }

    fun part2Sub(input: List<String>, operator: (Int, Int) -> Char): UInt {
        var selected = "0"
        val remaining = mutableListOf<String>()
        remaining.addAll(input)

        for (index in input[0].indices) {
            val grouped = remaining.groupBy { word -> word[index] }
            val chosenKey = operator.invoke(grouped['0']?.size ?: 0, grouped['1']?.size ?: 0)

            remaining.clear()
            remaining.addAll(grouped[chosenKey] ?: emptyList())
            if (remaining.size == 1) {
                selected = remaining[0]
                break
            }
        }

        return selected.toUInt(2)
    }

    fun part2(input : List<String>): UInt {
        val oxygen = part2Sub(input) { zero, one -> if (one >= zero) '1' else '0' }
        val carbon = part2Sub(input) { zero, one -> if (zero <= one) '0' else '1' }
        return oxygen * carbon
    }

    val input = readInput(3)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

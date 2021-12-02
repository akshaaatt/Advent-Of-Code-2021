package day1

import readInputAsInts

fun main() {
    fun part1(input: List<Int>): Int {
        return input.zipWithNext { a, b -> b - a }.filter { it > 0 }.size
    }

    fun part2(input: List<Int>): Int {
        val windowedSum = input.windowed(size = 3).map { it.sum() }
        return part1(windowedSum)
    }

    val input = readInputAsInts(1)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

package day2

import readInput

data class Point(val horizontal: Int, val depth: Int, val aim: Int) {

    private fun splitInput(command: String): Pair<String, Int> {
        val components = command.split(" ")
        val direction = components[0]
        val magnitude = components[1].toInt()
        return Pair(direction, magnitude)
    }

    fun moveWithoutAim(command: String): Point {
        val (direction, magnitude) = splitInput(command)
        return when (direction) {
            "forward"   -> Point(this.horizontal + magnitude, this.depth, this.aim)
            "down"      -> Point(this.horizontal, this.depth + magnitude, this.aim)
            "up"        -> Point(this.horizontal, this.depth - magnitude, this.aim)
            else        -> this
        }
    }

    fun moveWithAim(command: String): Point {
        val (direction, magnitude) = splitInput(command)
        return when (direction) {
            "forward"   -> Point(this.horizontal + magnitude, this.depth + magnitude * this.aim, this.aim)
            "down"      -> Point(this.horizontal, this.depth, this.aim + magnitude)
            "up"        -> Point(this.horizontal, this.depth, this.aim - magnitude)
            else        -> this
        }
    }
}

fun main() {
    fun part(input: List<String>, operation: (Point, String) -> Point): Int {
        val start = Point(0, 0, 0)
        val end = input.fold(start, operation)
        return end.horizontal * end.depth
    }

    fun part1(input: List<String>): Int = part(input, Point::moveWithoutAim)

    fun part2(input: List<String>): Int = part(input, Point::moveWithAim)

    val input = readInput(2)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

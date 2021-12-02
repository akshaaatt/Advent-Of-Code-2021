import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: Int) = File("src/day$day", "Day$day.txt").readLines()

fun readInputAsInts(day: Int) = readInput(day).map { it.toInt() }

/**
 * Converts string to aoc.md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
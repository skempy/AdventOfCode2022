import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInputAsList(name: String, test: String = "") = File("src/${name.lowercase()}", "$name$test.txt")
    .readLines()

fun readInputAsPairs(name: String, test: String = "", breakPoint: Char = ' ') = File("src/${name.lowercase()}", "$name$test.txt")
    .readLines().map { Pair(it.substringBefore(breakPoint).trim(), it.substringAfter(breakPoint).trim()) }

fun readInputAsString(name: String, test: String = "") = File("src/${name.lowercase()}", "$name$test.txt").readText()

fun readInputAsInt(name: String, test: String = "") =
    File("src/${name.lowercase()}", "$name$test.txt").readLines().map { it.toInt() }

fun readInputAsIntList(name: String, test: String = "") =
    File("src/${name.lowercase()}", "$name$test.txt").readLines()[0].split(",").map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

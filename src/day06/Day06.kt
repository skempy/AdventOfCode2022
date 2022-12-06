package day06

import readInputAsList

fun main() {

    fun findMarker(
        input: List<String>,
        sizeOfPacket: Int,
    ): Int {
        return input.first()
            .windowed(sizeOfPacket)
            .indexOfFirst { packet -> packet.toSet().size == sizeOfPacket  } + sizeOfPacket
    }

    fun part1(input: List<String>): Int {
        return findMarker(input, 4)
    }

    fun part2(input: List<String>): Int {
        return findMarker(input, 14)
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInputAsList("Day06", "_test")
    println("Test Part1: ${part1(testInput)}")
    check(part1(testInput) == 7)

    val input = readInputAsList("Day06")
    println("Actual Part1: ${part1(input)}")
    check(part1(input) == 1804)

    println("Test Part2: ${part2(testInput)}")
    check(part2(testInput) == 19)
    println("Actual Part2: ${part2(input)}")
    check(part2(input) == 2508)
}

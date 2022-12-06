package day06

import readInputAsList

fun main() {

    fun findMarker(
        input: List<String>,
        listOfNonRepeatingChars: MutableMap<Int, String>,
        sizeOfPacket: Int,
    ) {
        input.first()
            .windowed(sizeOfPacket)
            .forEachIndexed { index, packet ->
                if (packet.toSet().size == sizeOfPacket) {
                    listOfNonRepeatingChars[index.plus(sizeOfPacket)] = packet
                }
            }
    }

    fun part1(input: List<String>): Int {
        val listOfNonRepeatingChars = mutableMapOf<Int, String>()

        findMarker(input, listOfNonRepeatingChars, 4)

        return listOfNonRepeatingChars.keys.min()
    }

    fun part2(input: List<String>): Int {
        val listOfNonRepeatingChars = mutableMapOf<Int, String>()

        findMarker(input, listOfNonRepeatingChars, 14)

        return listOfNonRepeatingChars.keys.min()
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

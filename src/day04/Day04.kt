package day04

import readInputAsPairs

fun main() {

    fun part1(input: List<Pair<String, String>>): Int {
        val ranges = input.map {
            Pair(
                it.first.substringBefore('-').toInt()..it.first.substringAfter('-').toInt(),
                it.second.substringBefore('-').toInt()..it.second.substringAfter('-').toInt()
            )
        }

        return ranges.count {
            it.first.min() <= it.second.min() && it.first.max() >= it.second.max() ||
                it.second.min() <= it.first.min() && it.second.max() >= it.first.max()
        }
    }

    fun part2(input: List<Pair<String, String>>): Int {
        val ranges = input.map {
            Pair(
                it.first.substringBefore('-').toInt()..it.first.substringAfter('-').toInt(),
                it.second.substringBefore('-').toInt()..it.second.substringAfter('-').toInt()
            )
        }

        return ranges.count {
            it.first.min() <= it.second.max() && it.second.min() <= it.first.max()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsPairs("Day04", "_test", ',')
    println("Test Part1: ${part1(testInput)}")
    check(part1(testInput) == 2)

    val input = readInputAsPairs("Day04", breakPoint = ',')
    println("Actual Part1: ${part1(input)}")
    check(part1(input) == 524)

    println("Test Part2: ${part2(testInput)}")
    check(part2(testInput) == 4)
    println("Actual Part2: ${part2(input)}")
    check(part2(input) == 798)
}

package day05

import readInputAsList

data class CrateInstruction(val quantity: Int, val source: Int, val target: Int)

fun main() {

    fun findStacksOfCrates(
        input: List<String>,
    ): MutableMap<Int, MutableList<Char>> {
        val stacks = mutableMapOf<Int, MutableList<Char>>()
        val stackPosition = mutableListOf<Int>()

        input.first { line -> line.filterNot { it.isWhitespace() }.all { char -> char.isDigit() } }
            .forEachIndexed { index, char ->
                if (!char.isWhitespace()) {
                    stackPosition.add(index)
                }
            }

        stackPosition.forEachIndexed { index1, charPosition ->
            stacks[index1] =
                input.takeWhile { it.contains('[') }
                    .mapNotNull { it.getOrNull(charPosition) }
                    .filter { !it.isWhitespace() }
                    .toMutableList()
        }
        return stacks
    }

    fun parseInstructions(
        input: List<String>,
    ): List<CrateInstruction> {
        return input.dropWhile { !it.startsWith("move") }.map { instruction ->
            val res = "move (\\d+) from (\\d) to (\\d)".toRegex().find(instruction)!!.groupValues
            CrateInstruction(res[1].toInt(), res[2].toInt() - 1, res[3].toInt() - 1)
        }
    }

    fun part1(input: List<String>): String {
        val stacks = findStacksOfCrates(input)

        parseInstructions(input).forEach { instruction ->
            repeat(instruction.quantity) {
                val crateToMove = stacks[instruction.source]?.take(1)!!.single()
                stacks[instruction.source]?.remove(crateToMove)
                stacks[instruction.target]?.add(0, crateToMove)
            }
        }

        return String(stacks.map { it.value.first() }.toCharArray())
    }

    fun part2(input: List<String>): String {
        val stacks = findStacksOfCrates(input)

        parseInstructions(input).forEach { crateInstruction ->
            val crateToMove = stacks[crateInstruction.source]?.take(crateInstruction.quantity)!!
            crateToMove.forEach {
                stacks[crateInstruction.source]?.remove(it)
            }
            stacks[crateInstruction.target]?.addAll(0, crateToMove)
        }

        return String(stacks.filter { it.value.isNotEmpty() }.map { it.value.first() }.toCharArray())
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInputAsList("Day05", "_test")
    println("Test Part1: ${part1(testInput)}")
    check(part1(testInput) == "CMZ")

    val input = readInputAsList("Day05")
    println("Actual Part1: ${part1(input)}")
    check(part1(input) == "VGBBJCRMN")

    println("Test Part2: ${part2(testInput)}")
    check(part2(testInput) == "MCD")
    println("Actual Part2: ${part2(input)}")
    check(part2(input) == "LBBVJBRMH")
}

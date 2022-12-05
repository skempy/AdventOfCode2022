package day05

import readInputAsList

data class CrateInstruction(val quantity: Int, val position1: Int, val position2: Int)

fun main() {

    fun findStacksOfCrates(
        input: List<String>,
        stacks: MutableMap<Int, MutableList<Char>>,
    ): Int {
        val stackPosition = mutableListOf<Int>()
        var stackNumbersLine = 0

        input.forEachIndexed { index, line ->
            if (line.filterNot { it.isWhitespace() }.matches("-?[0-9]+(\\\\\\\\.[0-9]+)?".toRegex())) {
                stackNumbersLine = index
                line.forEachIndexed { index, c ->
                    if (!c.isWhitespace()) {
                        stackPosition.add(index)
                    }
                }
            }
        }

        stackPosition.forEachIndexed { index1, stackPosition ->
            stacks[index1] =
                (0 until stackNumbersLine).filter { it -> !input[it].padEnd(100, ' ')[stackPosition].isWhitespace() }
                    .map { index -> input[index].padEnd(100, ' ')[stackPosition] }.toMutableList()
        }
        return stackNumbersLine
    }

    fun parseInstructions(
        stackNumbersLine: Int,
        input: List<String>,
    ): List<CrateInstruction> {
        val crateInstructions = (stackNumbersLine.plus(2) until input.size).map { it ->
            val parsedInput = input[it].replace("move", "").replace("from", ",").replace("to", ",").replace(" ", "")
            CrateInstruction(
                parsedInput.substringBefore(",").toInt(),
                parsedInput.substringBeforeLast(",").substringAfterLast(",").toInt().minus(1),
                parsedInput.substringAfterLast(",").toInt().minus(1)
            )
        }
        return crateInstructions
    }

    fun part1(input: List<String>): String {
        val stacks = mutableMapOf<Int, MutableList<Char>>()
        val stackNumbersLine = findStacksOfCrates(input, stacks)

        val crateInstructions = parseInstructions(stackNumbersLine, input)

        crateInstructions.forEach { crateInstruction ->
            repeat(crateInstruction.quantity) {
                val crateToMove = stacks[crateInstruction.position1]?.take(1)!!.single()
                stacks[crateInstruction.position1]?.remove(crateToMove)
                stacks[crateInstruction.position2]?.add(0, crateToMove)
            }
        }

        return String(stacks.map { it.value.first() }.toCharArray())
    }

    fun part2(input: List<String>): String {
        val stacks = mutableMapOf<Int, MutableList<Char>>()

        val stackNumbersLine = findStacksOfCrates(input, stacks)

        val crateInstructions = parseInstructions(stackNumbersLine, input)

        crateInstructions.forEach { crateInstruction ->
            val crateToMove = stacks[crateInstruction.position1]?.take(crateInstruction.quantity)!!
            crateToMove.forEach {
                stacks[crateInstruction.position1]?.remove(it)
            }
            stacks[crateInstruction.position2]?.addAll(0, crateToMove)
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

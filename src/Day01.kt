fun main() {

    fun countCaloriesFrom(input: List<String>, listOfElves: MutableList<Int>) {
        var elf = 0
        input.forEach {
            elf = when {
                it.isNotBlank() -> { elf.plus(it.toInt()) }
                else -> { 0.also { listOfElves.add(elf) } }
            }
        }
        listOfElves.add(elf)
    }

    fun part1(input: List<String>): Int {
        val listOfElves = mutableListOf<Int>()
        countCaloriesFrom(input, listOfElves)
        return listOfElves.maxOf { it }
    }

    fun part2(input: List<String>): Int {
        val listOfElves = mutableListOf<Int>()
        countCaloriesFrom(input, listOfElves)
        return listOfElves.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    println("TestPart1: ${part1(testInput)}")
    println("TestPart1: ${part2(testInput)}")

    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println("Actual Part1: ${part1(input)}")
    println("Actual Part2: ${part2(input)}")
}

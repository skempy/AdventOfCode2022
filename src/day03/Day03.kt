package day03

import readInputAsList

enum class Alphabet(val lower: Int, val upper: Int) {
    A(1, 27),
    B(2, 28),
    C(3, 29),
    D(4, 30),
    E(5, 31),
    F(6, 32),
    G(7, 33),
    H(8, 34),
    I(9, 35),
    J(10, 36),
    K(11, 37),
    L(12, 38),
    M(13, 39),
    N(14, 40),
    O(15, 41),
    P(16, 42),
    Q(17, 43),
    R(18, 44),
    S(19, 45),
    T(20, 46),
    U(21, 47),
    V(22, 48),
    W(23, 49),
    X(24, 50),
    Y(25, 51),
    Z(26, 52)
}


fun main() {

    fun part1(input: List<String>): Int {
        val compartments = input.map { it.chunked(it.length / 2) }.map { Pair(it[0], it[1]) }

        val sharedItems = mutableListOf<Char>()

        compartments.forEach {
            sharedItems.add(it.second.first { c -> it.first.contains(c) })
        }

        return sharedItems.sumOf { char ->
            val score = Alphabet.valueOf(char.uppercase())
            if (char.isUpperCase()) {
                score.upper
            } else {
                score.lower
            }
        }
    }

    fun part2(input: List<String>): Int {
        val compartments = input.chunked(3)

        val sharedItems = mutableListOf<Char>()

        compartments.forEach {
            val list1 = it[1].filter { c -> it[0].contains(c) }.toList()
            val list2 = it[2].filter { c -> it[1].contains(c) }.toList()
            sharedItems.add(list1.first { c -> list2.contains(c) })
        }

        return sharedItems.sumOf { char ->
            val score = Alphabet.valueOf(char.uppercase())
            if (char.isUpperCase()) {
                score.upper
            } else {
                score.lower
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsList("Day03", "_test")
    println("Test Part1: ${part1(testInput)}")
    println("Test Part2: ${part2(testInput)}")

    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInputAsList("Day03")
    println("Actual Part1: ${part1(input)}")
    println("Actual Part2: ${part2(input)}")

    check(part1(input) == 7811)
    check(part2(input) == 13022)
}

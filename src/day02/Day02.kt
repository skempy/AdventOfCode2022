package day02

import day02.Result.DRAW
import day02.Result.LOSE
import day02.Result.WIN
import day02.TournamentOptions.Paper
import day02.TournamentOptions.Rock
import day02.TournamentOptions.Scissors
import readInputAsPairs

enum class Result(val points: Int) {
    WIN(6),
    DRAW(3),
    LOSE(0)
}

sealed class TournamentOptions {

    abstract fun versus(option: TournamentOptions): Int
    abstract fun shapePoints(): Int
    abstract fun roundEnd(result: Result): TournamentOptions

    object Rock : TournamentOptions() {

        private val outcomes = mapOf(
            Rock to DRAW,
            Paper to WIN,
            Scissors to LOSE
        )

        override fun versus(option: TournamentOptions): Int = outcomes[option]!!.points.plus(option.shapePoints())
        override fun shapePoints(): Int = 1
        override fun roundEnd(result: Result): TournamentOptions {
            val reversed = outcomes.entries.associate { (k, v) -> v to k }
            return reversed[result]!!
        }
    }

    object Paper : TournamentOptions() {
        private val outcomes = mapOf(
            Rock to LOSE,
            Paper to DRAW,
            Scissors to WIN
        )

        override fun versus(option: TournamentOptions): Int = outcomes[option]!!.points.plus(option.shapePoints())
        override fun shapePoints(): Int = 2
        override fun roundEnd(result: Result): TournamentOptions {
            val reversed = outcomes.entries.associate { (k, v) -> v to k }
            return reversed[result]!!
        }
    }

    object Scissors : TournamentOptions() {
        private val outcomes = mapOf(
            Rock to WIN,
            Paper to LOSE,
            Scissors to DRAW
        )

        override fun versus(option: TournamentOptions): Int = outcomes[option]!!.points.plus(option.shapePoints())
        override fun shapePoints(): Int = 3
        override fun roundEnd(result: Result): TournamentOptions {
            val reversed = outcomes.entries.associate { (k, v) -> v to k }
            return reversed[result]!!
        }
    }
}

fun main() {

    val rock = Pair("A", "X").toList()
    val paper = Pair("B", "Y").toList()
    val scissor = Pair("C", "Z").toList()

    fun mapToTournamentOptions(option: String): TournamentOptions = when {
        rock.contains(option) -> Rock
        paper.contains(option) -> Paper
        scissor.contains(option) -> Scissors
        else -> TODO("YOU SELECTED THE WRONG OPTION YOU SILLY ELF")
    }

    fun mapToResultOptions(option: String): Result = when (option) {
        "Z" -> WIN
        "Y" -> DRAW
        "X" -> LOSE
        else -> TODO("YOU SELECTED THE WRONG OPTION YOU SILLY ELF")
    }

    fun part1(input: List<Pair<String, String>>): Int {
        return input
            .map { Pair(mapToTournamentOptions(it.first), mapToTournamentOptions(it.second)) }
            .sumOf { round -> round.first.versus(round.second) }
    }

    fun part2(input: List<Pair<String, String>>): Int {
        return input
            .map { Pair(mapToTournamentOptions(it.first), mapToResultOptions(it.second)) }
            .sumOf { round -> round.first.versus(round.first.roundEnd(round.second)) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsPairs("Day02", "_test")
    println("Test Part1: ${part1(testInput)}")
    println("Test Part2: ${part2(testInput)}")

    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInputAsPairs("Day02")
    println("Actual Part1: ${part1(input)}")
    println("Actual Part2: ${part2(input)}")

    check(part1(input) == 11841)
    check(part2(input) == 13022)
}

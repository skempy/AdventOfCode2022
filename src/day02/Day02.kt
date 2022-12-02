package day02

import readInputAsPairs

fun main() {

    val rock = Pair("A","X").toList()
    val paper = Pair("B","Y").toList()
    val scissor = Pair("C","Z").toList()

    fun part1(input: List<Pair<String, String>>): Int {
        val score = mutableListOf<Int>()
        input.forEach {
            println("Opponent has ${it.first} and I have ${it.second}")
            when {
                rock.contains(it.first) && paper.contains(it.second) -> score.add(8)
                rock.contains(it.first) && scissor.contains(it.second) -> score.add(3)
                rock.contains(it.first) && rock.contains(it.second) -> score.add(4)
                paper.contains(it.first) && rock.contains(it.second) -> score.add(1)
                paper.contains(it.first) && scissor.contains(it.second) -> score.add(9)
                paper.contains(it.first) && paper.contains(it.second) -> score.add(5)
                scissor.contains(it.first) && rock.contains(it.second) -> score.add(7)
                scissor.contains(it.first) && paper.contains(it.second) -> score.add(2)
                scissor.contains(it.first) && scissor.contains(it.second) -> score.add(6)
            }
        }
        return score.sum()
    }

    fun part2(input: List<Pair<String, String>>): Int {
        val score = mutableListOf<Int>()
        input.forEach {
            println("Opponent has ${it.first} and I have ${it.second}")
            when {
                rock.contains(it.first) && paper.contains(it.second) -> score.add(4)
                rock.contains(it.first) && scissor.contains(it.second) -> score.add(8)
                rock.contains(it.first) && rock.contains(it.second) -> score.add(3)
                paper.contains(it.first) && rock.contains(it.second) -> score.add(1)
                paper.contains(it.first) && scissor.contains(it.second) -> score.add(9)
                paper.contains(it.first) && paper.contains(it.second) -> score.add(5)
                scissor.contains(it.first) && rock.contains(it.second) -> score.add(2)
                scissor.contains(it.first) && paper.contains(it.second) -> score.add(6)
                scissor.contains(it.first) && scissor.contains(it.second) -> score.add(7)
            }
        }
        return score.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsPairs("Day02", "_test")
    println("TestPart1: ${part1(testInput)}")
    println("TestPart1: ${part2(testInput)}")

    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInputAsPairs("Day02")
    println("Actual Part1: ${part1(input)}")
    println("Actual Part2: ${part2(input)}")


}

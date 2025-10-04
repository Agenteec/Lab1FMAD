package com.example.lab1fmad

import kotlin.random.Random


class LabLogic(private val numbersProvider: () -> List<Int> = ::defaultNumbers) {
    val numbers: List<Int> get() = numbersProvider()

    companion object {
        private fun defaultNumbers(size: Int = 20, min: Int = -20, max: Int = 20): List<Int> =
            require(min <= max).let { List(size) { Random.nextInt(from = min, until = max + 1) } }
    }

    fun sumOfNegativeElements(nums: List<Int> = numbers): Int =
        nums.asSequence().filter { it < 0 }.sum()

    fun countOfPositiveElements(nums: List<Int> = numbers): Int =
        nums.asSequence().count { it > 0 }.toInt()
}
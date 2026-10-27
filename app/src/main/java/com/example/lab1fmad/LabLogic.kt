package com.example.lab1fmad

import kotlin.random.Random

data class NormalizationResult(
    val source: List<Int>,
    val max: Int?,
    val normalized: List<Double>
)

class LabLogic(private val random: Random = Random.Default) {

    fun generateNumbers(size: Int = 10, min: Int = 1, max: Int = 100): List<Int> =
        require(size > 0 && min <= max).let { List(size) { random.nextInt(min, max + 1) } }

    fun normalizeByMax(nums: List<Int>): NormalizationResult =
        nums.maxOrNull()
            ?.takeIf { it != 0 }
            ?.let { max -> NormalizationResult(nums, max, nums.map { it.toDouble() / max }) }
            ?: NormalizationResult(nums, nums.maxOrNull(), emptyList())
}

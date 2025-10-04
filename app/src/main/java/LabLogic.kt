import kotlin.random.Random

class LabLogic {
    val numbers: List<Int>

    constructor() {
        numbers = generateRandomList()
    }

    constructor(numbers: List<Int>) {
        this.numbers = numbers.toList()
    }

    private fun generateRandomList(size: Int = 20, min: Int = -20, max: Int = 20): List<Int> {
        require(min <= max)
        return List(size) { Random.nextInt(from = min, until = max + 1) }
    }

    fun sumOfNegativeElements(): Int = numbers.filter { it < 0 }.sum()

    fun countOfPositiveElements(): Int = numbers.count { it > 0 }
}
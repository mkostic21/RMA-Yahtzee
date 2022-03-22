import com.sun.org.apache.xpath.internal.operations.Bool

class Score {
    var roundCounter = 1
        private set
    private var rolledValues = mutableListOf<Int>()

    fun update(rolls: List<Int>) {
        rolledValues.addAll(rolls)
        rolledValues.checkRolls()
    }

    fun reset() {
        roundCounter = 1
        rolledValues.clear()
    }

    fun MutableList<Int>.checkRolls() {
        val result = when {
            isYahtzee() -> "Yahtzee"
            isPoker() -> "Poker"
//            isSmallStraight() -> "Small Straight"
//            isLongStraight() -> "Long Straight"
            else -> "Unlucky"
        }
        println(result)
    }

    private fun isYahtzee(): Boolean {
        rolledValues.sort()
        return rolledValues.first() == rolledValues.last()
    }

    private fun isPoker(): Boolean {
        rolledValues.sort()
        return when {
            rolledValues.first() == rolledValues[3] -> true
            rolledValues[1] == rolledValues[4] -> true
            rolledValues[2] == rolledValues.last() -> true
            else -> false
        }
    }

    private fun isSmallStraight(): Boolean {
        return true
    }
//
//    private fun isLongStraight(): Boolean {
//
//    }
}
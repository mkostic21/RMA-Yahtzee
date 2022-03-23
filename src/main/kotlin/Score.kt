class Score {
    var roundCounter = 1
        private set
    private var rolledValues = mutableListOf<Int>()

    fun update(rolls: List<Int>) {
        rolledValues.clear()
        rolledValues.addAll(rolls)
        checkRolls()
        roundCounter++
    }

    fun reset() {
        roundCounter = 1
        rolledValues.clear()
    }

    private fun checkRolls() {
        rolledValues.sort()
        val result = when {
            isYahtzee() -> "Yahtzee"
            isFullHouse() -> "Full House"
            isPoker() -> "Poker"
            isThreeOfAKind() -> "Three Of A Kind"
            isLongStraight() -> "Long Straight"
            isSmallStraight() -> "Small Straight"
            else -> "Unlucky"
        }
        println(result + "\n")
    }

    private fun isYahtzee(): Boolean {
        return rolledValues.first() == rolledValues.last()
    }

    private fun isPoker(): Boolean {
        return when {
            rolledValues.first() == rolledValues[3] -> true
            rolledValues[1] == rolledValues[4] -> true
            rolledValues[2] == rolledValues.last() -> true
            else -> false
        }
    }

    private fun isSmallStraight(): Boolean {
        return rolledValues.containsAll(listOf(1, 2, 3, 4, 5))
    }

    private fun isLongStraight(): Boolean {
        return rolledValues.containsAll(listOf(2, 3, 4, 5, 6))
    }

    private fun isThreeOfAKind(): Boolean {
        return when {
            rolledValues.first() == rolledValues[1] && rolledValues.first() == rolledValues[2] -> true
            rolledValues[1] == rolledValues[2] && rolledValues[1] == rolledValues[3] -> true
            rolledValues[2] == rolledValues[3] && rolledValues[2] == rolledValues[4]-> true
            rolledValues[3] == rolledValues[4] && rolledValues[3] == rolledValues.last() -> true
            else -> false
        }
    }

    private fun isFullHouse(): Boolean {
        if(isPoker()){
            return when {
                rolledValues.first() == rolledValues.last() -> true
                rolledValues.first() == rolledValues[1] -> true
                rolledValues[4] == rolledValues.last() -> true
                else -> false
            }
        }
        return false
    }
}
import kotlin.properties.Delegates
import kotlin.system.exitProcess

class GameCLI(val score: Score, val dice: List<Die>) {
    private var input: String by Delegates.observable("") { _, _, new ->
        when (new.lowercase()) {
            "start" -> startGame()
            "quit" -> exitProcess(0)
        }
    }

    fun start() {
        println("type 'start' to begin")
        println("type 'quit' to exit")
        input = readln()
    }

    private fun startGame() {
        if (score.roundCounter == 3) {
            println("Welcome to round ${score.roundCounter}.")
            rollDie()

            score.reset()
            start()
        }
        rollDie()

        var userResponse = ""
        userResponse = promptUserToSaveScore()
        var isDiceLockValid = false

        while (userResponse != "y" && userResponse != "n") {
            userResponse = promptUserToSaveScore()
        }
        when (userResponse) {
            "y" -> {
                score.reset()
                start()
            }
            "n" -> {
                while (!isDiceLockValid) {
                    isDiceLockValid = promptDiceLock()
                }
                startGame()
            }
        }
    }

    private fun parseDiceLockInput(input: String): Boolean {
        dice.forEach { it.unlock() }

        if (input.isEmpty()) return true
        val indices = input.split(" ").filter { it.isNotEmpty() && it != " " }.map { it.toInt() - 1 }
        indices.forEach { index ->
            if (index in 0..5) {
                dice[index].lock()
            } else return false
        }
        return true
    }

    private fun promptUserToSaveScore(): String {
        println("Save score? [y/n]")
        return readln().lowercase()
    }

    private fun promptDiceLock(): Boolean {
        println("Lock dice?")
        println("example: 1 2 5")

        val userInput = readln().trim()

        userInput.filterNot { it.isWhitespace() }.forEach {
            if (!Character.isDigit(it)) {
                println("Invalid input")
                return false
            }
        }
        return parseDiceLockInput(input = userInput)
    }

    private fun rollDie() {
        val rolled = dice.map { die ->
            die.roll()
            die.value
        }

        println(rolled)
        score.update(rolls = rolled)
    }

}
import kotlin.properties.Delegates
import kotlin.system.exitProcess

class GameCLI(val score: Score, val dice: List<Die>) {
    var input: String by Delegates.observable("") { _, _, new ->
        when (new.lowercase()) {
            "start" -> startGame()
            "reset" -> resetGame()
            "quit" -> exitProcess(0)
        }
    }

    fun start() {
        println("Welcome to round ${score.roundCounter}.")
        println("type 'start' to begin")
        println("type 'reset' to reset the game")
        println("type 'quit' to exit")
        input = readln()
    }

    private fun startGame(){
//        val rolled = dice.map { die ->
//            die.roll()
//            die.value
//        }
        val rolled = listOf(2,4,3,1,1,1)
        score.update(rolls = rolled)
    }

    private fun resetGame(){
        score.reset()
        start()
    }

}
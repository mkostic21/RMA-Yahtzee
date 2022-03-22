fun main() {
    val score = Score()
    val dice = List(6) { Die() }
    val game = GameCLI(score = score, dice = dice)

    //begin game
    game.start()
}
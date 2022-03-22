class Die {
    var value = (1..6).random() //sets initial value to random number
        private set
    var isLocked = false
        private set

    fun roll() {
        when (isLocked) {
            false -> value = (1..6).random()
            true -> return
        }
    }

    fun lock() {
        isLocked = true
    }

    fun unlock() {
        isLocked = false
    }

}
class Die {
    var value = (1..6).random() //sets initial value to random number
        private set
    var isLocked = false
        private set

    fun lock() {
        isLocked = true
    }

    fun unlock() {
        isLocked = false
    }

    fun roll(){
        if(!isLocked){
            value = (1..6).random()
        }
    }

}
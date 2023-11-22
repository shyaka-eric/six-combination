class ColorGame {
    private val colors = listOf('R', 'B', 'Y', 'G', 'O', 'W')
    private val secretCombination: List<Char> = colors.shuffled().take(4)
    private var attempts = 0

    fun play() {
        while (true) {
            val guess = getGuess()
            val feedback = provideFeedback(guess)
            println("Feedback: $feedback")
            attempts++

            if (feedback == "Correct Guess!") {
                println("Congratulations! You guessed the correct combination in $attempts attempts.")
                break
            }
        }
    }

    private fun getGuess(): List<Char> =
        print("Enter your guess (e.g., RBYG): ").run {
            readLine()?.toUpperCase()?.filter { it in "RBYGOW" }?.toList() ?: emptyList()
        }

    private fun provideFeedback(guess: List<Char>): String =
        if (secretCombination == guess) "Correct Guess!"
        else "${secretCombination.zip(guess).count { it.first == it.second }} correct position, " +
                "${secretCombination.intersect(guess).count() - secretCombination.zip(guess).count { it.first == it.second }} correct color"
}

fun main() = ColorGame().play()

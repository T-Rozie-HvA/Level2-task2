package com.rozie.swipequiz

data class Questions(
    var question: String,
    var answer: Boolean
) {
    companion object {
        val SWIPE_QUIZ_QUESTIONS = arrayOf(
            "A 'val' and 'var' are the same.",
            "Mobile Application Development grans 12 ECTS.",
            "A Unit in Kotlin corresponds to a void in Java.",
            "In Kotlin 'when' replaces the 'switch' operator in Java."
        )

        val SWIPE_QUIZ_ANSWERS = arrayOf(
            false,
            true,
            false,
            true
        )
    }
}

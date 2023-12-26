package com.example.numbercomposition.domain.entity

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
) {
    val righAnswer: Int
        get() = sum - visibleNumber
}
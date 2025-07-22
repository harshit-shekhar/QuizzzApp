package com.harx.quizzzapp

data class QuizModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val time: String,
    val QuestionList: List<QuestionModel>


)
{
    constructor(): this("","","","", emptyList())
}

data class QuestionModel(
    val question: String,
    val option: List<String>,
    val correct: String,

){
    constructor(): this("", emptyList(),"")
}


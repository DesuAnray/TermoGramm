package org.example.project.ViewModel

import androidx.compose.ui.graphics.Color

data class InputData(
    var color: Color,
    val onChecked: Boolean, //Выделение элемента,
    val projectSection: String, //Раздел проекта
    val date: String, //Дата проведени НК
    val numberOfTheWeldedJoint: String, //Номер св. соед.
    val dimeterAndThickness: String, //диметр и толщина
    val steelGrade: String, //Марка Стали
    val time: String
)

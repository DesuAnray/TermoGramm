package org.example.project.ViewModel

data class InputData(
    val onChecked: Boolean, //Выделение элемента,
    val projectSection: String, //Раздел проекта
    val date: String, //Дата проведени НК
    val numberOfTheWeldedJoint: String, //Номер св. соед.
    val dimeterAndThickness: String, //диметр и толщина
    val steelGrade: String, //Марка Стали
    val time: String
)

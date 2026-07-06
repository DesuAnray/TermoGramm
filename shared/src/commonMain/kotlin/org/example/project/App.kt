package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {

    val coroutineScope = rememberCoroutineScope()

    val messagePaymentNumber = remember { mutableStateOf("") }
    val messageChartIndicator = remember { mutableStateOf("") }
    val messageDate = remember { mutableStateOf("") }
    val messageProductionFacility = remember { mutableStateOf("") }
    val messageProductionLineNumber = remember { mutableStateOf("") }
    val messageChartNumber = remember { mutableStateOf("") }
    val messageLengthOfConstruction = remember { mutableStateOf("") }
    val messageSteelGrand = remember { mutableStateOf("") }
    val messageHeadOfWorks = remember { mutableStateOf("") }
    val messageOperator = remember { mutableStateOf("") }
    val messageInitialTime = remember { mutableStateOf("") }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center,
        ){
            Column {
                Box(){
                    Column {
                        Row {
                            outLinedTextField(messagePaymentNumber, "Номер расчёта")
                            outLinedTextField(messageChartIndicator, "Номер диаграммы")
                            outLinedTextField(messageDate, "Дата")
                        }
                        Row {
                            outLinedTextField(messageProductionFacility, "Объект")
                        }
                        Row {
                            outLinedTextField(messageProductionLineNumber, "Номер линии")
                        }
                        Row {
                            outLinedTextField(messageChartNumber, "Номер диаграммы")
                            outLinedTextField(messageLengthOfConstruction, "Толщина")
                            outLinedTextField(messageSteelGrand, "Марка стали")
                        }
                        Row {
                            outLinedTextField(messageHeadOfWorks, "Руководитель")
                            outLinedTextField(messageOperator, "Оператор")
                            outLinedTextField(messageInitialTime, "Время")
                        }
                        Row(
                            modifier = Modifier
                                .padding(top = 30.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Button(
                                onClick = {
                                    coroutineScope.launch {

                                    }
                                }
                            ){
                                Text(
                                    text = "Создать PDF",
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun outLinedTextField(message:MutableState<String>, labelText: String): MutableState<String>{
    OutlinedTextField(
        modifier = Modifier
            .padding(10.dp, 10.dp)
            .width(200.dp)
            .height(70.dp),
        value = message.value,
        onValueChange = { newText -> message.value = newText},
        label = {
            Text(
                text = labelText,
                color = Color.White,
                fontSize = 10.sp,
            ) },
        placeholder = {
            Text(
                text = "Введите значение",
                color = Color.White,
                fontSize = 14.sp,
            ) },
        shape = RoundedCornerShape(18.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Cyan
        )
    )
    return message
}
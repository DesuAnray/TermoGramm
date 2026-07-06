package org.example.project.screens

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.ViewModel.OutlineTextFieldViewModel

@Composable
fun ConstantValuesScreen(navController: NavController, viewModel: OutlineTextFieldViewModel) {
    val messageDiagram = remember { mutableStateOf("") }
    val messageObject = remember { mutableStateOf("") }
    val messageSupervisor = remember { mutableStateOf("") }
    val messageOperator = remember { mutableStateOf("") }

    MaterialTheme{
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextFieldConstantValueScreen(messageDiagram, "Раздел проекта")
                OutlinedTextFieldConstantValueScreen(messageObject, "Объект")
                OutlinedTextFieldConstantValueScreen(messageSupervisor, "Руководитель")
                OutlinedTextFieldConstantValueScreen(messageOperator, "Оператор")

                Button(
                    modifier = Modifier
                        .padding(15.dp)
                        .width(180.dp)
                        .height(50.dp)
                        ,
                    onClick = {
                        viewModel.constValue.diagram = messageDiagram.value
                        viewModel.constValue.objectControl = messageObject.value
                        viewModel.constValue.supervisor = messageSupervisor.value
                        viewModel.constValue.operator = messageOperator.value
                        navController.navigate("DynamicValueScreen")
                    },
                    ){
                    Text(
                            text = "Далее",
                            color = Color.White,
                            fontSize = 14.sp,)
                    }

            }
        }
    }
}

@Composable
fun OutlinedTextFieldConstantValueScreen(message: MutableState<String>, label: String){
    OutlinedTextField(
        modifier = Modifier.padding(4.dp),
        value = message.value,
        onValueChange = { newText -> message.value = newText},
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Blue,
            focusedTextColor = Color.Black
        ),
        shape = RoundedCornerShape(10.dp),
        label = {Text(
            text = label,
            color = Color.Black,
            fontSize = 14.sp,
        )},
        placeholder = {
            Text(
                text = "Введите значение",
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    )
}
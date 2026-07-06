package org.example.project.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.ViewModel.InputData
import org.example.project.ViewModel.OutlineTextFieldViewModel
import org.example.project.screens.UIComponents.ImageButton
import org.jetbrains.compose.resources.InternalResourceApi
import termogramm.shared.generated.resources.IconAdd
import termogramm.shared.generated.resources.IconDelete
import termogramm.shared.generated.resources.iconBack
import termogramm.shared.generated.resources.Res
import termogramm.shared.generated.resources.iconSave


@OptIn(InternalResourceApi::class, ExperimentalFoundationApi::class)
@Composable
fun DynamicValueScreen(navController: NavController, viewModel: OutlineTextFieldViewModel) {
    val inputListText by viewModel.inputText.collectAsState()
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier.weight(0.15f),
            verticalArrangement = Arrangement.Center
        ) {
            ImageButton(
                text = "Вернуться на первую страницу",
                image = Res.drawable.iconBack,
                contentDescription = "Back",
                onClick = {
                    viewModel.constValue.diagram = ""
                    viewModel.constValue.objectControl = ""
                    viewModel.constValue.supervisor = ""
                    viewModel.constValue.operator = ""
                    navController.navigate("ConstantValueScreen")
                }
            )
            ImageButton(
                text = "Вернуться на первую страницу",
                image = Res.drawable.iconSave,
                contentDescription = "Save",
                onClick = {
                    viewModel.addXlsx()
                }
            )
            ImageButton(
                text = "Вернуться на первую страницу",
                image = Res.drawable.IconAdd,
                contentDescription = "Add new value",
                onClick = {
                    viewModel.createInput(
                        InputData(
                            "", "",
                            "", "", ""
                        )
                    )
                }
            )
            ImageButton(
                text = "Вернуться на первую страницу",
                image = Res.drawable.IconDelete,
                contentDescription = "Delete value",
                onClick = {
                    viewModel.removeItem()
                }
            )
        }
        Column(
            modifier = Modifier.weight(0.85f)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    LazyRow {
                        items(inputListText.size) { it ->
                            Column {

                                OutlinedTextField(
                                    modifier = Modifier
                                        .padding(4.dp),
                                    value = viewModel.getInputText(it).date,
                                    onValueChange = { newText: String -> viewModel.updateInputDate(it, newText) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Blue,
                                        focusedTextColor = Color.Black
                                    ),

                                    shape = RoundedCornerShape(10.dp),
                                    label = {
                                        Text(
                                            text = "Дата проведени НК",
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                        )
                                    },
                                    placeholder = {
                                        Text(
                                            text = "Введите значение",
                                            color = Color.Black,
                                            fontSize = 14.sp
                                        )
                                    }
                                )
                                OutlinedTextField(
                                    modifier = Modifier.padding(4.dp),
                                    value = viewModel.getInputText(it).numberOfTheWeldedJoint,
                                    onValueChange = { newText: String ->
                                        viewModel.updateInputNumberOfTheWeldedJoint(
                                            it,
                                            newText
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Blue,
                                        focusedTextColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    label = {
                                        Text(
                                            text = "Номер св. соед.",
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                        )
                                    },
                                    placeholder = {
                                        Text(
                                            text = "Введите значение",
                                            color = Color.Black,
                                            fontSize = 14.sp
                                        )
                                    }
                                )
                                OutlinedTextField(
                                    modifier = Modifier.padding(4.dp),
                                    value = viewModel.getInputText(it).dimeterAndThickness,
                                    onValueChange = { newText: String ->
                                        viewModel.updateInputDimeterAndThickness(
                                            it,
                                            newText
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Blue,
                                        focusedTextColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    label = {
                                        Text(
                                            text = "диметр и толщина",
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                        )
                                    },
                                    placeholder = {
                                        Text(
                                            text = "Введите значение",
                                            color = Color.Black,
                                            fontSize = 14.sp
                                        )
                                    }
                                )
                                OutlinedTextField(
                                    modifier = Modifier.padding(4.dp),
                                    value = viewModel.getInputText(it).steelGrade,
                                    onValueChange = { newText: String -> viewModel.updateInputSteelGrade(it, newText) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Blue,
                                        focusedTextColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    label = {
                                        Text(
                                            text = "Марка Стали",
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                        )
                                    },
                                    placeholder = {
                                        Text(
                                            text = "Введите значение",
                                            color = Color.Black,
                                            fontSize = 14.sp
                                        )
                                    }
                                )
                                OutlinedTextField(
                                    modifier = Modifier.padding(4.dp),
                                    value = viewModel.getInputText(it).time,
                                    onValueChange = { newText: String -> viewModel.updateInputTime(it, newText) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Blue,
                                        focusedTextColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    label = {
                                        Text(
                                            text = "Время",
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                        )
                                    },
                                    placeholder = {
                                        Text(
                                            text = "Введите значение",
                                            color = Color.Black,
                                            fontSize = 14.sp
                                        )
                                    }
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}
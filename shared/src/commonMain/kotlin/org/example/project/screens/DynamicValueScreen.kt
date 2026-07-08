package org.example.project.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.visible
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.rememberLifecycleOwner
import androidx.navigation.NavController
import org.example.project.ViewModel.OutlineTextFieldViewModel
import org.example.project.screens.UIComponents.ColorBox
import org.example.project.screens.UIComponents.ImageButton
import org.jetbrains.compose.resources.InternalResourceApi
import termogramm.shared.generated.resources.IconAdd
import termogramm.shared.generated.resources.IconDelete
import termogramm.shared.generated.resources.IconSelectItem
import termogramm.shared.generated.resources.iconBack
import termogramm.shared.generated.resources.Res
import termogramm.shared.generated.resources.iconSave


@OptIn(InternalResourceApi::class, ExperimentalFoundationApi::class)
@Composable
fun DynamicValueScreen(navController: NavController, viewModel: OutlineTextFieldViewModel) {
    val inputListText by viewModel.inputText.collectAsState()
    val visibleState = remember { mutableStateOf(false) }



    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier.weight(0.08f),
            verticalArrangement = Arrangement.Center
        ) {
           Row(
               modifier = Modifier.weight(0.5f),
               verticalAlignment = Alignment.Top
           ){
               Column(
                   modifier = Modifier.padding(start = 10.dp)
               ) {
                   Row(
                       modifier = Modifier.padding(bottom = 25.dp)
                   ) {
                       //Кнопка для возвращения на начальный экран
                       ImageButton(
                           image = Res.drawable.iconBack,
                           contentDescription = "Back",
                           onClick = {
                               viewModel.constValue.objectControl = ""
                               viewModel.constValue.supervisor = ""
                               viewModel.constValue.operator = ""
                               viewModel.removeAll()

                               navController.navigate("ConstantValueScreen")
                           }
                       )
                   }
                   //Кнопка для выделения несокольких элементо для дальнеших манипуляций с ними
                   ImageButton(
                       "Выделить",
                       contentDescription = "Select Items",
                       onClick = {
                           visibleState.value = !visibleState.value
                       },
                       image = Res.drawable.IconSelectItem
                   )
                   //Кнопка для удаления элементов из списка.
                   //При выделении нескольких элементов удаляет их
                   ImageButton(
                       text = "Удалить",
                       image = Res.drawable.IconDelete,
                       contentDescription = "Delete value",
                       onClick = {
                           if (visibleState.value){
                               viewModel.removeSelectedItem()
                           }
                           else{
                               viewModel.removeItem()
                           }

                       }
                   )
                   //Добовляет элемент в список
                   ImageButton(
                       text = "Добавить",
                       image = Res.drawable.IconAdd,
                       contentDescription = "Add new value",
                       onClick = {
                           viewModel.createInput(

                           )
                       }
                   )
                   //Вызывает меню для дальнейшего сохранения файла в папку
                   ImageButton(
                       text = "Сохранить",
                       image = Res.drawable.iconSave,
                       contentDescription = "Save",
                       onClick = {
                           viewModel.addXlsx()
                       }
                   )
               }
           }
            Row(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(20.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ColorBox(Color.Yellow)
                    ColorBox(Color.Red)
                    ColorBox(Color.Gray)
                    ColorBox(Color.Green)
                    ColorBox(Color.Magenta)
                }
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ColorBox(Color.Cyan)
                    ColorBox(Color(255,165,0))
                    ColorBox(Color(0, 179, 255))
                    ColorBox(Color(255, 128, 128))
                    ColorBox(Color(225, 0, 255))
                }
            }
        }
        Column(
            modifier = Modifier.weight(0.92f)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    LazyColumn {
                        items(inputListText.size) { it ->
                            Row(
                                modifier = Modifier
                                    .height(68.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Checkbox(
                                        modifier = Modifier
                                            .visible(visibleState.value),
                                        checked = viewModel.getInputText(it).onChecked,
                                        onCheckedChange = {newIt ->
                                            viewModel.updateOnCheckedState(it,
                                                newCheck = !viewModel.getInputText(it).onChecked)
                                        }
                                    )
                                }

                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "${it+1}",
                                        fontSize = 18.sp,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }

                                OutlinedTextField(
                                    modifier = Modifier
                                        .width(150.dp)
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
                                            text = "Дата работ",
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                        )
                                    },
                                    placeholder = {
                                        Text(
                                            text = "Введите дату",
                                            color = Color.Black,
                                            fontSize = 14.sp
                                        )
                                    }
                                )
                                OutlinedTextField(
                                    modifier = Modifier.padding(4.dp),
                                    value = viewModel.getInputText(it).projectSection,
                                    onValueChange = {newText: String ->
                                        viewModel.updateInputProjectSection(it, newText)
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color.Blue,
                                        focusedTextColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    label = {
                                        Text(
                                            text = "Раздел проекта",
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
                                            text = "Диметр и толщина",
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
                                    modifier = Modifier
                                        .width(150.dp)
                                        .padding(4.dp),
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
                                            text = "Введите время",
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
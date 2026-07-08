package org.example.project.screens.UIComponents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColorBox(colorButton: Color, onClick:() -> Unit) {

    Box(
        modifier = Modifier
            .size(30.dp)
            .padding(4.dp)
            .background(colorButton, RoundedCornerShape(3.dp))
            .onClick(
                enabled = true,
                onClick = onClick
            )
    ){
    }
}
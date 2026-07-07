package org.example.project.screens.UIComponents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.onClick
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageButton(text: String = "", image: DrawableResource,  contentDescription: String = "", onClick: () -> Unit) {

    Row(
        modifier = Modifier.onClick(
            enabled = true,
            onClick = onClick
        ),
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            Image(painterResource(image),
                contentDescription = contentDescription,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)

                    .padding(start = 20.dp, top = 20.dp)
            )
        }
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ){
            Text(
                text = text
            )
        }
    }
}
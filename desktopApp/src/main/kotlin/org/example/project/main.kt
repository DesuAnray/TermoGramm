package org.example.project

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import org.example.project.ViewModel.OutlineTextFieldViewModel
import org.example.project.screens.ConstantValuesScreen
import org.example.project.screens.DynamicValueScreen




fun main() = application {
    val navController = rememberNavController()
    val windowState = rememberWindowState(
        size = DpSize.Unspecified,
        placement = WindowPlacement.Fullscreen
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "TermoGramm",
        state = windowState
    ) {
        val viewModel: OutlineTextFieldViewModel  = viewModel {
            OutlineTextFieldViewModel()
        }
        NavHost(navController, startDestination = "ConstantValueScreen") {
            composable("ConstantValueScreen") {
                ConstantValuesScreen(navController, viewModel)
            }
            composable ("DynamicValueScreen"){

                DynamicValueScreen(navController, viewModel)
            }
        }
    }
}
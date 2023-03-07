package com.github.johnondrej.scratchcard.shared.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.johnondrej.scratchcard.R
import com.github.johnondrej.scratchcard.features.activation.ActivationScreen
import com.github.johnondrej.scratchcard.features.scratch.ScratchScreen
import com.github.johnondrej.scratchcard.features.status.StatusScreen
import com.github.johnondrej.scratchcard.ui.theme.ScratchCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScratchCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    Scaffold(
                        topBar = {
                            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
                        }
                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = Routes.STATUS,
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable(route = Routes.STATUS) {
                                StatusScreen(
                                    onOpenScratchScreen = {
                                        navController.navigate(Routes.SCRATCH)
                                    },
                                    onOpenActivationScreen = {
                                        navController.navigate(Routes.ACTIVATION)
                                    }
                                )
                            }

                            composable(route = Routes.SCRATCH) {
                                ScratchScreen()
                            }

                            composable(route = Routes.ACTIVATION) {
                                ActivationScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

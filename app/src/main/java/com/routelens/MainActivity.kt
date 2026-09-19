package com.routelens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.routelens.feature.navigation.ui.ActiveNavigationScreen
import com.routelens.feature.navigation.ui.NavigationViewModel
import com.routelens.feature.navigation.ui.RoutePlannerScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var isNavigating by remember { mutableStateOf(false) }

                    if (isNavigating) {
                        ActiveNavigationScreen(
                            viewModel = viewModel,
                            onStopNavigation = { isNavigating = false }
                        )
                    } else {
                        RoutePlannerScreen(
                            viewModel = viewModel,
                            onStartNavigation = { isNavigating = true }
                        )
                    }
                }
            }
        }
    }
}
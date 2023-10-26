package com.shkonda.geekknastu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.shkonda.geekknastu.navigation.BottomNavigation
import com.shkonda.geekknastu.navigation.NavGraph
import com.shkonda.geekknastu.ui.components.scaffold.top_bar.TopBarDecode
import com.shkonda.geekknastu.ui.theme.GEEKKnASTUTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

/*Появляется после входа в аккаунт*/
@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GEEKKnASTUTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    Scaffold(
                        topBar = { TopBarDecode() },
                        bottomBar = { BottomNavigation(navController) }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {

                            NavGraph(navHostController = navController)
                        }

                    }
                }

            }
        }
    }
}
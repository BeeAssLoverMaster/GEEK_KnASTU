package com.shkonda.geekknastu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shkonda.geekknastu.navigation.NavigationGraph
import com.shkonda.geekknastu.ui.theme.GEEKKnASTUTheme
import dagger.hilt.android.AndroidEntryPoint


data class TabItem(
    val title: String
)

@AndroidEntryPoint
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
class AuthorizationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            GEEKKnASTUTheme {
                NavigationGraph()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val activity = (this as? Activity)

        val user = Firebase.auth.currentUser
        if (user != null) {
            val intent = Intent(this, MainAppActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}

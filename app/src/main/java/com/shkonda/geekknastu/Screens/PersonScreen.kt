package com.shkonda.geekknastu.Screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.shkonda.geekknastu.AuthorizationActivity
import com.shkonda.geekknastu.MainAppActivity

@Composable
fun PersonScreen(

) {
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    Button(
        onClick = {
            val mAuth = FirebaseAuth.getInstance()
            mAuth.signOut()
            val intent = Intent(context, AuthorizationActivity::class.java)
            context.startActivity(intent)
            activity?.finish()
        }
    ) {
    }
}
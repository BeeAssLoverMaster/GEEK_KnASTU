package com.shkonda.geekknastu.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.shkonda.geekknastu.AuthorizationActivity

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
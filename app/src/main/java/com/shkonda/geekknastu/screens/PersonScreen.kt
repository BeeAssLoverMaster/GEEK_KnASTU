package com.shkonda.geekknastu.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.shkonda.geekknastu.AuthorizationActivity

@Composable
fun PersonScreen(

) {
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    ConstraintLayout(Modifier.fillMaxSize()) {
        val button = createRef()
        Button(
            onClick = {
                val mAuth = FirebaseAuth.getInstance()
                mAuth.signOut()
                val intent = Intent(context, AuthorizationActivity::class.java)
                context.startActivity(intent)
                activity?.finish()
            },
            Modifier.constrainAs(button) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            Text(text = "Выйти из аккаунта")
        }
    }
}
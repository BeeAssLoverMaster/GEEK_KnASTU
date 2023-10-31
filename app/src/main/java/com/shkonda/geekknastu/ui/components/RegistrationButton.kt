package com.shkonda.geekknastu.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shkonda.geekknastu.ui.theme.MainGreen
import com.shkonda.geekknastu.ui.theme.White

@Composable
fun RegistrationButton(modifier: Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "Появляется окно с регистрацией", Toast.LENGTH_SHORT).show()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MainGreen
        ),
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.PersonAddAlt1,
            contentDescription = "person",
            tint = White,
            modifier = Modifier.padding(end = 8.dp).size(30.dp)
        )
        Text(
            text = "Пройти регистрацию",
            color = White,
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 20.sp
        )
    }
}
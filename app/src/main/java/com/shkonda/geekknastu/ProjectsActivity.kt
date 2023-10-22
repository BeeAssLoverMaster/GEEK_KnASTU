package com.shkonda.geekknastu

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shkonda.geekknastu.ui.theme.GEEKKnASTUTheme

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    GEEKKnASTUTheme {
        ProjectsActivity("Android")
    }
}

class ProjectsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GEEKKnASTUTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProjectsActivity("Android")
                }
            }
        }
    }
}

@Composable
fun ProjectsActivity(name: String, modifier: Modifier = Modifier) {
    val backgroundColor: Color = Color(25, 118, 211)
    val textColor: Color = Color(25, 118, 211)
    val context = LocalContext.current     //Контекст текущей activity
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Image(
            painter = painterResource(id = R.drawable.billboard_geek),
            contentDescription = "Billboard GEEK KNASTU",
            modifier = Modifier.fillMaxWidth()
        )*/

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(backgroundColor)
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                onClick = {
                    val navigate = Intent(context, MainActivity::class.java)    //Для перехода в конкретную activity
                    context.startActivity(navigate)
                },
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color(25, 118, 211))
            ) {
                Text(
                    text = "СКБ",
                    color = Color.White
                )
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color(25, 118, 211))
            ) {
                Text(
                    text = "Проекты",
                    color = Color.White
                )
            }
        }


    }
}


package com.shkonda.geekknastu.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.shkonda.geekknastu.screens.additional_info.AlwaysDigital
import com.shkonda.geekknastu.screens.additional_info.Arduinator
import com.shkonda.geekknastu.screens.additional_info.ScienceInnovation
import com.shkonda.geekknastu.ui.components.AssetImage
import com.shkonda.geekknastu.util.ListItem

@Composable
fun InfoScreen(
    item: ListItem
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (item.title == "Везде цифра") {
                AlwaysDigital(item)
            } else if (item.title == "Ардуинатор") {
                Arduinator(item)
            } else {
                ScienceInnovation(item)
            }
        }
    }
}
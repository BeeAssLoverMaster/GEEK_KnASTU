package com.shkonda.geekknastu.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shkonda.geekknastu.ui.theme.BlueTopBar
import com.shkonda.geekknastu.util.ListItem

@Composable
fun MainListItem(item: ListItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(250.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(5.dp, BlueTopBar)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AssetImage(
                imageName = item.imageName,
                contentDescription = item.title
            )
            Text(
                text = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlueTopBar)
                    .padding(10.dp),
                textAlign =  TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun AssetImage(imageName: String, contentDescription: String) {
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open(imageName)
    val bitMap = BitmapFactory.decodeStream(inputStream)

    Image(
        bitmap = bitMap.asImageBitmap(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}
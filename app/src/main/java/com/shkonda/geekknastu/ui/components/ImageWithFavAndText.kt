package com.shkonda.geekknastu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.shkonda.geekknastu.MainViewModel
import com.shkonda.geekknastu.ui.theme.BGTransparent
import com.shkonda.geekknastu.ui.theme.BlueTopBar
import com.shkonda.geekknastu.util.ListItem

@Composable
fun ImageWithFavAndText(
    mainViewModel: MainViewModel = hiltViewModel(),
    item: ListItem
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (image, text, favoriteButton) = createRefs()
        AssetImage(
            imageName = item.imageName,
            contentDescription = item.title,
            Modifier
                .fillMaxSize()
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueTopBar)
                .padding(10.dp)
                .constrainAs(text) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        IconButton(
            onClick = {
                mainViewModel.insertItem(
                    item.copy(isFav = !item.isFav)
                )
            },
            modifier = Modifier.constrainAs(favoriteButton) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(BGTransparent)
                    .padding(5.dp),
                tint = if (item.isFav) Color.Red else Color.Gray
            )
        }
    }
}
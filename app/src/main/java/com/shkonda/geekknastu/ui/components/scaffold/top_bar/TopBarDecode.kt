package com.shkonda.geekknastu.ui.components.scaffold.top_bar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shkonda.geekknastu.ui.theme.BlueTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDecode() {
    TopAppBar(
        modifier = Modifier.height(50.dp),
        title = {
            Text(
                modifier = Modifier
                    .padding(0.dp, 10.dp),
                text = "DeCode",
            )
        },

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = BlueTopBar,
            titleContentColor = Color.White,
        ),
    )
}
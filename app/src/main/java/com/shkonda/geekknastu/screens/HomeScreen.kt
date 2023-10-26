package com.shkonda.geekknastu.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.shkonda.geekknastu.MainViewModel
import com.shkonda.geekknastu.TabItem
import com.shkonda.geekknastu.ui.components.MainListItem
import com.shkonda.geekknastu.ui.theme.BlueTopBar
import com.shkonda.geekknastu.util.ListItem

/*1.0*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(mainViewModel: MainViewModel = hiltViewModel(), onClick: (ListItem) -> Unit) {

    val mainList = mainViewModel.mainList
    mainViewModel.getAllItemsByCategory("Конкурсы")
    val mAuth = FirebaseAuth.getInstance()
    val tabItems = listOf(
        TabItem(
            title = "Деятельность"
        ),
        TabItem(
            title = "Занятость"
        )
    )
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }
    Column(
//        modifier = Modifier
//            .padding(innerPadding)
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = BlueTopBar,
            contentColor = Color.White,
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(6.dp)
                        .padding(50.dp, 0.dp, 50.dp, 1.dp)
                        .border(
                            width = 20.dp,
                            shape = RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp),
                            color = Color.White
                        )

                ) {

                }
            }
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(text = item.title)

                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            /*Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Text(tabItems[index].title)
            }*/
            when (index) {
                0 -> {
                    /*mainList.value.forEach { item ->
                        if (item.isFav) {
                            MainListItem(item = item) { listItem ->
                                onClick(listItem)
                            }
                        }
                    }*/
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(mainList.value) { item ->
                            if (item.isFav) {
                                MainListItem(item = item) { listItem ->
                                    onClick(listItem)
                                }

                            }
                        }
                    }
                }

                1 -> {
                    Text(tabItems[index].title)
                }
            }
        }
    }
}

/*
@Composable
fun Projects(
    mainViewModel: MainViewModel = hiltViewModel(), onClick: (ListItem) -> Unit
) {

    */
/*LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(mainList.value) { item ->
//            MainListItem(item = item) {}
            Text(text = "Выбранные конкурсы ${item.isFav}")
        }

    }*//*

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        mainList.value.forEach { item ->
            if (item.isFav) {
                MainListItem(item = item) { listItem ->
                    onClick(listItem)
                }
            }
        }
//        Text(text = "${mainList.value.size}")
    }
}*/

package com.shkonda.geekknastu.screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.shkonda.geekknastu.TabItem
import com.shkonda.geekknastu.ui.components.MainListItem
import com.shkonda.geekknastu.ui.theme.BlueTopBar
import com.shkonda.geekknastu.util.IdArrayList
import com.shkonda.geekknastu.util.ListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventScreen(
    onClick: (ListItem) -> Unit
) {
    val context = LocalContext.current
    val tabItems = listOf(
        TabItem(
            title = "Конкурсы"
        ),
        TabItem(
            title = "Конференции"
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
            when (index) {
                0 -> {
                    Contests(context, index) { listItem ->
                        onClick(listItem)
                    }
                }

                1 -> {
                    Conferences(context, index) { listItem ->
                        onClick(listItem)
                    }
                }
            }
        }
    }
}

@Composable
fun Contests(
    context: Context,
    index: Int,
    onClick: (ListItem) -> Unit
) {
    val mainList = remember {
        mutableStateOf(getListItemsByIndex(index, context))
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(mainList.value) { item ->
            MainListItem(item = item) { listItem ->
                onClick(listItem)
            }
        }

    }
}

@Composable
fun Conferences(
    context: Context,
    index: Int,
    onClick: (ListItem) -> Unit
) {
    val mainList = remember {
        mutableStateOf(getListItemsByIndex(index, context))
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(mainList.value) { item ->
            MainListItem(item = item) { listItem ->
                onClick(listItem)
            }
        }
    }
}

private fun getListItemsByIndex(
    index: Int,
    context: Context
): List<ListItem> {
    val list = ArrayList<ListItem>()
    val arrayList = context.resources.getStringArray(IdArrayList.listId[index])
    arrayList.forEach { item ->
        val itemArray = item.split("|")
        list.add(
            ListItem(
                title = itemArray[0],
                imageName = itemArray[1],
                htmlName = itemArray[2]
            )
        )
    }
    return list
}
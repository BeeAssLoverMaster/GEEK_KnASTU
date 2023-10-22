package com.shkonda.geekknastu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shkonda.geekknastu.bottom_navigation.BottomNavigation
import com.shkonda.geekknastu.bottom_navigation.MainScreen
import com.shkonda.geekknastu.bottom_navigation.NavGraph
import com.shkonda.geekknastu.top_bar.TopBarDecode
import com.shkonda.geekknastu.ui.theme.GEEKKnASTUTheme

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GEEKKnASTUTheme {
        MainScreen()
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GEEKKnASTUTheme {
                val navController = rememberNavController()
                /*
                val items = listOf(
                    BottomNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home, false),
                    BottomNavigationItem("Events", Icons.Filled.Event, Icons.Outlined.Event, false, 45),
                    BottomNavigationItem("Profile", Icons.Filled.Person, Icons.Outlined.Person, true),

                    )
                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                                  selectedItemIndex = index

                                        },
                                        label = {
                                            Text(item.title)
                                        },
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    if (item.badgeCount != null) {
                                                        Badge {
                                                            Text(text = item.badgeCount.toString())
                                                        }
                                                    } else if (item.hasNews) {
                                                        Badge ()
                                                    }
                                            }
                                            ) {
                                                Icon(
                                                    if(index == selectedItemIndex) {
                                                        item.selectedIcon
                                                    } else item.unselectedIcon,
                                                    item.title
                                                )

                                            }
                                        }
                                    )
                                }
                            }
                        },
                        content = {

                        }
                    )
                }
                */
                Scaffold(
                    topBar = { TopBarDecode() },
                    bottomBar = { BottomNavigation(navController) }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavGraph(navHostController = navController)
                    }

                }
            }
        }
    }
}

val backgroundColor: Color = Color(25, 118, 211)

data class TabItem(
    val title: String
)

sealed class Screen(
    val route: String
) {
    //Будущие экраны на панели навигации
    object Home : Screen("home")
    object Events : Screen("events")
    object Profile : Screen("profile")
}

@Composable
fun BottomAppBarWithNavigation(
    navController: NavHostController
) {
    BottomAppBar {
        NavigationItem(navController, Screen.Home, Icons.Outlined.Home, "Home")
        NavigationItem(navController, Screen.Events, Icons.Outlined.Event, "Events")
        NavigationItem(navController, Screen.Profile, Icons.Outlined.Home, "Profile")
    }
}

@Composable
fun NavigationItem(
    navController: NavHostController,
    screen: Screen,
    icon: ImageVector,
    label: String
) {
    IconButton(
        onClick = { navController.navigate(screen.route) }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
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
                    containerColor = backgroundColor,
                    titleContentColor = Color.White,
                ),
            )
        },
        /*bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(50.dp),
                containerColor = Color.White,
                contentColor = Color.Black,
            ) {
                Row(
                    Modifier
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = backgroundColor
                        )
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Event,
                            contentDescription = "Events"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Person"
                        )
                    }
                }
            }
        },*/
        content = { innerPadding ->     //Чтобы контент был между topBar и bottomBar
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
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = backgroundColor,
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(tabItems[index].title)
                    }
                }
            }


        }
    )
}

@Composable
fun EventsScreen() {
    Text("EventsScreen")
}


/*ИЗНАЧАЛЬНАЯ ИДЕЯ*/
/*
data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
) {
    val tabItems = listOf(
        TabItem(
            title = "СКБ",
            unselectedIcon = Icons.Outlined.Groups,
            selectedIcon = Icons.Filled.Groups
        ),
        TabItem(
            title = "Проекты",
            unselectedIcon = Icons.Outlined.Assignment,
            selectedIcon = Icons.Filled.Assignment
        )
    )
    val backgroundColor: Color = Color(25, 118, 211)

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
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = backgroundColor,
            contentColor = Color.White,
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(5.dp)
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
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    },
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
                    SCB_View()
                }

                1 -> {
                    ProjectsView()
                }
            }
        }
    }
    /*СТАРЫЙ КОД*/
    /*
    /*Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        *//*Image(
            painter = painterResource(id = R.drawable.billboard_geek),
            contentDescription = "Billboard GEEK KNASTU",
            modifier = Modifier.fillMaxWidth()
        )*//*

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(backgroundColor)
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                onClick = { *//*TODO*//* },
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
                onClick = {
                    val navigate = Intent(
                        context,
                        ProjectsActivity::class.java
                    )    //Для перехода в конкретную activity
                    context.startActivity(navigate)
                },
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

        Text(
            text = "Студенческие конструкторские бюро",
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp),
            color = Color(25, 118, 216),
            fontSize = 19.sp
        )

        Row(
            modifier = Modifier.padding(0.dp, 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.electronic),
                contentDescription = "Electronic",
                modifier = Modifier
                    .clickable(onClick = {
                        val navigate = Intent(
                            context,
                            ElectronicsAndRobots::class.java
                        )    //Для перехода в конкретную activity
                        context.startActivity(navigate)
                    })
                    .size(150.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.padding(10.dp, 0.dp))

            Image(
                painter = painterResource(id = R.drawable.robots),
                contentDescription = "Robots",
                modifier = Modifier
                    .clickable(onClick = {})
                    .size(150.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }*/
*/
}

@Composable
private fun SCB_View() {
    val context = LocalContext.current     //Контекст текущей activity
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Студенческие конструкторские бюро",
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp),
            color = Color(25, 118, 216),
            fontSize = 19.sp
        )
        Row {
            Image(
                painter = painterResource(id = R.drawable.electronic),
                contentDescription = "Electronic",
                modifier = Modifier
                    .clickable(onClick = {
                        val navigate = Intent(
                            context,
                            ElectronicsAndRobots::class.java
                        )    //Для перехода в конкретную activity
                        context.startActivity(navigate)
                    })
                    .size(170.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.padding(5.dp, 0.dp))

            Image(
                painter = painterResource(id = R.drawable.robots),
                contentDescription = "Robots",
                modifier = Modifier
                    .clickable(onClick = {})
                    .size(170.dp),
                contentScale = ContentScale.FillBounds
            )
        }

        Row(Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)) {
            Image(
                painter = painterResource(id = R.drawable.electronic),
                contentDescription = "Electronic",
                modifier = Modifier
                    .clickable(onClick = {
                        val navigate = Intent(
                            context,
                            ElectronicsAndRobots::class.java
                        )    //Для перехода в конкретную activity
                        context.startActivity(navigate)
                    })
                    .size(170.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.padding(5.dp, 0.dp))

            Image(
                painter = painterResource(id = R.drawable.robots),
                contentDescription = "Robots",
                modifier = Modifier
                    .clickable(onClick = {})
                    .size(170.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }

}

@Composable
private fun ProjectsView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Проекты",
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp),
            color = Color(25, 118, 216),
            fontSize = 19.sp
        )

        Row(
            modifier = Modifier.padding(0.dp, 10.dp)
        ) {
            val mContext = LocalContext.current     //Контекст текущей activity
            Image(
                painter = painterResource(id = R.drawable.lazer_tir),
                contentDescription = "Electronic",
                modifier = Modifier
                    .clickable(onClick = {

                    })
                    .size(150.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.padding(10.dp, 0.dp))

            Image(
                painter = painterResource(id = R.drawable.robots),
                contentDescription = "Robots",
                modifier = Modifier
                    .clickable(onClick = {})
                    .size(150.dp),
                contentScale = ContentScale.FillBounds
            )

        }
    }
}

*/
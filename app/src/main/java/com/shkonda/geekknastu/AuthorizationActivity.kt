package com.shkonda.geekknastu

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shkonda.geekknastu.Screens.login_screen.SignInViewModel
import com.shkonda.geekknastu.navigation.NavigationGraph
import com.shkonda.geekknastu.navigation.Screens
import com.shkonda.geekknastu.ui.theme.GEEKKnASTUTheme
import com.shkonda.geekknastu.ui.theme.LightBlue
import com.shkonda.geekknastu.ui.theme.RegularFont
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

lateinit var sharedPreferences: SharedPreferences
var PREFS_KEY = "prefs"
var EMAIL_KEY = "email"
var PASSWORD_KEY = "pwd"

@AndroidEntryPoint
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
class AuthorizationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
            GEEKKnASTUTheme {

//                SignInScreen(sharedPreferences, navController)
                NavigationGraph(sharedPreferences)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val activity = (this as? Activity)

        sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

        val email = sharedPreferences.getString(EMAIL_KEY, "").toString()
        val password = sharedPreferences.getString(PASSWORD_KEY, "").toString()
        val user = Firebase.auth.currentUser
        if (user != null) {
            val intent = Intent(this, MainAppActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    sharedPreferences: SharedPreferences,
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)
    val activity = (LocalContext.current as? Activity)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Войти в аккаунт",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            fontFamily = RegularFont,
        )
        Text(
            text = "Введите свои данные для входа",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Color.Gray,
            fontFamily = RegularFont
        )
        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = LightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = LightBlue, unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ), shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                Text(text = "Email")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = LightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = LightBlue, unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ), shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                Text(text = "Пароль")
            }
        )

        Button(
            onClick = {
                scope.launch {
                    viewModel.loginUser(email, password)
//                    context.startActivity(Intent(context, Screens.HomeScreen::class.java))

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 30.dp, end = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black, contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(text = "Войти", color = Color.White, modifier = Modifier.padding(7.dp))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isLoading == true) {
                CircularProgressIndicator()
            }

        }
        Text(
            modifier = Modifier
                .padding(14.dp)
                .clickable {
                    navController.navigate(Screens.SignUpScreen.route)
                },
            text = "Новый пользователь? Зарегистрируйся ",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = RegularFont
        )

        LaunchedEffect(key1 = state.value?.isSuccess) {
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty() == true) {
                    val success = state.value?.isSuccess
                    Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                    saveData(email, password, sharedPreferences, context)
                    activity?.finish()
                }
            }
        }

        LaunchedEffect(key1 = state.value?.isError) {
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true) {
                    val error = state.value?.isError
                    Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}

fun saveData(
    email: String,
    password: String,
    sharedPreferences: SharedPreferences,
    context: Context
) {
    val editor: SharedPreferences.Editor = sharedPreferences.edit()

    editor.putString("email", email)
    editor.putString("password", password)

    editor.apply()

    val intent = Intent(context, MainAppActivity::class.java)
    context.startActivity(intent)
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
/*
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

*/
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
package ru.hse.buildingapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.hse.buildingapp.ui.screens.*
import ru.hse.buildingapp.ui.theme.BuildingAppTheme

var robotoFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

class MainActivity : ComponentActivity() {

    lateinit var scaffoldState : ScaffoldState
    lateinit var scope : CoroutineScope
    lateinit var navController : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            scaffoldState = rememberScaffoldState()
            scope = rememberCoroutineScope()
            navController = rememberNavController()
            BuildingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Content()
                }
            }
        }
    }

    sealed class Screen(val route: String,
                        @StringRes val labelId: Int?,
                        @DrawableRes val darkIconId: Int? = null,
                        @DrawableRes val lightIconId : Int? = null) {
        object Home : Screen("home", R.string.home, R.drawable.home_icon)
        object Projects : Screen("projects",
            R.string.projects,
            R.drawable.projects_icon_dark,
            R.drawable.projects_icon_light)
        object Project : Screen("project/{id}",
        R.string.projects,
        null,
        null)
        object News : Screen("news",
            R.string.news,
            R.drawable.news_icon)
        object AboutUs : Screen("aboutus",
            R.string.about_us,
            R.drawable.about_us_icon_dark,
            R.drawable.about_us_icon_light)
        object ProjectRequest: Screen("projectrequest",
            R.string.project_request)
        object Login: Screen("login",
            R.string.login,
            null,
            R.drawable.login_icon_light)
    }

    val bottomNavItems = listOf(
        Screen.Home,
        Screen.Projects,
        Screen.News,
        Screen.AboutUs
    )
    val leftNavItems = listOf(
        Screen.Projects,
        Screen.AboutUs,
        Screen.Login
    )

    @Composable
    fun LeftNavItem(s : Screen, selected : Boolean, onClick : () -> Unit) {
        val backgroundColor = if(!selected) Color(0xFF3C6AB0) else Color(0xFF30548A)
        Row(Modifier
            .padding(end = 1.dp)
            .height(60.dp)
            .fillMaxWidth()
            .clickable { onClick() }
            .background(backgroundColor, RoundedCornerShape(0, 30, 0, 0)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(18.dp))
            Image(
                painterResource(id = s.lightIconId!!),
                contentDescription = null
            )
            Spacer(Modifier.width(30.dp))
            Text(fontFamily = robotoFamily,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
                textAlign = Center,
                maxLines = 1,
                text = stringResource(s.labelId!!),
                color = Color(0xFFFFFFFF))
        }
    }


    @SuppressLint("SuspiciousIndentation")
    @Preview
    @Composable
    fun Content() {
        var title by rememberSaveable { mutableStateOf("Home") }
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(220.dp),
                        painter = painterResource(id = R.drawable.nav_drawer_logo),
                        contentDescription = null
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF3C6AB0), RoundedCornerShape(0, 15, 0, 0))
                    )
                    {
                        Spacer(Modifier.height(25.dp))
                        leftNavItems.forEach() { item ->
                            LeftNavItem(item, selected = currentDestination?.hierarchy?.any { it.route == item.route } == true) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true

                                }
                            }
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }
            },
            topBar = {
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp
                ) {
                    Box(Modifier.height(32.dp)) {
                        Row {
                            IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_menu_dark),
                                    contentDescription = "Side menu"
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                fontFamily = robotoFamily,
                                fontWeight = FontWeight.W500,
                                fontSize = 24.sp,
                                textAlign = Center,
                                maxLines = 1,
                                text = title,
                                color = Color(0xFF2D4855)
                            )
                        }
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            IconButton(onClick = {/*TODO*/ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.profile_icon),
                                    contentDescription = "Profile icon"
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                BottomNavigation(
                    backgroundColor = Color.White,
                    modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        bottomNavItems.forEachIndexed { index, item ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = item.darkIconId!!),
                                        contentDescription = null
                                    )
                                },
                                label = { Text(stringResource(item.labelId!!)) },
                                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true

                                    }
                                },
                                selectedContentColor = Color(0xFF3C6AB0),
                                unselectedContentColor = Color(0xFF5A6970)
                            )
                            if (index != bottomNavItems.size - 1) {
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.bottom_nav_separator),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        )
        { innerPadding ->
            NavHost(
                navController,
                startDestination = Screen.Home.route,
                Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    title = stringResource(id = Screen.Home.labelId!!)
                    HomeScreen.View(navController)
                }
                composable(Screen.Projects.route) {
                    title = stringResource(id = Screen.Projects.labelId!!)
                    ProjectsListScreen.View(navController)
                }
                composable(Screen.News.route) {
                    title = stringResource(id = Screen.News.labelId!!)
                    NewsScreen.View()
                }
                composable(Screen.AboutUs.route) {
                    title = stringResource(id = Screen.AboutUs.labelId!!)
                }
                composable(Screen.ProjectRequest.route) {
                    title = stringResource(id = Screen.ProjectRequest.labelId!!)
                    ProjectRequestScreen.View()
                }
                composable(Screen.Login.route) {
                    title = stringResource(id = Screen.Login.labelId!!)
                    LoginScreen.View()
                }
                composable(Screen.Project.route) { backStackEntry ->
                    title = stringResource(id = Screen.Project.labelId!!)
                    val arg : String = backStackEntry.arguments?.getString("id") ?: throw NullPointerException()
                    ProjectScreen.View(ProjectViewModel(Integer.parseInt(arg)))
                }
            }
        }
    }
}




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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.hse.buildingapp.ui.theme.BuildingAppTheme

var robotoFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
}

sealed class Screen(val route: String, @StringRes val labelId: Int?, @DrawableRes val iconId: Int?) {
    object Home : Screen("home", R.string.home, R.drawable.home_icon)
    object Projects : Screen("projects", R.string.projects, R.drawable.projects_icon)
    object News : Screen("news", R.string.news, R.drawable.news_icon)
    object AboutUs : Screen("aboutus", R.string.about_us, R.drawable.about_us_icon)
    object ProjectRequest: Screen("projectrequest", R.string.project_request, null)
}

val items = listOf(
    Screen.Home,
    Screen.Projects,
    Screen.News,
    Screen.AboutUs
)

@SuppressLint("SuspiciousIndentation")
@Preview
@Composable
fun Content()
{
    var title by rememberSaveable{ mutableStateOf("Home")}
    val navController = rememberNavController()

    Scaffold(
        drawerContent = {
            Text("Drawer title")
            Divider()
        },
        topBar = {
            TopAppBar(backgroundColor = Color.Transparent,
                      elevation = 0.dp) {
                Box(Modifier.height(32.dp)) {
                    Row {
                        IconButton(onClick = { /*TODO*/ }) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_menu_dark),
                                contentDescription = "Side menu"
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
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
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd) {
                        IconButton(onClick = {/*TODO*/}) {
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
                        items.forEachIndexed {index, item ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = item.iconId!!),
                                        contentDescription = null
                                    )
                                },
                                label = { Text(stringResource(item.labelId!!)) },
                                selected = currentDestination?.hierarchy?.any{it.route == item.route} == true,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true

                                    }
                                },
                                selectedContentColor = Color(0xFF3C6AB0),
                                unselectedContentColor = Color(0xFF5A6970)
                            )
                            if(index != items.size - 1) {
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
        {
                innerPadding ->
                NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
                    composable(Screen.Home.route){
                        title = stringResource(id = Screen.Home.labelId!!)
                        HomeScreen.view { navController.navigate(Screen.ProjectRequest.route) {
                            val currentDestination = navController.currentDestination;
                            if(currentDestination != null) {
                                popUpTo(currentDestination.id) {
                                    saveState = true
                                    inclusive = true
                                }
                            }
                        } }
                    }
                    composable(Screen.Projects.route){
                        title = stringResource(id = Screen.Projects.labelId!!)
                        ProjectsScreen.view()
                    }
                    composable(Screen.News.route){
                        title = stringResource(id = Screen.News.labelId!!)
                        NewsScreen.view()
                    }
                    composable(Screen.AboutUs.route){title = stringResource(id = Screen.AboutUs.labelId!!)}
                    composable(Screen.ProjectRequest.route){
                        title = stringResource(id = Screen.ProjectRequest.labelId!!)
                        ProjectRequestScreen.view()
                    }
                }
        }
}




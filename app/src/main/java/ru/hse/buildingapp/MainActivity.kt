package ru.hse.buildingapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.hse.buildingapp.navigation.NavigationAdapter
import ru.hse.buildingapp.ui.screens.*
import ru.hse.buildingapp.ui.viewmodels.*

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
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            )
            {
                val helperNavController : NavHostController = rememberNavController()
                NavHost(navController = helperNavController,
                    startDestination = NavigationAdapter.Screen.LanguageChoose.route) {
                    composable("lang-choose") {
                        LanguageChooseScreen.View(helperNavController)
                    }
                    composable("main-app") {
                        Content()
                    }
                }
            }
        }
    }

    val bottomNavItems = listOf(
        NavigationAdapter.Screen.Home,
        NavigationAdapter.Screen.Projects,
        NavigationAdapter.Screen.News,
        NavigationAdapter.Screen.AboutUs
    )
    val leftNavItems = listOf(
        NavigationAdapter.Screen.Projects,
        NavigationAdapter.Screen.AboutUs,
        NavigationAdapter.Screen.Login
    )

    @Composable
    fun LeftNavItem(s : NavigationAdapter.Screen, selected : Boolean, onClick : () -> Unit) {
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
                            LeftNavItem(item, selected = currentDestination?.hierarchy?.any { it.route?.contains(item.route) == true } == true) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id)
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
                                selected = currentDestination?.hierarchy?.any { it.route?.contains(item.route) == true} == true,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id)
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
                startDestination = NavigationAdapter.Screen.Home.route,
                Modifier.padding(innerPadding)
            ) {
                composable(NavigationAdapter.Screen.Home.route) {
                    title = stringResource(id = NavigationAdapter.Screen.Home.labelId!!)
                    HomeScreen.View(viewModel(factory = HomeViewModelFactory(navController)))
                }
                composable(NavigationAdapter.Screen.Projects.route) {
                    title = stringResource(id = NavigationAdapter.Screen.Projects.labelId!!)
                    ProjectsListScreen.View(viewModel(factory = ProjectsListViewModelFactory(navController)))
                }
                composable(NavigationAdapter.Screen.News.route) {
                    title = stringResource(id = NavigationAdapter.Screen.News.labelId!!)
                    NewsScreen.View()
                }
                composable(NavigationAdapter.Screen.ProjectRequest.route) {
                    title = stringResource(id = NavigationAdapter.Screen.ProjectRequest.labelId!!)
                    ProjectRequestScreen.View(viewModel(factory = ProjectRequestViewModelFactory(navController)))
                }
                composable(NavigationAdapter.Screen.Login.route) {
                    title = stringResource(id = NavigationAdapter.Screen.Login.labelId!!)
                    LoginScreen.View(viewModel(factory = LoginViewModelFactory(navController)))
                }
                composable(NavigationAdapter.Screen.Project.route) { backStackEntry ->
                    title = stringResource(id = NavigationAdapter.Screen.Project.labelId!!)
                    val arg : String = backStackEntry.arguments?.getString("id") ?: throw NullPointerException()
                    ProjectScreen.View(viewModel(factory = ProjectViewModelFactory(Integer.parseInt(arg), navController)))
                }
                composable(NavigationAdapter.Screen.Reports.route) { backStackEntry ->
                    title = stringResource(id = NavigationAdapter.Screen.Reports.labelId!!)
                    val arg : String = backStackEntry.arguments?.getString("id") ?: throw NullPointerException()
                    ReportsScreen.View(viewModel(factory = ReportsViewModelFactory(Integer.parseInt(arg))))
                }
                composable(NavigationAdapter.Screen.AboutUs.route) {
                    title = stringResource(id = NavigationAdapter.Screen.AboutUs.labelId!!)
                    AboutUsScreen.View()
                }
            }
        }
    }
}




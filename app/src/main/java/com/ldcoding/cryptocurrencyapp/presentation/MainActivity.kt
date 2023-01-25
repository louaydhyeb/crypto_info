package com.ldcoding.cryptocurrencyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BadgeBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ldcoding.cryptocurrencyapp.presentation.coin_detail.CoinDetailScreen
import com.ldcoding.cryptocurrencyapp.presentation.coin_list.CoinListScreen
import com.ldcoding.cryptocurrencyapp.presentation.ui.theme.CryptocurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppTheme {
                val navController = rememberNavController()
                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

                val navBackStackEntry by navController.currentBackStackEntryAsState()

                // Control BottomBar
                when (navBackStackEntry?.destination?.route) {
                    Screen.SplashScreen.route -> {
                        // Show BottomBar
                        bottomBarState.value = false
                    }
                    Screen.CoinDetailScreen.route + "/{coinId}" -> {
                        // Show BottomBar
                        bottomBarState.value = false
                    }
                    "settings" -> {
                        // Show BottomBar
                        bottomBarState.value = true
                    }
                    Screen.CoinListScreen.route -> {
                        // Hide BottomBar
                        bottomBarState.value = true
                    }
                }

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            bottomBarState = bottomBarState,
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = Screen.CoinListScreen.route,
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Settings",
                                    route = "settings",
                                    icon = Icons.Default.Settings,
                                    badgeCount = 214
                                )
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.CoinListScreen.route) {
            CoinListScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen()
        }
        composable(route = Screen.CoinDetailScreen.route + "/{coinId}") {
            CoinDetailScreen()
        }
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    bottomBarState: MutableState<Boolean>,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                modifier = modifier,
                backgroundColor = Color.DarkGray,
                elevation = 5.dp
            ) {
                items.forEach { item ->
                    val selected = item.route == backStackEntry.value?.destination?.route
                    BottomNavigationItem(
                        selected = selected,
                        onClick = { onItemClick(item) },
                        selectedContentColor = Color.Green,
                        unselectedContentColor = Color.Gray,
                        icon = {
                            Column(horizontalAlignment = CenterHorizontally) {
                                if (item.badgeCount > 0) {
                                    BadgeBox(
                                        badgeContent = {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    ) {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.name
                                        )
                                    }
                                } else {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.name
                                    )
                                }
                                if (selected) {
                                    Text(
                                        text = item.name,
                                        textAlign = TextAlign.Center,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings screen")
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)

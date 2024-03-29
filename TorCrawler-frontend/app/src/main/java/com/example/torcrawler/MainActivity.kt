package com.example.torcrawler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.torcrawler.ui.screen.DetailScreen
import com.example.torcrawler.ui.screen.DetectedOnionScreen
import com.example.torcrawler.ui.screen.HomeScreen
import com.example.torcrawler.ui.theme.TorCrawlerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorCrawlerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController =  rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ){
                        composable(Screen.Home.route){
                            HomeScreen(navController = navController)
                        }

                        composable(
                            Screen.Detail.route
                        ){
                            DetailScreen()
                        }

                        composable(
                            Screen.DetectedOnions.route+"/{search_term}",
                            arguments = listOf(navArgument("search_term"){
                                type = NavType.StringType
                            })
                        ){backStackEntry ->
                            DetectedOnionScreen(
                                search_term = backStackEntry.arguments?.getString("search_term")?:"",
                                navController
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String){
    data object Home: Screen("home")
    data object Detail: Screen("detail")
    data object DetectedOnions: Screen("detectedOnions")
}




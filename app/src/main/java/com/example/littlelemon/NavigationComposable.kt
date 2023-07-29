package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class NavigationComposable {
}

//@Preview(showBackground=true)
@Composable
//fun MyNavigation(){    //For testing
fun MyNavigation(logged:Boolean, onSubmit:(f:String, l:String, e:String)->Unit,
                 onLogout:()->Unit, sharedPreferences: android.content.SharedPreferences,
                 databaseMenuItems: List<MenuItemRoom>){
    val navController = rememberNavController()
    //Check the user is logged in or not




    NavHost(navController = navController,
        startDestination =if (!logged) Home.route else OnboardingRoute.route

    ){
        composable(Home.route){
            HomeScreen(navController, databaseMenuItems)
        }
        composable(OnboardingRoute.route){
            Onboarding(navController, onSubmit)
        }
        composable(Profile.route){
            ProfileScreen(navController,onLogout, sharedPreferences)
        }
    }

}









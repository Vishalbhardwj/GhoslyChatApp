package com.example.ghoslychatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ghoslychatapp.Screens.ChatListScreen
import com.example.ghoslychatapp.Screens.LoginScreen
import com.example.ghoslychatapp.Screens.ProfileScreen
import com.example.ghoslychatapp.Screens.SignUpScreen
import com.example.ghoslychatapp.Screens.SingleStatusScreen
import dagger.hilt.android.AndroidEntryPoint

sealed class DestinationScreen(var route: String){
    object SignUp :DestinationScreen("signUp")
    object LogIn :DestinationScreen("logIn")
    object Profile:DestinationScreen("profile")
    object ChatList :DestinationScreen("chatList")

    object SingleChat:DestinationScreen("singleChat/{chatId}"){
         fun createRoute(charId: String) = "singlechat/$charId"
    }

    object StatusList :DestinationScreen("statusList")

    object SingleStatus :DestinationScreen("singleStatus/{userId}"){
        fun createRoute(userId: String) = "singlechat/$userId"
    }


}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            appNavigation()
        }
    }

    @Composable
    fun appNavigation(){
        var navController = rememberNavController()
        var vm = hiltViewModel<GCViewModel>()
        NavHost(navController = navController, startDestination = DestinationScreen.SignUp.route){

            composable(DestinationScreen.SignUp.route){
                SignUpScreen(navController,vm)
            }

            composable(DestinationScreen.LogIn.route){
                LoginScreen(navController,vm)
            }

            composable(DestinationScreen.ChatList.route){
                ChatListScreen(navController,vm)
            }
            composable(DestinationScreen.Profile.route){
                ProfileScreen(navController,vm)
            }
            composable(DestinationScreen.SingleStatus.route){
                SingleStatusScreen(navController,vm)
            }

        }


    }
}





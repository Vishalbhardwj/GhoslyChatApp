package com.example.ghoslychatapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.ui.Modifier


fun navigationTo(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(route)
        launchSingleTop = true
    }
}

@Composable
fun commonCircularProgressBar() {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .alpha(0.5f)
            .clickable(enabled = false) {}
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun checkAlreadyUser(vm:GCViewModel,navController: NavController){
    var alreadyUser = remember {
        mutableStateOf(false)
    }
    var signIn = vm.signIn.value

    if(signIn && !alreadyUser.value){
        alreadyUser.value = true
       navController.navigate(DestinationScreen.ChatList.route){
           popUpTo(0)
       }
    }

}
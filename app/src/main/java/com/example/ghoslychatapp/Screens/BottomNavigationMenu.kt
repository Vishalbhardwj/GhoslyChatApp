package com.example.ghoslychatapp.Screens


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ghoslychatapp.DestinationScreen
import com.example.ghoslychatapp.R


enum class BottomNavigationItem(val icon: Int, val navDestination: DestinationScreen) {
    CHATlIST(R.drawable.chat1, DestinationScreen.ChatList),
    STATUS(R.drawable.status, DestinationScreen.SingleStatus),
    PROFILE(R.drawable.user, DestinationScreen.Profile)
}




@Composable
fun BottomNavigationMenu(

) {


}
package com.example.ghoslychatapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun  ChatListScreen() {
    Box(modifier= Modifier.fillMaxSize(), Alignment.Center
    ){
        Text(text = "This is ChatListScreen",modifier = Modifier.background(Color.Cyan))
    }
}
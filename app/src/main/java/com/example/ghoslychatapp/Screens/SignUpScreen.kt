package com.example.ghoslychatapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ghoslychatapp.DestinationScreen
import com.example.ghoslychatapp.GCViewModel
import com.example.ghoslychatapp.R
import com.example.ghoslychatapp.checkAlreadyUser
import com.example.ghoslychatapp.commonCircularProgressBar
import com.example.ghoslychatapp.navigationTo

@Composable
fun SignUpScreen(navController: NavController, vm: GCViewModel) {

    checkAlreadyUser(vm,navController)
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            var nameState = remember {
                mutableStateOf(TextFieldValue())
            }
            var numberState = remember {
                mutableStateOf(TextFieldValue())
            }
            var emailState = remember {
                mutableStateOf(TextFieldValue())
            }
            var passwordState = remember {
                mutableStateOf(TextFieldValue())
            }
            Image(
                painter = painterResource(id = R.drawable.moster3), contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = nameState.value,
                onValueChange = {
                    nameState.value = it
                },
                label = { Text(text = "Name") },
                modifier = Modifier.padding(top = 12.dp, bottom = 10.dp)
            )

            OutlinedTextField(
                value = numberState.value,
                onValueChange = {
                    numberState.value = it
                },
                label = { Text(text = "Number") },
                modifier = Modifier.padding(10.dp)
            )
            OutlinedTextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                },
                label = { Text(text = "Email") },
                modifier = Modifier.padding(10.dp)
            )
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                },
                label = { Text(text = "Password") },
                modifier = Modifier.padding(10.dp)
            )

            Button(onClick = {
                             vm.signUp(
                                 nameState.value.text,
                                 numberState.value.text,
                                 emailState.value.text,
                                 passwordState.value.text
                             )
            }, modifier = Modifier.padding(18.dp)) {
                Text(text = "SignUp")
            }
            Text(
                text = "Already User? LogIn ->",
                color = Color.Blue,
                fontSize = 15.sp,
                modifier = Modifier
                    .clickable {
                        navigationTo(
                            navController = navController,
                            route = DestinationScreen.LogIn.route
                        )

                    }
                    .padding(9.dp)
            )


        }

    }
    if(vm.inProcess.value){
        commonCircularProgressBar()

    }

}
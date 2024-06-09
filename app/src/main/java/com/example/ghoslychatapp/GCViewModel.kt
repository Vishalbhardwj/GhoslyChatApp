package com.example.ghoslychatapp

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ghoslychatapp.data.Event
import com.example.ghoslychatapp.data.USER_NODE
import com.example.ghoslychatapp.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class GCViewModel @Inject constructor(
    val auth:FirebaseAuth,
    val db : FirebaseFirestore


) :ViewModel(){

    var inProcess = mutableStateOf(false)
    var eventMutableState = mutableStateOf<Event<String>?>(null)
    var signIn = mutableStateOf(false)
    var userData = mutableStateOf<UserData?>(null)

    init {
//        val currentUser = auth.currentUser
//        signIn.value = currentUser!=null
//        currentUser?.uid?.let{
//            getUserData(it)
//        }
    }

    fun signUp(name:String, number:String,email:String,password:String){
        inProcess.value = true
        if(name.isEmpty() or number.isEmpty() or email.isEmpty() or password.isEmpty()){
            handleException(customMessage = "Please fill all fields.")
            return
        }
        db.collection(USER_NODE).whereEqualTo("number",number).get().addOnSuccessListener {
            if(it.isEmpty){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    if(it.isSuccessful){
                        signIn.value = true
                        createOrUpdateProfile(name,number)
                    }else{
                        handleException(it.exception,"SignUp Failed")
                    }
                }
            }else{
                handleException(customMessage = "Number Already Exists")
                inProcess.value = false
            }
        }

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            if(it.isSuccessful){
                signIn.value = true
                createOrUpdateProfile(name,number)
            }else{
                handleException(it.exception,"SignUp Failed")
            }
        }
    }

    fun createOrUpdateProfile(name:String?=null,number:String?=null,imgUrl:String?=null){
        val uid = auth.currentUser?.uid

        val userData = UserData(
            userId = uid,
            name = name?:userData.value?.name,
            number = number?:userData.value?.number,
            imageUrl =imgUrl?:userData.value?. imageUrl
        )

        uid?.let {
            inProcess.value = true
            db.collection(USER_NODE).document(uid).get().addOnSuccessListener {
                if(it.exists()){
//                    Update user!
                }else{
                    db.collection(USER_NODE).document(uid).set(userData)
                    inProcess.value = false
                    getUserData(uid)
                }
            }.addOnFailureListener(){
                handleException(it,"Cannot Retrieve User")
            }




        }


    }

    private fun getUserData(uid: String) {
        inProcess.value = true
        db.collection(USER_NODE).document(uid).addSnapshotListener{
            value,error ->
            if(error!=null){
                handleException(error,"Cannot Retrieve User")
            }
            if(value!=null){
                val user = value.toObject<UserData>()
                userData.value = user
                inProcess.value=false
            }
        }

    }

    fun logIn(email: String , password: String){
        if(email.isEmpty() or password.isEmpty()){
            handleException(customMessage = "Please Fill All Fields.")
            return
        }else{
            inProcess.value = true
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    signIn.value =true;
                    auth.currentUser?.uid?.let {
                        getUserData(it)
                    }
                }else{
                    handleException(it.exception,"LogIn failed.")
                }
            }


        }
    }

    fun handleException(exception: Exception?=null,customMessage:String=""){
        Log.e( "handleException","GhoslyChatApp Exception",exception)
        exception?.printStackTrace()
        val errorMessage = exception?.localizedMessage?:""
        val message = if(customMessage.isNullOrEmpty()) errorMessage else customMessage

        eventMutableState.value = Event(message)
        inProcess.value = false

    }
}
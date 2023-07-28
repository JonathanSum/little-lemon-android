package com.example.littlelemon

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonColor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun ProfileScreen(navController: NavController, user:User){

//    val firstNameLiveData = MutableLiveData<String>(user.firstName)
////    val lastNameLiveData = MutableLiveData<String>(user.lastName)
////    val emailLiveData = MutableLiveData<String>(user.email)

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

        Text(text="ProfileScreen Screen", fontSize=48.sp)
        Button(onClick = {
            navController.navigate(Home.route)
        }){
            Text(text="Home")
        }

        ProfileBlock(firstName,
            lastName,
            email,
            firstName->firstName)
    }
}
@Composable
fun ProfileBlock(firstName1:String, lastName:String, email:String){
    Column(
    ){
        Image(
            painter = painterResource(
                id = R.drawable.login_logo),
            contentDescription = "Logo Image",
            modifier = Modifier
                .requiredHeight(150.dp)
                .graphicsLayer(
                    // adding some zoom limits (min 50%, max 200%)
                    scaleX = maxOf(.5f, minOf(3f, 5f)),
                    scaleY = maxOf(.5f, minOf(3f, 5f)),
                ),
        )
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = LittleLemonColor.darkGreen)
                .requiredHeight(120.dp)) {
            Text(text="Let's get to know you",
                color= LittleLemonColor.white,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                textAlign = TextAlign.Justify,)
        }

        Text(text="Personal information",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 60.dp, bottom = 60.dp),
            textAlign = TextAlign.Left,)

        Text(text="First Name",modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 15.dp),

            textAlign = TextAlign.Left,)
        OutlinedTextField(
            value = firstName1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            onValueChange = {firstName1 = it},
//            label = {Text(text = "First Name")} ,
            shape = RoundedCornerShape(20)
        )
        Text(
            text="Last Name",modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            textAlign = TextAlign.Left,)
        OutlinedTextField(
            value = lastName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            onValueChange = {
                    value -> lastName = value
            },
//            label = {Text(text = "Last Name")},
            shape = RoundedCornerShape(20)
        )
        Text(text="Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            textAlign = TextAlign.Left,)
        OutlinedTextField(
            value = email,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            onValueChange = {
                email = it
            },
//            label = {Text(text = "Email")},
            shape = RoundedCornerShape(20)
        )
        Button(
            onClick = {

            },
            colors =  ButtonDefaults.buttonColors(LittleLemonColor.yellow),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 50.dp, bottom = 100.dp),
            shape = RoundedCornerShape(20),
        ){
            Text(
                text = "Register ",
            )
        }
    }
}
package com.example.littlelemon

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit

@Composable
fun ProfileScreen(navController: NavController,onLogout:()->Unit, sharedPreferences: android.content.SharedPreferences){

//    val firstNameLiveData = MutableLiveData<String>(user.firstName)
////    val lastNameLiveData = MutableLiveData<String>(user.lastName)
////    val emailLiveData = MutableLiveData<String>(user.email)

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
//        var firstName by remember { mutableStateOf(user.firstName) }
//        var lastName by remember { mutableStateOf(user.lastName) }
//        var email by remember { mutableStateOf(user.email) }

        val firstNameLiveData = MutableLiveData<String>()
        val lastNameLiveData = MutableLiveData<String>()
        val emailLiveData = MutableLiveData<String>()

        firstNameLiveData.value = sharedPreferences.getString("firstName", "")
        lastNameLiveData.value = sharedPreferences.getString("lastName", "")
        emailLiveData.value = sharedPreferences.getString("email", "")

        Text(text="ProfileScreen Screen", fontSize=48.sp)
        Button(onClick = {
            navController.navigate(Home.route)
        }){
            Text(text="Home")
        }

        ProfileBlock(navController, firstNameLiveData.value.toString(),
            lastNameLiveData.value.toString(),
            emailLiveData.value.toString(),{
                firstNameLiveData.value = it
            },{
                lastNameLiveData.value = it
            },{
                emailLiveData.value = it
            },onLogout
            )
    }
}
@Composable
fun ProfileBlock(navController:NavController, firstName:String,
                 lastName:String,
                 email:String,
                 onChangefName:(String)->Unit,
                 onChangelName:(String)->Unit,
                 onChangeEmail:(String)->Unit,onLogout:()->Unit){
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scrollState)
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
            value = firstName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            onValueChange = onChangefName,
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
            onValueChange =
                    onChangelName
            ,
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
            onValueChange =
                onChangeEmail
            ,
//            label = {Text(text = "Email")},
            shape = RoundedCornerShape(20)
        )
        Button(
            onClick = {
                onLogout()
                Toast.makeText(context,"Logout successful!", Toast.LENGTH_LONG).show()
                navController.navigate(OnboardingRoute.route)
                      }
            ,
            colors =  ButtonDefaults.buttonColors(LittleLemonColor.yellow),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 100.dp, bottom = 100.dp),
            shape = RoundedCornerShape(20),
        ){
            Text(
                text = "Log out ",
            )
        }
    }
}
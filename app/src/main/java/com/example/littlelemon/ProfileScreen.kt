package com.example.littlelemon

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.core.content.edit
import com.example.littlelemon.ui.theme.LittleLemonTheme

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


        ProfileBlock(navController, firstNameLiveData.value.toString(),
            lastNameLiveData.value.toString(),
            emailLiveData.value.toString(),{
                firstNameLiveData.value = it
            },{
                lastNameLiveData.value = it
            },{
                emailLiveData.value = it
            },onLogout, {navController.navigate(Home.route)}
            )
    }
}
@Composable
fun ProfileBlock(navController:NavController, firstName:String,
                 lastName:String,
                 email:String,
                 onChangefName:(String)->Unit,
                 onChangelName:(String)->Unit,
                 onChangeEmail:(String)->Unit,onLogout:()->Unit,
                homeRedirect:()->Unit){
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
                )
                .clickable(onClick = {
                    homeRedirect()
                })
        )

        Text(text="Personal information",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 60.dp, bottom = 60.dp),
            textAlign = TextAlign.Left,
        color = LittleLemonColor.charcoal)
            


        Text(text="First Name",modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            textAlign = TextAlign.Left,
            color = LittleLemonColor.charcoal)
        OutlinedTextField(
            value = firstName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            onValueChange = onChangefName,
//            label = {Text(text = "First Name")} ,
            shape = RoundedCornerShape(20)
            , textStyle = TextStyle(color = LittleLemonColor.charcoal),
        )
        Text(
            text="Last Name",modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            textAlign = TextAlign.Left,
            color = LittleLemonColor.charcoal)
        OutlinedTextField(
            value = lastName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            onValueChange =
                    onChangelName
            , textStyle = TextStyle(color = LittleLemonColor.charcoal),
//            label = {Text(text = "Last Name")},
            shape = RoundedCornerShape(20)
        )
        Text(text="Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            textAlign = TextAlign.Left,
            color = LittleLemonColor.charcoal)
        OutlinedTextField(
            value = email,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            onValueChange =
                onChangeEmail
            , textStyle = TextStyle(color = LittleLemonColor.charcoal),
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
                text = "Log out ",color = LittleLemonColor.charcoal
            )
        }
    }
}
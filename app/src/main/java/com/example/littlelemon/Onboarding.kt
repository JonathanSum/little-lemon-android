package com.example.littlelemon

import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTheme


@Composable
fun LoginScreen(onSubmit:(f:String, l:String, e:String)->Unit){

//    val SharedPreferences  sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//    val value = sharedPreferences.getString("key", "default value")
//
//
//    sharedPreferences.edit(commit = true) {
//         putString("key",”value”)
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scrollState)
//        verticalArrangement = Arrangement.Center,
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
            value = "",modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            onValueChange = {},
            label = {Text(text = "Mary")} ,
            shape = RoundedCornerShape(20)
        )
        Text(
            text="Last Name",modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            textAlign = TextAlign.Left,)
        OutlinedTextField(
            value = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            onValueChange = {},
            label = {Text(text = "Lee")},
            shape = RoundedCornerShape(20)
        )
        Text(text="Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            textAlign = TextAlign.Left,)
        OutlinedTextField(
            value = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            onValueChange = {},
            label = {Text(text = "A123@gmail.com")},
            shape = RoundedCornerShape(20)
        )
        Button(
            onClick = {onSubmit("f1","l1","e1")},
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
@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("") }
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
//                style = MaterialTheme.typography.bodyMedium


            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}


@Composable
fun Onboarding(navController: NavController){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LittleLemonTheme {
            LoginScreen( ::onSubmit1)
        }
    }
}













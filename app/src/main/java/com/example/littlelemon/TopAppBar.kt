package com.example.littlelemon

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

//@Preview
@Composable
fun TopAppBar(navController: NavHostController, turnOn : Boolean = true, openMenu:()->Unit){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier=Modifier.fillMaxWidth().padding(top=10.dp),

        verticalAlignment = Alignment.CenterVertically) {


        IconButton(onClick = {
                openMenu()
        }) {
            if(turnOn){
            Image(
                painter = painterResource(id = R.drawable.baseline_menu_24),
                contentDescription = "Left Open Icon",
                modifier = Modifier.size(24.dp)
            )}
        }


        Image(painter = painterResource(id = R.drawable.littlelemonimgtxt),
            contentDescription = "little lemon img txt",
            modifier = Modifier.fillMaxWidth(0.5f)
                .padding(horizontal=20.dp)
        )
        IconButton(onClick = {
            navController.navigate(Profile.route)
        }) {
            Image(painter = painterResource(id = R.drawable.icon),
                contentDescription = "Right user Icon",
                modifier = Modifier.size(30.dp).clip(RoundedCornerShape(50.dp))
            )
        }
        }

}
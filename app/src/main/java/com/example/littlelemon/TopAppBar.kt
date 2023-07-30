package com.example.littlelemon

import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

//@Preview
@Composable
fun TopAppBar(navController: NavHostController, scaffoldState: ScaffoldState? = null, scope: CoroutineScope? = null){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier=Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                scope?.launch{scaffoldState?.drawerState?.open()}
            }) {
                Image(painter = painterResource(id = R.drawable.ic_launcher),
                contentDescription = "Left Open Icon",
                    modifier = Modifier.size(24.dp)
                    )
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
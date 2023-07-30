package com.example.littlelemon

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
 fun HomeScreen(navController: NavController, databaseMenuItems:  List<MenuItemRoom>){



    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text="HomeScreen Screen", fontSize=48.sp)
        Button(onClick = {
            navController.navigate(Profile.route)
        }){
            Text(text="Profile")
        }



        if (databaseMenuItems.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                text = "The menu is empty"
            )
        } else {
            MenuItemsList(databaseMenuItems)
        }


    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemsList(databaseMenuItems:  List<MenuItemRoom>) {
    if (databaseMenuItems.isEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            text = "The menu is empty"
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp)
        ) {
            items(
                items = databaseMenuItems,
                itemContent = { menuItem ->
                    Column() {
                        Text(menuItem.title)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
//                        Text(menuItem.description)

                            GlideImage(
                                model = menuItem.image,
                                contentDescription="Food Image"
                            )
//                        Text(
//                            modifier = Modifier
//                                .weight(1f)
//                                .padding(5.dp),
//                            textAlign = TextAlign.Right,
//                            text = menuItem.image
//                        )
                        }
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(5.dp),
                            textAlign = TextAlign.Right,
                            text = "%.2f".format(menuItem.price)
                        )

                    }

                }
            )
        }
    }


}
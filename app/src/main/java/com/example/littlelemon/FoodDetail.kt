package com.example.littlelemon


import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FoodDetail(navController: NavHostController, id: Int, databaseMenuItems: List<MenuItemRoom>) {
    if (databaseMenuItems.isEmpty()) {
        Text("Error: Menu List is empty.")
    }

    val food = requireNotNull(databaseMenuItems.filter { it.id == id })


    Column(horizontalAlignment =Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly,

    ) {
        var counter by remember {
            mutableStateOf(1)
        }

        TopAppBar(navController)
        GlideImage(
            model = food[0].image,
            contentDescription = "Food Image",
            modifier = Modifier.clip(RoundedCornerShape(1.dp)).padding(vertical = 50.dp)
        )


            Text(text = food[0].title, style =
            MaterialTheme.typography.h1)
            Text(text = food[0].description, style =
            MaterialTheme.typography.body1)

            Counter(counter,{counter++},{counter--})

            Button(
                modifier = Modifier.fillMaxWidth(0.75f).padding(10.dp),
                onClick = {
                    //No back server yet
                },
            ) {
                val total:String = "Add for ${"%.2f".format(counter*food[0].price)}"
                Text(text = total, textAlign = TextAlign.Center)

            }

    }
}

@Composable
fun Counter(counter:Int,increase:()->Unit,decrease:()->Unit) {
//fun Counter() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth(),
    ) {

        TextButton(onClick = {
            decrease()
        }) {
            Text(
                text = "-", style = MaterialTheme.typography.h2
            )
        }
        Text(
            text = counter.toString(),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(16.dp)
        )
        TextButton(onClick = {
            increase()
        }) {
            Text(
                text = "+", style = MaterialTheme.typography.h2
            )
        }
    }
}
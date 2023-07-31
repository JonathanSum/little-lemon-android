package com.example.littlelemon


import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.littlelemon.ui.theme.LittleLemonColor
//import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
//import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.CoroutineScope

//@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FoodDetail(
    navController: NavHostController, id: Int, databaseMenuItems: List<MenuItemRoom>,

    ) {
    if (databaseMenuItems.isEmpty()) {
        Text("Error: Menu List is empty.")
    }

    val food = requireNotNull(databaseMenuItems.filter { it.id == id })


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,

        ) {
        var counter by remember {
            mutableStateOf(1)
        }

        TopAppBar(navController)
//        GlideImage(
//            model = food[0].image,
//            contentDescription = "Food Image",
//            modifier = Modifier
//                .clip(RoundedCornerShape(1.dp))
//                .padding(vertical = 50.dp)
//        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(food[0].image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder_img),
            contentDescription = "Food Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(1.dp))
                .padding(vertical = 50.dp)
                .fillMaxHeight(0.35f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = food[0].title, style =
                MaterialTheme.typography.displayLarge, modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = food[0].description, style =
                MaterialTheme.typography.bodyLarge, color = LittleLemonColor.charcoal
            )

            Counter(counter, { counter++ }, { counter-- })

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(10.dp, top = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.yellow),
                onClick = {
                    //No back server yet
                },
            ) {
                val total: String = "Add for ${"%.2f".format(counter * food[0].price)}"
                Text(text = total, textAlign = TextAlign.Center, color = LittleLemonColor.charcoal)

            }
        }


    }
}

@Composable
fun Counter(counter: Int, increase: () -> Unit, decrease: () -> Unit) {
//fun Counter() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {

        TextButton(onClick = {
            decrease()
        }) {
            Text(
                text = "-", style = MaterialTheme.typography.displayMedium
            )
        }
        Text(
            text = counter.toString(),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(16.dp)
        )
        TextButton(onClick = {
            increase()
        }) {
            Text(
                text = "+", style = MaterialTheme.typography.displayMedium
            )
        }
    }
}
package com.example.littlelemon


import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


//@Preview
@Composable
fun LowerPanel(navController: NavHostController, dishes: List<MenuItemRoom> = listOf()){
    Column{
        WeeklySpecialCard()
        LazyColumn {
            itemsIndexed(dishes){_, dish->
                Log.d("Log Message dish", dish.title)
                MenuDish(navController, dish)
            }
        }
    }
}
@Composable
fun WeeklySpecialCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            text = stringResource(R.string.order_for_delivery),
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun MenuDish(navController: NavHostController? = null, food: MenuItemRoom){
    Card(onClick = {
//        Log.d("AAA", "Click ${dish.id}")
        navController?.navigate(FoodDetail.route + "/${food.id}")
    }){
    Row( modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(){
           Text(text =  food.title, style=MaterialTheme.typography.h2)
            Text(text =  food.description, style=MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxWidth(0.75f).padding(top=5.dp, bottom=5.dp))
            Text(text = "%.2f".format(food.price), style = MaterialTheme.typography.body2)
        }

        GlideImage(
            model = food.image,
            contentDescription="Food Image",
            modifier = Modifier.clip(RoundedCornerShape(1.dp))
        )
    }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end=8.dp),
        thickness = 1.dp
    )
}

@Preview
@Composable
fun ScrollableRow() {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        repeat(20) {
            Card(
                elevation = 16.dp,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = it.toString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
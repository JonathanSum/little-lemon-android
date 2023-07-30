package com.example.littlelemon


import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
//import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
//import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonColor


//@Preview
@Composable
fun LowerPanel(navController: NavHostController, dishes: List<MenuItemRoom> = listOf()){
    Column{
        WeeklySpecialCard()
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
            Categories.forEach{
                MenuCategory(category = it)
            }
        }
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
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}
//select bar
@Composable
fun MenuCategory(category: String){
    Button(
        onClick = {},
    colors = ButtonDefaults.buttonColors(contentColor = LittleLemonColor.LightlyGrey),
    shape = RoundedCornerShape(40),
        modifier = Modifier.padding(5.dp)
    ){
        Text(text = category)
    }
}
val Categories = listOf<String>(
    "Starters", "Mains", "Desserts", "Drinks"
)

//select bar


//@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDish(navController: NavHostController? = null, food: MenuItemRoom){
    Card(onClick = {
//        Log.d("AAA", "Click ${dish.id}")
        navController?.navigate(FoodDetail.route + "/${food.id}")
    }){
    Row( modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(){
           Text(text =  food.title, style=MaterialTheme.typography.displayMedium)
            Text(text =  food.description, style=MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(0.75f).padding(top=5.dp, bottom=5.dp))
            Text(text = "%.2f".format(food.price), style = MaterialTheme.typography.bodyMedium)
        }

//        GlideImage(
//            model = food.image,
//            contentDescription="Food Image",
//            modifier = Modifier.clip(RoundedCornerShape(1.dp))
//        )
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
//                elevation = 16.dp,
                elevation = CardDefaults.cardElevation(16.dp),
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
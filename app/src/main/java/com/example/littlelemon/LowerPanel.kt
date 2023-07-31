package com.example.littlelemon


//import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
//import com.bumptech.glide.integration.compose.GlideImage
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.littlelemon.ui.theme.LittleLemonColor


//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LowerPanel(navController: NavHostController, foods: List<MenuItemRoom> = listOf()){




    var orderMenuItems by remember {mutableStateOf(false)}

    var results = if (orderMenuItems) foods.sortedBy{it.title} else foods

//    Log.d("Debug", " Before Testing")
//
//    for ( x in results){
//        Log.d("Debug", x.title)
//    }
//    Log.d("Debug","Testing ended")

    Column(modifier = Modifier.background(LittleLemonColor.white)){
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonColor.green),

        ) {


            val textField = remember { FocusRequester() }
            val focusManager = LocalFocusManager.current
            var iconName by remember { mutableStateOf(R.drawable.baseline_search_24) }
            var searchFocus by remember { mutableStateOf(false) }

            var searchPhrase by remember {mutableStateOf("")}
            if(searchPhrase != "") {
                Log.d("Debug", " Before Testing")

                for ( x in results){
                    Log.d("Debug", x.title)
                }
                Log.d("Debug","Testing ended")


//                results = results.filter { it.title.contains(searchPhrase) }
                results = if (searchPhrase !="")
                          results.filter { it.title.contains(searchPhrase) } else results
                Log.d("Debug", "result of filtering with title"+ searchPhrase)
                Log.d("Debug","Print started with size of "+results.size)
                for ( x in results){
                    Log.d("Debug", x.title)
                }
                Log.d("Debug","Print ended")



            }
            else foods

            TextField(
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = LittleLemonColor.LightlyGrey,
                    focusedContainerColor = LittleLemonColor.cloud,
                ),
                textStyle = TextStyle(color = LittleLemonColor.charcoal),

                modifier = Modifier.padding(vertical = 16.dp, horizontal = 18.dp)
                    .focusRequester(textField).fillMaxWidth().onFocusChanged {
                        iconName = if (it.isFocused) R.drawable.baseline_arrow_back_24 else R.drawable.baseline_search_24

                        searchFocus = it.isFocused
                    }.clip(RoundedCornerShape(10.dp)),
                value = searchPhrase,
                onValueChange = {
                    searchPhrase = it

                },
                leadingIcon = {
                    IconButton(
                        onClick = {
                            if (searchFocus){focusManager.clearFocus()}
                        },
                    ) {
                        Icon(
                            painter = painterResource(id =

                            if (searchFocus) R.drawable.baseline_arrow_back_24 else R.drawable.baseline_search_24
                            ),
                            contentDescription = "Search Icon",
                        )
                    }
                },
                placeholder = {Text(color = LittleLemonColor.charcoal, text = "Enter search phrase")}

            )

        }
        OrderTitle()
        LazyRow(state = rememberLazyListState(), modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            itemsIndexed(Categories){_, category->
//                Log.d("Log Message dish", dish.title)
                MenuCategory(category)
            }
        }



        Divider(
            modifier = Modifier.padding(start = 8.dp, end=8.dp,
                                        top = 20.dp, bottom = 20.dp),
            thickness = 1.dp
        )
        LazyColumn (

                ){

//            itemsIndexed(foods){_, food->
////                Log.d("Log Message dish", dish.title)
//                MenuDish(navController, food)
//            }

            itemsIndexed(results){_, food->
//                Log.d("Log Message dish", dish.title)
                MenuDish(navController, food)
            }
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleFilledTextFieldSample() {

    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(Modifier.clickable{keyboardController?.hide()}.padding(50.dp)
        .onFocusChanged {
            val isFocused = it.isFocused
            val hasFocus = it.hasFocus
            val isCaptured= it.isCaptured
        }

    ){
        TextField(
            value = text,
            onValueChange = { text = it },
        )

        var checked by remember { mutableStateOf(false) }

        Switch(
            checked = checked,
            onCheckedChange = { checked = it },
            // Prevent component from being focused
            modifier = Modifier
                .focusProperties { canFocus = true   }
        )

    }

}
@Composable
fun OrderTitle(){
        Text(
            text = stringResource(R.string.order_for_delivery),
            style = MaterialTheme.typography.displayMedium,
            color = LittleLemonColor.charcoal,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .padding(start = 5.dp),

        )
}
//select bar
@Composable
fun MenuCategory(category: String){
    Button(
        onClick = {},
    colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.cloud),
    shape = RoundedCornerShape(50),
        modifier = Modifier.padding(5.dp)
    ){
        Text(text = category, color = LittleLemonColor.green)
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
    Card(
        onClick = {
//        Log.d("AAA", "Click ${dish.id}")
            navController?.navigate(FoodDetail.route + "/${food.id}")


        },
        colors = CardDefaults.cardColors(
            containerColor = LittleLemonColor.white
        ),

        ){
    Row( modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .fillMaxHeight(0.2f)


    ) {
        Column(modifier = Modifier.padding(horizontal = 8.dp)){
           Text(text =  food.title, style=MaterialTheme.typography.displayMedium)
            Text(text =  food.description, style=MaterialTheme.typography.bodyLarge,
                color = LittleLemonColor.charcoal,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(top = 5.dp, bottom = 5.dp))
            Text(text = "$%.2f".format(food.price),color = LittleLemonColor.charcoal, style = MaterialTheme.typography.bodyMedium)
        }



        
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(food.image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder_img),
            contentDescription = food.title,
            contentScale = ContentScale.FillHeight,

            alignment = Alignment.BottomStart,

            modifier = Modifier
                .clip(RoundedCornerShape(1.dp))
                .fillMaxHeight(0.3f)
                .aspectRatio(1f)
                .width(IntrinsicSize.Min)


        )

    }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end=8.dp),
        thickness = 0.5.dp
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
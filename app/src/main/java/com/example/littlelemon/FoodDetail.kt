package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FoodDetail(id:Int, databaseMenuItems:  List<MenuItemRoom>){
    val food = 
//    val dish = requireNotNull(FoodRepository.getDish(id))
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)){
        TopAppBar()
//        Image(painter = , contentDescription = )
        Column(){
            Text(text="")
            Text(text="")
            Counter()
            Button(onClick = {

            }, ){
                Text(text="", textAlign = TextAlign.Center)

            }
        }
    }
}
@Composable
fun Counter(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ){
        var counter by remember{
            mutableStateOf(1)
        }
        TextButton(
            onClick = {
                counter--
            }
        ){
            Text(
                text = "-",
                style = MaterialTheme.typography.h2
            )
        }
        Text(
            text = counter.toString(),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(16.dp)
        )
        TextButton(
            onClick={
                counter++
            }
        ){
            Text(
                text = "+",
                style = MaterialTheme.typography.h2
            )
        }
    }
}
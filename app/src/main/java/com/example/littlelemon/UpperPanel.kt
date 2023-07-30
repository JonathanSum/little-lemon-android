package com.example.littlelemon


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun UpperPanel(){
    Column(
        modifier = Modifier
            .padding(start = 12.dp,end = 12.dp, top=16.dp, bottom = 16.dp)
    ){
        Text(text = "Little Lemon",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold)
        Text(text = "Chicago",
        fontSize = 24.sp)
        Row(horizontalArrangement =  Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 20.dp)
        ){
            Text(
                text = stringResource(id = R.string.description)
            )
            Image(
                painter = painterResource(id = R.drawable.upperpanelimage),
                contentDescription = "Upper Panel Image",
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
            )
        }
    }
}
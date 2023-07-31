package com.example.littlelemon


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonColor


@Composable
fun UpperPanel(){
    Column(
        modifier = Modifier
            .padding(top=16.dp).background(LittleLemonColor.backgroundColorUpper).padding(horizontal = 10.dp)
    ){
        Text(text = "Little Lemon",
        color = LittleLemonColor.yellow,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,        modifier = Modifier
                .padding(top = 20.dp))
        Text(text = "Chicago",
        fontSize = 24.sp, color = LittleLemonColor.cloud)
        Row(horizontalArrangement =  Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 20.dp)
        ){
            Text(modifier = Modifier
                .fillMaxWidth(0.6f).padding(10.dp, end = 24.dp),
                text = stringResource(id = R.string.description),
                color = LittleLemonColor.cloud
            )
            Image(
                painter = painterResource(id = R.drawable.upperpanelimage),
                contentDescription = "Upper Panel Image",
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(20.dp)),

            )
        }
    }
}
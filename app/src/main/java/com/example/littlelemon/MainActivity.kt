package com.example.littlelemon

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.room.Room
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class MainActivity : ComponentActivity() {
        private val httpClient = HttpClient(Android){
            install(ContentNegotiation){
                json(contentType = ContentType("text", "plain"))
            }
        }
    private val database by lazy{
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            LittleLemonTheme {
                val databaseMenuItems by database.menuItemDao().getAll()
                    .observeAsState(emptyList())
                // add is not empty check here
                if (databaseMenuItems.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp),
                        text = "The menu is empty"
                    )
                } else {
                    MenuItemsList(menuItems)
                }
            

            }
        }
    }
    private suspend fun fetchMenu(): List<MenuItemNetwork> {

//        TODO("Retrieve data")
        // data URL: https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json

        Log.d("Log Message", "Hello Here!!!")

        val response: MenuNetwork =
            httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json")
                .body()

        return response.menu

    }

    @Composable
    private fun MenuItemsList(items: List<MenuItemRoom>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp)
        ) {
            items(
                items = items,
                itemContent = { menuItem ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
        Column() {
            
        }
                    }
            )
        }
    }

}
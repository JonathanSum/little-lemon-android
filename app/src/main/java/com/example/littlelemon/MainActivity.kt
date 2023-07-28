package com.example.littlelemon

import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    private val firstNameLiveData = MutableLiveData<String>()
    private val lastNameLiveData = MutableLiveData<String>()
    private val emailLiveData = MutableLiveData<String>()
    private val sharedPreferencesListener = OnSharedPreferenceChangeListener {sharedPreferences, key ->
        if(key=="firstName"){
            firstNameLiveData.value = sharedPreferences.getString(key, "")
        }
        if(key=="lastName"){
            lastNameLiveData.value = sharedPreferences.getString(key, "")
        }
        if(key=="email"){
            emailLiveData.value = sharedPreferences.getString(key, "")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstNameLiveData.value = sharedPreferences.getString("firstName", "")
        lastNameLiveData.value = sharedPreferences.getString("lastName", "")
        emailLiveData.value = sharedPreferences.getString("email", "")
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesListener)

        setContent {
            LittleLemonTheme {

                fun onSubmit(firstName:String, lastName:String, email:String){

                    sharedPreferences.edit(commit = true) { putString(firstName,"")}
                    sharedPreferences.edit(commit = true) { putString(lastName,"")}
                    sharedPreferences.edit(commit = true) { putString(email,"")}

                }
                MyNavigation(::onSubmit)
                /*
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
                    MenuItemsList(databaseMenuItems)
                }

                */

            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menuList: List<MenuItemNetwork> = fetchMenu()
                saveMenuToDatabase(menuList)
            }
        }
    }


    private suspend fun fetchMenu(): List<MenuItemNetwork> {

//        TODO("Retrieve data")
        // data URL: https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json


        val response: MenuNetwork =
            httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body()

        return response.menu

    }
    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>){
        val menuItemsRoom = menuItemsNetwork.map{it.toMenuItemRoom()}
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())

    }
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
                Column() {
                    Text(menuItem.title)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(menuItem.description)
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(5.dp),
                            textAlign = TextAlign.Right,
                            text = "Img link"
                        )
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





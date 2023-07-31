package com.example.littlelemon

import  android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
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
        when (key) {
            "firstName" -> {
                firstNameLiveData.value = sharedPreferences.getString(key, "")
            }
            "lastName" -> {
                lastNameLiveData.value = sharedPreferences.getString(key, "")
            }
            "email" -> {
                emailLiveData.value = sharedPreferences.getString(key, "")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LittleLemonTheme {
                //TODO("I think I do not need the Live Data here")
                firstNameLiveData.value = sharedPreferences.getString("firstName", "")
                lastNameLiveData.value = sharedPreferences.getString("lastName", "")
                emailLiveData.value = sharedPreferences.getString("email", "")


                sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesListener)

                var  logged = false;
//                Log.d("Log Message", "Hello Here started!!!")
//                Log.d("Log Message", sharedPreferences.getString("firstName", "").toString())
//                Log.d("Log Message", sharedPreferences.getString("lastName", "").toString())
//                Log.d("Log Message", sharedPreferences.getString("email", "").toString())
                val user  = User(firstNameLiveData.value!!,
                lastNameLiveData.value!!,emailLiveData.value!!)
                if(firstNameLiveData.value!!.isNotBlank()
                    && lastNameLiveData.value !!.isNotBlank()
                    && emailLiveData.value !!.isNotBlank()){
                    logged = true;
                }

                Log.d("Log Message", "Hello Here ended!!!")
                fun onSubmit(firstName:String, lastName:String, email:String){
                    Log.d("Log Message", "onSubmit started!!!")
                    sharedPreferences.edit(commit = true) { putString("firstName",firstName)}
                    sharedPreferences.edit(commit = true) { putString("lastName",lastName)}
                    sharedPreferences.edit(commit = true) { putString("email",email)}

                    Log.d("Log Message", "onSubmit ended!!!")
                    Log.d("Log Message", firstName)
                    Log.d("Log Message", lastName)
                    Log.d("Log Message", email)
                    Log.d("Log Message", "Now the user information in sharedPreferences:")


                    Log.d("Log Message", sharedPreferences.getString("firstName", "").toString())
                    Log.d("Log Message", sharedPreferences.getString("lastName", "").toString())
                    Log.d("Log Message", sharedPreferences.getString("email", "").toString())

                    Log.d("Log Message", "Now the user information in LiveData:")
                    Log.d("Log Message", firstNameLiveData.value!!)
                    Log.d("Log Message", lastNameLiveData.value!!)
                    Log.d("Log Message", emailLiveData.value!!)
                }
                fun onLogOut(){
                    sharedPreferences.edit(commit = true) { putString("firstName","")}
                    sharedPreferences.edit(commit = true) { putString("lastName","")}
                    sharedPreferences.edit(commit = true) { putString("email","")}
                    logged = false;
                }


                val databaseMenuItems by database.menuItemDao().getAll()
                    .observeAsState(emptyList())



                MyNavigation(logged, ::onSubmit,::onLogOut, sharedPreferences, databaseMenuItems)
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





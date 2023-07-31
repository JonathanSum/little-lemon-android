package com.example.littlelemon

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor
//import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
//import com.bumptech.glide.integration.compose.GlideImage

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
 fun HomeScreen(
    navController: NavHostController,
    databaseMenuItems: List<MenuItemRoom>,
){
    HomePage(navController, databaseMenuItems)
}


//@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemsList(databaseMenuItems:  List<MenuItemRoom>) {
    if (databaseMenuItems.isEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            text = "The menu is empty"
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp)
        ) {
            items(
                items = databaseMenuItems,
                itemContent = { menuItem ->
                    Column() {
                        Text(menuItem.title)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
//                        Text(menuItem.description)

//                            GlideImage(
//                                model = menuItem.image,
//                                contentDescription="Food Image"
//                            )
//                        Text(
//                            modifier = Modifier
//                                .weight(1f)
//                                .padding(5.dp),
//                            textAlign = TextAlign.Right,
//                            text = menuItem.image
//                        )
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


}
@Composable
fun HomePage(navController: NavHostController, databaseMenuItems:  List<MenuItemRoom>) {
//    val scaffoldState = rememberScaffoldState()
//    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = LittleLemonColor.green) {
                Text("Little Lemon  Restaurant", modifier = Modifier.padding(16.dp),
                    color = LittleLemonColor.yellow)
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item", color = LittleLemonColor.charcoal)},
                    selected = false,
                    onClick = { /*TODO*/ },
                    colors =
                    NavigationDrawerItemDefaults.colors(
                        selectedTextColor = LittleLemonColor.yellow,
                        unselectedBadgeColor = LittleLemonColor.yellow,
                        unselectedIconColor = LittleLemonColor.charcoal,
                        unselectedContainerColor = LittleLemonColor.cloud,
                    )
                )
                // ...other drawer items
            }
        },
    ) {
    Scaffold(
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                text = { Text("Show drawer") },
//                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
//                onClick = {
//                    scope.launch {
//                        drawerState.apply {
//                            if (isClosed) open() else close()
//                        }
//                    }
//                }
//            )
//        },

        topBar = { TopAppBar(navController, true) {
            scope.launch {
                drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        }
        })
    { contentPadding ->


        Column(modifier = Modifier.padding(contentPadding)) {
            UpperPanel()
//             add is not empty check here
                if (databaseMenuItems.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp),
                        text = "The menu is empty"
                    )
                } else {
                    LowerPanel(navController, databaseMenuItems)
                }
            }
        }
    }
}
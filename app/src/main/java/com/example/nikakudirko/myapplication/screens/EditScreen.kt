package com.example.nikakudirko.myapplication.screens

import android.widget.HorizontalScrollView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.nikakudirko.myapplication.NewsArticle
import com.example.nikakudirko.myapplication.R
import com.example.nikakudirko.myapplication.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen( navController: NavController, newsArticle: NewsArticle? = null){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_light_green))
            .padding(20.dp),
       // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        var filledText by remember {
            mutableStateOf("")
        }

        val checkedState = remember {
            mutableStateOf(true)
        }
        TextField(
            modifier = Modifier,
            value = filledText,
            onValueChange = { filledText = it},
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Left
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Green,
                unfocusedIndicatorColor = Color.White
            ),
            placeholder = { Text(" name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            maxLines = 1
        )

        TextField(
            modifier = Modifier
                .padding(vertical = 10.dp),
            value = filledText,
            onValueChange = { filledText = it},
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Left
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Green,
                unfocusedIndicatorColor = Color.White
            ),
            placeholder = { Text(" title") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AutoStories,
                    contentDescription = null
                )
            },
            maxLines = 1
        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){

            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.DarkGray,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = Color.Blue,
                    uncheckedTrackColor = Color.Green,
                )
            )
            Text(
                modifier = Modifier.padding(start = 10 .dp),
                text = "Is this artickle draft"
            )

        }


        Column(
            modifier = Modifier.height(300 .dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Transparent),
                value = filledText,
                onValueChange = { filledText = it},
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Left
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.White
                ),
                placeholder = { Text(" name") },
            )
        }


        Button(
            onClick = {
                navController.navigate(Screen.HomeScreen.route){
                    launchSingleTop = true //?????
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    restoreState = true
                }
            }
        ) {
            Icon(imageVector = Icons.Filled.AttachFile, contentDescription = null )
            Text(
                text = if(newsArticle == null) "Add new article"
                else "Commit changes"
            )
        }
    }


}

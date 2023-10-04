package com.example.nikakudirko.myapplication.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        var authorNameText by remember {
            mutableStateOf("")
        }
        var titleText by remember {
            mutableStateOf("")
        }
        var articleText by remember {
        mutableStateOf("")
    }


        val checkedState = remember {
            mutableStateOf(true)
        }

        Text(
            text = "Edit screen",
            color = colorResource(id = R.color.text_dark_green2),
            fontSize = 35 .sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 4 .sp
        )
        TextField(
            modifier = Modifier,
            value = authorNameText,
            onValueChange = { authorNameText = it},
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Left
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = colorResource(id = R.color.card_back),
                containerColor = Color.Transparent,
                focusedLeadingIconColor = colorResource(id = R.color.card_back),
                unfocusedIndicatorColor = colorResource(id = R.color.text_dark_green2),
                unfocusedLeadingIconColor = colorResource(id = R.color.text_dark_green2),
                placeholderColor = colorResource(id = R.color.text_dark_green2),
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
            value = titleText,
            onValueChange = { titleText = it},
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Left
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = colorResource(id = R.color.card_back),
                containerColor = Color.Transparent,
                focusedLeadingIconColor = colorResource(id = R.color.card_back),
                unfocusedIndicatorColor = colorResource(id = R.color.text_dark_green2),
                unfocusedLeadingIconColor = colorResource(id = R.color.text_dark_green2),
                placeholderColor = colorResource(id = R.color.text_dark_green2),
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
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = colorResource(id = R.color.card_back)
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
                    .fillMaxHeight(),
                value = articleText,
                onValueChange = { articleText = it},
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Left
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = colorResource(id = R.color.card_back),
                    containerColor = Color.Transparent,
                    unfocusedIndicatorColor = colorResource(id = R.color.text_dark_green2),
                    placeholderColor = colorResource(id = R.color.text_dark_green2),
                ),
                placeholder = { Text(" name") },
            )
        }


        Button(
            modifier = Modifier.padding(vertical = 10 .dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.card_back),

            ),
            onClick = {
                navController.navigate(Screen.HomeScreen.route){
                    launchSingleTop = true //?????
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    restoreState = true
                }
            },

        ) {
            Icon(imageVector = Icons.Filled.AttachFile, contentDescription = null )
            Text(
                text = if(newsArticle == null) "Add new article"
                else "Commit changes"
            )
        }
    }


}

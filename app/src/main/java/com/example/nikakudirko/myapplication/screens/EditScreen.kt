package com.example.nikakudirko.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Delete
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.nikakudirko.myapplication.NewsArticle
import com.example.nikakudirko.myapplication.R
import com.example.nikakudirko.myapplication.Screen
import com.example.nikakudirko.myapplication.viewmodels.EditViewModel
import com.example.nikakudirko.myapplication.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun EditScreen(navController: NavController, articleId: String? = null) {

fun EditScreen(navController: NavController, onMemoryUpdate: () -> Unit) {

    val viewModel: EditViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_light_green))
            .padding(20.dp),
        // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
       /* var authorNameText by remember {
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
*/

        TextField(
            modifier = Modifier.width(300 .dp),
            value = uiState.author,
            onValueChange = { newName -> viewModel.setArticleAuthor(newName) },
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
                .padding(vertical = 10.dp)
                .width(300.dp),

            value = uiState.title,
            onValueChange = { newName -> viewModel.setArticleTitle(newName) },
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

        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Switch(

                checked = uiState.isDraft,
                onCheckedChange = { isDraft -> viewModel.setArticleDraft(isDraft) },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = colorResource(id = R.color.card_back)
                )
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "Is this artickle draft"
            )

        }


        /*Column(
            modifier = Modifier.height(250.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxHeight(),
                value = articleText,
                onValueChange = { articleText = it },
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
*/

        Row {
            Button(
                modifier = Modifier.padding(vertical = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.card_back),

                    ),
                onClick = {

                    viewModel.saveArticle()
                    navController.navigate(Screen.HomeScreen.route)
                },

                ) {
                Icon(imageVector = Icons.Filled.AttachFile, contentDescription = null)
                Text(
                    text = "save"
                )
            }


            Button(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 3 .dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.card_back),

                    ),
                onClick = {

                    viewModel.deleteArticle()
                    navController.navigate(Screen.HomeScreen.route)

                },

                ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                Text(
                    text = "delete"
                )
            }
        }




    }


}

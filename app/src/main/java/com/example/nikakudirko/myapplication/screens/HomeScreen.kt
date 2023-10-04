package com.example.nikakudirko.myapplication.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.nikakudirko.myapplication.NewsArticle
import com.example.nikakudirko.myapplication.R
import com.example.nikakudirko.myapplication.ArtickleRepository
import com.example.nikakudirko.myapplication.Screen

@Composable
fun HomeScreen(navController: NavController){

    val articklesRepository = ArtickleRepository()
    val rep = articklesRepository.getAll()

    val deletedItem = remember {
        mutableStateListOf<NewsArticle>()
    }

    Column {
        Text(text = "Home screen")

        LazyColumn(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxSize()
                .background(colorResource(id = R.color.background_light_green))
        ){
            items(items = rep){ item ->
                newCard(navController, item)
            }
        }
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun newCard(navController: NavController, item: NewsArticle) {
    Card(
        colors = if(item.isDraft)
            CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_back),
        ) else CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.teal_200),
        ) ,
        modifier = Modifier
            .padding(vertical = 4 .dp, horizontal = 8 .dp),
        onClick = {
            navController.navigate(Screen.EditScreen.route){
                launchSingleTop = true //?????
                popUpTo(navController.graph.findStartDestination().id){
                    saveState = true
                }
                restoreState = true
            }
        }
    ){
        articleEntityView(item)
    }
}


@Composable
fun articleEntityView(newsArticle: NewsArticle){
    var expanded by remember {
        mutableStateOf(false)
    }


    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ){

                Text(
                    text = newsArticle.title,
                    fontSize = 20 .sp,
                    fontWeight = FontWeight.Bold
                )

                Text(text = newsArticle.author)

                if(expanded){
                    Column {
                        Text(text = ("kkdkdkkdkncdk\t").repeat(3))


                    }

                }


            }

            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = if(expanded) Icons.Filled.KeyboardArrowUp
                    else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null)
            }

        }
}


package com.example.nikakudirko.myapplication.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
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
import com.example.nikakudirko.myapplication.ArticleRepository
import com.example.nikakudirko.myapplication.Screen

@Composable
fun HomeScreen(navController: NavController){

    val articlesRepository = ArticleRepository()
    val rep = articlesRepository.getAll()


    Column(
        modifier = Modifier
        .background(colorResource(id = R.color.background_light_green)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home screen",
            color = Color.Black,
            fontSize = 35 .sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 4 .sp
        )

        LazyColumn(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxSize()

        ){
            items(items = rep){ item ->
                NewCard(navController, item)
            }
        }


    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCard(navController: NavController, item: NewsArticle) {
    Card(
        colors = if(item.isDraft)
            CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.draft_card),
        ) else CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.not_draft_card),
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
        ArticleEntityView(item)
    }
}


@Composable
fun ArticleEntityView(newsArticle: NewsArticle){
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
                        Text(text = ("в задании ничего не сказано про текст статьи\t").repeat(3))


                    }
                    
                    if(newsArticle.isDraft){
                        Text(text = "Черновик")
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



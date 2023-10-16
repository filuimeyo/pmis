package com.example.nikakudirko.myapplication.screens

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.nikakudirko.myapplication.NewsArticle
import com.example.nikakudirko.myapplication.R
import com.example.nikakudirko.myapplication.Screen
import com.example.nikakudirko.myapplication.viewmodels.HomeViewModel
import java.util.UUID

import androidx.lifecycle.compose.collectAsStateWithLifecycle



@Composable
fun HomeScreen(controller: NavController) {
    val viewModel: HomeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        items = uiState.articles,
        onEdit = { controller.navigate(Screen.EditScreen.route) },
        onRemove = { } ,
        navController = controller
    )
}

@Composable
private fun HomeScreenContent(
    items: List<NewsArticle>,
    onRemove: (NewsArticle) -> Unit,
    onEdit: () -> Unit,
    navController: NavController
) {
    System.out.println(items.toString())
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
            items(items = items){ item ->
               ArticleItem(article = item, onRemove = {

               }, onEdit={navController.navigate(Screen.EditScreen.route)})
            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticleItem(
    article: NewsArticle,
    onRemove: (UUID) -> Unit,
    onEdit: (UUID) -> Unit,
    modifier: Modifier = Modifier,
) {

    Card(
        colors = if(article.isDraft)
            CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.draft_card),
            ) else CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.not_draft_card),
        ) ,
        modifier = Modifier
            .padding(vertical = 4 .dp, horizontal = 8 .dp),
        onClick = {onEdit(article.id)}
    ){
       /* var expanded by remember {
            mutableStateOf(false)
        }*/


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
                    text = article.title,
                    fontSize = 20 .sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    fontSize = 15 .sp,
                    fontWeight = FontWeight.SemiBold,
                    text = "Автор: "  + article.author
                )

              /*  if(expanded){
                    Column {
                        Text(text = ("в задании ничего не сказано про текст статьи\t").repeat(3))


                    }

                    if(article.isDraft){
                        Text(
                            modifier= Modifier.padding(vertical = 5 .dp),
                            fontSize = 15 .sp,
                            text = "Это черновик"
                        )
                    }

                }*/


            }


            Column {
                val contex = LocalContext.current
                IconButton(

                    onClick = {
                        Toast.makeText(contex, "Удаления пока нет", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null
                    )
                }
            }

           /* IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = if(expanded) Icons.Filled.KeyboardArrowUp
                    else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null)
            }*/



        }
    }

}





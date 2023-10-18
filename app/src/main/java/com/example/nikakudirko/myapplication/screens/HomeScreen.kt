package com.example.nikakudirko.myapplication.screens

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.navigation.NavHostController
import com.example.nikakudirko.myapplication.MemoriesNavigationActions


@Composable
fun HomeScreen(navController: NavHostController, paddingValues: PaddingValues) {
    val viewModel: HomeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val navActions: MemoriesNavigationActions = remember(navController) {
        MemoriesNavigationActions(navController)
    }

    HomeScreenContent(
        items = uiState.articles,
        onEdit = { memoryId ->
            navActions.navigateToAddEditMemory(R.string.editscreen, memoryId)

            // navController.navigate(Screen.EditScreen.withArgs(it.toString()))
            /*  val id = it
              navController.navigate(
                  "${ADD_EDIT_MEMORYHOME_SCREEN}/$title".let {
                      //  System.out.println(if (id != null) "$it?$MEMORY_ID_ARG=$id" else it)
                      if (id != null) "$it?$MEMORY_ID_ARG=$id" else it
                  }
              )*/
        },
        onRemove = {
            viewModel.deleteAticle(it)
        },
        navController = navController,
        paddingValues = paddingValues
    )
}

@Composable
private fun HomeScreenContent(
    items: List<NewsArticle>,
    onRemove: (UUID) -> Unit,
    onEdit: (UUID) -> Unit,
    navController: NavController,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(colorResource(id = R.color.background_light_green))

    ) {
        items(items = items) { item ->
            ArticleItem(
                article = item,
                onRemove = {
                    onRemove(it)
                },
                onEdit = {

                    onEdit(it)

                }
            )
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
        colors = if (article.isDraft)
            CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.draft_card),
            ) else CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.not_draft_card),
        ),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp),
        onClick = {
            onEdit(article.id)
        }
    ) {
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
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {

                Text(
                    text = article.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = "Автор: " + article.author
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
                        onRemove(article.id)
                        Toast.makeText(contex, "Item will be deleted", Toast.LENGTH_SHORT).show()
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





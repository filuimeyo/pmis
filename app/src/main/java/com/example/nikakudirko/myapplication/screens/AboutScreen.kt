package com.example.nikakudirko.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nikakudirko.myapplication.MainActivity
import com.example.nikakudirko.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen(){

  /*  Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = colorResource(id = R.color.text_dark_green2),
                ),
                title = {
                    Text(
                        stringResource(R.string.about_screen_top_app_bar),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 50 .dp, start = 20 .dp)
                        ,
                        tint = colorResource(id = R.color.text_dark_green2)
                    )
                },


                )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(colorResource(id = R.color.background_light_green))
                    .verticalScroll(rememberScrollState())

            ){
                Box(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 75.dp)
                        .width(360.dp)

                ){
                    Image(
                        painter = painterResource(id = R.drawable.news_image2),
                        contentDescription = null,

                        )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.about_screen_string),
                        color = Color.Black,
                        fontSize = 45 .sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 6 .sp,

                        )
                    Text(
                        stringResource(id = R.string.about_screen_little_string),
                        color = Color.Black,
                        fontSize = 20 .sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(170 .dp)

                    )
                }



                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ){

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.card_back),
                        ),


                        ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ){
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(50.dp),

                                )
                            Column {
                                Text(
                                    stringResource(R.string.mail_link_name),
                                    color = colorResource(id = R.color.white),
                                    fontSize = 20 .sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                )
                                Text(
                                    stringResource(R.string.mail_link),
                                    color = colorResource(id = R.color.white),
                                    fontSize = 17 .sp
                                )
                            }


                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.5.dp)

                        ){
                            Image(
                                bitmap = ImageBitmap.imageResource(R.drawable.w),

                                contentDescription = null,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(50.dp)
                                    .clip(CircleShape)

                            )
                            Column {
                                Text(
                                    stringResource(R.string.github_link_name),
                                    color = colorResource(id = R.color.white),
                                    fontSize = 20 .sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                )
                                Text(
                                    stringResource(R.string.github_link),
                                    color = colorResource(id = R.color.white),
                                    fontSize = 17 .sp
                                )
                            }


                        }
                    }

                }
                ShowInfo()


            }
        }
    )*/

      Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(colorResource(id = R.color.background_light_green))
                    .verticalScroll(rememberScrollState())

            ){
                Box(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 25.dp)
                        .width(360.dp)

                ){
                    Image(
                        painter = painterResource(id = R.drawable.news_image2),
                        contentDescription = null,

                        )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.about_screen_string),
                        color = Color.Black,
                        fontSize = 45 .sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 6 .sp,

                        )
                    Text(
                        stringResource(id = R.string.about_screen_little_string),
                        color = Color.Black,
                        fontSize = 20 .sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(170 .dp)

                    )
                }



                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ){

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.card_back),
                        ),


                        ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ){
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(50.dp),

                                )
                            Column {
                                Text(
                                    stringResource(R.string.mail_link_name),
                                    color = colorResource(id = R.color.white),
                                    fontSize = 20 .sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                )
                                Text(
                                    stringResource(R.string.mail_link),
                                    color = colorResource(id = R.color.white),
                                    fontSize = 17 .sp
                                )
                            }


                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.5.dp)

                        ){
                            Image(
                                bitmap = ImageBitmap.imageResource(R.drawable.w),

                                contentDescription = null,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(50.dp)
                                    .clip(CircleShape)

                            )
                            Column {
                                Text(
                                    stringResource(R.string.github_link_name),
                                    color = colorResource(id = R.color.white),
                                    fontSize = 20 .sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                )
                                Text(
                                    stringResource(R.string.github_link),
                                    color = colorResource(id = R.color.white),
                                    fontSize = 17 .sp
                                )
                            }


                        }
                    }

                }
                ShowInfo()


            }
}

@Composable
fun ShowInfo(modifier: Modifier = Modifier){

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        MainActivity.aboutMeStrings.forEach{
            Row {
                Text(
                    text = stringResource(id = it),
                    color = colorResource(id = R.color.text_dark_green2),
                    fontSize = 20 .sp
                )
            }
        }
    }
}


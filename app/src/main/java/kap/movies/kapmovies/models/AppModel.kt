package kap.movies.kapmovies.models

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kap.movies.kapmovies.R
import kap.movies.kapmovies.scrapperApi.ScrapperViewModel

object AppModel {
    private var viewModel = ScrapperViewModel()

    @Composable
    fun AppNavBar(
        context: Context,
        backColor: Color = Color.LightGray
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(backColor)
                .padding(vertical = 10.dp),
            text = context.getString(R.string.app_name),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }

    @Composable
    fun AppBody(
        paddingValues: PaddingValues,
        backColor: Color = Color.White
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(backColor)
                .padding(paddingValues)
        ) {
            ListOfData()
        }
    }

    @Composable
    fun ListOfData(
        listOfItem: List<MovieItemDataclass>? = null
    ) {
        LaunchedEffect(key1 = Unit, block = { viewModel.getImageFrom() })
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            items(items = viewModel.arrayOfElements){ item ->
                ItemRow(text = item.title, imageUrl = item.imageUrl)
                Divider(thickness = 3.dp)
            }
        }
    }


    @Composable
    fun ItemRow(
        text: String,
        imageUrl:String
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3F)
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl, contentScale = ContentScale.Inside),
                contentDescription = "Movie Image",
                modifier = Modifier
                    .background(Color.DarkGray)
                    .size(200.dp, 100.dp)
            )
            Spacer(modifier = Modifier.size(width = 15.dp, height = 0.dp))
            Text(text = text)
        }
    }
}
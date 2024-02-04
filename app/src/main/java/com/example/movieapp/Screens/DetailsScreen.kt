package com.example.movieapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.movieapp.R
import com.example.movieapp.Model.Movie
import com.example.movieapp.Model.getMovies
import com.example.movieapp.Widgets.MovieRow

@Composable
fun DetailsScreen(
    navController: NavController,
    movieId: String?
) {
    val newMovieList = getMovies().filter { movie ->
        movie.id == movieId
    }
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Gray,
            elevation = 5.dp,
           // modifier = Modifier.padding(bottom = 20.dp)
        )
        {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Movie", style = MaterialTheme.typography.h6)
            }
        }
    }) {paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight().padding(bottom = paddingValues.calculateBottomPadding()),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = "")
                MovieRow(movie = newMovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text("Movie Images", modifier = Modifier.padding(8.dp))
                HorizontalScrollImage(newMovieList)
            }
        }
    }
}

@Composable
private fun HorizontalScrollImage(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { images ->
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 0.dp,
                color = Color.Transparent
            ) {
                Image(
                    painter = rememberImagePainter(data = images,
                        builder = {
                            crossfade(true)
                            transformations(CircleCropTransformation())
                            scale(Scale.FIT)
                            placeholder(R.drawable.img)
                        }),
                    contentDescription = "Movie poster"
                )
            }
        }
    }
}

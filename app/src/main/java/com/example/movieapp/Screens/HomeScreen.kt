package com.example.movieapp.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.Model.Movie
import com.example.movieapp.Model.getMovies
import com.example.movieapp.Navigation.MovieScreens
import com.example.movieapp.Widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Gray,
            elevation = 80.dp,
        ) {
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Movie", style = MaterialTheme.typography.h6,textAlign = TextAlign.Center,)
        }
    }) { paddingValues ->
        Box(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding(), top = paddingValues.calculateTopPadding())
        ) {
            MainContent(navController = navController)
        }
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies(),
    ) {
    Surface() {
        Column(modifier = Modifier.padding(12.dp)) {
            LazyColumn {
                items(items = movieList) {
                    MovieRow(movie = it) { movie ->
                        navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
                    }
                }
            }
        }
    }
}

package kap.movies.kapmovies.scrapperApi

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kap.movies.kapmovies.models.MovieItemDataclass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScrapperViewModel : ViewModel() {

    private val _arrayOfElements = mutableStateListOf<MovieItemDataclass>()
    val arrayOfElements : List<MovieItemDataclass> get() = _arrayOfElements

    fun getImageFrom() {
        viewModelScope.launch(Dispatchers.IO) {
            val apiInterface = ApiInterface.getWebElements()
            run {
                for ((index, element) in apiInterface.withIndex()) {
                    val imageUrl = element.getElementsByClass("slide")
                        .attr("style")
                        .dropWhile { it != 'h' }
                        .dropLast(1)
                    val titleUrl = element.getElementsByClass("screen-reader-text").text()
                    if (imageUrl.isEmpty()) continue
                    val movieItemDataclass =
                        MovieItemDataclass(imageUrl = imageUrl, title = titleUrl)
                    _arrayOfElements.add(movieItemDataclass)
                }
            }
        }
    }
}
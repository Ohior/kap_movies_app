package kap.movies.kapmovies.scrapper

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.ComposableLambda
import kap.movies.kapmovies.models.MovieItemDataclass
import kap.movies.kapmovies.utils.Tools
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements


interface WebScrapper{

companion object {
    fun getWebElements(lambda: (Elements) -> Unit) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect("https://www.my9jarocks.wf/tag/action").get()
            val allInfo = doc.getElementsByClass("container-wrapper")
            lambda(allInfo)
        }
    }

    fun getTagElements(elements: Elements): Array<String> {
        val arrayOfElements: ArrayList<MovieItemDataclass> = ArrayList()
        run {
            for ((index, element) in elements.withIndex()) {
                val imageUrl = element.getElementsByClass("slide")
                    .attr("style")
                    .dropWhile { it != 'h' }
                    .dropLast(1)
                val titleUrl = element.getElementsByClass("screen-reader-text").text()
                if (imageUrl.isEmpty()) continue
                val movieItemDataclass = MovieItemDataclass(imageUrl = imageUrl, title = titleUrl)
                arrayOfElements.add(movieItemDataclass)
                Tools.debugMessage(movieItemDataclass.toString())
            }
        }
        return emptyArray<String>()
}
    }
}

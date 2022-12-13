package kap.movies.kapmovies.scrapperApi

import org.jsoup.Jsoup
import org.jsoup.select.Elements

interface ApiInterface {

    suspend fun getImageFrom(elements: Elements):Array<String>

    suspend fun getTitleFrom(elements: Elements):Array<String>

    companion object{
        fun getWebElements(): Elements {
            val doc = Jsoup.connect("https://www.my9jarocks.wf/tag/action").get()
            return doc.getElementsByClass("container-wrapper")
        }
    }
}
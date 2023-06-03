package id.co.ukdw.ngayogyakarta.engine

import android.content.Context
import java.util.*
import kotlin.String

class SearchEngine(var ctx : Context) {
    private val corpus: ArrayList<String> = ArrayList()

//    init {
//        loadCorpus()
//    }
//
//    fun loadCorpus() {
//        val fileList = ctx.assets.list("corpus")
//        for (file in fileList!!) {
//            val content = ctx.assets.open(file).toString()
//            corpus.add(content)
//        }
//    }
//
//    private fun preprocess(content: String): String {
//        val stopword = StopWordRemoverFactory(ctx).create()
//        val stemmer = StemmerFactory(ctx).create()
//
//        var result = content.lowercase().trim()
//        result = result.replace("[\\W\\s(0-9)]+".toRegex(), " ")
//        result = stopword.remove(result)
//        result = stemmer.stem(result)
//
//        return result
//    }
//
//    fun searchDocument(keyword: String): NDArray<Float,D2> {
//        val tfIdfVectorizer = TfIdfVectorizer<Number>()
//        val corpusPreprocessed = corpus.map { preprocess(it) }
//        val corpusVectorized = tfIdfVectorizer.fitTransform(listOf(corpusPreprocessed))
//        val keywordPreprocessed = preprocess(keyword)
//        val keywordVectorized = tfIdfVectorizer.transform(listOf(listOf(keywordPreprocessed)))
//        val dotProduct = mk.linalg.dot(corpusVectorized, keywordVectorized)
//        val corpusNorm = mk.linalg.norm(corpusVectorized)
//        val keywordNorm = mk.linalg.norm(keywordVectorized)
//        val cosineSimilarity = dotProduct / (corpusNorm * keywordNorm)
//        return cosineSimilarity
//    }
}
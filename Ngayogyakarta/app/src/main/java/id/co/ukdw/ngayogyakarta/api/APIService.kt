package id.co.ukdw.ngayogyakarta.api

import id.co.ukdw.ngayogyakarta.api.response.GetAllDocResponse
import id.co.ukdw.ngayogyakarta.api.response.GetDetailDocResponse
import id.co.ukdw.ngayogyakarta.api.response.SearchDocResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("document")
    fun getAllDocument() : Call<GetAllDocResponse>

    @GET("document/{id_document}")
    fun getDetailDocument(@Path("id_document") idDocument : String) : Call<GetDetailDocResponse>

    @GET("search")
    fun searchDocument(@Query("query") query : String) : Call<SearchDocResponse>
}
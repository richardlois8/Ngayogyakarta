package id.co.ukdw.ngayogyakarta.api.response

import com.google.gson.annotations.SerializedName

data class SearchDocResponse(

	@field:SerializedName("data")
	val data: List<SearchItem>,

	@field:SerializedName("error")
	val error: String,

	@field:SerializedName("message")
	val message: String
)

data class SearchItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("cos_sim")
	val cosSim: Double,

	@field:SerializedName("title")
	val title: String
)

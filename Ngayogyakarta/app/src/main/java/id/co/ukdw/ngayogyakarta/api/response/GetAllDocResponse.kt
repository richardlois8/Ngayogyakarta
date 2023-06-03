package id.co.ukdw.ngayogyakarta.api.response

import com.google.gson.annotations.SerializedName

data class GetAllDocResponse(

	@field:SerializedName("data")
	val data: List<DetailDocument>,

	@field:SerializedName("error")
	val error: String,

	@field:SerializedName("message")
	val message: String
)

data class DetailDocument(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("content")
	val content: String
)

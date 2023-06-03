package id.co.ukdw.ngayogyakarta.api.response

import com.google.gson.annotations.SerializedName

data class GetDetailDocResponse(

	@field:SerializedName("data")
	val data: DetailDocument,

	@field:SerializedName("error")
	val error: String,

	@field:SerializedName("message")
	val message: String
)


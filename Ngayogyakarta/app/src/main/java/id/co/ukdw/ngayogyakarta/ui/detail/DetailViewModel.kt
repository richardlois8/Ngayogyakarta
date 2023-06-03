package id.co.ukdw.ngayogyakarta.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.co.ukdw.ngayogyakarta.api.APIConfig
import id.co.ukdw.ngayogyakarta.api.response.DetailDocument
import id.co.ukdw.ngayogyakarta.api.response.GetDetailDocResponse

class DetailViewModel : ViewModel() {
    private val _detailDocument : MutableLiveData<DetailDocument?> = MutableLiveData()
    val detailDocumentLiveData : LiveData<DetailDocument?> = _detailDocument

    fun getDetailDocument(query : String){
        val client = APIConfig.getApiServie().getDetailDocument(query)
        client.enqueue(object : retrofit2.Callback<GetDetailDocResponse>{
            override fun onResponse(
                call: retrofit2.Call<GetDetailDocResponse>,
                response: retrofit2.Response<GetDetailDocResponse>
            ) {
                if (response.code() == 200 && response.isSuccessful){
                    val body = response.body()
                    _detailDocument.value = body?.data
                }else{
                    _detailDocument.value = null
                }
            }

            override fun onFailure(call: retrofit2.Call<GetDetailDocResponse>, t: Throwable) {
                _detailDocument.value = null
            }
        })
    }
}
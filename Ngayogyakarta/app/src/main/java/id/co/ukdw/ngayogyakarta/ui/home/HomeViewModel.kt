package id.co.ukdw.ngayogyakarta.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.co.ukdw.ngayogyakarta.api.APIConfig
import id.co.ukdw.ngayogyakarta.api.response.DetailDocument
import id.co.ukdw.ngayogyakarta.api.response.GetAllDocResponse
import id.co.ukdw.ngayogyakarta.api.response.SearchDocResponse
import id.co.ukdw.ngayogyakarta.api.response.SearchItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel(){
    private val _allDocumentLiveData: MutableLiveData<List<DetailDocument>?> = MutableLiveData()
    val allDocumentLiveData : LiveData<List<DetailDocument>?> = _allDocumentLiveData

    private val _searchDocumentLiveData: MutableLiveData<List<SearchItem>?> = MutableLiveData()
    val searchResultLiveData : LiveData<List<SearchItem>?> = _searchDocumentLiveData

    fun getAllDocument(){
        val client = APIConfig.getApiServie().getAllDocument()
        client.enqueue(object : Callback<GetAllDocResponse> {
            override fun onResponse(
                call: Call<GetAllDocResponse>,
                response: Response<GetAllDocResponse>
            ) {
                if (response.code() == 200 && response.isSuccessful){
                    val body = response.body()
                    _allDocumentLiveData.postValue(body?.data)
                } else{
                    _allDocumentLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<GetAllDocResponse>, t: Throwable) {
                _allDocumentLiveData.postValue(null)
            }

        })
    }

    fun searchDocument(query : String){
        val client = APIConfig.getApiServie().searchDocument(query)
        client.enqueue(object : Callback<SearchDocResponse> {
            override fun onResponse(
                call: Call<SearchDocResponse>,
                response: Response<SearchDocResponse>
            ) {
                if (response.code() == 200 && response.isSuccessful){
                    val body = response.body()
                    _searchDocumentLiveData.postValue(body?.data)
                } else{
                    _searchDocumentLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<SearchDocResponse>, t: Throwable) {
                _searchDocumentLiveData.postValue(null)
            }

        })
    }
}
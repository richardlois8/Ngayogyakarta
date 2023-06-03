package id.co.ukdw.ngayogyakarta.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.ukdw.ngayogyakarta.api.response.SearchItem
import id.co.ukdw.ngayogyakarta.databinding.ItemSearchResultBinding
import id.co.ukdw.ngayogyakarta.model.Article
import id.co.ukdw.ngayogyakarta.ui.RecyclerViewClickListener

class SearchAdapter(var listDoc : List<SearchItem>, private val listener: RecyclerViewClickListener) : RecyclerView.Adapter<SearchAdapter.viewHolder>() {

    class viewHolder(var binding : ItemSearchResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data : SearchItem, listener: RecyclerViewClickListener){
            binding.data = data
            Glide.with(itemView.context).load(data.image).into(binding.imgDoc)
            binding.cvDocument.setOnClickListener{
                listener.onItemClicked(data.title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val v = ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(v)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bindData(listDoc[position], listener)
    }

    override fun getItemCount(): Int = listDoc.size
}
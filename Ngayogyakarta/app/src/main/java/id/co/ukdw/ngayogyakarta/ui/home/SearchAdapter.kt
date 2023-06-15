package id.co.ukdw.ngayogyakarta.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import id.co.ukdw.ngayogyakarta.api.response.SearchItem
import id.co.ukdw.ngayogyakarta.databinding.ItemSearchResultBinding
import id.co.ukdw.ngayogyakarta.model.Article
import id.co.ukdw.ngayogyakarta.ui.RecyclerViewClickListener

class SearchAdapter(var listDoc : List<SearchItem>, private val listener: RecyclerViewClickListener) : RecyclerView.Adapter<SearchAdapter.viewHolder>() {

    class viewHolder(var binding : ItemSearchResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data : SearchItem, listener: RecyclerViewClickListener){
            binding.data = data

            val shimmer = Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1500) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.7f) //the alpha of the underlying children
                .setHighlightAlpha(0.6f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()
            val shimmerDrawable = ShimmerDrawable().apply {
                setShimmer(shimmer)
            }


            Glide.with(itemView.context)
                .load(data.image)
                .placeholder(shimmerDrawable)
                .into(binding.imgDoc)

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
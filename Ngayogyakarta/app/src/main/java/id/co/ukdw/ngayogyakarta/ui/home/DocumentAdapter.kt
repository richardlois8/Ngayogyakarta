package id.co.ukdw.ngayogyakarta.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import id.co.ukdw.ngayogyakarta.api.response.DetailDocument
import id.co.ukdw.ngayogyakarta.databinding.ItemDocumentBinding
import id.co.ukdw.ngayogyakarta.model.Article
import id.co.ukdw.ngayogyakarta.ui.RecyclerViewClickListener

class DocumentAdapter(var listDoc : List<DetailDocument>, private val listener: RecyclerViewClickListener) : RecyclerView.Adapter<DocumentAdapter.viewHolder>() {
    var onClick : ((Article) -> Unit)? = null

    class viewHolder(var binding : ItemDocumentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data : DetailDocument, listener: RecyclerViewClickListener){
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
        val v = ItemDocumentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(v)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bindData(listDoc[position], listener)
    }

    override fun getItemCount(): Int = listDoc.size
}
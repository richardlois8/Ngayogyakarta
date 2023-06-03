package id.co.ukdw.ngayogyakarta.ui.detail

import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import id.co.ukdw.ngayogyakarta.R
import id.co.ukdw.ngayogyakarta.databinding.FragmentDetailDocumentBinding
import java.text.SimpleDateFormat
import java.util.*


class DocumentDetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailDocumentBinding
    private lateinit var detailVM : DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailDocumentBinding.inflate(inflater, container, false)
        detailVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.toolbarDetail.btnHome.setOnClickListener {
            findNavController().navigate(R.id.action_global_homeFragment)
        }
    }

    fun getData(){
        val query = arguments?.getString("query")
        detailVM.getDetailDocument(query ?: "")
        detailVM.detailDocumentLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.data = it
                val time = Calendar.getInstance().time
                val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("in", "ID"))
                val currDate = formatter.format(time)
                binding.txtDate.text = currDate.toString()
                Glide.with(this)
                    .load(it.image)
                    .into(binding.imgDoc)
                val typeFace = Typeface.createFromAsset(resources.assets, "font/noto_javanese.ttf")
                binding.txtContent.typeface = typeFace
                binding.txtContent.text = it.content
            }
        }
    }
}
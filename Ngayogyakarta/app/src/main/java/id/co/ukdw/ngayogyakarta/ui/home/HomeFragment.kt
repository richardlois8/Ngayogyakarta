package id.co.ukdw.ngayogyakarta.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.ukdw.ngayogyakarta.R
import id.co.ukdw.ngayogyakarta.databinding.FragmentHomeBinding
import id.co.ukdw.ngayogyakarta.ui.RecyclerViewClickListener


class HomeFragment : Fragment(), RecyclerViewClickListener {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeVM : HomeViewModel
    private lateinit var query : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        setListener()
    }

    private fun initData(){
        showLoading(true)
        homeVM.getAllDocument()
        homeVM.allDocumentLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = DocumentAdapter(it, this)
                binding.recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.recView.adapter = adapter
                showLoading(false)
            }
        }
        query = ""
        binding.etSearchBar.setText(query)
    }

    private fun searchQuery(query: String){
        homeVM.searchDocument(query)
        homeVM.searchResultLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = SearchAdapter(it, this)
                binding.recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.recView.adapter = adapter
                showLoading(false)
            }
        }
    }

    private fun setListener(){
        binding.etSearchBar.setOnEditorActionListener{ _, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    query = binding.etSearchBar.text.toString()
                    searchQuery(query)
                    showLoading(true)
                    true
                }
                else -> false
            }
        }
    }

    override fun onItemClicked(title: String) {
        val bundle = Bundle()
        bundle.putString("documentTitle", title)
        bundle.putString("query", query)
        findNavController().navigate(R.id.action_homeFragment_to_documentDetailFragment, bundle)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.loadingHome.visibility = View.VISIBLE
        } else {
            binding.loadingHome.visibility = View.GONE
        }
    }
}
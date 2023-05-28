package com.rimapps.shoppingapp.features.productList.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rimapps.shoppingapp.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListViewModel by viewModels()
    private val adapter = ProductCardListAdapter(::onItemClicked,
        ::onFavouriteIconClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews()
        observeData()
        viewModel.loadProductList()
    }

    private fun observeData() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            updateUI(viewState)
        }
    }

    private fun setViews() {
        binding.viewProductList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.viewProductList.adapter = adapter
    }

    private fun updateUI(viewState: ProductListViewState) {
        when(viewState){
            is ProductListViewState.Content -> {
                binding.viewProductList.isVisible = true
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                adapter.setData(viewState.productList)

            }

            ProductListViewState.Error -> {
                binding.viewProductList.isVisible = false
                binding.errorView.isVisible = true
                binding.loadingView.isVisible = false
            }

            ProductListViewState.Loading -> {
                binding.viewProductList.isVisible = false
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = true
            }
        }
    }


    // parameter just to show how to retrieve data from Adapter to the fragment
    private fun onItemClicked(viewState: ProductCardViewState) {
        findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment())

    }
    private fun onFavouriteIconClicked(productCardViewState: ProductCardViewState) {
        viewModel.favouriteIconClicked(productCardViewState.id)

        Toast.makeText(requireContext(),productCardViewState.isFavourite.toString(),Toast.LENGTH_SHORT).show()
    }
}
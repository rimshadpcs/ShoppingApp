package com.rimapps.shoppingapp.features.productDetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.rimapps.shoppingapp.R
import com.rimapps.shoppingapp.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadProduct("someProductId")
        collectFromViewModel()
    }

    private fun collectFromViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED
            )
                .collect {
                    updateUi(it)
                }
        }
    }

    private fun updateUi(viewState: ProductDetailsViewState) {
        when (viewState) {
            is ProductDetailsViewState.Content -> {
                with(binding) {
                    binding.loadingView.isVisible = false
                    val product = viewState.product
                    viewProductTitle.text = product.title
                    Glide.with(requireContext()).load(product.imageUrl)
                        .into(viewProductImage)
                    binding.viewPrice.text = product.price
                    binding.viewFullDescription.text = product.fullDescription
                }
            }

            ProductDetailsViewState.Error -> {
                binding.loadingView.isVisible = false
            }

            ProductDetailsViewState.Loading -> {
                binding.loadingView.isVisible = true
            }
        }
    }
}
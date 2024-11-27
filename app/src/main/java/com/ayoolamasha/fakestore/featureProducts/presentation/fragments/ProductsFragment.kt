package com.ayoolamasha.fakestore.featureProducts.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayoolamasha.fakestore.databinding.FragmentProductsBinding
import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData
import com.ayoolamasha.fakestore.featureProducts.presentation.ProductViewModel
import com.ayoolamasha.fakestore.featureProducts.presentation.ProductsUIState
import com.ayoolamasha.fakestore.featureProducts.presentation.adapter.ProductsAdapter
import com.ayoolamasha.fakestore.featureProducts.presentation.viewmodel.ProductSharedViewModel
import com.ayoolamasha.statusBarColorBlue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment(){
    private lateinit var binding: FragmentProductsBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val productSharedViewModel: ProductSharedViewModel by activityViewModels()
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().moveTaskToBack(true)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusBarColorBlue()

        /**
         * GET PRODUCT STATE
         */
        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.getProductState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { getProductState(it) }
        }

        binding.showError.retryButton.setOnClickListener{
            productViewModel.makeGetProductsCall()
        }
    }

    private fun getProductState(productsUIState: ProductsUIState) {
        if (productsUIState.isLoading) {
            binding.showProgress.isVisible = true
            binding.isError = false
        } else if (productsUIState.isError) {
            binding.showProgress.isVisible = false
            binding.isError = true
            binding.showError.errorMsg.text = productsUIState.error
        } else if (productsUIState.isSuccess) {
            binding.showProgress.isVisible = false
            binding.productRecyler.isVisible = true
            initRecycler()
            productsAdapter.submitList(productsUIState.productUIData)

        }
    }

    private fun initRecycler(){
        productsAdapter = ProductsAdapter{
            navigateToProductDetails(it)
        }
        binding.productRecyler.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = productsAdapter
        }
    }

    private fun navigateToProductDetails(productsUiData: ProductsUiData){
        val productsDetails = ProductsUiData(
            productId = productsUiData.productId,
         productTitle= productsUiData.productTitle,
         productDescription= productsUiData.productDescription,
        productPrice= productsUiData.productPrice,
         productCategory= productsUiData.productCategory,
        productRating= productsUiData.productRating,
         productCount= productsUiData.productCount,
         productImage= productsUiData.productImage,
        )

        productSharedViewModel.productsUiData = productsDetails

        val action = ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment()
        findNavController().navigate(action)
    }
}
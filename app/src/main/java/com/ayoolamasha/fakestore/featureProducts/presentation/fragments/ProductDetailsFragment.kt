package com.ayoolamasha.fakestore.featureProducts.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.ayoolamasha.fakestore.databinding.FragmentProductDetailsBinding
import com.ayoolamasha.fakestore.featureProducts.presentation.ProductViewModel
import com.ayoolamasha.fakestore.featureProducts.presentation.ProductsUIState
import com.ayoolamasha.fakestore.featureProducts.presentation.viewmodel.ProductSharedViewModel
import com.ayoolamasha.statusBarColorBlue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment : Fragment(){
    private lateinit var binding: FragmentProductDetailsBinding
    private val productSharedViewModel: ProductSharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusBarColorBlue()
        binding.productUIData = productSharedViewModel.productsUiData

        binding.backArrow.setOnClickListener {
            binding.root.findNavController().navigateUp()
        }
    }

}
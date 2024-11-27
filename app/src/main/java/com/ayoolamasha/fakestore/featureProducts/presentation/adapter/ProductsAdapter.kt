package com.ayoolamasha.fakestore.featureProducts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayoolamasha.fakestore.databinding.ItemProductsRecyclerDesignBinding
import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData

class ProductsAdapter (private var onProductClick: (ProductsUiData) -> Unit) :
    ListAdapter<ProductsUiData, ProductsAdapter.ProductViewHolder>(ProductDiffCallBack) {

    inner class ProductViewHolder(private val binding: ItemProductsRecyclerDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binds(items: ProductsUiData) {
            binding.apply {
                productUIData = items
                executePendingBindings()
                setClickListener { onProductClick(items) }

            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            ItemProductsRecyclerDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val reportUICase = getItem(position)
        holder.binds(reportUICase)
    }

    object ProductDiffCallBack : DiffUtil.ItemCallback<ProductsUiData>() {
        override fun areItemsTheSame(
            oldItem: ProductsUiData,
            newItem: ProductsUiData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductsUiData,
            newItem: ProductsUiData
        ): Boolean {
            return false
        }

    }


}
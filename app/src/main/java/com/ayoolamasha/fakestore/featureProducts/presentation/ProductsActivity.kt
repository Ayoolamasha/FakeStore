package com.ayoolamasha.fakestore.featureProducts.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayoolamasha.fakestore.databinding.ActivityProductsNavHolderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProductsNavHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
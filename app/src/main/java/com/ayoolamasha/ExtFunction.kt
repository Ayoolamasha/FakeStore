package com.ayoolamasha

import android.view.WindowManager
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.ayoolamasha.fakestore.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("app:loadImage")
fun loadImage(image: ImageView?, imageString: String?) {
    if (image != null) {
        Glide.with(image.context)
            .load(imageString)
            .centerCrop()
            .into(image)
    }
}

fun Fragment.statusBarColorBlue() {
    val window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue)
}
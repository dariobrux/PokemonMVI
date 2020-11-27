package com.dariobrux.pokemon.common

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dariobrux.pokemon.R

fun ImageView.loadImage(context: Context, url: String, onDominantColor: (Int) -> Unit) {
    Glide.with(context).asBitmap().load(url).diskCacheStrategy(DiskCacheStrategy.ALL).listener(object : RequestListener<Bitmap> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
            return true
        }

        override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            resource ?: return true
            resource.getDominantColor(ContextCompat.getColor(context, R.color.white)) { color ->
                onDominantColor.invoke(color)
            }
            return false
        }
    }).into(this)
}
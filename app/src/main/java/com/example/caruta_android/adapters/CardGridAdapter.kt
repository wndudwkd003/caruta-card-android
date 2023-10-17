package com.example.caruta_android.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.caruta_android.classes.CarutaCard
import com.example.caruta_android.databinding.CarutaCardItemBinding

class CardGridAdapter(private val selectCards: MutableList<CarutaCard>) : RecyclerView.Adapter<CardGridAdapter.ViewHolder>() {

    init {
        selectCards.shuffle()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardGridAdapter.ViewHolder {
        val binding = CarutaCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = selectCards.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = selectCards[position]
        holder.binding(item)
    }

    inner class ViewHolder(private val viewBinding: CarutaCardItemBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun binding(item: CarutaCard) {
            if (item.source.startsWith("#") && item.source.length in 7..9) {
                val colorDrawable = ColorDrawable(Color.parseColor(item.source))
                Glide.with(viewBinding.root)
                    .load(colorDrawable)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
                    .into(viewBinding.imageView)
            } else {
                Glide.with(viewBinding.root)
                    .load(item.source)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
                    .into(viewBinding.imageView)
            }
            viewBinding.textView.gravity = Gravity.CENTER
            viewBinding.textView.text = item.id

        }
    }
}



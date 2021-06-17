package com.beatrice.bookapp.catalogue.ui.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beatrice.bookapp.databinding.BookItemLayoutBinding

class BookViewHolder(binding: BookItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    var titleTextView: TextView = binding.titleTextView
    var authorTextView: TextView = binding.authorTextView

}
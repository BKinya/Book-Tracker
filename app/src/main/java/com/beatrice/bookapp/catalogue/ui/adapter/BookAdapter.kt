package com.beatrice.bookapp.catalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.databinding.BookItemLayoutBinding

class BookAdapter(private val booksDataSet: List<Book>) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.titleTextView.text = booksDataSet[position].title
        holder.authorTextView.text = booksDataSet[position].author.toString()
    }

    override fun getItemCount(): Int {
        return booksDataSet.size
    }
}

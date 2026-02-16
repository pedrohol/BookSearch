package com.example.livros.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.livros.R
import com.example.livros.databinding.FragmentSearchBooksBinding
import com.example.livros.databinding.ItemBookCoverBinding
import com.example.livros.model.Authors
import com.example.livros.model.Books
import com.example.livros.model.SearchBooks
import com.example.livros.repository.BooksRepository
import com.example.livros.retrofit.BooksService
import com.example.livros.util.Constants
import com.example.livros.view.BookFragment
import com.example.livros.view.SearchBookFragment

class SearchBookAdapter(private val onClick: (Books) -> Unit) : RecyclerView.Adapter<SearchBookAdapter.SearchViewHolder>() {

    private var listBook = mutableListOf<Books>()
    fun setBooksList(books: List<List<Books>>?) {

        val finalList = mutableListOf<Books>()

        if (books != null) {
            for (i in books) {
                for (j in i) {
                    finalList.add(j)
                }
            }
        }

        this.listBook = finalList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val binding = ItemBookCoverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SearchViewHolder,
        position: Int
    ) {
        val book = listBook[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return listBook.size
    }

    inner class SearchViewHolder(val binding: ItemBookCoverBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Books) {

            binding.itemBookTitle.text = book.title

            val  requestOptions = RequestOptions()
                .placeholder(R.drawable.placeholder_book)
                .error(R.drawable.placeholder_book)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(book.image)
                .into(binding.itemBookCover)

            itemView.setOnClickListener {
                onClick(book)
            }

        }

    }
}




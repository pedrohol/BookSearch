package com.example.livros.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.livros.R
import com.example.livros.databinding.FragmentSearchBooksBinding
import com.example.livros.databinding.ItemBookCoverBinding
import com.example.livros.model.Authors
import com.example.livros.model.Books
import com.example.livros.repository.BooksRepository
import com.example.livros.view.BookFragment

class SearchBookAdapter(private val onClick: (Long) -> Unit) : RecyclerView.Adapter<SearchBookAdapter.SearchViewHolder>() {

    private val listBook = BooksRepository().booksList

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

    inner class SearchViewHolder(val binding: ItemBookCoverBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(book: Books) {

            binding.itemBookTitle.text = book.title
            binding.itemBookCover.setImageResource(book.image)

            binding.itemBookCover.setOnClickListener {
                onClick.invoke(book.id)
            }
        }
    }
}


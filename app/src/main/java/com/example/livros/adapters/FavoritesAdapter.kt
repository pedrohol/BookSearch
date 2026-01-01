package com.example.livros.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.livros.databinding.ItemFavoriteBookBinding
import com.example.livros.model.Books
import com.example.livros.repository.BooksRepository

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private val favoriteList = BooksRepository().booksList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesViewHolder {
        val binding = ItemFavoriteBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FavoritesViewHolder,
        position: Int
    ) {
        val favorites = favoriteList[position]
        holder.bind(favorites)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    inner class FavoritesViewHolder(val binding: ItemFavoriteBookBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(favorites: Books) {
            binding.itemFavoriteTitle.text = favorites.title
            binding.itemFavoriteAuthorName.text = favorites.authors[0].name
            binding.itemFavoriteBookCover.setImageResource(favorites.image)
        }
    }
}
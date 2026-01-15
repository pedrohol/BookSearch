package com.example.livros.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.livros.databinding.ItemFavoriteBookBinding
import com.example.livros.model.Books
import com.example.livros.repository.BooksRepository
import com.example.livros.room.FavoriteEntity

class FavoritesAdapter(private val onClick: (FavoriteEntity) -> Unit): RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {


    var favoriteList = listOf<FavoriteEntity>()

    fun getFavorites(favorites: List<FavoriteEntity>) {
        this.favoriteList = favorites
        notifyDataSetChanged()
    }

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
        fun bind(favorites: FavoriteEntity) {
            binding.itemFavoriteTitle.text = favorites.title
            binding.itemFavoriteAuthorName.text = favorites.author

            val  requestOptions = RequestOptions()
                .placeholder(com.example.livros.R.drawable.placeholder_book)
                .error(com.example.livros.R.drawable.placeholder_book)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(favorites.image)
                .into(binding.itemFavoriteBookCover)

            itemView.setOnLongClickListener {
                onClick(favorites)
                true
            }

        }
    }
}
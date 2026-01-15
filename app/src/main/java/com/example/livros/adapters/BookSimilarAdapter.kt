package com.example.livros.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.livros.databinding.FragmentBookBinding
import com.example.livros.databinding.ItemBookSimilarCoverBinding
import com.example.livros.model.BookSimilar
import com.example.livros.model.SimilarData

class BookSimilarAdapter(private val onClick: (SimilarData) -> Unit) : RecyclerView.Adapter<BookSimilarAdapter.SimilarViewHolder>() {

    private var similarList = mutableListOf<SimilarData>()

    fun setSimilarList(similarBooks: List<SimilarData>) {
        this.similarList = similarBooks.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val binding = ItemBookSimilarCoverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        val similar = similarList[position]
        holder.bind(similar)
    }

    override fun getItemCount(): Int {
        return similarList.size
    }

    inner class SimilarViewHolder(val binding: ItemBookSimilarCoverBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (similar: SimilarData) {

            val  requestOptions = RequestOptions()
                .placeholder(com.example.livros.R.drawable.placeholder_book)
                .error(com.example.livros.R.drawable.placeholder_book)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(similar.image)
                .into(binding.itemSimilarBookCover)

            itemView.setOnClickListener {
                onClick(similar)
            }

        }

    }
}


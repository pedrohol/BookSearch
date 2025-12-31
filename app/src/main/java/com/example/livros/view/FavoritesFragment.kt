package com.example.livros.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.livros.R
import com.example.livros.adapters.FavoritesAdapter
import com.example.livros.databinding.FragmentFavoritesBinding

class FavoritesFragment: Fragment(R.layout.fragment_favorites) {

    private lateinit var binding: FragmentFavoritesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoritesBinding.bind(view)

        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritesRecyclerView.adapter = FavoritesAdapter()
    }
}
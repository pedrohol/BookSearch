package com.example.livros.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.InvalidationTracker
import androidx.transition.Visibility
import com.example.livros.R
import com.example.livros.adapters.FavoritesAdapter
import com.example.livros.databinding.FragmentFavoritesBinding
import com.example.livros.repository.FavoritesRepository
import com.example.livros.room.FavoriteDao
import com.example.livros.room.FavoriteDatabase
import com.example.livros.viewmodel.Factory.FavoritesViewModelFactory
import com.example.livros.viewmodel.FavoritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment: Fragment(R.layout.fragment_favorites) {

    private lateinit var binding: FragmentFavoritesBinding

    private val favoriteViewModel: FavoritesViewModel by viewModel()

    private val adapter = FavoritesAdapter() {
        favoriteViewModel.removeFavorite(it)
        Toast.makeText(requireContext(), "Favorito Removido", Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = FavoriteDatabase.getDataBase(requireContext())
        val dao = database.favoriteDao()
        val repository = FavoritesRepository(dao)

//        favoriteViewModel = ViewModelProvider(this, FavoritesViewModelFactory(repository)).get(FavoritesViewModel::class.java)

        binding = FragmentFavoritesBinding.bind(view)

        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritesRecyclerView.adapter = adapter

        favoriteViewModel.allFavorites.observe(viewLifecycleOwner, Observer{ favorites ->

            if(favorites.isNullOrEmpty()){
                binding.favoritesVazio.visibility = View.VISIBLE
                binding.favoritesRecyclerView.visibility = View.GONE

            } else {
                binding.favoritesVazio.visibility = View.GONE
                binding.favoritesRecyclerView.visibility = View.VISIBLE

                adapter.getFavorites(favorites)

            }
        })

    }
}
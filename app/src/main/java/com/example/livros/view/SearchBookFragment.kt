package com.example.livros.view

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.livros.R
import com.example.livros.adapters.SearchBookAdapter
import com.example.livros.databinding.FragmentSearchBooksBinding
import com.example.livros.databinding.ItemBookCoverBinding
import com.example.livros.model.Authors
import com.example.livros.model.Books
import java.util.zip.Inflater
import androidx.navigation.findNavController

class SearchBookFragment: Fragment(R.layout.fragment_search_books) {

    private lateinit var binding: FragmentSearchBooksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBooksBinding.bind(view)


        binding.searchRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        binding.searchRecyclerView.adapter = SearchBookAdapter{
            view.findNavController().navigate(R.id.bookFragment)
        }

    }

}
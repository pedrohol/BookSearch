package com.example.livros.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.livros.R
import com.example.livros.databinding.FragmentSearchBooksBinding

class SearchBookFragment: Fragment(R.layout.fragment_search_books) {

    private lateinit var binding: FragmentSearchBooksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBooksBinding.bind(view)
    }

}
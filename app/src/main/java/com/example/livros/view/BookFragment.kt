package com.example.livros.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.livros.R
import com.example.livros.databinding.FragmentBookBinding
import com.example.livros.model.Books
import com.example.livros.repository.BooksRepository

class BookFragment: Fragment(R.layout.fragment_book) {

    private lateinit var binding: FragmentBookBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookBinding.bind(view)
    }
}
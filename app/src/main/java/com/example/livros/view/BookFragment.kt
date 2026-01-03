package com.example.livros.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.livros.R
import com.example.livros.databinding.FragmentBookBinding
import com.example.livros.model.Books
import com.example.livros.repository.BooksRepository
import com.example.livros.retrofit.BooksService
import com.example.livros.util.Constants
import com.example.livros.viewmodel.BooksInformationViewModel
import com.example.livros.viewmodel.BooksInformationViewModelFactory
import com.example.livros.viewmodel.BooksViewModelFactory

class BookFragment: Fragment(R.layout.fragment_book) {

    private lateinit var binding: FragmentBookBinding

    private val retrofitService = BooksService.getInstance()

    lateinit var viewModel : BooksInformationViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookBinding.bind(view)

        viewModel = ViewModelProvider(this, BooksInformationViewModelFactory(BooksRepository(retrofitService))).get(
            BooksInformationViewModel::class.java
        )
    }

    override fun onStart() {
        super.onStart()

        viewModel.bookInformation.observe(this, Observer{ book ->
            binding.bookTitle.text = book.title
            binding.bookAuthor.text = book.authors[0].name
            binding.bookDescription.text = book.description

        })
    }

}
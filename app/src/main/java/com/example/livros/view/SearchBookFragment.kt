package com.example.livros.view

import android.content.ClipData
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
import com.example.livros.repository.SearchBookRepository
import com.example.livros.retrofit.BooksService
import com.example.livros.viewmodel.SearchBookViewModel
import com.example.livros.viewmodel.SearchBookViewModelFactory

class SearchBookFragment: Fragment(R.layout.fragment_search_books) {

    private lateinit var binding: FragmentSearchBooksBinding

    private val retrofitService = BooksService.getInstance()

    private val adapter = SearchBookAdapter {
        view?.findNavController()?.navigate(R.id.bookFragment)
    }

    lateinit var viewModel: SearchBookViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBooksBinding.bind(view)

        binding.searchRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        binding.searchRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, SearchBookViewModelFactory(SearchBookRepository(retrofitService))).get(
            SearchBookViewModel::class.java
        )

    }

    override fun onStart() {
        super.onStart()

        viewModel.booksList.observe(this, Observer { books ->
            adapter.setBooksList(books.books)
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        Log.i("Erro", message)
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllBooks()
    }

}
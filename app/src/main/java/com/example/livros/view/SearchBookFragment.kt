package com.example.livros.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.livros.R
import com.example.livros.adapters.SearchBookAdapter
import com.example.livros.databinding.FragmentSearchBooksBinding
import androidx.navigation.findNavController
import com.example.livros.repository.BooksRepository
import com.example.livros.retrofit.BooksService
import com.example.livros.viewmodel.SearchBookViewModel
import com.example.livros.viewmodel.Factory.BooksViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchBookFragment: Fragment(R.layout.fragment_search_books) {

    private lateinit var binding: FragmentSearchBooksBinding

    //private val retrofitService = BooksService.getInstance()

    private val adapter = SearchBookAdapter { book ->
        val bookFragment = SearchBookFragmentDirections.actionMenuBooksToBookFragment(book.id)
        view?.findNavController()?.navigate(bookFragment)
    }

    private val viewModel: SearchBookViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBooksBinding.bind(view)

        binding.searchRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.searchRecyclerView.adapter = adapter

        binding.searchSearchText.setOnEditorActionListener { v, actionId, event ->

            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchSearchText.text.toString()

                if (query.isNotEmpty()) {
                    viewModel.searchBooks(query)
                }
                true
            } else {
                true
            }

        }

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
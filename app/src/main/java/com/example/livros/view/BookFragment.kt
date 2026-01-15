package com.example.livros.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.livros.R
import com.example.livros.adapters.BookSimilarAdapter
import com.example.livros.databinding.FragmentBookBinding
import com.example.livros.repository.BooksRepository
import com.example.livros.repository.FavoritesRepository
import com.example.livros.retrofit.BooksService
import com.example.livros.room.FavoriteDatabase
import com.example.livros.room.FavoriteEntity
import com.example.livros.viewmodel.BooksInformationViewModel
import com.example.livros.viewmodel.BooksSimilarViewModel
import com.example.livros.viewmodel.Factory.BooksInformationViewModelFactory
import com.example.livros.viewmodel.Factory.BooksSimilarFactory
import com.example.livros.viewmodel.Factory.FavoritesViewModelFactory
import com.example.livros.viewmodel.FavoritesViewModel

class BookFragment: Fragment(R.layout.fragment_book) {

    private lateinit var binding: FragmentBookBinding

    private val retrofitService = BooksService.getInstance()

    private val adapter = BookSimilarAdapter { bookSimilar ->
        val action = BookFragmentDirections.actionBookFragmentSelf(bookSimilar.id)
        view?.findNavController()?.navigate(action)
    }

    lateinit var viewModel : BooksInformationViewModel
    lateinit var favoriteViewModel: FavoritesViewModel

    lateinit var similarViewModel: BooksSimilarViewModel

    val args: BookFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookBinding.bind(view)

        binding.bookRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        binding.bookRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, BooksInformationViewModelFactory(BooksRepository(retrofitService))).get(
            BooksInformationViewModel::class.java
        )

        similarViewModel = ViewModelProvider(this, BooksSimilarFactory(BooksRepository(retrofitService))).get(
            BooksSimilarViewModel::class.java
        )

        val database = FavoriteDatabase.getDataBase(requireContext())
        val dao = database.favoriteDao()
        val repository = FavoritesRepository(dao)

        favoriteViewModel = ViewModelProvider(this, FavoritesViewModelFactory(repository)).get(FavoritesViewModel::class.java)

        binding.backArrow.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onStart() {
        super.onStart()

        viewModel.bookInformation.observe(this, Observer { book ->

            val title = book.title
            val author = book.authors[0].name
            val description = book.description
            val image = book.image


            binding.bookTitle.text = title
            binding.bookAuthor.text = author
            binding.bookDescription.text = description

            Glide.with(this)
                .load(image)
                .placeholder(R.drawable.placeholder_book)
                .error(R.drawable.placeholder_book)
                .into(binding.bookCover)

            binding.bookFavorite.setOnClickListener {
                val bookFavorite = FavoriteEntity(0, title, author, image)
                favoriteViewModel.addFavorite(bookFavorite)
                Toast.makeText(requireContext(), "Adicionado aos Favoritos", Toast.LENGTH_LONG).show()
            }

        })

        viewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            Log.i("Erro", message)
        })

        similarViewModel.booksSimilar.observe(this, Observer { similars ->
            adapter.setSimilarList(similars.similarBooks)
        })

        similarViewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            Log.i("Erro", message)
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getBookInformation(args.bookId)
        similarViewModel.getBookSimilars(args.bookId)

    }

}
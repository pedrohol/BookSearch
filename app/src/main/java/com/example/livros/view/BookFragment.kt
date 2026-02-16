package com.example.livros.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.livros.R
import com.example.livros.adapters.BookSimilarAdapter
import com.example.livros.databinding.FragmentBookBinding
import com.example.livros.repository.FavoritesRepository
import com.example.livros.room.FavoriteDatabase
import com.example.livros.room.FavoriteEntity
import com.example.livros.viewmodel.BooksInformationViewModel
import com.example.livros.viewmodel.BooksSimilarViewModel
import com.example.livros.viewmodel.FavoritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookFragment: Fragment(R.layout.fragment_book) {

    private lateinit var binding: FragmentBookBinding

    private val adapter = BookSimilarAdapter { bookSimilar ->
        val action = BookFragmentDirections.actionBookFragmentSelf(bookSimilar.id)
        view?.findNavController()?.navigate(action)
    }

    private val viewModel : BooksInformationViewModel by viewModel()
    private val favoriteViewModel: FavoritesViewModel by viewModel()
    private val similarViewModel: BooksSimilarViewModel by viewModel()

    val args: BookFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookBinding.bind(view)

        binding.bookRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        binding.bookRecyclerView.adapter = adapter

        binding.backArrow.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onStart() {
        super.onStart()

        viewModel.bookInformation.observe(this, Observer { book ->

            if(book != null) {

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
            }
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            Log.i("Erro", message)
        })

        similarViewModel.booksSimilar.observe(this, Observer { similars ->

            if (similars != null) {
                binding.bookSimilars.visibility = View.VISIBLE
                adapter.setSimilarList(similars.similarBooks)
            }

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
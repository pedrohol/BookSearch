package com.example.livros.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.livros.databinding.FragmentBookBinding

class BookFragment: Fragment() {

    private lateinit var binding: FragmentBookBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookBinding.bind(view)
    }
}
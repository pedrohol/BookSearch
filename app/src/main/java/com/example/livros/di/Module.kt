package com.example.livros.di

import androidx.room.Room
import com.example.livros.repository.BooksRepository
import com.example.livros.repository.FavoritesRepository
import com.example.livros.retrofit.BooksService
import com.example.livros.room.FavoriteDatabase
import com.example.livros.util.Constants
import com.example.livros.viewmodel.BooksInformationViewModel
import com.example.livros.viewmodel.BooksSimilarViewModel
import com.example.livros.viewmodel.FavoritesViewModel
import com.example.livros.viewmodel.SearchBookViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {

    viewModel {
        BooksInformationViewModel(get())
    }

    viewModel {
        BooksSimilarViewModel(get())
    }

    viewModel {
        FavoritesViewModel(get())
    }

    viewModel {
        SearchBookViewModel(get())
    }


}

val retrofitModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    single {get<Retrofit>().create(BooksService::class.java)}

    factory { BooksRepository(get()) }

}

val roomModule = module {

    single {
        Room.databaseBuilder(
            get(),
            FavoriteDatabase::class.java,
            "favorites_database"
        ).build()
    }

    single {get<FavoriteDatabase>().favoriteDao()}

    factory { FavoritesRepository(get()) }

}


val moduleList = listOf(
    viewModelModule,
    retrofitModule,
    roomModule
)
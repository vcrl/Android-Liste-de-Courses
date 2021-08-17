package com.example.mvvmshoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmshoppinglist.data.repositories.ShoppingRepository

/*
Class qui permet de donner un paramètre à la classe ShoppingViewModel par l'intérmédiaire d'une
factory (voir dans ShoppingActivity)
 */

class ShoppingViewModelFactory(private val repository: ShoppingRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}
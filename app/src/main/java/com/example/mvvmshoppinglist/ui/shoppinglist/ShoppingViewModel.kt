package com.example.mvvmshoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.example.mvvmshoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository : ShoppingRepository) : ViewModel() {

    // CoroutineScope(Dispatchers.Main) = on lance la fonction sur le thread principal
    // Room intègre une protection du main thread
    fun upsert(item : ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item : ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}
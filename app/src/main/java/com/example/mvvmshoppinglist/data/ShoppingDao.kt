package com.example.mvvmshoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmshoppinglist.data.db.entities.ShoppingItem

@Dao
// Dao = classe qui gère les fonctions d'accès à la base de donnée
interface ShoppingDao {
    // OnConflictStrategy.REPLACE = si l'item existe déjà, on l'overwrite avec les nouvelles données
    // Suspend means the function will asynchronously with coroutines
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    // With LiveData it will make it really efficient to update the recyclerview later on
    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>>
}
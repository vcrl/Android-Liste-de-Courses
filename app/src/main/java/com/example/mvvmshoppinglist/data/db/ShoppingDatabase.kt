package com.example.mvvmshoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmshoppinglist.data.ShoppingDao
import com.example.mvvmshoppinglist.data.db.entities.ShoppingItem

@Database(
    // entities = which classes are supposed to be in the database
    entities = [ShoppingItem::class],
    // the version must change everytime we update the database
    version = 1
)
// en abstract class can not be instantiated, meaning we can not create object for the abstract class
abstract class ShoppingDatabase : RoomDatabase() {

    // with this function we make sure we can access our database queries (without overriding them)
    abstract fun getShoppingDao() : ShoppingDao

    // companion object = static keyword in Java, so accessible from outside
    companion object {
        // we need to add @Volatile so we make sure there is only one thread writing to this class
        // at the time, so it can just be initialized once
        @Volatile
        // we're creating a singleton
        private var instance : ShoppingDatabase? = null
        // Any() toutes les classes du projet
        private val LOCK = Any()

        // this function is executed whenever we create an instance of our ShoppingDatabse class
        // operator fun invoke = executed when object is created
        // and returns our instance, but if our instance = null, then it calls synchronized(LOCK)
        // -> locks everyclass so no other threads can set the instance at the time when we execute the function
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        // basically creates the database with name "ShoppingDB.db" on the device
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ShoppingDatabase::class.java, "ShoppingDB.db").build()

    }
}
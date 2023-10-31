package com.shkonda.geekknastu.data_base.events

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shkonda.geekknastu.util.ListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)

    @Delete
    suspend fun deleteItem(item: ListItem)

    @Query("SELECT * FROM events WHERE category LIKE :cat")
    fun getAllItemsByCategory(cat: String): Flow<List<ListItem>>

    @Query("SELECT * FROM events WHERE isFav =  1")
    fun getFavorites(): Flow<List<ListItem>>
}
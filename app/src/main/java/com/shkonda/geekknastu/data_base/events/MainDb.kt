package com.shkonda.geekknastu.data_base.events

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shkonda.geekknastu.util.ListItem

@Database(
    entities = [ListItem::class],
    version = 1
)
abstract class MainDb : RoomDatabase() {
    abstract val dao: Dao
}
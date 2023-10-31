package com.shkonda.geekknastu.data_base.di

import android.app.Application
import androidx.room.Room
import com.shkonda.geekknastu.data_base.events.MainDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideMainDB(app: Application): MainDb {
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "events_info"
        ).createFromAsset("db/events_info.db").build()
    }
}
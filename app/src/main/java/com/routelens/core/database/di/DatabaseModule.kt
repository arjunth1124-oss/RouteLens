package com.routelens.core.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.routelens.core.database.AppDatabase
import com.routelens.core.database.dao.TransitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "routelens.db"
        )
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("INSERT INTO stops (id, name, latitude, longitude) VALUES ('stop_1', 'India Gate', 28.6129, 77.2295)")
                    db.execSQL("INSERT INTO stops (id, name, latitude, longitude) VALUES ('stop_2', 'Connaught Place', 28.6315, 77.2167)")
                    db.execSQL("INSERT INTO stops (id, name, latitude, longitude) VALUES ('stop_3', 'Central Station', 28.6139, 77.2090)")
                    db.execSQL("INSERT INTO stops (id, name, latitude, longitude) VALUES ('stop_4', 'City Center', 28.6250, 77.2180)")
                    db.execSQL("INSERT INTO stops (id, name, latitude, longitude) VALUES ('stop_5', 'Taj Mahal, Agra', 27.1751, 78.0421)")
                }
            })
            .build()
    }

    @Provides
    fun provideTransitDao(database: AppDatabase): TransitDao {
        return database.transitDao()
    }
}
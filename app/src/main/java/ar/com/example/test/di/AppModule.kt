package ar.com.example.test.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ar.com.example.test.data.room.ApplicationDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context, ApplicationDB::class.java, "person_table"
    ).build()

    @Singleton
    @Provides
    fun providesPersonDao(db: ApplicationDB) = db.personDao()
}
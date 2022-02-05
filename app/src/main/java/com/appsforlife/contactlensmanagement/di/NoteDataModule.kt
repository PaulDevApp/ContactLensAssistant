package com.appsforlife.contactlensmanagement.di

import android.app.Application
import com.appsforlife.contactlensmanagement.data.AppDataBase
import com.appsforlife.contactlensmanagement.data.noteitemdata.NoteItemDao
import com.appsforlife.contactlensmanagement.data.noteitemdata.NoteItemListRepositoryImpl
import com.appsforlife.contactlensmanagement.domain.repositories.NoteItemRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface NoteDataModule {

    @ApplicationScope
    @Binds

    fun bindNoteListRepository(impl: NoteItemListRepositoryImpl): NoteItemRepository

    companion object {
        @Provides
        fun provideNoteListDao(
            application: Application
        ): NoteItemDao {
            return AppDataBase.getInstance(application).noteItemDao()
        }
    }
}
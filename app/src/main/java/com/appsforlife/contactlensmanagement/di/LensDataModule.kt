package com.appsforlife.contactlensmanagement.di

import android.app.Application
import com.appsforlife.contactlensmanagement.data.AppDataBase
import com.appsforlife.contactlensmanagement.data.lensitemdata.LensItemDao
import com.appsforlife.contactlensmanagement.data.lensitemdata.LensItemListRepositoryImpl
import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface LensDataModule {

    @ApplicationScope
    @Binds
    fun bindLensListRepository(impl: LensItemListRepositoryImpl): LensItemRepository

    companion object {
        @Provides
        fun provideLensListDao(
            application: Application
        ): LensItemDao {
            return AppDataBase.getInstance(application).lensItemDao()
        }
    }
}
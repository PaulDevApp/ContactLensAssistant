package com.appsforlife.contactlensmanagement.di

import android.app.Application
import com.appsforlife.contactlensmanagement.presentation.fragments.DetailNoteFragment
import com.appsforlife.contactlensmanagement.presentation.fragments.MainFragment
import com.appsforlife.contactlensmanagement.presentation.fragments.NoteListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        LensDataModule::class,
        NoteDataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(detailNoteFragment: DetailNoteFragment)

    fun inject(noteListFragment: NoteListFragment)

    fun inject(mainFragment: MainFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
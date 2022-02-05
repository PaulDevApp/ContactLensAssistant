package com.appsforlife.contactlensmanagement.di

import androidx.lifecycle.ViewModel
import com.appsforlife.contactlensmanagement.presentation.viewmodels.lensitemviewmodel.LensItemViewModel
import com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels.DetailNoteItemViewModel
import com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels.NoteItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LensItemViewModel::class)
    fun bindLensItemViewModel(viewModel: LensItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NoteItemViewModel::class)
    fun bindNoteItemViewModel(viewModel: NoteItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailNoteItemViewModel::class)
    fun bindDetailNoteItemViewModel(viewModel: DetailNoteItemViewModel): ViewModel
}
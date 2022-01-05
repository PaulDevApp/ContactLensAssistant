package com.appsforlife.contactlensmanagement.presentation.viewmodelfactories.noteitemviewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsforlife.contactlensmanagement.data.noteitemdata.NoteItemListRepositoryImpl
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.*
import com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels.DetailNoteItemViewModel

class NoteItemViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        NoteItemListRepositoryImpl(context)
    }

    private val editNoteItemUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        EditNoteItemUseCase(repository)
    }

    private val addNoteItemUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        AddNoteItemUseCase(repository)
    }

    private val getNoteItemUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        GetNoteItemUseCase(repository)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailNoteItemViewModel(
            editNoteItemUseCase = editNoteItemUseCase,
            addNoteItemUseCase = addNoteItemUseCase,
            getNoteItemUseCase = getNoteItemUseCase
        ) as T
    }
}
package com.appsforlife.contactlensmanagement.presentation.viewmodelfactories.noteitemviewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsforlife.contactlensmanagement.data.noteitemdata.NoteItemListRepositoryImpl
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.*
import com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels.DetailNoteItemViewModel
import com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels.NoteItemViewModel

class NoteItemViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        NoteItemListRepositoryImpl(context)
    }

    private val getNoteItemListUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        GetNoteItemListUseCase(repository)
    }

    private val deleteNoteItemUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        DeleteNoteItemUseCase(repository)
    }

    private val getNotesItemCountUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        GetNotesItemCountUseCase(repository)
    }

    private val addNoteItemUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        AddNoteItemUseCase(repository)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteItemViewModel(
            getNoteItemListUseCase = getNoteItemListUseCase,
            deleteNoteItemUseCase = deleteNoteItemUseCase,
            getNotesItemCountUseCase = getNotesItemCountUseCase,
            addNoteItemUseCase = addNoteItemUseCase
        ) as T
    }
}

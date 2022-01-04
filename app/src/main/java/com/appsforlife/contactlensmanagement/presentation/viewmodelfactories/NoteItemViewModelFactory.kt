package com.appsforlife.contactlensmanagement.presentation.viewmodelfactories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsforlife.contactlensmanagement.data.noteitemdata.NoteItemListRepositoryImpl
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.AddNoteItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.DeleteNoteItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.GetNoteItemListUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.GetNoteItemUseCase
import com.appsforlife.contactlensmanagement.presentation.viewmodels.NoteItemViewModel

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
    private val addNoteItemUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        AddNoteItemUseCase(repository)
    }

    private val getNoteItemUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        GetNoteItemUseCase(repository)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteItemViewModel(
            getNoteItemListUseCase = getNoteItemListUseCase,
            deleteNoteItemUseCase = deleteNoteItemUseCase,
            addNoteItemUseCase = addNoteItemUseCase,
            getNoteItemUseCase = getNoteItemUseCase
        ) as T
    }
}
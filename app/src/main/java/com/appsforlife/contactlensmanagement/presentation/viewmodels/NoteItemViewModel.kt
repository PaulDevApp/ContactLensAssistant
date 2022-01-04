package com.appsforlife.contactlensmanagement.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsforlife.contactlensmanagement.domain.entities.LensItem
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.AddNoteItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.DeleteNoteItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.GetNoteItemListUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.GetNoteItemUseCase
import kotlinx.coroutines.launch

class NoteItemViewModel(
    getNoteItemListUseCase: GetNoteItemListUseCase,
    private val deleteNoteItemUseCase: DeleteNoteItemUseCase,
    private val addNoteItemUseCase: AddNoteItemUseCase,
    private val getNoteItemUseCase: GetNoteItemUseCase
) : ViewModel() {

    val noteItemList = getNoteItemListUseCase.getNoteItemList()

    fun deleteNoteItem(noteItem: NoteItem) {
        viewModelScope.launch {
            deleteNoteItemUseCase.deleteNoteItem(noteItem)
        }
    }

    fun addNoteItem(noteItem: NoteItem) {
        viewModelScope.launch {
            addNoteItemUseCase.addNoteItem(noteItem)
        }
    }

    fun getNoteItem(noteItemId: Int){
        viewModelScope.launch {
            getNoteItemUseCase.getNoteItem(noteItemId)
        }
    }

}
package com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.AddNoteItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.DeleteNoteItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.GetNoteItemListUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.GetNotesItemCountUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteItemViewModel @Inject constructor(
    getNoteItemListUseCase: GetNoteItemListUseCase,
    private val deleteNoteItemUseCase: DeleteNoteItemUseCase,
    getNotesItemCountUseCase: GetNotesItemCountUseCase,
    private val addNoteItemUseCase: AddNoteItemUseCase,
) : ViewModel() {

    val noteItemList = getNoteItemListUseCase.getNoteItemList()
    val notesCount = getNotesItemCountUseCase.getNotesItemCount()

    fun deleteNoteItem(noteItem: NoteItem) {
        viewModelScope.launch {
            deleteNoteItemUseCase.deleteNoteItem(noteItem = noteItem)
        }
    }

    fun addNoteItem(noteItem: NoteItem) {
        viewModelScope.launch {
            addNoteItemUseCase.addNoteItem(noteItem)
        }
    }

}
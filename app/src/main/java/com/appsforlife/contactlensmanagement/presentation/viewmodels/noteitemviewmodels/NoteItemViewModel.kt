package com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.DeleteNoteItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.GetNoteItemListUseCase
import kotlinx.coroutines.launch

class NoteItemViewModel(
    getNoteItemListUseCase: GetNoteItemListUseCase,
    private val deleteNoteItemUseCase: DeleteNoteItemUseCase,
) : ViewModel() {

    val noteItemList = getNoteItemListUseCase.getNoteItemList()

    fun deleteNoteItem(noteItem: NoteItem) {
        viewModelScope.launch {
            deleteNoteItemUseCase.deleteNoteItem(noteItem = noteItem)
        }
    }

}
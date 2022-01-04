package com.appsforlife.contactlensmanagement.domain.usecases.noteusecases

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.repositories.NoteItemRepository

class GetNoteItemListUseCase(private val noteItemRepository: NoteItemRepository) {

    fun getNoteItemList(): LiveData<List<NoteItem>> {
        return noteItemRepository.getNoteItemList()
    }
}
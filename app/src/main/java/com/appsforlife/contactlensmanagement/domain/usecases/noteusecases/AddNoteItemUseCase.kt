package com.appsforlife.contactlensmanagement.domain.usecases.noteusecases

import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.repositories.NoteItemRepository

class AddNoteItemUseCase(private val noteItemRepository: NoteItemRepository){

    suspend fun addNoteItem(noteItem: NoteItem){
        noteItemRepository.addNoteItem(noteItem)
    }
}
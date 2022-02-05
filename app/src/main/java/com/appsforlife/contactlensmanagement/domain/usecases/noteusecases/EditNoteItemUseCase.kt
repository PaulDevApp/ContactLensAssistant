package com.appsforlife.contactlensmanagement.domain.usecases.noteusecases

import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.repositories.NoteItemRepository
import javax.inject.Inject

class EditNoteItemUseCase @Inject constructor(
    private val noteItemRepository: NoteItemRepository
) {

    suspend fun editNoteItem(noteItem: NoteItem) {
        noteItemRepository.editNoteItem(noteItem)
    }
}
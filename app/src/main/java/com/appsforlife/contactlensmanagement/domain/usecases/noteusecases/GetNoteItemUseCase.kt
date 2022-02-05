package com.appsforlife.contactlensmanagement.domain.usecases.noteusecases

import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.repositories.NoteItemRepository
import javax.inject.Inject

class GetNoteItemUseCase @Inject constructor(
    private val noteItemRepository: NoteItemRepository
) {
    suspend fun getNoteItem(noteItemId: Int): NoteItem {
        return noteItemRepository.getNoteItem(noteItemId)
    }
}
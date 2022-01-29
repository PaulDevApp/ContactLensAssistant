package com.appsforlife.contactlensmanagement.domain.usecases.noteusecases

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository
import com.appsforlife.contactlensmanagement.domain.repositories.NoteItemRepository

class GetNotesItemCountUseCase(private val noteItemRepository: NoteItemRepository) {

    fun getNotesItemCount(): LiveData<Int> {
        return noteItemRepository.getNotesItemCount()
    }
}
package com.appsforlife.contactlensmanagement.domain.usecases.noteusecases

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.repositories.NoteItemRepository
import javax.inject.Inject

class GetNotesItemCountUseCase @Inject constructor(
    private val noteItemRepository: NoteItemRepository
) {
    fun getNotesItemCount(): LiveData<Int> {
        return noteItemRepository.getNotesItemCount()
    }
}
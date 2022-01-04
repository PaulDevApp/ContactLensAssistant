package com.appsforlife.contactlensmanagement.domain.repositories

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem

interface NoteItemRepository {

    suspend fun addNoteItem(noteItem: NoteItem)

    suspend fun deleteNoteItem(noteItem: NoteItem)

    fun getNoteItemList(): LiveData<List<NoteItem>>

    suspend fun getNoteItem(noteItemId: Int): NoteItem
}
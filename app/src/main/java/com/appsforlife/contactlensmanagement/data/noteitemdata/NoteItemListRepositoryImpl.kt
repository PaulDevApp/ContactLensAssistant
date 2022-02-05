package com.appsforlife.contactlensmanagement.data.noteitemdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.repositories.NoteItemRepository
import javax.inject.Inject

class NoteItemListRepositoryImpl @Inject constructor(
    private val noteItemDao: NoteItemDao,
    private val mapper: NoteItemListMapper
) : NoteItemRepository {

    override suspend fun addNoteItem(noteItem: NoteItem) {
        noteItemDao.addNoteItem(mapper.mapEntityToDbModel(noteItem))
    }

    override suspend fun deleteNoteItem(noteItem: NoteItem) {
        noteItemDao.deleteNoteItem(noteItem.id)
    }

    override fun getNoteItemList(): LiveData<List<NoteItem>> = Transformations.map(
        noteItemDao.getNoteItemList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun getNoteItem(noteItemId: Int): NoteItem {
        val dbModel = noteItemDao.getNoteItem(noteItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override suspend fun editNoteItem(noteItem: NoteItem) {
        noteItemDao.addNoteItem(mapper.mapEntityToDbModel(noteItem))
    }

    override fun getNotesItemCount(): LiveData<Int> = noteItemDao.getRowCount()

}
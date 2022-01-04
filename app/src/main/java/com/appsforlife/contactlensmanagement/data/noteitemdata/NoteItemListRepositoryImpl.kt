package com.appsforlife.contactlensmanagement.data.noteitemdata

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.appsforlife.contactlensmanagement.data.AppDataBase
import com.appsforlife.contactlensmanagement.data.lensitemdata.LensItemListMapper
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.repositories.NoteItemRepository

class NoteItemListRepositoryImpl(
    context: Context
) : NoteItemRepository {

    private val noteItemDao = AppDataBase.getInstance(context).noteItemDao()
    private val mapper = NoteItemListMapper()

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
}
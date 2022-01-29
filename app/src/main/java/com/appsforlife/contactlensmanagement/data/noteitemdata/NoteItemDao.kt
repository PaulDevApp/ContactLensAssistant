package com.appsforlife.contactlensmanagement.data.noteitemdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteItemDao {

    @Query("SELECT * FROM note_item_db_name ORDER BY id DESC")
    fun getNoteItemList(): LiveData<List<NoteItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteItem(noteItemDbModel: NoteItemDbModel)

    @Query("DELETE FROM note_item_db_name WHERE id=:noteItemId")
    suspend fun deleteNoteItem(noteItemId: Int)

    @Query("SELECT * FROM note_item_db_name WHERE id=:noteItemId LIMIT 1")
    suspend fun getNoteItem(noteItemId: Int): NoteItemDbModel

    @Query("SELECT COUNT(id) FROM note_item_db_name")
    fun getRowCount(): LiveData<Int>
}
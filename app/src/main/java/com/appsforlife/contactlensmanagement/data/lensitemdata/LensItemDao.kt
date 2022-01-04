package com.appsforlife.contactlensmanagement.data.lensitemdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LensItemDao {

    @Query("SELECT * FROM lens_item_db_name ORDER BY id ASC")
    fun getLensItemList(): LiveData<List<LensItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLensItem(lensItemDbModel: LensItemDbModel)

    @Query("DELETE FROM lens_item_db_name WHERE id=:lensItemId")
    suspend fun deleteLensItem(lensItemId: Int)

    @Query("DELETE from lens_item_db_name")
    suspend fun removeAllLensItems()

    @Query("SELECT COUNT(date) FROM lens_item_db_name")
    fun getRowCount(): LiveData<Int>
}
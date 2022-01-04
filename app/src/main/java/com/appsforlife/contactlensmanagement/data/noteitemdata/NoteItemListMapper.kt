package com.appsforlife.contactlensmanagement.data.noteitemdata

import com.appsforlife.contactlensmanagement.domain.entities.NoteItem

class NoteItemListMapper {

    fun mapEntityToDbModel(noteItem: NoteItem) = NoteItemDbModel(
        id = noteItem.id,
        opticalPower = noteItem.opticalPower,
        radiusOfCurvature = noteItem.radiusOfCurvature,
        diameter = noteItem.diameter
    )

    fun mapDbModelToEntity(noteItemDbModel: NoteItemDbModel) = NoteItem(
        id = noteItemDbModel.id,
        opticalPower = noteItemDbModel.opticalPower,
        radiusOfCurvature = noteItemDbModel.radiusOfCurvature,
        diameter = noteItemDbModel.diameter
    )

    fun mapListDbModelToListEntity(list: List<NoteItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}
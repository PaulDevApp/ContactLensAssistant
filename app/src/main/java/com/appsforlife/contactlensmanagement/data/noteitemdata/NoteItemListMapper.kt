package com.appsforlife.contactlensmanagement.data.noteitemdata

import com.appsforlife.contactlensmanagement.domain.entities.NoteItem

class NoteItemListMapper {

    fun mapEntityToDbModel(noteItem: NoteItem) = NoteItemDbModel(
        id = noteItem.id,
        leftOpticalPower = noteItem.leftOpticalPower,
        leftRadiusOfCurvature = noteItem.leftRadiusOfCurvature,
        leftDiameter = noteItem.leftDiameter,
        rightOpticalPower = noteItem.rightOpticalPower,
        rightRadiusOfCurvature = noteItem.rightRadiusOfCurvature,
        rightDiameter = noteItem.rightDiameter,
        comment = noteItem.comment,
        title = noteItem.title
    )

    fun mapDbModelToEntity(noteItemDbModel: NoteItemDbModel) = NoteItem(
        id = noteItemDbModel.id,
        leftOpticalPower = noteItemDbModel.leftOpticalPower,
        leftRadiusOfCurvature = noteItemDbModel.leftRadiusOfCurvature,
        leftDiameter = noteItemDbModel.leftDiameter,
        rightOpticalPower = noteItemDbModel.rightOpticalPower,
        rightRadiusOfCurvature = noteItemDbModel.rightRadiusOfCurvature,
        rightDiameter = noteItemDbModel.rightDiameter,
        comment = noteItemDbModel.comment,
        title = noteItemDbModel.title
    )

    fun mapListDbModelToListEntity(list: List<NoteItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}
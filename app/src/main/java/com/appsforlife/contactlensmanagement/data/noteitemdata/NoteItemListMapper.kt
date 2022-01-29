package com.appsforlife.contactlensmanagement.data.noteitemdata

import com.appsforlife.contactlensmanagement.domain.entities.NoteItem

class NoteItemListMapper {

    fun mapEntityToDbModel(noteItem: NoteItem) = NoteItemDbModel(
        id = noteItem.id,
        leftOpticalPower = noteItem.leftOpticalPower,
        leftRadiusOfCurvature = noteItem.leftRadiusOfCurvature,
        leftCylinderPower = noteItem.leftCylinderPower,
        leftAxis = noteItem.leftAxis,
        rightOpticalPower = noteItem.rightOpticalPower,
        rightRadiusOfCurvature = noteItem.rightRadiusOfCurvature,
        rightCylinderPower = noteItem.rightCylinderPower,
        rightAxis = noteItem.rightAxis,
        title = noteItem.title,
        text = noteItem.text
    )

    fun mapDbModelToEntity(noteItemDbModel: NoteItemDbModel) = NoteItem(
        id = noteItemDbModel.id,
        leftOpticalPower = noteItemDbModel.leftOpticalPower,
        leftRadiusOfCurvature = noteItemDbModel.leftRadiusOfCurvature,
        leftCylinderPower = noteItemDbModel.leftCylinderPower,
        leftAxis = noteItemDbModel.leftAxis,
        rightOpticalPower = noteItemDbModel.rightOpticalPower,
        rightRadiusOfCurvature = noteItemDbModel.rightRadiusOfCurvature,
        rightCylinderPower = noteItemDbModel.rightCylinderPower,
        rightAxis = noteItemDbModel.rightAxis,
        title = noteItemDbModel.title,
        text = noteItemDbModel.text
    )

    fun mapListDbModelToListEntity(list: List<NoteItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}
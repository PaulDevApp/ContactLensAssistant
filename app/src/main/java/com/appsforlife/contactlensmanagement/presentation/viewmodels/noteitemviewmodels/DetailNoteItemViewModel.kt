package com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.AddNoteItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.EditNoteItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.GetNoteItemUseCase
import kotlinx.coroutines.launch

class DetailNoteItemViewModel(
    private val editNoteItemUseCase: EditNoteItemUseCase,
    private val addNoteItemUseCase: AddNoteItemUseCase,
    private val getNoteItemUseCase: GetNoteItemUseCase
) : ViewModel() {

    private val _noteItem = MutableLiveData<NoteItem>()
    val noteItem: LiveData<NoteItem>
        get() = _noteItem


    fun getNoteItem(noteItemId: Int) {
        viewModelScope.launch {
            val noteItem = getNoteItemUseCase.getNoteItem(noteItemId)
            _noteItem.value = noteItem
        }
    }

    fun editNoteItem(
        inputLeftOpticalPower: String?,
        inputLeftRadiusOfCurvature: String?,
        inputLeftCylinderPower: String?,
        inputLeftAxis: String?,
        inputRightOpticalPower: String?,
        inputRightRadiusOfCurvature: String?,
        inputRightCylinderPower: String?,
        inputRightAxis: String?,
        inputTitle: String?,
        inputText: String?
    ) {
        val leftOpticalPower = parseValue(value = inputLeftOpticalPower)
        val leftRadiusOfCurvature = parseValue(value = inputLeftRadiusOfCurvature)
        val leftCylinderPower = parseValue(value = inputLeftCylinderPower)
        val leftAxis = parseValue(value = inputLeftAxis)
        val rightOpticalPower = parseValue(value = inputRightOpticalPower)
        val rightRadiusOfCurvature = parseValue(value = inputRightRadiusOfCurvature)
        val rightCylinderPower = parseValue(value = inputRightCylinderPower)
        val rightAxis = parseValue(value = inputRightAxis)
        val title = parseValue(value = inputTitle)
        val text = parseValue(value = inputText)
        _noteItem.value?.let {
            viewModelScope.launch {
                val noteItem = it.copy(
                    leftOpticalPower = leftOpticalPower,
                    leftRadiusOfCurvature = leftRadiusOfCurvature,
                    leftCylinderPower = leftCylinderPower,
                    leftAxis = leftAxis,
                    rightOpticalPower = rightOpticalPower,
                    rightRadiusOfCurvature = rightRadiusOfCurvature,
                    rightCylinderPower = rightCylinderPower,
                    rightAxis = rightAxis,
                    title = title,
                    text = text
                )
                editNoteItemUseCase.editNoteItem(noteItem)
            }
        }
    }


    fun addNoteItem(
        inputLeftOpticalPower: String?,
        inputLeftRadiusOfCurvature: String?,
        inputLeftCylinderPower: String?,
        inputLeftAxis: String?,
        inputRightOpticalPower: String?,
        inputRightRadiusOfCurvature: String?,
        inputRightCylinderPower: String?,
        inputRightAxis: String?,
        inputTitle: String?,
        inputText: String?
    ) {
        val leftOpticalPower = parseValue(value = inputLeftOpticalPower)
        val leftRadiusOfCurvature = parseValue(value = inputLeftRadiusOfCurvature)
        val leftCylinderPower = parseValue(value = inputLeftCylinderPower)
        val leftAxis = parseValue(value = inputLeftAxis)
        val rightOpticalPower = parseValue(value = inputRightOpticalPower)
        val rightRadiusOfCurvature = parseValue(value = inputRightRadiusOfCurvature)
        val rightCylinderPower = parseValue(value = inputRightCylinderPower)
        val rightAxis = parseValue(value = inputRightAxis)
        val title = parseValue(value = inputTitle)
        val text = parseValue(value = inputText)
        viewModelScope.launch {
            val noteItem = NoteItem(
                leftOpticalPower = leftOpticalPower,
                leftRadiusOfCurvature = leftRadiusOfCurvature,
                leftCylinderPower = leftCylinderPower,
                leftAxis = leftAxis,
                rightOpticalPower = rightOpticalPower,
                rightRadiusOfCurvature = rightRadiusOfCurvature,
                rightCylinderPower = rightCylinderPower,
                rightAxis = rightAxis,
                title = title,
                text = text
            )
            addNoteItemUseCase.addNoteItem(noteItem)
        }
    }

    private fun parseValue(value: String?): String {
        return if (TextUtils.isEmpty(value)) {
            "-"
        } else {
            value.toString()
        }
    }

}
















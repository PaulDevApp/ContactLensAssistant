package com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsforlife.contactlensmanagement.domain.entities.LensItem
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.domain.usecases.noteusecases.*
import kotlinx.coroutines.launch

class DetailNoteItemViewModel(
    private val editNoteItemUseCase: EditNoteItemUseCase,
    private val addNoteItemUseCase: AddNoteItemUseCase,
    private val getNoteItemUseCase: GetNoteItemUseCase
) : ViewModel() {

    private val _noteItem = MutableLiveData<NoteItem>()
    val noteItem: LiveData<NoteItem>
        get() = _noteItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getNoteItem(noteItemId: Int) {
        viewModelScope.launch {
            val noteItem = getNoteItemUseCase.getNoteItem(noteItemId)
            _noteItem.value = noteItem
        }
    }

    fun editNoteItem(
        inputLeftOpticalPower: String?,
        inputLeftRadiusOfCurvature: String?,
        inputLeftDiameter: String?,
        inputRightOpticalPower: String?,
        inputRightRadiusOfCurvature: String?,
        inputRightDiameter: String?,
        inputComment: String?,
        inputTitle: String?
    ) {
        val leftOpticalPower = parseValue(value = inputLeftOpticalPower)
        val leftRadiusOfCurvature = parseValue(value = inputLeftRadiusOfCurvature)
        val leftDiameter = parseValue(value = inputLeftDiameter)
        val rightOpticalPower = parseValue(value = inputRightOpticalPower)
        val rightRadiusOfCurvature = parseValue(value = inputRightRadiusOfCurvature)
        val rightDiameter = parseValue(value = inputRightDiameter)
        val comment = parseValue(value = inputComment)
        val title = parseValue(value = inputTitle)
        _noteItem.value?.let {
            viewModelScope.launch {
                val noteItem = it.copy(
                    leftOpticalPower = leftOpticalPower,
                    leftRadiusOfCurvature = leftRadiusOfCurvature,
                    leftDiameter = leftDiameter,
                    rightOpticalPower = rightOpticalPower,
                    rightRadiusOfCurvature = rightRadiusOfCurvature,
                    rightDiameter = rightDiameter,
                    title = title
                )
                editNoteItemUseCase.editNoteItem(noteItem)
                finishWork()
            }
        }
    }


    fun addNoteItem(
        inputLeftOpticalPower: String?,
        inputLeftRadiusOfCurvature: String?,
        inputLeftDiameter: String?,
        inputRightOpticalPower: String?,
        inputRightRadiusOfCurvature: String?,
        inputRightDiameter: String?,
        inputComment: String?,
        inputTitle: String?
    ) {
        val leftOpticalPower = parseValue(value = inputLeftOpticalPower)
        val leftRadiusOfCurvature = parseValue(value = inputLeftRadiusOfCurvature)
        val leftDiameter = parseValue(value = inputLeftDiameter)
        val rightOpticalPower = parseValue(value = inputRightOpticalPower)
        val rightRadiusOfCurvature = parseValue(value = inputRightRadiusOfCurvature)
        val rightDiameter = parseValue(value = inputRightDiameter)
        val comment = parseValue(value = inputComment)
        val title = parseValue(value = inputTitle)
        viewModelScope.launch {
            val noteItem = NoteItem(
                leftOpticalPower = leftOpticalPower,
                leftRadiusOfCurvature = leftRadiusOfCurvature,
                leftDiameter = leftDiameter,
                rightOpticalPower = rightOpticalPower,
                rightRadiusOfCurvature = rightRadiusOfCurvature,
                rightDiameter = rightDiameter,
                title = title
            )
            addNoteItemUseCase.addNoteItem(noteItem)
            finishWork()
        }
    }

    private fun parseValue(value: String?): String {
        return value?.trim() ?: "-"
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}
















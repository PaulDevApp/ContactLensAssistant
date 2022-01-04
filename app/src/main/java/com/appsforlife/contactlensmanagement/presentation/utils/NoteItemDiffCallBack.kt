package com.appsforlife.contactlensmanagement.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem

class NoteItemDiffCallBack : DiffUtil.ItemCallback<NoteItem>() {
    override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return oldItem == newItem
    }
}
package com.appsforlife.contactlensmanagement.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.appsforlife.contactlensmanagement.domain.entities.LensItem

class LensItemDiffCallBack : DiffUtil.ItemCallback<LensItem>() {
    override fun areItemsTheSame(oldItem: LensItem, newItem: LensItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LensItem, newItem: LensItem): Boolean {
        return oldItem == newItem
    }
}
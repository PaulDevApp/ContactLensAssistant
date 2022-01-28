package com.appsforlife.contactlensmanagement.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import com.appsforlife.contactlensmanagement.databinding.NoteItemDetailBinding
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.presentation.utils.NoteItemDiffCallBack
import com.appsforlife.contactlensmanagement.presentation.viewholder.NoteItemViewHolder

class NoteListAdapter : ListAdapter<NoteItem, NoteItemViewHolder>(NoteItemDiffCallBack()) {

    var onItemClickListener: ((NoteItem) -> Unit)? = null
    var onItemLongClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val binding = NoteItemDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val noteItem = getItem(position)
        val binding = holder.binding

        binding.noteItem = noteItem

        binding.root.setOnClickListener {
            onItemClickListener?.invoke(noteItem)
        }
    }
}
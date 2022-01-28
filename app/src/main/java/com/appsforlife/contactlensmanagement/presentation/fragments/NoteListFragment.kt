package com.appsforlife.contactlensmanagement.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.LayoutNoteListFragmentBinding
import com.appsforlife.contactlensmanagement.presentation.adapters.LensListAdapter
import com.appsforlife.contactlensmanagement.presentation.adapters.NoteListAdapter
import com.appsforlife.contactlensmanagement.presentation.dialogs.DialogStartOver
import com.appsforlife.contactlensmanagement.presentation.viewmodelfactories.lensitemviewmodelfactory.LensItemViewModelFactory
import com.appsforlife.contactlensmanagement.presentation.viewmodelfactories.noteitemviewmodelfactory.NoteItemViewModelFactory
import com.appsforlife.contactlensmanagement.presentation.viewmodels.lensitemviewmodel.LensItemViewModel
import com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels.NoteItemViewModel

class NoteListFragment : Fragment() {

    private lateinit var noteListAdapter: NoteListAdapter
    private lateinit var noteViewModel: NoteItemViewModel

    private var _binding: LayoutNoteListFragmentBinding? = null
    private val binding: LayoutNoteListFragmentBinding
        get() = _binding ?: throw RuntimeException("LayoutNoteListFragmentBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFabTransition()
    }

    private fun setFabTransition() {
        sharedElementEnterTransition = activity?.let {
            TransitionInflater.from(it)
                .inflateTransition(R.transition.fragment_fab_transition)
        }
        sharedElementReturnTransition = activity?.let {
            TransitionInflater.from(it)
                .inflateTransition(R.transition.fragment_fab_transition)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = LayoutNoteListFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = ViewModelProvider(
            this,
            NoteItemViewModelFactory(requireContext())
        )[NoteItemViewModel::class.java]

        setUpRecyclerView()

        noteViewModel.noteItemList.observe(viewLifecycleOwner) {
            noteListAdapter.submitList(it)
        }

        setUpToolbar()

        onBackPressedCallBack()

        binding.fabAddNote.setOnClickListener {
            launchDetailNoteFragment()
        }
    }

    private fun setUpRecyclerView() {
        noteListAdapter = NoteListAdapter()
        with(binding) {
            rvNoteItems.layoutManager = GridLayoutManager(requireContext(), 2)
            rvNoteItems.adapter = noteListAdapter
            rvNoteItems.setHasFixedSize(true)
        }
    }

    private fun setUpToolbar() {
        binding.layoutToolbarNote.tvToolbarTitle.text =
            requireActivity().resources.getString(R.string.notes_with_parameters)
    }

    private fun onBackPressedCallBack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPresses()
                }
            })
    }

    private fun backPresses() {
        requireActivity().supportFragmentManager.popBackStack(MainFragment.NAME, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchDetailNoteFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailNoteFragment.newInstance())
            .addToBackStack(null)
            .addSharedElement(binding.fabAddNote, binding.fabAddNote.transitionName)
            .commit()
    }

    companion object {

        const val NAME = "NoteListFragment"
        private var isAnim = true

        fun newInstance(): NoteListFragment {
            return NoteListFragment()
        }
    }

}
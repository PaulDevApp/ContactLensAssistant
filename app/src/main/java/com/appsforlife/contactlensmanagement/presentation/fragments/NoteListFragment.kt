package com.appsforlife.contactlensmanagement.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.LayoutNoteListFragmentBinding

class NoteListFragment : Fragment() {

    private var _binding: LayoutNoteListFragmentBinding? = null
    private val binding: LayoutNoteListFragmentBinding
        get() = _binding ?: throw RuntimeException("LayoutNoteListFragmentBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFabTransition()
    }

    private fun setFabTransition() {
        sharedElementEnterTransition = TransitionInflater.from(activity)
            .inflateTransition(R.transition.fragment_fab_transition)
        sharedElementReturnTransition = TransitionInflater.from(activity)
            .inflateTransition(R.transition.fragment_fab_transition)
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

        setUpToolbar()

        onBackPressedCallBack()

        binding.fabAddNote.setOnClickListener {
            launchDetailNoteFragment()
        }
    }

    private fun setUpToolbar() {
        binding.layoutToolbarNote.tvToolbarTitle.text =
            requireActivity().resources.getString(R.string.notes_with_parameters)
    }

    private fun onBackPressedCallBack() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
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

        fun newInstance(): NoteListFragment {
            return NoteListFragment()
        }
    }

}
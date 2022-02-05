package com.appsforlife.contactlensmanagement.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.LayoutNoteListFragmentBinding
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.presentation.App
import com.appsforlife.contactlensmanagement.presentation.adapters.NoteListAdapter
import com.appsforlife.contactlensmanagement.presentation.viewmodelfactory.ViewModelFactory
import com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels.NoteItemViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class NoteListFragment : Fragment() {

    private lateinit var noteListAdapter: NoteListAdapter
    private lateinit var noteViewModel: NoteItemViewModel

    private var _binding: LayoutNoteListFragmentBinding? = null
    private val binding: LayoutNoteListFragmentBinding
        get() = _binding ?: throw RuntimeException("LayoutNoteListFragmentBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFabTransition()
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
            viewModelFactory
        )[NoteItemViewModel::class.java]

        setUpRecyclerView()

        noteViewModel.noteItemList.observe(viewLifecycleOwner) {
            noteListAdapter.submitList(it)
            startAnimationList()
        }

        setUpToolbar()

        onBackPressedCallBack()

        noteListAdapter.onItemClickListener = {
            launchDetailEditNoteFragment(it.id)
        }

        binding.fabAddNote.setOnClickListener {
            launchDetailAddNoteFragment()
        }

        noteViewModel.notesCount.observe(viewLifecycleOwner) {
            setLottieVisibility(it)
        }
    }

    private fun setUpRecyclerView() {
        noteListAdapter = NoteListAdapter()
        with(binding) {
            rvNoteItems.layoutManager = GridLayoutManager(requireContext(), 2)
            rvNoteItems.adapter = noteListAdapter
            rvNoteItems.setHasFixedSize(true)
            setUpItemTouchHelper(rvNoteItems)
        }
    }

    private fun setUpItemTouchHelper(rvLensItemList: RecyclerView) {
        val callBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = noteListAdapter.currentList[viewHolder.adapterPosition]
                noteViewModel.deleteNoteItem(item)
                setSnackBar(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rvLensItemList)
    }

    private fun setSnackBar(item: NoteItem) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            resources.getString(R.string.remove),
            Snackbar.LENGTH_LONG
        )
            .setAnchorView(R.id.fab_add_note)
            .setActionTextColor(requireActivity().getColor(R.color.colorAccentNight))
            .setAction(
                resources.getString(R.string.to_return)
            ) {
                noteViewModel.addNoteItem(item)
            }.show()
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

    private fun launchDetailEditNoteFragment(noteId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailNoteFragment.newInstanceEdit(noteId))
            .addToBackStack(null)
            .addSharedElement(binding.fabAddNote, binding.fabAddNote.transitionName)
            .commit()
    }

    private fun launchDetailAddNoteFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailNoteFragment.newInstance())
            .addToBackStack(null)
            .addSharedElement(binding.fabAddNote, binding.fabAddNote.transitionName)
            .commit()
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

    private fun startAnimationList() {
        val animation = AnimationUtils.loadLayoutAnimation(
            requireContext(),
            R.anim.layout_animation
        )
        if (isAnim) {
            binding.rvNoteItems.layoutAnimation = animation
            isAnim = false
        }
    }

    private fun setLottieVisibility(it: Int) {
        if (it > 0) {
            binding.lottieEmpty.visibility = View.GONE
        } else {
            binding.lottieEmpty.visibility = View.VISIBLE
        }
    }

    companion object {

        const val NAME = "NoteListFragment"
        private var isAnim = true

        fun newInstance(): NoteListFragment {
            return NoteListFragment()
        }

    }

}
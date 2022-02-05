package com.appsforlife.contactlensmanagement.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.LayoutDetailFragmentBinding
import com.appsforlife.contactlensmanagement.domain.entities.NoteItem
import com.appsforlife.contactlensmanagement.presentation.App
import com.appsforlife.contactlensmanagement.presentation.dialogs.DialogInfo
import com.appsforlife.contactlensmanagement.presentation.viewmodelfactory.ViewModelFactory
import com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels.DetailNoteItemViewModel
import javax.inject.Inject


class DetailNoteFragment : Fragment() {

    private lateinit var detailNoteViewModel: DetailNoteItemViewModel
    private lateinit var dialogInfo: DialogInfo

    private var _binding: LayoutDetailFragmentBinding? = null
    private val binding: LayoutDetailFragmentBinding
        get() = _binding ?: throw RuntimeException("LayoutDetailFragmentBinding is null")

    private var noteId: Int = NoteItem.UNDEFINED_ID
    private var screenMode: String = MODE_UNKNOWN

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

        parseParams()
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
        _binding = LayoutDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailNoteViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[DetailNoteItemViewModel::class.java]

        dialogInfo = DialogInfo(requireActivity())

        with(binding) {
            viewModel = detailNoteViewModel
            lifecycleOwner = viewLifecycleOwner

            ivInfoOpticalPower.setOnClickListener {
                dialogInfo.createDialogInfo(requireActivity().resources.getString(R.string.info_optical_power))
            }

            ivInfoRadiusOfCurvature.setOnClickListener {
                dialogInfo.createDialogInfo(requireActivity().resources.getString(R.string.info_radius_of_curvature))
            }

            ivAstigmatism.setOnClickListener {
                dialogInfo.createDialogInfo(requireActivity().resources.getString(R.string.info_astigmatism))
            }

        }


        setUpToolbar()

        onBackPressedCallBack()

        launchRightMode()

    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }

        screenMode = mode

        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(NOTE_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            noteId = args.getInt(NOTE_ID, NoteItem.UNDEFINED_ID)
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchAddMode() {
        with(binding) {
            fabSave.setOnClickListener {
                detailNoteViewModel.addNoteItem(
                    inputLeftOpticalPower = etOpticalPowerLeft.text?.toString(),
                    inputLeftRadiusOfCurvature = etRadiusOfCurvatureLeft.text?.toString(),
                    inputLeftCylinderPower = etCylinderPowerLeft.text?.toString(),
                    inputLeftAxis = etAxisLeft.text?.toString(),
                    inputRightOpticalPower = etOpticalPowerRight.text?.toString(),
                    inputRightRadiusOfCurvature = etRadiusOfCurvatureRight.text?.toString(),
                    inputRightCylinderPower = etCylinderPowerRight.text?.toString(),
                    inputRightAxis = etAxisRight.text?.toString(),
                    inputTitle = etTitle.text?.toString(),
                    inputText = etText.text?.toString()
                )
                backPresses()
            }
        }
    }

    private fun launchEditMode() {
        with(binding) {
            detailNoteViewModel.getNoteItem(noteId)
            fabSave.setOnClickListener {
                detailNoteViewModel.editNoteItem(
                    inputLeftOpticalPower = etOpticalPowerLeft.text?.toString(),
                    inputLeftRadiusOfCurvature = etRadiusOfCurvatureLeft.text?.toString(),
                    inputLeftCylinderPower = etCylinderPowerLeft.text?.toString(),
                    inputLeftAxis = etAxisLeft.text?.toString(),
                    inputRightOpticalPower = etOpticalPowerRight.text?.toString(),
                    inputRightRadiusOfCurvature = etRadiusOfCurvatureRight.text?.toString(),
                    inputRightCylinderPower = etCylinderPowerRight.text?.toString(),
                    inputRightAxis = etAxisRight.text?.toString(),
                    inputTitle = etTitle.text?.toString(),
                    inputText = etText.text?.toString()
                )
                backPresses()
            }
        }
    }

    private fun setUpToolbar() {
        binding.toolbarNoteList.tvToolbarTitle.text =
            requireActivity().resources.getString(R.string.parameters)
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
        requireActivity().supportFragmentManager.popBackStack(NoteListFragment.NAME, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val NOTE_ID = "extra_note_id"

        private const val MODE = "mode"
        private const val MODE_UNKNOWN = ""
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        fun newInstance(): DetailNoteFragment {
            return DetailNoteFragment().apply {
                arguments = Bundle().apply {
                    putString(MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEdit(noteId: Int): DetailNoteFragment {
            return DetailNoteFragment().apply {
                arguments = Bundle().apply {
                    putString(MODE, MODE_EDIT)
                    putInt(NOTE_ID, noteId)
                }
            }
        }
    }
}
package com.appsforlife.contactlensmanagement.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.appsforlife.contactlensmanagement.databinding.LayoutDetailFragmentBinding
import com.appsforlife.contactlensmanagement.presentation.viewmodelfactories.noteitemviewmodelfactory.DetailNoteItemViewModelFactory
import com.appsforlife.contactlensmanagement.presentation.viewmodels.noteitemviewmodels.DetailNoteItemViewModel


class DetailNoteFragment : Fragment() {

    private lateinit var detailNoteViewModel: DetailNoteItemViewModel

    private var _binding: LayoutDetailFragmentBinding? = null
    private val binding: LayoutDetailFragmentBinding
        get() = _binding ?: throw RuntimeException("LayoutDetailFragmentBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFabTransition()
    }

    private fun setFabTransition() {
        sharedElementEnterTransition = activity?.let {
            TransitionInflater.from(it)
                .inflateTransition(com.appsforlife.contactlensmanagement.R.transition.fragment_fab_transition)
        }
        sharedElementReturnTransition = activity?.let {
            TransitionInflater.from(it)
                .inflateTransition(com.appsforlife.contactlensmanagement.R.transition.fragment_fab_transition)
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
            DetailNoteItemViewModelFactory(requireContext())
        )[DetailNoteItemViewModel::class.java]

        with(binding) {
            viewModel = detailNoteViewModel
            lifecycleOwner = viewLifecycleOwner

            fabSave.setOnClickListener {
                saveNote()
                backPresses()
            }
        }


        setUpToolbar()

        onBackPressedCallBack()
    }

    private fun saveNote() {
        with(binding) {
            detailNoteViewModel.addNoteItem(
                inputLeftOpticalPower = etOpticalPowerLeft.text?.toString(),
                inputLeftRadiusOfCurvature = etRadiusOfCurvatureLeft.text?.toString(),
                inputLeftCylinderPower = etCylinderPowerLeft.text?.toString(),
                inputLeftAxis = etAxisLeft.text?.toString(),
                inputRightOpticalPower = etOpticalPowerRight.text?.toString(),
                inputRightRadiusOfCurvature = etRadiusOfCurvatureRight.text?.toString(),
                inputRightCylinderPower = etCylinderPowerRight.text?.toString(),
                inputRightAxis = etAxisRight.text?.toString(),
                inputTitle = etTitle.text?.toString()
            )
        }
    }

    private fun setUpToolbar() {
        binding.toolbarNoteList.tvToolbarTitle.text =
            requireActivity().resources.getString(com.appsforlife.contactlensmanagement.R.string.parameters)
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

        fun newInstance(): DetailNoteFragment {
            return DetailNoteFragment()
        }
    }
}
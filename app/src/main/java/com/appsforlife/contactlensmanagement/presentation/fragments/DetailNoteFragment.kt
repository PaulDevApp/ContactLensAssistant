package com.appsforlife.contactlensmanagement.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.LayoutDetailFragmentBinding

class DetailNoteFragment : Fragment() {

    private var _binding: LayoutDetailFragmentBinding? = null
    private val binding: LayoutDetailFragmentBinding
        get() = _binding ?: throw RuntimeException("LayoutDetailFragmentBinding is null")

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
        _binding = LayoutDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()

        onBackPressedCallBack()
    }

    private fun setUpToolbar() {
        binding.toolbarNoteList.tvToolbarTitle.text =
            requireActivity().resources.getString(R.string.parameters)
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

    companion object {

        fun newInstance(): DetailNoteFragment {
            return DetailNoteFragment()
        }
    }
}
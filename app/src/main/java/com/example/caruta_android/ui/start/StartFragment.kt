package com.example.caruta_android.ui.start

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.caruta_android.R
import com.example.caruta_android.databinding.FragmentStartBinding
import com.example.caruta_android.utils.CardCreator

class StartFragment : Fragment() {

    companion object {
        fun newInstance() = StartFragment()
    }

    private var _viewBinding: FragmentStartBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentStartBinding.inflate(layoutInflater, container, false)


        viewBinding.btnStart.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToInProgressFragment()
            findNavController().navigate(action)
        }

        return viewBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }


}
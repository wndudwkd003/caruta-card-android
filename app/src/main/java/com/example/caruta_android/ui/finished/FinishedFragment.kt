package com.example.caruta_android.ui.finished

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.caruta_android.R
import com.example.caruta_android.databinding.FragmentFinishedBinding
import com.example.caruta_android.databinding.FragmentInProgressBinding
import com.example.caruta_android.databinding.FragmentStartBinding

class FinishedFragment : Fragment() {

    companion object {
        fun newInstance() = FinishedFragment()
    }

    private var _viewBinding: FragmentFinishedBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var viewModel: FinishedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentFinishedBinding.inflate(layoutInflater, container, false)






        return viewBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }


}
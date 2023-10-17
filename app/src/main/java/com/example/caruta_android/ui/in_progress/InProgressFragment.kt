package com.example.caruta_android.ui.in_progress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.caruta_android.adapters.CardGridAdapter
import com.example.caruta_android.adapters.CardStackAdapter
import com.example.caruta_android.databinding.FragmentInProgressBinding
import com.example.caruta_android.utils.CardCreator
import com.example.caruta_android.views.CountdownDialog

class InProgressFragment : Fragment() {

    companion object {
        fun newInstance() = InProgressFragment()
    }

    private var _viewBinding: FragmentInProgressBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var viewModel: InProgressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentInProgressBinding.inflate(layoutInflater, container, false)

        val cardCreator = CardCreator(30)
        val showCards = cardCreator.getConversionShowCards()
        val selectCards = cardCreator.getConversionSelectCards()

        val cardStackAdapter = CardStackAdapter(showCards)
        viewBinding.rvCardStack.adapter = cardStackAdapter

        val cardGridAdapter = CardGridAdapter(selectCards)
        viewBinding.rvCardGrid.adapter = cardGridAdapter

        val gridLayoutManager = GridLayoutManager(requireContext(), 4)
        viewBinding.rvCardGrid.layoutManager = gridLayoutManager

        val startCountdownDialog = CountdownDialog(requireContext())
        startCountdownDialog.countDownListener = object : CountdownDialog.CountDownListener {
            override fun onComplete() {
                initProgress()
            }
        }

        startCountdownDialog.show()

        return viewBinding.root
    }

    private fun initProgress() {


    }


    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }


}
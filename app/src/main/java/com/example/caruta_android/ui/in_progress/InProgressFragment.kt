package com.example.caruta_android.ui.in_progress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.caruta_android.adapters.CardGridAdapter
import com.example.caruta_android.adapters.CardStackAdapter
import com.example.caruta_android.databinding.FragmentInProgressBinding
import com.example.caruta_android.utils.CardCreator
import com.example.caruta_android.utils.TimeFormatConvertor
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
        viewModel = ViewModelProvider(this)[InProgressViewModel::class.java]

        val cardStackAdapter = CardStackAdapter()
        viewModel.showCards.value?.let {
            cardStackAdapter.showCards = it
            viewBinding.rvCardStack.adapter = cardStackAdapter
        }


        var cardGridAdapter: CardGridAdapter?
        viewModel.selectCards.value?.let {
            cardGridAdapter = CardGridAdapter(it)
            cardGridAdapter?.listener = object: CardGridAdapter.OnItemClickListener {
                override fun onClick(id: Int) {
                    cardStackAdapter.click(id)
                }
            }
            viewBinding.rvCardGrid.adapter = cardGridAdapter
            viewBinding.rvCardGrid.layoutManager = GridLayoutManager(requireContext(), 4)
        }

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
        viewModel.startTimer()
        viewModel.elapsedTime.observe(viewLifecycleOwner) { timeText ->
            viewBinding.inProgressState.tvTime.text = timeText
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }


}
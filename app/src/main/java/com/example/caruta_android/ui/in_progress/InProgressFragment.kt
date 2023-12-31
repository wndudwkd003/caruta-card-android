package com.example.caruta_android.ui.in_progress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.caruta_android.adapters.CardGridAdapter
import com.example.caruta_android.adapters.CardStackAdapter
import com.example.caruta_android.databinding.FragmentInProgressBinding
import com.example.caruta_android.utils.CardCreator
import com.example.caruta_android.utils.TimeFormatConvertor
import com.example.caruta_android.views.CountdownDialog
import java.text.FieldPosition

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
        cardStackAdapter.showCards = mutableListOf()
        viewBinding.rvCardStack.adapter = cardStackAdapter


        viewModel.selectedCard.observe(viewLifecycleOwner) {
            cardStackAdapter.addCardItem(it)
        }


        var cardGridAdapter: CardGridAdapter?
        viewModel.selectCards.value?.let {
            cardGridAdapter = CardGridAdapter(it)
            cardGridAdapter?.listener = object: CardGridAdapter.OnItemClickListener {
                override fun onClick(id: String, position: Int) {
                    val checkedIndex = cardStackAdapter.checkingCard(id)
                    if (checkedIndex != -1) {
                        cardGridAdapter?.changeItemVisible(position)

                        cardStackAdapter.click(checkedIndex)
                        viewModel.selectRandomCard()
                    } else {
                        Toast.makeText(requireContext(), "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    }
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
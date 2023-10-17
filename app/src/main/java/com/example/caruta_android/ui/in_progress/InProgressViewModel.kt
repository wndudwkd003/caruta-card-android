package com.example.caruta_android.ui.in_progress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.caruta_android.classes.CarutaCard
import com.example.caruta_android.utils.CardCreator
import com.example.caruta_android.utils.TimeFormatConvertor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InProgressViewModel : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _elapsedTime = MutableLiveData("00:00.00")
    val elapsedTime: LiveData<String> get() = _elapsedTime
    private var rawTime = 0L

    private val _showCards = MutableLiveData<MutableList<CarutaCard>>()
    val showCards: LiveData<MutableList<CarutaCard>> get() = _showCards

    private val _selectCards = MutableLiveData<MutableList<CarutaCard>>()
    val selectCards: LiveData<MutableList<CarutaCard>> get() = _selectCards

    init {
        val cardCreator = CardCreator(30)
        _showCards.value = cardCreator.getConversionShowCards()
        _selectCards.value = cardCreator.getConversionSelectCards()
    }



    fun startTimer() {
        uiScope.launch {
            while (true) {
                delay(1)
                rawTime += 1L
                _elapsedTime.postValue(TimeFormatConvertor.convertToTimeFormat(rawTime))

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
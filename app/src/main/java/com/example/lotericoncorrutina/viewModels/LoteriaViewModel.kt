package com.example.lotericoncorrutina.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoteriaViewModel : ViewModel() {
    private val _lotoNumbers = mutableStateOf(emptyList<Int>())
    val lotoNumbers: State<List<Int>> = _lotoNumbers

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun generateLotoNumbers() {
        viewModelScope.launch {

            val generatedNumbers = mutableListOf<Int>()

            for (i in 1..6) {

                val number = (1..60).shuffled().first()
                generatedNumbers.add(number)


                _lotoNumbers.value = generatedNumbers.toList()


                delay(2000)
                _isLoading.value = true  // Empezamos mostrando el indicador
                delay(2000)
                _isLoading.value = false
            }


            _isLoading.value = false
        }
    }
}
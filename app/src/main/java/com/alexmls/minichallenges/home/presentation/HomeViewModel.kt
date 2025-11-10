package com.alexmls.minichallenges.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmls.minichallenges.home.domain.GetMiniChallengeCalendarUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCalendar: GetMiniChallengeCalendarUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = HomeState()
    )

    init {
        loadCalendar()
    }

    private fun loadCalendar() {
        viewModelScope.launch {
            val months = getCalendar()
            _state.value = HomeState(months = months)
        }
    }

}
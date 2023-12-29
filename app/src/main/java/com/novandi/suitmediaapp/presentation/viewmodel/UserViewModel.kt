package com.novandi.suitmediaapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.novandi.suitmediaapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    userUseCase: UserUseCase
) : ViewModel() {
    val users = userUseCase.getUsers().cachedIn(viewModelScope).asLiveData()
}
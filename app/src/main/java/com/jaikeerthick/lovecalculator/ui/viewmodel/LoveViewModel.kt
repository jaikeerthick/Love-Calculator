package com.jaikeerthick.lovecalculator.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaikeerthick.lovecalculator.repository.LoveRepository
import com.jaikeerthick.lovecalculator.ui.model.LoveResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoveViewModel@Inject constructor(
    private val repository: LoveRepository
) : ViewModel() {

    val response : MutableLiveData<LoveResponse> = MutableLiveData()


    fun getPercentage(fName: String, sName: String){

        viewModelScope.launch {
            response.postValue(repository.getPercentage(fName, sName))
        }

    }

}
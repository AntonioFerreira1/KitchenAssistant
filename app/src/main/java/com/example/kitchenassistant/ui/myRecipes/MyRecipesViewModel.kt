package com.example.kitchenassistant.ui.myRecipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyRecipesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is myRecipes Fragment"
    }
    val text: LiveData<String> = _text
}
package com.oleg.androidmvvm.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {
    var title = ObservableField<String>("")
    var releaseDate = ObservableField<String>("")
}
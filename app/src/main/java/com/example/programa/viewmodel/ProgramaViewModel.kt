package com.example.programa.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.programa.data.ProgramaDao
import com.example.programa.model.Programa
import com.example.programa.repository.ProgramaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProgramaViewModel(application: Application) : AndroidViewModel(application) {

    // atributo para obtener la lista de lugares en un arraylist especial
    val getAllData: MutableLiveData<List<Programa>>

    //se define los atributos para acceder al repositorio de lugar
    private val repository : ProgramaRepository = ProgramaRepository(ProgramaDao())

    //bloque de inicializacion de los atributos
    init {
        getAllData=repository.getAllData
    }

    fun savePrograma(programa: Programa){
        viewModelScope.launch(Dispatchers.IO){
            repository.savePrograma(programa)
        }
    }


}
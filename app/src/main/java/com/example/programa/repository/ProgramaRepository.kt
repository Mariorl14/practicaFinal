package com.example.programa.repository

import androidx.lifecycle.MutableLiveData
import com.example.programa.data.ProgramaDao
import com.example.programa.model.Programa

class ProgramaRepository(private val programaDao: ProgramaDao) {

    fun savePrograma(programa: Programa){
        programaDao.savePrograma(programa)
    }

    fun deletePrograma(programa: Programa){
        programaDao.deletePrograma(programa)
    }
    val getAllData: MutableLiveData<List<Programa>> = programaDao.getAllData()
}
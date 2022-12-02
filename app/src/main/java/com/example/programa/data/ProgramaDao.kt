package com.example.programa.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.example.programa.model.Programa

class ProgramaDao {

    private val coleccion1 = "programasApp"
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init{
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getAllData() : MutableLiveData<List<Programa>>{
        val listaprorgamas = MutableLiveData<List<Programa>>()
        firestore.collection(coleccion1)
            .addSnapshotListener{ instantanea, e ->
                if(e!=null){ //Se valida si se genero algun error en la captura de los documentos
                    return@addSnapshotListener
                }
                if (instantanea!=null){ //Si hay informacion recuperada...
                    //Recorro la instante documentos para crear la lista de lugares
                    val lista = ArrayList<Programa>()
                    instantanea.documents.forEach{
                        val programa =  it.toObject(Programa::class.java)
                        if (programa!=null){
                            lista.add(programa)
                        }
                    }
                    listaprorgamas.value = lista
                }
            }
        return listaprorgamas
    }

    fun savePrograma(programa: Programa){
        val documento: DocumentReference
        if (programa.id.isEmpty()){ //Si id tiene valor entonces es un documento nuevo
            documento = firestore.collection(coleccion1).document()
            programa.id =  documento.id
        }else{ //si el id tiene valor... entonces el documento existe..y recupero la info de el
            documento = firestore.collection(coleccion1).document(programa.id)
        }
        documento.set(programa)
            .addOnSuccessListener { Log.d("savePrograma","Se creó o modificó un programa") }
            .addOnCanceledListener { Log.d("savePrograma","NO se creó o modificó un programa") }
    }

    fun deletePrograma(programa: Programa){
        if (programa.id.isNotEmpty()){ //Si el id tiene valor..entonces podemos eliminar el lugar
            firestore.collection(coleccion1).document(programa.id).delete()
                .addOnSuccessListener { Log.d("deletePrograma","Se eliminó un programa") }
                .addOnCanceledListener { Log.d("deletePrograma","NO se eliminó un programa") }
        }
    }

}
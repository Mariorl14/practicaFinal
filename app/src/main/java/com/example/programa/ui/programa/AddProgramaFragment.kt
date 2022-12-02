package com.example.programa.ui.programa

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.example.programa.R
import com.example.programa.databinding.FragmentAddProgramaBinding
import com.example.programa.model.Programa
import com.example.programa.viewmodel.ProgramaViewModel

class AddProgramaFragment : Fragment() {
    private var _binding: FragmentAddProgramaBinding? = null
    private val binding get() = _binding!!

    private lateinit var programaViewModel: ProgramaViewModel
    private  lateinit var  tomarFotoActivity: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        programaViewModel =
            ViewModelProvider(this)[ProgramaViewModel::class.java]

        _binding = FragmentAddProgramaBinding.inflate(inflater, container, false)

        binding.btAgregar.setOnClickListener{
            addPrograma()
        }


        return binding.root
    }


    private fun addPrograma() {

        val nombre=binding.etNombre.text.toString()
        val audiencia=binding.etAudiencia.text.toString().toDouble()
        val canal=binding.etCanal.text.toString().toDouble()
        val presentadores=binding.etPresentadores.text.toString()
        val web=binding.etWeb.text.toString()

        if(nombre.isNotEmpty()){ // si puedo crear un lugar
            val programa= Programa("",nombre,audiencia,canal,presentadores,web)
            programaViewModel.savePrograma(programa)
            findNavController().navigate(R.id.action_addProgramaFragment_to_nav_home)
        }else{ //mensaje de error
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.programa.ui.programa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.programa.R
import com.example.programa.adapter.ProgramaAdapter
import com.example.programa.databinding.FragmentProgramaBinding
import com.example.programa.viewmodel.ProgramaViewModel

class LugarFragment : Fragment() {

    private var _binding: FragmentProgramaBinding? = null
    private val binding get() = _binding!!

    private lateinit var programaViewModel: ProgramaViewModel



  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
      programaViewModel =
            ViewModelProvider(this).get(ProgramaViewModel::class.java)

    _binding = FragmentProgramaBinding.inflate(inflater, container, false)
      binding.addPrograma.setOnClickListener{
          //es la que permite pasar de un fragmento a otro lugar
          findNavController().navigate(R.id.action_nav_home_to_addProgramaFragment)
      }

      //activar el reciclador
      val programaAdapter=ProgramaAdapter()
      val reciclador = binding.reciclador
      reciclador.adapter=programaAdapter
      //esta siempre va en cualquier sistema
      reciclador.layoutManager=LinearLayoutManager(requireContext())
      programaViewModel.getAllData.observe(viewLifecycleOwner){
          programaAdapter.setData(it)
      }


    return binding.root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.programa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.programa.model.Programa
import com.example.programa.ui.programa.ProgramaFragmentDirections

class ProgramaAdapter : RecyclerView.Adapter<ProgramaAdapter.LugarViewHolder>(){

    //para gestionar la informacion de todos los lugares
    private var lista = emptyList<Programa>()

    inner class LugarViewHolder(private val itemBinding: LugarFilaBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(programa: Programa){
            itemBinding.tvNombre.text = programa.nombre
            itemBinding.tvCorreo.text = programa.correo
            itemBinding.tvTelefono.text = programa.telefono
            itemBinding.tvWeb.text = programa.web

            //mostrar la imgane del lugar en el card

            //activa el click para modificar un lugar
            itemBinding.vistaFila.setOnClickListener{
                val accion=LugarFragmentDirections
                    .actionNavLugarToUpdateLugarFragment(programa)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    //se crea la caja de informacion
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val itemBinding=LugarFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LugarViewHolder(itemBinding)
    }
    //se diseña la cajita con la informacion
    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
      val lugar = lista[position]
      holder.dibuja(lugar)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setData(lugares : List<Programa>){
        lista=lugares
        notifyDataSetChanged()
    }
}
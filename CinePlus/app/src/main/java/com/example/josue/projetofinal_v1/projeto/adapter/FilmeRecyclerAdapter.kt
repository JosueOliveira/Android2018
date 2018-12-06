package com.example.josue.projetofinal_v1.projeto.adapter

import android.content.Context
import android.media.Image
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.josue.projetofinal_v1.R
import com.example.josue.projetofinal_v1.Utils.Utils
import com.example.josue.projetofinal_v1.projeto.db.Filme
import kotlinx.android.synthetic.main.activity_item_lista_filme.view.*
import kotlinx.android.synthetic.main.activity_novo_filme.view.*


class FilmeRecyclerAdapter internal constructor(context: Context) :
RecyclerView.Adapter<FilmeRecyclerAdapter.ViewHolder>(){

    var onItemClick: ((Filme) -> Unit)? = null

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var filmes = emptyList<Filme>()

    // infla o layout do item da lista para cada componente da lista
    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val view = inflater.inflate(R.layout.activity_item_lista_filme, holder, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = filmes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = filmes[position]
        holder.nameFilme.text = current.nome
        val bitmap = Utils.getImage(current.data)
        holder.imgFilme.setImageBitmap(bitmap)

    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameFilme: TextView = itemView.txtFilmeNome
        val imgFilme: ImageView = itemView.imgListaFilme
        init{
            itemView.setOnClickListener{
                onItemClick?.invoke(filmes[adapterPosition])

            }
        }
    }



    fun setFilmeList(filmeList: List<Filme>){
        this.filmes = filmeList
        notifyDataSetChanged()
    }

}
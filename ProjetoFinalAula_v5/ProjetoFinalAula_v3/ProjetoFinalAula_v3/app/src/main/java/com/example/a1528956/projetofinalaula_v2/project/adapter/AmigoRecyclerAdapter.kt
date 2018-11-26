package com.example.a1528956.projetofinalaula_v2.project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.a1528956.projetofinalaula_v2.R
import com.example.a1528956.projetofinalaula_v2.R.id.txtFriendLisName
import com.example.a1528956.projetofinalaula_v2.project.db.Friend
import kotlinx.android.synthetic.main.item_lista_amigo.view.*
import java.text.FieldPosition

class AmigoRecyclerAdapter internal constructor(context:Context) :
RecyclerView.Adapter<AmigoRecyclerAdapter.ViewHolder>(){

    private val inflater:LayoutInflater = LayoutInflater.from(context)
    private var amigos = emptyList<Friend>()

    //infla o layout para cada componete da view
    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val view:View = inflater.inflate(R.layout.item_lista_amigo, holder, false)
        return ViewHolder(view)
    }
    //retorna o tamanho da view
    override fun getItemCount() = amigos.size
    //colocando itens da lista nos itens de view lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = amigos[position]
        holder.nameFriend.text = current.nome

    }

    //cria sub class
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //val nameFriend: TextView = itemView.txtFriendLisName
        val nameFriend: TextView = itemView.txtFriendLisName

    }
    //lista temporaria
    /*private fun lista(): List<String>{
        return listOf(
                "Mario",
                "Roberto",
                "Allana"
        )
    }*/

    fun setFriendList(friendList: List<Friend>){
        this.amigos = friendList
        notifyDataSetChanged()

    }

}
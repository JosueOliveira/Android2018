package com.example.a1528956.projetofinalaula_v2.project.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.a1528956.projetofinalaula_v2.R
import com.example.a1528956.projetofinalaula_v2.project.adapter.AmigoRecyclerAdapter
import kotlinx.android.synthetic.main.activity_lista_amigo.*

class ListaAmigoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_amigo)


        val recyclerView:RecyclerView = rvListaAmigos
        val adapter = AmigoRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //ou
        //recyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
    }
}

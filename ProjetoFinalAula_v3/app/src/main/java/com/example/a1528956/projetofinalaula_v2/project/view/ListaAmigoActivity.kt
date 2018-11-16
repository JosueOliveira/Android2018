package com.example.a1528956.projetofinalaula_v2.project.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.a1528956.projetofinalaula_v2.R
import com.example.a1528956.projetofinalaula_v2.project.adapter.AmigoRecyclerAdapter
import com.example.a1528956.projetofinalaula_v2.project.db.Friend
import com.example.a1528956.projetofinalaula_v2.project.viewmodel.FriendViewModel
import kotlinx.android.synthetic.main.activity_lista_amigo.*

class ListaAmigoActivity : AppCompatActivity() {

    private lateinit var friendViewModel: FriendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_amigo)

        //val friend = Friend(1, "Juliana")




        val recyclerView:RecyclerView = rvListaAmigos
        val adapter = AmigoRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //ou
        //recyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        friendViewModel = ViewModelProviders.of(this).get(FriendViewModel::class.java)


        friendViewModel.allFriends.observe(this, Observer { friends ->

            friends?.let { adapter.setFriendList(it) }
        })
    }
}

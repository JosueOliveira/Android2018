package com.example.josue.projetofinal_v1.projeto.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.josue.projetofinal_v1.R
import com.example.josue.projetofinal_v1.projeto.adapter.FilmeRecyclerAdapter
import com.example.josue.projetofinal_v1.projeto.db.Filme
import com.example.josue.projetofinal_v1.projeto.viewmodel.FilmeViewModel
import kotlinx.android.synthetic.main.activity_lista_filme.*

class ListaFilmeActivity : AppCompatActivity() {


    private lateinit var filmeViewModel: FilmeViewModel

    private val requestCodeAddFilme = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filme)

        fIncluirNovoFilme.setOnClickListener{
            val intent = Intent(this@ListaFilmeActivity, NovoFilmeActivity::class.java)
            startActivityForResult(intent,requestCodeAddFilme )
        }
    }

    override fun onStart() {
        super.onStart()
       CarregarReciclyView()

    }

    override fun onResume() {
        super.onResume()
        CarregarReciclyView()
    }

    fun CarregarReciclyView() {
        val recyclerView = rvListaFilmes
        val adapter = FilmeRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        adapter.onItemClick = {
            val intent = Intent(this@ListaFilmeActivity, NovoFilmeActivity::class.java)
            intent.putExtra(NovoFilmeActivity.EXTRA_REPLAY, it)//it variável do objeto
            startActivityForResult(intent, requestCodeAddFilme)
        }

        //atualiza a lista
        filmeViewModel = ViewModelProviders.of(this).get(FilmeViewModel::class.java)

        filmeViewModel.allFilmes.observe(this, Observer { filmes ->

            filmes?.let { adapter.setFilmeList(it) }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == requestCodeAddFilme && resultCode == Activity.RESULT_OK){
            data.let {

                try {

                    val filme: Filme = data?.getSerializableExtra(NovoFilmeActivity.EXTRA_REPLAY) as Filme

                    if(filme.id > 0)
                        filmeViewModel.update(filme)
                    else
                        filmeViewModel.insert(filme)

                }catch (e:Exception){
                        val filme: Filme = data?.getSerializableExtra(NovoFilmeActivity.EXTRA_DELETE) as Filme

                        filmeViewModel.delete(filme)
                }
            }
        }
    }

}

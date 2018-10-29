package com.example.a1528956.aulaprojeto4

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_fgts.*

class FGTSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fgts)
        //habiliata btn voltar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnCalcularSeguro.setOnClickListener {
            val fgts = ((etxSalarioBruto.text.toString().toDouble() * 0.8) * etxMesesTrabalhados.text.toString().toDouble()).toString()
            txtResultado.text =  fgts
        }

        btnVerFonte?.setOnClickListener  {
            val uris = Uri.parse("https://calculofgts.net/")
            val intents = Intent(Intent.ACTION_VIEW, uris)
            startActivity(intents)
        }



    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if(item?.itemId == android.R.id.home){
            finish()
            true
        }else{
            super.onOptionsItemSelected(item)
        }
    }
}

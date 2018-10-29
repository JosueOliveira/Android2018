package com.example.a1528956.aulaprojeto4

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_fgts.*
import kotlinx.android.synthetic.main.activity_inss.*
import kotlin.system.exitProcess

class InssActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inss)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        xSalario.setOnClickListener{ txtSalario.text.clear()}

        btnFonteInss.setOnClickListener{
            val uris = Uri.parse("http://www.calculoexato.net/como-calcular-inss/")
            val intents = Intent(Intent.ACTION_VIEW, uris)
            startActivity(intents)
        }

        btnCalcularInss.setOnClickListener{
            if(txtSalario.text.isNullOrEmpty()){
                txtResultadoInss.text = ""
                Toast.makeText(this, "Necessário informar o salário", Toast.LENGTH_LONG).show()

            }else{

            val salario = txtSalario.text.toString().toDouble()
            if(salario < 5645.81){
                if(salario < 2822.91){
                    if(salario < 1693.73){
                        val resultado = salario * 0.09
                        txtResultadoInss.text = "Inss do mês:R$ " + "%.2f".format(salario * 0.09)
                    }else
                    {
                        txtResultadoInss.text = "Inss do mês:R$ " + "%.2f".format(salario * 0.09)
                    }
                }else{
                    txtResultadoInss.text = "Inss do mês:R$ " + "%.2f".format(salario * 0.11)
                }
                }else{
                    txtResultadoInss.text = "Teto máximo para  2018 é 5.645,80"
                }
            }
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

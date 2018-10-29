package com.example.a1528956.aulaprojeto4

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_seg_desemprego.*

class SegDesempregoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seg_desemprego)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        xQtdAcionou.setOnClickListener { txtQtdAcionado.text.clear() }
        xAntepenulitmo.setOnClickListener { txtAntePenultimoSl.text.clear() }
        xPenultimoSl.setOnClickListener { txtPenultimoSl.text.clear() }
        xUtlimoSl.setOnClickListener { txtUltimoSl.text.clear() }
        xMesesTrabalhados.setOnClickListener{ txtMesesTrabalhados.text.clear() }

        btnFonteSegDes.setOnClickListener{
            val uris = Uri.parse("https://www.calcule.net/trabalhista/calculo-seguro-desemprego/")
            val intents = Intent(Intent.ACTION_VIEW, uris)
            startActivity(intents)
        }

        btnCalcularSeguro.setOnClickListener {

            var receber : Boolean = true
            if (txtAntePenultimoSl.text.isNullOrEmpty() || txtPenultimoSl.text.isNullOrEmpty() || txtUltimoSl.text.isNullOrEmpty()
                    || txtMesesTrabalhados.text.isNullOrEmpty() || txtQtdAcionado.text.isNullOrEmpty()) {
                resultadoParcelas.text = ""
                resultadoValor.text = ""
                Toast.makeText(this, "Necessário informar todos os campos", Toast.LENGTH_LONG).show()
            }
            else
            {

                var antePenultimoSalario = txtAntePenultimoSl.text.toString().toDouble()
                val penultimoSalario = txtPenultimoSl.text.toString().toDouble()
                val ultimoSalario = txtUltimoSl.text.toString().toInt()
                val qtdTrabalhado = txtMesesTrabalhados.text.toString().toInt()
                val qtdAcionou = txtQtdAcionado.text.toString().toInt()

                //primeira
                if (qtdAcionou == 0) {
                if (qtdTrabalhado >= 12) {
                    if (qtdTrabalhado < 24) {
                        resultadoParcelas.text = "Receberá " + 4.toString() + " parcelas."
                    } else {
                        resultadoParcelas.text = "Receberá " + 5.toString() + " parcelas."
                    }
                } else {
                    resultadoParcelas.text = "Não atingiu o tempo mínimo de 12 meses trabalhados."
                    receber = false
                }
            }
                if (qtdAcionou == 1) {
                if (qtdTrabalhado < 24) {
                    if (qtdTrabalhado >= 12) {
                        resultadoParcelas.text = "Receberá " + 4.toString() + " parcelas."
                    } else {
                        if (qtdTrabalhado >= 9) {
                            resultadoParcelas.text = "Receberá " + 3.toString() + " parcelas."
                        } else {
                            resultadoParcelas.text = "Não atingiu o tempo mínimo de 9 meses trabalhados."
                            receber = false
                        }
                    }
                } else {
                    resultadoParcelas.text = "Receberá " + 5.toString() + " parcelas."
                }
            }
                if (qtdAcionou >= 2) {
                if (qtdTrabalhado < 24) {
                    if (qtdTrabalhado >= 12) {
                        resultadoParcelas.text = "Receberá " + 4.toString() + " parcelas."
                    } else {
                        if (qtdTrabalhado >= 6) {
                            resultadoParcelas.text = "Receberá " + 3.toString() + " parcelas."
                        } else {
                            resultadoParcelas.text = "Não atingiu o tempo mínimo de 9 meses trabalhados."
                            receber = false
                        }
                    }
                } else {
                    resultadoParcelas.text = "Receberá " + 5.toString() + " parcelas."
                }
            }

            if(receber){
                    val media = (antePenultimoSalario + penultimoSalario + ultimoSalario) / 3
                    if (media < 2467.33) {
                    if (media < 1480.26) {
                        resultadoValor.text = "Valor a Receber:R$ " + "%.2f".format(media * 0.80)
                    } else {
                        resultadoValor.text = "Valor a Receber:R$ " + "%.2f".format(media * 0.50)
                    }
                    } else {
                        resultadoValor.text = "Valor a Receber:R$ 1.677,74"
                    }
                }else{
                resultadoValor.text = ""
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

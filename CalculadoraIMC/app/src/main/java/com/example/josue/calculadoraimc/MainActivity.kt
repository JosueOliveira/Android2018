package com.example.josue.calculadoraimc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    var resultado:Double = 0.0
    var peso:Double = 0.0
    var altura:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sbIdade.setOnSeekBarChangeListener(

                object : SeekBar.OnSeekBarChangeListener{

                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        txtIdade.text = "Idade " + (progress.toString()) + " Anos"
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        txtIdade.text = "Idade " + seekBar?.progress.toString() + " Anos"
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        txtIdade.text = "Idade " + seekBar?.progress.toString() + " Anos"
                    }
                }
        )

        sbPeso.setOnSeekBarChangeListener(

                object : SeekBar.OnSeekBarChangeListener{

                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        txtPeso.text = "Peso " + (progress.toString()) + " Kg"
                        calcularImc()
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        txtPeso.text = "Peso " + seekBar?.progress.toString() + " Kg"
                        calcularImc()
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        txtPeso.text = "Peso " + seekBar?.progress.toString() + " Kg"
                        calcularImc()
                    }
                }
        )

        sbAltura.setOnSeekBarChangeListener(

                object : SeekBar.OnSeekBarChangeListener{

                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        txtAltura.text = "Altura " + (progress.toString()) + " cm"
                        calcularImc()
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        txtAltura.text = "Altura " + seekBar?.progress.toString() + " cm"
                        calcularImc()
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        txtAltura.text = "Altura " + seekBar?.progress.toString() + " cm"
                        calcularImc()
                    }
                }
        )

        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rbSedentario -> calcularImc()
                R.id.rbModerado -> calcularImc()
                R.id.rbIntenso -> calcularImc()
            }
        }

        sMulher?.setOnClickListener{
            if(sMulher.isChecked){
                txtSexo.text = "Homem"
            }else{
                txtSexo.text = "Mulher"
            }
        }


    }

    private fun calcularImc(){

        peso = sbPeso.progress.toDouble()
        altura = sbAltura.progress.toDouble() / 100

        if(peso > 0 && altura > 0){

            resultado = peso / (altura * altura)

            if(resultado < 18.5) {
                if(resultado < 16.9){
                    txtResultado.text = "Seu IMC é " + resultado.toString() + "\n Portanto você está muito abaixo do peso."
                    if(rbSedentario.isChecked)
                    {
                        rbStatus.rating = 0.toFloat()
                    }
                    else if(rbModerado.isChecked){
                        rbStatus.rating = 1.toFloat()
                    }else{
                        rbStatus.rating = 2.toFloat()
                    }

                }else{
                    txtResultado.text = "Seu IMC é " + resultado.toString() + "\n Portanto você está abaixo do Peso. "
                    if(rbSedentario.isChecked)
                    {
                        rbStatus.rating = 0.toFloat()
                    }
                    else if(rbModerado.isChecked){
                        rbStatus.rating = 1.toFloat()
                    }else{
                        rbStatus.rating = 2.toFloat()
                    }
                }
            }
            else if(resultado <= 24.9) {
                txtResultado.text = "Seu IMC é " + resultado.toString() + "\n Portanto você está no peso ideal"
                if(rbSedentario.isChecked)
                {
                    rbStatus.rating = 0.toFloat()
                }
                else if(rbModerado.isChecked){
                    rbStatus.rating = 4.toFloat()
                }else if(rbIntenso.isChecked){
                    rbStatus.rating = 5.toFloat()
                }
            }
            else if(resultado <= 29.9) {
                txtResultado.text = "Seu IMC é " + resultado.toString() + "\n Portanto você está acima do Peso"
                if(rbSedentario.isChecked)
                {
                    rbStatus.rating = 0.toFloat()
                }
                else if(rbModerado.isChecked){
                    rbStatus.rating = 3.toFloat()
                }else if(rbIntenso.isChecked){
                    rbStatus.rating = 4.toFloat()
                }
            }
            else if(resultado <= 34.9) {
                txtResultado.text = "Seu IMC é " + resultado.toString() + "\n Portanto você está com o besidade Grau I"
                if(rbSedentario.isChecked)
                {
                    rbStatus.rating = 0.toFloat()
                }
                else if(rbModerado.isChecked){
                    rbStatus.rating = 2.toFloat()
                }else if(rbIntenso.isChecked){
                    rbStatus.rating = 3.toFloat()
                }
            }
            else if(resultado <= 40){
                txtResultado.text = "Seu IMC é " + resultado.toString() + "\n Portanto você está com obesidade Grau II"
                if(rbSedentario.isChecked)
                {
                    rbStatus.rating = 0.toFloat()
                }
                else if(rbModerado.isChecked){
                    rbStatus.rating = 1.toFloat()
                }else if(rbIntenso.isChecked){
                    rbStatus.rating = 2.toFloat()
                }
            }else{
                txtResultado.text = "Seu IMC é " + resultado.toString() + "\n Portanto você está com obesidade Grau III"
                if(rbSedentario.isChecked)
                {
                    rbStatus.rating = 0.toFloat()
                }
                else if(rbModerado.isChecked){
                    rbStatus.rating = 1.toFloat()
                }else if(rbIntenso.isChecked){
                    rbStatus.rating = 2.toFloat()
                }
            }
        }else{
            txtResultado.text = ""
        }
    }
}

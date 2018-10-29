package com.example.a1528956.projeto5

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ver_pets.*

class VerPetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_pets)

        txtTelefone.setOnClickListener{
            makePhoneCall(txtTelefone.text.toString())
        }

        txtTelefone.setOnLongClickListener{
            sendSMS(txtTelefone.text.toString())
        }

        txtemail.setOnClickListener{
            sendEmail(txtemail.text.toString())
        }

        txtSite.setOnClickListener{
            openSite("http://www.google.com")
        }

    }
    //subir menu na tela activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //carregar item de layout ( inflate )
        menuInflater.inflate(R.menu.menu_edit_pet, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menuEditarPet ->{
                val intent = Intent(this, CadastraPetActivity::class.java)
                startActivity(intent)
                return  true
            }

            R.id.menuPetExclui -> {
                Toast.makeText(this, "colocar método excluir", Toast.LENGTH_LONG).show()
                return  true
            }

            R.id.menuPetFavorito -> {
                Toast.makeText(this, "colocar método favorito", Toast.LENGTH_LONG).show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)

        }
        return super.onOptionsItemSelected(item)
    }
}

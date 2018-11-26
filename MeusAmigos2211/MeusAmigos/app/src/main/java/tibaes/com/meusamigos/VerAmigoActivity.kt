package tibaes.com.meusamigos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ver_amigo.*

class VerAmigoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_amigo)

        
        // ação no clique
        txtTelefone.setOnClickListener {
            makePhoneCall(txtTelefone.text.toString())
        }

        // ação ao clique longo
        txtTelefone.setOnLongClickListener {
            sendSMS(txtTelefone.text.toString())
        }

        // ação para abrir o app de envio de e-mail
        txtEmail.setOnClickListener {
            sendEmail(txtEmail.text.toString())
        }

        // ação para entrar em um site
        txtFace.setOnClickListener {
            openSite("https://www.facebook.com/julianatibaes")
        }
    }

    // subir o menu na tela da activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_amigo, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menuAmigoEditar ->{
                val intent = Intent(this,
                        CadatraAmigoActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuAmigoExclui -> {
                Toast.makeText(this, "colocar método de excluir",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menuAmigoFavorito -> {
                Toast.makeText(this, "colocar método de favorito",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }
}

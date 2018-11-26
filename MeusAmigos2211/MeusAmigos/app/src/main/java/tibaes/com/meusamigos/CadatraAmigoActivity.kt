package tibaes.com.meusamigos

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadatra_amigo.*

class CadatraAmigoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadatra_amigo)

        fAddAmigo.setOnClickListener {
            getPermissionImagemGaleria()
        }
    }

    private fun getPermissionImagemGaleria() {
        // verificar se a versão é maior ou igual a API M, esse código
        // só funciona a partir dessa versão
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // verificar se tenho permissão.
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                // permissão negada
                // verificar se a pessoa permite acessar os arquivos
                val permission =
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permission, REQUEST_IMAGE_GALLERY)
            }
            else{
                adicionarFotoAmigo()
            }
        } else {
            Toast.makeText(this,
                    "Não suportado para a versão do seu S.O..",
                    Toast.LENGTH_LONG).show()
        }
    }

    private fun adicionarFotoAmigo() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY)
    }

    companion object {
        private val REQUEST_IMAGE_GALLERY = 1000
        private val PERMISSION_CODE_GALERY = 1001
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE_GALERY -> {
                if(grantResults.size > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    adicionarFotoAmigo()
                else Toast.makeText(this,
                        "Permissão negada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK &&
                requestCode == REQUEST_IMAGE_GALLERY)
            imgNovoAmigo.setImageURI(data?.data)
    }
}

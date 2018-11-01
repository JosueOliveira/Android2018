package com.example.a1528956.projeto5

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastra_pet.*
import java.util.jar.Manifest

class CadastraPetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastra_pet)
        faddPet.setOnClickListener{
            getPermissionGaleria()
        }


    }

    private fun getPermissionGaleria() {
        //verificar se a versão é maior ou igaul a api desse coidgo
        //só funciona a partir
        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.M){
            //verificar se tenho permissão
            if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                //se permissão negada
                //so0licita ao user permission
                val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permission, REQUEST_IMAGE_GALLERY)
            }else{
                adicionarPet()
            }

        }else{
            Toast.makeText(this, "Não suportado nesta versão.", Toast.LENGTH_LONG).show()
        }
    }

    private fun adicionarPet(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.type = Context.CAMERA_SERVICE
        intent.type = Context.BATTERY_SERVICE
        //Metodo (startActivityForResult) abre actiovity e espera um result
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY)

    }

    companion object {
        private val REQUEST_IMAGE_GALLERY = 1000
        private val PERMISSION_CODE_GALLERY = 1001
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode)
        {
            PERMISSION_CODE_GALLERY -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    adicionarPet()
                }else{
                    Toast.makeText(this, "Permissão negada.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_GALLERY){
            imgPet.setImageURI(data?.data)
        }
    }
}

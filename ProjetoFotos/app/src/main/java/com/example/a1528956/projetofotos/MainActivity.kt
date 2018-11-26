package com.example.a1528956.projetofotos

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.ResultReceiver
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val REQUEST_IMAGE_CAPTURE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener{

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED || checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    requestPermissions(permission, REQUEST_IMAGE_CAPTURE)
                }else{
                    takePicture()
                }
            }else{
                Toast.makeText(this@MainActivity, "Versão não suportada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK &&
                requestCode == REQUEST_IMAGE_CAPTURE){
            imageView.setImageURI(data?.data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_IMAGE_CAPTURE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    takePicture()
                else
                    Toast.makeText(this@MainActivity, "Permissão negada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun takePicture(){
        val values = ContentValues()

        values.put(MediaStore.Images.Media.TITLE,"nova imagem")
        values.put(MediaStore.Images.Media.DESCRIPTION,"imagen camera")
        values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpg")

        val image_uri = contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if(intent.resolveActivity(packageManager) != null){

            //adiciona o caminho da image uri
            val pathImage = image_uri.toString()
            //enviar para a inten do tipo extra output a image uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)

            //adicionar as flags de verificação de permissão
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
            or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        }

        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

    }
}

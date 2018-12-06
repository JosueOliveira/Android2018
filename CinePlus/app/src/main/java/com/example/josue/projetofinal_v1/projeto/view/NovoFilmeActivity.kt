package com.example.josue.projetofinal_v1.projeto.view

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.josue.projetofinal_v1.R
import com.example.josue.projetofinal_v1.Utils.Utils
import com.example.josue.projetofinal_v1.projeto.db.Filme
import kotlinx.android.synthetic.main.activity_novo_filme.*
import java.io.*
import java.util.*

class NovoFilmeActivity : AppCompatActivity() {

    var categoriaSelecionada: Int = 0
    var urlImage: String = ""
    var nome_image: String = ""
    lateinit var filme: Filme
    var newObject: Boolean = false
    private val channelId = "cineplus.com"//canal para utilizar notificação utilzado a partir do Oreo
    private var notificationManager: NotificationManager? = null


    companion object {
        const val EXTRA_REPLAY = "view.REPLY"
        const val EXTRA_DELETE = "view.Delete"
        private val REQUEST_IMAGE_GALLERY = 1000
        private val PERMISSION_CODE_GALERY = 1001
        private val IMAGE_DIRECTORY = "/CinePlus"
        private val ImageCode = 1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_filme)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        var categorias = arrayOf("Ação","Terror", "Suspense", "Comédia", "Série")

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categorias)

        sCategoria.adapter = adapter

        sCategoria.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                categoriaSelecionada = 0
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedItem = parent!!.getItemAtPosition(position).toString()

                when(position){
                    0 -> {
                        categoriaSelecionada = 0
                    }
                    1 -> {
                        categoriaSelecionada = 1
                    }
                    2 -> {
                        categoriaSelecionada = 2
                    }
                    3->{
                        categoriaSelecionada = 3
                    }
                    4->{
                        categoriaSelecionada = 4
                    }
                }
            }

        }

        faddImageFilme?.setOnClickListener{
            getPermissionImagemGaleria()
        }


        val intent : Intent = getIntent()

        try{
            filme = intent.getSerializableExtra(EXTRA_REPLAY) as Filme
            filme.let {
                txtNomeFilme.setText(filme.nome)
                txtSinopse.setText(filme.sinopse)
                val bitmap = Utils.getImage(filme.data)
                imgFilme.setImageBitmap(bitmap)
                sCategoria.setSelection(filme.categoria)

                newObject = false
            }

        }catch (e: Exception){
            newObject = true
        }

    }

    private fun getPermissionImagemGaleria(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                val permission =
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permission, REQUEST_IMAGE_GALLERY)
            }else{
                adicionarFilme()
            }
        }else{
            Toast.makeText(this,
                    "Não suportado para a versão do seu S.O..",
                    Toast.LENGTH_LONG).show()
        }
    }

    private fun adicionarFilme(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY)
    }

    //habilita opões do menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //carregar item de layout ( inflate )
        menuInflater.inflate(R.menu.menu_edit_filme, menu)

        try {
            filme.let {
                val menuItem = menu?.findItem(R.id.menu_filme_delete)
                menuItem?.isVisible = true
            }
        }catch (e:Exception){
            val menuItem = menu?.findItem(R.id.menu_filme_delete)
            menuItem?.isVisible = false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            PERMISSION_CODE_GALERY -> {
                if(grantResults.size > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_DENIED)
                    adicionarFilme()
                else Toast.makeText(this,
                        "Permissão negada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_GALLERY){

            imgFilme.setImageURI(data?.data)
        }
    }

    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
                (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {

            wallpaperDirectory.mkdirs()
        }

        try
        {
            Log.d("heel",wallpaperDirectory.toString())

            nome_image = (Calendar.getInstance().getTimeInMillis()).toString()

            val f = File(wallpaperDirectory, (nome_image + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                    arrayOf(f.getPath()),
                    arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){

            R.id.menu_salvar -> {

                if(imgFilme.drawable == null){
                        Toast.makeText(this, "Necessário selecionar uma imagen", Toast.LENGTH_LONG).show()
                }else{

                nome_image = (Calendar.getInstance().getTimeInMillis()).toString()

                val bitmap = (imgFilme.drawable as BitmapDrawable).bitmap

                if(!(::filme.isInitialized)){
                    filme = Filme(
                            nome = txtNomeFilme.text.toString(),
                            categoria = categoriaSelecionada,
                            sinopse = txtSinopse.text.toString(),
                            urlImage = nome_image,
                            data = Utils.getBytes(bitmap)
                    )
                    notificacao(channelId, filme.nome, "Adicionado")
                }else{
                    filme.nome = txtNomeFilme.text.toString()
                    filme.categoria = categoriaSelecionada
                    filme.sinopse = txtSinopse.text.toString()
                    filme.urlImage = imgFilme.toString()
                    filme.data = Utils.getBytes(bitmap)
                }

                var replyIntent = Intent()

                replyIntent.putExtra(EXTRA_REPLAY, filme)

                setResult(Activity.RESULT_OK, replyIntent)

                finish()
                }
            }

            R.id.menu_filme_delete ->{
                 val replyIntent = Intent()
                replyIntent.putExtra(EXTRA_DELETE, filme)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
                return  true
            }

            else -> return super.onOptionsItemSelected(item)

        }
        return super.onOptionsItemSelected(item)
    }

    fun notificacao(id: String = "", name: String = "", descricao: String = ""){


        val pendingIntent = PendingIntent.getActivity(this,0, intent, 0)

        val mNotification = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //código novo
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, name, importance)
            channel.description = descricao
            channel.enableLights(true)
            channel.lightColor = Color.BLUE
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

            notificationManager?.createNotificationChannel(channel)

            Notification.Builder(this, channelId)
        }else {
            Notification.Builder(this)
        }.apply {
            //código antigo
                setContentIntent(pendingIntent)
                setSmallIcon(R.drawable.notification_icon_background)
                setAutoCancel(true)
                setContentTitle(filme.nome)
                setContentText("Adicionado")
            }.build()
            val mNotificationId: Int = 1000
            val nManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.notify(mNotificationId, mNotification as Notification)

    }
}

package com.example.a1528956.projetofinalaula_v2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.a1528956.projetofinalaula_v2.project.db.Friend
import kotlinx.android.synthetic.main.activity_novo_amigo.*

class ActivityNovoAmigo : AppCompatActivity() {

    companion object {
        private val REQUEST_IMAGE_GALLERY = 1000
        private val REQUEST_IMAGE_CAPTURE = 2000
        const val EXTRA_REPLY = "view.REPLY"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_amigo)

        fAddFriend?.setOnClickListener{

            val friend = Friend(
                    nome = etNome.toString()
            )
            val replyIntent = Intent()
            replyIntent.putExtra(ActivityNovoAmigo.EXTRA_REPLY, friend)
            setResult(Activity.RESULT_OK, replyIntent)

            finish()
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

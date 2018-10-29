package com.example.josue.projeto3

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val channelId = "smartFilmes.com"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var filmes = arrayOf("Maze Runner: Correr ou Morrer", "A Maldição da Residência Hill", "It: A Coisa", "Pearl Harbor", "A Morte e Vida de Charlie")

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, filmes)

        sFilmes.adapter = adapter

        sFilmes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                txtResumo.text = ""
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {

                val selectedItem = parent!!.getItemAtPosition(position).toString()

                when(position){
                    0 -> { txtResumo.text = ("\n" + selectedItem + "\n" + "\n" +
                            "Em um futuro, meio apocalíptico, meio utópico, o jovem Thomas é escolhido para " +
                            "enfrentar o sistema. Ao acordar dentro de um escuro elevador em movimento, " +
                            "ele não consegue se lembrar nem de seu nome. Na comunidade isolada em que " +
                            "foi abandonado, ele conhece outros garotos que passaram pelo mesmo. Para " +
                            "conseguir escapar, Thomas precisa descobrir os sombrios segredos guardados " +
                            "em sua mente e correr muito." + "\n" + "\n" + "\n")
                    imgFilme.visibility = View.VISIBLE
                        imgFilme.setImageResource(R.drawable.mazerunner)
                    notificacao()

                }
                1 -> {
                    txtResumo.text = ("\n" + selectedItem + "\n" + "\n" +
                            "A família Crain se muda para a Residência Hill com o objetivo de " +
                            "reformá-la e, após alguns poucos meses, revendê-la ganhando um bom " +
                            "lucro em cima. No entanto, cada noite se revela um desafio para o " +
                            "casal e seus cinco filhos, graças à aparição de fantasmas e outras " +
                            "alucinações. Vinte anos depois, os membros da família, já adultos, " +
                            "se reúnem mais uma vez para lidarem com as consequências do tempo " +
                            "em que viveram na mansão, uma lembrança que os persegue até hoje." +
                            "\n" + "\n" + "\n")
                    imgFilme.visibility = View.VISIBLE
                    imgFilme.setImageResource(R.drawable.amaldicao)
                    notificacao()
                }
                2 -> {
                    txtResumo.text = ("\n" + selectedItem + "\n" + "\n" +
                            "Um grupo de sete adolescentes de Derry, uma cidade no Maine, formam" +
                            " o auto-intitulado \"Losers Club\" - o clube dos perdedores. A pacata " +
                            "rotina da cidade é abalada quando crianças começam a desaparecer e tudo " +
                            "o que pode ser encontrado delas são partes de seus corpos. Logo, os " +
                            "integrantes do \"Losers Club\" acabam ficando face a face com o " +
                            "responsável pelos crimes: o palhaço Pennywise." + "\n" + "\n" + "\n")
                    imgFilme.visibility = View.VISIBLE
                    imgFilme.setImageResource(R.drawable.acoisa)
                    notificacao()
                }
                    3 -> {
                        txtResumo.text = ("\n" + selectedItem + "\n" + "\n" +
                                "Dois pilotos e amigos de longa data se apaixonam pela mesma mulher, " +
                                "mas precisam deixar suas diferenças de lado quando os japoneses " +
                                "atacam a base naval de Pearl Harbor em 7 de dezembro de 1941." +
                                "\n" + "\n" + "\n")

                        imgFilme.visibility = View.VISIBLE
                        imgFilme.setImageResource(R.drawable.pearlharbor)
                        notificacao()
                    }
                    4 -> {
                        txtResumo.text = ("\n" + selectedItem + "\n" + "\n" +
                                "Adorado pela mãe e pelo irmãozinho Sam, Charlie St. " +
                                "Cloud é um velejador de sucesso e universitário com um futuro " +
                                "brilhante. Quando Sam morre em um um terrível acidente, os sonhos " +
                                "de Charlie também morrem. Mas a ligação entre os dois irmãos é tão " +
                                "forte que, todo entardecer, eles se encontram. A volta de uma ex-colega " +
                                "de escola obriga Charlie a tomar uma decisão difícil: permanecer preso" +
                                " ao passado ou deixar que o amor lhe mostre o futuro." +
                                "\n" + "\n" + "\n")
                        imgFilme.visibility = View.VISIBLE
                        imgFilme.setImageResource(R.drawable.charlie)
                        notificacao()
                    }
                }
            }
        }
    }

    fun notificacao(){
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val mNotification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, channelId)
        } else {
            Notification.Builder(this)
        }.apply {
            setContentIntent(pendingIntent)
            // adicionando um ícone na notificação
            setSmallIcon(R.drawable.notification_icon_background)
            setAutoCancel(true)
            // título da notificação
            setContentTitle(sFilmes.selectedItem.toString())
            // mensagem da notificação
            setContentText("Confira a sinopse de " + sFilmes.selectedItem.toString())
        }.build()
        val mNotificationId: Int = 1000
        val nManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nManager.notify(mNotificationId, mNotification)
    }


}

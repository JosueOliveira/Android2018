package tibaes.com.meusamigos

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.makePhoneCall(number: String) : Boolean{
    try {
        // preparar a intent com o número do telefone
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        // iniciar a atividade
        startActivity(intent)
        return true
    } catch (e: Exception){
        // ver o erro
        e.printStackTrace()
        return false
    }
}

fun Context.sendSMS(number: String) : Boolean{
    try {
        // preparar a intent com o número do telefone
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.fromParts("sms", number, null))
        // iniciar a atividade
        startActivity(intent)
        return true
    } catch (e: Exception){
        // ver o erro
        e.printStackTrace()
        return false
    }
}

fun Context.sendEmail(email: String) : Boolean{
    try {
        // preparar a intent com o email de destinatário
        val intent = Intent(Intent.ACTION_SENDTO,
                Uri.parse("mailto:$email"))
        // iniciar a atividade
        startActivity(intent)
        return true
    } catch (e: Exception){
        // ver o erro
        e.printStackTrace()
        return false
    }
}

fun Context.openSite(site: String) : Boolean{
    try {
        // preparar a intent com o site que será aberto
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse(site))
        // iniciar a atividade
        startActivity(intent)
        return true
    } catch (e: Exception){
        // ver o erro
        e.printStackTrace()
        return false
    }
}
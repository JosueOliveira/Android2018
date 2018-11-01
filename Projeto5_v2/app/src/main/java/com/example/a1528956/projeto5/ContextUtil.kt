package com.example.a1528956.projeto5

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Telephony

fun Context.makePhoneCall(number: String): Boolean{
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(intent)
            return true
    }catch(e: Exception){
        e.printStackTrace()
        return false
    }
}

fun Context.sendSMS(number: String): Boolean{
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number ,null))
        startActivity(intent)
        return true
    }catch(e: Exception){
        e.printStackTrace()
        return false
    }
}

fun Context.sendEmail(email: String): Boolean{
    try {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
        startActivity(intent)
        return true
    }catch(e: Exception){
        e.printStackTrace()
        return false
    }
}

fun Context.openSite(site: String): Boolean{
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(site))
        startActivity(intent)
        return true
    }catch(e: Exception){
        e.printStackTrace()
        return false
    }
}
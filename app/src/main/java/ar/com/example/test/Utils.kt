package ar.com.example.test

import android.content.Context
import android.widget.Toast

fun toast(context: Context, message:String, itsLong: Boolean = false) {
    if (itsLong) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }
}

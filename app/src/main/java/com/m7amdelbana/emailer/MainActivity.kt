package com.m7amdelbana.emailer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var edtEmail: EditText
    lateinit var edtSubject: EditText
    lateinit var edtMessage: EditText
    lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()

        // TODO: Make Validation

        btnSend.setOnClickListener {
            sendEmail(
                edtEmail.text.toString().trim(),
                edtSubject.text.toString().trim(),
                edtMessage.text.toString().trim()
            )
        }
    }

    private fun initUI(){
        edtEmail = findViewById(R.id.email_editText)
        edtSubject = findViewById(R.id.subject_editText)
        edtMessage = findViewById(R.id.message_editText)
        btnSend = findViewById(R.id.send_button)
    }

    private fun sendEmail(email: String, subject: String, message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plan"

        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(intent, "Choose Email Provider."))
        } catch (e: Exception) {
            Toast.makeText(this, "No Email Provider", Toast.LENGTH_LONG).show()
        }

    }
}

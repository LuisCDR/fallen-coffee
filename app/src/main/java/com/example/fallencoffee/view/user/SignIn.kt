package com.example.fallencoffee.view.user

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Patterns.EMAIL_ADDRESS
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fallencoffee.R
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignIn : AppCompatActivity() {

    private val txtUsername = findViewById<TextView>(R.id.txtUsername)
    private val editTxtUsername = findViewById<EditText>(R.id.editTxtUsername)
    private val txtPassword = findViewById<TextView>(R.id.txtPassword)
    private val editTxtPassword = findViewById<EditText>(R.id.editTxtPassword)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val bundleSignUp: Bundle? = intent.extras
        editTxtUsername.let { it.text = bundleSignUp!!.get("editTxtEmail") as Editable? }

    }

    override fun getIntent(): Intent {
        return super.getIntent()
    }

    fun onClickSignUp(view: View) {
        val intentSignUp = Intent(this@SignIn, SignUp::class.java)
        val strUsername: String = editTxtUsername.text!!.toString()
        when (isEmail(strUsername)) {
            true -> {intentSignUp.putExtra("Email", editTxtUsername.text.toString())}
            false -> {intentSignUp.putExtra("Username", editTxtUsername.text.toString())}
        }
        startActivity(intentSignUp)
    }

    fun onClickScannerQR(view: View) {
        //val intentScannerQR = Intent(this@SignIn, )
        //startActivity(intentScannerQR)
    }

    private fun isEmail(str: String) : Boolean {
        return EMAIL_ADDRESS.matcher(str).matches()
    }
}

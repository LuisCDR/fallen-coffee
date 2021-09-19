package com.example.fallencoffee.view.user

import android.content.Intent
import android.content.Intent.getIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent.ACTION_UP
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.fallencoffee.R
import com.example.fallencoffee.db.ReadDB
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    private val read = ReadDB()
    private val editTxtUsername = findViewById<EditText>(R.id.editTxtUsername)
    private val editTxtEmail = findViewById<EditText>(R.id.editTxtEmail)
    private val editTxtPassword = findViewById<EditText>(R.id.editTxtPassword)
    private val editTxtRPassword = findViewById<EditText>(R.id.editTxtRPassword)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editTxtRPassword.setOnKeyListener { view, keyCode, keyEvent ->
            if (keyCode == KEYCODE_ENTER && keyEvent.action == ACTION_UP) {
                onClickNext(view)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
        //val inte = SignIn()
        //val i =inte.getIntent()
        //val bundle = i.extras
        //bundle.get
    }

    override fun getIntent() : Intent? {
        return super.getIntent()
    }

    fun onClickNext(view: View) {}

    fun onClickBack(view: View) {
        val intentBack = Intent(this@SignUp, SignIn::class.java)
        intentBack.putExtra("editTxtEmail", editTxtEmail.text)

        startActivity(intentBack)
    }

    fun findUser() {
        val username = editTxtUsername.text.toString()
        if (read.existUserBy(username)) {
            editTxtUsername.setText(R.string.empty)
            Toast.makeText(this@SignUp
                , "Al parecer se te adelantaron"
                , Toast.LENGTH_LONG).show()
        }
    }

    fun passwordEqual() {
        try {
            val isEquals: Boolean = editTxtPassword.text == editTxtRPassword.text
            if (!isEquals) {throw Exception("Not Equals!", Throwable()) }
        } catch (e : Exception) {
            Toast.makeText(this@SignUp, "Al repetir tu contraseña debe ser la misma", Toast.LENGTH_SHORT).show()
        } finally {
            editTxtPassword.setText(R.string.empty)
            editTxtRPassword.setText(R.string.empty)
            editTxtPassword.isFocused
        }

    }
}
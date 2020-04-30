package com.warriorwave.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.warriorwave.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName = MyName("Yo Merengues")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        // findViewById<Button>(R.id.done_button).setOnClickListener { addNickName(it) }
        // replacing the before code with this we link the activity with the layout using data-binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.doneButton.setOnClickListener { addNickName(it) }
        binding.myName = myName
    }

    private fun addNickName(view: View) {
        if ( binding.nicknameEdit.text.isNotBlank()) {
            binding.apply {
                // for our data binding var we should use null safety assigns, because the var can be nullable
                myName?.nickname = nicknameEdit.text.toString()
                invalidateAll() // recreate all the binding expressions
                nicknameText.visibility = View.VISIBLE
                nicknameEdit.visibility = View.GONE
            }
            view.visibility = View.GONE
        }
        // we hide the keyboard when the button have been pushed
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

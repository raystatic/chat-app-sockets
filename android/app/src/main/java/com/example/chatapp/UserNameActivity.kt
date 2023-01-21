package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.example.chatapp.databinding.ActivityUserNameBinding

class UserNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etUsername.doAfterTextChanged {
            val username = it.toString()
            binding.btnProceed.isEnabled = username.isNotEmpty()
        }
        binding.btnProceed.setOnClickListener {
            val username = binding.etUsername.text.toString()
            if (username.isNotEmpty()) {
                val intent = Intent(this, ChatActivity::class.java)
                intent.putExtra(ChatActivity.USERNAME, username)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.etUsername.requestFocus()
    }
}
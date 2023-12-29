package com.novandi.suitmediaapp.presentation.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.novandi.suitmediaapp.R
import com.novandi.suitmediaapp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.setNavigationOnClickListener { finish() }

        val name = intent.extras?.getString(USER_NAME)
        binding.content.tvWelcomeName.text = name.toString()

        val selectedName = intent.extras?.getString(USER_NAME_SELECTED) ?: getString(R.string.selected_user_name)
        binding.content.tvSelectedName.text = selectedName

        binding.content.btnChoose.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            val bundle = Bundle()
            bundle.putString(USER_NAME, name)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    companion object {
        const val USER_NAME = "user_name"
        const val USER_NAME_SELECTED = "user_name_selected"
    }
}
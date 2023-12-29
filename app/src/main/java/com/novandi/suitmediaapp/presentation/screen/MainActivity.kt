package com.novandi.suitmediaapp.presentation.screen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.novandi.suitmediaapp.R
import com.novandi.suitmediaapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= 21) binding.suitmediaLogo.clipToOutline = true

        setupCheckButton()
        setupNextButton()
    }

    private fun setupCheckButton() {
        binding.btnCheck.setOnClickListener {
            val palindromeText = binding.edPalindrome.text.toString()
            if (palindromeText != "") {
                val message = getString(
                    R.string.palindrome_result,
                    palindromeText,
                    if (isPalindrome(palindromeText)) "palindrome" else "not palindrome"
                )
                showDialog(getString(R.string.palindrome_check), message)
            } else showDialog(getString(R.string.warning), getString(R.string.alert_palindrome_empty))
        }
    }

    private fun setupNextButton() {
        binding.btnNext.setOnClickListener {
            val name = binding.edName.text.toString()
            if (name != "") {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                val bundle = Bundle()
                bundle.putString(HomeActivity.USER_NAME, name)
                intent.putExtras(bundle)
                startActivity(intent)
            } else showDialog(getString(R.string.warning), getString(R.string.alert_name_empty))
        }
    }

    private fun showDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this@MainActivity)
            .setTitle(title).setMessage(message)
            .show()
    }

    private fun isPalindrome(string: String): Boolean {
        return string.reversed() == string
    }
}
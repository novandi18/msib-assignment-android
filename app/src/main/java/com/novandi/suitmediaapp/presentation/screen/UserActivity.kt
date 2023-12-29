package com.novandi.suitmediaapp.presentation.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.novandi.suitmediaapp.databinding.ActivityUserBinding
import com.novandi.suitmediaapp.domain.model.UserData
import com.novandi.suitmediaapp.presentation.adapter.LoadingStateAdapter
import com.novandi.suitmediaapp.presentation.adapter.UserAdapter
import com.novandi.suitmediaapp.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private val viewModel by viewModels<UserViewModel>()
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.setNavigationOnClickListener { finish() }

        val name = intent.extras?.getString(HomeActivity.USER_NAME).toString()

        setupAdapter()
        showResults(name)

        with(binding.swipeRefresh) {
            setOnRefreshListener {
                userAdapter.refresh()
                isRefreshing = false
            }
        }
    }

    private fun setupAdapter() {
        binding.rvUser.layoutManager = LinearLayoutManager(this@UserActivity)
        binding.rvUser.adapter = userAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                userAdapter.retry()
            }
        )
    }

    private fun showResults(name: String) {
        viewModel.users.observe(this@UserActivity) { user ->
            binding.swipeRefresh.isRefreshing = false
            userAdapter.submitData(lifecycle, user)

            userAdapter.setOnItemClickListener(object : UserAdapter.OnItemClickListener {
                override fun onItemClicked(data: UserData) {
                    val intent = Intent(this@UserActivity, HomeActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString(HomeActivity.USER_NAME_SELECTED, "${data.firstName} ${data.lastName}")
                    bundle.putString(HomeActivity.USER_NAME, name)
                    intent.putExtras(bundle)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
            })
        }
    }
}
package com.amary.friends.detail.screen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amary.friends.core.observer.observeState
import com.amary.friends.data.api.model.Friends
import com.amary.friends.detail.R
import com.amary.friends.detail.databinding.ActivityDetailBinding
import com.amary.friends.detail.navigation.DetailNavigationImpl.Companion.ARGUMENT
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModels()

    @Suppress("DEPRECATION")
    private val data by lazy { intent.extras?.getSerializable(ARGUMENT) as Friends }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.updateState(data)
        observer()
    }

    private fun observer() {
        observeState(viewModel.state) { state ->
            renderDetail(
                firstName = state.firstName,
                lastName = state.lastName,
                address = state.address,
                avatar = state.avatar
            )
        }
    }

    private fun renderDetail(
        firstName: String,
        lastName: String,
        address: String,
        avatar: String
    ) {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(avatar)
                .into(imageView)

            txtFirstName.text = firstName
            txtLastName.text = lastName
            txtAddress.text = address
        }
    }
        }
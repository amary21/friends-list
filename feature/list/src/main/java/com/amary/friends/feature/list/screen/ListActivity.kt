package com.amary.friends.feature.list.screen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.amary.friends.core.observer.observeState
import com.amary.friends.data.api.model.Friends
import com.amary.friends.feature.list.databinding.ActivityListBinding
import com.amary.friends.navigation.DetailNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class ListActivity : AppCompatActivity() {

    private val viewModel: ListViewModel by viewModels()

    @Inject
    lateinit var detailNavigation: DetailNavigation

    private lateinit var binding: ActivityListBinding
    private val adapter by lazy {
        ListFriendsAdapter { friends ->
            val intent = detailNavigation.getDetailActivity(
                context = this,
                argument = bundleOf(detailNavigation.argument to friends)
            )
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
        observer()
    }

    private fun initView() {
        binding.apply {
            rvFriends.apply {
                adapter = this@ListActivity.adapter
                layoutManager =
                    LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    private fun observer() {
        observeState(viewModel.state) { state ->
            renderLoading(state.isLoading)
            renderList(state.friends)
            renderError(state.error)
        }
    }

    private fun renderLoading(loading: Boolean) {
        binding.pbLoader.isVisible = loading
    }

    private fun renderList(friends: List<Friends>) {
        adapter.submitList(friends)
        binding.rvFriends.isVisible = friends.isNotEmpty()
    }

    private fun renderError(error: String?) {
        binding.txtError.isVisible = error != null
        binding.txtError.text = error
    }
}
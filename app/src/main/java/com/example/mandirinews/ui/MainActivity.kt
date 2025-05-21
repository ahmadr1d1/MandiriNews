package com.example.mandirinews.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mandirinews.R
import com.example.mandirinews.data.di.Injection
import com.example.mandirinews.databinding.ActivityMainBinding
import com.example.mandirinews.data.di.ViewModelFactory
import com.example.mandirinews.ui.MainAdapter.Companion.VIEW_TYPE_HORIZONTAL
import com.example.mandirinews.ui.MainAdapter.Companion.VIEW_TYPE_VERTICAL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val repository = Injection.provideNewsRepository()
    private val factory = ViewModelFactory(repository)
    private val viewModel: MainViewModel by viewModels { factory }
    private lateinit var headlineNewsAdapter: MainAdapter
    private lateinit var allNewsAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerViews()

        viewModel.headlineNewsResult.observe(this) { headlines ->
            if (headlines != null) {
                val listHeadlineNews = headlines.map { it?.copy(viewType = VIEW_TYPE_HORIZONTAL) }
                headlineNewsAdapter.submitList(listHeadlineNews)
            }
        }

        viewModel.allNewsResult.observe(this) { allNews ->
            if (allNews != null) {
                val listAllNews = allNews.map { it?.copy(viewType = VIEW_TYPE_VERTICAL) }
                allNewsAdapter.submitList(listAllNews)
            }
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
        viewModel.isError.observe(this) {
            showError(it)
        }

        viewModel.getHeadlineNews()
        viewModel.getAllNews()
        setSupportActionBar(binding.toolbarHome)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupRecyclerViews() {
        headlineNewsAdapter = MainAdapter()
        binding.rvHeadlineNews.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = headlineNewsAdapter
        }

        allNewsAdapter = MainAdapter()
        binding.rvAllNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = allNewsAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this@MainActivity, "Fitur sedang dikembangkan", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(isError: Boolean) {
        if (isError) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
        }
    }
}
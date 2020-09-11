package digitalfish.omninews.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import digitalfish.omninews.R
import digitalfish.omninews.databinding.ActivityMainBinding
import digitalfish.omninews.ui.adapter.TabPagerAdapter
import digitalfish.omninews.viewmodel.MainViewModel


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel =
            ViewModelProvider(this,
                viewModelFactory { appContainer.mainViewModel.create() }).get(
                MainViewModel::class.java
            )
        binding.lifecycleOwner = this
        setObservers()
        setViewPager()
    }

    private fun setViewPager() {
        binding.viewPager.adapter = TabPagerAdapter(this, supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    private fun setObservers() {
        viewModel.errorMessageLive.observe(this, Observer { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.action_search)
        searchItem?.let {
            val searchView: SearchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.loadData(it)
                        searchView.onActionViewCollapsed()
                    }
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    //Not used in case all data is downloaded from the server
                    return false
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}
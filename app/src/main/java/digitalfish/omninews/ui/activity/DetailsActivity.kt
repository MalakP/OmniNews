package digitalfish.omninews.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import digitalfish.omninews.R
import digitalfish.omninews.databinding.ActivityDetailsBinding
import digitalfish.omninews.extras.Constants.EXTRA_DATA_TYPE
import digitalfish.omninews.extras.Constants.EXTRA_SELECTED_ITEM
import digitalfish.omninews.viewmodel.DetailsViewModel

private lateinit var binding: ActivityDetailsBinding
private lateinit var viewModel: DetailsViewModel

class DetailsActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedIdx = intent.getIntExtra(EXTRA_SELECTED_ITEM, -1)
        val dataType = intent.getIntExtra(EXTRA_DATA_TYPE, -1)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        viewModel = ViewModelProvider(this,
            viewModelFactory { appContainer.detailsViewModel.create() }).get(
            DetailsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.loadData(dataType, selectedIdx)

        displayHomeUp()
    }

    private fun displayHomeUp() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
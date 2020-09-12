package digitalfish.omninews.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import digitalfish.omninews.R
import digitalfish.omninews.databinding.FragmentDataListBinding
import digitalfish.omninews.extras.Constants.DATA_TYPE_ARTICLE
import digitalfish.omninews.extras.Constants.DATA_TYPE_TOPIC
import digitalfish.omninews.extras.Constants.EXTRA_DATA_TYPE
import digitalfish.omninews.extras.Constants.EXTRA_SELECTED_ITEM
import digitalfish.omninews.ui.activity.DetailsActivity
import digitalfish.omninews.ui.adapter.ArticleAdapter
import digitalfish.omninews.ui.adapter.TopicAdapter
import digitalfish.omninews.viewmodel.MainViewModel

class DataListFragment : Fragment() {

    private var viewModel: MainViewModel? = null
    private lateinit var binding: FragmentDataListBinding
    private lateinit var adapter:Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let { ViewModelProvider(it).get(MainViewModel::class.java) }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_data_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val type = arguments?.getInt(EXTRA_DATA_TYPE) ?: 0
        setupRecycler(type)
        setObservers(type)
        return binding.root
    }

    private fun setupRecycler(type: Int) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        when(type){
            DATA_TYPE_TOPIC -> binding.recyclerView.adapter = createTopicAdapter()
            else -> binding.recyclerView.adapter = createArticleAdapter()
        }
    }

    private fun setObservers(type: Int) {
        viewModel?.newsLive?.observe(this, Observer { news ->
            news?.let {
                when(type){
                    DATA_TYPE_TOPIC->it.topics?.let { topics -> (adapter as TopicAdapter).setItems(topics) }
                    else->it.articles?.let { articles -> (adapter as ArticleAdapter).setItems(articles) }
                }
            }
        })
    }

    private fun createArticleAdapter():ArticleAdapter {
        adapter =
            ArticleAdapter(emptyList(), object : ArticleAdapter.OnItemInteraction {
                override fun onEntryClicked(entryIdx: Int) {
                    val intent = Intent(activity, DetailsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.putExtra(EXTRA_SELECTED_ITEM, entryIdx)
                    intent.putExtra(EXTRA_DATA_TYPE, DATA_TYPE_ARTICLE)
                    startActivity(intent)
                }
            })
        return adapter as ArticleAdapter
    }

    private fun createTopicAdapter():TopicAdapter {
        adapter =
            TopicAdapter(emptyList(), object : TopicAdapter.OnItemInteraction {
                override fun onEntryClicked(entryIdx: Int) {
                    val intent = Intent(activity, DetailsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.putExtra(EXTRA_SELECTED_ITEM, entryIdx)
                    intent.putExtra(EXTRA_DATA_TYPE, DATA_TYPE_TOPIC)
                    startActivity(intent)
                }
            })
        return adapter as TopicAdapter
    }

    companion object {

        @JvmStatic
        fun newInstance(type:Int): DataListFragment {
            return DataListFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_DATA_TYPE, type)
                }
            }
        }
    }
}
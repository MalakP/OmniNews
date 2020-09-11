package digitalfish.omninews.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import digitalfish.omninews.R
import digitalfish.omninews.extras.Constants.DATA_TYPE_ARTICLE
import digitalfish.omninews.extras.Constants.DATA_TYPE_TOPIC
import digitalfish.omninews.ui.fragment.DataListFragment

private val TAB_TITLES = arrayOf(
        R.string.tab_articles,
        R.string.tab_topics
)

class TabPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> DataListFragment.newInstance(DATA_TYPE_ARTICLE)
            1 -> DataListFragment.newInstance(DATA_TYPE_TOPIC)
            else-> DataListFragment.newInstance(DATA_TYPE_ARTICLE)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}
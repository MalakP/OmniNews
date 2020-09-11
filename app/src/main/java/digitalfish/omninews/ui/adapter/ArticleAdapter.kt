package digitalfish.omninews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import digitalfish.omninews.R
import digitalfish.omninews.data.model.Article
import digitalfish.omninews.databinding.ArticleItemBinding

class ArticleAdapter(
    private var mItems: List<Article>,
    private val mListener: OnItemInteraction
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: ArticleItemBinding = DataBindingUtil.inflate(inflater, R.layout.article_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(mItems[position])

        with(holder.binding.root) {
            setOnClickListener {
                mListener.onEntryClicked(position)
            }
        }
    }

    fun setItems(newItems: List<Article>){
        mItems = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mItems.size

    inner class ViewHolder(val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article) {
            binding.item = item
            binding.image = "https://gfx-android.omni.se/images/"+item.main_resource?.image_asset?.id
            binding.executePendingBindings()
        }
    }

    interface OnItemInteraction{
        fun onEntryClicked(entryIdx:Int)
    }
}

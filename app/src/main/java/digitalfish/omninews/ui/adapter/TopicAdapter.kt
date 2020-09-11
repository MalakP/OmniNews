package digitalfish.omninews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import digitalfish.omninews.R
import digitalfish.omninews.data.model.Article
import digitalfish.omninews.data.model.Topic
import digitalfish.omninews.databinding.ArticleItemBinding
import digitalfish.omninews.databinding.TopicItemBinding

class TopicAdapter(
    private var mItems: List<Topic>,
    private val mListener: OnItemInteraction
) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: TopicItemBinding = DataBindingUtil.inflate(inflater, R.layout.topic_item, parent, false)
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

    fun setItems(newItems: List<Topic>){
        mItems = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mItems.size

    inner class ViewHolder(val binding: TopicItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Topic) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    interface OnItemInteraction{
        fun onEntryClicked(entryIdx:Int)
    }
}

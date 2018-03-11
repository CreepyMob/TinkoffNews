package com.creepymob.mobile.tinkoffnews.app.news

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.creepymob.mobile.tinkoffnews.R
import com.creepymob.mobile.tinkoffnews.app.HtmlCompat
import com.creepymob.mobile.tinkoffnews.app.inflate
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import kotlinx.android.synthetic.main.cell_news_entry.view.*

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 22:55
 *
 */
class NewsAdapter(private val htmlCompat: HtmlCompat) : RecyclerView.Adapter<NewsEntryHolder>() {

    var content: List<NewsEntry>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onNewsEntryClickListener: ((newsEntry: NewsEntry) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsEntryHolder =
            NewsEntryHolder(parent.inflate(R.layout.cell_news_entry), htmlCompat, onNewsEntryClickListener)

    override fun getItemCount(): Int = content?.count() ?: 0

    override fun onBindViewHolder(holder: NewsEntryHolder, position: Int) {
        holder.bind(content!![position])
    }
}


class NewsEntryHolder(view: View, private val htmlCompat: HtmlCompat, onNewsEntryClickListener: ((newsEntry: NewsEntry) -> Unit)?) : RecyclerView.ViewHolder(view) {

    private var newsEntry: NewsEntry? = null

    init {
        itemView.setOnClickListener {
            newsEntry?.also {
                onNewsEntryClickListener?.invoke(it)
            }
        }
    }

    fun bind(entry: NewsEntry) {
        newsEntry = entry

        itemView.newsText.text = htmlCompat.fromHtml(entry.text)
    }

}

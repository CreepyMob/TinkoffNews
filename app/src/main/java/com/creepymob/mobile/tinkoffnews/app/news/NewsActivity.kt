package com.creepymob.mobile.tinkoffnews.app.news

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.creepymob.mobile.tinkoffnews.R
import com.creepymob.mobile.tinkoffnews.app.App
import com.creepymob.mobile.tinkoffnews.app.HtmlCompat
import com.creepymob.mobile.tinkoffnews.app.news.details.NewsDetailsActivity
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import com.creepymob.mobile.tinkoffnews.presentation.NewsPresenter
import com.creepymob.mobile.tinkoffnews.presentation.NewsView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*

class NewsActivity : MvpAppCompatActivity(), NewsView {

    @InjectPresenter
    lateinit var presenter: NewsPresenter

    private lateinit var adapter: NewsAdapter

    @ProvidePresenter
    fun provideNewsPresenter(): NewsPresenter = App.component.getNewsPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsAdapter(HtmlCompat)
                .apply {
                    onNewsEntryClickListener = { presenter.onNewsEntryClick(it) }
                }

        toolbar.title = getString(R.string.title_news)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout.setOnRefreshListener {
            presenter.onRefreshClick()
        }

        btnReload.setOnClickListener {
            presenter.onReloadClick()
        }
    }


    override fun showNews(news: List<NewsEntry>) {
        swipeRefreshLayout.visibility = View.VISIBLE
        errorHolder.visibility = View.GONE

        adapter.content = news
    }

    override fun showProgress() {
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
    }

    override fun showError(exception: Throwable) {
        errorMessage.text = exception.message
        errorHolder.visibility = View.VISIBLE
        swipeRefreshLayout.visibility = View.GONE
    }

    override fun showErrorMessage(exception: Throwable) {
        Toast.makeText(this, "refresh error: ${exception.message}", Toast.LENGTH_SHORT).show()
    }

    override fun showNewsDetails(newsEntryId: Long) {
        NewsDetailsActivity.start(this, newsEntryId)
    }

    override fun showRefreshProgress() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideRefreshProgress() {
        swipeRefreshLayout.isRefreshing = false
    }
}

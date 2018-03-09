package com.creepymob.mobile.tinkoffnews.app.news

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.creepymob.mobile.tinkoffnews.R
import com.creepymob.mobile.tinkoffnews.app.App
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import com.creepymob.mobile.tinkoffnews.presentation.NewsPresenter
import com.creepymob.mobile.tinkoffnews.presentation.NewsView

class NewsActivity : MvpAppCompatActivity(), NewsView {

    @InjectPresenter
    lateinit var presenter: NewsPresenter

    @ProvidePresenter
    fun provideNewsPresenter(): NewsPresenter{
        System.out.println("provideNewsPresenter")
        return App.component.getNewsPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        System.out.println("onCreate")
    }

    override fun showNews(news: List<NewsEntry>) {
        System.out.println("showNews: ${news.joinToString("\n")}")
    }

    override fun showProgress() {
        System.out.println("showProgress")
    }

    override fun hideProgress() {
        System.out.println("hideProgress")
    }

    override fun showError(exception: Throwable) {
        System.out.println("showError: $exception")
    }

    override fun showErrorMessage(exception: Throwable) {
        System.out.println("showErrorMessage: $exception")
    }

    override fun showNewsDetails(newsEntryId: Long) {
        System.out.println("showNewsDetails: $newsEntryId")
    }

    override fun showRefreshProgress() {
        System.out.println("showRefreshProgress:")
    }

    override fun hideRefreshProgress() {
        System.out.println("hideRefreshProgress:")
    }
}

package com.creepymob.mobile.tinkoffnews.app.news.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.creepymob.mobile.tinkoffnews.R
import com.creepymob.mobile.tinkoffnews.app.App
import com.creepymob.mobile.tinkoffnews.app.HtmlCompat
import com.creepymob.mobile.tinkoffnews.di.NewsDetailsModule
import com.creepymob.mobile.tinkoffnews.entity.NewsEntryDetails
import com.creepymob.mobile.tinkoffnews.presentation.NewsDetailsPresenter
import com.creepymob.mobile.tinkoffnews.presentation.NewsDetailsView
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 23:32
 *
 */
class NewsDetailsActivity : MvpAppCompatActivity(), NewsDetailsView {

    companion object {

        private const val ARG_NEWS_ID: String = "ARG_NEWS_ID"

        fun start(context: Context, id: Long) {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(ARG_NEWS_ID, id)
            context.startActivity(intent)
        }
    }

    @InjectPresenter
    lateinit var presenter: NewsDetailsPresenter

    @ProvidePresenter
    fun provideNewsDetailsPresenter(): NewsDetailsPresenter = App.component
            .plusNewsDetailsComponent(NewsDetailsModule(intent.getLongExtra(ARG_NEWS_ID, 0L)))
            .getNewsDetailsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news_details)

        newsDetails.movementMethod = LinkMovementMethod.getInstance()

        toolbar.title = getString(R.string.title_news_details)
    }

    override fun showDetails(newsEntryDetails: NewsEntryDetails) {
        errorHolder.visibility = View.GONE
        content.visibility = View.VISIBLE

        newsDetails.text = HtmlCompat.fromHtml(newsEntryDetails.content)
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
        content.visibility = View.GONE
    }

}
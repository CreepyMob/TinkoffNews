package com.creepymob.mobile.tinkoffnews.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * User: andrey
 * Date: 08.03.2018
 * Time: 23:47
 *
 */

//{
//    "id": "2683",
//    "name": "gibdd-for-winphone",
//    "text": "ТКС Банк запустил мобильное приложение «Штрафы ГИБДД» для Windows Phone",
//    "publicationDate": {
//    "milliseconds": 1411570863000
//},
//    "bankInfoTypeId": 2
//}

const val TABLE_NEWS_ENTRY = "news_entry"

@Entity(tableName = TABLE_NEWS_ENTRY)
data class NewsEntry(@PrimaryKey val id: Long,
                     @ColumnInfo val name: String,
                     @ColumnInfo val text: String,
                     @ColumnInfo(name = "publication_date") val publicationDate: Date,
                     @ColumnInfo val bankInfoTypeId: Int) : Datable {

    override val date: Date
        get() = publicationDate
}
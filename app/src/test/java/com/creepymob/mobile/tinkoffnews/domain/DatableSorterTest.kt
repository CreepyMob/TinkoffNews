package com.creepymob.mobile.tinkoffnews.domain

import com.creepymob.mobile.tinkoffnews.entity.Datable
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 15:43
 */
@RunWith(MockitoJUnitRunner::class)
class DatableSorterTest {

    private lateinit var sorter: DatableSorter
    private lateinit var unsorted: List<Datable>
    private lateinit var sortedByAsk: List<Datable>
    private lateinit var sortedByDesk: List<Datable>

    @Before
    fun setUp() {
        sorter = DatableSorter()

        unsorted = listOf(
                MockDatable(1),
                MockDatable(50),
                MockDatable(100),
                MockDatable(60),
                MockDatable(120)
        )

        sortedByAsk = listOf(
                MockDatable(1),
                MockDatable(50),
                MockDatable(60),
                MockDatable(100),
                MockDatable(120)
        )

        sortedByDesk = listOf(
                MockDatable(120),
                MockDatable(100),
                MockDatable(60),
                MockDatable(50),
                MockDatable(1)
        )
    }

    @Test
    fun sortAsk() {
        assertEquals(sortedByAsk, sorter.sort(unsorted, SortType.ASK))
    }

    @Test
    fun sortDesk() {
        assertEquals(sortedByDesk, sorter.sort(unsorted, SortType.DESK))
    }

    private data class MockDatable(private val time: Long) : Datable {

        override val date: Date by lazy { Date(time) }
    }

}
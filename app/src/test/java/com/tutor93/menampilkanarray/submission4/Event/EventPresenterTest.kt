package com.tutor93.menampilkanarray.submission4.Event

import com.google.gson.Gson
import com.tutor93.menampilkanarray.TestContextProvider
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.Event
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.MatchResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class EventPresenterTest {

    @Mock
    private
    lateinit var view: EventView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: EventPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchList() {
        val match: MutableList<Event> = mutableListOf()
        val response = MatchResponse(match)
        val leagueId = League.id

        `when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getMatchList(leagueId, true)),
            MatchResponse::class.java
        )).thenReturn(response)

        presenter.getMatchList(leagueId, true)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showMatchList(match)
        Mockito.verify(view).hideLoading()
    }
}
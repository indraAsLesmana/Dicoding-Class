package com.tutor93.menampilkanarray.latihan3_footballclub

import com.google.gson.Gson
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.latihan4_footballclub.TeamsView
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.TeamResponse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class Latihan3PresenterTest{
    @Mock
    private
    lateinit var view: Latihan3Activity

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository
    private lateinit var presenter: Latihan3Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Latihan3Presenter(view, apiRepository, gson, CoroutineContextProviderTest())
    }

    @Test
    fun getTeamList(){
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"

        Mockito.`when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getTeams(league)),
            TeamResponse::class.java
        )).thenReturn(response)

        presenter.getTeamList(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}
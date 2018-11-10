package com.tutor93.menampilkanarray.submission4.detailview

import com.google.gson.Gson
import com.tutor93.menampilkanarray.TestContextProvider
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.model.TheSportDBApi
import com.tutor93.menampilkanarray.model.response.TeamResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailTeamPresenterTest {

    @Mock
    private
    lateinit var viewDetail: DetailTeamView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: DetailTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailTeamPresenter(viewDetail, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamDetail() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"

        Mockito.`when`(
            gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(league)),
                TeamResponse::class.java
            )
        ).thenReturn(response)

        presenter.getTeamDetail(league)

        Mockito.verify(viewDetail).showLoading()
        Mockito.verify(viewDetail).showTeamList(teams)
        Mockito.verify(viewDetail).hideLoading()
    }
}
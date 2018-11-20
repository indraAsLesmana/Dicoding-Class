package com.tutor93.menampilkanarray.detailview

import com.tutor93.menampilkanarray.model.Team

interface DetailTeamViewFragment: DetailTeamView {
    fun sendGetRequest(mTeam: Team)

}
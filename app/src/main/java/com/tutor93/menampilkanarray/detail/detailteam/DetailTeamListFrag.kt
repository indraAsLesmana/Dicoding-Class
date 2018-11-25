package com.tutor93.menampilkanarray.detail.detailteam

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.tutor93.menampilkanarray.R
import com.tutor93.menampilkanarray.api.ApiRepository
import com.tutor93.menampilkanarray.detail.DetailPresenter
import com.tutor93.menampilkanarray.detail.DetailView
import com.tutor93.menampilkanarray.gone
import com.tutor93.menampilkanarray.model.Player
import com.tutor93.menampilkanarray.model.Team
import com.tutor93.menampilkanarray.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailTeamListFrag: Fragment(), DetailView {
    private lateinit var progressBar        : ProgressBar
    private lateinit var swipeRefresh       : SwipeRefreshLayout
    private lateinit var teamBadge          : ImageView
    private lateinit var teamDescription    : TextView
    private lateinit var presenter          : DetailPresenter

    private var mTeam           : Team = Team()
    private var isFavorite      : Boolean = false
    private var isFavoriteTemp  : Boolean = false
    private var menuItem        : Menu? = null

    override fun sendGetRequest(mTeam: Team) {
        presenter.getTeamDetail(mTeam.teamId!!)
        swipeRefresh.onRefresh { presenter.getTeamDetail(mTeam.teamId!!) }
    }

    override fun hideLoading() {
        progressBar.gone()
    }
    override fun showLoading() {
        if (!swipeRefresh.isRefreshing) progressBar.visible()
    }

    override fun showTeamList(data: List<Team>) {
        mTeam = Team(data[0].teamId,
            data[0].teamName,
            data[0].teamBadge)

        swipeRefresh.isRefreshing = false
        teamDescription.text    = data[0].teamDescription
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return Ui().createView(AnkoContext.create(ctx, container!!, false))

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //mTeam.teamId = arguments?.getParcelable<Team>("data")?.teamId
        /**
         * load data
         * */
        presenter = DetailPresenter(this, ApiRepository(), Gson())
        /* saya telah cek di atas, mustahil jadi kosong :)*/
        //presenter.getTeamDetail(mTeam.teamId!!)
        //swipeRefresh.onRefresh { presenter.getTeamDetail(mTeam.teamId!!) }
    }

    inner class Ui: AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL
                    backgroundColor = Color.WHITE

                    swipeRefresh = swipeRefreshLayout {
                        setColorSchemeResources(
                            R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light
                        )

                        scrollView {
                            isVerticalScrollBarEnabled = false
                            relativeLayout {
                                lparams(width = matchParent, height = wrapContent)

                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)
                                    padding = dip(10)
                                    orientation = LinearLayout.VERTICAL
                                    gravity = Gravity.CENTER_HORIZONTAL

                                    teamDescription = textView().lparams {
                                        topMargin = dip(20)
                                    }
                                }
                                progressBar = progressBar {
                                }.lparams {
                                    centerHorizontally()
                                }
                            }
                        }
                    }
                }

            }
        }
    }
    override fun showTeamLogo(url: String, into: Int) {}
    override fun showPLayerDetail(data: Player) {}
    override fun showPlayerList(player: List<Player>) {}

}
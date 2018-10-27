package com.tutor93.menampilkanarray.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Event() : Parcelable {
    @SerializedName("idEvent")
    @Expose
    var idEvent: String? = null
    @SerializedName("idSoccerXML")
    @Expose
    var idSoccerXML: String? = null
    @SerializedName("strEvent")
    @Expose
    var strEvent: String? = null
    @SerializedName("strFilename")
    @Expose
    var strFilename: String? = null
    @SerializedName("strSport")
    @Expose
    var strSport: String? = null
    @SerializedName("idLeague")
    @Expose
    var idLeague: String? = null
    @SerializedName("strLeague")
    @Expose
    var strLeague: String? = null
    @SerializedName("strSeason")
    @Expose
    var strSeason: String? = null
    @SerializedName("strDescriptionEN")
    @Expose
    var strDescriptionEN: String? = null
    @SerializedName("strHomeTeam")
    @Expose
    var strHomeTeam: String? = null
    @SerializedName("strAwayTeam")
    @Expose
    var strAwayTeam: String? = null
    @SerializedName("intHomeScore")
    @Expose
    var intHomeScore: Int? = null
    @SerializedName("intRound")
    @Expose
    var intRound: String? = null
    @SerializedName("intAwayScore")
    @Expose
    var intAwayScore: Int? = null
    @SerializedName("intSpectators")
    @Expose
    var intSpectators: Int? = null
    @SerializedName("strHomeGoalDetails")
    @Expose
    var strHomeGoalDetails: String? = null
    @SerializedName("strHomeRedCards")
    @Expose
    var strHomeRedCards: String? = null
    @SerializedName("strHomeYellowCards")
    @Expose
    var strHomeYellowCards: String? = null
    @SerializedName("strHomeLineupGoalkeeper")
    @Expose
    var strHomeLineupGoalkeeper: String? = null
    @SerializedName("strHomeLineupDefense")
    @Expose
    var strHomeLineupDefense: String? = null
    @SerializedName("strHomeLineupMidfield")
    @Expose
    var strHomeLineupMidfield: String? = null
    @SerializedName("strHomeLineupForward")
    @Expose
    var strHomeLineupForward: String? = null
    @SerializedName("strHomeLineupSubstitutes")
    @Expose
    var strHomeLineupSubstitutes: String? = null
    @SerializedName("strHomeFormation")
    @Expose
    var strHomeFormation: String? = null
    @SerializedName("strAwayRedCards")
    @Expose
    var strAwayRedCards: String? = null
    @SerializedName("strAwayYellowCards")
    @Expose
    var strAwayYellowCards: String? = null
    @SerializedName("strAwayGoalDetails")
    @Expose
    var strAwayGoalDetails: String? = null
    @SerializedName("strAwayLineupGoalkeeper")
    @Expose
    var strAwayLineupGoalkeeper: String? = null
    @SerializedName("strAwayLineupDefense")
    @Expose
    var strAwayLineupDefense: String? = null
    @SerializedName("strAwayLineupMidfield")
    @Expose
    var strAwayLineupMidfield: String? = null
    @SerializedName("strAwayLineupForward")
    @Expose
    var strAwayLineupForward: String? = null
    @SerializedName("strAwayLineupSubstitutes")
    @Expose
    var strAwayLineupSubstitutes: String? = null
    @SerializedName("strAwayFormation")
    @Expose
    var strAwayFormation: String? = null
    @SerializedName("intHomeShots")
    @Expose
    var intHomeShots: Int? = null
    @SerializedName("intAwayShots")
    @Expose
    var intAwayShots: Int? = null
    @SerializedName("dateEvent")
    @Expose
    var dateEvent: Date? = null
    @SerializedName("strDate")
    @Expose
    var strDate: String? = null
    @SerializedName("strTime")
    @Expose
    var strTime: String? = null
    @SerializedName("strTVStation")
    @Expose
    var strTVStation: String? = null
    @SerializedName("idHomeTeam")
    @Expose
    var idHomeTeam: String? = null
    @SerializedName("idAwayTeam")
    @Expose
    var idAwayTeam: String? = null
    @SerializedName("strResult")
    @Expose
    var strResult: String? = null
    @SerializedName("strCircuit")
    @Expose
    var strCircuit: String? = null
    @SerializedName("strCountry")
    @Expose
    var strCountry: String? = null
    @SerializedName("strCity")
    @Expose
    var strCity: String? = null
    @SerializedName("strPoster")
    @Expose
    var strPoster: String? = null
    @SerializedName("strFanart")
    @Expose
    var strFanart: String? = null
    @SerializedName("strThumb")
    @Expose
    var strThumb: String? = null
    @SerializedName("strBanner")
    @Expose
    var strBanner: String? = null
    @SerializedName("strMap")
    @Expose
    var strMap: String? = null
    @SerializedName("strLocked")
    @Expose
    var strLocked: String? = null

    constructor(parcel: Parcel) : this() {
        idEvent = parcel.readString()
        idSoccerXML = parcel.readString()
        strEvent = parcel.readString()
        strFilename = parcel.readString()
        strSport = parcel.readString()
        idLeague = parcel.readString()
        strLeague = parcel.readString()
        strSeason = parcel.readString()
        strDescriptionEN = parcel.readString()
        strHomeTeam = parcel.readString()
        strAwayTeam = parcel.readString()
        intHomeScore = parcel.readValue(Int::class.java.classLoader) as? Int
        intRound = parcel.readString()
        intAwayScore = parcel.readValue(Int::class.java.classLoader) as? Int
        intSpectators = parcel.readValue(Int::class.java.classLoader) as? Int
        strHomeGoalDetails = parcel.readString()
        strHomeRedCards = parcel.readString()
        strHomeYellowCards = parcel.readString()
        strHomeLineupGoalkeeper = parcel.readString()
        strHomeLineupDefense = parcel.readString()
        strHomeLineupMidfield = parcel.readString()
        strHomeLineupForward = parcel.readString()
        strHomeLineupSubstitutes = parcel.readString()
        strHomeFormation = parcel.readString()
        strAwayRedCards = parcel.readString()
        strAwayYellowCards = parcel.readString()
        strAwayGoalDetails = parcel.readString()
        strAwayLineupGoalkeeper = parcel.readString()
        strAwayLineupDefense = parcel.readString()
        strAwayLineupMidfield = parcel.readString()
        strAwayLineupForward = parcel.readString()
        strAwayLineupSubstitutes = parcel.readString()
        strAwayFormation = parcel.readString()
        intHomeShots = parcel.readValue(Int::class.java.classLoader) as? Int
        intAwayShots = parcel.readValue(Int::class.java.classLoader) as? Int
        strDate = parcel.readString()
        strTime = parcel.readString()
        strTVStation = parcel.readString()
        idHomeTeam = parcel.readString()
        idAwayTeam = parcel.readString()
        strResult = parcel.readString()
        strCircuit = parcel.readString()
        strCountry = parcel.readString()
        strCity = parcel.readString()
        strPoster = parcel.readString()
        strFanart = parcel.readString()
        strThumb = parcel.readString()
        strBanner = parcel.readString()
        strMap = parcel.readString()
        strLocked = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idEvent)
        parcel.writeString(idSoccerXML)
        parcel.writeString(strEvent)
        parcel.writeString(strFilename)
        parcel.writeString(strSport)
        parcel.writeString(idLeague)
        parcel.writeString(strLeague)
        parcel.writeString(strSeason)
        parcel.writeString(strDescriptionEN)
        parcel.writeString(strHomeTeam)
        parcel.writeString(strAwayTeam)
        parcel.writeValue(intHomeScore)
        parcel.writeString(intRound)
        parcel.writeValue(intAwayScore)
        parcel.writeValue(intSpectators)
        parcel.writeString(strHomeGoalDetails)
        parcel.writeString(strHomeRedCards)
        parcel.writeString(strHomeYellowCards)
        parcel.writeString(strHomeLineupGoalkeeper)
        parcel.writeString(strHomeLineupDefense)
        parcel.writeString(strHomeLineupMidfield)
        parcel.writeString(strHomeLineupForward)
        parcel.writeString(strHomeLineupSubstitutes)
        parcel.writeString(strHomeFormation)
        parcel.writeString(strAwayRedCards)
        parcel.writeString(strAwayYellowCards)
        parcel.writeString(strAwayGoalDetails)
        parcel.writeString(strAwayLineupGoalkeeper)
        parcel.writeString(strAwayLineupDefense)
        parcel.writeString(strAwayLineupMidfield)
        parcel.writeString(strAwayLineupForward)
        parcel.writeString(strAwayLineupSubstitutes)
        parcel.writeString(strAwayFormation)
        parcel.writeValue(intHomeShots)
        parcel.writeValue(intAwayShots)
        parcel.writeString(strDate)
        parcel.writeString(strTime)
        parcel.writeString(strTVStation)
        parcel.writeString(idHomeTeam)
        parcel.writeString(idAwayTeam)
        parcel.writeString(strResult)
        parcel.writeString(strCircuit)
        parcel.writeString(strCountry)
        parcel.writeString(strCity)
        parcel.writeString(strPoster)
        parcel.writeString(strFanart)
        parcel.writeString(strThumb)
        parcel.writeString(strBanner)
        parcel.writeString(strMap)
        parcel.writeString(strLocked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }

}
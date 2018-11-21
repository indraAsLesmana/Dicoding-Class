package com.tutor93.menampilkanarray.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Player() : Parcelable {
    @SerializedName("idPlayer")
    @Expose
    var idPlayer: String? = null
    @SerializedName("idTeam")
    @Expose
    var idTeam: String? = null
    @SerializedName("idSoccerXML")
    @Expose
    var idSoccerXML: String? = null
    @SerializedName("idPlayerManager")
    @Expose
    var idPlayerManager: String? = null
    @SerializedName("strNationality")
    @Expose
    var strNationality: String? = null
    @SerializedName("strPlayer")
    @Expose
    var strPlayer: String? = null
    @SerializedName("strTeam")
    @Expose
    var strTeam: String? = null
    @SerializedName("strSport")
    @Expose
    var strSport: String? = null
    @SerializedName("intSoccerXMLTeamID")
    @Expose
    var intSoccerXMLTeamID: String? = null
    @SerializedName("dateBorn")
    @Expose
    var dateBorn: String? = null
    @SerializedName("dateSigned")
    @Expose
    var dateSigned: String? = null
    @SerializedName("strSigning")
    @Expose
    var strSigning: String? = null
    @SerializedName("strWage")
    @Expose
    var strWage: String? = null
    @SerializedName("strBirthLocation")
    @Expose
    var strBirthLocation: String? = null
    @SerializedName("strDescriptionEN")
    @Expose
    var strDescriptionEN: String? = null
    @SerializedName("strDescriptionDE")
    @Expose
    var strDescriptionDE: String? = null
    @SerializedName("strDescriptionFR")
    @Expose
    var strDescriptionFR: String? = null
    @SerializedName("strDescriptionCN")
    @Expose
    var strDescriptionCN: String? = null
    @SerializedName("strDescriptionIT")
    @Expose
    var strDescriptionIT: String? = null
    @SerializedName("strDescriptionJP")
    @Expose
    var strDescriptionJP: String? = null
    @SerializedName("strDescriptionRU")
    @Expose
    var strDescriptionRU: String? = null
    @SerializedName("strDescriptionES")
    @Expose
    var strDescriptionES: String? = null
    @SerializedName("strDescriptionPT")
    @Expose
    var strDescriptionPT: String? = null
    @SerializedName("strDescriptionSE")
    @Expose
    var strDescriptionSE: String? = null
    @SerializedName("strDescriptionNL")
    @Expose
    var strDescriptionNL: String? = null
    @SerializedName("strDescriptionHU")
    @Expose
    var strDescriptionHU: String? = null
    @SerializedName("strDescriptionNO")
    @Expose
    var strDescriptionNO: String? = null
    @SerializedName("strDescriptionIL")
    @Expose
    var strDescriptionIL: String? = null
    @SerializedName("strDescriptionPL")
    @Expose
    var strDescriptionPL: String? = null
    @SerializedName("strGender")
    @Expose
    var strGender: String? = null
    @SerializedName("strPosition")
    @Expose
    var strPosition: String? = null
    @SerializedName("strCollege")
    @Expose
    var strCollege: String? = null
    @SerializedName("strFacebook")
    @Expose
    var strFacebook: String? = null
    @SerializedName("strWebsite")
    @Expose
    var strWebsite: String? = null
    @SerializedName("strTwitter")
    @Expose
    var strTwitter: String? = null
    @SerializedName("strInstagram")
    @Expose
    var strInstagram: String? = null
    @SerializedName("strYoutube")
    @Expose
    var strYoutube: String? = null
    @SerializedName("strHeight")
    @Expose
    var strHeight: String? = null
    @SerializedName("strWeight")
    @Expose
    var strWeight: String? = null
    @SerializedName("intLoved")
    @Expose
    var intLoved: String? = null
    @SerializedName("strThumb")
    @Expose
    var strThumb: String? = null
    @SerializedName("strCutout")
    @Expose
    var strCutout: String? = null
    @SerializedName("strBanner")
    @Expose
    var strBanner: String? = null
    @SerializedName("strFanart1")
    @Expose
    var strFanart1: String? = null
    @SerializedName("strFanart2")
    @Expose
    var strFanart2: String? = null
    @SerializedName("strFanart3")
    @Expose
    var strFanart3: String? = null
    @SerializedName("strFanart4")
    @Expose
    var strFanart4: String? = null
    @SerializedName("strLocked")
    @Expose
    var strLocked: String? = null

    constructor(parcel: Parcel) : this() {
        idPlayer = parcel.readString()
        idTeam = parcel.readString()
        idSoccerXML = parcel.readString()
        idPlayerManager = parcel.readString()
        strNationality = parcel.readString()
        strPlayer = parcel.readString()
        strTeam = parcel.readString()
        strSport = parcel.readString()
        intSoccerXMLTeamID = parcel.readString()
        dateBorn = parcel.readString()
        dateSigned = parcel.readString()
        strSigning = parcel.readString()
        strWage = parcel.readString()
        strBirthLocation = parcel.readString()
        strDescriptionEN = parcel.readString()
        strDescriptionDE = parcel.readString()
        strDescriptionFR = parcel.readString()
        strDescriptionCN = parcel.readString()
        strDescriptionIT = parcel.readString()
        strDescriptionJP = parcel.readString()
        strDescriptionRU = parcel.readString()
        strDescriptionES = parcel.readString()
        strDescriptionPT = parcel.readString()
        strDescriptionSE = parcel.readString()
        strDescriptionNL = parcel.readString()
        strDescriptionHU = parcel.readString()
        strDescriptionNO = parcel.readString()
        strDescriptionIL = parcel.readString()
        strDescriptionPL = parcel.readString()
        strGender = parcel.readString()
        strPosition = parcel.readString()
        strCollege = parcel.readString()
        strFacebook = parcel.readString()
        strWebsite = parcel.readString()
        strTwitter = parcel.readString()
        strInstagram = parcel.readString()
        strYoutube = parcel.readString()
        strHeight = parcel.readString()
        strWeight = parcel.readString()
        intLoved = parcel.readString()
        strThumb = parcel.readString()
        strCutout = parcel.readString()
        strBanner = parcel.readString()
        strFanart1 = parcel.readString()
        strFanart2 = parcel.readString()
        strFanart3 = parcel.readString()
        strFanart4 = parcel.readString()
        strLocked = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idPlayer)
        parcel.writeString(idTeam)
        parcel.writeString(idSoccerXML)
        parcel.writeString(idPlayerManager)
        parcel.writeString(strNationality)
        parcel.writeString(strPlayer)
        parcel.writeString(strTeam)
        parcel.writeString(strSport)
        parcel.writeString(intSoccerXMLTeamID)
        parcel.writeString(dateBorn)
        parcel.writeString(dateSigned)
        parcel.writeString(strSigning)
        parcel.writeString(strWage)
        parcel.writeString(strBirthLocation)
        parcel.writeString(strDescriptionEN)
        parcel.writeString(strDescriptionDE)
        parcel.writeString(strDescriptionFR)
        parcel.writeString(strDescriptionCN)
        parcel.writeString(strDescriptionIT)
        parcel.writeString(strDescriptionJP)
        parcel.writeString(strDescriptionRU)
        parcel.writeString(strDescriptionES)
        parcel.writeString(strDescriptionPT)
        parcel.writeString(strDescriptionSE)
        parcel.writeString(strDescriptionNL)
        parcel.writeString(strDescriptionHU)
        parcel.writeString(strDescriptionNO)
        parcel.writeString(strDescriptionIL)
        parcel.writeString(strDescriptionPL)
        parcel.writeString(strGender)
        parcel.writeString(strPosition)
        parcel.writeString(strCollege)
        parcel.writeString(strFacebook)
        parcel.writeString(strWebsite)
        parcel.writeString(strTwitter)
        parcel.writeString(strInstagram)
        parcel.writeString(strYoutube)
        parcel.writeString(strHeight)
        parcel.writeString(strWeight)
        parcel.writeString(intLoved)
        parcel.writeString(strThumb)
        parcel.writeString(strCutout)
        parcel.writeString(strBanner)
        parcel.writeString(strFanart1)
        parcel.writeString(strFanart2)
        parcel.writeString(strFanart3)
        parcel.writeString(strFanart4)
        parcel.writeString(strLocked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}

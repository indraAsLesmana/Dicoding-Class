package com.tutor93.menampilkanarray.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper{
            if (instance == null){
                instance = MyDatabaseOpenHelper(ctx)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            "TABLE_FAVORITE", true,
            "ID_" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            "TEAM_ID" to TEXT + UNIQUE,
            "TEAM_NAME" to TEXT,
            "TEAM_BADGE" to TEXT,
            "TEAM_EVENT" to TEXT,
            "TEAM_AWAY_BADGE" to TEXT,
            "TEAM_HOME_BADGE" to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable("TABLE_FAVORITE", true)
    }

}

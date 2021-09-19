package com.example.fallencoffee.db

import org.ktorm.database.Database
import org.ktorm.support.sqlite.SQLiteDialect

class CupDB{
    fun connection(): Database = Database.connect(
        "JDBC:sqlite://resource/Cup.db",
        null,
        null,
        null, SQLiteDialect())
}
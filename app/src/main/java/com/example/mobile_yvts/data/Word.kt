package com.example.mobile_yvts.data;

import androidx.room.Entity
import androidx.room.PrimaryKey

// ugsiin sang roomd hadgalna
@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val english: String,
    val mongolian: String
)
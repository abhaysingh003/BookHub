package com.umeshsingh.bookhub.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val book_id: Int,
    @ColumnInfo(name = "book_name") val favBookName: String,
    @ColumnInfo(name = "book_author") val favBookAuthor: String,
    @ColumnInfo(name = "book_price") val favBookPrice: String,
    @ColumnInfo(name = "book_rating") val favBookRating: String,
    @ColumnInfo(name = "book_desc") val favBookDesc: String,
    @ColumnInfo(name = "book_image") val favBookImage: String
)
package com.example.roomlocaldb.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Mahasiswa")
data class Mahasiswa(
        @PrimaryKey
        val nim: String,
        val nama: String,
        val alamat: String,
        val jeniskelamin: String,
        val angkatan: String,
        val kelas: String,
    )


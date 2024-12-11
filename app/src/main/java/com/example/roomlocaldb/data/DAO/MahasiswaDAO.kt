package com.example.roomlocaldb.data.DAO

import androidx.room.Dao
import androidx.room.Insert
import com.example.roomlocaldb.data.entity.Mahasiswa

@Dao
interface MahasiswaDAO {
    @Insert
    suspend fun InsertMahasiswa(mahasiswa: Mahasiswa)
    abstract fun insertMahasiswa(mahasiswa: Mahasiswa)
}
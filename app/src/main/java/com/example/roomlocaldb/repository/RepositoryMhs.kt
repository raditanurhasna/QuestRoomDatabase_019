package com.example.roomlocaldb.repository

import com.example.roomlocaldb.data.entity.Mahasiswa

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
}
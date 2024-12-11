package com.example.roomlocaldb.repository

import com.example.roomlocaldb.data.DAO.MahasiswaDAO
import com.example.roomlocaldb.data.entity.Mahasiswa

class LocalRepositoryMhs (
    private val  mahasiswaDao : MahasiswaDAO
    ): RepositoryMhs {
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }

}
package com.example.roomlocaldb.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomlocaldb.data.DAO.MahasiswaDao
import com.example.roomlocaldb.data.entity.Mahasiswa

@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase() {

    // Mendefinisikan fungsi untuk mengakses data mahasiswa

    abstract fun mahasiswaDao(): MahasiswaDao

    companion object {
        @Volatile //Memastikan bahwa nilai variabel instance selalu sama di semua
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabase::class.java, // Class database
                    "KrsDatabase" // Nama database
                )
                    .build().also { Instance = it }
            })
        }
    }
}
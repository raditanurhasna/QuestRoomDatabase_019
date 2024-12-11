package com.example.roomlocaldb.dependeciesinjection

import android.content.Context
import com.example.p10roomlocal.data.database.KrsDatabase
import com.example.roomlocaldb.KrsApp
import com.example.roomlocaldb.repository.LocalRepositoryMhs
import com.example.roomlocaldb.repository.RepositoryMhs

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}
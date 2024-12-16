package com.example.roomlocaldb.ui.theme.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomlocaldb.data.entity.Mahasiswa
import com.example.roomlocaldb.repository.RepositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel (
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {
    var uiState by mutableStateOf(MhsUIState())

    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }
    private  fun validateFields(): Boolean {
        val event = uiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jeniskelamin = if (event.jeniskelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong"

        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(){
        val currentEvent = uiState.mahasiswaEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil disimpan",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}





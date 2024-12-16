package com.example.roomlocaldb.ui.theme.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomlocaldb.data.entity.Mahasiswa
import com.example.roomlocaldb.repository.RepositoryMhs
import com.example.roomlocaldb.ui.theme.navigation.DestinasiDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs,

) : ViewModel(){
    private val  _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])

    val detailUiState: StateFlow<DetailUiState> = repositoryMhs.getMhs(_nim)
       .filterNotNull()
       .map {
        DetailUiState(
            detailUiEvent = it.toDetailUiEvent(),
            isLoading = false,
        )
    }
    .onStart {
        emit(DetailUiState(isLoading = true))
        delay(600)


    }
    .catch {
         emit(
             DetailUiState(
                 isLoading = false,
                 isError = true,
                 errorMessage = it.message ?: "Terjadi Kesalahan",
             )
         )
        }
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2000),
        initialValue = DetailUiState (
            isLoading = true,
        ),

    )

    fun deleteMhs() {
        detailUiState.value.detailUiEvent.toMahasiswaEntity().let {
            viewModelScope.launch {
                repositoryMhs.deleteMhs(it)
            }
        }
    }
}





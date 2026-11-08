package com.example.urcast.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urcast.data.remote.dto.NodeDto
import com.example.urcast.data.remote.dto.ReadingDto
import com.example.urcast.data.repository.NodeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.urcast.data.remote.dto.CreateNodeRequest

data class NodesUiState(
    val nodes: List<NodeDto> = emptyList(),
    val readings: List<ReadingDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class NodeViewModel(
    private val repository: NodeRepository = NodeRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        NodesUiState(isLoading = true)
    )

    val uiState = _uiState.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            try {
                val nodes = repository.getNodes()
                val readings = repository.getReadings()

                _uiState.value = NodesUiState(
                    nodes = nodes,
                    readings = readings,
                    isLoading = false
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unable to load weather data"
                )
            }
        }
    }
    fun registerNode(
        request: CreateNodeRequest,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                repository.createNode(request)

                fetchData()
                onSuccess()

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = e.message ?: "Unable to register node"
                )
            }
        }
    }
}
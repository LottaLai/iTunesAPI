package com.lotta.itunesapi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.lotta.itunesapi.model.FilterModel
import com.lotta.itunesapi.repository.interfaces.MediaRepositoryInterface
import com.lotta.itunesapi.model.Track
import com.lotta.itunesapi.ui.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MediaRepositoryInterface
) : ViewModel() {
    var mediaFilterList = MutableLiveData<MutableList<FilterModel>>()
    var tracks = MutableLiveData<PagingData<Track>>()
    var filter = MutableLiveData<PagingData<Track>>()

    fun getSearchResult(termQuery: String){
        viewModelScope.launch {
            MainActivity.isLoading.value = true
            repo.getSearchTracksResult(termQuery).cachedIn(viewModelScope).collectLatest{ data ->
                tracks.value = data
                MainActivity.isLoading.value = false
            }
        }
    }

    fun filterTracks(filterString: String) {
        filter.value = if (filterString == "all") {
            tracks.value
        } else {
            tracks.value?.filter { track ->
                track.kind == filterString
            }
        }
    }

    fun filterList(
        enLanguage: Array<String>,
        curLanguage: Array<String>
    ) {
        val filterList = mutableListOf<FilterModel>()
        for (x in enLanguage.indices) {
            val filterModel = FilterModel(enLanguage[x], curLanguage[x], x == 0)
            filterList.add(filterModel)
        }
        mediaFilterList.value = filterList
    }
}
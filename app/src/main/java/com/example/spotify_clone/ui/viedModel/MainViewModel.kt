package com.example.spotify_clone.ui.viedModel

import com.example.spotify_clone.exoplayer.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val musicServiceConnection:MusicServiceConnection
) {
}
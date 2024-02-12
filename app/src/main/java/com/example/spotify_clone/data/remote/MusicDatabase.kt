package com.example.spotify_clone.data.remote

import android.content.Context
import com.example.spotify_clone.data.entities.Song
<<<<<<< HEAD
import com.example.spotify_clone.utils.Constants.SONG_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
=======
import com.example.spotify_clone.other.Constants.SONG_COLLECTION
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
>>>>>>> refs/remotes/origin/main
import kotlinx.coroutines.tasks.await

class MusicDatabase() {


    private val firestore = Firebase.firestore
    private val songCollection = firestore.collection(SONG_COLLECTION)



    suspend fun getAllSongs():List<Song>{
        return try{
            songCollection.get().await().toObjects(Song::class.java)
        }
        catch (e:Exception){
            emptyList()
        }
    }
}
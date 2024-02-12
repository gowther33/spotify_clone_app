package com.example.spotify_clone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.spotify_clone.data.entities.Song
import com.example.spotify_clone.databinding.SongItemViewBinding
import javax.inject.Inject

class SongAdapter @Inject constructor(
    private val glide : RequestManager
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>(){

    inner class SongViewHolder(binding: SongItemViewBinding):RecyclerView.ViewHolder(binding.root){
        private val icon = binding.ivItemImage
        private val title = binding.tvPrimary
        private val subTitle = binding.tvSecondary
        private val layout = binding.songLayout

        fun bind(song:Song){
            title.text = song.title
            subTitle.text = song.subtitle
            glide.load(song.imageUrl).into(icon)

            layout.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Song>(){
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var songs:List<Song>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = SongItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    private var onItemClickListener : ((Song) -> Unit)? = null

    fun setOnItemClickListener(listener: (Song) -> Unit){
        onItemClickListener = listener
    }
}
package kz.tutorial.jsonplaceholdertypicode.presentation.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import kz.tutorial.jsonplaceholdertypicode.R
import kz.tutorial.jsonplaceholdertypicode.databinding.ItemAlbumBinding
import kz.tutorial.jsonplaceholdertypicode.domain.entity.Album
import kz.tutorial.jsonplaceholdertypicode.presentation.utils.ClickListenerWithThree

class AlbumsAdapter():
    RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>(){

    private var data: List<Album> = emptyList()
    fun setData(data: List<Album>){
        this.data = data
    }
    var listener: ClickListenerWithThree? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            listener?.onClick(
                data[position].id,
                data[position].username,
                data[position].title

            )
        }
    }

    class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(album: Album){
            binding.tvTitle.text = album.title
            binding.tvUsername.text = album.username
            Glide.with(binding.root)
                .load(album.photoUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivImage)
        }
    }
}


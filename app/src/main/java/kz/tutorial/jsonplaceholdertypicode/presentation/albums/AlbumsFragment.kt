package kz.tutorial.jsonplaceholdertypicode.presentation.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kz.tutorial.jsonplaceholdertypicode.R
import kz.tutorial.jsonplaceholdertypicode.databinding.FragmentAlbumsBinding
import kz.tutorial.jsonplaceholdertypicode.domain.entity.Album
import kz.tutorial.jsonplaceholdertypicode.presentation.utils.ClickListener
import kz.tutorial.jsonplaceholdertypicode.presentation.utils.ClickListenerWithThree
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsFragment : Fragment() {



    private lateinit var binding: FragmentAlbumsBinding
    private lateinit var adapter: AlbumsAdapter
    private val viewModel: AlbumsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        initAdapter()
        initObservers()
        return binding.root
    }

    private fun initAdapter()
    {
        adapter = AlbumsAdapter()
        adapter.listener = ClickListenerWithThree {id, name, albumName->
            NavHostFragment.findNavController(this).navigate(
                AlbumsFragmentDirections.albumsToPhotos(
                    id, name, albumName
                )
            )
        }
        binding.rvAlbums.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL,false)
        binding.rvAlbums.adapter = adapter

    }

    private fun initObservers(){
        viewModel.albumsLiveData.observe(viewLifecycleOwner)
        {
            when(it){
                is AlbumsState.Error -> {}
                AlbumsState.Loading ->{
                    changeLoading(true)
                }

                is AlbumsState.Success ->{
                    changeLoading(false)
                    changeAdapter(it.listAlbums)
                }
            }
        }
    }

    private fun changeAdapter(list: List<Album>){
        adapter.setData(list)
        adapter.notifyDataSetChanged()
    }

    private fun changeLoading(status: Boolean){
        when (status){
            true -> binding.loading.visibility = View.VISIBLE
            else -> binding.loading.visibility = View.GONE
        }
    }


    //0. Создаем item_album, где отрисовываем с дизайна верстку альбома для списка
    //Создаем AlbumViewHolder, который в bind принимает Album и
    // с помощью Glide заливаем изображение (инструкция ниже) и ассайним остальные данные
    // Также не забываем itemClickListener привязать
    // Далее делаем сам адаптер

    //1. Сначала создайте вьюмодел которая использует GetAlbumsUseCase.
    //Не забываем использовать di
    //Внедряем сюда viewModel
    //Следим за liveData и при получении данных
    // отправляем данные в адаптер, который мы засэтапили на предыдущем шаге

    //2. Подключаем обработчик нажатия и пока оставляем пустым.
    // После, при создании PhotosFragment заинтегрируйте его в обработку клика





}
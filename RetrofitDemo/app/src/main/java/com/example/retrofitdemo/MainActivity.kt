package com.example.retrofitdemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofitdemo.Albums
import com.example.retrofitdemo.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService : AlbumService
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.root
        retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
        getRequestWithQueryParameters()
        getRequestWithPathParameter()
        uploadAlbum()
    }


    private fun getRequestWithQueryParameters(){

        // get all albums example
//        val responseLiveData : LiveData<Response<Albums>> = liveData{
//            val response = retService.getAlbums()
//            emit(response)
//        }

        // get albums with a query parameter example
        val responseLiveData : LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }

        responseLiveData.observe(this, Observer{
            val albumsList = it.body()?.listIterator()
            if (albumsList != null){
                while (albumsList.hasNext()){
                    val albumListItem = albumsList.next()
                    val result = " " + "Album id: ${albumListItem.id}" + "\n" +
                            " " + "Album user id: ${albumListItem.userId}" + "\n" +
                            " " + "Album title: ${albumListItem.title}" + "\n\n\n"
                    binding.textView.append(result)
                }
            }
        })
    }

    private fun getRequestWithPathParameter(){
        // path parameter example
        val pathResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }

    private fun uploadAlbum(){
        val album = AlbumsItem(0, "New Album", 0)
        val postResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }
        postResponse.observe(this, Observer {
            val receivedAlbumsItem = it.body()
            val result = " " + "Album id: ${receivedAlbumsItem?.id}" + "\n" +
                    " " + "Album user id: ${receivedAlbumsItem?.userId}" + "\n" +
                    " " + "Album title: ${receivedAlbumsItem?.title}" + "\n\n\n"
            binding.textView.text = result
        })
    }
}

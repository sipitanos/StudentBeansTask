package com.example.studentbeans.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentbeans.model.PostsModel
import com.example.studentbeans.retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsViewModel: ViewModel() {
    // LiveData
    private var postList = MutableLiveData<List<PostsModel>?>()


    fun setList(posts: List<PostsModel>){
        postList.postValue(posts)
    }
    fun getList(): MutableLiveData<List<PostsModel>?> {
        return postList
    }
    // force reset of GET call
    fun destroyList(){
        postList.value = null
    }

    fun makeAPICall(){
        val retrofit = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build() //convert it to json

        val api = retrofit.create(RetrofitInterface::class.java) //api variable initialised
        val call = api.photos

        call?.enqueue(object: retrofit2.Callback<List<PostsModel?>?> { // asynchronously send the request
            // request succeeded
            override fun onResponse(call: Call<List<PostsModel?>?>, response: Response<List<PostsModel?>?>) {
                val posts: List<PostsModel> = response.body() as List<PostsModel>
                setList(posts) // place the data in the LiveData to be seen by the fragment observer
            }
            //request failed
            override fun onFailure(call: Call<List<PostsModel?>?>, t: Throwable) {
                // no code here, simply will not populate the view
            }
        })
    }
}

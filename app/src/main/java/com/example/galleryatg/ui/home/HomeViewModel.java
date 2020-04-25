package com.example.galleryatg.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.galleryatg.FlickerInterface;
import com.example.galleryatg.Gallery;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<Gallery> galleryList;

    //we will call this method to get the data
    public LiveData<Gallery> getPost() {
        //if the list is null
        if (galleryList == null) {
            galleryList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadPost();
        }

        //finally we will return the list
        return galleryList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlickerInterface flickerInterface = retrofit.create(FlickerInterface.class);
        Call<Gallery> call = flickerInterface.getPost();


        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                galleryList.setValue(response.body());
                Log.i("msg",response.body().toString());
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {
                Log.i("err",t.toString());

            }
        });


    }

    /*private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }**/
}
package com.sukhralia.sampleapparchitectureui.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sukhralia.sampleapparchitectureui.R

fun getPersonNameFromLiveData(list : List<com.sukhralia.sampleapparchitectureui.person.database.Person>) : String{

    var finalString = ""

    if(list.isNotEmpty()){
        for(i in list.indices){
            finalString += list[i].name
        }
    }

    return finalString
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.loading_error))
            .into(imgView)
    }
}
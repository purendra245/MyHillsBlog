package com.example.myhillsblog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myhillsblog.R
import com.example.myhillsblog.model.Blogs
import com.example.myhillsblog.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private  val TAG = "MainActivity"
    private val viewModel:MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObserver()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)
    }


    private fun subscribeObserver(){

        viewModel.dataState.observe(this, Observer {
            dataState->
            when(dataState){
                is DataState.Success<List<Blogs>> ->{
                    displayProgressBar(false)
                    appendBlogTitle(dataState.data)
                }
                is DataState.Error ->{
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading ->{
                    displayProgressBar(true)
                }
            }
        })
    }

    private  fun displayError(message:String?){
        if(message!=null){
            text.text = message
        }
        else{
            text.text ="unknown error"
        }
    }


    private fun displayProgressBar(isDisplay:Boolean){
        progress_bar.visibility = if(isDisplay) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitle(listBlogs: List<Blogs>){

        val sb = StringBuilder()

        for (blog in listBlogs){
            sb.append(blog.title+"\n")
        }


        text.text = sb.toString()
    }

}
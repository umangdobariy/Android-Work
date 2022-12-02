package com.example.music_album.Adoptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music_album.Modal.Auto
import com.example.music_album.databinding.ActivityAutoSliderBinding
import com.example.music_album.databinding.AutoSliderLayoutBinding

class Auto_Slider(var context: Context,var autoslider :MutableList<Auto>):RecyclerView.Adapter<Auto_Slider.myViewHolder>() {

    lateinit var binding: AutoSliderLayoutBinding

    class myViewHolder(var bind: AutoSliderLayoutBinding) :RecyclerView.ViewHolder(bind.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
      binding = AutoSliderLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        var auto = autoslider[position]
        holder.bind.ivIg.setImageResource(auto.img)
    }

    override fun getItemCount(): Int {
      return  autoslider.size
    }
    fun getListSize() {
        autoslider.size
    }

//    fun autoScroll(listSize: Int, intervalInMillis: Long) {
//        dispose?.let {
//            if(!it.isDisposed) return
//        }
//        dispose = Flowable.interval(intervalInMillis, TimeUnit.MILLISECONDS)
//            .map { it % listSize + 1 }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                recyclerView.smoothScrollToPosition(it.toInt() + 1)
//            }
//    }
//
//    fun stopAutoScroll() {
//        dispose?.let(Disposable::dispose)
//    }


}
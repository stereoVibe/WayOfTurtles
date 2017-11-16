//package io.sokolvault.wayofturtles.ui
//
//import android.arch.lifecycle.LiveData
//
//open class AbsentLiveData<T> private constructor() : LiveData<T>() {
//    init {
//        postValue(null)
//    }
//
//    companion object {
//        fun <T> create(): LiveData<T> {
//
//            return AbsentLiveData()
//        }
//    }
//}
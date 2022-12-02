package com.example.mvplec_46.activites.userlist

import com.example.mvplec_46.callback.DataRespones
import com.example.mvplec_46.network.ApiClient
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(var view: MainView) {

     fun loadUserList() {

         view.showProgress()


        var  callback = ApiClient.init().getUserList(1)

        callback.enqueue(object : Callback<DataRespones> {
            override fun onResponse(
                call: retrofit2.Call<DataRespones>,
                response: Response<DataRespones>
            ) {

                view.hideProgress()

                if (response.isSuccessful){

                    var res = response.body()
                    res?.let {
                        view.success(it.userList)


                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<DataRespones>, t: Throwable) {

                view.failure("something went wrong")
                view.hideProgress()
            }

        })
    }

}
package id.rdev.DaftarBelanja.activity.data_barang.presenter

import id.rdev.DaftarBelanja.activity.data_barang.data.ResultDataBarang
import id.rdev.DaftarBelanja.model.Barang
import id.rdev.DaftarBelanja.model.User
import id.rdev.DaftarBelanja.network.NetworkConfig
import id.rdev.DaftarBelanja.response.ResultSimple
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataBarangPresenter(val dataBarangView: DataBarangView) {
    fun getDataBarang(user: User?) {
        NetworkConfig.service()
            .getDataBarang(user?.idUser)
            .enqueue(object: Callback<ResultDataBarang>{
                override fun onFailure(call: Call<ResultDataBarang>, t: Throwable) {
                    dataBarangView.onErrorDataBarang(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResultDataBarang>,
                    response: Response<ResultDataBarang>
                ) {
                    val body = response.body()
                    if (body?.status == 200) {
                        dataBarangView.onSuccessDataBarang(body.barang)
                    } else {
                        dataBarangView.onErrorDataBarang(body?.message)
                    }
                }
            })
    }

    fun deleteBarang(user: User?, barang: Barang?) {
        NetworkConfig.service()
            .deleteBarang(user?.idUser, Integer.parseInt(barang?.idBarang), barang?.namaBarang)
            .enqueue(object: Callback<ResultSimple>{
                override fun onFailure(call: Call<ResultSimple>, t: Throwable) {
                    dataBarangView.onErrorDeleteBarang(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                ) {
                    val body = response.body()
                    if (body?.status == 200) {
                        dataBarangView.onSuccessDeleteBarang(body.message)
                    } else {
                        dataBarangView.onErrorDeleteBarang(body?.message)
                    }
                }
            })
    }
}
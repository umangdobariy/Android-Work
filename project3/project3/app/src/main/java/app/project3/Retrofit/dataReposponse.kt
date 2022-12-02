package app.project3.Retrofit

import androidx.core.internal.view.SupportMenu
import com.google.gson.annotations.SerializedName

data class dataReposponse(
    var page:Int,
    @SerializedName("per_page")
    var perPage:Int,
    var total:Int,
    @SerializedName("total_pages")
    var totalPage:Int,
    var support:Support,
    @SerializedName("data")
    var dataList:MutableList<Data>
) {

}
package com.example.singlehotel.fragmentimport

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.singlehotel.R
import com.example.singlehotel.fragment.*
import com.example.singlehotel.fragment.EditProfileFragment
import com.example.singlehotel.util.*
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.lang.Exception

io.github.inflationx.viewpump.ViewPump.Builder.addInterceptor
import io.github.inflationx.viewpump.ViewPump.Builder.build
import com.facebook.CallbackManager.Factory.create
import com.facebook.FacebookException.toString
import com.facebook.CallbackManager.onActivityResult
import com.facebook.GraphRequest.parameters
import com.facebook.GraphRequest.executeAsync
import com.facebook.AccessToken.isExpired
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import com.example.singlehotel.rest.ApiClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import com.example.singlehotel.response.AppRP
import com.example.singlehotel.response.AboutUsRP
import com.example.singlehotel.response.PrivacyPolicyRP
import com.example.singlehotel.response.TermsConditionsRP
import com.example.singlehotel.response.FaqRP
import com.example.singlehotel.response.LoginRP
import com.example.singlehotel.response.RegisterRP
import com.example.singlehotel.response.DataRP
import com.example.singlehotel.response.ProfileRP
import retrofit2.http.Multipart
import okhttp3.RequestBody
import okhttp3.MultipartBody
import com.example.singlehotel.response.ContactRP
import com.example.singlehotel.response.HomeRP
import com.example.singlehotel.response.RoomRP
import com.example.singlehotel.response.RoomDetailRP
import com.example.singlehotel.response.ReviewRP
import com.example.singlehotel.response.UserReviewRP
import com.example.singlehotel.response.UserReviewSubmitRP
import com.example.singlehotel.response.BookingRoomRP
import com.example.singlehotel.response.GalleryCatRP
import com.example.singlehotel.response.GalleryListRP
import com.example.singlehotel.response.FacilitiesRP
import com.example.singlehotel.response.LocationRP
import com.example.singlehotel.interfaces.OnClick
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.startapp.sdk.adsbase.StartAppSDK
import com.startapp.sdk.adsbase.StartAppAd
import com.applovin.sdk.AppLovinSdk
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.startapp.sdk.adsbase.adlisteners.AdEventListener
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.startapp.sdk.ads.banner.Banner
import com.applovin.mediation.ads.MaxAdView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.facebook.login.LoginManager
import com.example.singlehotel.activity.MainActivity
import kotlin.jvm.JvmOverloads
import com.example.singlehotel.util.TouchImageView.Fling
import com.example.singlehotel.util.TouchImageView.ZoomVariables
import com.example.singlehotel.util.TouchImageView.OnTouchImageViewListener
import com.example.singlehotel.util.TouchImageView.ScaleListener
import com.example.singlehotel.util.TouchImageView.GestureListener
import com.example.singlehotel.util.TouchImageView.PrivateOnTouchListener
import com.example.singlehotel.util.TouchImageView.DoubleTapZoom
import com.example.singlehotel.util.TouchImageView.CompatScroller
import com.onesignal.OneSignal
import com.example.singlehotel.util.YouApplication.NotificationExtenderExample
import io.github.inflationx.viewpump.ViewPump
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.calligraphy3.CalligraphyConfig
import com.onesignal.OneSignal.OSNotificationOpenedHandler
import com.onesignal.OSNotificationOpenedResult
import androidx.viewpager.widget.ViewPager
import com.example.singlehotel.util.EnchantedViewPager.EnchantedViewPagerSwipeListener
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.singlehotel.util.EnchantedViewPager.SWIPE_DIRECTION
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.singlehotel.item.RoomList
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener
import com.startapp.sdk.ads.nativead.StartAppNativeAd
import com.startapp.sdk.ads.nativead.NativeAdPreferences
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.github.ornolfr.ratingview.RatingView
import com.google.android.material.textview.MaterialTextView
import androidx.cardview.widget.CardView
import com.example.singlehotel.item.ReviewList
import com.example.singlehotel.activity.ViewImage
import com.example.singlehotel.item.RoomAmenitiesList
import com.example.singlehotel.item.HomeBanner
import androidx.viewpager.widget.PagerAdapter
import com.example.singlehotel.item.GalleryList
import com.example.singlehotel.item.FacilitiesList
import com.example.singlehotel.item.GalleryDetailList
import com.example.singlehotel.item.RoomSlider
import androidx.appcompat.app.AppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import com.google.android.material.appbar.MaterialToolbar
import com.google.gson.JsonObject
import com.google.gson.Gson
import com.example.singlehotel.rest.ApiInterface
import cn.refactor.library.SmoothCheckBox
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.facebook.CallbackManager
import com.google.android.material.button.MaterialButton
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult
import com.facebook.FacebookException
import com.example.singlehotel.activity.TermsConditions
import com.example.singlehotel.activity.ForgetPassword
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.google.android.material.card.MaterialCardView
import androidx.appcompat.widget.AppCompatSpinner
import com.example.singlehotel.adapter.SliderRoomDetailAdapter
import com.example.singlehotel.adapter.RoomAmenities
import com.example.singlehotel.activity.BookRoom
import com.example.singlehotel.adapter.ReviewAdapter
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.google.ads.consent.ConsentInfoUpdateListener
import com.google.ads.consent.ConsentFormListener
import androidx.appcompat.app.AppCompatDelegate
import com.facebook.AccessToken
import com.example.singlehotel.activity.RoomDetail
import com.example.singlehotel.adapter.GalleryDetailAdapter
import com.example.singlehotel.adapter.GalleryListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.singlehotel.adapter.SliderAdapter
import com.example.singlehotel.activity.AboutUs
import com.example.singlehotel.adapter.RoomAdapter
import com.example.singlehotel.adapter.GalleryAdapter
import com.example.singlehotel.activity.GalleryDetail
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import org.greenrobot.eventbus.Subscribe
import com.example.singlehotel.util.Events.ProfileUpdate
import com.google.android.material.switchmaterial.SwitchMaterial
import com.example.singlehotel.activity.Faq
import com.example.singlehotel.activity.PrivacyPolicy
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.example.singlehotel.item.ContactList
import com.example.singlehotel.adapter.FacilitiesAdapter
import com.google.android.material.textfield.TextInputLayout

class EditProfileFragment : Fragment() {
    private var method: Method? = null
    private var imageProfile: String? = null
    private var progressBar: ProgressBar? = null
    private var progressDialog: ProgressDialog? = null
    private var imageViewUser: CircleImageView? = null
    private var imageViewEdit: ImageView? = null
    private var imm: InputMethodManager? = null
    private var buttonSubmit: MaterialButton? = null
    private var textViewName: MaterialTextView? = null
    private var textInputEmail: TextInputLayout? = null
    private var conMain: ConstraintLayout? = null
    private var conNoData: ConstraintLayout? = null
    private var isProfile = false
    private var isRemove = false
    private var editTextName: TextInputEditText? = null
    private var editTextEmail: TextInputEditText? = null
    private var editTextPhoneNo: TextInputEditText? = null
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.edit_profile_fragment, container, false)
        if (MainActivity.Companion.toolbar != null) {
            MainActivity.Companion.toolbar.setTitle(resources.getString(R.string.edit_profile))
        }
        GlobalBus.getBus().register(this)
        method = Method(activity)
        progressDialog = ProgressDialog(activity)
        imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        conMain = view.findViewById(R.id.con_main_editPro)
        conNoData = view.findViewById(R.id.con_noDataFound)
        progressBar = view.findViewById(R.id.progressbar_edit_profile)
        imageViewUser = view.findViewById(R.id.imageView_user_edit_profile)
        imageViewEdit = view.findViewById(R.id.imageView_editPro)
        textViewName = view.findViewById(R.id.textView_name_editPro)
        editTextName = view.findViewById(R.id.editText_name_edit_profile)
        editTextEmail = view.findViewById(R.id.editText_email_edit_profile)
        editTextPhoneNo = view.findViewById(R.id.editText_phone_edit_profile)
        textInputEmail = view.findViewById(R.id.textInput_email_edit_profile)
        buttonSubmit = view.findViewById(R.id.button_edit_profile)
        if (method!!.isDarkMode) {
            imageViewEdit.setImageDrawable(resources.getDrawable(R.drawable.ic_add_profile))
        } else {
            imageViewEdit.setImageDrawable(resources.getDrawable(R.drawable.ic_add_profile_white))
        }
        if (method!!.getLoginType() == "google" || method!!.getLoginType() == "facebook") {
            editTextName.setFocusable(false)
            editTextName.setCursorVisible(false)
        }
        conMain.setVisibility(View.GONE)
        conNoData.setVisibility(View.GONE)
        progressBar.setVisibility(View.GONE)
        if (method!!.isNetworkAvailable) {
            profile(method!!.userId())
        } else {
            method!!.alertBox(resources.getString(R.string.internet_connection))
        }
        return view
    }

    @Subscribe
    fun getData(proImage: Events.ProImage) {
        isProfile = proImage.isProfile
        isRemove = proImage.isRemove
        if (proImage.isProfile) {
            imageProfile = proImage.imagePath
            val uri = Uri.fromFile(File(imageProfile))
            Glide.with(activity!!.applicationContext).load(uri)
                .placeholder(R.drawable.user_profile)
                .into(imageViewUser!!)
        }
        if (proImage.isRemove) {
            Glide.with(activity!!.applicationContext).load(R.drawable.user_profile)
                .placeholder(R.drawable.user_profile)
                .into(imageViewUser!!)
        }
    }

    private fun save() {
        val name = editTextName!!.text.toString()
        val phoneNo = editTextPhoneNo!!.text.toString()
        editTextName!!.error = null
        editTextPhoneNo!!.error = null
        if (name == "" || name.isEmpty()) {
            editTextName!!.requestFocus()
            editTextName!!.error = resources.getString(R.string.please_enter_name)
        } else if (phoneNo == "" || phoneNo.isEmpty()) {
            editTextPhoneNo!!.requestFocus()
            editTextPhoneNo!!.error = resources.getString(R.string.please_enter_phone)
        } else {
            if (method!!.isNetworkAvailable) {
                editTextName!!.clearFocus()
                editTextPhoneNo!!.clearFocus()
                imm!!.hideSoftInputFromWindow(editTextName!!.windowToken, 0)
                imm!!.hideSoftInputFromWindow(editTextPhoneNo!!.windowToken, 0)
                profileUpdate(method!!.userId(), name, phoneNo, imageProfile)
            } else {
                method!!.alertBox(resources.getString(R.string.internet_connection))
            }
        }
    }

    fun profile(userId: String?) {
        if (activity != null) {
            progressBar!!.visibility = View.VISIBLE
            val jsObj = Gson().toJsonTree(API(activity)) as JsonObject
            jsObj.addProperty("user_id", userId)
            jsObj.addProperty("method_name", "user_profile")
            val apiService = ApiClient.getClient().create(
                ApiInterface::class.java
            )
            val call = apiService.getProfile(API.Companion.toBase64(jsObj.toString()))
            call.enqueue(object : Callback<ProfileRP?> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<ProfileRP?>, response: Response<ProfileRP?>) {
                    val statusCode = response.code()
                    if (activity != null) {
                        try {
                            val profileRP = response.body()!!
                            if (profileRP.status == "1") {
                                imageProfile = profileRP.user_image
                                Glide.with(activity!!.applicationContext).load(profileRP.user_image)
                                    .placeholder(R.drawable.user_profile).into(
                                        imageViewUser!!
                                    )
                                textViewName!!.text = profileRP.name
                                editTextName!!.setText(profileRP.name)
                                if (profileRP.email == "") {
                                    textInputEmail!!.visibility = View.GONE
                                } else {
                                    textInputEmail!!.visibility = View.VISIBLE
                                    editTextEmail!!.setText(profileRP.email)
                                }
                                editTextPhoneNo!!.setText(profileRP.phone)
                                imageViewEdit!!.setOnClickListener { v: View? ->
                                    val fragment: BottomSheetDialogFragment = ProImage()
                                    fragment.show(
                                        activity!!.supportFragmentManager,
                                        "Bottom Sheet Dialog Fragment"
                                    )
                                }
                                imageViewUser!!.setOnClickListener { V: View? ->
                                    val fragment: BottomSheetDialogFragment = ProImage()
                                    fragment.show(
                                        activity!!.supportFragmentManager,
                                        "Bottom Sheet Dialog Fragment"
                                    )
                                }
                                buttonSubmit!!.setOnClickListener { v: View? -> save() }
                                conMain!!.visibility = View.VISIBLE
                            } else if (profileRP.status == "2") {
                                method!!.suspend(profileRP.message)
                            } else {
                                conNoData!!.visibility = View.VISIBLE
                                method!!.alertBox(profileRP.message)
                            }
                        } catch (e: Exception) {
                            Log.d("exception_error", e.toString())
                            method!!.alertBox(resources.getString(R.string.failed_try_again))
                        }
                    }
                    progressBar!!.visibility = View.GONE
                }

                override fun onFailure(call: Call<ProfileRP?>, t: Throwable) {
                    // Log error here since request failed
                    Log.e("fail", t.toString())
                    conNoData!!.visibility = View.VISIBLE
                    progressBar!!.visibility = View.GONE
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
            })
        }
    }

    private fun profileUpdate(
        id: String?,
        sendName: String,
        sendPhone: String,
        profileImage: String?
    ) {
        if (activity != null) {
            progressDialog!!.show()
            progressDialog!!.setMessage(resources.getString(R.string.loading))
            progressDialog!!.setCancelable(false)
            var body: MultipartBody.Part? = null
            val jsObj = Gson().toJsonTree(API(activity)) as JsonObject
            jsObj.addProperty("user_id", id)
            jsObj.addProperty("name", sendName)
            jsObj.addProperty("phone", sendPhone)
            jsObj.addProperty("is_remove", isRemove)
            jsObj.addProperty("method_name", "user_profile_update")
            if (isProfile) {
                val requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), File(profileImage))
                // MultipartBody.Part is used to send also the actual file name
                body = MultipartBody.Part.createFormData(
                    "user_image",
                    File(profileImage).name,
                    requestFile
                )
            }
            // add another part within the multipart request
            val requestBody_data: RequestBody = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                API.Companion.toBase64(jsObj.toString())
            )
            val apiService = ApiClient.getClient().create(
                ApiInterface::class.java
            )
            val call = apiService.editProfile(requestBody_data, body)
            call.enqueue(object : Callback<DataRP?> {
                override fun onResponse(call: Call<DataRP?>, response: Response<DataRP?>) {
                    val statusCode = response.code()
                    if (activity != null) {
                        try {
                            val dataRP = response.body()!!
                            if (dataRP.status == "1") {
                                if (dataRP.success == "1") {
                                    val profileUpdate = ProfileUpdate("")
                                    GlobalBus.getBus().post(profileUpdate)
                                    activity!!.supportFragmentManager.popBackStack()
                                }
                                Toast.makeText(activity, dataRP.msg, Toast.LENGTH_SHORT).show()
                            } else if (dataRP.status == "2") {
                                method!!.suspend(dataRP.message)
                            } else {
                                method!!.alertBox(dataRP.message)
                            }
                        } catch (e: Exception) {
                            Log.d("exception_error", e.toString())
                            method!!.alertBox(resources.getString(R.string.failed_try_again))
                        }
                    }
                    progressDialog!!.dismiss()
                }

                override fun onFailure(call: Call<DataRP?>, t: Throwable) {
                    // Log error here since request failed
                    Log.e("fail", t.toString())
                    progressDialog!!.dismiss()
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this)
    }
}
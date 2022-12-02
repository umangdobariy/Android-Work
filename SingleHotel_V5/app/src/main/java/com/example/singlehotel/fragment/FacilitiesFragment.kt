package com.example.singlehotel.fragmentimport

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.singlehotel.R
import com.example.singlehotel.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
import com.example.singlehotel.fragment.HomeFragment
import com.example.singlehotel.fragment.RoomFragment
import com.example.singlehotel.fragment.LocationFragment
import com.example.singlehotel.fragment.GalleryFragment
import com.example.singlehotel.fragment.FacilitiesFragment
import com.example.singlehotel.fragment.ProfileFragment
import com.example.singlehotel.fragment.SettingFragment
import com.google.ads.consent.ConsentInfoUpdateListener
import com.google.ads.consent.ConsentFormListener
import androidx.appcompat.app.AppCompatDelegate
import com.facebook.AccessToken
import com.example.singlehotel.activity.RoomDetail
import com.example.singlehotel.adapter.GalleryDetailAdapter
import com.example.singlehotel.adapter.GalleryListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.singlehotel.adapter.SliderAdapter
import com.example.singlehotel.fragment.ContactUsFragment
import com.example.singlehotel.activity.AboutUs
import com.example.singlehotel.adapter.RoomAdapter
import com.example.singlehotel.adapter.GalleryAdapter
import com.example.singlehotel.activity.GalleryDetail
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import org.greenrobot.eventbus.Subscribe
import com.example.singlehotel.util.Events.ProfileUpdate
import com.example.singlehotel.fragment.EditProfileFragment
import com.example.singlehotel.fragment.ChangePasswordFragment
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

class FacilitiesFragment : Fragment() {
    private var method: Method? = null
    private var progressBar: ProgressBar? = null
    private var webView: WebView? = null
    private var recyclerView: RecyclerView? = null
    private var facilitiesAdapter: FacilitiesAdapter? = null
    private var conMain: ConstraintLayout? = null
    private var conNoData: ConstraintLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(context).inflate(R.layout.facilities_fragment, container, false)
        if (MainActivity.Companion.toolbar != null) {
            MainActivity.Companion.toolbar.setTitle(resources.getString(R.string.facilities))
        }
        method = Method(activity)
        conMain = view.findViewById(R.id.con_facilities_fragment)
        conNoData = view.findViewById(R.id.con_noDataFound)
        progressBar = view.findViewById(R.id.progressBar_facilities_fragment)
        webView = view.findViewById(R.id.webView_facilities_fragment)
        recyclerView = view.findViewById(R.id.recyclerView_facilities_fragment)
        conMain.setVisibility(View.GONE)
        conNoData.setVisibility(View.GONE)
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setFocusable(false)
        recyclerView.setNestedScrollingEnabled(false)
        if (method!!.isNetworkAvailable) {
            facilities()
        } else {
            progressBar.setVisibility(View.GONE)
            method!!.alertBox(resources.getString(R.string.internet_connection))
        }
        return view
    }

    fun facilities() {
        if (activity != null) {
            progressBar!!.visibility = View.VISIBLE
            val jsObj = Gson().toJsonTree(API(activity)) as JsonObject
            jsObj.addProperty("method_name", "get_facilities")
            val apiService = ApiClient.getClient().create(
                ApiInterface::class.java
            )
            val call = apiService.getFacilities(API.Companion.toBase64(jsObj.toString()))
            call.enqueue(object : Callback<FacilitiesRP?> {
                @SuppressLint("SetTextI18n", "SetJavaScriptEnabled")
                override fun onResponse(
                    call: Call<FacilitiesRP?>,
                    response: Response<FacilitiesRP?>
                ) {
                    val statusCode = response.code()
                    if (activity != null) {
                        try {
                            val facilitiesRP = response.body()!!
                            if (facilitiesRP.status == "1") {
                                webView!!.setBackgroundColor(Color.TRANSPARENT)
                                webView!!.isFocusableInTouchMode = false
                                webView!!.isFocusable = false
                                webView!!.settings.defaultTextEncodingName = "UTF-8"
                                webView!!.settings.javaScriptEnabled = true
                                val mimeType = "text/html"
                                val encoding = "utf-8"
                                val text = ("<html dir=" + method!!.isWebViewTextRtl + "><head>"
                                        + "<style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/fonts/poppins_medium.ttf\")}body{font-family: MyFont;color: " + method!!.webViewText() + "line-height:1.6}"
                                        + "a {color:" + method!!.webViewLink() + "text-decoration:none}"
                                        + "</style></head>"
                                        + "<body>"
                                        + facilitiesRP.hotel_info
                                        + "</body></html>")
                                webView!!.loadDataWithBaseURL(null, text, mimeType, encoding, null)
                                if (facilitiesRP.facilitiesLists.size != 0) {
                                    facilitiesAdapter = FacilitiesAdapter(
                                        activity,
                                        "facilities",
                                        facilitiesRP.facilitiesLists
                                    )
                                    recyclerView!!.adapter = facilitiesAdapter
                                }
                                conMain!!.visibility = View.VISIBLE
                            } else {
                                conNoData!!.visibility = View.VISIBLE
                                method!!.alertBox(facilitiesRP.message)
                            }
                        } catch (e: Exception) {
                            Log.d("exception_error", e.toString())
                            method!!.alertBox(resources.getString(R.string.failed_try_again))
                        }
                    }
                    progressBar!!.visibility = View.GONE
                }

                override fun onFailure(call: Call<FacilitiesRP?>, t: Throwable) {
                    // Log error here since request failed
                    Log.e("error_fail", t.toString())
                    conNoData!!.visibility = View.VISIBLE
                    progressBar!!.visibility = View.VISIBLE
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
            })
        }
    }
}
package com.example.singlehotel.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import io.github.inflationx.viewpump.ViewPump.Builder.addInterceptor
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
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.startapp.sdk.adsbase.StartAppSDK
import com.startapp.sdk.adsbase.StartAppAd
import com.applovin.sdk.AppLovinSdk
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
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
import com.example.singlehotel.util.GetPath
import com.example.singlehotel.util.GlobalBus
import kotlin.jvm.JvmOverloads
import com.example.singlehotel.util.TouchImageView.Fling
import com.example.singlehotel.util.TouchImageView.ZoomVariables
import com.example.singlehotel.util.TouchImageView.OnTouchImageViewListener
import com.example.singlehotel.util.TouchImageView.ScaleListener
import com.example.singlehotel.util.TouchImageView.GestureListener
import com.example.singlehotel.util.TouchImageView
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
import com.example.singlehotel.util.EnchantedViewPager
import com.example.singlehotel.util.EnchantedPagerConstants
import com.example.singlehotel.util.EnchantedViewPager.SWIPE_DIRECTION
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.singlehotel.item.RoomList
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
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
import com.example.singlehotel.util.API
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
import com.example.singlehotel.util.EndlessRecyclerViewScrollListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.singlehotel.adapter.SliderAdapter
import com.example.singlehotel.fragment.ContactUsFragment
import com.example.singlehotel.activity.AboutUs
import com.example.singlehotel.adapter.RoomAdapter
import com.example.singlehotel.adapter.GalleryAdapter
import com.example.singlehotel.activity.GalleryDetail
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.singlehotel.R
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
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import com.startapp.sdk.adsbase.Ad
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

class Method {
    var activity: Activity?
    var onClick: OnClick? = null
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    private val myPreference = "SingleHotel"
    var prefLogin = "pref_login"
    private val firstTime = "firstTime"
    var profileId = "profileId"
    var loginType = "loginType"
    var showLogin = "show_login"
    var notification = "notification"
    var themSetting = "them"

    constructor(activity: Activity?) {
        this.activity = activity
        pref = activity!!.getSharedPreferences(myPreference, 0) // 0 - for private mode
        editor = pref.edit()
    }

    constructor(activity: Activity?, onClick: OnClick?) {
        this.activity = activity
        this.onClick = onClick
        pref = activity!!.getSharedPreferences(myPreference, 0) // 0 - for private mode
        editor = pref.edit()
    }

    fun login() {
        if (!pref.getBoolean(firstTime, false)) {
            editor.putBoolean(prefLogin, false)
            editor.putBoolean(firstTime, true)
            editor.commit()
        }
    }

    //user login or not
    val isLogin: Boolean
        get() = pref.getBoolean(prefLogin, false)

    //get login type
    fun getLoginType(): String? {
        return pref.getString(loginType, null)
    }

    //get user id
    fun userId(): String? {
        return pref.getString(profileId, "0")
    }

    //Get theme
    val theme: String?
        get() = pref.getString(themSetting, "system")

    //get device id
    @get:SuppressLint("HardwareIds")
    val deviceId: String
        get() {
            val deviceId: String
            deviceId = try {
                Settings.Secure.getString(activity!!.contentResolver, Settings.Secure.ANDROID_ID)
            } catch (e: Exception) {
                "NotFound"
            }
            return deviceId
        }

    fun forceRTLIfSupported() {
        if (activity!!.resources.getString(R.string.isRTL) == "true") {
            activity!!.window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }

    //rtl
    val isRtl: Boolean
        get() = activity!!.resources.getString(R.string.isRTL) == "true"

    //network check
    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    //google map application installation or not check
    val isAppInstalled: Boolean
        get() {
            val packageName = "com.google.android.apps.maps"
            val mIntent = activity!!.packageManager.getLaunchIntentForPackage(packageName)
            return mIntent != null
        }

    fun changeStatusBarColor() {
        val window = activity!!.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

    val screenWidth: Int
        get() {
            val columnWidth: Int
            val wm = activity!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val point = Point()
            point.x = display.width
            point.y = display.height
            columnWidth = point.x
            return columnWidth
        }
    val isAdmobFBAds: Boolean
        get() = Constant.appRP.banner_ad_type == Constant.AD_TYPE_ADMOB || Constant.appRP.interstitial_ad_type == Constant.AD_TYPE_ADMOB || Constant.appRP.native_ad_type == Constant.AD_TYPE_ADMOB || Constant.appRP.banner_ad_type == Constant.AD_TYPE_FACEBOOK || Constant.appRP.interstitial_ad_type == Constant.AD_TYPE_FACEBOOK || Constant.appRP.native_ad_type == Constant.AD_TYPE_FACEBOOK
    val isStartAppAds: Boolean
        get() = Constant.appRP.banner_ad_type == Constant.AD_TYPE_STARTAPP || Constant.appRP.interstitial_ad_type == Constant.AD_TYPE_STARTAPP || Constant.appRP.native_ad_type == Constant.AD_TYPE_STARTAPP
    val isApplovinAds: Boolean
        get() = Constant.appRP.banner_ad_type == Constant.AD_TYPE_APPLOVIN || Constant.appRP.interstitial_ad_type == Constant.AD_TYPE_APPLOVIN || Constant.appRP.native_ad_type == Constant.AD_TYPE_APPLOVIN

    fun initializeAds() {
        if (isAdmobFBAds) {
            MobileAds.initialize(activity!!) { }
        }
        if (isStartAppAds) {
            StartAppSDK.init(activity!!, Constant.appRP.startapp_app_id, false)
            StartAppAd.disableSplash()
        }
        if (isApplovinAds) {
            if (!AppLovinSdk.getInstance(activity).isInitialized) {
                AppLovinSdk.initializeSdk(activity)
                AppLovinSdk.getInstance(activity).mediationProvider = "max"
            }
        }
    }

    //---------------Interstitial Ad---------------//
    fun onClickAd(position: Int, type: String?, id: String?, title: String?) {
        val progressDialog = ProgressDialog(activity)
        progressDialog.show()
        progressDialog.setMessage(activity!!.resources.getString(R.string.loading))
        progressDialog.setCancelable(false)
        if (Constant.appRP != null) {
            if (Constant.appRP.isInterstitial_ad) {
                Constant.AD_COUNT = Constant.AD_COUNT + 1
                if (Constant.AD_COUNT == Constant.interstitialAdShow) {
                    Constant.AD_COUNT = 0
                    when (Constant.appRP.interstitial_ad_type) {
                        Constant.AD_TYPE_ADMOB, Constant.AD_TYPE_FACEBOOK -> {
                            val builder = AdRequest.Builder()
                            if (personalizationAd) {
                                val extras = Bundle()
                                extras.putString("npa", "1")
                                builder.addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
                            }
                            Constant.AD_COUNT = 0
                            InterstitialAd.load(
                                activity!!,
                                Constant.appRP.interstitial_ad_id,
                                builder.build(),
                                object : InterstitialAdLoadCallback() {
                                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                                        super.onAdLoaded(interstitialAd)
                                        interstitialAd.show(activity!!)
                                        interstitialAd.fullScreenContentCallback =
                                            object : FullScreenContentCallback() {
                                                override fun onAdFailedToShowFullScreenContent(
                                                    adError: AdError
                                                ) {
                                                    super.onAdFailedToShowFullScreenContent(adError)
                                                    progressDialog.dismiss()
                                                    onClick!!.position(position, type, id, title)
                                                }

                                                override fun onAdDismissedFullScreenContent() {
                                                    super.onAdDismissedFullScreenContent()
                                                    progressDialog.dismiss()
                                                    onClick!!.position(position, type, id, title)
                                                }
                                            }
                                    }

                                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                        super.onAdFailedToLoad(loadAdError)
                                        progressDialog.dismiss()
                                        onClick!!.position(position, type, id, title)
                                    }
                                })
                        }
                        Constant.AD_TYPE_STARTAPP -> {
                            Constant.AD_COUNT = 0
                            val startAppAd = StartAppAd(activity!!)
                            startAppAd.loadAd(StartAppAd.AdMode.FULLPAGE, object : AdEventListener {
                                override fun onReceiveAd(ad: Ad) {
                                    startAppAd.showAd(object : AdDisplayListener {
                                        override fun adHidden(ad: Ad) {
                                            progressDialog.dismiss()
                                            onClick!!.position(position, type, id, title)
                                        }

                                        override fun adDisplayed(ad: Ad) {
                                            progressDialog.dismiss()
                                        }

                                        override fun adClicked(ad: Ad) {
                                            progressDialog.dismiss()
                                        }

                                        override fun adNotDisplayed(ad: Ad) {
                                            progressDialog.dismiss()
                                            onClick!!.position(position, type, id, title)
                                        }
                                    })
                                }

                                override fun onFailedToReceiveAd(ad: Ad?) {
                                    progressDialog.dismiss()
                                    onClick!!.position(position, type, id, title)
                                }
                            })
                        }
                        Constant.AD_TYPE_APPLOVIN -> {
                            Constant.AD_COUNT = 0
                            val interstitialAd =
                                MaxInterstitialAd(Constant.appRP.interstitial_ad_id, activity)
                            interstitialAd.loadAd()
                            interstitialAd.setListener(object : MaxAdListener {
                                override fun onAdLoaded(ad: MaxAd) {
                                    interstitialAd.showAd()
                                }

                                override fun onAdDisplayed(ad: MaxAd) {}
                                override fun onAdHidden(ad: MaxAd) {
                                    progressDialog.dismiss()
                                    onClick!!.position(position, type, id, title)
                                }

                                override fun onAdClicked(ad: MaxAd) {}
                                override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
                                    progressDialog.dismiss()
                                    onClick!!.position(position, type, id, title)
                                }

                                override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
                                    progressDialog.dismiss()
                                    onClick!!.position(position, type, id, title)
                                }
                            })
                        }
                    }
                } else {
                    progressDialog.dismiss()
                    onClick!!.position(position, type, id, title)
                }
            } else {
                progressDialog.dismiss()
                onClick!!.position(position, type, id, title)
            }
        } else {
            progressDialog.dismiss()
            onClick!!.position(position, type, id, title)
        }
    }

    //---------------Interstitial Ad---------------//
    //---------------Banner Ad---------------//
    fun showBannerAd(linearLayout: LinearLayout?) {
        if (Constant.appRP != null) {
            if (Constant.appRP.isBanner_ad) {
                when (Constant.appRP.banner_ad_type) {
                    Constant.AD_TYPE_ADMOB, Constant.AD_TYPE_FACEBOOK -> {
                        val mAdView = AdView(
                            activity!!
                        )
                        mAdView.setAdSize(AdSize.BANNER)
                        mAdView.adUnitId = Constant.appRP.banner_ad_id
                        val builder = AdRequest.Builder()
                        if (!personalizationAd) {
                            // load non Personalized ads
                            val extras = Bundle()
                            extras.putString("npa", "1")
                            builder.addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
                        } // else do nothing , it will load PERSONALIZED ads
                        mAdView.loadAd(builder.build())
                        linearLayout!!.addView(mAdView)
                        linearLayout.gravity = Gravity.CENTER
                    }
                    Constant.AD_TYPE_STARTAPP -> {
                        val startAppBanner = Banner(activity)
                        startAppBanner.layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        linearLayout!!.addView(startAppBanner)
                        startAppBanner.loadAd()
                    }
                    Constant.AD_TYPE_APPLOVIN -> {
                        val adView = MaxAdView(Constant.appRP.banner_ad_id, activity)
                        val width = ViewGroup.LayoutParams.MATCH_PARENT
                        val heightPx =
                            activity!!.resources.getDimensionPixelSize(R.dimen.banner_height)
                        adView.layoutParams = FrameLayout.LayoutParams(width, heightPx)
                        linearLayout!!.addView(adView)
                        adView.loadAd()
                    }
                }
            } else {
                linearLayout!!.visibility = View.GONE
            }
        } else {
            linearLayout!!.visibility = View.GONE
        }
    }

    //---------------Banner Ad---------------//
    fun getTempUploadPath(uri: Uri?): String {
        val root = activity!!.externalCacheDir!!.absoluteFile
        return try {
            val filePath = root.path + File.separator + System.currentTimeMillis() + ".jpg"
            val inputStream = activity!!.contentResolver.openInputStream(
                uri!!
            )
            val bm = BitmapFactory.decodeStream(inputStream)
            if (saveBitMap(root, bm, filePath)) {
                filePath
            } else {
                ""
            }
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

    private fun saveBitMap(root: File, Final_bitmap: Bitmap, filePath: String): Boolean {
        if (!root.exists()) {
            val isDirectoryCreated = root.mkdirs()
            if (!isDirectoryCreated) return false
        }
        val pictureFile = File(filePath)
        return try {
            pictureFile.createNewFile()
            val oStream = FileOutputStream(pictureFile)
            Final_bitmap.compress(Bitmap.CompressFormat.PNG, 18, oStream)
            oStream.flush()
            oStream.close()
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    //alert message box
    fun alertBox(message: String?) {
        try {
            if (activity != null) {
                if (!activity!!.isFinishing) {
                    val builder =
                        MaterialAlertDialogBuilder(activity!!, R.style.DialogTitleTextStyle)
                    builder.setMessage(Html.fromHtml(message))
                    builder.setCancelable(false)
                    builder.setPositiveButton(
                        activity!!.resources.getString(R.string.ok)
                    ) { arg0: DialogInterface?, arg1: Int -> }
                    val alertDialog = builder.create()
                    alertDialog.show()
                }
            }
        } catch (e: Exception) {
            Log.d("error_message", e.toString())
        }
    }

    //account suspend
    fun suspend(message: String?) {
        if (isLogin) {
            val loginType = getLoginType()!!
            if (loginType == "google") {

                // Configure sign-in to request the user's ID, email address, and basic
                // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()

                // Build a GoogleSignInClient with the options specified by gso.
                val mGoogleSignInClient = GoogleSignIn.getClient(activity!!, gso)
                mGoogleSignInClient.signOut()
                    .addOnCompleteListener(activity!!) { task: Task<Void?>? -> }
            } else if (loginType == "facebook") {
                LoginManager.getInstance().logOut()
            }
            editor.putBoolean(prefLogin, false)
            editor.commit()
        }
        try {
            if (activity != null) {
                if (!activity!!.isFinishing) {
                    val builder =
                        MaterialAlertDialogBuilder(activity!!, R.style.DialogTitleTextStyle)
                    builder.setMessage(Html.fromHtml(message))
                    builder.setCancelable(false)
                    builder.setPositiveButton(
                        activity!!.resources.getString(R.string.ok)
                    ) { arg0: DialogInterface?, arg1: Int ->
                        activity!!.startActivity(Intent(activity, MainActivity::class.java))
                        activity!!.finishAffinity()
                    }
                    val alertDialog = builder.create()
                    alertDialog.show()
                }
            }
        } catch (e: Exception) {
            Log.d("error_message", e.toString())
        }
    }// Night mode is active, we're using dark theme// Night mode is not active, we're using the light theme

    //check dark mode or not
    val isDarkMode: Boolean
        get() {
            val currentNightMode =
                activity!!.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            return when (currentNightMode) {
                Configuration.UI_MODE_NIGHT_NO ->                 // Night mode is not active, we're using the light theme
                    false
                Configuration.UI_MODE_NIGHT_YES ->                 // Night mode is active, we're using dark theme
                    true
                else -> false
            }
        }

    fun webViewText(): String {
        val color: String
        color = if (isDarkMode) {
            Constant.webTextDark
        } else {
            Constant.webTextLight
        }
        return color
    }

    fun webViewLink(): String {
        val color: String
        color = if (isDarkMode) {
            Constant.webLinkDark
        } else {
            Constant.webLinkLight
        }
        return color
    }

    val isWebViewTextRtl: String
        get() {
            val isRtl: String
            isRtl = if (isRtl) {
                "rtl"
            } else {
                "ltr"
            }
            return isRtl
        }

    companion object {
        var personalizationAd = false
        var loginBack = false
    }
}
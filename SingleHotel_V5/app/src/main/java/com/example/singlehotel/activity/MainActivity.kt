package com.example.singlehotel.activityimport

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.singlehotel.BuildConfig
import com.example.singlehotel.R
import com.example.singlehotel.util.*
import com.google.ads.consent.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL

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

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var method: Method? = null
    private var form: ConsentForm? = null
    private var drawer: DrawerLayout? = null
    private var navigationView: NavigationView? = null
    private var progressBar: ProgressBar? = null
    private var linearLayout: LinearLayout? = null
    private var doubleBackToExitPressedOnce = false
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        method = Method(this@MainActivity)
        method!!.forceRTLIfSupported()
        MainActivity.Companion.toolbar = findViewById<MaterialToolbar>(R.id.toolbar_main)
        setSupportActionBar(MainActivity.Companion.toolbar)
        supportActionBar!!.title = resources.getString(R.string.home)
        drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            MainActivity.Companion.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        MainActivity.Companion.toolbar.setNavigationIcon(R.drawable.ic_side_nav)
        progressBar = findViewById(R.id.progressBar_main)
        navigationView = findViewById(R.id.nav_view)
        linearLayout = findViewById(R.id.linearLayout_adView_main)
        navigationView.setNavigationItemSelectedListener(this)
        if (method!!.isNetworkAvailable) {
            appDetail()
        } else {
            progressBar.setVisibility(View.GONE)
            method!!.alertBox(resources.getString(R.string.internet_connection))
        }
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
            }
            if (supportFragmentManager.backStackEntryCount != 0) {
                val title: String?
                title =
                    if (supportFragmentManager.fragments[supportFragmentManager.backStackEntryCount] !is SupportRequestManagerFragment) {
                        supportFragmentManager.fragments[supportFragmentManager.backStackEntryCount].tag
                    } else {
                        supportFragmentManager.fragments[supportFragmentManager.backStackEntryCount - 1].tag
                    }
                if (title != null) {
                    MainActivity.Companion.toolbar.setTitle(title)
                }
                super.onBackPressed()
            } else {
                doubleBackToExitPressedOnce = true
                Toast.makeText(
                    this,
                    resources.getString(R.string.Please_click_BACK_again_to_exit),
                    Toast.LENGTH_SHORT
                ).show()
                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        //Checking if the item is in checked state or not, if not make it in checked state
        item.isChecked = !item.isChecked

        //Closing drawer on item click
        drawer!!.closeDrawers()

        // Handle navigation view item clicks here.
        val id = item.itemId
        return when (id) {
            R.id.home -> {
                backStackRemove()
                selectDrawerItem(0)
                supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout_main,
                    HomeFragment(), resources.getString(R.string.home)
                ).commitAllowingStateLoss()
                true
            }
            R.id.room -> {
                backStackRemove()
                selectDrawerItem(1)
                supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout_main,
                    RoomFragment(), resources.getString(R.string.room)
                ).commitAllowingStateLoss()
                true
            }
            R.id.location -> {
                backStackRemove()
                selectDrawerItem(2)
                MainActivity.Companion.toolbar.setTitle(resources.getString(R.string.location))
                supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout_main,
                    LocationFragment(), resources.getString(R.string.location)
                ).commitAllowingStateLoss()
                true
            }
            R.id.gallery -> {
                backStackRemove()
                selectDrawerItem(3)
                supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout_main,
                    GalleryFragment(), resources.getString(R.string.gallery)
                ).commitAllowingStateLoss()
                true
            }
            R.id.facilities -> {
                backStackRemove()
                selectDrawerItem(4)
                supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout_main,
                    FacilitiesFragment(), resources.getString(R.string.facilities)
                ).commitAllowingStateLoss()
                true
            }
            R.id.profile -> {
                backStackRemove()
                selectDrawerItem(5)
                supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout_main,
                    ProfileFragment(), resources.getString(R.string.profile)
                ).commitAllowingStateLoss()
                true
            }
            R.id.setting -> {
                backStackRemove()
                selectDrawerItem(6)
                supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout_main,
                    SettingFragment(), resources.getString(R.string.setting)
                ).commitAllowingStateLoss()
                true
            }
            else -> true
        }
    }

    fun appDetail() {
        progressBar!!.visibility = View.VISIBLE
        val jsObj = Gson().toJsonTree(API(this@MainActivity)) as JsonObject
        jsObj.addProperty("method_name", "get_app_details")
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.getAppData(API.Companion.toBase64(jsObj.toString()))
        call.enqueue(object : Callback<AppRP?> {
            override fun onResponse(call: Call<AppRP?>, response: Response<AppRP?>) {
                val statusCode = response.code()
                try {
                    Constant.appRP = response.body()
                    assert(Constant.appRP != null)
                    method!!.initializeAds()
                    if (Constant.appRP.status == "1") {
                        if (Constant.appRP.app_update_status == "true") {
                            if (Constant.appRP.app_new_version > BuildConfig.VERSION_CODE) {
                                showAdDialog(
                                    Constant.appRP.app_update_desc,
                                    Constant.appRP.app_redirect_url,
                                    Constant.appRP.cancel_update_status
                                )
                            }
                        }
                        if (Constant.appRP.interstitial_ad_click == "") {
                            Constant.interstitialAdShow = 0
                        } else {
                            Constant.interstitialAdShow =
                                Constant.appRP.interstitial_ad_click.toInt()
                        }
                        if (Constant.appRP.native_ad_position == "") {
                            Constant.nativeAdPos = 0
                        } else {
                            Constant.nativeAdPos = Constant.appRP.native_ad_position.toInt()
                        }
                        if (method!!.isAdmobFBAds) {
                            checkForConsent()
                        } else {
                            method!!.showBannerAd(linearLayout)
                        }
                        try {
                            supportFragmentManager.beginTransaction().replace(
                                R.id.frameLayout_main, HomeFragment(),
                                resources.getString(R.string.home)
                            ).commitAllowingStateLoss()
                            selectDrawerItem(0)
                        } catch (e: Exception) {
                            Toast.makeText(
                                this@MainActivity, resources.getString(R.string.wrong),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        method!!.alertBox(Constant.appRP.message)
                    }
                } catch (e: Exception) {
                    Log.d("exception_error", e.toString())
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
                progressBar!!.visibility = View.GONE
            }

            override fun onFailure(call: Call<AppRP?>, t: Throwable) {
                // Log error here since request failed
                Log.e("error_fail", t.toString())
                progressBar!!.visibility = View.GONE
                method!!.alertBox(resources.getString(R.string.failed_try_again))
            }
        })
    }

    fun selectDrawerItem(position: Int) {
        navigationView!!.menu.getItem(position).isChecked = true
    }

    fun deselectDrawerItem(position: Int) {
        navigationView!!.menu.getItem(position).isCheckable = false
        navigationView!!.menu.getItem(position).isChecked = false
    }

    fun backStackRemove() {
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }

    fun checkForConsent() {
        val consentInformation = ConsentInformation.getInstance(this@MainActivity)
        val publisherIds = arrayOf(Constant.appRP.publisher_id)
        consentInformation.requestConsentInfoUpdate(
            publisherIds,
            object : ConsentInfoUpdateListener {
                override fun onConsentInfoUpdated(consentStatus: ConsentStatus) {
                    Log.d("consentStatus", consentStatus.toString())
                    when (consentStatus) {
                        ConsentStatus.PERSONALIZED -> {
                            Method.Companion.personalizationAd = true
                            method!!.showBannerAd(linearLayout)
                        }
                        ConsentStatus.NON_PERSONALIZED -> {
                            Method.Companion.personalizationAd = false
                            method!!.showBannerAd(linearLayout)
                        }
                        ConsentStatus.UNKNOWN -> if (ConsentInformation.getInstance(baseContext).isRequestLocationInEeaOrUnknown) {
                            requestConsent()
                        } else {
                            Method.Companion.personalizationAd = true
                            method!!.showBannerAd(linearLayout)
                        }
                        else -> {}
                    }
                }

                override fun onFailedToUpdateConsentInfo(errorDescription: String) {
                    // User's consent status failed to update.
                }
            })
    }

    fun requestConsent() {
        var privacyUrl: URL? = null
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = URL(Constant.appRP.privacy_policy_link)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            // Handle error.
        }
        form = ConsentForm.Builder(this@MainActivity, privacyUrl)
            .withListener(object : ConsentFormListener() {
                override fun onConsentFormLoaded() {
                    showForm()
                    // Consent form loaded successfully.
                }

                override fun onConsentFormOpened() {
                    // Consent form was displayed.
                }

                override fun onConsentFormClosed(
                    consentStatus: ConsentStatus,
                    userPrefersAdFree: Boolean
                ) {
                    Log.d("consentStatus_form", consentStatus.toString())
                    when (consentStatus) {
                        ConsentStatus.PERSONALIZED -> {
                            Method.Companion.personalizationAd = true
                            method!!.showBannerAd(linearLayout)
                        }
                        ConsentStatus.NON_PERSONALIZED, ConsentStatus.UNKNOWN -> {
                            Method.Companion.personalizationAd = false
                            method!!.showBannerAd(linearLayout)
                        }
                    }
                }

                override fun onConsentFormError(errorDescription: String) {
                    Log.d("errorDescription", errorDescription)
                }
            })
            .withPersonalizedAdsOption()
            .withNonPersonalizedAdsOption()
            .build()
        form.load()
    }

    private fun showForm() {
        if (form != null) {
            form!!.show()
        }
    }

    private val bannerAdType: Boolean
        private get() = Constant.appRP.banner_ad_type == "admob"

    private fun showAdDialog(description: String, link: String, isCancel: String) {
        val dialog = Dialog(this@MainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_update_app)
        dialog.setCancelable(false)
        if (method!!.isRtl) {
            dialog.window!!.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window!!.setLayout(
            ViewPager.LayoutParams.MATCH_PARENT,
            ViewPager.LayoutParams.WRAP_CONTENT
        )
        val textViewDescription =
            dialog.findViewById<MaterialTextView>(R.id.textView_description_dialog_update)
        val buttonUpdate = dialog.findViewById<MaterialButton>(R.id.button_update_dialog_update)
        val buttonCancel = dialog.findViewById<MaterialButton>(R.id.button_cancel_dialog_update)
        if (isCancel == "true") {
            buttonCancel.visibility = View.VISIBLE
        } else {
            buttonCancel.visibility = View.GONE
        }
        textViewDescription.text = description
        buttonUpdate.setOnClickListener { v: View? ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
            dialog.dismiss()
        }
        buttonCancel.setOnClickListener { v: View? -> dialog.dismiss() }
        dialog.show()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var toolbar: MaterialToolbar? = null
    }
}
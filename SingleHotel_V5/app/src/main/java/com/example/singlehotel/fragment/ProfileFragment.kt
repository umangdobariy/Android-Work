package com.example.singlehotel.fragmentimport

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.singlehotel.R
import com.example.singlehotel.activity.*
import com.example.singlehotel.util.*
import com.google.android.gms.tasks.Task
import de.hdodenhof.circleimageview.CircleImageView
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
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.google.android.material.card.MaterialCardView
import androidx.appcompat.widget.AppCompatSpinner
import com.example.singlehotel.adapter.SliderRoomDetailAdapter
import com.example.singlehotel.adapter.RoomAmenities
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
import com.example.singlehotel.adapter.GalleryDetailAdapter
import com.example.singlehotel.adapter.GalleryListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.singlehotel.adapter.SliderAdapter
import com.example.singlehotel.fragment.ContactUsFragment
import com.example.singlehotel.adapter.RoomAdapter
import com.example.singlehotel.adapter.GalleryAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import org.greenrobot.eventbus.Subscribe
import com.example.singlehotel.util.Events.ProfileUpdate
import com.example.singlehotel.fragment.EditProfileFragment
import com.example.singlehotel.fragment.ChangePasswordFragment
import com.google.android.material.switchmaterial.SwitchMaterial
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

class ProfileFragment : Fragment() {
    private var method: Method? = null
    private var progressBar: ProgressBar? = null
    private var imageView: CircleImageView? = null
    private var cardViewPass: MaterialCardView? = null
    private var conMain: ConstraintLayout? = null
    private var conNoData: ConstraintLayout? = null
    private var buttonLogin: MaterialButton? = null
    private var buttonLoginLogout: MaterialButton? = null
    private var textViewName: MaterialTextView? = null
    private var textViewNotLogin: MaterialTextView? = null
    private var imageViewLoginType: ImageView? = null
    private var imageViewEdit: ImageView? = null
    private var imageViewData: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.profile_fragment, container, false)
        GlobalBus.getBus().register(this)
        method = Method(activity)
        if (MainActivity.Companion.toolbar != null) {
            MainActivity.Companion.toolbar.setTitle(resources.getString(R.string.profile))
        }
        conMain = view.findViewById(R.id.con_profile)
        conNoData = view.findViewById(R.id.con_not_login)
        progressBar = view.findViewById(R.id.progressbar_profile)
        imageView = view.findViewById(R.id.imageView_pro)
        imageViewLoginType = view.findViewById(R.id.imageView_loginType_pro)
        textViewName = view.findViewById(R.id.textView_name_pro)
        buttonLogin = view.findViewById(R.id.button_not_login)
        buttonLoginLogout = view.findViewById(R.id.button_login_profile)
        imageViewData = view.findViewById(R.id.imageView_not_login)
        textViewNotLogin = view.findViewById(R.id.textView_not_login)
        imageViewEdit = view.findViewById(R.id.imageView_edit_profile)
        cardViewPass = view.findViewById(R.id.cardView_changePassword_pro)
        progressBar.setVisibility(View.GONE)
        data(false, false)
        conMain.setVisibility(View.GONE)
        buttonLogin.setOnClickListener(View.OnClickListener { v: View? ->
            if (method!!.isLogin) {
                logout()
            } else {
                startActivity(Intent(activity, Login::class.java))
                activity!!.finishAffinity()
            }
        })
        callData()
        return view
    }

    private fun callData() {
        if (method!!.isNetworkAvailable) {
            if (method!!.isLogin) {
                profile(method!!.userId())
            } else {
                data(true, true)
            }
        } else {
            method!!.alertBox(resources.getString(R.string.internet_connection))
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun data(isShow: Boolean, isLogin: Boolean) {
        if (isShow) {
            if (isLogin) {
                textViewNotLogin!!.text = resources.getString(R.string.you_have_not_login)
                imageViewData!!.setImageDrawable(resources.getDrawable(R.drawable.no_login))
            } else {
                textViewNotLogin!!.text = resources.getString(R.string.no_data_found)
                imageViewData!!.setImageDrawable(resources.getDrawable(R.drawable.no_data))
                if (method!!.isLogin) {
                    buttonLogin!!.text = resources.getString(R.string.logout)
                } else {
                    buttonLogin!!.text = resources.getString(R.string.login)
                }
            }
            conNoData!!.visibility = View.VISIBLE
        } else {
            conNoData!!.visibility = View.GONE
        }
    }

    @Subscribe
    fun getData(profileUpdate: ProfileUpdate?) {
        if (MainActivity.Companion.toolbar != null) {
            MainActivity.Companion.toolbar.setTitle(resources.getString(R.string.profile))
        }
        data(false, false)
        conMain!!.visibility = View.GONE
        callData()
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
                @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
                override fun onResponse(call: Call<ProfileRP?>, response: Response<ProfileRP?>) {
                    val statusCode = response.code()
                    if (activity != null) {
                        try {
                            val profileRP = response.body()!!
                            if (profileRP.status == "1") {
                                if (profileRP.success == "1") {
                                    val loginType = method!!.getLoginType()
                                    if (loginType == "google" || loginType == "facebook") {
                                        cardViewPass!!.visibility = View.GONE
                                        imageViewLoginType!!.visibility = View.VISIBLE
                                        if (loginType == "google") {
                                            imageViewLoginType!!.setImageDrawable(
                                                resources.getDrawable(
                                                    R.drawable.google_user_pro
                                                )
                                            )
                                        } else {
                                            imageViewLoginType!!.setImageDrawable(
                                                resources.getDrawable(
                                                    R.drawable.fb_user_pro
                                                )
                                            )
                                        }
                                    } else {
                                        cardViewPass!!.visibility = View.VISIBLE
                                        imageViewLoginType!!.visibility = View.GONE
                                    }
                                    Glide.with(activity!!.applicationContext).load(
                                        profileRP.user_image
                                    )
                                        .placeholder(R.drawable.user_profile).into(
                                            imageView!!
                                        )
                                    imageView!!.setOnClickListener { v: View? ->
                                        startActivity(
                                            Intent(
                                                activity, ViewImage::class.java
                                            )
                                                .putExtra("path", profileRP.user_image)
                                        )
                                    }
                                    textViewName!!.text = profileRP.name
                                    imageViewEdit!!.setOnClickListener { v: View? ->
                                        activity!!.supportFragmentManager.beginTransaction().add(
                                            R.id.frameLayout_main,
                                            EditProfileFragment(),
                                            resources.getString(R.string.edit_profile)
                                        )
                                            .addToBackStack(resources.getString(R.string.edit_profile))
                                            .commitAllowingStateLoss()
                                    }
                                    cardViewPass!!.setOnClickListener { v: View? ->
                                        val changePasswordFragment = ChangePasswordFragment()
                                        val bundle = Bundle()
                                        bundle.putString("name", profileRP.name)
                                        bundle.putString("image", profileRP.user_image)
                                        changePasswordFragment.arguments = bundle
                                        activity!!.supportFragmentManager.beginTransaction().add(
                                            R.id.frameLayout_main,
                                            changePasswordFragment,
                                            resources.getString(R.string.change_pass)
                                        )
                                            .addToBackStack(resources.getString(R.string.change_pass))
                                            .commitAllowingStateLoss()
                                    }
                                    if (method!!.isLogin) {
                                        buttonLoginLogout!!.text =
                                            resources.getString(R.string.logout)
                                    } else {
                                        buttonLoginLogout!!.text =
                                            resources.getString(R.string.login)
                                    }
                                    buttonLoginLogout!!.setOnClickListener { v: View? -> logout() }
                                    conMain!!.visibility = View.VISIBLE
                                } else {
                                    method!!.suspend(profileRP.msg)
                                }
                            } else if (profileRP.status == "2") {
                                method!!.suspend(profileRP.message)
                            } else {
                                data(true, false)
                                method!!.alertBox(profileRP.message)
                            }
                        } catch (e: Exception) {
                            data(true, false)
                            method!!.alertBox(resources.getString(R.string.failed_try_again))
                            Log.d("exception_error", e.toString())
                        }
                    }
                    progressBar!!.visibility = View.GONE
                }

                override fun onFailure(call: Call<ProfileRP?>, t: Throwable) {
                    // Log error here since request failed
                    Log.e("fail", t.toString())
                    data(true, false)
                    progressBar!!.visibility = View.GONE
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
            })
        }
    }

    //alert message box
    fun logout() {
        val builder = MaterialAlertDialogBuilder(activity!!, R.style.DialogTitleTextStyle)
        builder.setCancelable(false)
        builder.setMessage(resources.getString(R.string.logout_message))
        builder.setPositiveButton(
            resources.getString(R.string.logout)
        ) { arg0: DialogInterface?, arg1: Int ->
            if (method!!.getLoginType() == "google") {

                // Configure sign-in to request the user's ID, email address, and basic
                // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()

                // Build a GoogleSignInClient with the options specified by gso.
                //Google login
                val mGoogleSignInClient = GoogleSignIn.getClient(activity!!, gso)
                mGoogleSignInClient.signOut()
                    .addOnCompleteListener(activity!!) { task: Task<Void?>? ->
                        method!!.editor.putBoolean(
                            method!!.prefLogin, false
                        )
                        method!!.editor.commit()
                        startActivity(Intent(activity, Login::class.java))
                        activity!!.finishAffinity()
                    }
            } else if (method!!.getLoginType() == "facebook") {
                LoginManager.getInstance().logOut()
                method!!.editor.putBoolean(method!!.prefLogin, false)
                method!!.editor.commit()
                startActivity(Intent(activity, Login::class.java))
                activity!!.finishAffinity()
            } else {
                method!!.editor.putBoolean(method!!.prefLogin, false)
                method!!.editor.commit()
                startActivity(Intent(activity, Login::class.java))
                activity!!.finishAffinity()
            }
        }
        builder.setNegativeButton(
            resources.getString(R.string.cancel)
        ) { dialogInterface: DialogInterface?, i: Int -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this)
    }
}
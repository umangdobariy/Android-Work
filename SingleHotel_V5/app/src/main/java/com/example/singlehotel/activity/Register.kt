package com.example.singlehotel.activityimport

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.singlehotel.R
import com.example.singlehotel.activity.*
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

class Register : AppCompatActivity() {
    private var method: Method? = null
    private var imm: InputMethodManager? = null
    private var progressDialog: ProgressDialog? = null
    private var checkBox: MaterialCheckBox? = null
    private var editTextName: TextInputEditText? = null
    private var editTextEmail: TextInputEditText? = null
    private var editTextPassword: TextInputEditText? = null
    private var editTextConformPassword: TextInputEditText? = null
    private var editTextPhoneNo: TextInputEditText? = null
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        method = Method(this@Register)
        method!!.forceRTLIfSupported()
        progressDialog = ProgressDialog(this@Register)
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        editTextName = findViewById(R.id.editText_name_register)
        editTextEmail = findViewById(R.id.editText_email_register)
        editTextPassword = findViewById(R.id.editText_password_register)
        editTextConformPassword = findViewById(R.id.editText_conform_password_register)
        editTextPhoneNo = findViewById(R.id.editText_phoneNo_register)
        checkBox = findViewById(R.id.checkbox_register)
        val textViewLogin = findViewById<MaterialTextView>(R.id.textView_login_register)
        val textViewTerms = findViewById<MaterialTextView>(R.id.textView_terms_register)
        val buttonSubmit = findViewById<MaterialButton>(R.id.button_submit)
        textViewTerms.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    this@Register,
                    TermsConditions::class.java
                )
            )
        }
        textViewLogin.setOnClickListener { v: View? ->
            startActivity(Intent(this@Register, Login::class.java))
            finishAffinity()
        }
        buttonSubmit.setOnClickListener { v: View? -> form() }
    }

    private fun isValidMail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun form() {
        val name = editTextName!!.text.toString()
        val email = editTextEmail!!.text.toString()
        val password = editTextPassword!!.text.toString()
        val conformPassword = editTextConformPassword!!.text.toString()
        val phoneNo = editTextPhoneNo!!.text.toString()
        editTextName!!.error = null
        editTextEmail!!.error = null
        editTextPassword!!.error = null
        editTextConformPassword!!.error = null
        editTextPhoneNo!!.error = null
        if (name == "" || name.isEmpty()) {
            editTextName!!.requestFocus()
            editTextName!!.error = resources.getString(R.string.please_enter_name)
        } else if (!isValidMail(email) || email.isEmpty()) {
            editTextEmail!!.requestFocus()
            editTextEmail!!.error = resources.getString(R.string.please_enter_email)
        } else if (password == "" || password.isEmpty()) {
            editTextPassword!!.requestFocus()
            editTextPassword!!.error = resources.getString(R.string.please_enter_password)
        } else if (conformPassword == "" || conformPassword.isEmpty()) {
            editTextConformPassword!!.requestFocus()
            editTextConformPassword!!.error =
                resources.getString(R.string.please_enter_confirm_password)
        } else if (phoneNo == "" || phoneNo.isEmpty()) {
            editTextPhoneNo!!.requestFocus()
            editTextPhoneNo!!.error = resources.getString(R.string.please_enter_phone)
        } else if (password != conformPassword) {
            method!!.alertBox(resources.getString(R.string.password_not_match))
        } else if (!checkBox!!.isChecked) {
            method!!.alertBox(resources.getString(R.string.please_select_terms))
        } else {
            editTextName!!.clearFocus()
            editTextEmail!!.clearFocus()
            editTextPassword!!.clearFocus()
            editTextConformPassword!!.clearFocus()
            editTextPhoneNo!!.clearFocus()
            imm!!.hideSoftInputFromWindow(editTextName!!.windowToken, 0)
            imm!!.hideSoftInputFromWindow(editTextEmail!!.windowToken, 0)
            imm!!.hideSoftInputFromWindow(editTextPassword!!.windowToken, 0)
            imm!!.hideSoftInputFromWindow(editTextConformPassword!!.windowToken, 0)
            imm!!.hideSoftInputFromWindow(editTextPhoneNo!!.windowToken, 0)
            if (method!!.isNetworkAvailable) {
                register(name, email, password, phoneNo)
            } else {
                method!!.alertBox(resources.getString(R.string.internet_connection))
            }
        }
    }

    @SuppressLint("HardwareIds")
    fun register(sendName: String?, sendEmail: String?, sendPassword: String?, sendPhone: String?) {
        progressDialog!!.show()
        progressDialog!!.setMessage(resources.getString(R.string.loading))
        progressDialog!!.setCancelable(false)
        val jsObj = Gson().toJsonTree(API(this@Register)) as JsonObject
        jsObj.addProperty("name", sendName)
        jsObj.addProperty("email", sendEmail)
        jsObj.addProperty("password", sendPassword)
        jsObj.addProperty("phone", sendPhone)
        jsObj.addProperty("device_id", method.getDeviceId())
        jsObj.addProperty("type", "normal")
        jsObj.addProperty("method_name", "user_register")
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.getRegister(API.Companion.toBase64(jsObj.toString()))
        call.enqueue(object : Callback<RegisterRP?> {
            override fun onResponse(call: Call<RegisterRP?>, response: Response<RegisterRP?>) {
                val statusCode = response.code()
                try {
                    val registerRP = response.body()!!
                    if (registerRP.status == "1") {
                        if (registerRP.success == "1") {
                            editTextName!!.setText("")
                            editTextEmail!!.setText("")
                            editTextPassword!!.setText("")
                            editTextConformPassword!!.setText("")
                            editTextPhoneNo!!.setText("")
                            Toast.makeText(this@Register, registerRP.msg, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@Register, Login::class.java))
                            finishAffinity()
                        } else {
                            method!!.alertBox(registerRP.msg)
                        }
                    } else {
                        method!!.alertBox(registerRP.message)
                    }
                } catch (e: Exception) {
                    Log.d("exception_error", e.toString())
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
                progressDialog!!.dismiss()
            }

            override fun onFailure(call: Call<RegisterRP?>, t: Throwable) {
                // Log error here since request failed
                Log.e("fail", t.toString())
                progressDialog!!.dismiss()
                method!!.alertBox(resources.getString(R.string.failed_try_again))
            }
        })
    }
}
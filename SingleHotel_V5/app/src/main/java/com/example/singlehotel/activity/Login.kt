package com.example.singlehotel.activityimport

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import com.example.singlehotel.R
import com.example.singlehotel.activity.*
import com.example.singlehotel.activity.Login
import com.example.singlehotel.util.*
import com.google.android.gms.tasks.Task
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*

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

class Login : AppCompatActivity() {
    private var method: Method? = null
    private var checkBox: SmoothCheckBox? = null
    private var checkBoxTerms: MaterialCheckBox? = null
    private var editTextEmail: TextInputEditText? = null
    private var editTextPassword: TextInputEditText? = null
    private var progressDialog: ProgressDialog? = null

    //Google login
    var mGoogleSignInClient: GoogleSignInClient? = null

    //Facebook login
    private var callbackManager: CallbackManager? = null
    private var imm: InputMethodManager? = null
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        method = Method(this@Login)
        method!!.forceRTLIfSupported()
        Login.Companion.pref =
            getSharedPreferences(Login.Companion.my_preference, 0) // 0 - for private mode
        Login.Companion.editor = Login.Companion.pref.edit()
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        progressDialog = ProgressDialog(this@Login)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        //facebook button
        callbackManager = create()
        editTextEmail = findViewById(R.id.editText_email_login)
        editTextPassword = findViewById(R.id.editText_password_login)
        val buttonLogin = findViewById<MaterialButton>(R.id.button_login)
        val llGoogleSign = findViewById<LinearLayout>(R.id.ll_google_login)
        val frameLayoutFbSign = findViewById<FrameLayout>(R.id.frameLayout_login)
        val buttonSkip = findViewById<MaterialButton>(R.id.button_skip_login)
        val textViewSignUp = findViewById<MaterialTextView>(R.id.textView_register_login)
        val textViewFp = findViewById<MaterialTextView>(R.id.textView_fp_login)
        val textViewTerms = findViewById<MaterialTextView>(R.id.textView_terms_login)
        checkBoxTerms = findViewById(R.id.checkbox_terms_login)
        checkBox = findViewById(R.id.checkbox_login)
        checkBox.setChecked(false)
        if (Login.Companion.pref.getBoolean(Login.Companion.pref_check, false)) {
            editTextEmail.setText(Login.Companion.pref.getString(Login.Companion.pref_email, null))
            editTextPassword.setText(
                Login.Companion.pref.getString(
                    Login.Companion.pref_password,
                    null
                )
            )
            checkBox.setChecked(true)
        } else {
            editTextEmail.setText("")
            editTextPassword.setText("")
            checkBox.setChecked(false)
        }
        checkBox.setOnCheckedChangeListener(SmoothCheckBox.OnCheckedChangeListener { checkBox: SmoothCheckBox?, isChecked: Boolean ->
            if (isChecked) {
                Login.Companion.editor.putString(
                    Login.Companion.pref_email,
                    editTextEmail.getText().toString()
                )
                Login.Companion.editor.putString(
                    Login.Companion.pref_password,
                    editTextPassword.getText().toString()
                )
                Login.Companion.editor.putBoolean(Login.Companion.pref_check, true)
            } else {
                Login.Companion.editor.putBoolean(Login.Companion.pref_check, false)
            }
            Login.Companion.editor.commit()
        })
        buttonLogin.setOnClickListener { v: View? -> login() }
        llGoogleSign.setOnClickListener { v: View? ->
            if (checkBoxTerms.isChecked()) {
                signIn()
            } else {
                method!!.alertBox(resources.getString(R.string.please_select_terms))
            }
        }
        frameLayoutFbSign.setOnClickListener { v: View ->
            if (checkBoxTerms.isChecked()) {
                if (v === frameLayoutFbSign) {
                    LoginManager.getInstance().logInWithReadPermissions(
                        this@Login, Arrays.asList<String>(
                            Login.Companion.EMAIL
                        )
                    )
                }
            } else {
                method!!.alertBox(resources.getString(R.string.please_select_terms))
            }
        }
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    fbUser(loginResult)
                }

                override fun onCancel() {}
                override fun onError(error: FacebookException) {
                    Toast.makeText(this@Login, error.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        textViewSignUp.setOnClickListener { v: View? ->
            Method.Companion.loginBack = false
            startActivity(Intent(this@Login, Register::class.java))
        }
        textViewTerms.setOnClickListener { v: View? ->
            Method.Companion.loginBack = false
            startActivity(Intent(this@Login, TermsConditions::class.java))
        }
        buttonSkip.setOnClickListener { v: View? ->
            if (Method.Companion.loginBack) {
                Method.Companion.loginBack = false
                onBackPressed()
            } else {
                startActivity(Intent(this@Login, MainActivity::class.java))
                finish()
            }
        }
        textViewFp.setOnClickListener { v: View? ->
            Method.Companion.loginBack = false
            startActivity(Intent(this@Login, ForgetPassword::class.java))
        }
    }

    //Google login
    private fun signIn() {
        if (method!!.isNetworkAvailable) {
            val signInIntent = mGoogleSignInClient!!.signInIntent
            startActivityForResult(signInIntent, Login.Companion.RC_SIGN_IN)
        } else {
            method!!.alertBox(resources.getString(R.string.internet_connection))
        }
    }

    //Google login get callback
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == Login.Companion.RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    //Google login
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )!!
            val id = account.id
            val name = account.displayName
            val email = account.email
            registerSocialNetwork(id, name, email, "google")
        } catch (e: ApiException) {
            Log.d("error_data", e.toString())
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
        }
    }

    //facebook login get email and name
    private fun fbUser(loginResult: LoginResult) {
        val graphRequest =
            GraphRequest.newMeRequest(loginResult.accessToken, object : GraphJSONObjectCallback() {
                override fun onCompleted(`object`: JSONObject?, response: GraphResponse?) {
                    try {
                        val id = `object`!!.getString("id")
                        val name = `object`.getString("name")
                        val email = `object`.getString("email")
                        registerSocialNetwork(id, name, email, "facebook")
                    } catch (e: JSONException) {
                        try {
                            val id = `object`!!.getString("id")
                            val name = `object`.getString("name")
                            registerSocialNetwork(id, name, "", "facebook")
                        } catch (e1: JSONException) {
                            e1.printStackTrace()
                        }
                    }
                }
            })
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email") // Parameters that we ask for facebook
        graphRequest.parameters = parameters
        graphRequest.executeAsync()
    }

    private fun isValidMail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun login() {
        val email = editTextEmail!!.text.toString()
        val password = editTextPassword!!.text.toString()
        editTextEmail!!.error = null
        editTextPassword!!.error = null
        if (!isValidMail(email) || email.isEmpty()) {
            editTextEmail!!.requestFocus()
            editTextEmail!!.error = resources.getString(R.string.please_enter_email)
        } else if (password.isEmpty()) {
            editTextPassword!!.requestFocus()
            editTextPassword!!.error = resources.getString(R.string.please_enter_password)
        } else {
            editTextEmail!!.clearFocus()
            editTextPassword!!.clearFocus()
            imm!!.hideSoftInputFromWindow(editTextEmail!!.windowToken, 0)
            imm!!.hideSoftInputFromWindow(editTextPassword!!.windowToken, 0)
            if (method!!.isNetworkAvailable) {
                login(email, password, "normal")
            } else {
                method!!.alertBox(resources.getString(R.string.internet_connection))
            }
        }
    }

    fun login(sendEmail: String?, sendPassword: String?, type: String?) {
        progressDialog!!.show()
        progressDialog!!.setMessage(resources.getString(R.string.loading))
        progressDialog!!.setCancelable(false)
        val jsObj = Gson().toJsonTree(API(this@Login)) as JsonObject
        jsObj.addProperty("email", sendEmail)
        jsObj.addProperty("password", sendPassword)
        jsObj.addProperty("method_name", "user_login")
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.getLogin(API.Companion.toBase64(jsObj.toString()))
        call.enqueue(object : Callback<LoginRP?> {
            override fun onResponse(call: Call<LoginRP?>, response: Response<LoginRP?>) {
                val statusCode = response.code()
                try {
                    val loginRP = response.body()!!
                    if (loginRP.status == "1") {
                        if (loginRP.success == "1") {
                            if (checkBox!!.isChecked) {
                                Login.Companion.editor.putString(
                                    Login.Companion.pref_email,
                                    editTextEmail!!.text.toString()
                                )
                                Login.Companion.editor.putString(
                                    Login.Companion.pref_password,
                                    editTextPassword!!.text.toString()
                                )
                                Login.Companion.editor.putBoolean(Login.Companion.pref_check, true)
                                Login.Companion.editor.commit()
                            }
                            method!!.editor.putBoolean(method!!.prefLogin, true)
                            method!!.editor.putString(method!!.profileId, loginRP.user_id)
                            method!!.editor.putString(method!!.loginType, type)
                            method!!.editor.commit()
                            editTextEmail!!.setText("")
                            editTextPassword!!.setText("")
                            if (Method.Companion.loginBack) {
                                Method.Companion.loginBack = false
                                onBackPressed()
                            } else {
                                startActivity(
                                    Intent(this@Login, MainActivity::class.java)
                                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                )
                                finishAffinity()
                            }
                        } else {
                            method!!.alertBox(loginRP.msg)
                        }
                    } else {
                        method!!.alertBox(loginRP.message)
                    }
                } catch (e: Exception) {
                    Log.d("exception_error", e.toString())
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
                progressDialog!!.dismiss()
            }

            override fun onFailure(call: Call<LoginRP?>, t: Throwable) {
                // Log error here since request failed
                Log.e("fail", t.toString())
                progressDialog!!.dismiss()
                method!!.alertBox(resources.getString(R.string.failed_try_again))
            }
        })
    }

    @SuppressLint("HardwareIds")
    fun registerSocialNetwork(id: String?, sendName: String?, sendEmail: String?, type: String) {
        progressDialog!!.show()
        progressDialog!!.setMessage(resources.getString(R.string.loading))
        progressDialog!!.setCancelable(false)
        val jsObj = Gson().toJsonTree(API(this@Login)) as JsonObject
        jsObj.addProperty("name", sendName)
        jsObj.addProperty("email", sendEmail)
        jsObj.addProperty("auth_id", id)
        jsObj.addProperty("device_id", method.getDeviceId())
        jsObj.addProperty("type", type)
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
                            method!!.editor.putBoolean(method!!.prefLogin, true)
                            method!!.editor.putString(method!!.profileId, registerRP.user_id)
                            method!!.editor.putString(method!!.loginType, type)
                            method!!.editor.commit()
                            Toast.makeText(this@Login, registerRP.msg, Toast.LENGTH_SHORT).show()
                            if (Method.Companion.loginBack) {
                                Method.Companion.loginBack = false
                                onBackPressed()
                            } else {
                                startActivity(Intent(this@Login, MainActivity::class.java))
                                finishAffinity()
                            }
                        } else {
                            failLogin(type)
                            method!!.alertBox(registerRP.msg)
                        }
                    } else {
                        failLogin(type)
                        method!!.alertBox(registerRP.message)
                    }
                } catch (e: Exception) {
                    Log.d("exception_error", e.toString())
                    failLogin(type)
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
                progressDialog!!.dismiss()
            }

            override fun onFailure(call: Call<RegisterRP?>, t: Throwable) {
                // Log error here since request failed
                Log.e("fail", t.toString())
                failLogin(type)
                progressDialog!!.dismiss()
                method!!.alertBox(resources.getString(R.string.failed_try_again))
            }
        })
    }

    private fun failLogin(type: String) {
        if (type == "google") {
            mGoogleSignInClient!!.signOut()
                .addOnCompleteListener(this@Login) { task: Task<Void?>? ->
                    method!!.editor.putBoolean(
                        method!!.prefLogin, false
                    )
                    method!!.editor.commit()
                }
        } else {
            LoginManager.getInstance().logOut()
        }
    }

    companion object {
        const val my_preference = "login_single_hotel"
        const val pref_email = "email"
        const val pref_password = "password"
        const val pref_check = "pref_check"
        private val pref: SharedPreferences? = null
        private val editor: SharedPreferences.Editor? = null
        private const val RC_SIGN_IN = 7
        private const val EMAIL = "email"
    }
}
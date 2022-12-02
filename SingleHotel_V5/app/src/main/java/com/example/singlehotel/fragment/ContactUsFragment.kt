package com.example.singlehotel.fragmentimport

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.singlehotel.R
import com.example.singlehotel.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.ArrayList

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

class ContactUsFragment : Fragment() {
    var toolbar: MaterialToolbar? = null
    private var method: Method? = null
    private var spinner: Spinner? = null
    private var imm: InputMethodManager? = null
    private var con: ConstraintLayout? = null
    private var conNoData: ConstraintLayout? = null
    private var buttonSubmit: MaterialButton? = null
    private var progressDialog: ProgressDialog? = null
    private var contactLists: MutableList<ContactList>? = null
    private var contactType: String? = null
    private var contactId: String? = null
    private var editTextName: TextInputEditText? = null
    private var editTextEmail: TextInputEditText? = null
    private var editTextPhoneNO: TextInputEditText? = null
    private var editTextMessage: TextInputEditText? = null
    private var textViewTitle: MaterialTextView? = null
    private var textViewAdd: MaterialTextView? = null
    private var textViewEmail: MaterialTextView? = null
    private var textViewPhone: MaterialTextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(context).inflate(R.layout.contactus_fragment, container, false)
        if (MainActivity.Companion.toolbar != null) {
            MainActivity.Companion.toolbar.setTitle(resources.getString(R.string.contact_us))
        }
        method = Method(activity)
        contactLists = ArrayList()
        progressDialog = ProgressDialog(activity)
        imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        con = view.findViewById(R.id.con_contactUS)
        conNoData = view.findViewById(R.id.con_noDataFound)
        textViewTitle = view.findViewById(R.id.textView_title_contactUS)
        textViewAdd = view.findViewById(R.id.textView_add_contactUS)
        textViewEmail = view.findViewById(R.id.textView_email_contactUS)
        textViewPhone = view.findViewById(R.id.textView_phone_contactUS)
        spinner = view.findViewById(R.id.spinner_contact_us)
        editTextName = view.findViewById(R.id.editText_name_contactUS)
        editTextEmail = view.findViewById(R.id.editText_email_contactUS)
        editTextPhoneNO = view.findViewById(R.id.editText_phoneNo_contactUS)
        editTextMessage = view.findViewById(R.id.editText_des_contactUS)
        buttonSubmit = view.findViewById(R.id.button_contactUs)
        con.setVisibility(View.GONE)
        conNoData.setVisibility(View.GONE)
        if (method!!.isNetworkAvailable) {
            if (method!!.isLogin) {
                getContact(method!!.userId())
            } else {
                getContact("0")
            }
        } else {
            method!!.alertBox(resources.getString(R.string.internet_connection))
        }
        return view
    }

    private fun isValidMail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun getContact(userId: String?) {
        if (activity != null) {
            contactLists!!.clear()
            progressDialog!!.show()
            progressDialog!!.setMessage(resources.getString(R.string.loading))
            progressDialog!!.setCancelable(false)
            val jsObj = Gson().toJsonTree(API(activity)) as JsonObject
            jsObj.addProperty("user_id", userId)
            jsObj.addProperty("method_name", "get_contact")
            val apiService = ApiClient.getClient().create(
                ApiInterface::class.java
            )
            val call = apiService.getContactSub(API.Companion.toBase64(jsObj.toString()))
            call.enqueue(object : Callback<ContactRP?> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<ContactRP?>, response: Response<ContactRP?>) {
                    val statusCode = response.code()
                    if (activity != null) {
                        try {
                            val contactRP = response.body()!!
                            if (contactRP.status == "1") {
                                textViewTitle!!.text = contactRP.hotel_name
                                textViewAdd!!.text = contactRP.hotel_address
                                textViewEmail!!.text = contactRP.hotel_email
                                textViewPhone!!.text = contactRP.hotel_phone
                                editTextName!!.setText(contactRP.name)
                                editTextEmail!!.setText(contactRP.email)
                                editTextPhoneNO!!.setText(contactRP.phone)
                                contactLists!!.add(
                                    ContactList(
                                        "",
                                        resources.getString(R.string.select_contact_subject)
                                    )
                                )
                                contactLists!!.addAll(contactRP.contactLists)

                                // Spinner Drop down elements
                                val strings: MutableList<String> = ArrayList()
                                for (i in contactLists!!.indices) {
                                    strings.add(contactLists!![i].subject)
                                }

                                // Creating adapter for spinner_cat
                                val dataAdapter = ArrayAdapter(
                                    activity!!, android.R.layout.simple_spinner_item, strings
                                )
                                // Drop down layout style - list view with radio button
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                // attaching data adapter to spinner_cat
                                spinner!!.adapter = dataAdapter
                                con!!.visibility = View.VISIBLE

                                // Spinner click listener
                                spinner!!.onItemSelectedListener =
                                    object : AdapterView.OnItemSelectedListener {
                                        override fun onItemSelected(
                                            parent: AdapterView<*>,
                                            view: View?,
                                            position: Int,
                                            id: Long
                                        ) {
                                            if (position == 0) {
                                                (parent.getChildAt(0) as TextView).setTextColor(
                                                    resources.getColor(R.color.textView_contactUs)
                                                )
                                            } else {
                                                (parent.getChildAt(0) as TextView).setTextColor(
                                                    resources.getColor(R.color.textView_app_color)
                                                )
                                            }
                                            contactType = contactLists!![position].subject
                                            contactId = contactLists!![position].id
                                        }

                                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                                    }
                                buttonSubmit!!.setOnClickListener { v: View? -> form() }
                            } else if (contactRP.status == "2") {
                                method!!.suspend(contactRP.message)
                            } else {
                                conNoData!!.visibility = View.VISIBLE
                                method!!.alertBox(contactRP.message)
                            }
                        } catch (e: Exception) {
                            Log.d("exception_error", e.toString())
                            method!!.alertBox(resources.getString(R.string.failed_try_again))
                        }
                    }
                    progressDialog!!.dismiss()
                }

                override fun onFailure(call: Call<ContactRP?>, t: Throwable) {
                    // Log error here since request failed
                    Log.e("fail", t.toString())
                    conNoData!!.visibility = View.VISIBLE
                    progressDialog!!.dismiss()
                    method!!.alertBox(resources.getString(R.string.failed_try_again))
                }
            })
        }
    }

    fun form() {
        val name = editTextName!!.text.toString()
        val email = editTextEmail!!.text.toString()
        val phoneNO = editTextPhoneNO!!.text.toString()
        val message = editTextMessage!!.text.toString()
        editTextName!!.error = null
        editTextEmail!!.error = null
        editTextPhoneNO!!.error = null
        editTextMessage!!.error = null
        if (contactType == resources.getString(R.string.select_contact_subject) || contactType == "" || contactType!!.isEmpty()) {
            method!!.alertBox(resources.getString(R.string.please_select_subject))
        } else if (name == "" || name.isEmpty()) {
            editTextName!!.requestFocus()
            editTextName!!.error = resources.getString(R.string.please_enter_name)
        } else if (!isValidMail(email) || email.isEmpty()) {
            editTextEmail!!.requestFocus()
            editTextEmail!!.error = resources.getString(R.string.please_enter_email)
        } else if (phoneNO == "" || phoneNO.isEmpty()) {
            editTextPhoneNO!!.requestFocus()
            editTextPhoneNO!!.error = resources.getString(R.string.please_enter_phone)
        } else if (message == "" || message.isEmpty()) {
            editTextMessage!!.requestFocus()
            editTextMessage!!.error = resources.getString(R.string.please_enter_message)
        } else {
            editTextName!!.clearFocus()
            editTextEmail!!.clearFocus()
            editTextPhoneNO!!.clearFocus()
            editTextMessage!!.clearFocus()
            imm!!.hideSoftInputFromWindow(editTextName!!.windowToken, 0)
            imm!!.hideSoftInputFromWindow(editTextEmail!!.windowToken, 0)
            imm!!.hideSoftInputFromWindow(editTextPhoneNO!!.windowToken, 0)
            imm!!.hideSoftInputFromWindow(editTextMessage!!.windowToken, 0)
            if (method!!.isNetworkAvailable) {
                contactUs(name, email, phoneNO, message, contactId)
            } else {
                method!!.alertBox(resources.getString(R.string.internet_connection))
            }
        }
    }

    fun contactUs(
        sendName: String?,
        sendEmail: String?,
        sendPhoneNo: String?,
        sendMessage: String?,
        contact_subject: String?
    ) {
        if (activity != null) {
            progressDialog!!.show()
            progressDialog!!.setMessage(resources.getString(R.string.loading))
            progressDialog!!.setCancelable(false)
            val jsObj = Gson().toJsonTree(API(activity)) as JsonObject
            jsObj.addProperty("contact_name", sendName)
            jsObj.addProperty("contact_email", sendEmail)
            jsObj.addProperty("contact_phone_no", sendPhoneNo)
            jsObj.addProperty("contact_msg", sendMessage)
            jsObj.addProperty("contact_subject", contact_subject)
            jsObj.addProperty("method_name", "user_contact_us")
            val apiService = ApiClient.getClient().create(
                ApiInterface::class.java
            )
            val call = apiService.submitContact(API.Companion.toBase64(jsObj.toString()))
            call.enqueue(object : Callback<DataRP?> {
                override fun onResponse(call: Call<DataRP?>, response: Response<DataRP?>) {
                    val statusCode = response.code()
                    if (activity != null) {
                        try {
                            val dataRP = response.body()!!
                            if (dataRP.status == "1") {
                                if (dataRP.success == "1") {
                                    editTextName!!.setText("")
                                    editTextEmail!!.setText("")
                                    editTextPhoneNO!!.setText("")
                                    editTextMessage!!.setText("")
                                    spinner!!.setSelection(0)
                                    conformDialog(dataRP.msg)
                                } else {
                                    method!!.alertBox(dataRP.msg)
                                }
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

    private fun conformDialog(message: String) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_contact_us)
        dialog.setCancelable(false)
        if (method!!.isRtl) {
            dialog.window!!.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window!!.setLayout(
            ViewPager.LayoutParams.MATCH_PARENT,
            ViewPager.LayoutParams.WRAP_CONTENT
        )
        val textViewMessage =
            dialog.findViewById<MaterialTextView>(R.id.textView_message_dialog_contactUS)
        val button = dialog.findViewById<MaterialButton>(R.id.button_dialog_contactUS)
        textViewMessage.text = message
        button.setOnClickListener { v: View? -> dialog.dismiss() }
        dialog.show()
    }
}
package com.example.singlehotel.fragmentimport

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.singlehotel.R
import com.example.singlehotel.activity.*
import com.example.singlehotel.util.*
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

class SettingFragment : Fragment() {
    private var method: Method? = null
    private var themMode: String? = null
    @SuppressLint("NonConstantResourceId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.setting_fragment, container, false)
        if (MainActivity.Companion.toolbar != null) {
            MainActivity.Companion.toolbar.setTitle(resources.getString(R.string.setting))
        }
        method = Method(activity)
        val switchMaterial = view.findViewById<SwitchMaterial>(R.id.switch_setting)
        val textViewContactUs = view.findViewById<MaterialTextView>(R.id.textView_contactUs_setting)
        val textViewFaq = view.findViewById<MaterialTextView>(R.id.textView_faq_setting)
        val textViewTerms = view.findViewById<MaterialTextView>(R.id.textView_terms_setting)
        val textViewShareApp = view.findViewById<MaterialTextView>(R.id.textView_shareApp_setting)
        val textViewRateApp = view.findViewById<MaterialTextView>(R.id.textView_rateApp_setting)
        val textViewMoreApp = view.findViewById<MaterialTextView>(R.id.textView_moreApp_setting)
        val textViewPrivacyPolicy =
            view.findViewById<MaterialTextView>(R.id.textView_privacy_policy_setting)
        val textViewAboutUs = view.findViewById<MaterialTextView>(R.id.textView_aboutUs_setting)
        val textViewThemType = view.findViewById<MaterialTextView>(R.id.textView_themType_setting)
        val conThem = view.findViewById<ConstraintLayout>(R.id.con_them_setting)
        val imageView = view.findViewById<ImageView>(R.id.imageView_them_setting)
        if (method!!.isDarkMode) {
            Glide.with(activity!!.applicationContext).load(R.drawable.mode_dark)
                .placeholder(R.drawable.placeholder_portable)
                .into(imageView)
        } else {
            Glide.with(activity!!.applicationContext).load(R.drawable.mode_icon)
                .placeholder(R.drawable.placeholder_portable)
                .into(imageView)
        }
        switchMaterial.isChecked = method!!.pref.getBoolean(method!!.notification, true)
        switchMaterial.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            OneSignal.unsubscribeWhenNotificationsAreDisabled(isChecked)
            method!!.editor.putBoolean(method!!.notification, isChecked)
            method!!.editor.commit()
        }
        when (method.getTheme()) {
            "system" -> textViewThemType.text = resources.getString(R.string.system_default)
            "light" -> textViewThemType.text = resources.getString(R.string.light)
            "dark" -> textViewThemType.text = resources.getString(R.string.dark)
        }
        textViewFaq.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    activity, Faq::class.java
                )
            )
        }
        textViewTerms.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    activity, TermsConditions::class.java
                )
            )
        }
        textViewShareApp.setOnClickListener { v: View? -> shareApp() }
        textViewRateApp.setOnClickListener { v: View? -> rateApp() }
        textViewMoreApp.setOnClickListener { v: View? -> moreApp() }
        textViewAboutUs.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    activity, AboutUs::class.java
                )
            )
        }
        textViewPrivacyPolicy.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    activity, PrivacyPolicy::class.java
                )
            )
        }
        textViewContactUs.setOnClickListener { v: View? ->
            activity!!.supportFragmentManager.beginTransaction().add(
                R.id.frameLayout_main, ContactUsFragment(),
                resources.getString(R.string.contact_us)
            ).addToBackStack(resources.getString(R.string.contact_us))
                .commitAllowingStateLoss()
        }
        conThem.setOnClickListener { v: View? ->
            val dialog = Dialog(requireActivity())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialogbox_them)
            if (method!!.isRtl) {
                dialog.window!!.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
            }
            dialog.window!!.setLayout(
                ViewPager.LayoutParams.FILL_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            val radioGroup = dialog.findViewById<RadioGroup>(R.id.radioGroup_them)
            val textViewOk = dialog.findViewById<MaterialTextView>(R.id.textView_ok_them)
            val textViewCancel = dialog.findViewById<MaterialTextView>(R.id.textView_cancel_them)
            val them1 = method!!.pref.getString(method!!.themSetting, "system")!!
            when (them1) {
                "system" -> radioGroup.check(radioGroup.getChildAt(0).id)
                "light" -> radioGroup.check(radioGroup.getChildAt(1).id)
                "dark" -> radioGroup.check(radioGroup.getChildAt(2).id)
            }
            radioGroup.setOnCheckedChangeListener { group: RadioGroup, checkedId: Int ->
                val rb = group.findViewById<MaterialRadioButton>(checkedId)
                if (null != rb && checkedId > -1) {
                    when (checkedId) {
                        R.id.radioButton_system_them -> themMode = "system"
                        R.id.radioButton_light_them -> themMode = "light"
                        R.id.radioButton_dark_them -> themMode = "dark"
                        else -> {}
                    }
                }
            }
            textViewOk.setOnClickListener { viewOk: View? ->
                method!!.editor.putString(method!!.themSetting, themMode)
                method!!.editor.commit()
                dialog.dismiss()
                startActivity(Intent(activity, SplashScreen::class.java))
                activity!!.finishAffinity()
            }
            textViewCancel.setOnClickListener { viewCancel: View? -> dialog.dismiss() }
            dialog.show()
        }
        return view
    }

    private fun rateApp() {
        val uri = Uri.parse("market://details?id=" + activity!!.application.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + activity!!.application.packageName)
                )
            )
        }
    }

    private fun moreApp() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    resources.getString(R.string.play_more_app)
                )
            )
        )
    }

    private fun shareApp() {
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, "My application name")
            var sAux = """
                
                ${resources.getString(R.string.Let_me_recommend_you_this_application)}
                
                
                """.trimIndent()
            sAux =
                sAux + "https://play.google.com/store/apps/details?id=" + activity!!.application.packageName
            i.putExtra(Intent.EXTRA_TEXT, sAux)
            startActivity(Intent.createChooser(i, "choose one"))
        } catch (e: Exception) {
            //e.toString();
        }
    }
}
package com.example.singlehotel.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
import com.example.singlehotel.util.*
import com.google.android.gms.ads.*
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.google.android.material.textfield.TextInputLayout
import com.startapp.sdk.adsbase.Ad
import java.lang.Exception

class RoomAdapter(
    private val activity: Activity?,
    private val roomLists: List<RoomList?>?,
    private val type: String,
    onClick: OnClick?
) : RecyclerView.Adapter<Any?>() {
    private val method: Method
    private val columnWidth: Int
    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_ITEM = 1
    private val VIEW_TYPE_ADS = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(activity).inflate(R.layout.room_adapter, parent, false)
            return ViewHolder(view)
        } else if (viewType == VIEW_TYPE_LOADING) {
            val v =
                LayoutInflater.from(activity).inflate(R.layout.layout_loading_item, parent, false)
            return ProgressViewHolder(v)
        } else if (viewType == VIEW_TYPE_ADS) {
            val v = LayoutInflater.from(activity).inflate(R.layout.layout_ads, parent, false)
            return AdOption(v)
        }
        return null
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder!!.itemViewType == VIEW_TYPE_ITEM) {
            val viewHolder = holder as ViewHolder?
            viewHolder!!.imageViewRoom.layoutParams =
                ConstraintLayout.LayoutParams(columnWidth, columnWidth / 2)
            viewHolder.view.layoutParams =
                ConstraintLayout.LayoutParams(columnWidth, columnWidth / 2)
            Glide.with(activity!!).load(roomLists!![position]!!.room_image_thumb)
                .placeholder(R.drawable.placeholder_landscape)
                .into(viewHolder.imageViewRoom)
            viewHolder.textViewRoomName.text = roomLists[position]!!.room_name
            viewHolder.textViewPrice.text = roomLists[position]!!.room_price
            viewHolder.textViewTotalRate.text = "(" + roomLists[position]!!.total_rate + ")"
            viewHolder.ratingBar.rating = roomLists[position]!!.rate_avg.toFloat()
            viewHolder.textViewTotalRate.setTypeface(null)
            viewHolder.constraintLayout.setOnClickListener { v: View? ->
                method.onClickAd(
                    position, type, roomLists[position]!!
                        .id, roomLists[position]!!.room_name
                )
            }
        } else if (holder.itemViewType == VIEW_TYPE_ADS) {
            val adOption = holder as AdOption?
            if (adOption!!.rl_native_ad.childCount == 0 && Constant.appRP.isNative_ad && !adOption.isAdRequested) {
                adOption.isAdRequested = true
                when (Constant.appRP.native_ad_type) {
                    Constant.AD_TYPE_ADMOB, Constant.AD_TYPE_FACEBOOK -> {
                        val adView = activity!!.layoutInflater.inflate(
                            R.layout.layout_native_ad_admob,
                            null
                        ) as NativeAdView
                        val adLoader = AdLoader.Builder(activity, Constant.appRP.native_ad_id)
                            .forNativeAd { nativeAd: NativeAd ->
                                populateUnifiedNativeAdView(nativeAd, adView)
                                adOption.rl_native_ad.removeAllViews()
                                adOption.rl_native_ad.addView(adView)
                                adOption.card_view.visibility = View.VISIBLE
                            }
                            .build()
                        val builder = AdRequest.Builder()
                        if (Method.Companion.personalizationAd) {
                            val extras = Bundle()
                            extras.putString("npa", "1")
                            builder.addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
                        }
                        adLoader.loadAd(builder.build())
                    }
                    Constant.AD_TYPE_STARTAPP -> {
                        val nativeAd = StartAppNativeAd(activity!!)
                        nativeAd.loadAd(NativeAdPreferences()
                            .setAdsNumber(1)
                            .setAutoBitmapDownload(true)
                            .setPrimaryImageSize(2), object : AdEventListener {
                            override fun onReceiveAd(ad: Ad) {
                                try {
                                    if (nativeAd.nativeAds.size > 0) {
                                        val nativeAdView = activity.layoutInflater.inflate(
                                            R.layout.layout_native_ad_startapp,
                                            null
                                        ) as RelativeLayout
                                        val icon = nativeAdView.findViewById<ImageView>(R.id.icon)
                                        val title = nativeAdView.findViewById<TextView>(R.id.title)
                                        val description =
                                            nativeAdView.findViewById<TextView>(R.id.description)
                                        val button = nativeAdView.findViewById<Button>(R.id.button)
                                        Glide.with(activity)
                                            .load(nativeAd.nativeAds[0].imageUrl)
                                            .into(icon)
                                        title.text = nativeAd.nativeAds[0].title
                                        description.text = nativeAd.nativeAds[0].description
                                        button.text =
                                            if (nativeAd.nativeAds[0].isApp) "Install" else "Open"
                                        adOption.rl_native_ad.removeAllViews()
                                        adOption.rl_native_ad.addView(nativeAdView)
                                        adOption.card_view.visibility = View.VISIBLE
                                    }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }

                            override fun onFailedToReceiveAd(ad: Ad?) {
                                adOption.isAdRequested = false
                            }
                        })
                    }
                    Constant.AD_TYPE_APPLOVIN -> {
                        val nativeAdLoader =
                            MaxNativeAdLoader(Constant.appRP.native_ad_id, activity)
                        nativeAdLoader.setNativeAdListener(object : MaxNativeAdListener() {
                            override fun onNativeAdLoaded(
                                nativeAdView: MaxNativeAdView?,
                                ad: MaxAd
                            ) {
                                nativeAdView!!.setPadding(0, 0, 0, 10)
                                nativeAdView.setBackgroundColor(Color.WHITE)
                                adOption.rl_native_ad.removeAllViews()
                                adOption.rl_native_ad.addView(nativeAdView)
                                adOption.card_view.visibility = View.VISIBLE
                            }

                            override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
                                adOption.isAdRequested = false
                            }

                            override fun onNativeAdClicked(ad: MaxAd) {}
                        })
                        nativeAdLoader.loadAd()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return roomLists!!.size + 1
    }

    fun hideHeader() {
        ProgressViewHolder.progressBar.visibility = View.GONE
    }

    private fun isHeader(position: Int): Boolean {
        return position == roomLists!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (roomLists!!.size == position) {
            VIEW_TYPE_LOADING
        } else if (roomLists[position] == null) {
            VIEW_TYPE_ADS
        } else {
            VIEW_TYPE_ITEM
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view: View
        val ratingBar: RatingView
        val imageViewRoom: ImageView
        val constraintLayout: ConstraintLayout
        val textViewRoomName: MaterialTextView
        val textViewPrice: MaterialTextView
        val textViewTotalRate: MaterialTextView

        init {
            constraintLayout = itemView.findViewById(R.id.con_room_adapter)
            imageViewRoom = itemView.findViewById(R.id.imageView_room_adapter)
            view = itemView.findViewById(R.id.view_room_adapter)
            textViewRoomName = itemView.findViewById(R.id.textView_roomName_room_adapter)
            textViewPrice = itemView.findViewById(R.id.textView_roomPrice_room_adapter)
            textViewTotalRate = itemView.findViewById(R.id.textView_totalRate_room_adapter)
            ratingBar = itemView.findViewById(R.id.ratingBar_room_adapter)
        }
    }

    class ProgressViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        companion object {
            var progressBar: ProgressBar
        }

        init {
            progressBar = v.findViewById(R.id.progressBar_loading)
        }
    }

    inner class AdOption(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card_view: CardView
        val rl_native_ad: RelativeLayout
        val isAdRequested = false

        init {
            card_view = itemView.findViewById(R.id.card_view)
            rl_native_ad = itemView.findViewById(R.id.rl_native_ad)
        }
    }

    private fun populateUnifiedNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        // Set the media view. Media content will be automatically populated in the media view once
        // adView.setNativeAd() is called.
        val mediaView = adView.findViewById<MediaView>(R.id.ad_media)
        adView.mediaView = mediaView

        // Set other ad assets.
        adView.headlineView =
            adView.findViewById(R.id.ad_headline)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.callToActionView =
            adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_icon)
        adView.priceView = adView.findViewById(R.id.ad_price)
        adView.starRatingView =
            adView.findViewById(R.id.ad_stars)
        adView.storeView = adView.findViewById(R.id.ad_store)
        adView.advertiserView =
            adView.findViewById(R.id.ad_advertiser)

        // The headline is guaranteed to be in every UnifiedNativeAd.
        (adView.headlineView as TextView?)!!.text = nativeAd.headline

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.body == null) {
            adView.bodyView!!.visibility = View.INVISIBLE
        } else {
            adView.bodyView!!.visibility = View.VISIBLE
            (adView.bodyView as TextView?)!!.text = nativeAd.body
        }
        if (nativeAd.callToAction == null) {
            adView.callToActionView!!.visibility = View.INVISIBLE
        } else {
            adView.callToActionView!!.visibility = View.VISIBLE
            (adView.callToActionView as Button?)!!.text = nativeAd.callToAction
        }
        if (nativeAd.icon == null) {
            adView.iconView!!.visibility = View.GONE
        } else {
            (adView.iconView as ImageView?)!!.setImageDrawable(
                nativeAd.icon!!.drawable
            )
            adView.iconView!!.visibility = View.VISIBLE
        }
        if (nativeAd.price == null) {
            adView.priceView!!.visibility = View.INVISIBLE
        } else {
            adView.priceView!!.visibility = View.VISIBLE
            (adView.priceView as TextView?)!!.text = nativeAd.price
        }
        if (nativeAd.store == null) {
            adView.storeView!!.visibility = View.INVISIBLE
        } else {
            adView.storeView!!.visibility = View.VISIBLE
            (adView.storeView as TextView?)!!.text = nativeAd.store
        }
        if (nativeAd.starRating == null) {
            adView.starRatingView!!.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar?)
                .setRating(nativeAd.starRating!!.toFloat())
            adView.starRatingView!!.visibility = View.VISIBLE
        }
        if (nativeAd.advertiser == null) {
            adView.advertiserView!!.visibility = View.INVISIBLE
        } else {
            (adView.advertiserView as TextView?)!!.text = nativeAd.advertiser
            adView.advertiserView!!.visibility = View.VISIBLE
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad. The SDK will populate the adView's MediaView
        // with the media content from this native ad.
        adView.setNativeAd(nativeAd)
    }

    init {
        method = Method(activity, onClick)
        columnWidth = method.screenWidth
    }
}
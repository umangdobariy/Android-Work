package com.example.singlehotel.util

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
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
import androidx.core.view.MotionEventCompat
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
import com.google.android.material.textfield.TextInputLayout

class EnchantedViewPager : ViewPager {
    private var mSwipeThreshold //to avoid single touchs
            = 0f
    private var mUseAlpha = false
    private var mUseScale = false
    private var mUseSwipe = false

    //variables to help swipe movements interpretation
    private var lastYactionDown = 0f
    private var originalDragXposition = 0f
    private var originalDragYposition = 0f
    private var dragStarted = false
    private var swipeListener: EnchantedViewPagerSwipeListener? = null

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    private fun init() {
        useAlpha()
        useScale()
        addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (positionOffset >= 0f && positionOffset <= 1f) {
                    val curPage = findViewWithTag<View>(ENCHANTED_VIEWPAGER_POSITION + position)
                    if (curPage != null) {
                        if (mUseScale) {
                            curPage.scaleY =
                                EnchantedPagerConstants.BIG_SCALE - EnchantedPagerConstants.DIFF_SCALE * positionOffset
                            curPage.scaleX =
                                EnchantedPagerConstants.BIG_SCALE - EnchantedPagerConstants.DIFF_SCALE * positionOffset
                        }
                        if (mUseAlpha) {
                            curPage.alpha = 1.0f - 0.5f * positionOffset
                        }
                    }
                    val nextPage =
                        findViewWithTag<View>(ENCHANTED_VIEWPAGER_POSITION + (position + 1))
                    if (nextPage != null) {
                        if (mUseScale) {
                            nextPage.scaleY =
                                EnchantedPagerConstants.SMALL_SCALE + EnchantedPagerConstants.DIFF_SCALE * positionOffset
                            nextPage.scaleX =
                                EnchantedPagerConstants.SMALL_SCALE + EnchantedPagerConstants.DIFF_SCALE * positionOffset
                        }
                        if (mUseAlpha) {
                            nextPage.alpha = 0.5f + 0.5f * positionOffset
                        }
                    }
                    val previousPage =
                        findViewWithTag<View>(ENCHANTED_VIEWPAGER_POSITION + (position - 1))
                    if (previousPage != null) {
                        if (mUseScale) {
                            previousPage.scaleY =
                                EnchantedPagerConstants.SMALL_SCALE + EnchantedPagerConstants.DIFF_SCALE * positionOffset
                            previousPage.scaleX =
                                EnchantedPagerConstants.SMALL_SCALE + EnchantedPagerConstants.DIFF_SCALE * positionOffset
                        }
                        if (mUseAlpha) {
                            previousPage.alpha = 0.5f + 0.5f * positionOffset
                        }
                    }
                    val nextAfterPage =
                        findViewWithTag<View>(ENCHANTED_VIEWPAGER_POSITION + (position + 2))
                    if (nextAfterPage != null) {
                        if (mUseScale) {
                            nextAfterPage.scaleX = EnchantedPagerConstants.SMALL_SCALE
                            nextAfterPage.scaleY = EnchantedPagerConstants.SMALL_SCALE
                        }
                        if (mUseAlpha) {
                            nextAfterPage.alpha = 0.5f
                        }
                    }
                }
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    //TouchEvent used for swipe actions
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!mUseSwipe) return super.onTouchEvent(event)
        val action = MotionEventCompat.getActionMasked(event)
        val position = currentItem
        val currPage = findViewWithTag<View>(ENCHANTED_VIEWPAGER_POSITION + position)
            ?: return super.onTouchEvent(event)
        mSwipeThreshold = (currPage.height / 4).toFloat()
        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                originalDragXposition = currPage.x
                originalDragYposition = currPage.y
                lastYactionDown = event.y
                super.onTouchEvent(event)
            }
            MotionEvent.ACTION_MOVE -> {
                if (!dragStarted && checkSwipe(event.y)) {
                    dragStarted = true
                }
                if (dragStarted) {
                    onDrag(event.y, currPage)
                    true
                } else {
                    super.onTouchEvent(event)
                }
            }
            MotionEvent.ACTION_UP -> {
                dragStarted = false
                val dismissed = checkDismiss(event.y, currPage)
                if (!dismissed) {
                    currPage.x = originalDragXposition
                    currPage.y = originalDragYposition
                }
                super.onTouchEvent(event)
            }
            else -> super.onTouchEvent(event)
        }
    }

    private fun checkDismiss(y: Float, view: View): Boolean {
        val viewDismissThreshold = (view.height / 2).toFloat()
        if (originalDragYposition < y) {
            if (y - lastYactionDown > viewDismissThreshold) {
                onSwipe(SWIPE_DIRECTION.SWIPE_DOWN, view)
                return true
            }
        } else {
            if (lastYactionDown - y > viewDismissThreshold) {
                onSwipe(SWIPE_DIRECTION.SWIPE_UP, view)
                return true
            }
        }
        return false
    }

    private fun checkSwipe(eventY: Float): Boolean {
        if (lastYactionDown < eventY) { //swipe down
            //check if the user swiped long enough
            if (eventY - lastYactionDown > mSwipeThreshold) {
                return true
            }
        } else { // swipe up
            //check if the user swiped long enough
            if (lastYactionDown - eventY > mSwipeThreshold) {
                return true
            }
        }
        return false
    }

    private fun onDrag(y: Float, view: View) {
        view.x = originalDragXposition
        view.y = y - view.height / 2
    }

    private fun onSwipe(direction: SWIPE_DIRECTION, view: View) {
        var translationValue = 0f
        translationValue = when (direction) {
            SWIPE_DIRECTION.SWIPE_UP -> -view.height.toFloat()
            SWIPE_DIRECTION.SWIPE_DOWN -> view.height.toFloat()
        }
        view.animate()
            .translationY(translationValue)
            .alpha(0.0f).setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    swipeListener!!.onSwipeFinished(currentItem)
                }

                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })
    }

    fun addSwipeToDismiss(listener: EnchantedViewPagerSwipeListener?) {
        swipeListener = listener
        mUseSwipe = true
    }

    fun useScale() {
        val padding_in_px = resources.getDimensionPixelSize(R.dimen.enchanted_view_pager_margin)
        setPadding(padding_in_px, 0, padding_in_px, 0)
        clipToPadding = false
        mUseScale = true
    }

    fun useAlpha() {
        mUseAlpha = true
    }

    fun removeSwipe() {
        mUseSwipe = false
    }

    fun removeAlpha() {
        mUseAlpha = false
    }

    fun removeScale() {
        mUseScale = false
        setPadding(0, 0, 0, 0)
    }

    interface EnchantedViewPagerSwipeListener {
        fun onSwipeFinished(position: Int)
    }

    private enum class SWIPE_DIRECTION {
        SWIPE_UP, SWIPE_DOWN
    }

    companion object {
        const val ENCHANTED_VIEWPAGER_POSITION = "ENCHANTED_VIEWPAGER_POSITION"
    }
}
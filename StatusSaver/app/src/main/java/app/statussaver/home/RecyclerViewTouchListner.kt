package app.statussaver.home

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewTouchListner internal constructor(context: Context, private var clickLinter:ClickListner):RecyclerView.OnItemTouchListener{

    private var gestureDetector:GestureDetector= GestureDetector(context,object:GestureDetector.SimpleOnGestureListener(){
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
          //  return super.onSingleTapUp(e)
         return true
        }
    })


    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
       var child=rv.findChildViewUnder(e.x,e.y)
       if(child!=null && clickLinter!=null && gestureDetector.onTouchEvent(e)){
          clickLinter.onClick(child,rv.getChildAdapterPosition(child))
       }
       return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }

    internal interface ClickListner{
        fun onClick(view:View,postion:Int)
    }
}
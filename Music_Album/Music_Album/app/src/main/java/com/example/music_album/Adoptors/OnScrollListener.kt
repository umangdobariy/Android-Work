package com.example.music_album.Adoptors

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnScrollListener(
    val itemCount: Int,
    val layoutManager: LinearLayoutManager,
    val stateChanged: (Int) -> Unit) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val firstItemVisible = layoutManager.findFirstVisibleItemPosition()

        if (firstItemVisible > 0 && firstItemVisible % (itemCount - 1) == 0) {
            // When position reaches end of the list, it should go back to the beginning
            recyclerView.scrollToPosition(1)
        } else if (firstItemVisible == 0) {
            // When position reaches beginning of the list, it should go back to the end
            recyclerView.scrollToPosition(itemCount - 1)
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        stateChanged(newState)
    }
//    fun autoScroll(listSize: Int, intervalInMillis: Long) {
//        dispose?.let {
//            if(!it.isDisposed) return
//        }
//        dispose = Flowable.interval(intervalInMillis, TimeUnit.MILLISECONDS)
//            .map { it % listSize + 1 }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                recyclerView.smoothScrollToPosition(it.toInt() + 1)
//            }
//    }
//
//    fun stopAutoScroll() {
//        dispose?.let(Disposable::dispose)
//    }
}
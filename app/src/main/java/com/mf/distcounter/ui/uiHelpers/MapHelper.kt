package com.mf.distcounter.ui.uiHelpers

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.libraries.maps.model.BitmapDescriptor
import com.google.android.libraries.maps.model.BitmapDescriptorFactory
import com.mf.distcounter.R
import kotlinx.android.synthetic.main.distance_marker_layout.view.*

fun drawLabel(inflater: LayoutInflater): BitmapDescriptor {
    val distanceMarkerLayout: ConstraintLayout =
        inflater.inflate(R.layout.distance_marker_layout, null) as ConstraintLayout
    distanceMarkerLayout.distance_marker_text.text = 1213.toString() + " meters"
    distanceMarkerLayout.measure(
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    )
    distanceMarkerLayout.elevation = 10f
    distanceMarkerLayout.layout(
        10,
        10,
        distanceMarkerLayout.measuredWidth,
        distanceMarkerLayout.measuredHeight
    )




    val flagBitmap: Bitmap = Bitmap.createBitmap(distanceMarkerLayout.width, distanceMarkerLayout.height, Bitmap.Config.ARGB_8888)
    val flagBitmapDescriptor: BitmapDescriptor = BitmapDescriptorFactory.fromBitmap(flagBitmap)
    return flagBitmapDescriptor
}
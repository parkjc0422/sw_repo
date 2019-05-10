package com.kt.widget.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.content.ComponentName
import android.app.PendingIntent
import android.net.Uri
import android.widget.RemoteViews
import com.kt.widget.MainActivity
import com.kt.widget.R
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by kt on 10/05/2019.
 */


class WidgetProvider: AppWidgetProvider() {

    /**
     * 위젯을 갱신할때 호출됨
     *
     * 주의 : Configure Activity를 정의했을때는 위젯 등록시 처음 한번은 호출이 되지 않습니다
     */
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds?.forEach { ids ->
            updateAppWidget(context, appWidgetManager, ids)
        }
    }

    fun updateAppWidget(context: Context?,
                        appWidgetManager: AppWidgetManager?,
                        appWidgetId: Int) {
        /**
         * 현재 시간 정보를 가져오기 위한 Calendar
         */
        val mCalendar = Calendar.getInstance()
        val mFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.KOREA)

        /**
         * RemoteViews를 이용해 Text설정
         */
        val updateViews = RemoteViews(context?.packageName, R.layout.widget_sample)

        updateViews.setTextViewText(R.id.mText, mFormat.format(mCalendar.time))

        /**
         * 레이아웃을 클릭하면 홈페이지 이동
         */
        val pendingIntent = PendingIntent.getActivity(context, 0,
                Intent(context, MainActivity::class.java), 0)
        updateViews.setOnClickPendingIntent(R.id.mLayout, pendingIntent)

        /**
         * 위젯 업데이트
         */
        appWidgetManager?.updateAppWidget(appWidgetId, updateViews)
    }

}
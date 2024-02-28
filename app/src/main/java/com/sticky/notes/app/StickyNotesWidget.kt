package com.sticky.notes.app

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class StickyNotesWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        for (appWidgetId in appWidgetIds) {
            val launchActivity = Intent(context, MainActivity::class.java)
            val pendingIntent =
                PendingIntent.getActivity(context, 0, launchActivity, PendingIntent.FLAG_IMMUTABLE)
            val remoteViews = RemoteViews(context.packageName, R.layout.sticky_app_widget)
            remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }

    }
}

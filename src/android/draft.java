package org.cordova.quasar.app;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.net.Uri;

public class RysqalAppWidgetProvider extends AppWidgetProvider {
  private static final String QRCode = "Open QR Generator";
  private static final String QRScan = "Open QR Scanner";

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
    for (int appWidgetId : appWidgetIds) {
      Intent intent = new Intent(context, MainActivity.class);
      PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.rysqal_widget);
      views.setOnClickPendingIntent(R.id.rysqal_widget_button, pendingIntent);
      views.setOnClickPendingIntent(R.id.QRCode_widget_button, getPendingSelfIntent(context, QRCode));
      views.setOnClickPendingIntent(R.id.QRCodeScanner_widget_button, getPendingSelfIntent(context, QRScan));

      appWidgetManager.updateAppWidget(appWidgetId, views);
    }
  }

  protected PendingIntent getPendingSelfIntent(Context context, String action) {
    Intent intent = new Intent(context, getClass());
    intent.setAction(action);
    return PendingIntent.getBroadcast(context, 0, intent, 0);
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    super.onReceive(context, intent);
    if (QRCode.equals(intent.getAction())) {
      Intent intent2 = new Intent(Intent.ACTION_VIEW);
      intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent2.setData(Uri.parse("http://www.google.com"));
      context.startActivity(intent2);

    } else if (QRScan.equals(intent.getAction())) {
      Intent intent2 = new Intent(Intent.ACTION_VIEW);
      intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent2.setData(Uri.parse("http://www.stackoverflow.com"));
      context.startActivity(intent2);
    }
  }
}

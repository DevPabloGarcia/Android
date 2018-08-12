package com.example.cice.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void notify(View view) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        //Esto es lo obligatorio de una notificacion
        notificationBuilder
                .setContentTitle("Titulo " + counter)
                .setSmallIcon(R.mipmap.ic_launcher);

        //esto no es obligatorio
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSubText("Subtext");


        //Construimos el pending intent para abrir main
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", "dhvbhdbvh");

        //Construimos la pila de activities
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(DetailActivity.class);
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = taskStackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        /*
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        */


        NotificationCompat.Action action = new NotificationCompat
                .Action(R.mipmap.ic_launcher, "Acción", pendingIntent);
        notificationBuilder.addAction(action);


        notificationBuilder.setContentIntent(pendingIntent);

        Notification notification = notificationBuilder.build();

        notificationManager.notify(counter, notification);
        counter++;
    }

    public void detail(View view) {
        startActivity(new Intent(this, DetailActivity.class));
    }

    public void notifyBigTextStyle(View view) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(notificationBuilder);

        bigTextStyle
                .bigText("Lorem ipsum ad his scripta blandit partiendo, eum fastidii accumsan euripidis in, eum liber hendrerit an. Qui ut wisi vocibus suscipiantur, quo dicit ridens inciderint id. Quo mundi lobortis reformidans eu, legimus senserit definiebas an eos. Eu sit tincidunt incorrupte definitionem, vis mutat affert percipit cu, eirmod consectetuer signiferumque eu per. In usu latine equidem dolores. Quo no falli viris intellegam, ut fugit veritus placerat per.");

        bigTextStyle.setBigContentTitle("Big title");

        notificationBuilder
                .setContentTitle("Titulo " + counter)
                .setSmallIcon(R.mipmap.ic_launcher);

        Notification notification = notificationBuilder.build();

        notificationManager.notify(counter, notification);
        counter++;
    }

    public void notifyBigImageStyle(View view) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        NotificationCompat.BigPictureStyle bigPictureStyle
                = new NotificationCompat.BigPictureStyle(notificationBuilder);

        bigPictureStyle.setBigContentTitle("Big content title");
        bigPictureStyle.setSummaryText("SummaryText");
        //BigIcon
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.bigicon);
        bigPictureStyle.bigLargeIcon(largeIcon);

        //BigPicture
        Bitmap bigPicture = BitmapFactory.decodeResource(getResources(), R.drawable.bigpicture);
        bigPictureStyle.bigPicture(bigPicture);

        notificationBuilder
                .setContentTitle("Titulo " + counter)
                .setSmallIcon(R.mipmap.ic_launcher)
        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.largeicon));

        Notification notification = notificationBuilder.build();

        notificationManager.notify(counter, notification);
        counter++;
    }

    public void notifyInboxStyle(View view) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        NotificationCompat.InboxStyle inboxStyle
                = new NotificationCompat.InboxStyle(notificationBuilder);

        inboxStyle.setBigContentTitle("Big content title");
        inboxStyle.setSummaryText("SummaryText");

        //Añadimos lineas
        inboxStyle.addLine("kshvbsdkjbf hgui rhfgiuwehf uih dkjbf hgui rhfgiuwehf");
        inboxStyle.addLine(counter + "rgevgveh hgui rhfgiuwehf uih");
        inboxStyle.addLine("btn7ik hgui rhfgiuwehf uih");
        inboxStyle.addLine("nr7urb hgui rhfgiuwehf uih");
        inboxStyle.addLine("kshvbsdkjbf hgui b6rusur uih");
        inboxStyle.addLine("vsyt e hgui rhfgb yrjdiuwehf uih");


        notificationBuilder
                .setContentTitle("Titulo " + counter)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.largeicon));

        Notification notification = notificationBuilder.build();

        notificationManager.notify(100, notification);
        counter++;
    }
}

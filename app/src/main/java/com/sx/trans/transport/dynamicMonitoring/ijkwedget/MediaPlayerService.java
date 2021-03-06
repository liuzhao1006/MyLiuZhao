package com.sx.trans.transport.dynamicMonitoring.ijkwedget;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import tv.danmaku.ijk.media.player.IMediaPlayer;


public class MediaPlayerService extends Service {
	private static IMediaPlayer sMediaPlayer;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MediaPlayerService.class);
        return intent;
    }

    public static void intentToStart(Context context) {
        context.startService(newIntent(context));
    }

    public static void intentToStop(Context context) {
        context.stopService(newIntent(context));
    }


    public static void setMediaPlayer(IMediaPlayer mp) {
        if (sMediaPlayer != null && sMediaPlayer != mp) {
            if (sMediaPlayer.isPlaying())
                sMediaPlayer.stop();
            sMediaPlayer.release();
            sMediaPlayer = null;
        }
        sMediaPlayer = mp;
    }

    public static IMediaPlayer getMediaPlayer() {
        return sMediaPlayer;
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}

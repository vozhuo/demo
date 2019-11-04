package com.example.myapplication.ui.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.myapplication.R;
import com.example.myapplication.util.ScreenUtils;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayerActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler mHandler = new VideoHandler(this);

    private String mVideoUri;
    private String mVideoTitle;

    private VideoView mVideoView;
    private SeekBar mSeekBar;
    private ImageButton mStartBtn;
    private ImageButton mFullScreenBtn;
    private TextView mTimeTv;
    private TextView mDurationTv;
    private MediaPlayer mMediaPlayer;
    private View mControllerBottom;
    private View mControllerTop;
    private ImageButton mBackBtn;
    private ImageButton mDownloadBtn;
    private TextView mTitleTv;

    private int mVideoStatus = VIDEO_RUNNING;
    private int mScreenState = PORT_SCREEN;

    private int mCurrentPosition = 0; //当前播放的进度，以毫秒为单位
    private int mTotalDuration = 0;   //总视频的进度，以毫秒为单位

    private boolean isControllerBottomVisible;

    private static final int VIDEO_IDLE = 0;
    private static final int VIDEO_PAUSE = 1;
    private static final int VIDEO_RUNNING = 2;
    private static final int VIDEO_END = 3;

    private static final int LAND_SCREEN = 1;
    private static final int PORT_SCREEN = 0;

    private static final int HANDLE_MESSAGE_UPDATE_UI = 0x123;
    private static final int MILLISECOND = 1000;
    private static final int UPDATE_UI_INTERVAL = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player_main);
        mVideoUri = getIntent().getStringExtra("VideoUri");
        mVideoTitle = getIntent().getStringExtra("VideoTitle");
        initViews();
    }

    private void initViews(){
        isControllerBottomVisible = true;
        mControllerBottom = findViewById(R.id.video_player_activity_controller_bottom);
        //mControllerBottom.setVisibility(View.INVISIBLE);
        mControllerTop = findViewById(R.id.video_player_activity_controller_top);
        mStartBtn = findViewById(R.id.video_player_activity_start_btn);
        mFullScreenBtn = findViewById(R.id.video_player_activity_fullscreen_btn);
        mTimeTv = findViewById(R.id.video_player_activity_time_tv);
        mDurationTv = findViewById(R.id.video_player_activity_duration_tv);
        mSeekBar = findViewById(R.id.video_player_activity_seekbar);
        mBackBtn = findViewById(R.id.video_player_activity_arrow_back_btn);
        mDownloadBtn = findViewById(R.id.video_player_activity_download_btn);
        mTitleTv = findViewById(R.id.video_player_activity_title_tv);

        mStartBtn.setOnClickListener(this);
        mFullScreenBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
        mDownloadBtn.setOnClickListener(this);

        mTitleTv.setText(mVideoTitle);
        mVideoView = findViewById(R.id.video_player_activity_videoview);
        mVideoView.setVideoURI(Uri.parse(mVideoUri));
        mVideoView.requestFocus();

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoStatus = VIDEO_END;
                mStartBtn.setImageResource(R.drawable.ic_start_white_48px);
                mHandler.removeMessages(HANDLE_MESSAGE_UPDATE_UI);

                if (!isControllerBottomVisible){
                    isControllerBottomVisible = true;
                    mControllerBottom.setVisibility(View.VISIBLE);
                    mControllerTop.setVisibility(View.VISIBLE);
                }
            }
        });
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //视频准备完毕
                Log.d("cylog","[VideoPlayerActivity.OnPreparedListener] Video Prepared.Here are some video information."
                        + "Video Duration:" + mp.getDuration() + "ms." + "Video Aspect:(" + mp.getVideoWidth() + ","+ mp.getVideoHeight() +")");
                mTotalDuration = mp.getDuration();
                mSeekBar.setMax(mTotalDuration);
                mVideoStatus = VIDEO_RUNNING;
                mStartBtn.setImageResource(R.drawable.ic_pause_white_48px);
                mDurationTv.setText(getTimeFromMillisecond(mp.getDuration()));
                mMediaPlayer = mp;

                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {
                        float percentage = percent / 100f;
                        mSeekBar.setSecondaryProgress((int) (percentage * mTotalDuration));
                    }
                });
                mHandler.sendEmptyMessageDelayed(HANDLE_MESSAGE_UPDATE_UI,UPDATE_UI_INTERVAL);
            }
        });
        mVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isControllerBottomVisible){
                    isControllerBottomVisible = false;
                    mControllerBottom.setVisibility(View.INVISIBLE);
                    mControllerTop.setVisibility(View.INVISIBLE);
                }else{
                    isControllerBottomVisible = true;
                    mControllerBottom.setVisibility(View.VISIBLE);
                    mControllerTop.setVisibility(View.VISIBLE);
                }
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mCurrentPosition = progress;
                    mVideoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mVideoView.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //对返回键进行处理，取消Handler的消息，否则会抛出MediaPlayer的状态异常
        if (keyCode == KeyEvent.KEYCODE_BACK){
            finishSelf();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void finishSelf(){
        mHandler.removeMessages(HANDLE_MESSAGE_UPDATE_UI);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video_player_activity_start_btn:{
                if (mVideoStatus == VIDEO_RUNNING){
                    mStartBtn.setImageResource(R.drawable.ic_start_white_48px);
                    mVideoView.pause();
                    mVideoStatus = VIDEO_PAUSE;
                    mHandler.removeMessages(HANDLE_MESSAGE_UPDATE_UI);
                }else if (mVideoStatus == VIDEO_PAUSE){
                    mStartBtn.setImageResource(R.drawable.ic_pause_white_48px);
                    mVideoView.start();
                    mVideoStatus = VIDEO_RUNNING;
                    mHandler.sendEmptyMessageDelayed(HANDLE_MESSAGE_UPDATE_UI,MILLISECOND);
                }else if (mVideoStatus == VIDEO_END){
                    //如果是重新播放，重置计时器
                    mCurrentPosition = 0;
                    mStartBtn.setImageResource(R.drawable.ic_pause_white_48px);
                    mVideoView.start();
                    mVideoStatus = VIDEO_RUNNING;
                    mHandler.sendEmptyMessageDelayed(HANDLE_MESSAGE_UPDATE_UI,MILLISECOND);
                }
                break;
            }

            case R.id.video_player_activity_fullscreen_btn:{
                //Log.d("cylog","[VideoPlayerActivity.OnClick] 切换至全屏");
                if (mScreenState == PORT_SCREEN){
                    convertToLandScreen();
                }else{
                    convertToPortScreen();
                }
                break;
            }

            case R.id.video_player_activity_arrow_back_btn:{
                finishSelf();
                break;
            }


            case R.id.video_player_activity_download_btn:{
                //downloadTrailer();
                break;
            }
        }
    }

    private void convertToLandScreen(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        int API_LEVEL = Build.VERSION.SDK_INT;

        if (API_LEVEL >= 17){
            display.getRealSize(point);
        }else{
            display.getSize(point);
        }

        int height = point.y;
        int width = point.x;

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mVideoView.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = width;
        layoutParams.setMargins(0,0,0,0);
        mVideoView.setLayoutParams(layoutParams);
        mScreenState = LAND_SCREEN;
    }

    private void convertToPortScreen(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(this,220f));
        mVideoView.setLayoutParams(params);
        mScreenState = PORT_SCREEN;
    }

    private String getTimeFromMillisecond(int millisecond){
        StringBuilder builder = new StringBuilder();

        if (millisecond == -1){
            return builder.append("00:00").toString();
        }

        int second = millisecond / 1000;
        int minute = second / 60;
        second = second - minute * 60;

        return String.format("%02d:%02d",minute,second);
    }

    static class VideoHandler extends Handler {

        private WeakReference<VideoPlayerActivity> mActivityReference;

        public VideoHandler(VideoPlayerActivity activity) {
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == HANDLE_MESSAGE_UPDATE_UI){
                VideoPlayerActivity activity = mActivityReference.get();
                if (activity != null){
                    MediaPlayer mediaPlayer = activity.mMediaPlayer;
                    if (mediaPlayer != null && mediaPlayer.isPlaying()){
                        int currentPos = mediaPlayer.getCurrentPosition();
                        activity.mTimeTv.setText(activity.getTimeFromMillisecond(currentPos));
                        activity.mSeekBar.setProgress(currentPos);
                        sendEmptyMessageDelayed(HANDLE_MESSAGE_UPDATE_UI,UPDATE_UI_INTERVAL);
                    }

                }
            }
        }
    };
}

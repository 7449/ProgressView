package com.example.y.progressview;


import android.os.Handler;
import android.os.Message;

import com.example.y.progressview.view.ProgressDefaults;

/**
 * by 12406 on 2016/5/25.
 */
public class ProgressHandler extends Handler {

    public Progress progress;

    public void setProgress(Progress progress) {
        this.progress = progress;
    }


    /**
     * 请求更新
     */
    public static final int UPDATE = 1;
    /**
     * 请求暂停
     */
    public static final int KEEP = 2;
    /**
     * 请求恢复
     */
    public static final int BREAK = 3;
    /**
     * 请求移除
     */
    public static final int REMOVE = -1;
    /**
     * 记录最新进度
     */
    public static final int PAGE = 4;

    //间隔时间
    public static final long TIME = 100;

    public static int SCHEDULE = 0;

    @Override

    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (hasMessages(UPDATE)) {
            removeMessages(UPDATE);
        }
        switch (msg.what) {
            case UPDATE:
                if (!(SCHEDULE == ProgressDefaults.PROGRESS_BAR_MAX)) {
                    SCHEDULE++;
                    sendEmptyMessageDelayed(UPDATE, TIME);
                } else {
                    sendEmptyMessageDelayed(REMOVE, TIME);
                }
                if (progress != null) {
                    progress.setSchedule(SCHEDULE);
                }
                break;
            case KEEP:
                break;
            case BREAK:
                sendEmptyMessageDelayed(UPDATE, TIME);
                break;
            case PAGE:
                SCHEDULE = msg.arg1;
                break;
            case REMOVE:
                progress.onSuccess();
                SCHEDULE = 0;
                break;
            default:
                sendEmptyMessageDelayed(UPDATE, TIME);
                break;
        }
    }


    public interface Progress {
        void setSchedule(int schedule);

        void onSuccess();
    }

}

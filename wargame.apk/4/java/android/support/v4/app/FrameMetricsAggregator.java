// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.SparseIntArray;
import android.view.FrameMetrics;
import android.view.Window;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class FrameMetricsAggregator
{
    private static class FrameMetricsApi24Impl extends FrameMetricsBaseImpl
    {

        public void add(Activity activity)
        {
            if(sHandlerThread == null)
            {
                sHandlerThread = new HandlerThread("FrameMetricsAggregator");
                sHandlerThread.start();
                sHandler = new Handler(sHandlerThread.getLooper());
            }
            for(int i = 0; i <= 8; i++)
                if(mMetrics[i] == null && (mTrackingFlags & 1 << i) != 0)
                    mMetrics[i] = new SparseIntArray();

            activity.getWindow().addOnFrameMetricsAvailableListener(mListener, sHandler);
            mActivities.add(new WeakReference(activity));
        }

        void addDurationItem(SparseIntArray sparseintarray, long l)
        {
            if(sparseintarray != null)
            {
                int i = (int)((0x7a120L + l) / 0xf4240L);
                if(l >= 0L)
                    sparseintarray.put(i, sparseintarray.get(i) + 1);
            }
        }

        public SparseIntArray[] getMetrics()
        {
            return mMetrics;
        }

        public SparseIntArray[] remove(Activity activity)
        {
            Iterator iterator = mActivities.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                WeakReference weakreference = (WeakReference)iterator.next();
                if(weakreference.get() != activity)
                    continue;
                mActivities.remove(weakreference);
                break;
            } while(true);
            activity.getWindow().removeOnFrameMetricsAvailableListener(mListener);
            return mMetrics;
        }

        public SparseIntArray[] reset()
        {
            SparseIntArray asparseintarray[] = mMetrics;
            mMetrics = new SparseIntArray[9];
            return asparseintarray;
        }

        public SparseIntArray[] stop()
        {
            for(int i = mActivities.size() - 1; i >= 0; i--)
            {
                WeakReference weakreference = (WeakReference)mActivities.get(i);
                Activity activity = (Activity)weakreference.get();
                if(weakreference.get() != null)
                {
                    activity.getWindow().removeOnFrameMetricsAvailableListener(mListener);
                    mActivities.remove(i);
                }
            }

            return mMetrics;
        }

        private static final int NANOS_PER_MS = 0xf4240;
        private static final int NANOS_ROUNDING_VALUE = 0x7a120;
        private static Handler sHandler = null;
        private static HandlerThread sHandlerThread = null;
        private ArrayList mActivities;
        android.view.Window.OnFrameMetricsAvailableListener mListener;
        private SparseIntArray mMetrics[];
        private int mTrackingFlags;




        FrameMetricsApi24Impl(int i)
        {
            mMetrics = new SparseIntArray[9];
            mActivities = new ArrayList();
            mListener = new _cls1();
            mTrackingFlags = i;
        }
    }

    private static class FrameMetricsBaseImpl
    {

        public void add(Activity activity)
        {
        }

        public SparseIntArray[] getMetrics()
        {
            return null;
        }

        public SparseIntArray[] remove(Activity activity)
        {
            return null;
        }

        public SparseIntArray[] reset()
        {
            return null;
        }

        public SparseIntArray[] stop()
        {
            return null;
        }

        private FrameMetricsBaseImpl()
        {
        }

    }

    public static interface MetricType
        extends Annotation
    {
    }


    public FrameMetricsAggregator()
    {
        this(1);
    }

    public FrameMetricsAggregator(int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
        {
            mInstance = new FrameMetricsApi24Impl(i);
            return;
        } else
        {
            mInstance = new FrameMetricsBaseImpl();
            return;
        }
    }

    public void add(Activity activity)
    {
        mInstance.add(activity);
    }

    public SparseIntArray[] getMetrics()
    {
        return mInstance.getMetrics();
    }

    public SparseIntArray[] remove(Activity activity)
    {
        return mInstance.remove(activity);
    }

    public SparseIntArray[] reset()
    {
        return mInstance.reset();
    }

    public SparseIntArray[] stop()
    {
        return mInstance.stop();
    }

    public static final int ANIMATION_DURATION = 256;
    public static final int ANIMATION_INDEX = 8;
    public static final int COMMAND_DURATION = 32;
    public static final int COMMAND_INDEX = 5;
    private static final boolean DBG = false;
    public static final int DELAY_DURATION = 128;
    public static final int DELAY_INDEX = 7;
    public static final int DRAW_DURATION = 8;
    public static final int DRAW_INDEX = 3;
    public static final int EVERY_DURATION = 511;
    public static final int INPUT_DURATION = 2;
    public static final int INPUT_INDEX = 1;
    private static final int LAST_INDEX = 8;
    public static final int LAYOUT_MEASURE_DURATION = 4;
    public static final int LAYOUT_MEASURE_INDEX = 2;
    public static final int SWAP_DURATION = 64;
    public static final int SWAP_INDEX = 6;
    public static final int SYNC_DURATION = 16;
    public static final int SYNC_INDEX = 4;
    private static final String TAG = "FrameMetrics";
    public static final int TOTAL_DURATION = 1;
    public static final int TOTAL_INDEX = 0;
    private FrameMetricsBaseImpl mInstance;

    // Unreferenced inner class android/support/v4/app/FrameMetricsAggregator$FrameMetricsApi24Impl$1

/* anonymous class */
    class FrameMetricsApi24Impl._cls1
        implements android.view.Window.OnFrameMetricsAvailableListener
    {

        public void onFrameMetricsAvailable(Window window, FrameMetrics framemetrics, int i)
        {
            if((mTrackingFlags & 1) != 0)
                addDurationItem(mMetrics[0], framemetrics.getMetric(8));
            if((mTrackingFlags & 2) != 0)
                addDurationItem(mMetrics[1], framemetrics.getMetric(1));
            if((mTrackingFlags & 4) != 0)
                addDurationItem(mMetrics[2], framemetrics.getMetric(3));
            if((mTrackingFlags & 8) != 0)
                addDurationItem(mMetrics[3], framemetrics.getMetric(4));
            if((mTrackingFlags & 0x10) != 0)
                addDurationItem(mMetrics[4], framemetrics.getMetric(5));
            if((mTrackingFlags & 0x40) != 0)
                addDurationItem(mMetrics[6], framemetrics.getMetric(7));
            if((mTrackingFlags & 0x20) != 0)
                addDurationItem(mMetrics[5], framemetrics.getMetric(6));
            if((mTrackingFlags & 0x80) != 0)
                addDurationItem(mMetrics[7], framemetrics.getMetric(0));
            if((mTrackingFlags & 0x100) != 0)
                addDurationItem(mMetrics[8], framemetrics.getMetric(2));
        }

        final FrameMetricsApi24Impl this$0;

            
            {
                this$0 = FrameMetricsApi24Impl.this;
                super();
            }
    }

}

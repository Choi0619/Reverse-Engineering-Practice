// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Service;
import android.app.job.*;
import android.content.*;
import android.os.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service
{
    final class CommandProcessor extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected transient Void doInBackground(Void avoid[])
        {
            do
            {
                avoid = dequeueWork();
                if(avoid != null)
                {
                    onHandleWork(avoid.getIntent());
                    avoid.complete();
                } else
                {
                    return null;
                }
            } while(true);
        }

        protected volatile void onCancelled(Object obj)
        {
            onCancelled((Void)obj);
        }

        protected void onCancelled(Void void1)
        {
            processorFinished();
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Void)obj);
        }

        protected void onPostExecute(Void void1)
        {
            processorFinished();
        }

        final JobIntentService this$0;

        CommandProcessor()
        {
            this$0 = JobIntentService.this;
            super();
        }
    }

    static interface CompatJobEngine
    {

        public abstract IBinder compatGetBinder();

        public abstract GenericWorkItem dequeueWork();
    }

    static final class CompatWorkEnqueuer extends WorkEnqueuer
    {

        void enqueueWork(Intent intent)
        {
            intent = new Intent(intent);
            intent.setComponent(mComponentName);
            if(mContext.startService(intent) == null)
                break MISSING_BLOCK_LABEL_68;
            this;
            JVM INSTR monitorenter ;
            if(!mLaunchingService)
            {
                mLaunchingService = true;
                if(!mServiceProcessing)
                    mLaunchWakeLock.acquire(60000L);
            }
            this;
            JVM INSTR monitorexit ;
            return;
            intent;
            this;
            JVM INSTR monitorexit ;
            throw intent;
        }

        public void serviceProcessingFinished()
        {
            this;
            JVM INSTR monitorenter ;
            if(mServiceProcessing)
            {
                if(mLaunchingService)
                    mLaunchWakeLock.acquire(60000L);
                mServiceProcessing = false;
                mRunWakeLock.release();
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void serviceProcessingStarted()
        {
            this;
            JVM INSTR monitorenter ;
            if(!mServiceProcessing)
            {
                mServiceProcessing = true;
                mRunWakeLock.acquire(0x927c0L);
                mLaunchWakeLock.release();
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void serviceStartReceived()
        {
            this;
            JVM INSTR monitorenter ;
            mLaunchingService = false;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private final Context mContext;
        private final android.os.PowerManager.WakeLock mLaunchWakeLock;
        boolean mLaunchingService;
        private final android.os.PowerManager.WakeLock mRunWakeLock;
        boolean mServiceProcessing;

        CompatWorkEnqueuer(Context context, ComponentName componentname)
        {
            super(context, componentname);
            mContext = context.getApplicationContext();
            context = (PowerManager)context.getSystemService("power");
            mLaunchWakeLock = context.newWakeLock(1, (new StringBuilder()).append(componentname.getClassName()).append(":launch").toString());
            mLaunchWakeLock.setReferenceCounted(false);
            mRunWakeLock = context.newWakeLock(1, (new StringBuilder()).append(componentname.getClassName()).append(":run").toString());
            mRunWakeLock.setReferenceCounted(false);
        }
    }

    final class CompatWorkItem
        implements GenericWorkItem
    {

        public void complete()
        {
            stopSelf(mStartId);
        }

        public Intent getIntent()
        {
            return mIntent;
        }

        final Intent mIntent;
        final int mStartId;
        final JobIntentService this$0;

        CompatWorkItem(Intent intent, int i)
        {
            this$0 = JobIntentService.this;
            super();
            mIntent = intent;
            mStartId = i;
        }
    }

    static interface GenericWorkItem
    {

        public abstract void complete();

        public abstract Intent getIntent();
    }

    static final class JobServiceEngineImpl extends JobServiceEngine
        implements CompatJobEngine
    {

        public IBinder compatGetBinder()
        {
            return getBinder();
        }

        public GenericWorkItem dequeueWork()
        {
label0:
            {
                synchronized(mLock)
                {
                    if(mParams != null)
                        break label0;
                }
                return null;
            }
            JobWorkItem jobworkitem = mParams.dequeueWork();
            obj;
            JVM INSTR monitorexit ;
            if(jobworkitem != null)
            {
                jobworkitem.getIntent().setExtrasClassLoader(mService.getClassLoader());
                return new WrapperWorkItem(jobworkitem);
            } else
            {
                return null;
            }
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public boolean onStartJob(JobParameters jobparameters)
        {
            mParams = jobparameters;
            mService.ensureProcessorRunningLocked(false);
            return true;
        }

        public boolean onStopJob(JobParameters jobparameters)
        {
            boolean flag = mService.doStopCurrentWork();
            synchronized(mLock)
            {
                mParams = null;
            }
            return flag;
            exception;
            jobparameters;
            JVM INSTR monitorexit ;
            throw exception;
        }

        static final boolean DEBUG = false;
        static final String TAG = "JobServiceEngineImpl";
        final Object mLock = new Object();
        JobParameters mParams;
        final JobIntentService mService;

        JobServiceEngineImpl(JobIntentService jobintentservice)
        {
            super(jobintentservice);
            mService = jobintentservice;
        }
    }

    final class JobServiceEngineImpl.WrapperWorkItem
        implements GenericWorkItem
    {

        public void complete()
        {
            synchronized(mLock)
            {
                if(mParams != null)
                    mParams.completeWork(mJobWork);
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public Intent getIntent()
        {
            return mJobWork.getIntent();
        }

        final JobWorkItem mJobWork;
        final JobServiceEngineImpl this$0;

        JobServiceEngineImpl.WrapperWorkItem(JobWorkItem jobworkitem)
        {
            this$0 = JobServiceEngineImpl.this;
            super();
            mJobWork = jobworkitem;
        }
    }

    static final class JobWorkEnqueuer extends WorkEnqueuer
    {

        void enqueueWork(Intent intent)
        {
            mJobScheduler.enqueue(mJobInfo, new JobWorkItem(intent));
        }

        private final JobInfo mJobInfo;
        private final JobScheduler mJobScheduler;

        JobWorkEnqueuer(Context context, ComponentName componentname, int i)
        {
            super(context, componentname);
            ensureJobId(i);
            mJobInfo = (new android.app.job.JobInfo.Builder(i, mComponentName)).setOverrideDeadline(0L).build();
            mJobScheduler = (JobScheduler)context.getApplicationContext().getSystemService("jobscheduler");
        }
    }

    static abstract class WorkEnqueuer
    {

        abstract void enqueueWork(Intent intent);

        void ensureJobId(int i)
        {
            if(!mHasJobId)
            {
                mHasJobId = true;
                mJobId = i;
            } else
            if(mJobId != i)
                throw new IllegalArgumentException((new StringBuilder()).append("Given job ID ").append(i).append(" is different than previous ").append(mJobId).toString());
        }

        public void serviceProcessingFinished()
        {
        }

        public void serviceProcessingStarted()
        {
        }

        public void serviceStartReceived()
        {
        }

        final ComponentName mComponentName;
        boolean mHasJobId;
        int mJobId;

        WorkEnqueuer(Context context, ComponentName componentname)
        {
            mComponentName = componentname;
        }
    }


    public JobIntentService()
    {
        mInterruptIfStopped = false;
        mStopped = false;
        mDestroyed = false;
        if(android.os.Build.VERSION.SDK_INT >= 26)
        {
            mCompatQueue = null;
            return;
        } else
        {
            mCompatQueue = new ArrayList();
            return;
        }
    }

    public static void enqueueWork(Context context, ComponentName componentname, int i, Intent intent)
    {
        if(intent == null)
            throw new IllegalArgumentException("work must not be null");
        synchronized(sLock)
        {
            context = getWorkEnqueuer(context, componentname, true, i);
            context.ensureJobId(i);
            context.enqueueWork(intent);
        }
        return;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    public static void enqueueWork(Context context, Class class1, int i, Intent intent)
    {
        enqueueWork(context, new ComponentName(context, class1), i, intent);
    }

    static WorkEnqueuer getWorkEnqueuer(Context context, ComponentName componentname, boolean flag, int i)
    {
        WorkEnqueuer workenqueuer = (WorkEnqueuer)sClassWorkEnqueuer.get(componentname);
        Object obj = workenqueuer;
        if(workenqueuer == null)
        {
            if(android.os.Build.VERSION.SDK_INT >= 26)
            {
                if(!flag)
                    throw new IllegalArgumentException("Can't be here without a job id");
                context = new JobWorkEnqueuer(context, componentname, i);
            } else
            {
                context = new CompatWorkEnqueuer(context, componentname);
            }
            sClassWorkEnqueuer.put(componentname, context);
            obj = context;
        }
        return ((WorkEnqueuer) (obj));
    }

    GenericWorkItem dequeueWork()
    {
        if(mJobImpl != null)
            return mJobImpl.dequeueWork();
        ArrayList arraylist = mCompatQueue;
        arraylist;
        JVM INSTR monitorenter ;
        GenericWorkItem genericworkitem;
        if(mCompatQueue.size() <= 0)
            break MISSING_BLOCK_LABEL_55;
        genericworkitem = (GenericWorkItem)mCompatQueue.remove(0);
        return genericworkitem;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        return null;
    }

    boolean doStopCurrentWork()
    {
        if(mCurProcessor != null)
            mCurProcessor.cancel(mInterruptIfStopped);
        mStopped = true;
        return onStopCurrentWork();
    }

    void ensureProcessorRunningLocked(boolean flag)
    {
        if(mCurProcessor == null)
        {
            mCurProcessor = new CommandProcessor();
            if(mCompatWorkEnqueuer != null && flag)
                mCompatWorkEnqueuer.serviceProcessingStarted();
            mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public boolean isStopped()
    {
        return mStopped;
    }

    public IBinder onBind(Intent intent)
    {
        if(mJobImpl != null)
            return mJobImpl.compatGetBinder();
        else
            return null;
    }

    public void onCreate()
    {
        super.onCreate();
        if(android.os.Build.VERSION.SDK_INT >= 26)
        {
            mJobImpl = new JobServiceEngineImpl(this);
            mCompatWorkEnqueuer = null;
            return;
        } else
        {
            mJobImpl = null;
            mCompatWorkEnqueuer = getWorkEnqueuer(this, new ComponentName(this, getClass()), false, 0);
            return;
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        if(mCompatQueue != null)
        {
            synchronized(mCompatQueue)
            {
                mDestroyed = true;
                mCompatWorkEnqueuer.serviceProcessingFinished();
            }
            return;
        } else
        {
            return;
        }
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected abstract void onHandleWork(Intent intent);

    public int onStartCommand(Intent intent, int i, int j)
    {
        if(mCompatQueue == null)
            break MISSING_BLOCK_LABEL_76;
        mCompatWorkEnqueuer.serviceStartReceived();
        ArrayList arraylist = mCompatQueue;
        arraylist;
        JVM INSTR monitorenter ;
        ArrayList arraylist1 = mCompatQueue;
        if(intent == null)
            break MISSING_BLOCK_LABEL_59;
_L1:
        arraylist1.add(new CompatWorkItem(intent, j));
        ensureProcessorRunningLocked(true);
        arraylist;
        JVM INSTR monitorexit ;
        return 3;
        intent = new Intent();
          goto _L1
        intent;
        arraylist;
        JVM INSTR monitorexit ;
        throw intent;
        return 2;
    }

    public boolean onStopCurrentWork()
    {
        return true;
    }

    void processorFinished()
    {
        if(mCompatQueue == null)
            break MISSING_BLOCK_LABEL_66;
        ArrayList arraylist = mCompatQueue;
        arraylist;
        JVM INSTR monitorenter ;
        mCurProcessor = null;
        if(mCompatQueue == null || mCompatQueue.size() <= 0) goto _L2; else goto _L1
_L1:
        ensureProcessorRunningLocked(false);
_L4:
        return;
_L2:
        if(!mDestroyed)
            mCompatWorkEnqueuer.serviceProcessingFinished();
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setInterruptIfStopped(boolean flag)
    {
        mInterruptIfStopped = flag;
    }

    static final boolean DEBUG = false;
    static final String TAG = "JobIntentService";
    static final HashMap sClassWorkEnqueuer = new HashMap();
    static final Object sLock = new Object();
    final ArrayList mCompatQueue;
    WorkEnqueuer mCompatWorkEnqueuer;
    CommandProcessor mCurProcessor;
    boolean mDestroyed;
    boolean mInterruptIfStopped;
    CompatJobEngine mJobImpl;
    boolean mStopped;

}

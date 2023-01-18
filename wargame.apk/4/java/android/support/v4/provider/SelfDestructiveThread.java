// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.provider;

import android.os.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SelfDestructiveThread
{
    public static interface ReplyCallback
    {

        public abstract void onReply(Object obj);
    }


    public SelfDestructiveThread(String s, int i, int j)
    {
        mCallback = new android.os.Handler.Callback() {

            public boolean handleMessage(Message message)
            {
                switch(message.what)
                {
                default:
                    return true;

                case 1: // '\001'
                    onInvokeRunnable((Runnable)message.obj);
                    return true;

                case 0: // '\0'
                    onDestruction();
                    return true;
                }
            }

            final SelfDestructiveThread this$0;

            
            {
                this$0 = SelfDestructiveThread.this;
                super();
            }
        }
;
        mThreadName = s;
        mPriority = i;
        mDestructAfterMillisec = j;
        mGeneration = 0;
    }

    private void onDestruction()
    {
label0:
        {
            synchronized(mLock)
            {
                if(!mHandler.hasMessages(1))
                    break label0;
            }
            return;
        }
        mThread.quit();
        mThread = null;
        mHandler = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void onInvokeRunnable(Runnable runnable)
    {
        runnable.run();
        synchronized(mLock)
        {
            mHandler.removeMessages(0);
            mHandler.sendMessageDelayed(mHandler.obtainMessage(0), mDestructAfterMillisec);
        }
        return;
        exception;
        runnable;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void post(Runnable runnable)
    {
        synchronized(mLock)
        {
            if(mThread == null)
            {
                mThread = new HandlerThread(mThreadName, mPriority);
                mThread.start();
                mHandler = new Handler(mThread.getLooper(), mCallback);
                mGeneration = mGeneration + 1;
            }
            mHandler.removeMessages(0);
            mHandler.sendMessage(mHandler.obtainMessage(1, runnable));
        }
        return;
        runnable;
        obj;
        JVM INSTR monitorexit ;
        throw runnable;
    }

    public int getGeneration()
    {
        int i;
        synchronized(mLock)
        {
            i = mGeneration;
        }
        return i;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean isRunning()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        Exception exception;
        if(mThread != null)
            flag = true;
        else
            flag = false;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void postAndReply(final Callable callable, ReplyCallback replycallback)
    {
        post(new Runnable() {

            public void run()
            {
                Object obj;
                try
                {
                    obj = callable.call();
                }
                catch(Exception exception)
                {
                    exception = null;
                }
                callingHandler.post(((_cls1) (obj)). new Runnable() {

                    public void run()
                    {
                        reply.onReply(result);
                    }

                    final _cls2 this$1;
                    final Object val$result;

            
            {
                this$1 = final__pcls2;
                result = Object.this;
                super();
            }
                }
);
            }

            final SelfDestructiveThread this$0;
            final Callable val$callable;
            final Handler val$callingHandler;
            final ReplyCallback val$reply;

            
            {
                this$0 = SelfDestructiveThread.this;
                callable = callable1;
                callingHandler = handler;
                reply = replycallback;
                super();
            }
        }
);
    }

    public Object postAndWait(final Callable callable, int i)
        throws InterruptedException
    {
        final ReentrantLock lock;
        final Condition cond;
        final AtomicReference holder;
        final AtomicBoolean running;
        lock = new ReentrantLock();
        cond = lock.newCondition();
        holder = new AtomicReference();
        running = new AtomicBoolean(true);
        post(new Runnable() {

            public void run()
            {
                Exception exception;
                try
                {
                    holder.set(callable.call());
                }
                catch(Exception exception1) { }
                lock.lock();
                running.set(false);
                cond.signal();
                lock.unlock();
                return;
                exception;
                lock.unlock();
                throw exception;
            }

            final SelfDestructiveThread this$0;
            final Callable val$callable;
            final Condition val$cond;
            final AtomicReference val$holder;
            final ReentrantLock val$lock;
            final AtomicBoolean val$running;

            
            {
                this$0 = SelfDestructiveThread.this;
                holder = atomicreference;
                callable = callable1;
                lock = reentrantlock;
                running = atomicboolean;
                cond = condition;
                super();
            }
        }
);
        lock.lock();
        if(running.get())
            break MISSING_BLOCK_LABEL_82;
        callable = ((Callable) (holder.get()));
        lock.unlock();
        return callable;
        long l = TimeUnit.MILLISECONDS.toNanos(i);
_L4:
        long l1;
        try
        {
            l1 = cond.awaitNanos(l);
        }
        // Misplaced declaration of an exception variable
        catch(final Callable callable)
        {
            l1 = l;
        }
        if(running.get()) goto _L2; else goto _L1
_L1:
        callable = ((Callable) (holder.get()));
        lock.unlock();
        return callable;
_L2:
        l = l1;
        if(l1 > 0L) goto _L4; else goto _L3
_L3:
        throw new InterruptedException("timeout");
        callable;
        lock.unlock();
        throw callable;
    }

    private static final int MSG_DESTRUCTION = 0;
    private static final int MSG_INVOKE_RUNNABLE = 1;
    private android.os.Handler.Callback mCallback;
    private final int mDestructAfterMillisec;
    private int mGeneration;
    private Handler mHandler;
    private final Object mLock = new Object();
    private final int mPriority;
    private HandlerThread mThread;
    private final String mThreadName;


}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.*;
import android.os.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

public final class LocalBroadcastManager
{
    private static final class BroadcastRecord
    {

        final Intent intent;
        final ArrayList receivers;

        BroadcastRecord(Intent intent1, ArrayList arraylist)
        {
            intent = intent1;
            receivers = arraylist;
        }
    }

    private static final class ReceiverRecord
    {

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("Receiver{");
            stringbuilder.append(receiver);
            stringbuilder.append(" filter=");
            stringbuilder.append(filter);
            if(dead)
                stringbuilder.append(" DEAD");
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter intentfilter, BroadcastReceiver broadcastreceiver)
        {
            filter = intentfilter;
            receiver = broadcastreceiver;
        }
    }


    private LocalBroadcastManager(Context context)
    {
        mAppContext = context;
        mHandler = new Handler(context.getMainLooper()) {

            public void handleMessage(Message message)
            {
                switch(message.what)
                {
                default:
                    super.handleMessage(message);
                    return;

                case 1: // '\001'
                    executePendingBroadcasts();
                    break;
                }
            }

            final LocalBroadcastManager this$0;

            
            {
                this$0 = LocalBroadcastManager.this;
                super(looper);
            }
        }
;
    }

    private void executePendingBroadcasts()
    {
_L4:
        Object obj = mReceivers;
        obj;
        JVM INSTR monitorenter ;
        int i = mPendingBroadcasts.size();
        if(i > 0)
            break MISSING_BLOCK_LABEL_25;
        obj;
        JVM INSTR monitorexit ;
        return;
        BroadcastRecord abroadcastrecord[];
        abroadcastrecord = new BroadcastRecord[i];
        mPendingBroadcasts.toArray(abroadcastrecord);
        mPendingBroadcasts.clear();
        obj;
        JVM INSTR monitorexit ;
        i = 0;
_L2:
        if(i < abroadcastrecord.length)
        {
            obj = abroadcastrecord[i];
            int k = ((BroadcastRecord) (obj)).receivers.size();
            for(int j = 0; j < k; j++)
            {
                ReceiverRecord receiverrecord = (ReceiverRecord)((BroadcastRecord) (obj)).receivers.get(j);
                if(!receiverrecord.dead)
                    receiverrecord.receiver.onReceive(mAppContext, ((BroadcastRecord) (obj)).intent);
            }

            break MISSING_BLOCK_LABEL_136;
        }
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static LocalBroadcastManager getInstance(Context context)
    {
        synchronized(mLock)
        {
            if(mInstance == null)
                mInstance = new LocalBroadcastManager(context.getApplicationContext());
            context = mInstance;
        }
        return context;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    public void registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        Object obj;
        ReceiverRecord receiverrecord;
        receiverrecord = new ReceiverRecord(intentfilter, broadcastreceiver);
        obj = (ArrayList)mReceivers.get(broadcastreceiver);
        ArrayList arraylist;
        arraylist = ((ArrayList) (obj));
        if(obj != null)
            break MISSING_BLOCK_LABEL_63;
        arraylist = new ArrayList(1);
        mReceivers.put(broadcastreceiver, arraylist);
        arraylist.add(receiverrecord);
        int i = 0;
_L2:
        if(i >= intentfilter.countActions())
            break; /* Loop/switch isn't completed */
        obj = intentfilter.getAction(i);
        arraylist = (ArrayList)mActions.get(obj);
        broadcastreceiver = arraylist;
        if(arraylist != null)
            break MISSING_BLOCK_LABEL_130;
        broadcastreceiver = new ArrayList(1);
        mActions.put(obj, broadcastreceiver);
        broadcastreceiver.add(receiverrecord);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        broadcastreceiver;
        hashmap;
        JVM INSTR monitorexit ;
        throw broadcastreceiver;
    }

    public boolean sendBroadcast(Intent intent)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        String s;
        String s1;
        android.net.Uri uri;
        String s2;
        java.util.Set set;
        s = intent.getAction();
        s1 = intent.resolveTypeIfNeeded(mAppContext.getContentResolver());
        uri = intent.getData();
        s2 = intent.getScheme();
        set = intent.getCategories();
        int i;
        Object obj;
        Object obj1;
        int j;
        int k;
        ArrayList arraylist;
        ReceiverRecord receiverrecord;
        if((intent.getFlags() & 8) != 0)
            i = 1;
        else
            i = 0;
        if(i == 0)
            break MISSING_BLOCK_LABEL_107;
        Log.v("LocalBroadcastManager", (new StringBuilder()).append("Resolving type ").append(s1).append(" scheme ").append(s2).append(" of intent ").append(intent).toString());
        arraylist = (ArrayList)mActions.get(intent.getAction());
        if(arraylist == null) goto _L2; else goto _L1
_L1:
        if(i == 0) goto _L4; else goto _L3
_L3:
        Log.v("LocalBroadcastManager", (new StringBuilder()).append("Action list: ").append(arraylist).toString());
          goto _L4
_L20:
        if(j >= arraylist.size()) goto _L6; else goto _L5
_L5:
        receiverrecord = (ReceiverRecord)arraylist.get(j);
        if(i == 0)
            break MISSING_BLOCK_LABEL_214;
        Log.v("LocalBroadcastManager", (new StringBuilder()).append("Matching against filter ").append(receiverrecord.filter).toString());
        if(!receiverrecord.broadcasting) goto _L8; else goto _L7
_L7:
        obj = obj1;
        if(i == 0) goto _L10; else goto _L9
_L9:
        Log.v("LocalBroadcastManager", "  Filter's target already added");
        obj = obj1;
          goto _L10
_L8:
        k = receiverrecord.filter.match(s, s1, s2, uri, set, "LocalBroadcastManager");
        if(k < 0) goto _L12; else goto _L11
_L11:
        if(i == 0)
            break MISSING_BLOCK_LABEL_305;
        Log.v("LocalBroadcastManager", (new StringBuilder()).append("  Filter matched!  match=0x").append(Integer.toHexString(k)).toString());
        obj = obj1;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_323;
        obj = new ArrayList();
        ((ArrayList) (obj)).add(receiverrecord);
        receiverrecord.broadcasting = true;
          goto _L10
        intent;
        hashmap;
        JVM INSTR monitorexit ;
        throw intent;
_L12:
        obj = obj1;
        if(i == 0) goto _L10; else goto _L13
_L13:
        k;
        JVM INSTR tableswitch -4 -1: default 388
    //                   -4 533
    //                   -3 526
    //                   -2 540
    //                   -1 547;
           goto _L14 _L15 _L16 _L17 _L18
_L14:
        obj = "unknown reason";
_L21:
        Log.v("LocalBroadcastManager", (new StringBuilder()).append("  Filter did not match: ").append(((String) (obj))).toString());
        obj = obj1;
          goto _L10
_L19:
        if(i >= ((ArrayList) (obj1)).size())
            break MISSING_BLOCK_LABEL_454;
        ((ReceiverRecord)((ArrayList) (obj1)).get(i)).broadcasting = false;
        i++;
          goto _L19
        mPendingBroadcasts.add(new BroadcastRecord(intent, ((ArrayList) (obj1))));
        if(!mHandler.hasMessages(1))
            mHandler.sendEmptyMessage(1);
        hashmap;
        JVM INSTR monitorexit ;
        return true;
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return false;
_L4:
        obj1 = null;
        j = 0;
          goto _L20
_L10:
        j++;
        obj1 = obj;
          goto _L20
_L16:
        obj = "action";
          goto _L21
_L15:
        obj = "category";
          goto _L21
_L17:
        obj = "data";
          goto _L21
_L18:
        obj = "type";
          goto _L21
_L6:
        if(obj1 == null) goto _L2; else goto _L22
_L22:
        i = 0;
          goto _L19
    }

    public void sendBroadcastSync(Intent intent)
    {
        if(sendBroadcast(intent))
            executePendingBroadcasts();
    }

    public void unregisterReceiver(BroadcastReceiver broadcastreceiver)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mReceivers.remove(broadcastreceiver);
        if(arraylist != null)
            break MISSING_BLOCK_LABEL_31;
        hashmap;
        JVM INSTR monitorexit ;
        return;
        int i = arraylist.size() - 1;
_L12:
        if(i < 0) goto _L2; else goto _L1
_L1:
        ReceiverRecord receiverrecord;
        receiverrecord = (ReceiverRecord)arraylist.get(i);
        receiverrecord.dead = true;
        int j = 0;
_L10:
        String s;
        ArrayList arraylist1;
        if(j >= receiverrecord.filter.countActions())
            break; /* Loop/switch isn't completed */
        s = receiverrecord.filter.getAction(j);
        arraylist1 = (ArrayList)mActions.get(s);
        if(arraylist1 == null) goto _L4; else goto _L3
_L3:
        int k = arraylist1.size() - 1;
_L8:
        if(k < 0) goto _L6; else goto _L5
_L5:
        ReceiverRecord receiverrecord1 = (ReceiverRecord)arraylist1.get(k);
        if(receiverrecord1.receiver == broadcastreceiver)
        {
            receiverrecord1.dead = true;
            arraylist1.remove(k);
        }
          goto _L7
_L6:
        if(arraylist1.size() <= 0)
            mActions.remove(s);
        break; /* Loop/switch isn't completed */
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        broadcastreceiver;
        hashmap;
        JVM INSTR monitorexit ;
        throw broadcastreceiver;
_L7:
        k--;
        if(true) goto _L8; else goto _L4
_L4:
        j++;
        if(true) goto _L10; else goto _L9
_L9:
        i--;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    private final HashMap mActions = new HashMap();
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList mPendingBroadcasts = new ArrayList();
    private final HashMap mReceivers = new HashMap();


}

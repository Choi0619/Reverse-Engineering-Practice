// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.provider;

import android.content.*;
import android.content.pm.*;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.util.*;
import android.widget.TextView;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.Callable;

// Referenced classes of package android.support.v4.provider:
//            SelfDestructiveThread, FontRequest

public class FontsContractCompat
{
    public static final class Columns
        implements BaseColumns
    {

        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";

        public Columns()
        {
        }
    }

    public static class FontFamilyResult
    {

        public FontInfo[] getFonts()
        {
            return mFonts;
        }

        public int getStatusCode()
        {
            return mStatusCode;
        }

        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo mFonts[];
        private final int mStatusCode;

        public FontFamilyResult(int i, FontInfo afontinfo[])
        {
            mStatusCode = i;
            mFonts = afontinfo;
        }
    }

    static interface FontFamilyResult.FontResultStatus
        extends Annotation
    {
    }

    public static class FontInfo
    {

        public int getResultCode()
        {
            return mResultCode;
        }

        public int getTtcIndex()
        {
            return mTtcIndex;
        }

        public Uri getUri()
        {
            return mUri;
        }

        public int getWeight()
        {
            return mWeight;
        }

        public boolean isItalic()
        {
            return mItalic;
        }

        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        public FontInfo(Uri uri, int i, int j, boolean flag, int k)
        {
            mUri = (Uri)Preconditions.checkNotNull(uri);
            mTtcIndex = i;
            mWeight = j;
            mItalic = flag;
            mResultCode = k;
        }
    }

    public static class FontRequestCallback
    {

        public void onTypefaceRequestFailed(int i)
        {
        }

        public void onTypefaceRetrieved(Typeface typeface)
        {
        }

        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        public FontRequestCallback()
        {
        }
    }

    static interface FontRequestCallback.FontRequestFailReason
        extends Annotation
    {
    }


    private FontsContractCompat()
    {
    }

    public static Typeface buildTypeface(Context context, CancellationSignal cancellationsignal, FontInfo afontinfo[])
    {
        return TypefaceCompat.createFromFontInfo(context, cancellationsignal, afontinfo, 0);
    }

    private static List convertToByteArrayList(Signature asignature[])
    {
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < asignature.length; i++)
            arraylist.add(asignature[i].toByteArray());

        return arraylist;
    }

    private static boolean equalsByteArrayList(List list, List list1)
    {
        if(list.size() != list1.size())
            return false;
        for(int i = 0; i < list.size(); i++)
            if(!Arrays.equals((byte[])list.get(i), (byte[])list1.get(i)))
                return false;

        return true;
    }

    public static FontFamilyResult fetchFonts(Context context, CancellationSignal cancellationsignal, FontRequest fontrequest)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        ProviderInfo providerinfo = getProvider(context.getPackageManager(), fontrequest, context.getResources());
        if(providerinfo == null)
            return new FontFamilyResult(1, null);
        else
            return new FontFamilyResult(0, getFontFromProvider(context, fontrequest, providerinfo.authority, cancellationsignal));
    }

    private static List getCertificates(FontRequest fontrequest, Resources resources)
    {
        if(fontrequest.getCertificates() != null)
            return fontrequest.getCertificates();
        else
            return FontResourcesParserCompat.readCerts(resources, fontrequest.getCertificatesArrayResId());
    }

    static FontInfo[] getFontFromProvider(Context context, FontRequest fontrequest, String s, CancellationSignal cancellationsignal)
    {
        ArrayList arraylist;
        Object obj;
        Uri uri;
        Uri uri1;
        arraylist = new ArrayList();
        uri = (new android.net.Uri.Builder()).scheme("content").authority(s).build();
        uri1 = (new android.net.Uri.Builder()).scheme("content").authority(s).appendPath("file").build();
        obj = null;
        s = obj;
        if(android.os.Build.VERSION.SDK_INT <= 16) goto _L2; else goto _L1
_L1:
        s = obj;
        context = context.getContentResolver();
        s = obj;
        fontrequest = fontrequest.getQuery();
        s = obj;
        context = context.query(uri, new String[] {
            "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"
        }, "query = ?", new String[] {
            fontrequest
        }, null, cancellationsignal);
_L20:
        fontrequest = arraylist;
        if(context == null) goto _L4; else goto _L3
_L3:
        fontrequest = arraylist;
        s = context;
        if(context.getCount() <= 0) goto _L4; else goto _L5
_L5:
        s = context;
        int l = context.getColumnIndex("result_code");
        s = context;
        cancellationsignal = new ArrayList();
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        i1 = context.getColumnIndex("_id");
        j1 = context.getColumnIndex("file_id");
        k1 = context.getColumnIndex("font_ttc_index");
        l1 = context.getColumnIndex("font_weight");
        i2 = context.getColumnIndex("font_italic");
_L19:
        if(!context.moveToNext()) goto _L7; else goto _L6
_L6:
        if(l == -1) goto _L9; else goto _L8
_L8:
        int i = context.getInt(l);
_L21:
        if(k1 == -1) goto _L11; else goto _L10
_L10:
        int j = context.getInt(k1);
_L22:
        if(j1 != -1) goto _L13; else goto _L12
_L12:
        fontrequest = ContentUris.withAppendedId(uri, context.getLong(i1));
_L23:
        if(l1 == -1) goto _L15; else goto _L14
_L14:
        int k = context.getInt(l1);
_L24:
        if(i2 == -1) goto _L17; else goto _L16
_L16:
        if(context.getInt(i2) != 1) goto _L17; else goto _L18
_L18:
        boolean flag = true;
_L25:
        cancellationsignal.add(new FontInfo(fontrequest, j, k, flag, i));
          goto _L19
        fontrequest;
        s = context;
        context = fontrequest;
_L26:
        if(s != null)
            s.close();
        throw context;
_L2:
        s = obj;
        context = context.getContentResolver();
        s = obj;
        fontrequest = fontrequest.getQuery();
        s = obj;
        context = context.query(uri, new String[] {
            "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"
        }, "query = ?", new String[] {
            fontrequest
        }, null);
          goto _L20
_L9:
        i = 0;
          goto _L21
_L11:
        j = 0;
          goto _L22
_L13:
        fontrequest = ContentUris.withAppendedId(uri1, context.getLong(j1));
          goto _L23
_L15:
        k = 400;
          goto _L24
_L17:
        flag = false;
          goto _L25
_L7:
        fontrequest = cancellationsignal;
_L4:
        if(context != null)
            context.close();
        return (FontInfo[])fontrequest.toArray(new FontInfo[0]);
        context;
          goto _L26
    }

    private static Typeface getFontInternal(Context context, FontRequest fontrequest, int i)
    {
        Object obj = null;
        FontFamilyResult fontfamilyresult;
        try
        {
            fontfamilyresult = fetchFonts(context, null, fontrequest);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        fontrequest = obj;
        if(fontfamilyresult.getStatusCode() == 0)
            fontrequest = TypefaceCompat.createFromFontInfo(context, null, fontfamilyresult.getFonts(), i);
        return fontrequest;
    }

    public static Typeface getFontSync(Context context, FontRequest fontrequest, TextView textview, int i, int j, int k)
    {
        String s;
        s = (new StringBuilder()).append(fontrequest.getIdentifier()).append("-").append(k).toString();
        Typeface typeface = (Typeface)sTypefaceCache.get(s);
        if(typeface != null)
            return typeface;
        if(i == 0)
            i = 1;
        else
            i = 0;
        if(i != 0 && j == -1)
            return getFontInternal(context, fontrequest, k);
        context = new Callable(context, fontrequest, k, s) {

            public Typeface call()
                throws Exception
            {
                Typeface typeface1 = FontsContractCompat.getFontInternal(context, request, style);
                if(typeface1 != null)
                    FontsContractCompat.sTypefaceCache.put(id, typeface1);
                return typeface1;
            }

            public volatile Object call()
                throws Exception
            {
                return call();
            }

            final Context val$context;
            final String val$id;
            final FontRequest val$request;
            final int val$style;

            
            {
                context = context1;
                request = fontrequest;
                style = i;
                id = s;
                super();
            }
        }
;
        if(i != 0)
        {
            try
            {
                context = (Typeface)sBackgroundThread.postAndWait(context, j);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                return null;
            }
            return context;
        }
        textview = new SelfDestructiveThread.ReplyCallback(new WeakReference(textview), textview, k) {

            public void onReply(Typeface typeface1)
            {
                if((TextView)textViewWeak.get() != null)
                    targetView.setTypeface(typeface1, style);
            }

            public volatile void onReply(Object obj)
            {
                onReply((Typeface)obj);
            }

            final int val$style;
            final TextView val$targetView;
            final WeakReference val$textViewWeak;

            
            {
                textViewWeak = weakreference;
                targetView = textview;
                style = i;
                super();
            }
        }
;
        synchronized(sLock)
        {
            if(!sPendingReplies.containsKey(s))
                break MISSING_BLOCK_LABEL_177;
            ((ArrayList)sPendingReplies.get(s)).add(textview);
        }
        return null;
        context;
        fontrequest;
        JVM INSTR monitorexit ;
        throw context;
        ArrayList arraylist = new ArrayList();
        arraylist.add(textview);
        sPendingReplies.put(s, arraylist);
        fontrequest;
        JVM INSTR monitorexit ;
        sBackgroundThread.postAndReply(context, new SelfDestructiveThread.ReplyCallback(s) {

            public void onReply(Typeface typeface1)
            {
                ArrayList arraylist1;
                synchronized(FontsContractCompat.sLock)
                {
                    arraylist1 = (ArrayList)FontsContractCompat.sPendingReplies.get(id);
                    FontsContractCompat.sPendingReplies.remove(id);
                }
                for(int l = 0; l < arraylist1.size(); l++)
                    ((SelfDestructiveThread.ReplyCallback)arraylist1.get(l)).onReply(typeface1);

                break MISSING_BLOCK_LABEL_72;
                typeface1;
                obj;
                JVM INSTR monitorexit ;
                throw typeface1;
            }

            public volatile void onReply(Object obj)
            {
                onReply((Typeface)obj);
            }

            final String val$id;

            
            {
                id = s;
                super();
            }
        }
);
        return null;
    }

    public static ProviderInfo getProvider(PackageManager packagemanager, FontRequest fontrequest, Resources resources)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        String s = fontrequest.getProviderAuthority();
        ProviderInfo providerinfo = packagemanager.resolveContentProvider(s, 0);
        if(providerinfo == null)
            throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder()).append("No package found for authority: ").append(s).toString());
        if(!providerinfo.packageName.equals(fontrequest.getProviderPackage()))
            throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder()).append("Found content provider ").append(s).append(", but package was not ").append(fontrequest.getProviderPackage()).toString());
        packagemanager = convertToByteArrayList(packagemanager.getPackageInfo(providerinfo.packageName, 64).signatures);
        Collections.sort(packagemanager, sByteArrayComparator);
        fontrequest = getCertificates(fontrequest, resources);
        for(int i = 0; i < fontrequest.size(); i++)
        {
            resources = new ArrayList((Collection)fontrequest.get(i));
            Collections.sort(resources, sByteArrayComparator);
            if(equalsByteArrayList(packagemanager, resources))
                return providerinfo;
        }

        return null;
    }

    public static Map prepareFontData(Context context, FontInfo afontinfo[], CancellationSignal cancellationsignal)
    {
        HashMap hashmap = new HashMap();
        int j = afontinfo.length;
        int i = 0;
        while(i < j) 
        {
            Object obj = afontinfo[i];
            if(((FontInfo) (obj)).getResultCode() == 0)
            {
                obj = ((FontInfo) (obj)).getUri();
                if(!hashmap.containsKey(obj))
                    hashmap.put(obj, TypefaceCompatUtil.mmap(context, cancellationsignal, ((Uri) (obj))));
            }
            i++;
        }
        return Collections.unmodifiableMap(hashmap);
    }

    public static void requestFont(Context context, FontRequest fontrequest, FontRequestCallback fontrequestcallback, Handler handler)
    {
        handler.post(new Runnable(context, fontrequest, new Handler(), fontrequestcallback) {

            public void run()
            {
                Object obj;
                try
                {
                    obj = FontsContractCompat.fetchFonts(context, null, request);
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    callerThreadHandler.post(new Runnable() {

                        public void run()
                        {
                            callback.onTypefaceRequestFailed(-1);
                        }

                        final _cls4 this$0;

            
            {
                this$0 = _cls4.this;
                super();
            }
                    }
);
                    return;
                }
                if(((FontFamilyResult) (obj)).getStatusCode() != 0)
                {
                    switch(((FontFamilyResult) (obj)).getStatusCode())
                    {
                    default:
                        callerThreadHandler.post(new Runnable() {

                            public void run()
                            {
                                callback.onTypefaceRequestFailed(-3);
                            }

                            final _cls4 this$0;

            
            {
                this$0 = _cls4.this;
                super();
            }
                        }
);
                        return;

                    case 1: // '\001'
                        callerThreadHandler.post(new Runnable() {

                            public void run()
                            {
                                callback.onTypefaceRequestFailed(-2);
                            }

                            final _cls4 this$0;

            
            {
                this$0 = _cls4.this;
                super();
            }
                        }
);
                        return;

                    case 2: // '\002'
                        callerThreadHandler.post(new Runnable() {

                            public void run()
                            {
                                callback.onTypefaceRequestFailed(-3);
                            }

                            final _cls4 this$0;

            
            {
                this$0 = _cls4.this;
                super();
            }
                        }
);
                        break;
                    }
                    return;
                }
                FontInfo afontinfo[] = ((FontFamilyResult) (obj)).getFonts();
                if(afontinfo == null || afontinfo.length == 0)
                {
                    callerThreadHandler.post(new Runnable() {

                        public void run()
                        {
                            callback.onTypefaceRequestFailed(1);
                        }

                        final _cls4 this$0;

            
            {
                this$0 = _cls4.this;
                super();
            }
                    }
);
                    return;
                }
                int j = afontinfo.length;
                for(int i = 0; i < j; i++)
                {
                    FontInfo fontinfo = afontinfo[i];
                    if(fontinfo.getResultCode() != 0)
                    {
                        i = fontinfo.getResultCode();
                        if(i < 0)
                        {
                            callerThreadHandler.post(new Runnable() {

                                public void run()
                                {
                                    callback.onTypefaceRequestFailed(-3);
                                }

                                final _cls4 this$0;

            
            {
                this$0 = _cls4.this;
                super();
            }
                            }
);
                            return;
                        } else
                        {
                            callerThreadHandler.post(i. new Runnable() {

                                public void run()
                                {
                                    callback.onTypefaceRequestFailed(resultCode);
                                }

                                final _cls4 this$0;
                                final int val$resultCode;

            
            {
                this$0 = final__pcls4;
                resultCode = I.this;
                super();
            }
                            }
);
                            return;
                        }
                    }
                }

                afontinfo = FontsContractCompat.buildTypeface(context, null, afontinfo);
                if(afontinfo == null)
                {
                    callerThreadHandler.post(new Runnable() {

                        public void run()
                        {
                            callback.onTypefaceRequestFailed(-3);
                        }

                        final _cls4 this$0;

            
            {
                this$0 = _cls4.this;
                super();
            }
                    }
);
                    return;
                } else
                {
                    callerThreadHandler.post(afontinfo. new Runnable() {

                        public void run()
                        {
                            callback.onTypefaceRetrieved(typeface);
                        }

                        final _cls4 this$0;
                        final Typeface val$typeface;

            
            {
                this$0 = final__pcls4;
                typeface = Typeface.this;
                super();
            }
                    }
);
                    return;
                }
            }

            final FontRequestCallback val$callback;
            final Handler val$callerThreadHandler;
            final Context val$context;
            final FontRequest val$request;

            
            {
                context = context1;
                request = fontrequest;
                callerThreadHandler = handler;
                callback = fontrequestcallback;
                super();
            }
        }
);
    }

    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    public static final String PARCEL_FONT_RESULTS = "font_results";
    public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
    private static final Comparator sByteArrayComparator = new Comparator() {

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((byte[])obj, (byte[])obj1);
        }

        public int compare(byte abyte0[], byte abyte1[])
        {
            if(abyte0.length != abyte1.length)
                return abyte0.length - abyte1.length;
            for(int i = 0; i < abyte0.length; i++)
                if(abyte0[i] != abyte1[i])
                    return abyte0[i] - abyte1[i];

            return 0;
        }

    }
;
    private static final Object sLock = new Object();
    private static final SimpleArrayMap sPendingReplies = new SimpleArrayMap();
    private static final LruCache sTypefaceCache = new LruCache(16);





}

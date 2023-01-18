// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TypefaceCompatUtil
{

    private TypefaceCompatUtil()
    {
    }

    public static void closeQuietly(Closeable closeable)
    {
        if(closeable == null)
            break MISSING_BLOCK_LABEL_10;
        closeable.close();
        return;
        closeable;
    }

    public static ByteBuffer copyToDirectBuffer(Context context, Resources resources, int i)
    {
        context = getTempFile(context);
        if(context == null)
            return null;
        boolean flag = copyToFile(context, resources, i);
        if(!flag)
        {
            context.delete();
            return null;
        }
        resources = mmap(context);
        context.delete();
        return resources;
        resources;
        context.delete();
        throw resources;
    }

    public static boolean copyToFile(File file, Resources resources, int i)
    {
        Resources resources1 = null;
        resources = resources.openRawResource(i);
        resources1 = resources;
        boolean flag = copyToFile(file, ((InputStream) (resources)));
        closeQuietly(resources);
        return flag;
        file;
        closeQuietly(resources1);
        throw file;
    }

    public static boolean copyToFile(File file, InputStream inputstream)
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        file = new FileOutputStream(file, false);
        obj = new byte[1024];
_L3:
        int i = inputstream.read(((byte []) (obj)));
        if(i == -1) goto _L2; else goto _L1
_L1:
        file.write(((byte []) (obj)), 0, i);
          goto _L3
        inputstream;
_L7:
        obj = file;
        Log.e("TypefaceCompatUtil", (new StringBuilder()).append("Error copying resource contents to temp file: ").append(inputstream.getMessage()).toString());
        closeQuietly(file);
        return false;
_L2:
        closeQuietly(file);
        return true;
        file;
_L5:
        closeQuietly(((Closeable) (obj)));
        throw file;
        inputstream;
        obj = file;
        file = inputstream;
        if(true) goto _L5; else goto _L4
_L4:
        inputstream;
        file = obj1;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static File getTempFile(Context context)
    {
        int i;
        String s;
        s = (new StringBuilder()).append(".font").append(Process.myPid()).append("-").append(Process.myTid()).append("-").toString();
        i = 0;
_L3:
        if(i >= 100) goto _L2; else goto _L1
_L1:
        File file = new File(context.getCacheDir(), (new StringBuilder()).append(s).append(i).toString());
        boolean flag = file.createNewFile();
        if(flag)
            return file;
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        i++;
          goto _L3
_L2:
        return null;
    }

    public static ByteBuffer mmap(Context context, CancellationSignal cancellationsignal, Uri uri)
    {
        Object obj;
        context = context.getContentResolver();
        long l;
        try
        {
            uri = context.openFileDescriptor(uri, "r", cancellationsignal);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        obj = new FileInputStream(uri.getFileDescriptor());
        context = ((FileInputStream) (obj)).getChannel();
        l = context.size();
        context = context.map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, l);
        if(obj == null) goto _L2; else goto _L1
_L1:
        if(true) goto _L4; else goto _L3
_L3:
        ((FileInputStream) (obj)).close();
_L2:
        if(uri == null) goto _L6; else goto _L5
_L5:
        if(true) goto _L8; else goto _L7
_L7:
        uri.close();
_L6:
        return context;
        context;
        try
        {
            throw new NullPointerException();
        }
        // Misplaced declaration of an exception variable
        catch(Context context) { }
        throw context;
        cancellationsignal;
_L13:
        if(uri == null) goto _L10; else goto _L9
_L9:
        if(context == null) goto _L12; else goto _L11
_L11:
        uri.close();
_L10:
        throw cancellationsignal;
_L4:
        ((FileInputStream) (obj)).close();
          goto _L2
        cancellationsignal;
        context = null;
          goto _L13
        context;
        throw new NullPointerException();
_L8:
        uri.close();
        return context;
        context;
        throw context;
        cancellationsignal;
_L19:
        if(obj == null) goto _L15; else goto _L14
_L14:
        if(context == null) goto _L17; else goto _L16
_L16:
        ((FileInputStream) (obj)).close();
_L15:
        throw cancellationsignal;
        obj;
        context.addSuppressed(((Throwable) (obj)));
        continue; /* Loop/switch isn't completed */
_L17:
        ((FileInputStream) (obj)).close();
        if(true) goto _L15; else goto _L18
_L18:
        uri;
        context.addSuppressed(uri);
          goto _L10
_L12:
        uri.close();
          goto _L10
        cancellationsignal;
        context = null;
          goto _L19
    }

    private static ByteBuffer mmap(File file)
    {
        long l;
        Throwable throwable;
        Object obj;
        try
        {
            obj = new FileInputStream(file);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            return null;
        }
        file = ((FileInputStream) (obj)).getChannel();
        l = file.size();
        file = file.map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, l);
        if(obj == null)
            break MISSING_BLOCK_LABEL_45;
        if(true)
            break MISSING_BLOCK_LABEL_56;
        ((FileInputStream) (obj)).close();
        return file;
        file;
        throw new NullPointerException();
        ((FileInputStream) (obj)).close();
        return file;
        throwable;
        throw throwable;
        file;
_L3:
        if(obj == null)
            break MISSING_BLOCK_LABEL_81;
        if(throwable == null)
            break MISSING_BLOCK_LABEL_94;
        ((FileInputStream) (obj)).close();
_L1:
        throw file;
        obj;
        throwable.addSuppressed(((Throwable) (obj)));
          goto _L1
        ((FileInputStream) (obj)).close();
          goto _L1
        file;
        throwable = null;
        if(true) goto _L3; else goto _L2
_L2:
    }

    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";
}

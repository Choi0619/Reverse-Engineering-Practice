// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.system.*;
import java.io.*;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompatBaseImpl

class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl
{

    TypefaceCompatApi21Impl()
    {
    }

    private File getFile(ParcelFileDescriptor parcelfiledescriptor)
    {
label0:
        {
            try
            {
                parcelfiledescriptor = Os.readlink((new StringBuilder()).append("/proc/self/fd/").append(parcelfiledescriptor.getFd()).toString());
                if(!OsConstants.S_ISREG(Os.stat(parcelfiledescriptor).st_mode))
                    break label0;
                parcelfiledescriptor = new File(parcelfiledescriptor);
            }
            // Misplaced declaration of an exception variable
            catch(ParcelFileDescriptor parcelfiledescriptor)
            {
                return null;
            }
            return parcelfiledescriptor;
        }
        return null;
    }

    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationsignal, android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i)
    {
        if(afontinfo.length >= 1) goto _L2; else goto _L1
_L1:
        context = null;
_L11:
        return context;
_L2:
        Object obj;
        afontinfo = findBestInfo(afontinfo, i);
        obj = context.getContentResolver();
        try
        {
            afontinfo = ((ContentResolver) (obj)).openFileDescriptor(afontinfo.getUri(), "r", cancellationsignal);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        cancellationsignal = getFile(afontinfo);
        if(cancellationsignal == null) goto _L4; else goto _L3
_L3:
        if(cancellationsignal.canRead()) goto _L5; else goto _L4
_L4:
        obj = new FileInputStream(afontinfo.getFileDescriptor());
        cancellationsignal = null;
        context = super.createFromInputStream(context, ((java.io.InputStream) (obj)));
        cancellationsignal = context;
        if(obj == null) goto _L7; else goto _L6
_L6:
        if(true) goto _L9; else goto _L8
_L8:
        ((FileInputStream) (obj)).close();
_L7:
        context = cancellationsignal;
        if(afontinfo == null) goto _L11; else goto _L10
_L10:
        if(true) goto _L13; else goto _L12
_L12:
        afontinfo.close();
        return cancellationsignal;
        context;
        throw new NullPointerException();
        context;
        try
        {
            throw new NullPointerException();
        }
        // Misplaced declaration of an exception variable
        catch(Context context) { }
        throw context;
        cancellationsignal;
_L14:
        if(afontinfo == null)
            break MISSING_BLOCK_LABEL_146;
        if(context == null)
            break MISSING_BLOCK_LABEL_254;
        afontinfo.close();
_L17:
        throw cancellationsignal;
_L9:
        ((FileInputStream) (obj)).close();
          goto _L7
        cancellationsignal;
        context = null;
          goto _L14
_L13:
        afontinfo.close();
        return cancellationsignal;
        context;
        cancellationsignal = context;
        throw context;
        context;
        if(obj == null)
            break MISSING_BLOCK_LABEL_188;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_201;
        ((FileInputStream) (obj)).close();
_L15:
        throw context;
        obj;
        cancellationsignal.addSuppressed(((Throwable) (obj)));
          goto _L15
        ((FileInputStream) (obj)).close();
          goto _L15
_L5:
        cancellationsignal = Typeface.createFromFile(cancellationsignal);
        context = cancellationsignal;
        if(afontinfo == null) goto _L11; else goto _L16
_L16:
        if(true)
            break MISSING_BLOCK_LABEL_239;
        afontinfo.close();
        return cancellationsignal;
        context;
        throw new NullPointerException();
        afontinfo.close();
        return cancellationsignal;
        afontinfo;
        context.addSuppressed(afontinfo);
          goto _L17
        afontinfo.close();
          goto _L17
    }

    private static final String TAG = "TypefaceCompatApi21Impl";
}

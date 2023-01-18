// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.OperationCanceledException;
import android.support.v4.os.CancellationSignal;

public final class ContentResolverCompat
{

    private ContentResolverCompat()
    {
    }

    public static Cursor query(ContentResolver contentresolver, Uri uri, String as[], String s, String as1[], String s1, CancellationSignal cancellationsignal)
    {
        if(android.os.Build.VERSION.SDK_INT < 16)
            break MISSING_BLOCK_LABEL_66;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_42;
        cancellationsignal = ((CancellationSignal) (cancellationsignal.getCancellationSignalObject()));
_L1:
        contentresolver = contentresolver.query(uri, as, s, as1, s1, (android.os.CancellationSignal)(android.os.CancellationSignal)cancellationsignal);
        return contentresolver;
        cancellationsignal = null;
          goto _L1
        contentresolver;
        if(contentresolver instanceof OperationCanceledException)
            throw new android.support.v4.os.OperationCanceledException();
        else
            throw contentresolver;
        if(cancellationsignal != null)
            cancellationsignal.throwIfCanceled();
        return contentresolver.query(uri, as, s, as1, s1);
    }
}

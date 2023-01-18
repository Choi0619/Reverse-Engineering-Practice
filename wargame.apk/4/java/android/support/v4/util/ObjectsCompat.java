// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;

import java.util.Objects;

public class ObjectsCompat
{
    private static class ImplApi19 extends ImplBase
    {

        public boolean equals(Object obj, Object obj1)
        {
            return Objects.equals(obj, obj1);
        }

        private ImplApi19()
        {
        }

    }

    private static class ImplBase
    {

        public boolean equals(Object obj, Object obj1)
        {
            return obj == obj1 || obj != null && obj.equals(obj1);
        }

        private ImplBase()
        {
        }

    }


    private ObjectsCompat()
    {
    }

    public static boolean equals(Object obj, Object obj1)
    {
        return IMPL.equals(obj, obj1);
    }

    private static final ImplBase IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            IMPL = new ImplApi19();
        else
            IMPL = new ImplBase();
    }
}

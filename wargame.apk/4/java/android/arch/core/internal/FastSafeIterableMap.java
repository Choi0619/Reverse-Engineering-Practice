// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.arch.core.internal;

import java.util.HashMap;

// Referenced classes of package android.arch.core.internal:
//            SafeIterableMap

public class FastSafeIterableMap extends SafeIterableMap
{

    public FastSafeIterableMap()
    {
        mHashMap = new HashMap();
    }

    public java.util.Map.Entry ceil(Object obj)
    {
        if(contains(obj))
            return ((SafeIterableMap.Entry)mHashMap.get(obj)).mPrevious;
        else
            return null;
    }

    public boolean contains(Object obj)
    {
        return mHashMap.containsKey(obj);
    }

    protected SafeIterableMap.Entry get(Object obj)
    {
        return (SafeIterableMap.Entry)mHashMap.get(obj);
    }

    public Object putIfAbsent(Object obj, Object obj1)
    {
        SafeIterableMap.Entry entry = get(obj);
        if(entry != null)
        {
            return entry.mValue;
        } else
        {
            mHashMap.put(obj, put(obj, obj1));
            return null;
        }
    }

    public Object remove(Object obj)
    {
        Object obj1 = super.remove(obj);
        mHashMap.remove(obj);
        return obj1;
    }

    private HashMap mHashMap;
}

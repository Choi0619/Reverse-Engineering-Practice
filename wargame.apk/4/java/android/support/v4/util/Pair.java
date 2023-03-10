// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;


public class Pair
{

    public Pair(Object obj, Object obj1)
    {
        first = obj;
        second = obj1;
    }

    public static Pair create(Object obj, Object obj1)
    {
        return new Pair(obj, obj1);
    }

    private static boolean objectsEqual(Object obj, Object obj1)
    {
        return obj == obj1 || obj != null && obj.equals(obj1);
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof Pair)
            if(objectsEqual(((Pair) (obj = (Pair)obj)).first, first) && objectsEqual(((Pair) (obj)).second, second))
                return true;
        return false;
    }

    public int hashCode()
    {
        int j = 0;
        int i;
        if(first == null)
            i = 0;
        else
            i = first.hashCode();
        if(second != null)
            j = second.hashCode();
        return i ^ j;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Pair{").append(String.valueOf(first)).append(" ").append(String.valueOf(second)).append("}").toString();
    }

    public final Object first;
    public final Object second;
}

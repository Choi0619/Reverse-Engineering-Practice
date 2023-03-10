// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;

import java.util.ConcurrentModificationException;
import java.util.Map;

// Referenced classes of package android.support.v4.util:
//            ContainerHelpers, ArrayMap

public class SimpleArrayMap
{

    public SimpleArrayMap()
    {
        mHashes = ContainerHelpers.EMPTY_INTS;
        mArray = ContainerHelpers.EMPTY_OBJECTS;
        mSize = 0;
    }

    public SimpleArrayMap(int i)
    {
        if(i == 0)
        {
            mHashes = ContainerHelpers.EMPTY_INTS;
            mArray = ContainerHelpers.EMPTY_OBJECTS;
        } else
        {
            allocArrays(i);
        }
        mSize = 0;
    }

    public SimpleArrayMap(SimpleArrayMap simplearraymap)
    {
        this();
        if(simplearraymap != null)
            putAll(simplearraymap);
    }

    private void allocArrays(int i)
    {
        if(i != 8) goto _L2; else goto _L1
_L1:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        Object aobj[];
        if(mTwiceBaseCache == null)
            break MISSING_BLOCK_LABEL_69;
        aobj = mTwiceBaseCache;
        mArray = aobj;
        mTwiceBaseCache = (Object[])(Object[])aobj[0];
        mHashes = (int[])(int[])aobj[1];
        aobj[1] = null;
        aobj[0] = null;
        mTwiceBaseCacheSize--;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        return;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
_L4:
        mHashes = new int[i];
        mArray = new Object[i << 1];
        return;
        Exception exception;
        exception;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        if(i != 4) goto _L4; else goto _L3
_L3:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        if(mBaseCache == null)
            break MISSING_BLOCK_LABEL_170;
        exception = ((Exception) (mBaseCache));
        mArray = exception;
        mBaseCache = (Object[])(Object[])exception[0];
        mHashes = (int[])(int[])exception[1];
        exception[1] = null;
        exception[0] = null;
        mBaseCacheSize--;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        return;
        exception;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        throw exception;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
          goto _L4
    }

    private static int binarySearchHashes(int ai[], int i, int j)
    {
        try
        {
            i = ContainerHelpers.binarySearch(ai, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw new ConcurrentModificationException();
        }
        return i;
    }

    private static void freeArrays(int ai[], Object aobj[], int i)
    {
        if(ai.length != 8) goto _L2; else goto _L1
_L1:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        if(mTwiceBaseCacheSize < 10)
        {
            aobj[0] = ((Object) (mTwiceBaseCache));
            break MISSING_BLOCK_LABEL_24;
        }
          goto _L3
_L8:
        mTwiceBaseCache = aobj;
        mTwiceBaseCacheSize++;
_L3:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        return;
        ai;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        throw ai;
_L2:
        if(ai.length != 4) goto _L5; else goto _L4
_L4:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        if(mBaseCacheSize >= 10) goto _L7; else goto _L6
_L6:
        aobj[0] = ((Object) (mBaseCache));
        aobj[1] = ai;
        i = (i << 1) - 1;
        break MISSING_BLOCK_LABEL_134;
_L9:
        mBaseCache = aobj;
        mBaseCacheSize++;
_L7:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        return;
        ai;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        throw ai;
        aobj[1] = ai;
        i = (i << 1) - 1;
        while(i >= 2) 
        {
            aobj[i] = null;
            i--;
        }
          goto _L8
_L5:
        return;
        while(i >= 2) 
        {
            aobj[i] = null;
            i--;
        }
          goto _L9
    }

    public void clear()
    {
        if(mSize > 0)
        {
            int ai[] = mHashes;
            Object aobj[] = mArray;
            int i = mSize;
            mHashes = ContainerHelpers.EMPTY_INTS;
            mArray = ContainerHelpers.EMPTY_OBJECTS;
            mSize = 0;
            freeArrays(ai, aobj, i);
        }
        if(mSize > 0)
            throw new ConcurrentModificationException();
        else
            return;
    }

    public boolean containsKey(Object obj)
    {
        return indexOfKey(obj) >= 0;
    }

    public boolean containsValue(Object obj)
    {
        return indexOfValue(obj) >= 0;
    }

    public void ensureCapacity(int i)
    {
        int j = mSize;
        if(mHashes.length < i)
        {
            int ai[] = mHashes;
            Object aobj[] = mArray;
            allocArrays(i);
            if(mSize > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, j);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, j << 1);
            }
            freeArrays(ai, aobj, j);
        }
        if(mSize != j)
            throw new ConcurrentModificationException();
        else
            return;
    }

    public boolean equals(Object obj)
    {
        if(this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if(!(obj instanceof SimpleArrayMap)) goto _L4; else goto _L3
_L3:
        int i;
        obj = (SimpleArrayMap)obj;
        if(size() != ((SimpleArrayMap) (obj)).size())
            return false;
        i = 0;
_L5:
        Object obj1;
        Object obj2;
        Object obj3;
        if(i >= mSize)
            continue; /* Loop/switch isn't completed */
        obj1 = keyAt(i);
        obj2 = valueAt(i);
        obj3 = ((SimpleArrayMap) (obj)).get(obj1);
        if(obj2 != null)
            break MISSING_BLOCK_LABEL_86;
        if(obj3 != null)
            break MISSING_BLOCK_LABEL_227;
        if(!((SimpleArrayMap) (obj)).containsKey(obj1))
            break MISSING_BLOCK_LABEL_227;
        break MISSING_BLOCK_LABEL_100;
        boolean flag;
        try
        {
            flag = obj2.equals(obj3);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(!flag)
            return false;
        i++;
        if(true) goto _L5; else goto _L4
_L4:
        if(!(obj instanceof Map))
            break; /* Loop/switch isn't completed */
        obj = (Map)obj;
        if(size() != ((Map) (obj)).size())
            return false;
        i = 0;
_L7:
        if(i >= mSize)
            break; /* Loop/switch isn't completed */
        obj1 = keyAt(i);
        obj2 = valueAt(i);
        obj3 = ((Map) (obj)).get(obj1);
        if(obj2 != null)
            break MISSING_BLOCK_LABEL_198;
        if(obj3 != null)
            break MISSING_BLOCK_LABEL_229;
        if(!((Map) (obj)).containsKey(obj1))
            break MISSING_BLOCK_LABEL_229;
        break MISSING_BLOCK_LABEL_212;
        boolean flag1;
        try
        {
            flag1 = obj2.equals(obj3);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(!flag1)
            return false;
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        if(true) goto _L1; else goto _L8
_L8:
        return false;
        return false;
        return false;
    }

    public Object get(Object obj)
    {
        int i = indexOfKey(obj);
        if(i >= 0)
            return mArray[(i << 1) + 1];
        else
            return null;
    }

    public int hashCode()
    {
        int ai[] = mHashes;
        Object aobj[] = mArray;
        int k = 0;
        int j = 0;
        int i = 1;
        int i1 = mSize;
        while(j < i1) 
        {
            Object obj = aobj[i];
            int j1 = ai[j];
            int l;
            if(obj == null)
                l = 0;
            else
                l = obj.hashCode();
            k += l ^ j1;
            j++;
            i += 2;
        }
        return k;
    }

    int indexOf(Object obj, int i)
    {
        int i1 = mSize;
        int j;
        if(i1 == 0)
        {
            j = -1;
        } else
        {
            int l = binarySearchHashes(mHashes, i1, i);
            j = l;
            if(l >= 0)
            {
                j = l;
                if(!obj.equals(mArray[l << 1]))
                {
                    int k;
                    for(k = l + 1; k < i1 && mHashes[k] == i; k++)
                        if(obj.equals(mArray[k << 1]))
                            return k;

                    for(l--; l >= 0 && mHashes[l] == i; l--)
                        if(obj.equals(mArray[l << 1]))
                            return l;

                    return ~k;
                }
            }
        }
        return j;
    }

    public int indexOfKey(Object obj)
    {
        if(obj == null)
            return indexOfNull();
        else
            return indexOf(obj, obj.hashCode());
    }

    int indexOfNull()
    {
        int l = mSize;
        int i;
        if(l == 0)
        {
            i = -1;
        } else
        {
            int k = binarySearchHashes(mHashes, l, 0);
            i = k;
            if(k >= 0)
            {
                i = k;
                if(mArray[k << 1] != null)
                {
                    int j;
                    for(j = k + 1; j < l && mHashes[j] == 0; j++)
                        if(mArray[j << 1] == null)
                            return j;

                    for(k--; k >= 0 && mHashes[k] == 0; k--)
                        if(mArray[k << 1] == null)
                            return k;

                    return ~j;
                }
            }
        }
        return i;
    }

    int indexOfValue(Object obj)
    {
        int k = mSize * 2;
        Object aobj[] = mArray;
        if(obj == null)
        {
            for(int i = 1; i < k; i += 2)
                if(aobj[i] == null)
                    return i >> 1;

        } else
        {
            for(int j = 1; j < k; j += 2)
                if(obj.equals(aobj[j]))
                    return j >> 1;

        }
        return -1;
    }

    public boolean isEmpty()
    {
        return mSize <= 0;
    }

    public Object keyAt(int i)
    {
        return mArray[i << 1];
    }

    public Object put(Object obj, Object obj1)
    {
        int k;
        byte byte0;
        int l;
        int i1;
        byte0 = 8;
        l = mSize;
        int i;
        if(obj == null)
        {
            k = 0;
            i = indexOfNull();
        } else
        {
            k = obj.hashCode();
            i = indexOf(obj, k);
        }
        if(i >= 0)
        {
            i = (i << 1) + 1;
            obj = mArray[i];
            mArray[i] = obj1;
            return obj;
        }
        i1 = ~i;
        if(l < mHashes.length)
            break MISSING_BLOCK_LABEL_188;
        if(l < 8) goto _L2; else goto _L1
_L1:
        int j = l + (l >> 1);
_L4:
        int ai[];
        Object aobj[];
        ai = mHashes;
        aobj = mArray;
        allocArrays(j);
        if(l != mSize)
            throw new ConcurrentModificationException();
        break; /* Loop/switch isn't completed */
_L2:
        j = byte0;
        if(l < 4)
            j = 4;
        if(true) goto _L4; else goto _L3
_L3:
        if(mHashes.length > 0)
        {
            System.arraycopy(ai, 0, mHashes, 0, ai.length);
            System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, aobj.length);
        }
        freeArrays(ai, aobj, l);
        if(i1 < l)
        {
            System.arraycopy(mHashes, i1, mHashes, i1 + 1, l - i1);
            System.arraycopy(((Object) (mArray)), i1 << 1, ((Object) (mArray)), i1 + 1 << 1, mSize - i1 << 1);
        }
        if(l != mSize || i1 >= mHashes.length)
        {
            throw new ConcurrentModificationException();
        } else
        {
            mHashes[i1] = k;
            mArray[i1 << 1] = obj;
            mArray[(i1 << 1) + 1] = obj1;
            mSize = mSize + 1;
            return null;
        }
    }

    public void putAll(SimpleArrayMap simplearraymap)
    {
        int j = simplearraymap.mSize;
        ensureCapacity(mSize + j);
        if(mSize == 0)
        {
            if(j > 0)
            {
                System.arraycopy(simplearraymap.mHashes, 0, mHashes, 0, j);
                System.arraycopy(((Object) (simplearraymap.mArray)), 0, ((Object) (mArray)), 0, j << 1);
                mSize = j;
            }
        } else
        {
            int i = 0;
            while(i < j) 
            {
                put(simplearraymap.keyAt(i), simplearraymap.valueAt(i));
                i++;
            }
        }
    }

    public Object remove(Object obj)
    {
        int i = indexOfKey(obj);
        if(i >= 0)
            return removeAt(i);
        else
            return null;
    }

    public Object removeAt(int i)
    {
        int j = 8;
        Object obj = mArray[(i << 1) + 1];
        int l = mSize;
        if(l <= 1)
        {
            freeArrays(mHashes, mArray, l);
            mHashes = ContainerHelpers.EMPTY_INTS;
            mArray = ContainerHelpers.EMPTY_OBJECTS;
            j = 0;
        } else
        {
            int k = l - 1;
            if(mHashes.length > 8 && mSize < mHashes.length / 3)
            {
                if(l > 8)
                    j = l + (l >> 1);
                int ai[] = mHashes;
                Object aobj[] = mArray;
                allocArrays(j);
                if(l != mSize)
                    throw new ConcurrentModificationException();
                if(i > 0)
                {
                    System.arraycopy(ai, 0, mHashes, 0, i);
                    System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, i << 1);
                }
                j = k;
                if(i < k)
                {
                    System.arraycopy(ai, i + 1, mHashes, i, k - i);
                    System.arraycopy(((Object) (aobj)), i + 1 << 1, ((Object) (mArray)), i << 1, k - i << 1);
                    j = k;
                }
            } else
            {
                if(i < k)
                {
                    System.arraycopy(mHashes, i + 1, mHashes, i, k - i);
                    System.arraycopy(((Object) (mArray)), i + 1 << 1, ((Object) (mArray)), i << 1, k - i << 1);
                }
                mArray[k << 1] = null;
                mArray[(k << 1) + 1] = null;
                j = k;
            }
        }
        if(l != mSize)
        {
            throw new ConcurrentModificationException();
        } else
        {
            mSize = j;
            return obj;
        }
    }

    public Object setValueAt(int i, Object obj)
    {
        i = (i << 1) + 1;
        Object obj1 = mArray[i];
        mArray[i] = obj;
        return obj1;
    }

    public int size()
    {
        return mSize;
    }

    public String toString()
    {
        if(isEmpty())
            return "{}";
        StringBuilder stringbuilder = new StringBuilder(mSize * 28);
        stringbuilder.append('{');
        int i = 0;
        while(i < mSize) 
        {
            if(i > 0)
                stringbuilder.append(", ");
            Object obj = keyAt(i);
            if(obj != this)
                stringbuilder.append(obj);
            else
                stringbuilder.append("(this Map)");
            stringbuilder.append('=');
            obj = valueAt(i);
            if(obj != this)
                stringbuilder.append(obj);
            else
                stringbuilder.append("(this Map)");
            i++;
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public Object valueAt(int i)
    {
        return mArray[(i << 1) + 1];
    }

    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    static Object mBaseCache[];
    static int mBaseCacheSize;
    static Object mTwiceBaseCache[];
    static int mTwiceBaseCacheSize;
    Object mArray[];
    int mHashes[];
    int mSize;
}

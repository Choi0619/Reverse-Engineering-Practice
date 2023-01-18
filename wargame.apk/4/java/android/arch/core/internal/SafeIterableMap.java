// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.arch.core.internal;

import java.util.*;

public class SafeIterableMap
    implements Iterable
{
    static class AscendingIterator extends ListIterator
    {

        SafeIterableMap.Entry backward(SafeIterableMap.Entry entry)
        {
            return entry.mPrevious;
        }

        SafeIterableMap.Entry forward(SafeIterableMap.Entry entry)
        {
            return entry.mNext;
        }

        AscendingIterator(SafeIterableMap.Entry entry, SafeIterableMap.Entry entry1)
        {
            super(entry, entry1);
        }
    }

    private static class DescendingIterator extends ListIterator
    {

        SafeIterableMap.Entry backward(SafeIterableMap.Entry entry)
        {
            return entry.mNext;
        }

        SafeIterableMap.Entry forward(SafeIterableMap.Entry entry)
        {
            return entry.mPrevious;
        }

        DescendingIterator(SafeIterableMap.Entry entry, SafeIterableMap.Entry entry1)
        {
            super(entry, entry1);
        }
    }

    static class Entry
        implements java.util.Map.Entry
    {

        public boolean equals(Object obj)
        {
            if(obj != this)
            {
                if(!(obj instanceof Entry))
                    return false;
                obj = (Entry)obj;
                if(!mKey.equals(((Entry) (obj)).mKey) || !mValue.equals(((Entry) (obj)).mValue))
                    return false;
            }
            return true;
        }

        public Object getKey()
        {
            return mKey;
        }

        public Object getValue()
        {
            return mValue;
        }

        public Object setValue(Object obj)
        {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString()
        {
            return (new StringBuilder()).append(mKey).append("=").append(mValue).toString();
        }

        final Object mKey;
        Entry mNext;
        Entry mPrevious;
        final Object mValue;

        Entry(Object obj, Object obj1)
        {
            mKey = obj;
            mValue = obj1;
        }
    }

    private class IteratorWithAdditions
        implements Iterator, SupportRemove
    {

        public boolean hasNext()
        {
            if(!mBeforeStart) goto _L2; else goto _L1
_L1:
            if(mStart == null) goto _L4; else goto _L3
_L3:
            return true;
_L4:
            return false;
_L2:
            if(mCurrent == null || mCurrent.mNext == null)
                return false;
            if(true) goto _L3; else goto _L5
_L5:
        }

        public volatile Object next()
        {
            return next();
        }

        public java.util.Map.Entry next()
        {
            if(mBeforeStart)
            {
                mBeforeStart = false;
                mCurrent = mStart;
            } else
            {
                SafeIterableMap.Entry entry;
                if(mCurrent != null)
                    entry = mCurrent.mNext;
                else
                    entry = null;
                mCurrent = entry;
            }
            return mCurrent;
        }

        public void supportRemove(SafeIterableMap.Entry entry)
        {
            if(entry == mCurrent)
            {
                mCurrent = mCurrent.mPrevious;
                boolean flag;
                if(mCurrent == null)
                    flag = true;
                else
                    flag = false;
                mBeforeStart = flag;
            }
        }

        private boolean mBeforeStart;
        private SafeIterableMap.Entry mCurrent;
        final SafeIterableMap this$0;

        private IteratorWithAdditions()
        {
            this$0 = SafeIterableMap.this;
            super();
            mBeforeStart = true;
        }

    }

    private static abstract class ListIterator
        implements Iterator, SupportRemove
    {

        private SafeIterableMap.Entry nextNode()
        {
            if(mNext == mExpectedEnd || mExpectedEnd == null)
                return null;
            else
                return forward(mNext);
        }

        abstract SafeIterableMap.Entry backward(SafeIterableMap.Entry entry);

        abstract SafeIterableMap.Entry forward(SafeIterableMap.Entry entry);

        public boolean hasNext()
        {
            return mNext != null;
        }

        public volatile Object next()
        {
            return next();
        }

        public java.util.Map.Entry next()
        {
            SafeIterableMap.Entry entry = mNext;
            mNext = nextNode();
            return entry;
        }

        public void supportRemove(SafeIterableMap.Entry entry)
        {
            if(mExpectedEnd == entry && entry == mNext)
            {
                mNext = null;
                mExpectedEnd = null;
            }
            if(mExpectedEnd == entry)
                mExpectedEnd = backward(mExpectedEnd);
            if(mNext == entry)
                mNext = nextNode();
        }

        SafeIterableMap.Entry mExpectedEnd;
        SafeIterableMap.Entry mNext;

        ListIterator(SafeIterableMap.Entry entry, SafeIterableMap.Entry entry1)
        {
            mExpectedEnd = entry1;
            mNext = entry;
        }
    }

    static interface SupportRemove
    {

        public abstract void supportRemove(SafeIterableMap.Entry entry);
    }


    public SafeIterableMap()
    {
        mIterators = new WeakHashMap();
        mSize = 0;
    }

    public Iterator descendingIterator()
    {
        DescendingIterator descendingiterator = new DescendingIterator(mEnd, mStart);
        mIterators.put(descendingiterator, Boolean.valueOf(false));
        return descendingiterator;
    }

    public java.util.Map.Entry eldest()
    {
        return mStart;
    }

    public boolean equals(Object obj)
    {
        boolean flag2;
        boolean flag3;
        flag2 = true;
        flag3 = false;
        if(obj != this) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag3;
        if(!(obj instanceof SafeIterableMap)) goto _L4; else goto _L3
_L3:
        Object obj1;
        obj1 = (SafeIterableMap)obj;
        flag = flag3;
        if(size() != ((SafeIterableMap) (obj1)).size()) goto _L4; else goto _L5
_L5:
        obj = iterator();
        obj1 = ((SafeIterableMap) (obj1)).iterator();
_L8:
        java.util.Map.Entry entry;
        Object obj2;
        if(!((Iterator) (obj)).hasNext() || !((Iterator) (obj1)).hasNext())
            break MISSING_BLOCK_LABEL_128;
        entry = (java.util.Map.Entry)((Iterator) (obj)).next();
        obj2 = ((Iterator) (obj1)).next();
        if(entry != null)
            break; /* Loop/switch isn't completed */
        flag = flag3;
        if(obj2 != null) goto _L4; else goto _L6
_L6:
        if(entry == null || entry.equals(obj2)) goto _L8; else goto _L7
_L7:
        return false;
        boolean flag1;
        if(!((Iterator) (obj)).hasNext() && !((Iterator) (obj1)).hasNext())
            flag1 = flag2;
        else
            flag1 = false;
        return flag1;
    }

    protected Entry get(Object obj)
    {
        Entry entry = mStart;
        do
        {
            if(entry == null || entry.mKey.equals(obj))
                return entry;
            entry = entry.mNext;
        } while(true);
    }

    public Iterator iterator()
    {
        AscendingIterator ascendingiterator = new AscendingIterator(mStart, mEnd);
        mIterators.put(ascendingiterator, Boolean.valueOf(false));
        return ascendingiterator;
    }

    public IteratorWithAdditions iteratorWithAdditions()
    {
        IteratorWithAdditions iteratorwithadditions = new IteratorWithAdditions();
        mIterators.put(iteratorwithadditions, Boolean.valueOf(false));
        return iteratorwithadditions;
    }

    public java.util.Map.Entry newest()
    {
        return mEnd;
    }

    protected Entry put(Object obj, Object obj1)
    {
        obj = new Entry(obj, obj1);
        mSize = mSize + 1;
        if(mEnd == null)
        {
            mStart = ((Entry) (obj));
            mEnd = mStart;
            return ((Entry) (obj));
        } else
        {
            mEnd.mNext = ((Entry) (obj));
            obj.mPrevious = mEnd;
            mEnd = ((Entry) (obj));
            return ((Entry) (obj));
        }
    }

    public Object putIfAbsent(Object obj, Object obj1)
    {
        Entry entry = get(obj);
        if(entry != null)
        {
            return entry.mValue;
        } else
        {
            put(obj, obj1);
            return null;
        }
    }

    public Object remove(Object obj)
    {
        obj = get(obj);
        if(obj == null)
            return null;
        mSize = mSize - 1;
        if(!mIterators.isEmpty())
        {
            for(Iterator iterator1 = mIterators.keySet().iterator(); iterator1.hasNext(); ((SupportRemove)iterator1.next()).supportRemove(((Entry) (obj))));
        }
        if(((Entry) (obj)).mPrevious != null)
            ((Entry) (obj)).mPrevious.mNext = ((Entry) (obj)).mNext;
        else
            mStart = ((Entry) (obj)).mNext;
        if(((Entry) (obj)).mNext != null)
            ((Entry) (obj)).mNext.mPrevious = ((Entry) (obj)).mPrevious;
        else
            mEnd = ((Entry) (obj)).mPrevious;
        obj.mNext = null;
        obj.mPrevious = null;
        return ((Entry) (obj)).mValue;
    }

    public int size()
    {
        return mSize;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        Iterator iterator1 = iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            stringbuilder.append(((java.util.Map.Entry)iterator1.next()).toString());
            if(iterator1.hasNext())
                stringbuilder.append(", ");
        } while(true);
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    private Entry mEnd;
    private WeakHashMap mIterators;
    private int mSize;
    private Entry mStart;

}

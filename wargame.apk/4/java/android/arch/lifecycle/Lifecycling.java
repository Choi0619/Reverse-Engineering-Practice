// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.arch.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package android.arch.lifecycle:
//            ReflectiveGenericLifecycleObserver, GenericLifecycleObserver

class Lifecycling
{

    Lifecycling()
    {
    }

    static String getAdapterName(String s)
    {
        return (new StringBuilder()).append(s.replace(".", "_")).append("_LifecycleAdapter").toString();
    }

    static GenericLifecycleObserver getCallback(Object obj)
    {
        if(obj instanceof GenericLifecycleObserver)
            return (GenericLifecycleObserver)obj;
        Constructor constructor;
        Constructor constructor1;
        Class class1;
        try
        {
            class1 = obj.getClass();
            constructor = (Constructor)sCallbackCache.get(class1);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException(((Throwable) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException(((Throwable) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException(((Throwable) (obj)));
        }
        if(constructor == null)
            break MISSING_BLOCK_LABEL_50;
        return (GenericLifecycleObserver)constructor.newInstance(new Object[] {
            obj
        });
        constructor1 = getGeneratedAdapterConstructor(class1);
        if(constructor1 == null) goto _L2; else goto _L1
_L1:
        constructor = constructor1;
        if(constructor1.isAccessible())
            break MISSING_BLOCK_LABEL_75;
        constructor1.setAccessible(true);
        constructor = constructor1;
_L4:
        sCallbackCache.put(class1, constructor);
        return (GenericLifecycleObserver)constructor.newInstance(new Object[] {
            obj
        });
_L2:
        constructor = sREFLECTIVE;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static Constructor getGeneratedAdapterConstructor(Class class1)
    {
        Object obj;
        String s;
        obj = class1.getPackage();
        if(obj != null)
            obj = ((Package) (obj)).getName();
        else
            obj = "";
        s = class1.getCanonicalName();
        if(s != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        if(!((String) (obj)).isEmpty())
            s = s.substring(((String) (obj)).length() + 1);
        s = getAdapterName(s);
        if(!((String) (obj)).isEmpty())
            break MISSING_BLOCK_LABEL_84;
        obj = s;
_L3:
        obj = Class.forName(((String) (obj))).getDeclaredConstructor(new Class[] {
            class1
        });
        return ((Constructor) (obj));
        obj = (new StringBuilder()).append(((String) (obj))).append(".").append(s).toString();
          goto _L3
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        class1 = class1.getSuperclass();
        if(class1 != null)
            return getGeneratedAdapterConstructor(class1);
        continue; /* Loop/switch isn't completed */
        class1;
        throw new RuntimeException(class1);
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static Map sCallbackCache = new HashMap();
    private static Constructor sREFLECTIVE;

    static 
    {
        try
        {
            sREFLECTIVE = android/arch/lifecycle/ReflectiveGenericLifecycleObserver.getDeclaredConstructor(new Class[] {
                java/lang/Object
            });
        }
        catch(NoSuchMethodException nosuchmethodexception) { }
    }
}

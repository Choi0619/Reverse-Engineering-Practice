// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.arch.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package android.arch.lifecycle:
//            GenericLifecycleObserver, OnLifecycleEvent, LifecycleOwner

class ReflectiveGenericLifecycleObserver
    implements GenericLifecycleObserver
{
    static class CallbackInfo
    {

        final Map mEventToHandlers = new HashMap();
        final Map mHandlerToEvent;

        CallbackInfo(Map map)
        {
            mHandlerToEvent = map;
            java.util.Map.Entry entry;
            for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); map.add(entry.getKey()))
            {
                entry = (java.util.Map.Entry)iterator.next();
                Lifecycle.Event event = (Lifecycle.Event)entry.getValue();
                List list = (List)mEventToHandlers.get(event);
                map = list;
                if(list == null)
                {
                    map = new ArrayList();
                    mEventToHandlers.put(event, map);
                }
            }

        }
    }

    static class MethodReference
    {

        public boolean equals(Object obj)
        {
            if(this != obj)
            {
                if(obj == null || getClass() != obj.getClass())
                    return false;
                obj = (MethodReference)obj;
                if(mCallType != ((MethodReference) (obj)).mCallType || !mMethod.getName().equals(((MethodReference) (obj)).mMethod.getName()))
                    return false;
            }
            return true;
        }

        public int hashCode()
        {
            return mCallType * 31 + mMethod.getName().hashCode();
        }

        final int mCallType;
        final Method mMethod;

        MethodReference(int i, Method method)
        {
            mCallType = i;
            mMethod = method;
            mMethod.setAccessible(true);
        }
    }


    ReflectiveGenericLifecycleObserver(Object obj)
    {
        mWrapped = obj;
        mInfo = getInfo(mWrapped.getClass());
    }

    private static CallbackInfo createInfo(Class class1)
    {
        Object obj1 = class1.getSuperclass();
        Object obj = new HashMap();
        if(obj1 != null)
        {
            obj1 = getInfo(((Class) (obj1)));
            if(obj1 != null)
                ((Map) (obj)).putAll(((CallbackInfo) (obj1)).mHandlerToEvent);
        }
        Method amethod[] = class1.getDeclaredMethods();
        Class aclass[] = class1.getInterfaces();
        int j = aclass.length;
        for(int i = 0; i < j; i++)
        {
            java.util.Map.Entry entry;
            for(Iterator iterator = getInfo(aclass[i]).mHandlerToEvent.entrySet().iterator(); iterator.hasNext(); verifyAndPutHandler(((Map) (obj)), (MethodReference)entry.getKey(), (Lifecycle.Event)entry.getValue(), class1))
                entry = (java.util.Map.Entry)iterator.next();

        }

        int k = amethod.length;
        j = 0;
        while(j < k) 
        {
            Method method = amethod[j];
            Object obj2 = (OnLifecycleEvent)method.getAnnotation(android/arch/lifecycle/OnLifecycleEvent);
            if(obj2 != null)
            {
                Class aclass1[] = method.getParameterTypes();
                byte byte0 = 0;
                if(aclass1.length > 0)
                {
                    byte0 = 1;
                    if(!aclass1[0].isAssignableFrom(android/arch/lifecycle/LifecycleOwner))
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                obj2 = ((OnLifecycleEvent) (obj2)).value();
                if(aclass1.length > 1)
                {
                    byte0 = 2;
                    if(!aclass1[1].isAssignableFrom(android/arch/lifecycle/Lifecycle$Event))
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    if(obj2 != Lifecycle.Event.ON_ANY)
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                }
                if(aclass1.length > 2)
                    throw new IllegalArgumentException("cannot have more than 2 params");
                verifyAndPutHandler(((Map) (obj)), new MethodReference(byte0, method), ((Lifecycle.Event) (obj2)), class1);
            }
            j++;
        }
        obj = new CallbackInfo(((Map) (obj)));
        sInfoCache.put(class1, obj);
        return ((CallbackInfo) (obj));
    }

    private static CallbackInfo getInfo(Class class1)
    {
        CallbackInfo callbackinfo = (CallbackInfo)sInfoCache.get(class1);
        if(callbackinfo != null)
            return callbackinfo;
        else
            return createInfo(class1);
    }

    private void invokeCallback(MethodReference methodreference, LifecycleOwner lifecycleowner, Lifecycle.Event event)
    {
        methodreference.mCallType;
        JVM INSTR tableswitch 0 2: default 120
    //                   0 32
    //                   1 64
    //                   2 95;
           goto _L1 _L2 _L3 _L4
_L2:
        methodreference.mMethod.invoke(mWrapped, new Object[0]);
        return;
_L3:
        try
        {
            methodreference.mMethod.invoke(mWrapped, new Object[] {
                lifecycleowner
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch(MethodReference methodreference)
        {
            throw new RuntimeException("Failed to call observer method", methodreference.getCause());
        }
        // Misplaced declaration of an exception variable
        catch(MethodReference methodreference)
        {
            throw new RuntimeException(methodreference);
        }
_L4:
        methodreference.mMethod.invoke(mWrapped, new Object[] {
            lifecycleowner, event
        });
        return;
_L1:
    }

    private void invokeCallbacks(CallbackInfo callbackinfo, LifecycleOwner lifecycleowner, Lifecycle.Event event)
    {
        invokeMethodsForEvent((List)callbackinfo.mEventToHandlers.get(event), lifecycleowner, event);
        invokeMethodsForEvent((List)callbackinfo.mEventToHandlers.get(Lifecycle.Event.ON_ANY), lifecycleowner, event);
    }

    private void invokeMethodsForEvent(List list, LifecycleOwner lifecycleowner, Lifecycle.Event event)
    {
        if(list != null)
        {
            for(int i = list.size() - 1; i >= 0; i--)
                invokeCallback((MethodReference)list.get(i), lifecycleowner, event);

        }
    }

    private static void verifyAndPutHandler(Map map, MethodReference methodreference, Lifecycle.Event event, Class class1)
    {
        Lifecycle.Event event1 = (Lifecycle.Event)map.get(methodreference);
        if(event1 != null && event != event1)
        {
            map = methodreference.mMethod;
            throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(map.getName()).append(" in ").append(class1.getName()).append(" already declared with different @OnLifecycleEvent value: previous").append(" value ").append(event1).append(", new value ").append(event).toString());
        }
        if(event1 == null)
            map.put(methodreference, event);
    }

    public void onStateChanged(LifecycleOwner lifecycleowner, Lifecycle.Event event)
    {
        invokeCallbacks(mInfo, lifecycleowner, event);
    }

    private static final int CALL_TYPE_NO_ARG = 0;
    private static final int CALL_TYPE_PROVIDER = 1;
    private static final int CALL_TYPE_PROVIDER_WITH_EVENT = 2;
    static final Map sInfoCache = new HashMap();
    private final CallbackInfo mInfo;
    private final Object mWrapped;

}

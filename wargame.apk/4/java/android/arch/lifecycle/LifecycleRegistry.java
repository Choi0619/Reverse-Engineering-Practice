// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.arch.lifecycle:
//            Lifecycle, LifecycleOwner, LifecycleObserver, Lifecycling, 
//            GenericLifecycleObserver

public class LifecycleRegistry extends Lifecycle
{
    static class ObserverWithState
    {

        void dispatchEvent(LifecycleOwner lifecycleowner, Lifecycle.Event event)
        {
            Lifecycle.State state = LifecycleRegistry.getStateAfter(event);
            mState = LifecycleRegistry.min(mState, state);
            mLifecycleObserver.onStateChanged(lifecycleowner, event);
            mState = state;
        }

        GenericLifecycleObserver mLifecycleObserver;
        Lifecycle.State mState;

        ObserverWithState(LifecycleObserver lifecycleobserver, Lifecycle.State state)
        {
            mLifecycleObserver = Lifecycling.getCallback(lifecycleobserver);
            mState = state;
        }
    }


    public LifecycleRegistry(LifecycleOwner lifecycleowner)
    {
        mObserverMap = new FastSafeIterableMap();
        mAddingObserverCounter = 0;
        mHandlingEvent = false;
        mNewEventOccurred = false;
        mParentStates = new ArrayList();
        mLifecycleOwner = lifecycleowner;
        mState = Lifecycle.State.INITIALIZED;
    }

    private void backwardPass()
    {
        for(Iterator iterator = mObserverMap.descendingIterator(); iterator.hasNext() && !mNewEventOccurred;)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            ObserverWithState observerwithstate = (ObserverWithState)entry.getValue();
            while(observerwithstate.mState.compareTo(mState) > 0 && !mNewEventOccurred && mObserverMap.contains(entry.getKey())) 
            {
                Lifecycle.Event event = downEvent(observerwithstate.mState);
                pushParentState(getStateAfter(event));
                observerwithstate.dispatchEvent(mLifecycleOwner, event);
                popParentState();
            }
        }

    }

    private Lifecycle.State calculateTargetState(LifecycleObserver lifecycleobserver)
    {
        lifecycleobserver = mObserverMap.ceil(lifecycleobserver);
        Lifecycle.State state;
        if(lifecycleobserver != null)
            lifecycleobserver = ((ObserverWithState)lifecycleobserver.getValue()).mState;
        else
            lifecycleobserver = null;
        if(!mParentStates.isEmpty())
            state = (Lifecycle.State)mParentStates.get(mParentStates.size() - 1);
        else
            state = null;
        return min(min(mState, lifecycleobserver), state);
    }

    private static Lifecycle.Event downEvent(Lifecycle.State state)
    {
        static class _cls1
        {

            static final int $SwitchMap$android$arch$lifecycle$Lifecycle$Event[];
            static final int $SwitchMap$android$arch$lifecycle$Lifecycle$State[];

            static 
            {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State = new int[Lifecycle.State.values().length];
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.INITIALIZED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.CREATED.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.STARTED.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.RESUMED.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.DESTROYED.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event = new int[Lifecycle.Event.values().length];
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_STOP.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_START.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_ANY.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.android.arch.lifecycle.Lifecycle.State[state.ordinal()])
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unexpected state value ").append(state).toString());

        case 1: // '\001'
            throw new IllegalArgumentException();

        case 2: // '\002'
            return Lifecycle.Event.ON_DESTROY;

        case 3: // '\003'
            return Lifecycle.Event.ON_STOP;

        case 4: // '\004'
            return Lifecycle.Event.ON_PAUSE;

        case 5: // '\005'
            throw new IllegalArgumentException();
        }
    }

    private void forwardPass()
    {
        for(android.arch.core.internal.SafeIterableMap.IteratorWithAdditions iteratorwithadditions = mObserverMap.iteratorWithAdditions(); iteratorwithadditions.hasNext() && !mNewEventOccurred;)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iteratorwithadditions.next();
            ObserverWithState observerwithstate = (ObserverWithState)entry.getValue();
            while(observerwithstate.mState.compareTo(mState) < 0 && !mNewEventOccurred && mObserverMap.contains(entry.getKey())) 
            {
                pushParentState(observerwithstate.mState);
                observerwithstate.dispatchEvent(mLifecycleOwner, upEvent(observerwithstate.mState));
                popParentState();
            }
        }

    }

    static Lifecycle.State getStateAfter(Lifecycle.Event event)
    {
        switch(_cls1..SwitchMap.android.arch.lifecycle.Lifecycle.Event[event.ordinal()])
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unexpected event value ").append(event).toString());

        case 1: // '\001'
        case 2: // '\002'
            return Lifecycle.State.CREATED;

        case 3: // '\003'
        case 4: // '\004'
            return Lifecycle.State.STARTED;

        case 5: // '\005'
            return Lifecycle.State.RESUMED;

        case 6: // '\006'
            return Lifecycle.State.DESTROYED;
        }
    }

    private boolean isSynced()
    {
        if(mObserverMap.size() == 0)
            return true;
        Lifecycle.State state = ((ObserverWithState)mObserverMap.eldest().getValue()).mState;
        Lifecycle.State state1 = ((ObserverWithState)mObserverMap.newest().getValue()).mState;
        boolean flag;
        if(state == state1 && mState == state1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    static Lifecycle.State min(Lifecycle.State state, Lifecycle.State state1)
    {
        if(state1 != null && state1.compareTo(state) < 0)
            return state1;
        else
            return state;
    }

    private void popParentState()
    {
        mParentStates.remove(mParentStates.size() - 1);
    }

    private void pushParentState(Lifecycle.State state)
    {
        mParentStates.add(state);
    }

    private void sync()
    {
        do
        {
            if(isSynced())
                break;
            mNewEventOccurred = false;
            if(mState.compareTo(((ObserverWithState)mObserverMap.eldest().getValue()).mState) < 0)
                backwardPass();
            java.util.Map.Entry entry = mObserverMap.newest();
            if(!mNewEventOccurred && entry != null && mState.compareTo(((ObserverWithState)entry.getValue()).mState) > 0)
                forwardPass();
        } while(true);
        mNewEventOccurred = false;
    }

    private static Lifecycle.Event upEvent(Lifecycle.State state)
    {
        switch(_cls1..SwitchMap.android.arch.lifecycle.Lifecycle.State[state.ordinal()])
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unexpected state value ").append(state).toString());

        case 1: // '\001'
        case 5: // '\005'
            return Lifecycle.Event.ON_CREATE;

        case 2: // '\002'
            return Lifecycle.Event.ON_START;

        case 3: // '\003'
            return Lifecycle.Event.ON_RESUME;

        case 4: // '\004'
            throw new IllegalArgumentException();
        }
    }

    public void addObserver(LifecycleObserver lifecycleobserver)
    {
        Lifecycle.State state;
        ObserverWithState observerwithstate;
        if(mState == Lifecycle.State.DESTROYED)
            state = Lifecycle.State.DESTROYED;
        else
            state = Lifecycle.State.INITIALIZED;
        observerwithstate = new ObserverWithState(lifecycleobserver, state);
        if((ObserverWithState)mObserverMap.putIfAbsent(lifecycleobserver, observerwithstate) != null)
            return;
        boolean flag;
        if(mAddingObserverCounter != 0 || mHandlingEvent)
            flag = true;
        else
            flag = false;
        state = calculateTargetState(lifecycleobserver);
        mAddingObserverCounter = mAddingObserverCounter + 1;
        for(; observerwithstate.mState.compareTo(state) < 0 && mObserverMap.contains(lifecycleobserver); state = calculateTargetState(lifecycleobserver))
        {
            pushParentState(observerwithstate.mState);
            observerwithstate.dispatchEvent(mLifecycleOwner, upEvent(observerwithstate.mState));
            popParentState();
        }

        if(!flag)
            sync();
        mAddingObserverCounter = mAddingObserverCounter - 1;
    }

    public Lifecycle.State getCurrentState()
    {
        return mState;
    }

    public int getObserverCount()
    {
        return mObserverMap.size();
    }

    public void handleLifecycleEvent(Lifecycle.Event event)
    {
        mState = getStateAfter(event);
        if(mHandlingEvent || mAddingObserverCounter != 0)
        {
            mNewEventOccurred = true;
            return;
        } else
        {
            mHandlingEvent = true;
            sync();
            mHandlingEvent = false;
            return;
        }
    }

    public void markState(Lifecycle.State state)
    {
        mState = state;
    }

    public void removeObserver(LifecycleObserver lifecycleobserver)
    {
        mObserverMap.remove(lifecycleobserver);
    }

    private int mAddingObserverCounter;
    private boolean mHandlingEvent;
    private final LifecycleOwner mLifecycleOwner;
    private boolean mNewEventOccurred;
    private FastSafeIterableMap mObserverMap;
    private ArrayList mParentStates;
    private Lifecycle.State mState;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.animation.*;
import android.content.Context;
import android.content.res.*;
import android.os.*;
import android.support.v4.util.*;
import android.support.v4.view.ViewCompat;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentManager, Fragment, BackStackRecord, FragmentTransition, 
//            FragmentHostCallback, FragmentManagerNonConfig, LoaderManagerImpl, SuperNotCalledException, 
//            FragmentContainer, FragmentManagerState, FragmentState, BackStackState, 
//            FragmentTransaction

final class FragmentManagerImpl extends FragmentManager
    implements android.view.LayoutInflater.Factory2
{
    private static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper
    {

        public void onAnimationEnd(Animation animation)
        {
            if(ViewCompat.isAttachedToWindow(mView) || android.os.Build.VERSION.SDK_INT >= 24)
                mView.post(new Runnable() {

                    public void run()
                    {
                        mView.setLayerType(0, null);
                    }

                    final AnimateOnHWLayerIfNeededListener this$0;

            
            {
                this$0 = AnimateOnHWLayerIfNeededListener.this;
                super();
            }
                }
);
            else
                mView.setLayerType(0, null);
            super.onAnimationEnd(animation);
        }

        View mView;

        AnimateOnHWLayerIfNeededListener(View view, android.view.animation.Animation.AnimationListener animationlistener)
        {
            super(animationlistener);
            mView = view;
        }
    }

    private static class AnimationListenerWrapper
        implements android.view.animation.Animation.AnimationListener
    {

        public void onAnimationEnd(Animation animation)
        {
            if(mWrapped != null)
                mWrapped.onAnimationEnd(animation);
        }

        public void onAnimationRepeat(Animation animation)
        {
            if(mWrapped != null)
                mWrapped.onAnimationRepeat(animation);
        }

        public void onAnimationStart(Animation animation)
        {
            if(mWrapped != null)
                mWrapped.onAnimationStart(animation);
        }

        private final android.view.animation.Animation.AnimationListener mWrapped;

        private AnimationListenerWrapper(android.view.animation.Animation.AnimationListener animationlistener)
        {
            mWrapped = animationlistener;
        }

    }

    private static class AnimationOrAnimator
    {

        public final Animation animation;
        public final Animator animator;

        private AnimationOrAnimator(Animator animator1)
        {
            animation = null;
            animator = animator1;
            if(animator1 == null)
                throw new IllegalStateException("Animator cannot be null");
            else
                return;
        }


        private AnimationOrAnimator(Animation animation1)
        {
            animation = animation1;
            animator = null;
            if(animation1 == null)
                throw new IllegalStateException("Animation cannot be null");
            else
                return;
        }

    }

    private static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter
    {

        public void onAnimationEnd(Animator animator)
        {
            mView.setLayerType(0, null);
            animator.removeListener(this);
        }

        public void onAnimationStart(Animator animator)
        {
            mView.setLayerType(2, null);
        }

        View mView;

        AnimatorOnHWLayerIfNeededListener(View view)
        {
            mView = view;
        }
    }

    static class FragmentTag
    {

        public static final int Fragment[] = {
            0x1010003, 0x10100d0, 0x10100d1
        };
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;


        FragmentTag()
        {
        }
    }

    static interface OpGenerator
    {

        public abstract boolean generateOps(ArrayList arraylist, ArrayList arraylist1);
    }

    private class PopBackStackState
        implements OpGenerator
    {

        public boolean generateOps(ArrayList arraylist, ArrayList arraylist1)
        {
            if(mPrimaryNav != null && mId < 0 && mName == null)
            {
                FragmentManager fragmentmanager = mPrimaryNav.peekChildFragmentManager();
                if(fragmentmanager != null && fragmentmanager.popBackStackImmediate())
                    return false;
            }
            return popBackStackState(arraylist, arraylist1, mName, mId, mFlags);
        }

        final int mFlags;
        final int mId;
        final String mName;
        final FragmentManagerImpl this$0;

        PopBackStackState(String s, int i, int j)
        {
            this$0 = FragmentManagerImpl.this;
            super();
            mName = s;
            mId = i;
            mFlags = j;
        }
    }

    static class StartEnterTransitionListener
        implements Fragment.OnStartEnterTransitionListener
    {

        public void cancelTransaction()
        {
            mRecord.mManager.completeExecute(mRecord, mIsBack, false, false);
        }

        public void completeTransaction()
        {
            boolean flag1 = false;
            boolean flag;
            int j;
            FragmentManagerImpl fragmentmanagerimpl;
            if(mNumPostponed > 0)
                flag = true;
            else
                flag = false;
            fragmentmanagerimpl = mRecord.mManager;
            j = fragmentmanagerimpl.mAdded.size();
            for(int i = 0; i < j; i++)
            {
                Fragment fragment = (Fragment)fragmentmanagerimpl.mAdded.get(i);
                fragment.setOnStartEnterTransitionListener(null);
                if(flag && fragment.isPostponed())
                    fragment.startPostponedEnterTransition();
            }

            fragmentmanagerimpl = mRecord.mManager;
            BackStackRecord backstackrecord = mRecord;
            boolean flag2 = mIsBack;
            if(!flag)
                flag1 = true;
            fragmentmanagerimpl.completeExecute(backstackrecord, flag2, flag1, true);
        }

        public boolean isReady()
        {
            return mNumPostponed == 0;
        }

        public void onStartEnterTransition()
        {
            mNumPostponed = mNumPostponed - 1;
            if(mNumPostponed != 0)
            {
                return;
            } else
            {
                mRecord.mManager.scheduleCommit();
                return;
            }
        }

        public void startListening()
        {
            mNumPostponed = mNumPostponed + 1;
        }

        private final boolean mIsBack;
        private int mNumPostponed;
        private final BackStackRecord mRecord;



        StartEnterTransitionListener(BackStackRecord backstackrecord, boolean flag)
        {
            mIsBack = flag;
            mRecord = backstackrecord;
        }
    }


    FragmentManagerImpl()
    {
        mNextFragmentIndex = 0;
        mCurState = 0;
        mStateBundle = null;
        mStateArray = null;
        mExecCommit = new Runnable() {

            public void run()
            {
                execPendingActions();
            }

            final FragmentManagerImpl this$0;

            
            {
                this$0 = FragmentManagerImpl.this;
                super();
            }
        }
;
    }

    private void addAddedFragments(ArraySet arrayset)
    {
        if(mCurState >= 1)
        {
            int j = Math.min(mCurState, 4);
            int k = mAdded.size();
            int i = 0;
            while(i < k) 
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if(fragment.mState < j)
                {
                    moveToState(fragment, j, fragment.getNextAnim(), fragment.getNextTransition(), false);
                    if(fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded)
                        arrayset.add(fragment);
                }
                i++;
            }
        }
    }

    private void animateRemoveFragment(final Fragment fragment, AnimationOrAnimator animationoranimator, int i)
    {
        final View viewToAnimate = fragment.mView;
        fragment.setStateAfterAnimating(i);
        if(animationoranimator.animation != null)
        {
            Animation animation = animationoranimator.animation;
            fragment.setAnimatingAway(fragment.mView);
            animation.setAnimationListener(new AnimationListenerWrapper(fragment) {

                public void onAnimationEnd(Animation animation1)
                {
                    super.onAnimationEnd(animation1);
                    if(fragment.getAnimatingAway() != null)
                    {
                        fragment.setAnimatingAway(null);
                        moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                    }
                }

                final FragmentManagerImpl this$0;
                final Fragment val$fragment;

            
            {
                this$0 = FragmentManagerImpl.this;
                fragment = fragment1;
                super(final_animationlistener);
            }
            }
);
            setHWLayerAnimListenerIfAlpha(viewToAnimate, animationoranimator);
            fragment.mView.startAnimation(animation);
            return;
        }
        Animator animator = animationoranimator.animator;
        fragment.setAnimator(animationoranimator.animator);
        final ViewGroup container = fragment.mContainer;
        if(container != null)
            container.startViewTransition(viewToAnimate);
        animator.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator1)
            {
                if(container != null)
                    container.endViewTransition(viewToAnimate);
                if(fragment.getAnimator() != null)
                {
                    fragment.setAnimator(null);
                    moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                }
            }

            final FragmentManagerImpl this$0;
            final ViewGroup val$container;
            final Fragment val$fragment;
            final View val$viewToAnimate;

            
            {
                this$0 = FragmentManagerImpl.this;
                container = viewgroup;
                viewToAnimate = view;
                fragment = fragment1;
                super();
            }
        }
);
        animator.setTarget(fragment.mView);
        setHWLayerAnimListenerIfAlpha(fragment.mView, animationoranimator);
        animator.start();
    }

    private void burpActive()
    {
        if(mActive != null)
        {
            for(int i = mActive.size() - 1; i >= 0; i--)
                if(mActive.valueAt(i) == null)
                    mActive.delete(mActive.keyAt(i));

        }
    }

    private void checkStateLoss()
    {
        if(mStateSaved)
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        if(mNoTransactionsBecause != null)
            throw new IllegalStateException((new StringBuilder()).append("Can not perform this action inside of ").append(mNoTransactionsBecause).toString());
        else
            return;
    }

    private void cleanupExec()
    {
        mExecutingActions = false;
        mTmpIsPop.clear();
        mTmpRecords.clear();
    }

    private void completeExecute(BackStackRecord backstackrecord, boolean flag, boolean flag1, boolean flag2)
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        if(flag)
            backstackrecord.executePopOps(flag2);
        else
            backstackrecord.executeOps();
        arraylist = new ArrayList(1);
        arraylist1 = new ArrayList(1);
        arraylist.add(backstackrecord);
        arraylist1.add(Boolean.valueOf(flag));
        if(flag1)
            FragmentTransition.startTransitions(this, arraylist, arraylist1, 0, 1, true);
        if(flag2)
            moveToState(mCurState, true);
        if(mActive != null)
        {
            int j = mActive.size();
            int i = 0;
            while(i < j) 
            {
                Fragment fragment = (Fragment)mActive.valueAt(i);
                if(fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && backstackrecord.interactsWith(fragment.mContainerId))
                {
                    if(fragment.mPostponedAlpha > 0.0F)
                        fragment.mView.setAlpha(fragment.mPostponedAlpha);
                    if(flag2)
                    {
                        fragment.mPostponedAlpha = 0.0F;
                    } else
                    {
                        fragment.mPostponedAlpha = -1F;
                        fragment.mIsNewlyAdded = false;
                    }
                }
                i++;
            }
        }
    }

    private void dispatchStateChange(int i)
    {
        mExecutingActions = true;
        moveToState(i, false);
        mExecutingActions = false;
        execPendingActions();
        return;
        Exception exception;
        exception;
        mExecutingActions = false;
        throw exception;
    }

    private void endAnimatingAwayFragments()
    {
        int i;
        int j;
        if(mActive == null)
            i = 0;
        else
            i = mActive.size();
        j = 0;
        while(j < i) 
        {
            Fragment fragment = (Fragment)mActive.valueAt(j);
            if(fragment != null)
                if(fragment.getAnimatingAway() != null)
                {
                    int k = fragment.getStateAfterAnimating();
                    View view = fragment.getAnimatingAway();
                    fragment.setAnimatingAway(null);
                    Animation animation = view.getAnimation();
                    if(animation != null)
                    {
                        animation.cancel();
                        view.clearAnimation();
                    }
                    moveToState(fragment, k, 0, 0, false);
                } else
                if(fragment.getAnimator() != null)
                    fragment.getAnimator().end();
            j++;
        }
    }

    private void ensureExecReady(boolean flag)
    {
        if(mExecutingActions)
            throw new IllegalStateException("FragmentManager is already executing transactions");
        if(Looper.myLooper() != mHost.getHandler().getLooper())
            throw new IllegalStateException("Must be called from main thread of fragment host");
        if(!flag)
            checkStateLoss();
        if(mTmpRecords == null)
        {
            mTmpRecords = new ArrayList();
            mTmpIsPop = new ArrayList();
        }
        mExecutingActions = true;
        executePostponedTransaction(null, null);
        mExecutingActions = false;
        return;
        Exception exception;
        exception;
        mExecutingActions = false;
        throw exception;
    }

    private static void executeOps(ArrayList arraylist, ArrayList arraylist1, int i, int j)
    {
        while(i < j) 
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(i);
            if(((Boolean)arraylist1.get(i)).booleanValue())
            {
                backstackrecord.bumpBackStackNesting(-1);
                boolean flag;
                if(i == j - 1)
                    flag = true;
                else
                    flag = false;
                backstackrecord.executePopOps(flag);
            } else
            {
                backstackrecord.bumpBackStackNesting(1);
                backstackrecord.executeOps();
            }
            i++;
        }
    }

    private void executeOpsTogether(ArrayList arraylist, ArrayList arraylist1, int i, int j)
    {
        boolean flag1 = ((BackStackRecord)arraylist.get(i)).mReorderingAllowed;
        boolean flag = false;
        int k;
        Fragment fragment;
        if(mTmpAddedFragments == null)
            mTmpAddedFragments = new ArrayList();
        else
            mTmpAddedFragments.clear();
        mTmpAddedFragments.addAll(mAdded);
        fragment = getPrimaryNavigationFragment();
        k = i;
        while(k < j) 
        {
            BackStackRecord backstackrecord1 = (BackStackRecord)arraylist.get(k);
            if(!((Boolean)arraylist1.get(k)).booleanValue())
                fragment = backstackrecord1.expandOps(mTmpAddedFragments, fragment);
            else
                fragment = backstackrecord1.trackAddedFragmentsInPop(mTmpAddedFragments, fragment);
            if(flag || backstackrecord1.mAddToBackStack)
                flag = true;
            else
                flag = false;
            k++;
        }
        mTmpAddedFragments.clear();
        if(!flag1)
            FragmentTransition.startTransitions(this, arraylist, arraylist1, i, j, false);
        executeOps(arraylist, arraylist1, i, j);
        k = j;
        if(flag1)
        {
            ArraySet arrayset = new ArraySet();
            addAddedFragments(arrayset);
            k = postponePostponableTransactions(arraylist, arraylist1, i, j, arrayset);
            makeRemovedFragmentsInvisible(arrayset);
        }
        if(k != i && flag1)
        {
            FragmentTransition.startTransitions(this, arraylist, arraylist1, i, k, true);
            moveToState(mCurState, true);
        }
        for(; i < j; i++)
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(i);
            if(((Boolean)arraylist1.get(i)).booleanValue() && backstackrecord.mIndex >= 0)
            {
                freeBackStackIndex(backstackrecord.mIndex);
                backstackrecord.mIndex = -1;
            }
            backstackrecord.runOnCommitRunnables();
        }

        if(flag)
            reportBackStackChanged();
    }

    private void executePostponedTransaction(ArrayList arraylist, ArrayList arraylist1)
    {
        int i;
        int j;
        int k;
        int l;
        StartEnterTransitionListener startentertransitionlistener;
        if(mPostponedTransactions == null)
            i = 0;
        else
            i = mPostponedTransactions.size();
        k = 0;
        j = i;
        i = k;
        if(i >= j)
            break MISSING_BLOCK_LABEL_236;
        startentertransitionlistener = (StartEnterTransitionListener)mPostponedTransactions.get(i);
        if(arraylist == null || startentertransitionlistener.mIsBack)
            break; /* Loop/switch isn't completed */
        k = arraylist.indexOf(startentertransitionlistener.mRecord);
        if(k == -1 || !((Boolean)arraylist1.get(k)).booleanValue())
            break; /* Loop/switch isn't completed */
        startentertransitionlistener.cancelTransaction();
        l = j;
        k = i;
_L4:
        i = k + 1;
        j = l;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_18;
_L1:
        if(!startentertransitionlistener.isReady())
        {
            k = i;
            l = j;
            if(arraylist == null)
                continue; /* Loop/switch isn't completed */
            k = i;
            l = j;
            if(!startentertransitionlistener.mRecord.interactsWith(arraylist, 0, arraylist.size()))
                continue; /* Loop/switch isn't completed */
        }
label0:
        {
            mPostponedTransactions.remove(i);
            k = i - 1;
            l = j - 1;
            if(arraylist == null || startentertransitionlistener.mIsBack)
                break label0;
            i = arraylist.indexOf(startentertransitionlistener.mRecord);
            if(i == -1 || !((Boolean)arraylist1.get(i)).booleanValue())
                break label0;
            startentertransitionlistener.cancelTransaction();
        }
        continue; /* Loop/switch isn't completed */
        startentertransitionlistener.completeTransaction();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private Fragment findFragmentUnder(Fragment fragment)
    {
        Object obj;
        ViewGroup viewgroup;
        viewgroup = fragment.mContainer;
        obj = fragment.mView;
        if(viewgroup != null && obj != null) goto _L2; else goto _L1
_L1:
        fragment = null;
_L6:
        return fragment;
_L2:
        int i = mAdded.indexOf(fragment) - 1;
_L7:
        if(i < 0) goto _L4; else goto _L3
_L3:
        obj = (Fragment)mAdded.get(i);
        if(((Fragment) (obj)).mContainer != viewgroup)
            continue; /* Loop/switch isn't completed */
        fragment = ((Fragment) (obj));
        if(((Fragment) (obj)).mView != null) goto _L6; else goto _L5
_L5:
        i--;
          goto _L7
_L4:
        return null;
    }

    private void forcePostponedTransactions()
    {
        if(mPostponedTransactions != null)
            for(; !mPostponedTransactions.isEmpty(); ((StartEnterTransitionListener)mPostponedTransactions.remove(0)).completeTransaction());
    }

    private boolean generateOpsForPendingActions(ArrayList arraylist, ArrayList arraylist1)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if(mPendingActions != null && mPendingActions.size() != 0)
            break MISSING_BLOCK_LABEL_26;
        this;
        JVM INSTR monitorexit ;
        return false;
        int j = mPendingActions.size();
        int i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        flag |= ((OpGenerator)mPendingActions.get(i)).generateOps(arraylist, arraylist1);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mPendingActions.clear();
        mHost.getHandler().removeCallbacks(mExecCommit);
        this;
        JVM INSTR monitorexit ;
        return flag;
        arraylist;
        this;
        JVM INSTR monitorexit ;
        throw arraylist;
    }

    private static android.view.animation.Animation.AnimationListener getAnimationListener(Animation animation)
    {
        try
        {
            if(sAnimationListenerField == null)
            {
                sAnimationListenerField = android/view/animation/Animation.getDeclaredField("mListener");
                sAnimationListenerField.setAccessible(true);
            }
            animation = (android.view.animation.Animation.AnimationListener)sAnimationListenerField.get(animation);
        }
        // Misplaced declaration of an exception variable
        catch(Animation animation)
        {
            Log.e("FragmentManager", "No field with the name mListener is found in Animation class", animation);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Animation animation)
        {
            Log.e("FragmentManager", "Cannot access Animation's mListener field", animation);
            return null;
        }
        return animation;
    }

    static AnimationOrAnimator makeFadeAnimation(Context context, float f, float f1)
    {
        context = new AlphaAnimation(f, f1);
        context.setInterpolator(DECELERATE_CUBIC);
        context.setDuration(220L);
        return new AnimationOrAnimator(context);
    }

    static AnimationOrAnimator makeOpenCloseAnimation(Context context, float f, float f1, float f2, float f3)
    {
        context = new AnimationSet(false);
        Object obj = new ScaleAnimation(f, f1, f, f1, 1, 0.5F, 1, 0.5F);
        ((ScaleAnimation) (obj)).setInterpolator(DECELERATE_QUINT);
        ((ScaleAnimation) (obj)).setDuration(220L);
        context.addAnimation(((Animation) (obj)));
        obj = new AlphaAnimation(f2, f3);
        ((AlphaAnimation) (obj)).setInterpolator(DECELERATE_CUBIC);
        ((AlphaAnimation) (obj)).setDuration(220L);
        context.addAnimation(((Animation) (obj)));
        return new AnimationOrAnimator(context);
    }

    private void makeRemovedFragmentsInvisible(ArraySet arrayset)
    {
        int j = arrayset.size();
        for(int i = 0; i < j; i++)
        {
            Fragment fragment = (Fragment)arrayset.valueAt(i);
            if(!fragment.mAdded)
            {
                View view = fragment.getView();
                fragment.mPostponedAlpha = view.getAlpha();
                view.setAlpha(0.0F);
            }
        }

    }

    static boolean modifiesAlpha(Animator animator)
    {
        if(animator == null)
            return false;
        if(animator instanceof ValueAnimator)
        {
            animator = ((ValueAnimator)animator).getValues();
            for(int i = 0; i < animator.length; i++)
                if("alpha".equals(animator[i].getPropertyName()))
                    return true;

        } else
        if(animator instanceof AnimatorSet)
        {
            animator = ((AnimatorSet)animator).getChildAnimations();
            for(int j = 0; j < animator.size(); j++)
                if(modifiesAlpha((Animator)animator.get(j)))
                    return true;

        }
        return false;
    }

    static boolean modifiesAlpha(AnimationOrAnimator animationoranimator)
    {
        if(animationoranimator.animation instanceof AlphaAnimation)
            return true;
        if(animationoranimator.animation instanceof AnimationSet)
        {
            animationoranimator = ((AnimationSet)animationoranimator.animation).getAnimations();
            for(int i = 0; i < animationoranimator.size(); i++)
                if(animationoranimator.get(i) instanceof AlphaAnimation)
                    return true;

            return false;
        } else
        {
            return modifiesAlpha(animationoranimator.animator);
        }
    }

    private boolean popBackStackImmediate(String s, int i, int j)
    {
        boolean flag;
        execPendingActions();
        ensureExecReady(true);
        if(mPrimaryNav != null && i < 0 && s == null)
        {
            FragmentManager fragmentmanager = mPrimaryNav.peekChildFragmentManager();
            if(fragmentmanager != null && fragmentmanager.popBackStackImmediate())
                return true;
        }
        flag = popBackStackState(mTmpRecords, mTmpIsPop, s, i, j);
        if(!flag)
            break MISSING_BLOCK_LABEL_92;
        mExecutingActions = true;
        removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
        cleanupExec();
        doPendingDeferredStart();
        burpActive();
        return flag;
        s;
        cleanupExec();
        throw s;
    }

    private int postponePostponableTransactions(ArrayList arraylist, ArrayList arraylist1, int i, int j, ArraySet arrayset)
    {
        int l = j;
        int k = j - 1;
        while(k >= i) 
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(k);
            boolean flag1 = ((Boolean)arraylist1.get(k)).booleanValue();
            int i1;
            boolean flag;
            if(backstackrecord.isPostponed() && !backstackrecord.interactsWith(arraylist, k + 1, j))
                flag = true;
            else
                flag = false;
            i1 = l;
            if(flag)
            {
                if(mPostponedTransactions == null)
                    mPostponedTransactions = new ArrayList();
                StartEnterTransitionListener startentertransitionlistener = new StartEnterTransitionListener(backstackrecord, flag1);
                mPostponedTransactions.add(startentertransitionlistener);
                backstackrecord.setOnStartPostponedListener(startentertransitionlistener);
                if(flag1)
                    backstackrecord.executeOps();
                else
                    backstackrecord.executePopOps(false);
                i1 = l - 1;
                if(k != i1)
                {
                    arraylist.remove(k);
                    arraylist.add(i1, backstackrecord);
                }
                addAddedFragments(arrayset);
            }
            k--;
            l = i1;
        }
        return l;
    }

    private void removeRedundantOperationsAndExecute(ArrayList arraylist, ArrayList arraylist1)
    {
        if(arraylist != null && !arraylist.isEmpty())
        {
            if(arraylist1 == null || arraylist.size() != arraylist1.size())
                throw new IllegalStateException("Internal error with the back stack records");
            executePostponedTransaction(arraylist, arraylist1);
            int i1 = arraylist.size();
            int k = 0;
            for(int i = 0; i < i1;)
            {
                int l = i;
                int j = k;
                if(!((BackStackRecord)arraylist.get(i)).mReorderingAllowed)
                {
                    if(k != i)
                        executeOpsTogether(arraylist, arraylist1, k, i);
                    k = i + 1;
                    j = k;
                    if(((Boolean)arraylist1.get(i)).booleanValue())
                        do
                        {
                            j = k;
                            if(k >= i1)
                                break;
                            j = k;
                            if(!((Boolean)arraylist1.get(k)).booleanValue())
                                break;
                            j = k;
                            if(((BackStackRecord)arraylist.get(k)).mReorderingAllowed)
                                break;
                            k++;
                        } while(true);
                    executeOpsTogether(arraylist, arraylist1, i, j);
                    i = j;
                    l = j - 1;
                    j = i;
                }
                i = l + 1;
                k = j;
            }

            if(k != i1)
            {
                executeOpsTogether(arraylist, arraylist1, k, i1);
                return;
            }
        }
    }

    public static int reverseTransit(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 4097: 
            return 8194;

        case 8194: 
            return 4097;

        case 4099: 
            return 4099;
        }
    }

    private void scheduleCommit()
    {
        boolean flag1 = true;
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        Exception exception;
        if(mPostponedTransactions != null && !mPostponedTransactions.isEmpty())
            flag = true;
        else
            flag = false;
        if(mPendingActions == null || mPendingActions.size() != 1)
            flag1 = false;
          goto _L1
_L2:
        mHost.getHandler().removeCallbacks(mExecCommit);
        mHost.getHandler().post(mExecCommit);
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L1:
        if(!flag && !flag1) goto _L3; else goto _L2
    }

    private static void setHWLayerAnimListenerIfAlpha(View view, AnimationOrAnimator animationoranimator)
    {
        while(view == null || animationoranimator == null || !shouldRunOnHWLayer(view, animationoranimator)) 
            return;
        if(animationoranimator.animator != null)
        {
            animationoranimator.animator.addListener(new AnimatorOnHWLayerIfNeededListener(view));
            return;
        } else
        {
            android.view.animation.Animation.AnimationListener animationlistener = getAnimationListener(animationoranimator.animation);
            view.setLayerType(2, null);
            animationoranimator.animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(view, animationlistener));
            return;
        }
    }

    private static void setRetaining(FragmentManagerNonConfig fragmentmanagernonconfig)
    {
        if(fragmentmanagernonconfig != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj = fragmentmanagernonconfig.getFragments();
        if(obj != null)
            for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
                ((Fragment)((Iterator) (obj)).next()).mRetaining = true;

        fragmentmanagernonconfig = fragmentmanagernonconfig.getChildNonConfigs();
        if(fragmentmanagernonconfig != null)
        {
            fragmentmanagernonconfig = fragmentmanagernonconfig.iterator();
            while(fragmentmanagernonconfig.hasNext()) 
                setRetaining((FragmentManagerNonConfig)fragmentmanagernonconfig.next());
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    static boolean shouldRunOnHWLayer(View view, AnimationOrAnimator animationoranimator)
    {
        while(view == null || animationoranimator == null || android.os.Build.VERSION.SDK_INT < 19 || view.getLayerType() != 0 || !ViewCompat.hasOverlappingRendering(view) || !modifiesAlpha(animationoranimator)) 
            return false;
        return true;
    }

    private void throwException(RuntimeException runtimeexception)
    {
        Log.e("FragmentManager", runtimeexception.getMessage());
        Log.e("FragmentManager", "Activity state:");
        Object obj = new PrintWriter(new LogWriter("FragmentManager"));
        if(mHost != null)
            try
            {
                mHost.onDump("  ", null, ((PrintWriter) (obj)), new String[0]);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("FragmentManager", "Failed dumping state", ((Throwable) (obj)));
            }
        else
            try
            {
                dump("  ", null, ((PrintWriter) (obj)), new String[0]);
            }
            catch(Exception exception)
            {
                Log.e("FragmentManager", "Failed dumping state", exception);
            }
        throw runtimeexception;
    }

    public static int transitToStyleIndex(int i, boolean flag)
    {
        switch(i)
        {
        default:
            return -1;

        case 4097: 
            return !flag ? 2 : 1;

        case 8194: 
            return !flag ? 4 : 3;

        case 4099: 
            break;
        }
        return !flag ? 6 : 5;
    }

    void addBackStackState(BackStackRecord backstackrecord)
    {
        if(mBackStack == null)
            mBackStack = new ArrayList();
        mBackStack.add(backstackrecord);
    }

    public void addFragment(Fragment fragment, boolean flag)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("add: ").append(fragment).toString());
        makeActive(fragment);
        if(!fragment.mDetached)
        {
            if(mAdded.contains(fragment))
                throw new IllegalStateException((new StringBuilder()).append("Fragment already added: ").append(fragment).toString());
            synchronized(mAdded)
            {
                mAdded.add(fragment);
            }
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if(fragment.mView == null)
                fragment.mHiddenChanged = false;
            if(fragment.mHasMenu && fragment.mMenuVisible)
                mNeedMenuInvalidate = true;
            if(flag)
                moveToState(fragment);
        }
        return;
        fragment;
        arraylist;
        JVM INSTR monitorexit ;
        throw fragment;
    }

    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if(mBackStackChangeListeners == null)
            mBackStackChangeListeners = new ArrayList();
        mBackStackChangeListeners.add(onbackstackchangedlistener);
    }

    public int allocBackStackIndex(BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        if(mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
            break MISSING_BLOCK_LABEL_100;
        if(mBackStackIndices == null)
            mBackStackIndices = new ArrayList();
        i = mBackStackIndices.size();
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Setting back stack index ").append(i).append(" to ").append(backstackrecord).toString());
        mBackStackIndices.add(backstackrecord);
        this;
        JVM INSTR monitorexit ;
        return i;
        i = ((Integer)mAvailBackStackIndices.remove(mAvailBackStackIndices.size() - 1)).intValue();
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Adding back stack index ").append(i).append(" with ").append(backstackrecord).toString());
        mBackStackIndices.set(i, backstackrecord);
        this;
        JVM INSTR monitorexit ;
        return i;
        backstackrecord;
        this;
        JVM INSTR monitorexit ;
        throw backstackrecord;
    }

    public void attachController(FragmentHostCallback fragmenthostcallback, FragmentContainer fragmentcontainer, Fragment fragment)
    {
        if(mHost != null)
        {
            throw new IllegalStateException("Already attached");
        } else
        {
            mHost = fragmenthostcallback;
            mContainer = fragmentcontainer;
            mParent = fragment;
            return;
        }
    }

    public void attachFragment(Fragment fragment)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("attach: ").append(fragment).toString());
        if(fragment.mDetached)
        {
            fragment.mDetached = false;
            if(!fragment.mAdded)
            {
                if(mAdded.contains(fragment))
                    throw new IllegalStateException((new StringBuilder()).append("Fragment already added: ").append(fragment).toString());
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("add from attach: ").append(fragment).toString());
                synchronized(mAdded)
                {
                    mAdded.add(fragment);
                }
                fragment.mAdded = true;
                if(fragment.mHasMenu && fragment.mMenuVisible)
                    mNeedMenuInvalidate = true;
            }
        }
        return;
        fragment;
        arraylist;
        JVM INSTR monitorexit ;
        throw fragment;
    }

    public FragmentTransaction beginTransaction()
    {
        return new BackStackRecord(this);
    }

    void completeShowHideFragment(final Fragment fragment)
    {
        if(fragment.mView == null) goto _L2; else goto _L1
_L1:
        AnimationOrAnimator animationoranimator;
        int i = fragment.getNextTransition();
        boolean flag;
        if(!fragment.mHidden)
            flag = true;
        else
            flag = false;
        animationoranimator = loadAnimation(fragment, i, flag, fragment.getNextTransitionStyle());
        if(animationoranimator == null || animationoranimator.animator == null) goto _L4; else goto _L3
_L3:
        animationoranimator.animator.setTarget(fragment.mView);
        if(fragment.mHidden)
        {
            if(fragment.isHideReplaced())
            {
                fragment.setHideReplaced(false);
            } else
            {
                final ViewGroup container = fragment.mContainer;
                final View animatingView = fragment.mView;
                container.startViewTransition(animatingView);
                animationoranimator.animator.addListener(new AnimatorListenerAdapter() {

                    public void onAnimationEnd(Animator animator)
                    {
                        container.endViewTransition(animatingView);
                        animator.removeListener(this);
                        if(fragment.mView != null)
                            fragment.mView.setVisibility(8);
                    }

                    final FragmentManagerImpl this$0;
                    final View val$animatingView;
                    final ViewGroup val$container;
                    final Fragment val$fragment;

            
            {
                this$0 = FragmentManagerImpl.this;
                container = viewgroup;
                animatingView = view;
                fragment = fragment1;
                super();
            }
                }
);
            }
        } else
        {
            fragment.mView.setVisibility(0);
        }
        setHWLayerAnimListenerIfAlpha(fragment.mView, animationoranimator);
        animationoranimator.animator.start();
_L2:
        if(fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible)
            mNeedMenuInvalidate = true;
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
        return;
_L4:
        if(animationoranimator != null)
        {
            setHWLayerAnimListenerIfAlpha(fragment.mView, animationoranimator);
            fragment.mView.startAnimation(animationoranimator.animation);
            animationoranimator.animation.start();
        }
        byte byte0;
        if(fragment.mHidden && !fragment.isHideReplaced())
            byte0 = 8;
        else
            byte0 = 0;
        fragment.mView.setVisibility(byte0);
        if(fragment.isHideReplaced())
            fragment.setHideReplaced(false);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void detachFragment(Fragment fragment)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("detach: ").append(fragment).toString());
        if(!fragment.mDetached)
        {
            fragment.mDetached = true;
            if(fragment.mAdded)
            {
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("remove from detach: ").append(fragment).toString());
                synchronized(mAdded)
                {
                    mAdded.remove(fragment);
                }
                if(fragment.mHasMenu && fragment.mMenuVisible)
                    mNeedMenuInvalidate = true;
                fragment.mAdded = false;
            }
        }
        return;
        fragment;
        arraylist;
        JVM INSTR monitorexit ;
        throw fragment;
    }

    public void dispatchActivityCreated()
    {
        mStateSaved = false;
        dispatchStateChange(2);
    }

    public void dispatchConfigurationChanged(Configuration configuration)
    {
        for(int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performConfigurationChanged(configuration);
        }

    }

    public boolean dispatchContextItemSelected(MenuItem menuitem)
    {
        for(int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null && fragment.performContextItemSelected(menuitem))
                return true;
        }

        return false;
    }

    public void dispatchCreate()
    {
        mStateSaved = false;
        dispatchStateChange(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        boolean flag = false;
        ArrayList arraylist = null;
        for(int i = 0; i < mAdded.size();)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            ArrayList arraylist1 = arraylist;
            boolean flag1 = flag;
            if(fragment != null)
            {
                arraylist1 = arraylist;
                flag1 = flag;
                if(fragment.performCreateOptionsMenu(menu, menuinflater))
                {
                    flag1 = true;
                    arraylist1 = arraylist;
                    if(arraylist == null)
                        arraylist1 = new ArrayList();
                    arraylist1.add(fragment);
                }
            }
            i++;
            arraylist = arraylist1;
            flag = flag1;
        }

        if(mCreatedMenus != null)
        {
            for(int j = 0; j < mCreatedMenus.size(); j++)
            {
                menu = (Fragment)mCreatedMenus.get(j);
                if(arraylist == null || !arraylist.contains(menu))
                    menu.onDestroyOptionsMenu();
            }

        }
        mCreatedMenus = arraylist;
        return flag;
    }

    public void dispatchDestroy()
    {
        mDestroyed = true;
        execPendingActions();
        dispatchStateChange(0);
        mHost = null;
        mContainer = null;
        mParent = null;
    }

    public void dispatchDestroyView()
    {
        dispatchStateChange(1);
    }

    public void dispatchLowMemory()
    {
        for(int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performLowMemory();
        }

    }

    public void dispatchMultiWindowModeChanged(boolean flag)
    {
        for(int i = mAdded.size() - 1; i >= 0; i--)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performMultiWindowModeChanged(flag);
        }

    }

    void dispatchOnFragmentActivityCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentActivityCreated(fragment, bundle, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentActivityCreated(this, fragment, bundle);
        } while(true);
    }

    void dispatchOnFragmentAttached(Fragment fragment, Context context, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentAttached(fragment, context, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentAttached(this, fragment, context);
        } while(true);
    }

    void dispatchOnFragmentCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentCreated(fragment, bundle, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentCreated(this, fragment, bundle);
        } while(true);
    }

    void dispatchOnFragmentDestroyed(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentDestroyed(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentDestroyed(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentDetached(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentDetached(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentDetached(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentPaused(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentPaused(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPaused(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentPreAttached(Fragment fragment, Context context, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentPreAttached(fragment, context, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPreAttached(this, fragment, context);
        } while(true);
    }

    void dispatchOnFragmentPreCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentPreCreated(fragment, bundle, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPreCreated(this, fragment, bundle);
        } while(true);
    }

    void dispatchOnFragmentResumed(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentResumed(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentResumed(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentSaveInstanceState(Fragment fragment, Bundle bundle, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentSaveInstanceState(fragment, bundle, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentSaveInstanceState(this, fragment, bundle);
        } while(true);
    }

    void dispatchOnFragmentStarted(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentStarted(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentStarted(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentStopped(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentStopped(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentStopped(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentViewCreated(Fragment fragment, View view, Bundle bundle, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentViewCreated(fragment, view, bundle, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentViewCreated(this, fragment, view, bundle);
        } while(true);
    }

    void dispatchOnFragmentViewDestroyed(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentViewDestroyed(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentViewDestroyed(this, fragment);
        } while(true);
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuitem)
    {
        for(int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null && fragment.performOptionsItemSelected(menuitem))
                return true;
        }

        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu)
    {
        for(int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performOptionsMenuClosed(menu);
        }

    }

    public void dispatchPause()
    {
        dispatchStateChange(4);
    }

    public void dispatchPictureInPictureModeChanged(boolean flag)
    {
        for(int i = mAdded.size() - 1; i >= 0; i--)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performPictureInPictureModeChanged(flag);
        }

    }

    public boolean dispatchPrepareOptionsMenu(Menu menu)
    {
        boolean flag = false;
        for(int i = 0; i < mAdded.size();)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            boolean flag1 = flag;
            if(fragment != null)
            {
                flag1 = flag;
                if(fragment.performPrepareOptionsMenu(menu))
                    flag1 = true;
            }
            i++;
            flag = flag1;
        }

        return flag;
    }

    public void dispatchReallyStop()
    {
        dispatchStateChange(2);
    }

    public void dispatchResume()
    {
        mStateSaved = false;
        dispatchStateChange(5);
    }

    public void dispatchStart()
    {
        mStateSaved = false;
        dispatchStateChange(4);
    }

    public void dispatchStop()
    {
        mStateSaved = true;
        dispatchStateChange(3);
    }

    void doPendingDeferredStart()
    {
        if(mHavePendingDeferredStart)
        {
            boolean flag = false;
            for(int i = 0; i < mActive.size();)
            {
                Fragment fragment = (Fragment)mActive.valueAt(i);
                boolean flag1 = flag;
                if(fragment != null)
                {
                    flag1 = flag;
                    if(fragment.mLoaderManager != null)
                        flag1 = flag | fragment.mLoaderManager.hasRunningLoaders();
                }
                i++;
                flag = flag1;
            }

            if(!flag)
            {
                mHavePendingDeferredStart = false;
                startPendingDeferredFragments();
            }
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        String s1 = (new StringBuilder()).append(s).append("    ").toString();
        if(mActive != null)
        {
            int k1 = mActive.size();
            if(k1 > 0)
            {
                printwriter.print(s);
                printwriter.print("Active Fragments in ");
                printwriter.print(Integer.toHexString(System.identityHashCode(this)));
                printwriter.println(":");
                for(int i = 0; i < k1; i++)
                {
                    Fragment fragment = (Fragment)mActive.valueAt(i);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(i);
                    printwriter.print(": ");
                    printwriter.println(fragment);
                    if(fragment != null)
                        fragment.dump(s1, filedescriptor, printwriter, as);
                }

            }
        }
        int l1 = mAdded.size();
        if(l1 > 0)
        {
            printwriter.print(s);
            printwriter.println("Added Fragments:");
            for(int j = 0; j < l1; j++)
            {
                Fragment fragment1 = (Fragment)mAdded.get(j);
                printwriter.print(s);
                printwriter.print("  #");
                printwriter.print(j);
                printwriter.print(": ");
                printwriter.println(fragment1.toString());
            }

        }
        if(mCreatedMenus != null)
        {
            int i2 = mCreatedMenus.size();
            if(i2 > 0)
            {
                printwriter.print(s);
                printwriter.println("Fragments Created Menus:");
                for(int k = 0; k < i2; k++)
                {
                    Fragment fragment2 = (Fragment)mCreatedMenus.get(k);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(k);
                    printwriter.print(": ");
                    printwriter.println(fragment2.toString());
                }

            }
        }
        if(mBackStack != null)
        {
            int j2 = mBackStack.size();
            if(j2 > 0)
            {
                printwriter.print(s);
                printwriter.println("Back Stack:");
                for(int l = 0; l < j2; l++)
                {
                    BackStackRecord backstackrecord = (BackStackRecord)mBackStack.get(l);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(l);
                    printwriter.print(": ");
                    printwriter.println(backstackrecord.toString());
                    backstackrecord.dump(s1, filedescriptor, printwriter, as);
                }

            }
        }
        this;
        JVM INSTR monitorenter ;
        if(mBackStackIndices == null) goto _L2; else goto _L1
_L1:
        int k2 = mBackStackIndices.size();
        if(k2 <= 0) goto _L2; else goto _L3
_L3:
        printwriter.print(s);
        printwriter.println("Back Stack Indices:");
        int i1 = 0;
_L4:
        if(i1 >= k2)
            break; /* Loop/switch isn't completed */
        filedescriptor = (BackStackRecord)mBackStackIndices.get(i1);
        printwriter.print(s);
        printwriter.print("  #");
        printwriter.print(i1);
        printwriter.print(": ");
        printwriter.println(filedescriptor);
        i1++;
        if(true) goto _L4; else goto _L2
_L2:
        if(mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
        {
            printwriter.print(s);
            printwriter.print("mAvailBackStackIndices: ");
            printwriter.println(Arrays.toString(mAvailBackStackIndices.toArray()));
        }
        this;
        JVM INSTR monitorexit ;
        if(mPendingActions != null)
        {
            int l2 = mPendingActions.size();
            if(l2 > 0)
            {
                printwriter.print(s);
                printwriter.println("Pending Actions:");
                for(int j1 = 0; j1 < l2; j1++)
                {
                    filedescriptor = (OpGenerator)mPendingActions.get(j1);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(j1);
                    printwriter.print(": ");
                    printwriter.println(filedescriptor);
                }

            }
        }
        break MISSING_BLOCK_LABEL_699;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        printwriter.print(s);
        printwriter.println("FragmentManager misc state:");
        printwriter.print(s);
        printwriter.print("  mHost=");
        printwriter.println(mHost);
        printwriter.print(s);
        printwriter.print("  mContainer=");
        printwriter.println(mContainer);
        if(mParent != null)
        {
            printwriter.print(s);
            printwriter.print("  mParent=");
            printwriter.println(mParent);
        }
        printwriter.print(s);
        printwriter.print("  mCurState=");
        printwriter.print(mCurState);
        printwriter.print(" mStateSaved=");
        printwriter.print(mStateSaved);
        printwriter.print(" mDestroyed=");
        printwriter.println(mDestroyed);
        if(mNeedMenuInvalidate)
        {
            printwriter.print(s);
            printwriter.print("  mNeedMenuInvalidate=");
            printwriter.println(mNeedMenuInvalidate);
        }
        if(mNoTransactionsBecause != null)
        {
            printwriter.print(s);
            printwriter.print("  mNoTransactionsBecause=");
            printwriter.println(mNoTransactionsBecause);
        }
        return;
    }

    public void enqueueAction(OpGenerator opgenerator, boolean flag)
    {
        if(!flag)
            checkStateLoss();
        this;
        JVM INSTR monitorenter ;
        if(!mDestroyed && mHost != null)
            break MISSING_BLOCK_LABEL_47;
        if(!flag)
            break MISSING_BLOCK_LABEL_31;
        this;
        JVM INSTR monitorexit ;
        return;
        throw new IllegalStateException("Activity has been destroyed");
        opgenerator;
        this;
        JVM INSTR monitorexit ;
        throw opgenerator;
        if(mPendingActions == null)
            mPendingActions = new ArrayList();
        mPendingActions.add(opgenerator);
        scheduleCommit();
        this;
        JVM INSTR monitorexit ;
    }

    void ensureInflatedFragmentView(Fragment fragment)
    {
label0:
        {
            if(fragment.mFromLayout && !fragment.mPerformedCreateView)
            {
                fragment.mView = fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
                if(fragment.mView == null)
                    break label0;
                fragment.mInnerView = fragment.mView;
                fragment.mView.setSaveFromParentEnabled(false);
                if(fragment.mHidden)
                    fragment.mView.setVisibility(8);
                fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                dispatchOnFragmentViewCreated(fragment, fragment.mView, fragment.mSavedFragmentState, false);
            }
            return;
        }
        fragment.mInnerView = null;
    }

    public boolean execPendingActions()
    {
        boolean flag;
        ensureExecReady(true);
        flag = false;
_L2:
        if(!generateOpsForPendingActions(mTmpRecords, mTmpIsPop))
            break; /* Loop/switch isn't completed */
        mExecutingActions = true;
        removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
        cleanupExec();
        flag = true;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        cleanupExec();
        throw exception;
_L1:
        doPendingDeferredStart();
        burpActive();
        return flag;
    }

    public void execSingleAction(OpGenerator opgenerator, boolean flag)
    {
        if(flag && (mHost == null || mDestroyed))
            return;
        ensureExecReady(flag);
        if(!opgenerator.generateOps(mTmpRecords, mTmpIsPop))
            break MISSING_BLOCK_LABEL_62;
        mExecutingActions = true;
        removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
        cleanupExec();
        doPendingDeferredStart();
        burpActive();
        return;
        opgenerator;
        cleanupExec();
        throw opgenerator;
    }

    public boolean executePendingTransactions()
    {
        boolean flag = execPendingActions();
        forcePostponedTransactions();
        return flag;
    }

    public Fragment findFragmentById(int i)
    {
        int j = mAdded.size() - 1;
_L10:
        if(j < 0) goto _L2; else goto _L1
_L1:
        Fragment fragment = (Fragment)mAdded.get(j);
        if(fragment == null || fragment.mFragmentId != i) goto _L4; else goto _L3
_L3:
        return fragment;
_L4:
        j--;
        continue; /* Loop/switch isn't completed */
_L2:
        if(mActive == null)
            break MISSING_BLOCK_LABEL_105;
        j = mActive.size() - 1;
_L8:
        if(j < 0) goto _L6; else goto _L5
_L5:
        Fragment fragment1;
        fragment1 = (Fragment)mActive.valueAt(j);
        if(fragment1 == null)
            continue; /* Loop/switch isn't completed */
        fragment = fragment1;
        if(fragment1.mFragmentId == i) goto _L3; else goto _L7
_L7:
        j--;
          goto _L8
_L6:
        return null;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public Fragment findFragmentByTag(String s)
    {
        if(s == null) goto _L2; else goto _L1
_L1:
        int i = mAdded.size() - 1;
_L11:
        if(i < 0) goto _L2; else goto _L3
_L3:
        Fragment fragment = (Fragment)mAdded.get(i);
        if(fragment == null || !s.equals(fragment.mTag)) goto _L5; else goto _L4
_L4:
        return fragment;
_L5:
        i--;
        continue; /* Loop/switch isn't completed */
_L2:
        if(mActive == null || s == null)
            break MISSING_BLOCK_LABEL_119;
        i = mActive.size() - 1;
_L9:
        if(i < 0) goto _L7; else goto _L6
_L6:
        Fragment fragment1;
        fragment1 = (Fragment)mActive.valueAt(i);
        if(fragment1 == null)
            continue; /* Loop/switch isn't completed */
        fragment = fragment1;
        if(s.equals(fragment1.mTag)) goto _L4; else goto _L8
_L8:
        i--;
          goto _L9
_L7:
        return null;
        if(true) goto _L11; else goto _L10
_L10:
    }

    public Fragment findFragmentByWho(String s)
    {
        if(mActive != null && s != null)
        {
            for(int i = mActive.size() - 1; i >= 0; i--)
            {
                Fragment fragment = (Fragment)mActive.valueAt(i);
                if(fragment == null)
                    continue;
                fragment = fragment.findFragmentByWho(s);
                if(fragment != null)
                    return fragment;
            }

        }
        return null;
    }

    public void freeBackStackIndex(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mBackStackIndices.set(i, null);
        if(mAvailBackStackIndices == null)
            mAvailBackStackIndices = new ArrayList();
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Freeing back stack index ").append(i).toString());
        mAvailBackStackIndices.add(Integer.valueOf(i));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    int getActiveFragmentCount()
    {
        if(mActive == null)
            return 0;
        else
            return mActive.size();
    }

    List getActiveFragments()
    {
        if(mActive != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        return ((List) (obj));
_L2:
        int j = mActive.size();
        ArrayList arraylist = new ArrayList(j);
        int i = 0;
        do
        {
            obj = arraylist;
            if(i >= j)
                continue;
            arraylist.add(mActive.valueAt(i));
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public FragmentManager.BackStackEntry getBackStackEntryAt(int i)
    {
        return (FragmentManager.BackStackEntry)mBackStack.get(i);
    }

    public int getBackStackEntryCount()
    {
        if(mBackStack != null)
            return mBackStack.size();
        else
            return 0;
    }

    public Fragment getFragment(Bundle bundle, String s)
    {
        int i = bundle.getInt(s, -1);
        if(i == -1)
        {
            bundle = null;
        } else
        {
            Fragment fragment = (Fragment)mActive.get(i);
            bundle = fragment;
            if(fragment == null)
            {
                throwException(new IllegalStateException((new StringBuilder()).append("Fragment no longer exists for key ").append(s).append(": index ").append(i).toString()));
                return fragment;
            }
        }
        return bundle;
    }

    public List getFragments()
    {
        if(mAdded.isEmpty())
            return Collections.EMPTY_LIST;
        List list;
        synchronized(mAdded)
        {
            list = (List)mAdded.clone();
        }
        return list;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    android.view.LayoutInflater.Factory2 getLayoutInflaterFactory()
    {
        return this;
    }

    public Fragment getPrimaryNavigationFragment()
    {
        return mPrimaryNav;
    }

    public void hideFragment(Fragment fragment)
    {
        boolean flag = true;
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("hide: ").append(fragment).toString());
        if(!fragment.mHidden)
        {
            fragment.mHidden = true;
            if(fragment.mHiddenChanged)
                flag = false;
            fragment.mHiddenChanged = flag;
        }
    }

    public boolean isDestroyed()
    {
        return mDestroyed;
    }

    boolean isStateAtLeast(int i)
    {
        return mCurState >= i;
    }

    public boolean isStateSaved()
    {
        return mStateSaved;
    }

    AnimationOrAnimator loadAnimation(Fragment fragment, int i, boolean flag, int j)
    {
        int k;
        k = fragment.getNextAnim();
        Animation animation = fragment.onCreateAnimation(i, flag, k);
        if(animation != null)
            return new AnimationOrAnimator(animation);
        fragment = fragment.onCreateAnimator(i, flag, k);
        if(fragment != null)
            return new AnimationOrAnimator(fragment);
        if(k == 0) goto _L2; else goto _L1
_L1:
        boolean flag1;
        boolean flag2;
        boolean flag3;
        flag3 = "anim".equals(mHost.getContext().getResources().getResourceTypeName(k));
        flag2 = false;
        flag1 = flag2;
        if(!flag3)
            break MISSING_BLOCK_LABEL_130;
        fragment = AnimationUtils.loadAnimation(mHost.getContext(), k);
        if(fragment == null)
            break MISSING_BLOCK_LABEL_127;
        fragment = new AnimationOrAnimator(fragment);
        return fragment;
        fragment;
        throw fragment;
        flag1 = true;
_L6:
        if(flag1) goto _L2; else goto _L3
_L3:
        fragment = AnimatorInflater.loadAnimator(mHost.getContext(), k);
        if(fragment == null) goto _L2; else goto _L4
_L4:
        fragment = new AnimationOrAnimator(fragment);
        return fragment;
        fragment;
        if(flag3)
            throw fragment;
        fragment = AnimationUtils.loadAnimation(mHost.getContext(), k);
        if(fragment != null)
            return new AnimationOrAnimator(fragment);
_L2:
        if(i == 0)
            return null;
        i = transitToStyleIndex(i, flag);
        if(i < 0)
            return null;
        switch(i)
        {
        default:
            i = j;
            if(j == 0)
            {
                i = j;
                if(mHost.onHasWindowAnimations())
                    i = mHost.onGetWindowAnimations();
            }
            if(i == 0)
                return null;
            else
                return null;

        case 1: // '\001'
            return makeOpenCloseAnimation(mHost.getContext(), 1.125F, 1.0F, 0.0F, 1.0F);

        case 2: // '\002'
            return makeOpenCloseAnimation(mHost.getContext(), 1.0F, 0.975F, 1.0F, 0.0F);

        case 3: // '\003'
            return makeOpenCloseAnimation(mHost.getContext(), 0.975F, 1.0F, 0.0F, 1.0F);

        case 4: // '\004'
            return makeOpenCloseAnimation(mHost.getContext(), 1.0F, 1.075F, 1.0F, 0.0F);

        case 5: // '\005'
            return makeFadeAnimation(mHost.getContext(), 0.0F, 1.0F);

        case 6: // '\006'
            return makeFadeAnimation(mHost.getContext(), 1.0F, 0.0F);
        }
        fragment;
        flag1 = flag2;
        if(true) goto _L6; else goto _L5
_L5:
    }

    void makeActive(Fragment fragment)
    {
        if(fragment.mIndex < 0)
        {
            int i = mNextFragmentIndex;
            mNextFragmentIndex = i + 1;
            fragment.setIndex(i, mParent);
            if(mActive == null)
                mActive = new SparseArray();
            mActive.put(fragment.mIndex, fragment);
            if(DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("Allocated fragment index ").append(fragment).toString());
                return;
            }
        }
    }

    void makeInactive(Fragment fragment)
    {
        if(fragment.mIndex < 0)
            return;
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Freeing fragment index ").append(fragment).toString());
        mActive.put(fragment.mIndex, null);
        mHost.inactivateFragment(fragment.mWho);
        fragment.initState();
    }

    void moveFragmentToExpectedState(Fragment fragment)
    {
        if(fragment != null)
        {
            int j = mCurState;
            int i = j;
            if(fragment.mRemoving)
                if(fragment.isInBackStack())
                    i = Math.min(j, 1);
                else
                    i = Math.min(j, 0);
            moveToState(fragment, i, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
            if(fragment.mView != null)
            {
                Object obj = findFragmentUnder(fragment);
                if(obj != null)
                {
                    obj = ((Fragment) (obj)).mView;
                    ViewGroup viewgroup = fragment.mContainer;
                    i = viewgroup.indexOfChild(((View) (obj)));
                    j = viewgroup.indexOfChild(fragment.mView);
                    if(j < i)
                    {
                        viewgroup.removeViewAt(j);
                        viewgroup.addView(fragment.mView, i);
                    }
                }
                if(fragment.mIsNewlyAdded && fragment.mContainer != null)
                {
                    if(fragment.mPostponedAlpha > 0.0F)
                        fragment.mView.setAlpha(fragment.mPostponedAlpha);
                    fragment.mPostponedAlpha = 0.0F;
                    fragment.mIsNewlyAdded = false;
                    AnimationOrAnimator animationoranimator = loadAnimation(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                    if(animationoranimator != null)
                    {
                        setHWLayerAnimListenerIfAlpha(fragment.mView, animationoranimator);
                        if(animationoranimator.animation != null)
                        {
                            fragment.mView.startAnimation(animationoranimator.animation);
                        } else
                        {
                            animationoranimator.animator.setTarget(fragment.mView);
                            animationoranimator.animator.start();
                        }
                    }
                }
            }
            if(fragment.mHiddenChanged)
            {
                completeShowHideFragment(fragment);
                return;
            }
        }
    }

    void moveToState(int i, boolean flag)
    {
        if(mHost == null && i != 0)
            throw new IllegalStateException("No activity");
        if(flag || i != mCurState) goto _L2; else goto _L1
_L1:
        return;
_L2:
        mCurState = i;
        if(mActive == null)
            continue; /* Loop/switch isn't completed */
        i = 0;
        int j1 = mAdded.size();
        for(int j = 0; j < j1;)
        {
            Fragment fragment = (Fragment)mAdded.get(j);
            moveFragmentToExpectedState(fragment);
            int l = i;
            if(fragment.mLoaderManager != null)
                l = i | fragment.mLoaderManager.hasRunningLoaders();
            j++;
            i = l;
        }

        j1 = mActive.size();
        boolean flag1 = false;
        int k = i;
        for(i = ((flag1) ? 1 : 0); i < j1;)
        {
            int i1;
label0:
            {
                Fragment fragment1 = (Fragment)mActive.valueAt(i);
                i1 = k;
                if(fragment1 == null)
                    break label0;
                if(!fragment1.mRemoving)
                {
                    i1 = k;
                    if(!fragment1.mDetached)
                        break label0;
                }
                i1 = k;
                if(!fragment1.mIsNewlyAdded)
                {
                    moveFragmentToExpectedState(fragment1);
                    i1 = k;
                    if(fragment1.mLoaderManager != null)
                        i1 = k | fragment1.mLoaderManager.hasRunningLoaders();
                }
            }
            i++;
            k = i1;
        }

        if(k == 0)
            startPendingDeferredFragments();
        if(mNeedMenuInvalidate && mHost != null && mCurState == 5)
        {
            mHost.onSupportInvalidateOptionsMenu();
            mNeedMenuInvalidate = false;
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    void moveToState(Fragment fragment)
    {
        moveToState(fragment, mCurState, 0, 0, false);
    }

    void moveToState(Fragment fragment, int i, int j, int k, boolean flag)
    {
        int j1;
label0:
        {
            if(fragment.mAdded)
            {
                j1 = i;
                if(!fragment.mDetached)
                    break label0;
            }
            j1 = i;
            if(i > 1)
                j1 = 1;
        }
        int l = j1;
        if(fragment.mRemoving)
        {
            l = j1;
            if(j1 > fragment.mState)
                if(fragment.mState == 0 && fragment.isInBackStack())
                    l = 1;
                else
                    l = fragment.mState;
        }
        i = l;
        if(fragment.mDeferStart)
        {
            i = l;
            if(fragment.mState < 4)
            {
                i = l;
                if(l > 3)
                    i = 3;
            }
        }
        if(fragment.mState > i) goto _L2; else goto _L1
_L1:
        if(!fragment.mFromLayout || fragment.mInLayout) goto _L4; else goto _L3
_L3:
        return;
_L4:
        int i1;
        int k1;
        if(fragment.getAnimatingAway() != null || fragment.getAnimator() != null)
        {
            fragment.setAnimatingAway(null);
            fragment.setAnimator(null);
            moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, true);
        }
        k = i;
        i1 = i;
        k1 = i;
        j = i;
        fragment.mState;
        JVM INSTR tableswitch 0 4: default 220
    //                   0 295
    //                   1 735
    //                   2 1130
    //                   3 1149
    //                   4 1203;
           goto _L5 _L6 _L7 _L8 _L9 _L10
_L6:
        break; /* Loop/switch isn't completed */
_L5:
        i1 = i;
_L12:
        if(fragment.mState != i1)
        {
            Log.w("FragmentManager", (new StringBuilder()).append("moveToState: Fragment state for ").append(fragment).append(" not updated inline; ").append("expected state ").append(i1).append(" found ").append(fragment.mState).toString());
            fragment.mState = i1;
            return;
        }
        if(true) goto _L3; else goto _L11
_L11:
        k = i;
        if(i > 0)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("moveto CREATED: ").append(fragment).toString());
            k = i;
            if(fragment.mSavedFragmentState != null)
            {
                fragment.mSavedFragmentState.setClassLoader(mHost.getContext().getClassLoader());
                fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                fragment.mTarget = getFragment(fragment.mSavedFragmentState, "android:target_state");
                if(fragment.mTarget != null)
                    fragment.mTargetRequestCode = fragment.mSavedFragmentState.getInt("android:target_req_state", 0);
                fragment.mUserVisibleHint = fragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                k = i;
                if(!fragment.mUserVisibleHint)
                {
                    fragment.mDeferStart = true;
                    k = i;
                    if(i > 3)
                        k = 3;
                }
            }
            fragment.mHost = mHost;
            fragment.mParentFragment = mParent;
            FragmentManagerImpl fragmentmanagerimpl;
            if(mParent != null)
                fragmentmanagerimpl = mParent.mChildFragmentManager;
            else
                fragmentmanagerimpl = mHost.getFragmentManagerImpl();
            fragment.mFragmentManager = fragmentmanagerimpl;
            if(fragment.mTarget != null)
            {
                if(mActive.get(fragment.mTarget.mIndex) != fragment.mTarget)
                    throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(fragment).append(" declared target fragment ").append(fragment.mTarget).append(" that does not belong to this FragmentManager!").toString());
                if(fragment.mTarget.mState < 1)
                    moveToState(fragment.mTarget, 1, 0, 0, true);
            }
            dispatchOnFragmentPreAttached(fragment, mHost.getContext(), false);
            fragment.mCalled = false;
            fragment.onAttach(mHost.getContext());
            if(!fragment.mCalled)
                throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(fragment).append(" did not call through to super.onAttach()").toString());
            ViewGroup viewgroup;
            if(fragment.mParentFragment == null)
                mHost.onAttachFragment(fragment);
            else
                fragment.mParentFragment.onAttachFragment(fragment);
            dispatchOnFragmentAttached(fragment, mHost.getContext(), false);
            if(!fragment.mIsCreated)
            {
                dispatchOnFragmentPreCreated(fragment, fragment.mSavedFragmentState, false);
                fragment.performCreate(fragment.mSavedFragmentState);
                dispatchOnFragmentCreated(fragment, fragment.mSavedFragmentState, false);
            } else
            {
                fragment.restoreChildFragmentState(fragment.mSavedFragmentState);
                fragment.mState = 1;
            }
            fragment.mRetaining = false;
        }
_L7:
        ensureInflatedFragmentView(fragment);
        i1 = k;
        if(k > 1)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("moveto ACTIVITY_CREATED: ").append(fragment).toString());
            if(!fragment.mFromLayout)
            {
                Object obj = null;
                if(fragment.mContainerId != 0)
                {
                    if(fragment.mContainerId == -1)
                        throwException(new IllegalArgumentException((new StringBuilder()).append("Cannot create fragment ").append(fragment).append(" for a container view with no id").toString()));
                    viewgroup = (ViewGroup)mContainer.onFindViewById(fragment.mContainerId);
                    obj = viewgroup;
                    if(viewgroup == null)
                    {
                        obj = viewgroup;
                        if(!fragment.mRestored)
                        {
                            try
                            {
                                obj = fragment.getResources().getResourceName(fragment.mContainerId);
                            }
                            catch(android.content.res.Resources.NotFoundException notfoundexception)
                            {
                                notfoundexception = "unknown";
                            }
                            throwException(new IllegalArgumentException((new StringBuilder()).append("No view found for id 0x").append(Integer.toHexString(fragment.mContainerId)).append(" (").append(((String) (obj))).append(") for fragment ").append(fragment).toString()));
                            obj = viewgroup;
                        }
                    }
                }
                fragment.mContainer = ((ViewGroup) (obj));
                fragment.mView = fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), ((ViewGroup) (obj)), fragment.mSavedFragmentState);
                if(fragment.mView != null)
                {
                    fragment.mInnerView = fragment.mView;
                    fragment.mView.setSaveFromParentEnabled(false);
                    if(obj != null)
                        ((ViewGroup) (obj)).addView(fragment.mView);
                    if(fragment.mHidden)
                        fragment.mView.setVisibility(8);
                    fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                    dispatchOnFragmentViewCreated(fragment, fragment.mView, fragment.mSavedFragmentState, false);
                    if(fragment.mView.getVisibility() == 0 && fragment.mContainer != null)
                        flag = true;
                    else
                        flag = false;
                    fragment.mIsNewlyAdded = flag;
                } else
                {
                    fragment.mInnerView = null;
                }
            }
            fragment.performActivityCreated(fragment.mSavedFragmentState);
            dispatchOnFragmentActivityCreated(fragment, fragment.mSavedFragmentState, false);
            if(fragment.mView != null)
                fragment.restoreViewState(fragment.mSavedFragmentState);
            fragment.mSavedFragmentState = null;
            i1 = k;
        }
_L8:
        k1 = i1;
        if(i1 > 2)
        {
            fragment.mState = 3;
            k1 = i1;
        }
_L9:
        j = k1;
        if(k1 > 3)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("moveto STARTED: ").append(fragment).toString());
            fragment.performStart();
            dispatchOnFragmentStarted(fragment, false);
            j = k1;
        }
_L10:
        i1 = j;
        if(j > 4)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("moveto RESUMED: ").append(fragment).toString());
            fragment.performResume();
            dispatchOnFragmentResumed(fragment, false);
            fragment.mSavedFragmentState = null;
            fragment.mSavedViewState = null;
            i1 = j;
        }
          goto _L12
_L2:
        i1 = i;
        if(fragment.mState <= i) goto _L12; else goto _L13
_L13:
        View view;
        switch(fragment.mState)
        {
        default:
            i1 = i;
            continue; /* Loop/switch isn't completed */

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_1374;

        case 5: // '\005'
            if(i < 5)
            {
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("movefrom RESUMED: ").append(fragment).toString());
                fragment.performPause();
                dispatchOnFragmentPaused(fragment, false);
            }
            // fall through

        case 4: // '\004'
            if(i < 4)
            {
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("movefrom STARTED: ").append(fragment).toString());
                fragment.performStop();
                dispatchOnFragmentStopped(fragment, false);
            }
            // fall through

        case 3: // '\003'
            if(i < 3)
            {
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("movefrom STOPPED: ").append(fragment).toString());
                fragment.performReallyStop();
            }
            break;

        case 2: // '\002'
            break;
        }
        break MISSING_BLOCK_LABEL_1572;
_L14:
        i1 = i;
        if(i < 1)
        {
            AnimationOrAnimator animationoranimator;
            Object obj1;
            if(mDestroyed)
                if(fragment.getAnimatingAway() != null)
                {
                    view = fragment.getAnimatingAway();
                    fragment.setAnimatingAway(null);
                    view.clearAnimation();
                } else
                if(fragment.getAnimator() != null)
                {
                    Animator animator = fragment.getAnimator();
                    fragment.setAnimator(null);
                    animator.cancel();
                }
            if(fragment.getAnimatingAway() != null || fragment.getAnimator() != null)
            {
                fragment.setStateAfterAnimating(i);
                i1 = 1;
            } else
            {
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("movefrom CREATED: ").append(fragment).toString());
                if(!fragment.mRetaining)
                {
                    fragment.performDestroy();
                    dispatchOnFragmentDestroyed(fragment, false);
                } else
                {
                    fragment.mState = 0;
                }
                fragment.performDetach();
                dispatchOnFragmentDetached(fragment, false);
                i1 = i;
                if(!flag)
                    if(!fragment.mRetaining)
                    {
                        makeInactive(fragment);
                        i1 = i;
                    } else
                    {
                        fragment.mHost = null;
                        fragment.mParentFragment = null;
                        fragment.mFragmentManager = null;
                        i1 = i;
                    }
            }
        }
        continue; /* Loop/switch isn't completed */
        if(i < 2)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("movefrom ACTIVITY_CREATED: ").append(fragment).toString());
            if(fragment.mView != null && mHost.onShouldSaveFragmentState(fragment) && fragment.mSavedViewState == null)
                saveFragmentViewState(fragment);
            fragment.performDestroyView();
            dispatchOnFragmentViewDestroyed(fragment, false);
            if(fragment.mView != null && fragment.mContainer != null)
            {
                fragment.mView.clearAnimation();
                fragment.mContainer.endViewTransition(fragment.mView);
                obj1 = null;
                animationoranimator = obj1;
                if(mCurState > 0)
                {
                    animationoranimator = obj1;
                    if(!mDestroyed)
                    {
                        animationoranimator = obj1;
                        if(fragment.mView.getVisibility() == 0)
                        {
                            animationoranimator = obj1;
                            if(fragment.mPostponedAlpha >= 0.0F)
                                animationoranimator = loadAnimation(fragment, j, false, k);
                        }
                    }
                }
                fragment.mPostponedAlpha = 0.0F;
                if(animationoranimator != null)
                    animateRemoveFragment(fragment, animationoranimator, i);
                fragment.mContainer.removeView(fragment.mView);
            }
            fragment.mContainer = null;
            fragment.mView = null;
            fragment.mInnerView = null;
            fragment.mInLayout = false;
        }
          goto _L14
        if(true) goto _L12; else goto _L15
_L15:
    }

    public void noteStateNotSaved()
    {
        mSavedNonConfig = null;
        mStateSaved = false;
        int j = mAdded.size();
        for(int i = 0; i < j; i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.noteStateNotSaved();
        }

    }

    public View onCreateView(View view, String s, Context context, AttributeSet attributeset)
    {
        if(!"fragment".equals(s))
            return null;
        s = attributeset.getAttributeValue(null, "class");
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, FragmentTag.Fragment);
        String s1 = s;
        if(s == null)
            s1 = typedarray.getString(0);
        int k = typedarray.getResourceId(1, -1);
        String s2 = typedarray.getString(2);
        typedarray.recycle();
        if(!Fragment.isSupportFragmentClass(mHost.getContext(), s1))
            return null;
        int i;
        if(view != null)
            i = view.getId();
        else
            i = 0;
        if(i == -1 && k == -1 && s2 == null)
            throw new IllegalArgumentException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Must specify unique android:id, android:tag, or have a parent with an id for ").append(s1).toString());
        if(k != -1)
            s = findFragmentById(k);
        else
            s = null;
        view = s;
        if(s == null)
        {
            view = s;
            if(s2 != null)
                view = findFragmentByTag(s2);
        }
        s = view;
        if(view == null)
        {
            s = view;
            if(i != -1)
                s = findFragmentById(i);
        }
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("onCreateView: id=0x").append(Integer.toHexString(k)).append(" fname=").append(s1).append(" existing=").append(s).toString());
        if(s == null)
        {
            view = mContainer.instantiate(context, s1, null);
            view.mFromLayout = true;
            int j;
            if(k != 0)
                j = k;
            else
                j = i;
            view.mFragmentId = j;
            view.mContainerId = i;
            view.mTag = s2;
            view.mInLayout = true;
            view.mFragmentManager = this;
            view.mHost = mHost;
            view.onInflate(mHost.getContext(), attributeset, ((Fragment) (view)).mSavedFragmentState);
            addFragment(view, true);
        } else
        {
            if(((Fragment) (s)).mInLayout)
                throw new IllegalArgumentException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Duplicate id 0x").append(Integer.toHexString(k)).append(", tag ").append(s2).append(", or parent id 0x").append(Integer.toHexString(i)).append(" with another fragment for ").append(s1).toString());
            s.mInLayout = true;
            s.mHost = mHost;
            view = s;
            if(!((Fragment) (s)).mRetaining)
            {
                s.onInflate(mHost.getContext(), attributeset, ((Fragment) (s)).mSavedFragmentState);
                view = s;
            }
        }
        if(mCurState < 1 && ((Fragment) (view)).mFromLayout)
            moveToState(view, 1, 0, 0, false);
        else
            moveToState(view);
        if(((Fragment) (view)).mView == null)
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(s1).append(" did not create a view.").toString());
        if(k != 0)
            ((Fragment) (view)).mView.setId(k);
        if(((Fragment) (view)).mView.getTag() == null)
            ((Fragment) (view)).mView.setTag(s2);
        return ((Fragment) (view)).mView;
    }

    public View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        return onCreateView(null, s, context, attributeset);
    }

    public void performPendingDeferredStart(Fragment fragment)
    {
label0:
        {
            if(fragment.mDeferStart)
            {
                if(!mExecutingActions)
                    break label0;
                mHavePendingDeferredStart = true;
            }
            return;
        }
        fragment.mDeferStart = false;
        moveToState(fragment, mCurState, 0, 0, false);
    }

    public void popBackStack()
    {
        enqueueAction(new PopBackStackState(null, -1, 0), false);
    }

    public void popBackStack(int i, int j)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad id: ").append(i).toString());
        } else
        {
            enqueueAction(new PopBackStackState(null, i, j), false);
            return;
        }
    }

    public void popBackStack(String s, int i)
    {
        enqueueAction(new PopBackStackState(s, -1, i), false);
    }

    public boolean popBackStackImmediate()
    {
        checkStateLoss();
        return popBackStackImmediate(null, -1, 0);
    }

    public boolean popBackStackImmediate(int i, int j)
    {
        checkStateLoss();
        execPendingActions();
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad id: ").append(i).toString());
        else
            return popBackStackImmediate(null, i, j);
    }

    public boolean popBackStackImmediate(String s, int i)
    {
        checkStateLoss();
        return popBackStackImmediate(s, -1, i);
    }

    boolean popBackStackState(ArrayList arraylist, ArrayList arraylist1, String s, int i, int j)
    {
        if(mBackStack != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if(s != null || i >= 0 || (j & 1) != 0) goto _L4; else goto _L3
_L3:
        if((i = mBackStack.size() - 1) < 0) goto _L1; else goto _L5
_L5:
        arraylist.add(mBackStack.remove(i));
        arraylist1.add(Boolean.valueOf(true));
_L8:
        return true;
_L4:
        int k;
        int l;
        k = -1;
        if(s == null && i < 0)
            continue; /* Loop/switch isn't completed */
        l = mBackStack.size() - 1;
        break MISSING_BLOCK_LABEL_89;
        if(l < 0) goto _L1; else goto _L6
_L6:
        k = l;
        if((j & 1) == 0)
            continue; /* Loop/switch isn't completed */
        j = l - 1;
        do
        {
            k = j;
            if(j < 0)
                continue; /* Loop/switch isn't completed */
            backstackrecord = (BackStackRecord)mBackStack.get(j);
            if(s == null || !s.equals(backstackrecord.getName()))
            {
                k = j;
                if(i < 0)
                    continue; /* Loop/switch isn't completed */
                k = j;
                if(i != backstackrecord.mIndex)
                    continue; /* Loop/switch isn't completed */
            }
            j--;
        } while(true);
        do
        {
            if(l < 0)
                continue; /* Loop/switch isn't completed */
            BackStackRecord backstackrecord = (BackStackRecord)mBackStack.get(l);
            if(s != null && s.equals(backstackrecord.getName()) || i >= 0 && i == backstackrecord.mIndex)
                continue; /* Loop/switch isn't completed */
            l--;
        } while(true);
        if(k == mBackStack.size() - 1) goto _L1; else goto _L7
_L7:
        i = mBackStack.size() - 1;
        while(i > k) 
        {
            arraylist.add(mBackStack.remove(i));
            arraylist1.add(Boolean.valueOf(true));
            i--;
        }
          goto _L8
    }

    public void putFragment(Bundle bundle, String s, Fragment fragment)
    {
        if(fragment.mIndex < 0)
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not currently in the FragmentManager").toString()));
        bundle.putInt(s, fragment.mIndex);
    }

    public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentlifecyclecallbacks, boolean flag)
    {
        mLifecycleCallbacks.add(new Pair(fragmentlifecyclecallbacks, Boolean.valueOf(flag)));
    }

    public void removeFragment(Fragment fragment)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("remove: ").append(fragment).append(" nesting=").append(fragment.mBackStackNesting).toString());
        boolean flag;
        if(!fragment.isInBackStack())
            flag = true;
        else
            flag = false;
        if(!fragment.mDetached || flag)
        {
            synchronized(mAdded)
            {
                mAdded.remove(fragment);
            }
            if(fragment.mHasMenu && fragment.mMenuVisible)
                mNeedMenuInvalidate = true;
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
        return;
        fragment;
        arraylist;
        JVM INSTR monitorexit ;
        throw fragment;
    }

    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if(mBackStackChangeListeners != null)
            mBackStackChangeListeners.remove(onbackstackchangedlistener);
    }

    void reportBackStackChanged()
    {
        if(mBackStackChangeListeners != null)
        {
            for(int i = 0; i < mBackStackChangeListeners.size(); i++)
                ((FragmentManager.OnBackStackChangedListener)mBackStackChangeListeners.get(i)).onBackStackChanged();

        }
    }

    void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentmanagernonconfig)
    {
        FragmentManagerState fragmentmanagerstate;
        while(parcelable == null || (fragmentmanagerstate = (FragmentManagerState)parcelable).mActive == null) 
            return;
        parcelable = null;
        if(fragmentmanagernonconfig != null)
        {
            List list1 = fragmentmanagernonconfig.getFragments();
            List list = fragmentmanagernonconfig.getChildNonConfigs();
            int i;
            int j1;
            if(list1 != null)
                i = list1.size();
            else
                i = 0;
            j1 = 0;
            do
            {
                parcelable = list;
                if(j1 >= i)
                    break;
                parcelable = (Fragment)list1.get(j1);
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: re-attaching retained ").append(parcelable).toString());
                int l1;
                for(l1 = 0; l1 < fragmentmanagerstate.mActive.length && fragmentmanagerstate.mActive[l1].mIndex != ((Fragment) (parcelable)).mIndex; l1++);
                if(l1 == fragmentmanagerstate.mActive.length)
                    throwException(new IllegalStateException((new StringBuilder()).append("Could not find active fragment with index ").append(((Fragment) (parcelable)).mIndex).toString()));
                FragmentState fragmentstate = fragmentmanagerstate.mActive[l1];
                fragmentstate.mInstance = parcelable;
                parcelable.mSavedViewState = null;
                parcelable.mBackStackNesting = 0;
                parcelable.mInLayout = false;
                parcelable.mAdded = false;
                parcelable.mTarget = null;
                if(fragmentstate.mSavedFragmentState != null)
                {
                    fragmentstate.mSavedFragmentState.setClassLoader(mHost.getContext().getClassLoader());
                    parcelable.mSavedViewState = fragmentstate.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                    parcelable.mSavedFragmentState = fragmentstate.mSavedFragmentState;
                }
                j1++;
            } while(true);
        }
        mActive = new SparseArray(fragmentmanagerstate.mActive.length);
        for(int j = 0; j < fragmentmanagerstate.mActive.length; j++)
        {
            FragmentState fragmentstate1 = fragmentmanagerstate.mActive[j];
            if(fragmentstate1 == null)
                continue;
            Object obj1 = null;
            Object obj = obj1;
            if(parcelable != null)
            {
                obj = obj1;
                if(j < parcelable.size())
                    obj = (FragmentManagerNonConfig)parcelable.get(j);
            }
            obj = fragmentstate1.instantiate(mHost, mContainer, mParent, ((FragmentManagerNonConfig) (obj)));
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: active #").append(j).append(": ").append(obj).toString());
            mActive.put(((Fragment) (obj)).mIndex, obj);
            fragmentstate1.mInstance = null;
        }

        if(fragmentmanagernonconfig != null)
        {
            parcelable = fragmentmanagernonconfig.getFragments();
            int k;
            int k1;
            if(parcelable != null)
                k = parcelable.size();
            else
                k = 0;
            for(k1 = 0; k1 < k; k1++)
            {
                fragmentmanagernonconfig = (Fragment)parcelable.get(k1);
                if(((Fragment) (fragmentmanagernonconfig)).mTargetIndex < 0)
                    continue;
                fragmentmanagernonconfig.mTarget = (Fragment)mActive.get(((Fragment) (fragmentmanagernonconfig)).mTargetIndex);
                if(((Fragment) (fragmentmanagernonconfig)).mTarget == null)
                    Log.w("FragmentManager", (new StringBuilder()).append("Re-attaching retained fragment ").append(fragmentmanagernonconfig).append(" target no longer exists: ").append(((Fragment) (fragmentmanagernonconfig)).mTargetIndex).toString());
            }

        }
        mAdded.clear();
        if(fragmentmanagerstate.mAdded != null)
        {
            for(int l = 0; l < fragmentmanagerstate.mAdded.length; l++)
            {
                fragmentmanagernonconfig = (Fragment)mActive.get(fragmentmanagerstate.mAdded[l]);
                if(fragmentmanagernonconfig == null)
                    throwException(new IllegalStateException((new StringBuilder()).append("No instantiated fragment for index #").append(fragmentmanagerstate.mAdded[l]).toString()));
                fragmentmanagernonconfig.mAdded = true;
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: added #").append(l).append(": ").append(fragmentmanagernonconfig).toString());
                if(mAdded.contains(fragmentmanagernonconfig))
                    throw new IllegalStateException("Already added!");
                synchronized(mAdded)
                {
                    mAdded.add(fragmentmanagernonconfig);
                }
            }

        }
        break MISSING_BLOCK_LABEL_779;
        fragmentmanagernonconfig;
        parcelable;
        JVM INSTR monitorexit ;
        throw fragmentmanagernonconfig;
        if(fragmentmanagerstate.mBackStack != null)
        {
            mBackStack = new ArrayList(fragmentmanagerstate.mBackStack.length);
            for(int i1 = 0; i1 < fragmentmanagerstate.mBackStack.length; i1++)
            {
                parcelable = fragmentmanagerstate.mBackStack[i1].instantiate(this);
                if(DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: back stack #").append(i1).append(" (index ").append(((BackStackRecord) (parcelable)).mIndex).append("): ").append(parcelable).toString());
                    fragmentmanagernonconfig = new PrintWriter(new LogWriter("FragmentManager"));
                    parcelable.dump("  ", fragmentmanagernonconfig, false);
                    fragmentmanagernonconfig.close();
                }
                mBackStack.add(parcelable);
                if(((BackStackRecord) (parcelable)).mIndex >= 0)
                    setBackStackIndex(((BackStackRecord) (parcelable)).mIndex, parcelable);
            }

        } else
        {
            mBackStack = null;
        }
        if(fragmentmanagerstate.mPrimaryNavActiveIndex >= 0)
            mPrimaryNav = (Fragment)mActive.get(fragmentmanagerstate.mPrimaryNavActiveIndex);
        mNextFragmentIndex = fragmentmanagerstate.mNextFragmentIndex;
        return;
    }

    FragmentManagerNonConfig retainNonConfig()
    {
        setRetaining(mSavedNonConfig);
        return mSavedNonConfig;
    }

    Parcelable saveAllState()
    {
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions();
        mStateSaved = true;
        mSavedNonConfig = null;
        if(mActive != null && mActive.size() > 0)
        {
            int j1 = mActive.size();
            FragmentState afragmentstate[] = new FragmentState[j1];
            boolean flag = false;
            int i = 0;
            while(i < j1) 
            {
                Fragment fragment = (Fragment)mActive.valueAt(i);
                if(fragment == null)
                    continue;
                if(fragment.mIndex < 0)
                    throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: active ").append(fragment).append(" has cleared index: ").append(fragment.mIndex).toString()));
                boolean flag1 = true;
                FragmentState fragmentstate = new FragmentState(fragment);
                afragmentstate[i] = fragmentstate;
                if(fragment.mState > 0 && fragmentstate.mSavedFragmentState == null)
                {
                    fragmentstate.mSavedFragmentState = saveFragmentBasicState(fragment);
                    if(fragment.mTarget != null)
                    {
                        if(fragment.mTarget.mIndex < 0)
                            throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: ").append(fragment).append(" has target not in fragment manager: ").append(fragment.mTarget).toString()));
                        if(fragmentstate.mSavedFragmentState == null)
                            fragmentstate.mSavedFragmentState = new Bundle();
                        putFragment(fragmentstate.mSavedFragmentState, "android:target_state", fragment.mTarget);
                        if(fragment.mTargetRequestCode != 0)
                            fragmentstate.mSavedFragmentState.putInt("android:target_req_state", fragment.mTargetRequestCode);
                    }
                } else
                {
                    fragmentstate.mSavedFragmentState = fragment.mSavedFragmentState;
                }
                flag = flag1;
                if(DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder()).append("Saved state of ").append(fragment).append(": ").append(fragmentstate.mSavedFragmentState).toString());
                    flag = flag1;
                }
                i++;
            }
            if(!flag)
            {
                if(DEBUG)
                {
                    Log.v("FragmentManager", "saveAllState: no fragments!");
                    return null;
                }
            } else
            {
                int ai[] = null;
                FragmentManagerState fragmentmanagerstate = null;
                int l = mAdded.size();
                if(l > 0)
                {
                    int ai1[] = new int[l];
                    int j = 0;
                    do
                    {
                        ai = ai1;
                        if(j >= l)
                            break;
                        ai1[j] = ((Fragment)mAdded.get(j)).mIndex;
                        if(ai1[j] < 0)
                            throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: active ").append(mAdded.get(j)).append(" has cleared index: ").append(ai1[j]).toString()));
                        if(DEBUG)
                            Log.v("FragmentManager", (new StringBuilder()).append("saveAllState: adding fragment #").append(j).append(": ").append(mAdded.get(j)).toString());
                        j++;
                    } while(true);
                }
                BackStackState abackstackstate[] = fragmentmanagerstate;
                if(mBackStack != null)
                {
                    int i1 = mBackStack.size();
                    abackstackstate = fragmentmanagerstate;
                    if(i1 > 0)
                    {
                        BackStackState abackstackstate1[] = new BackStackState[i1];
                        int k = 0;
                        do
                        {
                            abackstackstate = abackstackstate1;
                            if(k >= i1)
                                break;
                            abackstackstate1[k] = new BackStackState((BackStackRecord)mBackStack.get(k));
                            if(DEBUG)
                                Log.v("FragmentManager", (new StringBuilder()).append("saveAllState: adding back stack #").append(k).append(": ").append(mBackStack.get(k)).toString());
                            k++;
                        } while(true);
                    }
                }
                abackstackstate1 = new FragmentManagerState();
                abackstackstate1.mActive = afragmentstate;
                abackstackstate1.mAdded = ai;
                abackstackstate1.mBackStack = abackstackstate;
                if(mPrimaryNav != null)
                    abackstackstate1.mPrimaryNavActiveIndex = mPrimaryNav.mIndex;
                abackstackstate1.mNextFragmentIndex = mNextFragmentIndex;
                saveNonConfig();
                return abackstackstate1;
            }
        }
        return null;
    }

    Bundle saveFragmentBasicState(Fragment fragment)
    {
        Bundle bundle1 = null;
        if(mStateBundle == null)
            mStateBundle = new Bundle();
        fragment.performSaveInstanceState(mStateBundle);
        dispatchOnFragmentSaveInstanceState(fragment, mStateBundle, false);
        if(!mStateBundle.isEmpty())
        {
            bundle1 = mStateBundle;
            mStateBundle = null;
        }
        if(fragment.mView != null)
            saveFragmentViewState(fragment);
        Bundle bundle = bundle1;
        if(fragment.mSavedViewState != null)
        {
            bundle = bundle1;
            if(bundle1 == null)
                bundle = new Bundle();
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        bundle1 = bundle;
        if(!fragment.mUserVisibleHint)
        {
            bundle1 = bundle;
            if(bundle == null)
                bundle1 = new Bundle();
            bundle1.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle1;
    }

    public Fragment.SavedState saveFragmentInstanceState(Fragment fragment)
    {
        Object obj = null;
        if(fragment.mIndex < 0)
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not currently in the FragmentManager").toString()));
        Fragment.SavedState savedstate = obj;
        if(fragment.mState > 0)
        {
            fragment = saveFragmentBasicState(fragment);
            savedstate = obj;
            if(fragment != null)
                savedstate = new Fragment.SavedState(fragment);
        }
        return savedstate;
    }

    void saveFragmentViewState(Fragment fragment)
    {
        if(fragment.mInnerView != null)
        {
            if(mStateArray == null)
                mStateArray = new SparseArray();
            else
                mStateArray.clear();
            fragment.mInnerView.saveHierarchyState(mStateArray);
            if(mStateArray.size() > 0)
            {
                fragment.mSavedViewState = mStateArray;
                mStateArray = null;
                return;
            }
        }
    }

    void saveNonConfig()
    {
        ArrayList arraylist3 = null;
        ArrayList arraylist = null;
        ArrayList arraylist2 = null;
        ArrayList arraylist1 = null;
        if(mActive != null)
        {
            int i = 0;
            do
            {
                arraylist2 = arraylist1;
                arraylist3 = arraylist;
                if(i >= mActive.size())
                    break;
                Object obj = (Fragment)mActive.valueAt(i);
                arraylist3 = arraylist1;
                ArrayList arraylist4 = arraylist;
                if(obj != null)
                {
                    arraylist2 = arraylist;
                    if(((Fragment) (obj)).mRetainInstance)
                    {
                        arraylist3 = arraylist;
                        if(arraylist == null)
                            arraylist3 = new ArrayList();
                        arraylist3.add(obj);
                        int j;
                        if(((Fragment) (obj)).mTarget != null)
                            j = ((Fragment) (obj)).mTarget.mIndex;
                        else
                            j = -1;
                        obj.mTargetIndex = j;
                        arraylist2 = arraylist3;
                        if(DEBUG)
                        {
                            Log.v("FragmentManager", (new StringBuilder()).append("retainNonConfig: keeping retained ").append(obj).toString());
                            arraylist2 = arraylist3;
                        }
                    }
                    if(((Fragment) (obj)).mChildFragmentManager != null)
                    {
                        ((Fragment) (obj)).mChildFragmentManager.saveNonConfig();
                        obj = ((Fragment) (obj)).mChildFragmentManager.mSavedNonConfig;
                    } else
                    {
                        obj = ((Fragment) (obj)).mChildNonConfig;
                    }
                    arraylist = arraylist1;
                    if(arraylist1 == null)
                    {
                        arraylist = arraylist1;
                        if(obj != null)
                        {
                            arraylist1 = new ArrayList(mActive.size());
                            j = 0;
                            do
                            {
                                arraylist = arraylist1;
                                if(j >= i)
                                    break;
                                arraylist1.add(null);
                                j++;
                            } while(true);
                        }
                    }
                    arraylist3 = arraylist;
                    arraylist4 = arraylist2;
                    if(arraylist != null)
                    {
                        arraylist.add(obj);
                        arraylist4 = arraylist2;
                        arraylist3 = arraylist;
                    }
                }
                i++;
                arraylist1 = arraylist3;
                arraylist = arraylist4;
            } while(true);
        }
        if(arraylist3 == null && arraylist2 == null)
        {
            mSavedNonConfig = null;
            return;
        } else
        {
            mSavedNonConfig = new FragmentManagerNonConfig(arraylist3, arraylist2);
            return;
        }
    }

    public void setBackStackIndex(int i, BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        int k;
        if(mBackStackIndices == null)
            mBackStackIndices = new ArrayList();
        k = mBackStackIndices.size();
        int j = k;
        if(i >= k) goto _L2; else goto _L1
_L1:
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Setting back stack index ").append(i).append(" to ").append(backstackrecord).toString());
        mBackStackIndices.set(i, backstackrecord);
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        mBackStackIndices.add(null);
        if(mAvailBackStackIndices == null)
            mAvailBackStackIndices = new ArrayList();
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Adding available back stack index ").append(j).toString());
        mAvailBackStackIndices.add(Integer.valueOf(j));
        j++;
        if(true) goto _L2; else goto _L3
_L3:
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Adding back stack index ").append(i).append(" with ").append(backstackrecord).toString());
        mBackStackIndices.add(backstackrecord);
          goto _L4
        backstackrecord;
        this;
        JVM INSTR monitorexit ;
        throw backstackrecord;
    }

    public void setPrimaryNavigationFragment(Fragment fragment)
    {
        if(fragment != null && (mActive.get(fragment.mIndex) != fragment || fragment.mHost != null && fragment.getFragmentManager() != this))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not an active fragment of FragmentManager ").append(this).toString());
        } else
        {
            mPrimaryNav = fragment;
            return;
        }
    }

    public void showFragment(Fragment fragment)
    {
        boolean flag = false;
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("show: ").append(fragment).toString());
        if(fragment.mHidden)
        {
            fragment.mHidden = false;
            if(!fragment.mHiddenChanged)
                flag = true;
            fragment.mHiddenChanged = flag;
        }
    }

    void startPendingDeferredFragments()
    {
        if(mActive != null)
        {
            int i = 0;
            while(i < mActive.size()) 
            {
                Fragment fragment = (Fragment)mActive.valueAt(i);
                if(fragment != null)
                    performPendingDeferredStart(fragment);
                i++;
            }
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("FragmentManager{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" in ");
        if(mParent != null)
            DebugUtils.buildShortClassTag(mParent, stringbuilder);
        else
            DebugUtils.buildShortClassTag(mHost, stringbuilder);
        stringbuilder.append("}}");
        return stringbuilder.toString();
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentlifecyclecallbacks)
    {
        CopyOnWriteArrayList copyonwritearraylist = mLifecycleCallbacks;
        copyonwritearraylist;
        JVM INSTR monitorenter ;
        int i = 0;
        int j = mLifecycleCallbacks.size();
_L2:
        if(i >= j)
            break MISSING_BLOCK_LABEL_51;
        if(((Pair)mLifecycleCallbacks.get(i)).first != fragmentlifecyclecallbacks)
            break MISSING_BLOCK_LABEL_61;
        mLifecycleCallbacks.remove(i);
        copyonwritearraylist;
        JVM INSTR monitorexit ;
        return;
        fragmentlifecyclecallbacks;
        copyonwritearraylist;
        JVM INSTR monitorexit ;
        throw fragmentlifecyclecallbacks;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5F);
    static final Interpolator ACCELERATE_QUINT = new AccelerateInterpolator(2.5F);
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC = new DecelerateInterpolator(1.5F);
    static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5F);
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    static Field sAnimationListenerField = null;
    SparseArray mActive;
    final ArrayList mAdded = new ArrayList();
    ArrayList mAvailBackStackIndices;
    ArrayList mBackStack;
    ArrayList mBackStackChangeListeners;
    ArrayList mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    private final CopyOnWriteArrayList mLifecycleCallbacks = new CopyOnWriteArrayList();
    boolean mNeedMenuInvalidate;
    int mNextFragmentIndex;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList mPendingActions;
    ArrayList mPostponedTransactions;
    Fragment mPrimaryNav;
    FragmentManagerNonConfig mSavedNonConfig;
    SparseArray mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    ArrayList mTmpAddedFragments;
    ArrayList mTmpIsPop;
    ArrayList mTmpRecords;

    static 
    {
        DEBUG = false;
    }


}

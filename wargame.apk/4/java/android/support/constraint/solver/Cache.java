// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.constraint.solver;


// Referenced classes of package android.support.constraint.solver:
//            SolverVariable

public class Cache
{

    public Cache()
    {
        arrayRowPool = new Pools.SimplePool(256);
        solverVariablePool = new Pools.SimplePool(256);
        mIndexedVariables = new SolverVariable[32];
    }

    Pools.Pool arrayRowPool;
    SolverVariable mIndexedVariables[];
    Pools.Pool solverVariablePool;
}

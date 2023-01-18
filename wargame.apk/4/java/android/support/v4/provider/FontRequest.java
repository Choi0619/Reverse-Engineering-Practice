// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.provider;

import android.support.v4.util.Preconditions;
import android.util.Base64;
import java.util.List;

public final class FontRequest
{

    public FontRequest(String s, String s1, String s2, int i)
    {
        mProviderAuthority = (String)Preconditions.checkNotNull(s);
        mProviderPackage = (String)Preconditions.checkNotNull(s1);
        mQuery = (String)Preconditions.checkNotNull(s2);
        mCertificates = null;
        boolean flag;
        if(i != 0)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag);
        mCertificatesArray = i;
        mIdentifier = (new StringBuilder(mProviderAuthority)).append("-").append(mProviderPackage).append("-").append(mQuery).toString();
    }

    public FontRequest(String s, String s1, String s2, List list)
    {
        mProviderAuthority = (String)Preconditions.checkNotNull(s);
        mProviderPackage = (String)Preconditions.checkNotNull(s1);
        mQuery = (String)Preconditions.checkNotNull(s2);
        mCertificates = (List)Preconditions.checkNotNull(list);
        mCertificatesArray = 0;
        mIdentifier = (new StringBuilder(mProviderAuthority)).append("-").append(mProviderPackage).append("-").append(mQuery).toString();
    }

    public List getCertificates()
    {
        return mCertificates;
    }

    public int getCertificatesArrayResId()
    {
        return mCertificatesArray;
    }

    public String getIdentifier()
    {
        return mIdentifier;
    }

    public String getProviderAuthority()
    {
        return mProviderAuthority;
    }

    public String getProviderPackage()
    {
        return mProviderPackage;
    }

    public String getQuery()
    {
        return mQuery;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append((new StringBuilder()).append("FontRequest {mProviderAuthority: ").append(mProviderAuthority).append(", mProviderPackage: ").append(mProviderPackage).append(", mQuery: ").append(mQuery).append(", mCertificates:").toString());
        for(int i = 0; i < mCertificates.size(); i++)
        {
            stringbuilder.append(" [");
            List list = (List)mCertificates.get(i);
            for(int j = 0; j < list.size(); j++)
            {
                stringbuilder.append(" \"");
                stringbuilder.append(Base64.encodeToString((byte[])list.get(j), 0));
                stringbuilder.append("\"");
            }

            stringbuilder.append(" ]");
        }

        stringbuilder.append("}");
        stringbuilder.append((new StringBuilder()).append("mCertificatesArray: ").append(mCertificatesArray).toString());
        return stringbuilder.toString();
    }

    private final List mCertificates;
    private final int mCertificatesArray;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;
}

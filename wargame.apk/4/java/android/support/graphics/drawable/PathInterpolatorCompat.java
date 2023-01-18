// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;

// Referenced classes of package android.support.graphics.drawable:
//            AndroidResources

public class PathInterpolatorCompat
    implements Interpolator
{

    public PathInterpolatorCompat(Context context, AttributeSet attributeset, XmlPullParser xmlpullparser)
    {
        this(context.getResources(), context.getTheme(), attributeset, xmlpullparser);
    }

    public PathInterpolatorCompat(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, XmlPullParser xmlpullparser)
    {
        resources = TypedArrayUtils.obtainAttributes(resources, theme, attributeset, AndroidResources.STYLEABLE_PATH_INTERPOLATOR);
        parseInterpolatorFromTypeArray(resources, xmlpullparser);
        resources.recycle();
    }

    private void initCubic(float f, float f1, float f2, float f3)
    {
        Path path = new Path();
        path.moveTo(0.0F, 0.0F);
        path.cubicTo(f, f1, f2, f3, 1.0F, 1.0F);
        initPath(path);
    }

    private void initPath(Path path)
    {
        path = new PathMeasure(path, false);
        float f = path.getLength();
        int l = Math.min(3000, (int)(f / 0.002F) + 1);
        if(l <= 0)
            throw new IllegalArgumentException((new StringBuilder()).append("The Path has a invalid length ").append(f).toString());
        mX = new float[l];
        mY = new float[l];
        float af[] = new float[2];
        for(int i = 0; i < l; i++)
        {
            path.getPosTan(((float)i * f) / (float)(l - 1), af, null);
            mX[i] = af[0];
            mY[i] = af[1];
        }

        if((double)Math.abs(mX[0]) > 1.0000000000000001E-005D || (double)Math.abs(mY[0]) > 1.0000000000000001E-005D || (double)Math.abs(mX[l - 1] - 1.0F) > 1.0000000000000001E-005D || (double)Math.abs(mY[l - 1] - 1.0F) > 1.0000000000000001E-005D)
            throw new IllegalArgumentException((new StringBuilder()).append("The Path must start at (0,0) and end at (1,1) start: ").append(mX[0]).append(",").append(mY[0]).append(" end:").append(mX[l - 1]).append(",").append(mY[l - 1]).toString());
        f = 0.0F;
        int k = 0;
        for(int j = 0; k < l; j++)
        {
            float f1 = mX[j];
            if(f1 < f)
                throw new IllegalArgumentException((new StringBuilder()).append("The Path cannot loop back on itself, x :").append(f1).toString());
            mX[k] = f1;
            f = f1;
            k++;
        }

        if(path.nextContour())
            throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
        else
            return;
    }

    private void initQuad(float f, float f1)
    {
        Path path = new Path();
        path.moveTo(0.0F, 0.0F);
        path.quadTo(f, f1, 1.0F, 1.0F);
        initPath(path);
    }

    private void parseInterpolatorFromTypeArray(TypedArray typedarray, XmlPullParser xmlpullparser)
    {
        if(TypedArrayUtils.hasAttribute(xmlpullparser, "pathData"))
        {
            typedarray = TypedArrayUtils.getNamedString(typedarray, xmlpullparser, "pathData", 4);
            xmlpullparser = PathParser.createPathFromPathData(typedarray);
            if(xmlpullparser == null)
            {
                throw new InflateException((new StringBuilder()).append("The path is null, which is created from ").append(typedarray).toString());
            } else
            {
                initPath(xmlpullparser);
                return;
            }
        }
        if(!TypedArrayUtils.hasAttribute(xmlpullparser, "controlX1"))
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        if(!TypedArrayUtils.hasAttribute(xmlpullparser, "controlY1"))
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        float f = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "controlX1", 0, 0.0F);
        float f1 = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "controlY1", 1, 0.0F);
        boolean flag = TypedArrayUtils.hasAttribute(xmlpullparser, "controlX2");
        if(flag != TypedArrayUtils.hasAttribute(xmlpullparser, "controlY2"))
            throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
        if(!flag)
        {
            initQuad(f, f1);
            return;
        } else
        {
            initCubic(f, f1, TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "controlX2", 2, 0.0F), TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "controlY2", 3, 0.0F));
            return;
        }
    }

    public float getInterpolation(float f)
    {
        if(f <= 0.0F)
            return 0.0F;
        if(f >= 1.0F)
            return 1.0F;
        int i = 0;
        int j;
        for(j = mX.length - 1; j - i > 1;)
        {
            int k = (i + j) / 2;
            if(f < mX[k])
                j = k;
            else
                i = k;
        }

        float f1 = mX[j] - mX[i];
        if(f1 == 0.0F)
        {
            return mY[i];
        } else
        {
            f = (f - mX[i]) / f1;
            f1 = mY[i];
            return (mY[j] - f1) * f + f1;
        }
    }

    public static final double EPSILON = 1.0000000000000001E-005D;
    public static final int MAX_NUM_POINTS = 3000;
    private static final float PRECISION = 0.002F;
    private float mX[];
    private float mY[];
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.graphics.drawable;

import android.animation.*;
import android.content.Context;
import android.content.res.*;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.*;
import android.view.InflateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.graphics.drawable:
//            AndroidResources, ArgbEvaluator, AnimationUtilsCompat

public class AnimatorInflaterCompat
{
    private static class PathDataEvaluator
        implements TypeEvaluator
    {

        public volatile Object evaluate(float f, Object obj, Object obj1)
        {
            return evaluate(f, (android.support.v4.graphics.PathParser.PathDataNode[])obj, (android.support.v4.graphics.PathParser.PathDataNode[])obj1);
        }

        public android.support.v4.graphics.PathParser.PathDataNode[] evaluate(float f, android.support.v4.graphics.PathParser.PathDataNode apathdatanode[], android.support.v4.graphics.PathParser.PathDataNode apathdatanode1[])
        {
            if(!PathParser.canMorph(apathdatanode, apathdatanode1))
                throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
            if(mNodeArray == null || !PathParser.canMorph(mNodeArray, apathdatanode))
                mNodeArray = PathParser.deepCopyNodes(apathdatanode);
            for(int i = 0; i < apathdatanode.length; i++)
                mNodeArray[i].interpolatePathDataNode(apathdatanode[i], apathdatanode1[i], f);

            return mNodeArray;
        }

        private android.support.v4.graphics.PathParser.PathDataNode mNodeArray[];

        private PathDataEvaluator()
        {
        }


        PathDataEvaluator(android.support.v4.graphics.PathParser.PathDataNode apathdatanode[])
        {
            mNodeArray = apathdatanode;
        }
    }


    public AnimatorInflaterCompat()
    {
    }

    private static Animator createAnimatorFromXml(Context context, Resources resources, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser, float f)
        throws XmlPullParserException, IOException
    {
        return createAnimatorFromXml(context, resources, theme, xmlpullparser, Xml.asAttributeSet(xmlpullparser), null, 0, f);
    }

    private static Animator createAnimatorFromXml(Context context, Resources resources, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser, AttributeSet attributeset, AnimatorSet animatorset, int i, float f)
        throws XmlPullParserException, IOException
    {
        Object obj1 = null;
        ArrayList arraylist = null;
        int l = xmlpullparser.getDepth();
        do
        {
label0:
            {
                int j = xmlpullparser.next();
                if((j != 3 || xmlpullparser.getDepth() > l) && j != 1)
                {
                    if(j == 2)
                    {
                        Object obj = xmlpullparser.getName();
                        boolean flag = false;
                        if(((String) (obj)).equals("objectAnimator"))
                            obj = loadObjectAnimator(context, resources, theme, attributeset, f, xmlpullparser);
                        else
                        if(((String) (obj)).equals("animator"))
                            obj = loadAnimator(context, resources, theme, attributeset, null, f, xmlpullparser);
                        else
                        if(((String) (obj)).equals("set"))
                        {
                            obj = new AnimatorSet();
                            obj1 = TypedArrayUtils.obtainAttributes(resources, theme, attributeset, AndroidResources.STYLEABLE_ANIMATOR_SET);
                            int i1 = TypedArrayUtils.getNamedInt(((TypedArray) (obj1)), xmlpullparser, "ordering", 0, 0);
                            createAnimatorFromXml(context, resources, theme, xmlpullparser, attributeset, (AnimatorSet)obj, i1, f);
                            ((TypedArray) (obj1)).recycle();
                        } else
                        if(((String) (obj)).equals("propertyValuesHolder"))
                        {
                            obj = loadValues(context, resources, theme, xmlpullparser, Xml.asAttributeSet(xmlpullparser));
                            if(obj != null && obj1 != null && (obj1 instanceof ValueAnimator))
                                ((ValueAnimator)obj1).setValues(((PropertyValuesHolder []) (obj)));
                            flag = true;
                            obj = obj1;
                        } else
                        {
                            throw new RuntimeException((new StringBuilder()).append("Unknown animator name: ").append(xmlpullparser.getName()).toString());
                        }
                        obj1 = obj;
                        if(animatorset != null)
                        {
                            obj1 = obj;
                            if(!flag)
                            {
                                ArrayList arraylist1 = arraylist;
                                if(arraylist == null)
                                    arraylist1 = new ArrayList();
                                arraylist1.add(obj);
                                obj1 = obj;
                                arraylist = arraylist1;
                            }
                        }
                    }
                    continue;
                }
                if(animatorset != null && arraylist != null)
                {
                    context = new Animator[arraylist.size()];
                    int k = 0;
                    for(resources = arraylist.iterator(); resources.hasNext();)
                    {
                        context[k] = (Animator)resources.next();
                        k++;
                    }

                    if(i != 0)
                        break label0;
                    animatorset.playTogether(context);
                }
                return ((Animator) (obj1));
            }
            animatorset.playSequentially(context);
            return ((Animator) (obj1));
        } while(true);
    }

    private static Keyframe createNewKeyframe(Keyframe keyframe, float f)
    {
        if(keyframe.getType() == Float.TYPE)
            return Keyframe.ofFloat(f);
        if(keyframe.getType() == Integer.TYPE)
            return Keyframe.ofInt(f);
        else
            return Keyframe.ofObject(f);
    }

    private static void distributeKeyframes(Keyframe akeyframe[], float f, int i, int j)
    {
        f /= (j - i) + 2;
        for(; i <= j; i++)
            akeyframe[i].setFraction(akeyframe[i - 1].getFraction() + f);

    }

    private static void dumpKeyframes(Object aobj[], String s)
    {
        if(aobj != null && aobj.length != 0)
        {
            Log.d("AnimatorInflater", s);
            int j = aobj.length;
            int i = 0;
            while(i < j) 
            {
                Keyframe keyframe = (Keyframe)aobj[i];
                StringBuilder stringbuilder = (new StringBuilder()).append("Keyframe ").append(i).append(": fraction ");
                if(keyframe.getFraction() < 0.0F)
                    s = "null";
                else
                    s = Float.valueOf(keyframe.getFraction());
                stringbuilder = stringbuilder.append(s).append(", ").append(", value : ");
                if(keyframe.hasValue())
                    s = ((String) (keyframe.getValue()));
                else
                    s = "null";
                Log.d("AnimatorInflater", stringbuilder.append(s).toString());
                i++;
            }
        }
    }

    private static PropertyValuesHolder getPVH(TypedArray typedarray, int i, int j, int k, String s)
    {
        int l;
        boolean flag;
        boolean flag1;
        int i1;
        int j1;
        Object obj;
        String s2;
        android.support.v4.graphics.PathParser.PathDataNode apathdatanode[];
        android.support.v4.graphics.PathParser.PathDataNode apathdatanode1[];
        obj = typedarray.peekValue(j);
        String s1;
        if(obj != null)
            flag = true;
        else
            flag = false;
        if(flag)
            i1 = ((TypedValue) (obj)).type;
        else
            i1 = 0;
        obj = typedarray.peekValue(k);
        if(obj != null)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            j1 = ((TypedValue) (obj)).type;
        else
            j1 = 0;
        l = i;
        if(i == 4)
            if(flag && isColorType(i1) || flag1 && isColorType(j1))
                l = 3;
            else
                l = 0;
        if(l == 0)
            i = 1;
        else
            i = 0;
        s2 = null;
        obj = null;
        if(l != 2) goto _L2; else goto _L1
_L1:
        s1 = typedarray.getString(j);
        s2 = typedarray.getString(k);
        apathdatanode = PathParser.createNodesFromPathData(s1);
        apathdatanode1 = PathParser.createNodesFromPathData(s2);
        if(apathdatanode != null) goto _L4; else goto _L3
_L3:
        typedarray = ((TypedArray) (obj));
        if(apathdatanode1 == null) goto _L5; else goto _L4
_L4:
        if(apathdatanode == null) goto _L7; else goto _L6
_L6:
        typedarray = new PathDataEvaluator();
        if(apathdatanode1 == null) goto _L9; else goto _L8
_L8:
        if(!PathParser.canMorph(apathdatanode, apathdatanode1))
            throw new InflateException((new StringBuilder()).append(" Can't morph from ").append(s1).append(" to ").append(s2).toString());
        typedarray = PropertyValuesHolder.ofObject(s, typedarray, new Object[] {
            apathdatanode, apathdatanode1
        });
_L5:
        return typedarray;
_L9:
        return PropertyValuesHolder.ofObject(s, typedarray, new Object[] {
            apathdatanode
        });
_L7:
        typedarray = ((TypedArray) (obj));
        if(apathdatanode1 != null)
            return PropertyValuesHolder.ofObject(s, new PathDataEvaluator(), new Object[] {
                apathdatanode1
            });
        continue; /* Loop/switch isn't completed */
_L2:
        ArgbEvaluator argbevaluator = null;
        if(l == 3)
            argbevaluator = ArgbEvaluator.getInstance();
        if(i == 0)
            break; /* Loop/switch isn't completed */
        if(flag)
        {
            float f;
            if(i1 == 5)
                f = typedarray.getDimension(j, 0.0F);
            else
                f = typedarray.getFloat(j, 0.0F);
            if(flag1)
            {
                float f2;
                if(j1 == 5)
                    f2 = typedarray.getDimension(k, 0.0F);
                else
                    f2 = typedarray.getFloat(k, 0.0F);
                obj = PropertyValuesHolder.ofFloat(s, new float[] {
                    f, f2
                });
            } else
            {
                obj = PropertyValuesHolder.ofFloat(s, new float[] {
                    f
                });
            }
        } else
        {
            float f1;
            if(j1 == 5)
                f1 = typedarray.getDimension(k, 0.0F);
            else
                f1 = typedarray.getFloat(k, 0.0F);
            obj = PropertyValuesHolder.ofFloat(s, new float[] {
                f1
            });
        }
_L11:
        typedarray = ((TypedArray) (obj));
        if(obj != null)
        {
            typedarray = ((TypedArray) (obj));
            if(argbevaluator != null)
            {
                ((PropertyValuesHolder) (obj)).setEvaluator(argbevaluator);
                return ((PropertyValuesHolder) (obj));
            }
        }
        if(true) goto _L5; else goto _L10
_L10:
        if(flag)
        {
            if(i1 == 5)
                i = (int)typedarray.getDimension(j, 0.0F);
            else
            if(isColorType(i1))
                i = typedarray.getColor(j, 0);
            else
                i = typedarray.getInt(j, 0);
            if(flag1)
            {
                if(j1 == 5)
                    j = (int)typedarray.getDimension(k, 0.0F);
                else
                if(isColorType(j1))
                    j = typedarray.getColor(k, 0);
                else
                    j = typedarray.getInt(k, 0);
                obj = PropertyValuesHolder.ofInt(s, new int[] {
                    i, j
                });
            } else
            {
                obj = PropertyValuesHolder.ofInt(s, new int[] {
                    i
                });
            }
        } else
        {
            obj = s2;
            if(flag1)
            {
                if(j1 == 5)
                    i = (int)typedarray.getDimension(k, 0.0F);
                else
                if(isColorType(j1))
                    i = typedarray.getColor(k, 0);
                else
                    i = typedarray.getInt(k, 0);
                obj = PropertyValuesHolder.ofInt(s, new int[] {
                    i
                });
            }
        }
          goto _L11
        if(true) goto _L5; else goto _L12
_L12:
    }

    private static int inferValueTypeFromValues(TypedArray typedarray, int i, int j)
    {
        int l = 1;
        TypedValue typedvalue = typedarray.peekValue(i);
        int k;
        if(typedvalue != null)
            i = 1;
        else
            i = 0;
        if(i != 0)
            k = typedvalue.type;
        else
            k = 0;
        typedarray = typedarray.peekValue(j);
        if(typedarray != null)
            j = l;
        else
            j = 0;
        if(j != 0)
            l = ((TypedValue) (typedarray)).type;
        else
            l = 0;
        return (i == 0 || !isColorType(k)) && (j == 0 || !isColorType(l)) ? 0 : 3;
    }

    private static int inferValueTypeOfKeyframe(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, XmlPullParser xmlpullparser)
    {
        byte byte0 = 0;
        resources = TypedArrayUtils.obtainAttributes(resources, theme, attributeset, AndroidResources.STYLEABLE_KEYFRAME);
        theme = TypedArrayUtils.peekNamedValue(resources, xmlpullparser, "value", 0);
        if(theme != null)
            byte0 = 1;
        if(byte0 && isColorType(((TypedValue) (theme)).type))
            byte0 = 3;
        else
            byte0 = 0;
        resources.recycle();
        return byte0;
    }

    private static boolean isColorType(int i)
    {
        return i >= 28 && i <= 31;
    }

    public static Animator loadAnimator(Context context, int i)
        throws android.content.res.Resources.NotFoundException
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return AnimatorInflater.loadAnimator(context, i);
        else
            return loadAnimator(context, context.getResources(), context.getTheme(), i);
    }

    public static Animator loadAnimator(Context context, Resources resources, android.content.res.Resources.Theme theme, int i)
        throws android.content.res.Resources.NotFoundException
    {
        return loadAnimator(context, resources, theme, i, 1.0F);
    }

    public static Animator loadAnimator(Context context, Resources resources, android.content.res.Resources.Theme theme, int i, float f)
        throws android.content.res.Resources.NotFoundException
    {
        XmlResourceParser xmlresourceparser;
        XmlResourceParser xmlresourceparser1;
        XmlResourceParser xmlresourceparser2;
        xmlresourceparser = null;
        xmlresourceparser2 = null;
        xmlresourceparser1 = null;
        XmlResourceParser xmlresourceparser3 = resources.getAnimation(i);
        xmlresourceparser1 = xmlresourceparser3;
        xmlresourceparser = xmlresourceparser3;
        xmlresourceparser2 = xmlresourceparser3;
        context = createAnimatorFromXml(context, resources, theme, xmlresourceparser3, f);
        if(xmlresourceparser3 != null)
            xmlresourceparser3.close();
        return context;
        context;
        xmlresourceparser = xmlresourceparser1;
        resources = new android.content.res.Resources.NotFoundException((new StringBuilder()).append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        xmlresourceparser = xmlresourceparser1;
        resources.initCause(context);
        xmlresourceparser = xmlresourceparser1;
        throw resources;
        context;
        if(xmlresourceparser != null)
            xmlresourceparser.close();
        throw context;
        context;
        xmlresourceparser = xmlresourceparser2;
        resources = new android.content.res.Resources.NotFoundException((new StringBuilder()).append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        xmlresourceparser = xmlresourceparser2;
        resources.initCause(context);
        xmlresourceparser = xmlresourceparser2;
        throw resources;
    }

    private static ValueAnimator loadAnimator(Context context, Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, ValueAnimator valueanimator, float f, XmlPullParser xmlpullparser)
        throws android.content.res.Resources.NotFoundException
    {
        TypedArray typedarray = TypedArrayUtils.obtainAttributes(resources, theme, attributeset, AndroidResources.STYLEABLE_ANIMATOR);
        theme = TypedArrayUtils.obtainAttributes(resources, theme, attributeset, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        resources = valueanimator;
        if(valueanimator == null)
            resources = new ValueAnimator();
        parseAnimatorFromTypeArray(resources, typedarray, theme, f, xmlpullparser);
        int i = TypedArrayUtils.getNamedResourceId(typedarray, xmlpullparser, "interpolator", 0, 0);
        if(i > 0)
            resources.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, i));
        typedarray.recycle();
        if(theme != null)
            theme.recycle();
        return resources;
    }

    private static Keyframe loadKeyframe(Context context, Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, int i, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        float f;
        int j;
        attributeset = TypedArrayUtils.obtainAttributes(resources, theme, attributeset, AndroidResources.STYLEABLE_KEYFRAME);
        theme = null;
        f = TypedArrayUtils.getNamedFloat(attributeset, xmlpullparser, "fraction", 3, -1F);
        resources = TypedArrayUtils.peekNamedValue(attributeset, xmlpullparser, "value", 0);
        boolean flag;
        if(resources != null)
            flag = true;
        else
            flag = false;
        j = i;
        if(i == 4)
            if(flag && isColorType(((TypedValue) (resources)).type))
                j = 3;
            else
                j = 0;
        if(!flag) goto _L2; else goto _L1
_L1:
        resources = theme;
        j;
        JVM INSTR tableswitch 0 3: default 112
    //                   0 160
    //                   1 180
    //                   2 114
    //                   3 180;
           goto _L3 _L4 _L5 _L6 _L5
_L6:
        break; /* Loop/switch isn't completed */
_L3:
        resources = theme;
_L8:
        i = TypedArrayUtils.getNamedResourceId(attributeset, xmlpullparser, "interpolator", 1, 0);
        if(i > 0)
            resources.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, i));
        attributeset.recycle();
        return resources;
_L4:
        resources = Keyframe.ofFloat(f, TypedArrayUtils.getNamedFloat(attributeset, xmlpullparser, "value", 0, 0.0F));
        continue; /* Loop/switch isn't completed */
_L5:
        resources = Keyframe.ofInt(f, TypedArrayUtils.getNamedInt(attributeset, xmlpullparser, "value", 0, 0));
        continue; /* Loop/switch isn't completed */
_L2:
        if(j == 0)
            resources = Keyframe.ofFloat(f);
        else
            resources = Keyframe.ofInt(f);
        if(true) goto _L8; else goto _L7
_L7:
    }

    private static ObjectAnimator loadObjectAnimator(Context context, Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, float f, XmlPullParser xmlpullparser)
        throws android.content.res.Resources.NotFoundException
    {
        ObjectAnimator objectanimator = new ObjectAnimator();
        loadAnimator(context, resources, theme, attributeset, objectanimator, f, xmlpullparser);
        return objectanimator;
    }

    private static PropertyValuesHolder loadPvh(Context context, Resources resources, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser, String s, int i)
        throws XmlPullParserException, IOException
    {
        int l;
        int i1;
        Object obj = null;
        ArrayList arraylist = null;
        l = i;
        do
        {
            i = xmlpullparser.next();
            if(i == 3 || i == 1)
                break;
            if(xmlpullparser.getName().equals("keyframe"))
            {
                i = l;
                if(l == 4)
                    i = inferValueTypeOfKeyframe(resources, theme, Xml.asAttributeSet(xmlpullparser), xmlpullparser);
                Keyframe keyframe = loadKeyframe(context, resources, theme, Xml.asAttributeSet(xmlpullparser), i, xmlpullparser);
                ArrayList arraylist1 = arraylist;
                if(keyframe != null)
                {
                    arraylist1 = arraylist;
                    if(arraylist == null)
                        arraylist1 = new ArrayList();
                    arraylist1.add(keyframe);
                }
                xmlpullparser.next();
                arraylist = arraylist1;
                l = i;
            }
        } while(true);
        context = obj;
        if(arraylist == null)
            break MISSING_BLOCK_LABEL_462;
        int j = arraylist.size();
        context = obj;
        if(j <= 0)
            break MISSING_BLOCK_LABEL_462;
        context = (Keyframe)arraylist.get(0);
        resources = (Keyframe)arraylist.get(j - 1);
        float f = resources.getFraction();
        i = j;
        if(f < 1.0F)
            if(f < 0.0F)
            {
                resources.setFraction(1.0F);
                i = j;
            } else
            {
                arraylist.add(arraylist.size(), createNewKeyframe(resources, 1.0F));
                i = j + 1;
            }
        f = context.getFraction();
        i1 = i;
        if(f != 0.0F)
            if(f < 0.0F)
            {
                context.setFraction(0.0F);
                i1 = i;
            } else
            {
                arraylist.add(0, createNewKeyframe(context, 0.0F));
                i1 = i + 1;
            }
        context = new Keyframe[i1];
        arraylist.toArray(context);
        i = 0;
        if(i >= i1)
            break; /* Loop/switch isn't completed */
        resources = context[i];
        if(resources.getFraction() < 0.0F)
            if(i == 0)
            {
                resources.setFraction(0.0F);
            } else
            {
label0:
                {
                    if(i != i1 - 1)
                        break label0;
                    resources.setFraction(1.0F);
                }
            }
_L5:
        i++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_263;
        int k;
        int j1;
        j1 = i;
        k = i + 1;
_L3:
label1:
        {
            if(k < i1 - 1 && context[k].getFraction() < 0.0F)
                break label1;
            distributeKeyframes(context, context[j1 + 1].getFraction() - context[i - 1].getFraction(), i, j1);
        }
        continue; /* Loop/switch isn't completed */
        j1 = k;
        k++;
        if(true) goto _L3; else goto _L1
_L1:
        resources = PropertyValuesHolder.ofKeyframe(s, context);
        context = resources;
        if(l == 3)
        {
            resources.setEvaluator(ArgbEvaluator.getInstance());
            context = resources;
        }
        return context;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static PropertyValuesHolder[] loadValues(Context context, Resources resources, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        Object obj = null;
        do
        {
            int i = xmlpullparser.getEventType();
            if(i == 3 || i == 1)
                break;
            if(i != 2)
            {
                xmlpullparser.next();
            } else
            {
                Object obj1 = obj;
                if(xmlpullparser.getName().equals("propertyValuesHolder"))
                {
                    TypedArray typedarray = TypedArrayUtils.obtainAttributes(resources, theme, attributeset, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
                    String s = TypedArrayUtils.getNamedString(typedarray, xmlpullparser, "propertyName", 3);
                    int j = TypedArrayUtils.getNamedInt(typedarray, xmlpullparser, "valueType", 2, 4);
                    obj1 = loadPvh(context, resources, theme, xmlpullparser, s, j);
                    Object obj2 = obj1;
                    if(obj1 == null)
                        obj2 = getPVH(typedarray, j, 0, 1, s);
                    obj1 = obj;
                    if(obj2 != null)
                    {
                        obj1 = obj;
                        if(obj == null)
                            obj1 = new ArrayList();
                        ((ArrayList) (obj1)).add(obj2);
                    }
                    typedarray.recycle();
                }
                xmlpullparser.next();
                obj = obj1;
            }
        } while(true);
        context = null;
        if(obj != null)
        {
            int l = ((ArrayList) (obj)).size();
            resources = new PropertyValuesHolder[l];
            int k = 0;
            do
            {
                context = resources;
                if(k >= l)
                    break;
                resources[k] = (PropertyValuesHolder)((ArrayList) (obj)).get(k);
                k++;
            } while(true);
        }
        return context;
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator valueanimator, TypedArray typedarray, TypedArray typedarray1, float f, XmlPullParser xmlpullparser)
    {
        long l = TypedArrayUtils.getNamedInt(typedarray, xmlpullparser, "duration", 1, 300);
        long l1 = TypedArrayUtils.getNamedInt(typedarray, xmlpullparser, "startOffset", 2, 0);
        int j = TypedArrayUtils.getNamedInt(typedarray, xmlpullparser, "valueType", 7, 4);
        int k = j;
        if(TypedArrayUtils.hasAttribute(xmlpullparser, "valueFrom"))
        {
            k = j;
            if(TypedArrayUtils.hasAttribute(xmlpullparser, "valueTo"))
            {
                int i = j;
                if(j == 4)
                    i = inferValueTypeFromValues(typedarray, 5, 6);
                PropertyValuesHolder propertyvaluesholder = getPVH(typedarray, i, 5, 6, "");
                k = i;
                if(propertyvaluesholder != null)
                {
                    valueanimator.setValues(new PropertyValuesHolder[] {
                        propertyvaluesholder
                    });
                    k = i;
                }
            }
        }
        valueanimator.setDuration(l);
        valueanimator.setStartDelay(l1);
        valueanimator.setRepeatCount(TypedArrayUtils.getNamedInt(typedarray, xmlpullparser, "repeatCount", 3, 0));
        valueanimator.setRepeatMode(TypedArrayUtils.getNamedInt(typedarray, xmlpullparser, "repeatMode", 4, 1));
        if(typedarray1 != null)
            setupObjectAnimator(valueanimator, typedarray1, k, f, xmlpullparser);
    }

    private static void setupObjectAnimator(ValueAnimator valueanimator, TypedArray typedarray, int i, float f, XmlPullParser xmlpullparser)
    {
        valueanimator = (ObjectAnimator)valueanimator;
        String s = TypedArrayUtils.getNamedString(typedarray, xmlpullparser, "pathData", 1);
        if(s != null)
        {
            String s1 = TypedArrayUtils.getNamedString(typedarray, xmlpullparser, "propertyXName", 2);
            xmlpullparser = TypedArrayUtils.getNamedString(typedarray, xmlpullparser, "propertyYName", 3);
            if(i != 2)
                if(i != 4);
            if(s1 == null && xmlpullparser == null)
            {
                throw new InflateException((new StringBuilder()).append(typedarray.getPositionDescription()).append(" propertyXName or propertyYName is needed for PathData").toString());
            } else
            {
                setupPathMotion(PathParser.createPathFromPathData(s), valueanimator, 0.5F * f, s1, xmlpullparser);
                return;
            }
        } else
        {
            valueanimator.setPropertyName(TypedArrayUtils.getNamedString(typedarray, xmlpullparser, "propertyName", 0));
            return;
        }
    }

    private static void setupPathMotion(Path path, ObjectAnimator objectanimator, float f, String s, String s1)
    {
        PathMeasure pathmeasure = new PathMeasure(path, false);
        float f1 = 0.0F;
        ArrayList arraylist = new ArrayList();
        arraylist.add(Float.valueOf(0.0F));
        float f3;
        do
        {
            f3 = f1 + pathmeasure.getLength();
            arraylist.add(Float.valueOf(f3));
            f1 = f3;
        } while(pathmeasure.nextContour());
        path = new PathMeasure(path, false);
        int l = Math.min(100, (int)(f3 / f) + 1);
        float af1[] = new float[l];
        float af[] = new float[l];
        float af2[] = new float[2];
        int j = 0;
        f3 /= l - 1;
        f = 0.0F;
        for(int i = 0; i < l;)
        {
            path.getPosTan(f, af2, null);
            path.getPosTan(f, af2, null);
            af1[i] = af2[0];
            af[i] = af2[1];
            float f2 = f + f3;
            int k = j;
            f = f2;
            if(j + 1 < arraylist.size())
            {
                k = j;
                f = f2;
                if(f2 > ((Float)arraylist.get(j + 1)).floatValue())
                {
                    f = f2 - ((Float)arraylist.get(j + 1)).floatValue();
                    k = j + 1;
                    path.nextContour();
                }
            }
            i++;
            j = k;
        }

        path = null;
        arraylist = null;
        if(s != null)
            path = PropertyValuesHolder.ofFloat(s, af1);
        s = arraylist;
        if(s1 != null)
            s = PropertyValuesHolder.ofFloat(s1, af);
        if(path == null)
        {
            objectanimator.setValues(new PropertyValuesHolder[] {
                s
            });
            return;
        }
        if(s == null)
        {
            objectanimator.setValues(new PropertyValuesHolder[] {
                path
            });
            return;
        } else
        {
            objectanimator.setValues(new PropertyValuesHolder[] {
                path, s
            });
            return;
        }
    }

    private static final boolean DBG_ANIMATOR_INFLATER = false;
    private static final int MAX_NUM_POINTS = 100;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;
}

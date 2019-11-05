package dev.android.x2j;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 7hens
 */
public class X2J {
    public static final String APPLICATION_ID = "o_0_applicationId";
    public static final boolean IS_ANDROID_LIBRARY = Boolean.parseBoolean("o_0_isAndroidLibrary");

    public static void setContentView(Activity activity, int layoutId) {
        View view = createView(activity, layoutId);
        if (view != null) {
            activity.setContentView(view);
        } else {
            activity.setContentView(layoutId);
        }
    }

    public static View inflate(Context context, int layoutId, ViewGroup parent) {
        return inflate(context, layoutId, parent, parent != null);
    }

    public static View inflate(Context context, int layoutId, ViewGroup parent, boolean attach) {
        return inflate(LayoutInflater.from(context), layoutId, parent, attach);
    }

    public static View inflate(LayoutInflater inflater, int layoutId, ViewGroup parent) {
        return inflate(inflater, layoutId, parent, parent != null);
    }

    public static View inflate(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attach) {
        View view = createView(inflater.getContext(), layoutId);
        if (view != null) {
            if (parent != null && attach) {
                parent.addView(view);
            }
            return view;
        } else {
            return inflater.inflate(layoutId, parent, attach);
        }
    }

    static int getResourceIdFromAttr(Context ctx, int attr) {
        TypedValue outValue = new TypedValue();
        Resources.Theme theme = ctx.getTheme();
        theme.resolveAttribute(attr, outValue, true);
        TypedArray typedArray = theme.obtainStyledAttributes(outValue.resourceId, new int[]{attr});
        try {
            return typedArray.getResourceId(0, 0);
        } catch (Exception e) {
            typedArray.recycle();
            return 0;
        }
    }

    interface ViewCreator {
        View createView(Context context);
    }

    private static final ViewCreator EMPTY_CREATOR = new ViewCreator() {
        @Override
        public View createView(Context context) {
            return null;
        }
    };

    private static final SparseArray<ViewCreator> viewCreators = new SparseArray<>();

    @SuppressWarnings("ConstantConditions")
    private static View createView(Context context, int layoutId) {
        try {
            ViewCreator creator = viewCreators.get(layoutId);
            if (creator != null) {
                return creator.createView(context);
            }
            String clzName = "dev.android.x2j.X2J_" + layoutId;
            creator = (ViewCreator) X2J.class.getClassLoader().loadClass(clzName).newInstance();
            viewCreators.put(layoutId, creator);
            return creator.createView(context);
        } catch (Exception e) {
            Log.e("@X2J", "could not found layout " + layoutId, e);
            // 放一个默认的 ViewCreator 进去，防止每次都调用反射方法耗时
            viewCreators.put(layoutId, EMPTY_CREATOR);
            return null;
        }
    }

}

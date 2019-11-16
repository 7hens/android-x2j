package dev.android.x2j;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 7hens
 */
public final class X2J {
    public static final String APPLICATION_ID = "o_0_applicationId";
    public static final boolean IS_ANDROID_LIBRARY = Boolean.parseBoolean("o_0_isAndroidLibrary");

    public static void setContentView(Activity activity, int layoutId) {
        ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);
        activity.setContentView(inflate(activity, layoutId, root, false));
    }

    public static View inflate(LayoutInflater inflater, int layoutId, ViewGroup parent) {
        return inflate(inflater.getContext(), layoutId, parent, parent != null);
    }

    public static View inflate(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attach) {
        return inflate(inflater.getContext(), layoutId, parent, attach);
    }

    public static View inflate(Context context, int layoutId, ViewGroup parent) {
        return inflate(context, layoutId, parent, parent != null);
    }

    public static View inflate(Context context, final int layoutId, ViewGroup root, boolean attach) {
        ViewCreator creator = viewCreators.get(layoutId);
        if (creator == null) {
            Log.e("@X2J", "could not create layout " + layoutId + " by x2j");
            creator = new ViewCreator() {
                @Override
                public View createView(Context context, ViewGroup root, boolean attach) {
                    return LayoutInflater.from(context).inflate(layoutId, root, attach);
                }
            };
            viewCreators.put(layoutId, creator);
        }
        return creator.createView(context, root, attach);
    }

    interface ViewCreator {
        View createView(Context context, ViewGroup root, boolean attach);
    }

    private static final SparseArray<ViewCreator> viewCreators = new SparseArray<>();

    static {
        //o_0_initViewCreators
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

    static View.OnClickListener onClickListener(View view, String methodName) {
        return new DeclaredOnClickListener(view, methodName);
    }

    private static class DeclaredOnClickListener implements View.OnClickListener {
        private final View mHostView;
        private final String mMethodName;

        private Method mResolvedMethod;
        private Context mResolvedContext;

        DeclaredOnClickListener(View hostView, String methodName) {
            mHostView = hostView;
            mMethodName = methodName;
        }

        @Override
        public void onClick(View v) {
            if (mResolvedMethod == null) {
                resolveMethod(mHostView.getContext(), mMethodName);
            }

            try {
                mResolvedMethod.invoke(mResolvedContext, v);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(
                        "Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(
                        "Could not execute method for android:onClick", e);
            }
        }

        private void resolveMethod(Context context, String name) {
            while (context != null) {
                try {
                    if (!context.isRestricted()) {
                        mResolvedMethod = context.getClass().getMethod(mMethodName, View.class);
                        mResolvedContext = context;
                        return;
                    }
                } catch (NoSuchMethodException e) {
                    // Failed to find method, keep searching up the hierarchy.
                }

                if (context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                } else {
                    // Can't search up the hierarchy, null out and fail.
                    context = null;
                }
            }

            final int id = mHostView.getId();
            final String idText = id == View.NO_ID ? "" : " with id '"
                    + mHostView.getContext().getResources().getResourceEntryName(id) + "'";
            throw new IllegalStateException("Could not find method " + mMethodName
                    + "(View) in a parent or ancestor Context for android:onClick "
                    + "attribute defined on view " + mHostView.getClass() + idText);
        }
    }
}

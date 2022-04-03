package androidx2j;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangyue.we.x2c.IViewCreator;
import com.zhangyue.we.x2c.ano.Xml;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ConstantConditions")
@Xml(layouts = {"o_0_layouts"})
public final class X2J {
    public static final String APPLICATION_ID = "o_0_applicationId";
    public static final boolean IS_ANDROID_LIBRARY = Boolean.parseBoolean("o_0_isAndroidLibrary");
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    private static final Set<String> LAYOUT_SET = new HashSet<>(Arrays.asList("o_0_layouts"));

    private static final IViewCreator EMPTY_CREATOR = new IViewCreator() {
        @Override
        public View createView(Context context) {
            return null;
        }
    };

    public static void setContentView(Activity activity, int layoutId) {
        View view = getView(activity, layoutId);
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

    public static View inflate(LayoutInflater inflater, int layoutId, ViewGroup parent,
                               boolean attach) {
        View view = getView(inflater.getContext(), layoutId);
        if (view != null) {
            if (parent != null && attach) {
                parent.addView(view);
            }
            return view;
        } else {
            return inflater.inflate(layoutId, parent, attach);
        }
    }

    private static final SparseArray<IViewCreator> sViewCreators = new SparseArray<>();

    private static View getView(Context context, int layoutId) {
        IViewCreator creator = null;
        String layoutName = "" + layoutId;
        try {
            creator = sViewCreators.get(layoutId);
            if (creator != null) {
                return creator.createView(context);
            }
            int group = generateGroupId(layoutId);
            layoutName = getLayoutName(context, layoutId);
            if (LAYOUT_SET.contains(layoutName)) {
                String clzName = "com.zhangyue.we.x2c.X2C" + group + "_" + layoutName;
                creator = (IViewCreator) X2J.class.getClassLoader().loadClass(clzName).newInstance();
            }
        } catch (Exception e) {
            Log.e("@X2J", "could not found layout " + layoutName, e);
        }
        //如果creator为空，放一个默认进去，防止每次都调用反射方法耗时
        if (creator == null) {
            creator = EMPTY_CREATOR;
        }
        sViewCreators.put(layoutId, creator);
        return creator.createView(context);
    }

    private static int generateGroupId(int layoutId) {
        return layoutId >> 24;
    }

    private static final SparseArray<String> sLayoutNames = new SparseArray<>();
    private static boolean sIsLayoutNamesInitialized = false;

    private static synchronized void initLayoutNames() throws Exception {
        if (sIsLayoutNamesInitialized) {
            return;
        }
        Class<?> cLayout = X2J.class.getClassLoader().loadClass(APPLICATION_ID + ".X2J_R$" + "layout");
        for (Field field : cLayout.getFields()) {
            sLayoutNames.put((int) field.get(null), field.getName());
        }
        sIsLayoutNamesInitialized = true;
    }

    private static String getLayoutName(Context context, int layoutId) throws Exception {
        if (!IS_ANDROID_LIBRARY) {
            String resName = context.getResources().getResourceName(layoutId);
            return resName.substring(resName.lastIndexOf("/") + 1);
        }
        initLayoutNames();
        return sLayoutNames.get(layoutId);
    }
}

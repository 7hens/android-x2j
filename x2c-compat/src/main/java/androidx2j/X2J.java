package androidx2j;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangyue.we.x2c.X2C;
import com.zhangyue.we.x2c.ano.Xml;

@Xml(layouts = {"o_0_layout"})
public final class X2J {
    public static void setContentView(Activity activity, int layoutId) {
        X2C.setContentView(activity, layoutId);
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
        View view = X2C.getView(inflater.getContext(), layoutId);
        if (view != null) {
            if (parent != null && attach) {
                parent.addView(view);
            }
            return view;
        } else {
            return inflater.inflate(layoutId, parent, attach);
        }
    }
}

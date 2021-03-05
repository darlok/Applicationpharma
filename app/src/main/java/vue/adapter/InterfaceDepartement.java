package vue.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

public interface InterfaceDepartement {
    void bindView(View view, Context context, Cursor cursor);

    View newView(Context context, Cursor cursor, ViewGroup parent);
}

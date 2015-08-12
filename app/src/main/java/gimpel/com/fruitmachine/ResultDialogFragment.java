package gimpel.com.fruitmachine;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by Filip on 2015-08-12.
 */
public class ResultDialogFragment extends DialogFragment {
    public static final String DRAWABLE = "DRAWABLE_RESOURCE_ID";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);
        view.setBackgroundResource(getArguments().getInt(DRAWABLE));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        view.setMinimumWidth((int) (displayRectangle.width() * 0.9f));
        view.setMinimumHeight((int)(displayRectangle.height() * 0.7f));

        return view;
    }
}

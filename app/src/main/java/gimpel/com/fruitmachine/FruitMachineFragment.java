package gimpel.com.fruitmachine;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Random;

/**
 * Created by Filip on 2015-08-12.
 */
public class FruitMachineFragment extends Fragment implements Button.OnClickListener, AbsListView.OnScrollListener {
    private static final int MIN_SCROLL = 3;
    private static final int MAX_SCROLL = 9;
    Random rand = new Random();
    ListView slot_1;
    ListView slot_2;
    ListView slot_3;
    Button playButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fruit_machine_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        slot_1 = (ListView) getView().findViewById(R.id.slot_1);
        slot_2 = (ListView) getView().findViewById(R.id.slot_2);
        slot_3 = (ListView) getView().findViewById(R.id.slot_3);
        playButton = (Button) getView().findViewById(R.id.button_play);

        slot_1.setTag(SCROLL_STATE_IDLE);
        slot_2.setTag(SCROLL_STATE_IDLE);
        slot_3.setTag(SCROLL_STATE_IDLE);

        slot_1.setAdapter(new FruitAdapter(getActivity()));
        slot_2.setAdapter(new FruitAdapter(getActivity()));
        slot_3.setAdapter(new FruitAdapter(getActivity()));
        playButton.setOnClickListener(this);

        slot_1.setOnScrollListener(this);
        slot_2.setOnScrollListener(this);
        slot_3.setOnScrollListener(this);

        slot_1.setSelectionFromTop(Integer.MAX_VALUE/2, 0);
        slot_2.setSelectionFromTop(Integer.MAX_VALUE/2, 0);
        slot_3.setSelectionFromTop(Integer.MAX_VALUE/2, 0);
    }

    @Override
    public void onClick(View view) {
            int range = MAX_SCROLL - MIN_SCROLL + 1;

            slot_1.smoothScrollToPosition(
                    slot_1.getFirstVisiblePosition() + rand.nextInt(range) + MIN_SCROLL);
            slot_2.smoothScrollToPosition(
                    slot_2.getFirstVisiblePosition() + rand.nextInt(range) + MIN_SCROLL);
            slot_3.smoothScrollToPosition(
                    slot_3.getFirstVisiblePosition() + rand.nextInt(range) + MIN_SCROLL);
    }

    @Override
    public void onScrollStateChanged(AbsListView listView, int scrollState) {
        listView.setTag(scrollState);

        if (scrollState == SCROLL_STATE_IDLE){
            View child = listView.getChildAt (0);
            Rect rect = new Rect(0, 0, child.getWidth(), child.getHeight());
            double height = child.getHeight () * 1.0;
            listView.getChildVisibleRect (child, rect, null);
            if(Math.abs(rect.height())!=height){
                if (Math.abs (rect.height ()) < height / 2.0) {
                    listView.smoothScrollToPosition(listView.getLastVisiblePosition());
                }
                else if(Math.abs (rect.height ()) > height / 2.0){
                    listView.smoothScrollToPosition(listView.getFirstVisiblePosition());
                }
                else{
                    listView.smoothScrollToPosition(listView.getFirstVisiblePosition());
                }
            }
        }

        if (slot_1.getTag() == SCROLL_STATE_IDLE &&
            slot_2.getTag() == SCROLL_STATE_IDLE &&
            slot_3.getTag() == SCROLL_STATE_IDLE) {

            if ((slot_1.getFirstVisiblePosition() % 3 ==  slot_2.getFirstVisiblePosition() %3) &&
                    (slot_3.getFirstVisiblePosition() %3 == slot_1.getFirstVisiblePosition() % 3)) {

                int drawableResourceID = (int) slot_1.getAdapter().getItem(slot_1.getFirstVisiblePosition());

                FragmentManager fm = getFragmentManager();
                ResultDialogFragment resultDialogFragment = new ResultDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(ResultDialogFragment.DRAWABLE, drawableResourceID);
                resultDialogFragment.setArguments(bundle);
                resultDialogFragment.show(fm, "dialog_fragment");
            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {

    }
}



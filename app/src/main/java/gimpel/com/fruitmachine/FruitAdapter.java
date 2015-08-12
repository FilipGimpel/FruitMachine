package gimpel.com.fruitmachine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Filip on 2015-08-12.
 */
public class FruitAdapter extends BaseAdapter {
    Context context;


    public FruitAdapter(Context context) {
        this.context = context;
    }

    private static Integer[] items  = {
            R.drawable.fruittype_avocado,
            R.drawable.fruittype_burrito,
            R.drawable.fruittype_skeleton
    };

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Integer getItem(int position) {
        return items[position % items.length];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = new ImageView(context);
        }

        convertView.setBackgroundResource(getItem(position));
        return convertView;
    }
}

package rs.youthnow;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Stefan on 16.10.2016.
 */

public class VerticalSpace extends RecyclerView.ItemDecoration {

    int space;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
        outRect.left = space;
        outRect.right = space;

        if(parent.getChildLayoutPosition(view)==0)
            outRect.top = space;
    }

    public VerticalSpace(int space){
        this.space = space;


    }

}

package recipe.tangy.com.tangyrecipe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import recipe.tangy.com.tangyrecipe.R;

/**
 * Created by android on 16/2/18.
 */

public class FeedBackFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feed_back, container, false);
        return v;
    }
}

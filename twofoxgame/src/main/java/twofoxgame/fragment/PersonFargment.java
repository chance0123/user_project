package twofoxgame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.a1000phone.chengling.twofoxganme.R;

/**
 * Created by chengling on 2016/10/24.
 */
public class PersonFargment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_main_page_view, container, false);
        return view;
    }
}

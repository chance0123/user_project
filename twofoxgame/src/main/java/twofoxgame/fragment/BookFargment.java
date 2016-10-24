package twofoxgame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.a1000phone.chengling.twofoxganme.R;
import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import twofoxfargmentbean.Book_bean;

/**
 * Created by chengling on 2016/10/24.
 */
public class BookFargment extends Fragment {
    private static final String TAG = "android++";
    public  final String PATH = "http://big.pipaw.com/api/game/Strategy";
    private GridView mGridView;
    private List<Book_bean> datas = new ArrayList<>();
    private BookAdapter bookAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_main_page_view, container, false);
        mGridView = (GridView) view.findViewById(R.id.book_grid_view);
        bookAdapter = new BookAdapter();
        mGridView.setAdapter(bookAdapter);
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                preseJson(result);
            }
        });
        return view;
    }
    private void preseJson(String result){
        Log.i(TAG, "preseJson: "+"");
        try {
            JSONArray jsonArray = new JSONArray(result);
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("game_id");
                String game_name = jsonObject.getString("game_name");
                String img = jsonObject.getString("img");
                Book_bean book_bean = new Book_bean(id,game_name,img);
                datas.add(book_bean);
                Log.i(TAG, "preseJson: "+"end");
            }

            bookAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    class BookAdapter extends BaseAdapter{
        public static final String PATHFIX = "http://big.pipaw.com";
        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder viewHolder = null;
            if (view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.book_gridview_item,parent,false);
                viewHolder = new ViewHolder(view);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Book_bean bean = datas.get(position);
            ImageAsyncLoader.load(datas.get(position).getImg(),viewHolder.imageView).execute();
            viewHolder.textView.setText(datas.get(position).getGame_name());
            return view;
        }
        class ViewHolder{
            private ImageView imageView;
            private TextView textView;
            public ViewHolder(View view) {
                view.setTag(this);
                imageView = (ImageView) view.findViewById(R.id.book_grid_view_image_iv);
                textView = (TextView) view.findViewById(R.id.book_grid_view_text_txt);
            }
        }
    }
}

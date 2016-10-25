package twofoxgame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.a1000phone.chengling.twofoxganme.CustomListView;
import com.android.a1000phone.chengling.twofoxganme.R;
import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import twofoxfargmentbean.GameBean;

/**
 * Created by chengling on 2016/10/24.
 */
public class GameFargment extends Fragment {
    public static final String PATH = "http://big.pipaw.com/big/TodayPub?app_version=343";
    public static final String PATH2 = "http://big.pipaw.com/big/IndexGetHot?app_version=343&type=1";
    public static final String PATH3 = "http://big.pipaw.com/big/IndexGetHot?app_version=343&type=2";
    public static final String PATH4 = "http://big.pipaw.com/api/game/indexlist?app_version=343&type=firstpay&p=1";
    private static final String TAG = "11111";
    private CustomListView mListview;
    private List<GameBean> datas = new ArrayList<>();
    private GameOneAdapter gameOneAdapter;
    private ListView mHotListView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_main_page_view,container,false);
        mListview = (CustomListView) view.findViewById(R.id.game_today_list_view_lv);
        mHotListView = (ListView) view.findViewById(R.id.game_hot_list_view_lv);
        gameOneAdapter = new GameOneAdapter();
        mHotListView.setAdapter(gameOneAdapter);
        mListview.setAdapter(gameOneAdapter);
        AsyncTaskTool.load(PATH2).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                perseJson(result);
            }
        });
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                perseJson(result);
            }
        });
        return view;
    }
    private void perseJson(String reslut){
        try {
            JSONArray jsonArray = new JSONArray(reslut);
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
//                Log.i(TAG, "perseJson: "+"start++++++++");
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String logo = jsonObject.getString("logo");
                String game_name = jsonObject.getString("game_name");
                String type_name = jsonObject.getString("type_name");
                String game_visits = jsonObject.getString("game_visits");
                String desc1 = jsonObject.getString("desc1");
//                Log.i(TAG, "perseJson: ============"+logo);
                JSONObject download_data = jsonObject.getJSONObject("download_data");
                String size = download_data.getString("size");
                GameBean gameBean = new GameBean(desc1,game_name,game_visits,logo,size,type_name);
                datas.add(gameBean);
            }
            gameOneAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    class GameOneAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder viewHolder = null;
            if (view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.game_today_one_listview_item,parent,false);
                viewHolder = new ViewHolder(view);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            GameBean bean = datas.get(position);
            ImageAsyncLoader.load(datas.get(position).getLogo(),viewHolder.logo).execute();
            viewHolder.desc1.setText(datas.get(position).getDesc1());
            viewHolder.game_name.setText(datas.get(position).getGame_name());
            viewHolder.game_visits.setText("人气"+datas.get(position).getGame_visits());
            viewHolder.size.setText(datas.get(position).getSize()+"|");
            viewHolder.type_name.setText(datas.get(position).getType_name()+"|");
            return view;
        }
        class ViewHolder{

            private ImageView logo;
            private TextView game_name;
            private TextView type_name;
            private TextView size;
            private TextView game_visits;
            private TextView desc1;

            public ViewHolder(View view) {
                view.setTag(this);
                logo = (ImageView)view.findViewById(R.id.game_today_item_iv);
                game_name = (TextView) view.findViewById(R.id.game_today_item_one_txt);
                type_name = (TextView) view.findViewById(R.id.game_todang_item_two_txt);
                size = (TextView) view.findViewById(R.id.game_today_pass_item_txt);
                game_visits = (TextView) view.findViewById(R.id.game_today_item_three_txt);
                desc1 = (TextView) view.findViewById(R.id.game_today_four_item_txt);
            }
        }
    }
}

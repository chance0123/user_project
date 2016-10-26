package twofoxgame.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.a1000phone.chengling.twofoxganme.CustomGridView;
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
import twofoxfargmentbean.GameGridBean;

/**
 * Created by chengling on 2016/10/24.
 */
public class GameFargment extends Fragment {
    public static final String PATH = "http://big.pipaw.com/big/TodayPub?app_version=343";
    public static final String PATH2 = "http://big.pipaw.com/big/IndexGetHot?app_version=343&type=1";
    public static final String PATH3 = "http://big.pipaw.com/big/IndexGetHot?app_version=343&type=2";
    public static final String PATH4 = "http://big.pipaw.com/api/game/indexlist?app_version=343&type=firstpay&p=1";
    public static final String PATH5 = "http://big.pipaw.com/big/GameBannerNew?app_version=343";
    private static final String TAG = "11111";
    private List<GameBean> datas = new ArrayList<>();
    private List<GameBean> datas2 = new ArrayList<>();
    private List<GameGridBean> datas3 = new ArrayList<>();
    private List<GameBean> datas4 = new ArrayList<>();
    private List<String> datas5 = new ArrayList<>();
    private CustomListView mListview;
    private CustomListView mHotListView;
    private CustomGridView mGridView;
    private CustomListView mWelfareListView;
    private ViewPager mViewPager;
    private GameOneAdapter gameOneAdapter;
    private GameGridAdapter gameGridAdapter;
    private GameOneAdapter gameOneAdapter2;
    private GameOneAdapter gameOneAdapter4;
    private GameHanderAdapter gameHanderAdapter;

    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int index = msg.what;
            mViewPager.setCurrentItem(index % 4);
        }
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_main_page_view,container,false);
        mListview = (CustomListView) view.findViewById(R.id.game_today_list_view_lv);
        mHotListView = (CustomListView) view.findViewById(R.id.game_hot_list_view_lv);
        mWelfareListView = (CustomListView) view.findViewById(R.id.game_welfare_list_view_lv);
        mGridView = (CustomGridView) view.findViewById(R.id.game_main_boutique_game_gv);
        mViewPager = (ViewPager) view.findViewById(R.id.game_hander_view_pager); //这是viewpager
        gameOneAdapter = new GameOneAdapter(datas);
        gameGridAdapter = new GameGridAdapter();
        gameOneAdapter2 = new GameOneAdapter(datas2);
        gameOneAdapter4 = new GameOneAdapter(datas4);
        gameHanderAdapter = new GameHanderAdapter();
        mHotListView.setAdapter(gameOneAdapter2);
        mListview.setAdapter(gameOneAdapter);
        mWelfareListView.setAdapter(gameOneAdapter4);
        mGridView.setAdapter(gameGridAdapter);
        mViewPager.setAdapter(gameHanderAdapter);
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                perseJson(result);
            }
        });
        AsyncTaskTool.load(PATH2).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                perseJson2(result);
            }
        });
        AsyncTaskTool.load(PATH3).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                perseJson3(result);
            }
        });
        AsyncTaskTool.load(PATH4).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                perseJson4(result);
            }
        });
        AsyncTaskTool.load(PATH5).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                perseJson5(result);
            }
        });//这是viewpager
        return view;
    }


    private void perseJson(String reslut3){
        try {
            JSONArray jsonArray = new JSONArray(reslut3);
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
//                datas2.add(gameBean);
            }
            gameOneAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void perseJson2(String reslut2){
        try {
            JSONArray jsonArray = new JSONArray(reslut2);
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
//                datas.add(gameBean);
                datas2.add(gameBean);
            }
            gameOneAdapter2.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void perseJson3(String result3){
        try {
            JSONArray jsonArray = new JSONArray(result3);
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String logo = jsonObject.getString("logo");
                String game_name = jsonObject.getString("game_name");
                String type_name = jsonObject.getString("type_name");
                String game_visits = jsonObject.getString("game_visits");
                GameGridBean gameGridBean = new GameGridBean(game_name,game_visits,logo,type_name);
                datas3.add(gameGridBean);
            }
            gameGridAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void perseJson4(String reslut){
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
                datas4.add(gameBean);
//                datas2.add(gameBean);
            }
            gameOneAdapter4.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void  perseJson5(String result){
        try {
            JSONArray jsonArray = new JSONArray(result);
            int len  = jsonArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String img = jsonObject.getString("img");
                datas5.add(img);
            }
            gameHanderAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (true){

                    try {
                        Thread.sleep(3000);
                        mHandle.sendEmptyMessage(index);

                        index++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }//这是viewpager
    class GameOneAdapter extends BaseAdapter{

        private List<GameBean> gameList = new ArrayList<>();

        public GameOneAdapter(List<GameBean> games) {
            gameList = games;
        }

        @Override
        public int getCount() {
            return gameList == null ? 0 : gameList.size();
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
            GameBean bean = gameList.get(position);
            ImageAsyncLoader.load(gameList.get(position).getLogo(),viewHolder.logo).execute();
            viewHolder.desc1.setText(gameList.get(position).getDesc1());
            viewHolder.game_name.setText(gameList.get(position).getGame_name());
            viewHolder.game_visits.setText("人气"+gameList.get(position).getGame_visits());
            viewHolder.size.setText(gameList.get(position).getSize()+"|");
            viewHolder.type_name.setText(gameList.get(position).getType_name()+"|");
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
    class GameGridAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return datas3 == null ? 0 :datas3.size();
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
                view = LayoutInflater.from(getContext()).inflate(R.layout.game_grid_view_item,parent,false);
                viewHolder = new ViewHolder(view);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            GameGridBean gameGridBean = datas3.get(position);
            ImageAsyncLoader.load(datas3.get(position).getLogo(),viewHolder.logo).execute();
            viewHolder.game_name.setText(datas3.get(position).getGame_name());
            viewHolder.game_visits.setText(datas3.get(position).getGame_visits());
            viewHolder.type_name.setText(datas3.get(position).getType_name());
            return view;
        }
        class ViewHolder{

            private ImageView logo;
            private TextView game_name;
            private TextView type_name;
            private TextView game_visits;

            public ViewHolder(View view) {
                view.setTag(this);
                logo = (ImageView) view.findViewById(R.id.game_grid_view_item_iv);
                game_name = (TextView) view.findViewById(R.id.game_grid_view_item_one_txt);
                type_name = (TextView) view.findViewById(R.id.game_grid_view_item_two_txt);
                game_visits = (TextView) view.findViewById(R.id.game_grid_view_item_three_txt);
            }
        }
    }
    class GameHanderAdapter extends PagerAdapter{

        @Override
        public int getCount() {
//            Log.i(TAG, "getCount: "+datas5.size());
            return datas5 == null ? 0 : datas5.size();
//            return datas5 == null ? 0 : Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            container.addView(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            String s = datas5.get(position);
//            String path = "http://big.pipaw.com"+s;

                ImageAsyncLoader.load(datas5.get(position),imageView).execute();

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}

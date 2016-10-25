package twofoxgame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.a1000phone.chengling.twofoxganme.R;
import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import twofoxfargmentbean.ActivityBean;

/**
 * Created by chengling on 2016/10/24.
 */
public class ActivityFargment extends Fragment{
    public static final String PATH = "http://big.pipaw.com/api/activities/userindex?token=cnNCa5mMjTvXD%252Bw1YEZagvo%252BpKuh5M2aAl%252FOs29Raz95MnMqCjom4qSWYC%252BSFrelkEVyzVO9u9Jm1RWc7P3YWvJwn2H7qR4I&app_version=343&page_index=1&page_size=10";
    private static final String TAG = "android";
    private ListView mListView;
    private List<ActivityBean> datas = new ArrayList<>();
    private ActivityAdapter activityAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_page_view,container,false);
        mListView = (ListView)view.findViewById(R.id.activity_list_view);
        activityAdapter = new ActivityAdapter();
        mListView.setAdapter(activityAdapter);
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
//            Log.i(TAG, "perseJson: ++  ");
            JSONObject jsonObject1 = new JSONObject(reslut);
            JSONArray jsonArray = jsonObject1.getJSONArray("data");
            int len = jsonArray.length();
            for (int i = 0; i <len ; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String end_time = jsonObject.getString("end_time");
                String logo = jsonObject.getString("logo");
                String title = jsonObject.getString("title");
                String supports = jsonObject.getString("supports");
                String comments = jsonObject.getString("comments");
                String sn = jsonObject.getString("sn");
//                Log.i(TAG, "perseJson: --");
                ActivityBean activityBean = new ActivityBean(end_time,logo,title,supports,comments,sn);
                datas.add(activityBean);
            }
            activityAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    class ActivityAdapter extends BaseAdapter{

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
                view = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_view_item,parent,false);
                viewHolder = new ViewHolder(view);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            ActivityBean bean = datas.get(position);
            ImageAsyncLoader.load(datas.get(position).getLogo(),viewHolder.logo).execute();
            viewHolder.title.setText(datas.get(position).getTitle());
            viewHolder.comments.setText("回复 "+datas.get(position).getComments());
            viewHolder.end_time.setText(datas.get(position).getEnd_time());
            viewHolder.supports.setText("点赞 "+datas.get(position).getSupports());
            return view;
        }
        class ViewHolder{
            private TextView end_time;
            private TextView title;
            private TextView supports;
            private TextView comments;
            private ImageView logo;
            public ViewHolder(View view) {
                view.setTag(this);
                end_time = (TextView) view.findViewById(R.id.acitivity_time_txt);
                title = (TextView) view.findViewById(R.id.acitivity_one_txt);
                supports = (TextView) view.findViewById(R.id.acitivity_three_txt);
                comments = (TextView) view.findViewById(R.id.acitivity_four_txt);
                logo = (ImageView) view.findViewById(R.id.acitivity_item_image_iv);
            }
        }
    }
}

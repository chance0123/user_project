package com.android.a1000phone.chengling.twofoxganme;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;


import twofoxgame.fragment.ActivityFargment;
import twofoxgame.fragment.BookFargment;
import twofoxgame.fragment.GameFargment;
import twofoxgame.fragment.GiftFargment;
import twofoxgame.fragment.PersonFargment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mRadioGroup;
    private FragmentManager mFragmentManager;
    private GameFargment mGameFargment;
    private BookFargment mBookFargment;
    private GiftFargment mGiftFargment;
    private ActivityFargment mActivityFargment;
    private PersonFargment mPersonFargment;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radio_group_rg);
        mFragmentManager = getSupportFragmentManager();
        mGameFargment = new GameFargment();
        mBookFargment = new BookFargment();
        mGiftFargment = new GiftFargment();
        mActivityFargment = new ActivityFargment();
        mPersonFargment = new PersonFargment();
        mRadioGroup.check(R.id.main_game_rbn);
        checkFargment(mGameFargment);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    private void checkFargment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mFragment != null){
            fragmentTransaction.hide(mFragment);
        }
        if (!fragment.isAdded()){
            fragmentTransaction.add(R.id.main_frame_layout_fl,fragment);
        }
        else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mFragment = fragment;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_game_rbn:
                checkFargment(mGameFargment);
                break;
            case R.id.main_gift_rbn:
                checkFargment(mGiftFargment);
                break;
            case R.id.main_activity_rbn:
                checkFargment(mActivityFargment);
                break;
            case R.id.main_book_rbn:
                checkFargment(mBookFargment);
                break;
            case R.id.main_person_rbn:
                checkFargment(mPersonFargment);
                break;
        }
    }
}

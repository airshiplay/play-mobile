package com.airlenet.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by lig on 16/6/23.
 */
public class TransitionFragmentActivity extends BaseFragmentActivity {

    protected boolean isInit = true;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Class<Fragment> cls = (Class<Fragment>) getIntent()
                    .getSerializableExtra("cls");
            Bundle args = getIntent().getExtras();
            Fragment fg = cls.newInstance();
            fg.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, fg).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void navigateToFrament(BaseFragment frament) {
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, frament)
                .addToBackStack(frament.getClass().getName()).commitAllowingStateLoss();

    }

    public void navigateToFramentAnimation(Fragment frament) {
        getSupportFragmentManager()
                .beginTransaction()
//                .setCustomAnimations(
//                        R.anim.slide_in_from_right, R.anim.slide_out_to_left)
                .replace(android.R.id.content, frament)
                .addToBackStack(frament.getClass().getName())
                .commitAllowingStateLoss();
    }

    public void navigateToFramentNoBack(Fragment frament) {
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, frament)
                .commitAllowingStateLoss();

    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible() && fragment instanceof BaseFragment) {
                if (((BaseFragment) fragment).preventBack())
                    return;
            }
        }
        super.onBackPressed();
    }

    public void back() {
        getSupportFragmentManager().popBackStack();
    }


}

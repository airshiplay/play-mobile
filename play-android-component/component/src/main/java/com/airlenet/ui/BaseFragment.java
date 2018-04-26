package com.airlenet.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airlenet.component.R;

/**
 * Created by lig on 16/6/23.
 */
public class BaseFragment extends Fragment {
    protected Toolbar mToolbar;
    DelegateFragment delegate;
    public void startFragmentUI(Class<?> cls) {
        getActivity().startActivity(
                new Intent(getActivity(), TransitionFragmentActivity.class).putExtra(
                        "cls", cls));

    }

    public void startFragmentUIForResult(Class<?> cls) {
        startActivityForResult(new Intent(getActivity(),
                        TransitionFragmentActivity.class).putExtra("cls", cls),
                Activity.RESULT_FIRST_USER);
    }

    public void startFragmentUI(Class<?> cls, Bundle extras) {
        getActivity().startActivity(
                new Intent(getActivity(), TransitionFragmentActivity.class).putExtra(
                        "cls", cls).putExtras(extras));
    }

    /**
     * 同一个Activity进行fragment替换，可返回
     *
     * @param frament
     */
    public void navigateToFrament(BaseFragment frament) {
        ((TransitionFragmentActivity) getActivity()).navigateToFrament(frament);
    }

    public void navigateToFramentAnimation(Fragment frament) {
        ((TransitionFragmentActivity)
                getActivity()).navigateToFramentAnimation(frament);
    }

    /**
     * 同一个Activity进行fragment替换，不可返回，直接退出activity
     *
     * @param frament
     */
    public void navigateToFramentNoBack(BaseFragment frament) {
        ((TransitionFragmentActivity) getActivity()).navigateToFramentNoBack(frament);
    }

    /**
     * 阻止返回,true 不返回,false 返回
     *
     * @return
     */
    public boolean preventBack() {
        return false;
    }


    protected void showToast(CharSequence text) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    }


    public void finishActivity() {
        if (getActivity() != null)
            getActivity().finish();
    }

    public void onBackPressed() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    public void showWithStatus(String info) {
        ((BaseFragmentActivity) getActivity()).showWithStatus(info);
    }

    public void updateStatus(String info) {
        ((BaseFragmentActivity) getActivity()).updateStatus(info);
    }

    public void dismissStatus() {
        ((BaseFragmentActivity) getActivity()).dismissStatus();
    }


    public void setTitle(String title) {
        mToolbar.setTitle(title);
    }

    protected void initToolbar(boolean showHomeAsUp) {
        if (getActivity() instanceof AppCompatActivity) {
            setHasOptionsMenu(true);
            AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
            mToolbar = (Toolbar) appCompatActivity.findViewById(R.id.toolbar);
            appCompatActivity.setSupportActionBar(mToolbar);
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
            appCompatActivity.getSupportActionBar().setHomeButtonEnabled(showHomeAsUp);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });

        }
    }

    protected void initToolbar() {
        initToolbar(true);
    }

    protected boolean getDefaultDelegate() {
        return true;
    }

    public DelegateFragment getDelegateFragment() {
        if (delegate == null && getDefaultDelegate()) {
            String clsStr = getResources().getString(R.string.delegate_fragment);
            if (!TextUtils.isEmpty(clsStr)) {
                try {
                    Class c = Class.forName(clsStr);
                    delegate = (DelegateFragment) c.newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return delegate;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DelegateFragment delegate = getDelegateFragment();
        if (delegate != null)
            delegate.onAttach(this,context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (delegate != null)
            delegate.onCreate(this,savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (delegate != null)
            delegate.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (delegate != null)
            delegate.onActivityResult(this, requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (delegate != null)
            delegate.onCreateView(this,inflater, container, savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (delegate != null)
            delegate.onViewCreated(this,view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (delegate != null)
            delegate.onActivityCreated(this,savedInstanceState);
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        if (delegate != null)
            delegate.onResume(this);
        super.onResume();

    }

    @Override
    public void onPause() {
        if (delegate != null)
            delegate.onPause(this);
        super.onPause();

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (delegate != null)
            delegate.onViewStateRestored(this,savedInstanceState);
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onStart() {
        if (delegate != null)
            delegate.onStart(this);
        super.onStart();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (delegate != null)
            delegate.onSaveInstanceState(this,outState);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (delegate != null)
            delegate.onConfigurationChanged(this, newConfig);
        super.onConfigurationChanged(newConfig);

    }

    @Override
    public void onStop() {
        if (delegate != null)
            delegate.onStop(this);
        super.onStop();
    }

    @Override
    public void onLowMemory() {
        if (delegate != null)
            delegate.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        if (delegate != null)
            delegate.onDestroyView(this);
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        if (delegate != null)
            delegate.onDestroy(this);
        super.onDestroy();

    }

    @Override
    public void onDetach() {
        if (delegate != null)
            delegate.onDetach(this);
        super.onDetach();

    }


}

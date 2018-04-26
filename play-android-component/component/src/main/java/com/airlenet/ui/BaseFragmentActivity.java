package com.airlenet.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.TaskStackBuilder;
import android.app.assist.AssistContent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.airlenet.component.R;
import com.bigkoo.svprogresshud.SVProgressHUD;


/**
 * Created by lig on 16/6/23.
 */
public class BaseFragmentActivity extends AppCompatActivity {
    DelegateActivity delegate;
    private SVProgressHUD mSVProgressHUD;
    //@Overrided
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final DelegateActivity delegate = getDelegateActivity();
        if (delegate != null) {
            delegate.onCreate(this,savedInstanceState);
        }
        super.onCreate(savedInstanceState);
        mSVProgressHUD = new SVProgressHUD(this);
    }

    protected boolean getDefaultDelegate() {
        return true;
    }

    public DelegateActivity getDelegateActivity() {
        if (delegate == null && getDefaultDelegate()) {
            String clsStr = getResources().getString(R.string.delegate_activity);
            if (!TextUtils.isEmpty(clsStr)) {
                try {
                    Class c = Class.forName(clsStr);
                    delegate = (DelegateActivity) c.newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
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

    /**
     * 显示Fragment,全局替换
     *
     * @param frament
     */
    public void navigateToFrament(BaseFragment frament) {
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, frament)
                .addToBackStack(frament.getClass().getName()).commitAllowingStateLoss();
    }

    /**
     * 显示Toast,short
     *
     * @param text
     */
    protected void showToast(CharSequence text) {
        Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_SHORT).show();

    }

    public void startFragmentUI(Class<?> cls) {
        startActivity(
                new Intent(getApplicationContext(), TransitionFragmentActivity.class).putExtra(
                        "cls", cls));
    }

    public void startFragmentUIForResult(Class<?> cls) {
        startActivityForResult(new Intent(getApplicationContext(),
                        TransitionFragmentActivity.class).putExtra("cls", cls),
                Activity.RESULT_FIRST_USER);
    }

    public void startFragmentUI(Class<?> cls, Bundle extras) {
        startActivity(
                new Intent(getApplicationContext(), TransitionFragmentActivity.class).putExtra(
                        "cls", cls).putExtras(extras));
    }

    protected void initToolbar(boolean showHomeAsUp) {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
        getSupportActionBar().setHomeButtonEnabled(showHomeAsUp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void showWithStatus(String info) {
        mSVProgressHUD.showWithStatus(info);
    }

    public void updateStatus(String info) {
        mSVProgressHUD.setText(info);
    }

    public void dismissStatus() {
        if (mSVProgressHUD.isShowing()) {
            mSVProgressHUD.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mSVProgressHUD.isShowing()) {
                mSVProgressHUD.dismiss();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);

    }


    protected void onPostResume() {
        super.onPostResume();
    }

    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    protected void onStart() {
        if (delegate != null) {
            delegate.onStart(this);
        }
        super.onStart();

    }

    protected void onRestart() {
        if (delegate != null) {
            delegate.onRestart(this);
        }
        super.onRestart();

    }

    public void onStateNotSaved() {
        super.onStateNotSaved();
    }

    protected void onResume() {
        if (delegate != null) {
            delegate.onResume(this);
        }
        super.onResume();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onPause() {
        if (delegate != null) {
            delegate.onPause(this);
        }
        super.onPause();

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        return super.onCreateThumbnail(outBitmap, canvas);
    }

    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        return super.onCreateDescription();
    }

    @Override
    public void onProvideAssistData(Bundle data) {
        super.onProvideAssistData(data);
    }

    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        super.onProvideAssistContent(outContent);
    }

    @Override
    public boolean showAssist(Bundle args) {
        return super.showAssist(args);
    }

    @Override
    protected void onStop() {
        if (delegate != null) {
            delegate.onStop(this);
        }
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        if (delegate != null) {
            delegate.onDestroy(this);
        }
        super.onDestroy();

    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return super.onWindowStartingActionMode(callback);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return super.onWindowStartingActionMode(callback, type);
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Nullable
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
    }

    @Override
    public void onVisibleBehindCanceled() {
        super.onVisibleBehindCanceled();
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Uri onProvideReferrer() {
        return super.onProvideReferrer();
    }

    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }

    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return super.onSearchRequested(searchEvent);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
    }

    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
    }

    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        return super.onNavigateUpFromChild(child);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        super.onContextMenuClosed(menu);
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return super.onPreparePanel(featureId, view, menu);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        return super.onCreatePanelView(featureId);
    }

    @Override
    public void onDetachedFromWindow() {
        if (delegate != null) {
            delegate.onDetachedFromWindow(this);
        }
        super.onDetachedFromWindow();

    }

    @Override
    public void onAttachedToWindow() {
        if (delegate != null) {
            delegate.onAttachedToWindow(this);
        }
        super.onAttachedToWindow();

    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        return super.onTrackballEvent(event);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return super.onGenericMotionEvent(event);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (delegate != null) {
            delegate.onBackPressed(this);
        }
        super.onBackPressed();

    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return super.onKeyLongPress(keyCode, event);
    }


    @Override
    public void onTrimMemory(int level) {
        if (delegate != null) {
            delegate.onTrimMemory(level);
        }
        super.onTrimMemory(level);

    }

    @Override
    public void onLowMemory() {
        if (delegate != null) {
            delegate.onLowMemory();
        }
        super.onLowMemory();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (delegate != null) {
            delegate.onAttachFragment(this,fragment);
        }
        super.onAttachFragment(fragment);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (delegate != null) {
            delegate.onConfigurationChanged(this, newConfig);
        }
        super.onConfigurationChanged(newConfig);

    }
}


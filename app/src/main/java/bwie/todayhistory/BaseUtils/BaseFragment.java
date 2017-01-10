package bwie.todayhistory.BaseUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/6.
 */
public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentActivity mActivity=getActivity();
        View  view=initView(inflater);
        initFindViewbyId(view);
        initData();
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }
    /**
     * 跳转
     * @param cls
     */
    public void startActivitys(Class<?> cls){
        Intent intent = new Intent(getActivity(),cls);
        startActivity(intent);
    }

    /**
     * 子类实现此抽象方法返回View进行展示
     *
     * @return
     */
    public abstract View initView(LayoutInflater inflater);
    /**
     * 初始化控件
     */
    protected abstract void initFindViewbyId(View view);
    /**
     * 子类可以复写此方法初始化事件
     */
    protected void initEvent(){

    }
    /**
     * 子类在此方法中实现数据的初始化
     */
    protected abstract void initData();

}

package bwie.todayhistory.BaseUtils;

import android.os.Bundle;

/**
 * Created by lishaocong on 2016/11/10.
 */
public interface IOnCreate {

    /**
     * 返回布局文件ID
     * @return
     */
    int bindLayout();

    /**
     * 初始化数据
     *
     */
    void initData();

    /**
     *
     * 初始化控件
     */
    void initView(Bundle savedInstanceState);

    /**
     *
     * 加载网络数据  onCreate方法自动调用；
     */

    void loadData();
}

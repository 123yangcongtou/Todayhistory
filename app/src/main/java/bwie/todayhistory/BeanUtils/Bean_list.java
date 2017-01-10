package bwie.todayhistory.BeanUtils;

import java.util.List;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/6.
 */
public class Bean_list {

    public int error_code;
    public String reason;

    public List<ResultBean> result;

    public static class ResultBean {
        public String _id;
        public int day;
        public String des;
        public String lunar;
        public int month;
        public String pic;
        public String title;
        public int year;
    }
}

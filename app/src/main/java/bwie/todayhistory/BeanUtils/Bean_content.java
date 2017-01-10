package bwie.todayhistory.BeanUtils;

import java.util.List;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/8.
 */
public class Bean_content {

    public String reason;
    public int error_code;

    public List<ResultBean> result;

    public static class ResultBean {
        public String _id;
        public String title;
        public String pic;
        public int year;
        public int month;
        public int day;
        public String des;
        public String content;
        public String lunar;
    }
}

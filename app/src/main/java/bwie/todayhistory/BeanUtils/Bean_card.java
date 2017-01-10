package bwie.todayhistory.BeanUtils;

import java.util.List;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/8.
 */
public class Bean_card {

    public boolean error;

    public List<ResultsBean> results;

    public static class ResultsBean {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }
}

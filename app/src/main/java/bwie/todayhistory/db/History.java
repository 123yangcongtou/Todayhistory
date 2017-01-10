package bwie.todayhistory.db;

import org.litepal.crud.DataSupport;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/9.
 */
public class History extends DataSupport {
    private int id;
    private String db_mId;
    private String db_day;
    private String db_title;
    private String db_id;

    public String getDb_id() {
        return db_id;
    }

    public void setDb_id(String db_id) {
        this.db_id = db_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDb_mId() {
        return db_mId;
    }

    public void setDb_mId(String db_mId) {
        this.db_mId = db_mId;
    }

    public String getDb_day() {
        return db_day;
    }

    public void setDb_day(String db_day) {
        this.db_day = db_day;
    }

    public String getDb_title() {
        return db_title;
    }

    public void setDb_title(String db_title) {
        this.db_title = db_title;
    }
}

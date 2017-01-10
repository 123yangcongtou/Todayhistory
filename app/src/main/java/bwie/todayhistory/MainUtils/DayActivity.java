package bwie.todayhistory.MainUtils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dsw.calendar.component.MonthView;
import com.dsw.calendar.views.GridCalendarView;

import bwie.todayhistory.BeanUtils.MessageEvent;
import bwie.todayhistory.R;
import de.greenrobot.event.EventBus;

public class DayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Button sure= (Button) findViewById(R.id.sure);
        GridCalendarView gridCalendarView = (GridCalendarView) findViewById(R.id.gridMonthView);
        gridCalendarView.setDateClick(new MonthView.IDateClick(){
            @Override
            public void onClickOnDate(final int year, final int month, final int day) {
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(new MessageEvent(year+"",month+"",day+""));
                        finish();
                    }
                });
            }
        });
    }
}

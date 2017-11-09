package co.michaeland.galaxytech.activities;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import co.michaeland.galaxytech.R;

public class AppointmentMaker extends AppCompatActivity
        implements
        ActivityCompat.OnRequestPermissionsResultCallback
{
    final static int MY_PERMISSION_REQUEST= 1;

    Calendar calendar;
    CalendarView calendarView;
    TextView calendarOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_maker);

        calendarOutput = findViewById(R.id.calendarOutput);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setDate(System.currentTimeMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                calendarOutput.setText(dateFormat(calendarView.getDate()));
            }
        });

    }

    public void writeCalendar(){

    }

    public String dateFormat(long date){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd YYYY");
    return simpleDateFormat.format(date).toString();
}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(
                            this,
                            "Permission Granted!!!",
                            Toast.LENGTH_LONG
                    ).show();
                }else {
                    return;
                }

            }
        }

    }

    public void permissionCheck(String permission){
        if (ContextCompat.checkSelfPermission(this,
                permission
        ) != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permission
            )){
                Toast.makeText(this,"Need Calendar Permission", Toast.LENGTH_SHORT).show();

            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{permission},
                        MY_PERMISSION_REQUEST
                );
            }
        }
    }

}

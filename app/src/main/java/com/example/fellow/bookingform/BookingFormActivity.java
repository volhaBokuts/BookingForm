package com.example.fellow.bookingform;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fellow.bookingform.dto.BookingRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingFormActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, surname, nights, wishes;
    TextView date, time;
    RadioGroup gender;
    RadioButton male, female;
    CheckBox breakfast, dinner, supper;
    Spinner cities;
    Button send;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        name = (EditText) findViewById(R.id.eTName);
        surname = (EditText) findViewById(R.id.eTSurname);
        date = (TextView) findViewById(R.id.tVDate);
        time = (TextView) findViewById(R.id.tVTime);
        nights = (EditText) findViewById(R.id.eTNights);
        gender = (RadioGroup) findViewById(R.id.radioGroupGender);
        male = (RadioButton) findViewById(R.id.radioBtMale);
        female = (RadioButton) findViewById(R.id.radioBtFemale);
        breakfast = (CheckBox) findViewById(R.id.checkBoxBreakfast);
        dinner = (CheckBox) findViewById(R.id.checkBoxDinner);
        supper = (CheckBox) findViewById(R.id.checkBoxSupper);
        cities = (Spinner) findViewById(R.id.spinnerCities);
        wishes = (EditText) findViewById(R.id.eTWish);
        send = (Button) findViewById(R.id.btSend);

        if (send != null) {
            send.setOnClickListener(this);
        }
        date.setOnClickListener(this);
        time.setOnClickListener(this);

        setInitialDateTime();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tVDate:
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingFormActivity.this, d,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.tVTime:
                TimePickerDialog timePickerDialog = new TimePickerDialog(BookingFormActivity.this, t,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;
            case R.id.btSend:
                BookingRecord record = new BookingRecord();
                record.setName(name.getText().toString());
                record.setSurname(surname.getText().toString());
                record.setDate(date.getText().toString());
                record.setTime(time.getText().toString());
                record.setNights(nights.getText().toString());
                switch (gender.getCheckedRadioButtonId()) {
                    case R.id.radioBtMale:
                        record.setGender(male.getText().toString());
                        break;
                    case R.id.radioBtFemale:
                        record.setGender(female.getText().toString());
                }
                record.setCity(cities.getSelectedItem().toString());
                record.setWish(wishes.getText().toString());
        }
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {
        date.setText(DateUtils.formatDateTime(this,
                calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
        time.setText(DateUtils.formatDateTime(this, calendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };


    /*@Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        date.setText(DateUtils.formatDateTime(this,
                calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time.setText(DateUtils.formatDateTime(this,
                calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));
    }*/
}

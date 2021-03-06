package com.success_v1.agence;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.success_v1.successCar.R;

public class ReservationStep1 extends Activity{
	private int year;
	private int month;
	private int day;
	private String dateDepart;
	private String dateRetour;
	private String date;
	private RadioGroup radioGroup;
	private Button btnDateDepart;
	private Button btnDateRetour;
	private Button btnSearchCar;
	private int yearDepart;
	private int monthDepart;
	private int dayRetour;
	private int yearRetour;
	private int monthRetour;
	private int dayDepart;
	private int nbBtn;

	static final int DATE_DIALOG_DEPART = 999;
	static final int DATE_DIALOG_RETOUR = 899;

	JSONObject detail_tab = new JSONObject();


	public void ConvertDate(String format, Button btndate)
	{
		date = new SimpleDateFormat(format).format(new Date());
		btndate.setText(date);	
	}

	public Date ConvertDate2(String format, Button btndate)
	{
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		Date inputDate = null;
		try {
			inputDate = fmt.parse(btndate.getText().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return inputDate;
	}
	public void toast(String txt, int leng)
	{
		Toast.makeText(this, txt, leng).show();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reservation_step1);


		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);


		btnDateDepart = (Button) findViewById(R.id.btnDateDeb);
		btnDateRetour = (Button) findViewById(R.id.btnDateRetour);
		btnSearchCar = (Button) findViewById(R.id.btnSearchCar);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

		ConvertDate("dd-MM-yyyy", btnDateDepart);
		ConvertDate("dd-MM-yyyy", btnDateRetour);

		btnDateDepart.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				nbBtn=1;
				showDialog(DATE_DIALOG_DEPART);
			}
		});
		btnDateRetour.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				nbBtn =2;
				showDialog(DATE_DIALOG_RETOUR);
			}
		});

		btnSearchCar.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent listCarActivity = new Intent(getBaseContext(), com.success_v1.vehicule.VehiculeTab.class);

				// Parse the input date
				Date inputDate = ConvertDate2("dd-MM-yyyy", btnDateDepart);
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				dateDepart = fmt.format(inputDate);

				// Parse the input date
				Date inputDate2 = ConvertDate2("dd-MM-yyyy", btnDateRetour);
				SimpleDateFormat fmtt = new SimpleDateFormat("yyyy-MM-dd");
				dateRetour= fmtt.format(inputDate2);

				Date today = new Date();
				
				Integer a = inputDate.compareTo(inputDate2);
				Integer b = inputDate.compareTo(today);
				
				Log.i("today",a.toString());
				Log.i("compare 1",a.toString());
				Log.i("compare 2",b.toString());

				if((inputDate.compareTo(inputDate2) == 1) || (inputDate.compareTo(today) == -1))
				{
					toast("Dates incohérentes", Toast.LENGTH_LONG);				
				}
				else
				{
					listCarActivity.putExtra("dateDepart", dateDepart);
					listCarActivity.putExtra("dateRetour", dateRetour);
					int id = radioGroup.getCheckedRadioButtonId();
					if (id == R.id.rdioTourisme){
						listCarActivity.putExtra("typeVehicule", "Tourisme");
					}
					else if (id==R.id.rdioUtil)
					{
						listCarActivity.putExtra("typeVehicule", "Utilitaire");
					}
					startActivity(listCarActivity);
				}


			}
		});		
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_DEPART:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,day);
		case DATE_DIALOG_RETOUR:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,day);
		}

		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener 
	= new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			switch (nbBtn)
			{
			case 1:
				yearDepart = selectedYear;
				monthDepart = selectedMonth;
				dayDepart = selectedDay;
				// set selected date into textview
				btnDateDepart.setText(new StringBuilder().append(dayDepart).append("-").append(monthDepart + 1).append("-").append(yearDepart).append(" "));
				break;
			case 2:
				yearRetour = selectedYear;
				monthRetour = selectedMonth;
				dayRetour = selectedDay;
				// set selected date into textview
				btnDateRetour.setText(new StringBuilder().append(dayRetour).append("-").append(monthRetour + 1).append("-").append(yearRetour).append(" "));
				break;
			}
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

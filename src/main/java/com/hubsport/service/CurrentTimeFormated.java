package com.hubsport.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("timeService")
@Transactional
public class CurrentTimeFormated {

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat();

	public String time() {
		String timeStamp = new SimpleDateFormat("EEEE , dd MM YYYY").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	
	public String getTime(Date date) {
		String timeStamp = new SimpleDateFormat("EEEE , dd MM YYYY").format(date);
		return timeStamp;
	}
}

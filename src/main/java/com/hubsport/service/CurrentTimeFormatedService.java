package com.hubsport.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("timeService")
@Transactional
public class CurrentTimeFormatedService {

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat();

	public String time() {
		return new SimpleDateFormat("EEEE , dd MM YYYY").format(Calendar.getInstance().getTime());
	}

	public final String getTime(Date date) {
		return new SimpleDateFormat("EEEE , dd MM YYYY").format(date);
	}
}

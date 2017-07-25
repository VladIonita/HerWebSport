package com.hubsport.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
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
	
	
}

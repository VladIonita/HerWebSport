package com.hubsport.service;

import java.util.Calendar;
import java.util.Date;

public class LocalTime {

	public final Date showLocalTime() {
		Calendar cl = Calendar.getInstance();
		Date currentTime = cl.getTime();
		return currentTime;
	}
}

package com.example.demo.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MyScheduler {
	
	@Scheduled(cron="0 0/1 * 1/1 * ?") //sec-min-hour-day-month-year
	public void firstScheduler() {
		System.out.println(new Date());
	}

}

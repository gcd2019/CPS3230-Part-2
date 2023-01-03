package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.*;

public class Task2 {
	
	public static void main(String[] args) throws IOException {
		final Task2 t = new Task2();
		final Application a = t.new Application(false, 0, false);
		t.runSystem(a);
	}
	
	public void runSystem(Application a) throws IOException {
		
		// A user logs in
		a.setStatus(true);
		a.logIn();
		
		// Creates 3 alerts
		for (int i = 0; i < 3; i++)
			createAlertPostRequest(a);
		
		// Views the alerts
		a.setViewStatus(true);
		a.viewAlerts();
		
		// Deletes all the alerts
		createAlertDeleteRequest(a);
		a.setViewStatus(false);
		
		getEventsLog(a);
		
		// Setting viewed to false since alerts were deleted
		a.setStatus(false);
		
		// Log out
		a.logOut();
		
		// Logs in again
		a.setStatus(true);
		a.logIn();
		
		// Creates 7 alerts
		for (int i = 0; i < 7; i++)
			createAlertPostRequest(a);
		
		// Views the alerts
		a.setViewStatus(true);
		a.viewAlerts();
		
		// Deletes all the alerts
		createAlertDeleteRequest(a);
		a.setViewStatus(false);
		
		// Deletes all the alerts again
		// Entering BAD STATE on purpose
		createAlertDeleteRequest(a);
		
	}
	
	public class Application{
		private boolean isLoggedIn;
		private int alerts;
		private boolean viewed;
		
		public Application(final boolean isLoggedIn, final int alerts, final boolean viewed){
			this.isLoggedIn = isLoggedIn;
			this.alerts = alerts;
			this.viewed = viewed;
		}
		
		public void setStatus(boolean isLoggedIn){
			this.isLoggedIn = isLoggedIn;
		}
		
		public boolean getStatus(){
			return isLoggedIn;
		}
		
		public void setAlertNumber(int alerts){
			this.alerts = alerts;
		}
		
		public int getAlertNumber(){
			return alerts;
		}
		
		public void setViewStatus(boolean viewed){
			this.viewed = viewed;
		}
		
		public boolean getViewStatus(){
			return viewed;
		}
		
		public void logIn() {
			System.out.println("User has logged In.");
		}
		
		public void logOut() {
			System.out.println("User has logged Out.");
		}
		
		public void addAlert() {
			System.out.println("1 Alert has been added.");
		}
		
		public void deleteAlerts() {
			System.out.println("Alerts have been deleted.");
		}
		
		public void viewAlerts() {
			System.out.println("Alerts have been viewed.");
		}
	}
	
	public void getEventsLog(Application a) throws IOException {
		URL url = new URL("https://api.marketalertum.com/EventsLog/e7ee93d2-cf55-45da-a41e-6581361e3f20");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
        
		BufferedReader in = new BufferedReader(new InputStreamReader( con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			
			if (inputLine.contains("\"eventLogType\":0") == true){
				System.out.println("Events log message: Alert/s has/have been created.");
			}
			
			if (inputLine.contains("\"eventLogType\":1") == true) {
				System.out.println("Events log message: Alert/s has/have been deleted.");
			}
		}
		
		in.close();
	}
	
	public void createAlertDeleteRequest(Application a) throws IOException {
		URL url = new URL("https://api.marketalertum.com/Alert?userId=e7ee93d2-cf55-45da-a41e-6581361e3f20");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("DELETE");
		
        
		BufferedReader in = new BufferedReader(new InputStreamReader( con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
		
		in.close();
		
		a.setAlertNumber(0);
		a.deleteAlerts();
	}
	
	public void createAlertPostRequest(Application a) throws IOException {
        AlertRequest alertRequest = new AlertRequest();

        alertRequest.setAlertType(5);
        alertRequest.setHeading("Batman: The Long Halloween");
        alertRequest.setDescription("Author: Jeph Loeb");
        alertRequest.setUrl("https://www.bookdepository.com/Batman-Long-Halloween-Jeph-Loeb/9781401232597?ref\u003dgrid-view\u0026qid\u003d1668015004571\u0026sr\u003d1-2");
        alertRequest.setImageUrl("https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/mid/9781/4012/9781401232597.jpg");
        alertRequest.setPostedBy("e7ee93d2-cf55-45da-a41e-6581361e3f20");
        alertRequest.setPriceInCents(3730);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(alertRequest);

		URL url = new URL("https://api.marketalertum.com/Alert");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);
		
		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = jsonRequest.getBytes("utf-8");
		    os.write(input, 0, input.length);			
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader( con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine); 
		}
		
		in.close();
		
		a.setAlertNumber(a.alerts++);
		a.addAlert();
	}
}

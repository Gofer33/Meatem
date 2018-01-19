package announcements;

import java.util.*;

public class Announcement {
	
	private int id;
	private String title;
	private String text;
	private Date date;
	private String author;
	private ArrayList<String> categories;
	private String city;
	private String country;
	private String street;
	private Date meetDate;
	private int userLimit;
	private int userInterested;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getMeetDate() {
		return meetDate;
	}
	public void setMeetDate(Date meetDate) {
		this.meetDate = meetDate;
	}
	public int getUserLimit() {
		return userLimit;
	}
	public void setUserLimit(int userLimit) {
		this.userLimit = userLimit;
	}
	public int getUserInterested() {
		return userInterested;
	}
	public void setUserInterested(int userInterested) {
		this.userInterested = userInterested;
	}
	public Announcement()
	{
		this.categories = new ArrayList<String>();
		this.id=1;
		this.title="Announcement1";
		this.text="text1";
		this.author="author1";
		this.date=new Date();
		this.userLimit = -10;
		this.userInterested = -5;
	}
	public String getTitle()
	{
		return this.title;
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public Date getDate()
	{
		return this.date;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public String getAuthor()
	{
		return this.author;
	}
	
	public ArrayList<String> getCategories()
	{
		return this.categories;
	}
	
	public void setCategories(ArrayList<String> cat)
	{
		this.categories = cat;
	}
	
	

}

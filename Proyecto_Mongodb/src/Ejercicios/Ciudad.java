package Ejercicios;

public class Ciudad {
  private String name;
  private String country;
  private String timezone;
  private long population;
  private float longitude;
  private float latitude;
  
  

  public Ciudad(String name, String country, String timezone, long population, float longitude, float latitude) {
	super();
	this.name = name;
	this.country = country;
	this.timezone = timezone;
	this.population = population;
	this.longitude = longitude;
	this.latitude = latitude;
}
public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }

  public String getTimezone() {
    return timezone;
  }
  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  public long getPopulation() {
    return population;
  }

  public void setPopulation(long population) {
    this.population = population;
  }

  public float getLongitude() {
    return longitude;
  }
  public void setLongitude(float longitude) {
    this.longitude = longitude;
  }

  public float getLatitude() {
    return latitude;
  }
  public void setLatitude(float latitude) {
    this.latitude = latitude;
  }
}


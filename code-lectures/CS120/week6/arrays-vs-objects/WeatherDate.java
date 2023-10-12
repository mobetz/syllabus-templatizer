import java.time.LocalDate;



public class WeatherDate {



    //ATTRIBUTES
    private int temperature;
    private String wind_direction;
    private LocalDate date;


    /*
    One other benefit of classes is they make it easy to change the way individual fields of an object are
    represented without requiring a rewrite of the entire program. If you changed temperature into a number in the
    middle of your array, then the array would no longer only store one type of data, and it would be broken.
     */

    
    /*
    It is perfectly alright to have an object that is nothing but a "data bag" that does nothing but hold some
    attributes with a constructor and a few getters. A lot of objects start like this before we start identifying
    useful behaviors that can be attached to them.
     */
    

    public WeatherDate(int temp, String wind_direction, LocalDate date) {
    	this.temperature = temp;
    	this.wind_direction = wind_direction;
        this.date = date;
    }
    

    public int getTemperature() {
        return this.temperature;
    }

    public String getWindDirection() {
        return this.wind_direction;
    }

    public LocalDate getDate() {
        return this.date;
    }

}
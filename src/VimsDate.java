import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
//test
/*
 * This Class is to be used in conjunction with the AssetRetriever. It's main
 * function is to check whether or not a file date is newer than a given user date
 * of 2 different formats
 */
public class VimsDate
{
	// Create the format we would like the date to be in
	public static final DateFormat standardFormat = new SimpleDateFormat("yyyy/MM/dd");
	private Date assetDate;
	
	// Date object once the string has been parsed
	private Date parseDate;
	
	// Determine if a date is within a year prior to today
	public boolean isCurrentYear(String startDate)
	{
		// Parse the start date to our format
		parseDate = parseStringtoDate(startDate);
		
		// Return if we couldn't parse it
		if(parseDate == null)
			return false;
		
		// Determine what 1 year prior to today is
		Date lastYear;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		
		try
		{
			// Format the date
			lastYear = standardFormat.parse(standardFormat.format(cal.getTime()));
		} 
		catch (ParseException e)
		{
			// Handle errors
			AssetRetriever.status = "An error has occurred in retrieving the asset";
			return false;
		}
		
		// Compare the start to last year
		if(compare(lastYear,parseDate))
		{
			AssetRetriever.status = "Please enter a date within the past year";
			return false;
		}
		
		return true;
	}
	
	// Check if the file requested has a valid date
	public boolean isvalidAsset(File curFile, String startDate)
	{
		// Parse the input date otherwise the data doesn't exist
		if(parseStringtoDate(startDate) == null)
			return false;
		
		parseDate = parseStringtoDate(startDate);
		
		// Compare the file date and input date
		return compare(vimsDateToStandard(dateFromFilename(curFile)),parseDate);
	}
	
	// Converts the vims date to a standard format
	public Date vimsDateToStandard(String vimsDate)
	{
		// Check that the vims date isn't empty
		if(vimsDate.equals(""))
			return null;
		
		String year = "";
		String month = "";
		String day = "";
		String strDate;
		
		Date convertedDate = null;
		
		// Parse the vims date
		for(int i = 0; i < vimsDate.length(); i++)
		{
			if(i < 2)
				year += vimsDate.charAt(i);
			else if(i < 4)
				month += vimsDate.charAt(i);
			else
				day += vimsDate.charAt(i);
		}
		
		// Create a standard date as a string
		strDate = "20" + year + "/" + month + "/" + day;
		
		// Try to convert it to a date of our format
		try
		{
			convertedDate = standardFormat.parse(strDate);
		} 
		catch (ParseException e)
		{
			return null;
		}
		assetDate = convertedDate;
		
		return(convertedDate);
	}
	
	// Parse the date from the vims filename
	public String dateFromFilename(File f)
	{
		// Get the standalone filename
		String fName = f.getName();
		String date = "";

		// Check that it is atleast a vims directory in length
		if(fName.length() >= 35)
		{
			// Grab the 12 digit date from the filename
			for(int i = 23; i < 35; i++)
				date += fName.charAt(i);
		}
		
		// Return the portion of the date that we will use
		return date.substring(0,6);
	}
	
	// Way of formatting the date with our standard
	public String format(Date date)
	{
		return standardFormat.format(date);
	}
	
	// Simple comparison function to determine if the data is new or not
	public boolean compare(Date curFileDate, Date startDate)
	{	
		if(curFileDate == null || startDate == null)
			return false;
		if(curFileDate.compareTo(startDate) > 0)
		{
    		return true;
		}
		else
			return false;
	}
	
	// Return the asset date for the current asset
	public Date getAssetDate()
	{
		return assetDate;
	}
	
	// Get the vims date from the path of a source asset file
	public Date getDateFromSourcePath(File file)
	{
		return vimsDateToStandard(dateFromFilename(file));
	}
	
	// Convert a string in a date format to a Date object
	public Date parseStringtoDate(String date)
	{
		try
		{
			return standardFormat.parse(date);
		} 
		catch (ParseException e)
		{
			return null;
		}
	}
}
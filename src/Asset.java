import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Object to store the information for an asset
public class Asset 
{
        private String asset;
        private Date date;
 
        // Default constructor
        Asset()
        {
        	asset = "";
        	date = null;
        }
        
        // Constructor given the proper format
        public Asset(String asset, Date date) 
        { 
            this.asset = asset;
            this.date = date;
        }
        
        // Constructor which needs to convert the string to a date object
        public Asset(String asset, String date) 
        { 
            this.asset = asset;
            
            try
			{
				this.date = VimsDate.standardFormat.parse(date);
			} 
            catch (ParseException e)
			{
				// Handle errors
            	AssetRetriever.status = "Couldn't parse the date";
			}
        }
        
        // return the asset number
        public String getAsset() 
        {
        	return asset;
        }
        
        // return the date
        public Date getDate() 
        {
        	return date;
        }
        
        // set the asset number
        public void setAsset(String asset)
        {
        	this.asset = asset;
        }
        
        // set the date
        public void setDate(Date date)
        {
        	this.date = date; 
        }
        
        // toString
        public String toString()
        {
        	return asset + "," + VimsDate.standardFormat.format(date) + ",";
        }
}
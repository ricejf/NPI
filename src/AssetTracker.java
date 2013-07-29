import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

// The purpose of this class is to handle the tracking of assets that are sent to PI via the AssetRetriever
public class AssetTracker
{
	// File that we are going to keep track in
	private File file;
	private boolean exists;
	
	// Vector for storing the Records
	private Vector<Asset> assetRecords = new Vector<Asset>();
	
	// Constructor which creates a new file if one doesn't exist
	AssetTracker(File dest)
	{
		exists = false;
		file = new File(dest.toString() + "/SAVED_ASSETS.art");
		
		if (!file.exists()) 
		{
			try
			{
				file.createNewFile();
			} 
			catch (IOException e)
			{
				//Handle errors
				AssetRetriever.status = "Couldn't create the asset tracking file";
			}
		}
	}
	
	// Method for writing the asset data to the file
	public void write(Asset asset)
	{
		try
		{
			PrintWriter out;
			
			// We need to check if the asset already existed in our tracking file, if it did
			// we need to replace it with the newer date otherwise we can just append it to the file
			if(!exists)
			{
				out = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true)));
				out.print(asset);
			}
			else
			{
				// Rewrite the tracking file because we needed to replace an old date
				out = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsoluteFile())));
				
				for(int i = 0; i < assetRecords.size(); i++)
					out.print(assetRecords.elementAt(i));
			}
			
			out.close();
		} 
		catch (IOException e)
		{
			// Handle Errors
			AssetRetriever.status = "Couldn't save the asset information";
		}
	}
	
	// Method which reads all of the data into a vector
	public Vector<Asset> read()
	{
		Scanner read;
		
		assetRecords = new Vector<Asset>();
		String tempAsset;
		String tempDate;
		
		// Simply read the file placing each asset into the vector
		try
		{
			read = new Scanner (file);
			read.useDelimiter(",");
			
			while(read.hasNext())
			{
				tempAsset = read.next();
				tempDate = read.next();
				assetRecords.add(new Asset(tempAsset,tempDate));
			}
			
			return assetRecords;
		} 
		catch (FileNotFoundException e)
		{
			return null;
		}	
	}
	
	// Checks to determine if the data is newer than previously input data
	public boolean isNew(String asset, Date date)
	{
		Asset thisAsset = new Asset();
		VimsDate dateComp = new VimsDate();
		
		// Check each asset for the current date that has been put in PI
		for(int i = 0; i < assetRecords.size(); i++)
		{
			thisAsset = assetRecords.elementAt(i);
			
			// If the assets match and the data is newer we can return true otherwise we reject the data
			if(asset.equals(thisAsset.getAsset()) && dateComp.compare(date, thisAsset.getDate()))
			{
				exists = true;
				assetRecords.add(new Asset(asset, date));
				assetRecords.removeElementAt(i);
				return true;
			}
			else if(asset.equals(thisAsset.getAsset()))
			{
				return false;
			}
		}
		
		return true;
	}
}

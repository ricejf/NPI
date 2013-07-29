import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AssetRetrievalServlet
 */
public class AssetRetrievalServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AssetRetrievalServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// Get the user input
		String assetNum = request.getParameter("asset");
		String date = request.getParameter("date");

		// Check if the input is blank
		if (assetNum.equals(""))
		{
			request.setAttribute("errorMessage", "Asset not specified!");
			RequestDispatcher disp = getServletContext().getRequestDispatcher(
					"/input.jsp");
			disp.forward(request, response);
		} else if (date.equals(""))
		{
			request.setAttribute("errorMessage", "Date not specified!");
			RequestDispatcher disp = getServletContext().getRequestDispatcher(
					"/input.jsp");
			disp.forward(request, response);
		}

		// Start the transfer process
		AssetRetriever retrieve = new AssetRetriever(assetNum, date);
		
		// Get the result status
		String status = retrieve.getStatus();
		
		// Display the status in the designated message
		if(status.contains("successfully"))
			request.setAttribute("successMessage", status);
		else
			request.setAttribute("errorMessage", status);
		
		// Update the page
		RequestDispatcher disp = getServletContext().getRequestDispatcher(
				"/input.jsp");
		disp.include(request, response);
	}
}

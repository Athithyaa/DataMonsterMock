

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rosuda.JRI.Rengine;
import org.rosuda.REngine.REXP;




/**
 * Servlet implementation class MyFirstServlet
 */
@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Rengine re = new Rengine(new String[]{"--vanilla"},false, null);
		re.eval("install.packages(\"NBclust\")");
		re.eval("sample_data <- read.csv(\"C:/Users/Athithyaa/Downloads/file3.csv\",header=F)");
		re.eval("names(sample_data) <- c('ID','PRODUCT',"
				+ "'VENDOR',"
                + "'LPRICE',"
                + "'SPRICE',"
                + "'CATEGORY',"
                + "'BRAND')");
		re.eval("sample_data3<-sample_data$LPRICE");
		re.eval("set.seed(5)");
		re.eval("data.km<- kmeans(sample_data3,5)");
		re.eval("final_output<-cbind(PRODUCT=as.data.frame(sample_data$PRODUCT),CLUSTER=data.km$cluster)");
		re.eval("set.seed(1234)");
		re.eval("names(final_output)<-c('product','CLUSTER')");
		re.eval("package.install('plyr')"
				+ "require(plyr)" );
		re.eval("clusterbr<-ddply(final_output, .(CLUSTER), mutate, count = length(product))");
		re.eval("p<-unique(clusterbr[3])");
		
		re.eval("f2<-subset(final_output,CLUSTER==" + (Integer.parseInt(request.getParameter("firstName")) +1) + ")");
		re.eval("setwd(\"C:/Users/Athithyaa/NewWorkspace/MyfirstWebproj/WebContent\")");
		re.eval("write.csv(p,file=\"file3.csv\")");
		re.eval("write.csv(f2,file=\"file4.csv\")");
		
	  	out.println("<html><body>");
		out.println("<p style=\"color:red;\"> Indix Data Hackathon </p>");
		out.println("Price Vs Price Graph");
		out.println(" <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script> \n"
				+ "<script src=\"http://code.jquery.com/jquery-1.10.1.min.js\"></script>\n"
				+ "<script src=\"jquery.csv-0.71.js\"></script>"				
				+ " <script type=\"text/javascript\">\n"
				+ " google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});"
				+ " google.load(\"visualization\", \"1\", {packages:[\"table\"]});"
						+ "google.setOnLoadCallback(drawChart);"
						+ " function drawChart() {"
						+ " var data = google.visualization.arrayToDataTable(["
						+ "['Task','Hours of data'],"
						+ "['1',  3],"
						+ "['2',  43229],"
						+ "['3',  687],"
						+ "['4',  75],"
						+ "['5',  3]"
						+ "]);"
		         +   "var options = {\n"
		          + "title: 'Cluster Breakdown',\n"
		         + "};\n"
		          + "$.get(\"file2.csv\", function(csvString) {\n"
		          + "var arrayData = $.csv.toArrays(csvString, {onParseValue: $.csv.hooks.castToScalar}); \n"
		          + "var data2 = new google.visualization.arrayToDataTable(arrayData);\n"
		          + "var view = new google.visualization.DataView(data2);\n"
		          + "view.setColumns([0,1]);\n"
			        + "var chart2 = new google.visualization.ScatterChart(document.getElementById('chart_scatter_div'));\n"
                    + "chart2.draw(view, options);});\n"
		        + "var chart = new google.visualization.PieChart(document.getElementById('chart_pie_div'));\n"
		        + "chart.draw(data, options);\n"
		        + "$.get(\"file4.csv\", function(csvString) {\n"
		        + "var tabarrayData = $.csv.toArrays(csvString, {onParseValue: $.csv.hooks.castToScalar}); \n"
		        + "var tabdatarest = new google.visualization.arrayToDataTable(tabarrayData);"
		        + "var table = new google.visualization.Table(document.getElementById('chart_table_div'));"
                + "table.draw(tabdatarest, {showRowNumber: true});});"
		        + "}\n"
		     + "</script>\n"
		     + "<!-- <div id=\"chart_div\" style=\"width: 900px; height: 500px;\"></div> -->\n"
		     + "<div id=\"chart_scatter_div\" style=\"width: 900px; height: 500px;\"></div>"
		     + "<p> Product Category Breakdown </p>"
		     + "<div id=\"chart_pie_div\" style=\"width: 900px; height: 500px;\"></div>"
		     + "<p> Output Table </p>"
		     + "<div id=\"chart_table_div\" style=\"width: 900px; height: 500px;\"></div>"
		     + "</body></html>");
		  
		
		out.println("</html></body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}

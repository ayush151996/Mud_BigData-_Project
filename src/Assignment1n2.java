import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Assignment1n2 {
	static JFrame frame;
    private static final boolean SHOW_LEGEND = false;
    private static final boolean SHOW_TOOLTIPS = false;
    private static final boolean GENERATE_URLS = false;
    
    public static void event1() {
    	final JTextPane textPane;
	     textPane = new JTextPane();
	     frame.add(textPane,BorderLayout.CENTER);
       //Reset textpanes content so on every button click the new content of the read file will be displayed
       textPane.setText("");
       String fileResult = "";     
       String filename = "Mud_Weight.csv"; // This value is to be changed for Task2.
       try {
       BufferedReader csvReader = new BufferedReader(new FileReader(filename));
       String line = null;
       while ((line = csvReader.readLine()) != null) {
           //Do your logic here which information you want to parse from the csv file and which information you want to display in your textpane
           fileResult = fileResult + "\n" +line;
       }
       }
       catch(FileNotFoundException ex) {
           System.err.println("File was not found");
       }
       catch(IOException ioe) {
           System.err.println("There was an error while reading the file");
       }
       textPane.setText(fileResult);
    }
    
    public static void graphshow() {
    	        //Create a frame.
    			frame=new JFrame("MudWeight By Ayush");
    			frame.setSize(1400,1000);
    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			frame.getContentPane().setBackground(Color.white);
    			frame.setLayout(new FlowLayout());
    			//Create buttons.
    			JButton button=new JButton("Display");
    			JButton button2=new JButton("Clear");
    			frame.add(button);
    			frame.add(button2);
    			ImageIcon icon=new ImageIcon("gc.png");
    			JLabel label=new JLabel(icon);
    			frame.add(label);
    			//Display the frame
    			frame.setVisible(true);   
    			button2.addActionListener(new ActionListener() {
    				 public void actionPerformed(ActionEvent e) {
    					 try {
							FileWriter fw = new FileWriter("abc.csv",false);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            }
    			});
    		        button.addActionListener(new ActionListener()  {
    		            public void actionPerformed(ActionEvent e) {
    		            	 event1();
    		            }
    		        });
    		        
    		        frame.add(button,BorderLayout.SOUTH);
    			frame.setVisible(true);
    }
    
    public static void graphcreate()throws IOException {
        FileReader fr;
        fr = new FileReader("Mud_Weight.csv");//Mud_Weight-converted
        BufferedReader br = new BufferedReader(fr);

        // Get the x-axis label from the first token in the first line.
        // and the y-axis label from the last token in the first line.
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, ",");
        String xLabel = st.nextToken();
        String yLabel = st.nextToken();
        while (st.hasMoreTokens()) {
            yLabel = st.nextToken();
        }

        String line2 = br.readLine(); // This line will iterate new line.
        while (st.hasMoreTokens()) {
            yLabel = st.nextToken();
        }
        
        String title = yLabel + " by " + xLabel;

        // Get the data to plot from the remaining lines.
        float minY = Float.MAX_VALUE;
        float maxY = -Float.MAX_VALUE;
        XYSeries series = new XYSeries("?");
        while (true) {
            line = br.readLine();
            if (line == null) {
                break;
            }
            st = new StringTokenizer(line, ",");

            // The first token is the x value.
            String xValue = st.nextToken();

            // The last token is the y value.
            String yValue = "";
            while (st.hasMoreTokens()) {
                yValue = st.nextToken();
            }

            float x = Float.parseFloat(xValue);
            float y = Float.parseFloat(yValue);
            series.add(x, y);

            minY = Math.min(y, minY);
            maxY = Math.max(y, maxY);
        }        

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);       

        JFreeChart chart = ChartFactory.createXYLineChart(
            title, xLabel, yLabel, dataset,
            PlotOrientation.VERTICAL,
            SHOW_LEGEND, SHOW_TOOLTIPS, GENERATE_URLS);
        
        XYPlot plot = chart.getXYPlot();
        plot.getRangeAxis().setRange(minY, maxY);
        
        int width = 1000;
        int height = 600;
        ChartUtilities.saveChartAsPNG(
            new File("gc.png"), chart, width, height);
    }

    public static void main(String[] args) throws IOException {
    	//Using Functional Programming
        graphcreate();
        graphshow();
       
    }
}

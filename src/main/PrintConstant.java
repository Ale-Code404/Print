package main;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

public interface PrintConstant {
    
    	//Document
    	
    	public static final double DEFAULT_PAPER_WIDTH = Ticket.ppi(7.8);
    	public static final double DEFAULT_PAPER_HEIGHT = Ticket.ppi(200);   
	
    	public static final double DEFAULT_PAPER_POSITION_X = 2;
    	public static final double DEFAULT_PAPER_POSITION_Y = 2;
    	
    	public static final double DEFAULT_PAPER_MARGIN = Ticket.ppi(0.2);
    	
    	public static final double DEFAULT_PAPER_PRINTABLE_WIDTH = DEFAULT_PAPER_WIDTH;
    	public static final double DEFAULT_PAPER_PRINTABLE_HEIGHT = DEFAULT_PAPER_HEIGHT;
    	
	//TEXT
	
	public static final int DEFAUT_TEXT_ALIGN = FlowLayout.LEFT;
	public static final String DEFAULT_TEXT_DATA = "No data";
	
	public static final String DEFAULT_TEXT_FONT_NAME = "Segoe UI";
	public static final int DEFAULT_TEXT_FONT_STYLE = Font.PLAIN;
	public static final int DEFAULT_TEXT_FONT_SIZE = 10;
	
	public static final Font DEFAULT_DOCUMENT_FONT = new Font(DEFAULT_TEXT_FONT_NAME,  DEFAULT_TEXT_FONT_STYLE,  DEFAULT_TEXT_FONT_SIZE);
	
	
}

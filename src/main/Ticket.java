package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.print.*;
import javax.swing.*;
import javax.swing.border.*;

import org.json.*;

public class Ticket extends JPanel implements Printable, PrintConstant {
	JFrame container;
	private boolean state;

	public Ticket(String json) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(
				new Dimension((int) PrintConstant.DEFAULT_PAPER_WIDTH, (int) PrintConstant.DEFAULT_PAPER_HEIGHT));
		this.create(json);
		container = new JFrame();
		container.add(this);
		container.pack();
	}

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex == 0) {

			Graphics2D g2 = (Graphics2D) graphics;
			g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			this.printAll(graphics);

			return PAGE_EXISTS;
		} else {
			return NO_SUCH_PAGE;
		}
	}

	public void create(String json) {

		this.state = false;
		JSONArray array = null;
		int jump = 0;

		array = new JSONArray(json);

		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);

			String typeObject = object.getString("object");

			switch (typeObject) {
			case "text": {
				try {

					JLabel label;
					JPanel container;

					if (object.has("data")) {
						label = new JLabel(object.getString("data"));
					} else {
						label = new JLabel(PrintConstant.DEFAULT_TEXT_DATA);
					}

					if (object.has("align")) {
						container = new JPanel(this.getAlign(object.getString("align")));
					} else {
						String notAlign = "error";
						container = new JPanel(this.getAlign(notAlign));
					}

					if (object.has("font")) {
						JSONArray font = object.getJSONArray("font");
						label.setFont(getFont(font));
					} else {
						label.setFont(getFont(null));
					}

					if (jump % 2 == 0) {
						container.setBackground(Color.LIGHT_GRAY);
					}

					container.add(label);
					this.add(container);
					jump++;

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			}
		}
		this.state = true;
	}

	public FlowLayout getAlign(String align) {

		FlowLayout layout;

		switch (align) {
		case "center": {
			return layout = new FlowLayout(FlowLayout.CENTER, 0, 0);
		}
		case "left": {
			return layout = new FlowLayout(FlowLayout.LEFT, 0, 0);
		}
		case "rigth": {
			return layout = new FlowLayout(FlowLayout.RIGHT, 0, 0);
		}
		default:
			return layout = new FlowLayout(PrintConstant.DEFAUT_TEXT_ALIGN, 0, 0);
		}
	}

	public Font getFont(JSONArray font) {

		Font fontLabel = null;

		if (font != null) {

			String fontName = font.getString(0);
			String fontStyle = font.getString(1);
			int fontSize = font.getInt(2);
			int fontStyleInt;

			switch (fontStyle) {

			case "normal":
				fontStyleInt = Font.PLAIN;
				break;
			case "italic":
				fontStyleInt = Font.ITALIC;
				break;
			case "bold":
				fontStyleInt = Font.BOLD;
				break;
			default:
				fontStyleInt = PrintConstant.DEFAULT_TEXT_FONT_STYLE;
				break;
			}

			fontLabel = new Font(fontName, fontStyleInt, fontSize);

		} else {
			fontLabel = PrintConstant.DEFAULT_DOCUMENT_FONT;
		}

		return fontLabel;
	}

	public PageFormat getPageFormat() {
		PageFormat ticket = new PageFormat();
		Paper paperTicket = ticket.getPaper();

		double width = PrintConstant.DEFAULT_PAPER_WIDTH;
		double height = PrintConstant.DEFAULT_PAPER_HEIGHT;

		double initialPosX = PrintConstant.DEFAULT_PAPER_POSITION_X;
		double initialPosY = PrintConstant.DEFAULT_PAPER_POSITION_Y;

		double printableX = PrintConstant.DEFAULT_PAPER_PRINTABLE_WIDTH;
		double printableY = PrintConstant.DEFAULT_PAPER_PRINTABLE_HEIGHT;

		paperTicket.setSize(width, height);
		paperTicket.setImageableArea(initialPosX, initialPosY, printableX, printableY);

		ticket.setOrientation(PageFormat.PORTRAIT);
		ticket.setPaper(paperTicket);

		return ticket;
	}

	public static double ppi(double cm) {
		return (cm * 0.393701) * 72;
	}
}

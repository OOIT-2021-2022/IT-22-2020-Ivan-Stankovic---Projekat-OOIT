package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Point;
import geometry.Shape;

public class PnlDrawing extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private int i;
	private Shape selectedShape = null;

	/**
	 * Create the panel.
	 */
	public PnlDrawing() {
		setBackground(Color.WHITE);
	}
	
	public void addShape(Shape shape) {
		shapes.add(shape);
		repaint();
	}
	
	public void deselect() {
	    shapes.forEach(shape -> shape.setSelected(false));
	    repaint();
	}
	
	public void select(Point point) {
	    for (int i = shapes.size() - 1; i >= 0; i--) {
	        Shape shape = shapes.get(i);
	        if (shape.contains(point.getX(), point.getY())) {
	            if (shape == selectedShape) {
	                shape.setSelected(false);
	                selectedShape = null; 
	            } else {
	                if (selectedShape != null) {
	                    selectedShape.setSelected(false);
	                }
	                shape.setSelected(true);
	                selectedShape = shape;
	            }
	            repaint();
	            return;
	        }
	    }
	    if (selectedShape != null) {
	        selectedShape.setSelected(false);
	        selectedShape = null;
	        repaint();
	    }
	}
	
	public int getSelected() {
		for(int i = 0; i < shapes.size(); i++) {
			if(shapes.get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}
	
	public Shape getShape(int index) {
		Shape shape = shapes.get(index);
		return shape;
	}
	
	public void setShape(int index, Shape shape) {
		shapes.set(index, shape);
		repaint();
	}
	
	public void removeSelected() {
	    for (int i = shapes.size() - 1; i >= 0; i--) {
	        if (shapes.get(i).isSelected()) {
	            shapes.remove(i);
	        }
	    }
	    repaint();
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = shapes.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
		
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
}

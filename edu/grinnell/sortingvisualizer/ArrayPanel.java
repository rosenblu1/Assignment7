package grinnell.sortingvisualizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

    private NoteIndices notes;
    
    /**
     * Constructs a new ArrayPanel that renders the given note indices to
     * the screen.
     * @param notes the indices to render
     * @param width the width of the panel
     * @param height the height of the panel
     */
    public ArrayPanel(NoteIndices notes, int width, int height) {
        this.notes = notes;
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
       g.setColor(Color.BLACK);
       int barWidth = this.getWidth() / notes.arr.length;
       int scalar = this.getHeight() / notes.arr.length;
       
       for(int x = 0, i = 0; x <= this.getWidth() - barWidth && i < notes.arr.length ; x += barWidth, i++) {
         int h = notes.arr[i] * scalar + 1;
         if (notes.isHighlighted(i)) {
           g.setColor(Color.BLUE);
         }
         g.fillRect(x, 0, barWidth, h);
         g.setColor(Color.BLACK);
       } // for
       
    } // paintComponent(Graphics)
}
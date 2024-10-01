/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package simulation;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author emmabian
 */
public class DisplayPanel extends javax.swing.JPanel implements MouseListener {

    Simulation sim = new Simulation();
    boolean filled = false;
    int xClick, yClick;
    int rowClick, colClick;
    
    /**
     * Creates new form DisplayPanel
     */
    public DisplayPanel() {
        initComponents();
        addMouseListener(this);
    }

    public void start() {
        sim.resetGrid(sim.grid);
        sim.resetGrid(sim.newGrid);
        
        
        sim.generateNew(sim.grid, sim.numPredator, sim.numPrey);
        filled = true;
        
        repaint();
    }
    
    public void reset() {
        filled = false;
        repaint();
    }
    
    public void update() {
        sim.updateGrid(sim.grid, sim.newGrid);
        repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        xClick = e.getX();
        yClick = e.getY();
        
        colClick = xClick/25;
        rowClick = yClick/25;
        
        if (sim.grid[rowClick][colClick] == -2) {
            sim.grid[rowClick][colClick] = -1;
        } else if (sim.grid[rowClick][colClick] == -1) {
            sim.grid[rowClick][colClick] = 1;
        } else {
            sim.grid[rowClick][colClick] = -2;
        }
        
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        System.out.println("paint");
        for (int i=1;i<sim.grid.length;i++) {
            g.drawLine(i*25, 0, i*25, getHeight());
        }
        
        for (int i=1;i<sim.grid[0].length;i++) {
            g.drawLine(0, i*25, getWidth(), i*25);
        }
        
        if (filled) {
            for (int i=0;i<sim.grid.length;i++) {
                for (int j=0;j<sim.grid[0].length;j++) {

                    //-2 means that there is a blank space, -1 means that there is a prey, all positive numbers indicate predator
                    if (sim.grid[i][j] == -2) {

                    } else if (sim.grid[i][j] == -1) {
                        g.fillOval(j*25 + 5, i*25 + 5, 15, 15);
                    } else if (sim.grid[i][j] > 0) {
                        g.fillRect(j*25 + 5, i*25 + 5, 15, 15);
                    }

                }
            }
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

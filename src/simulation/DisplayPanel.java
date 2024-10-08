/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package simulation;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;

/**
 *
 * @author emmabian
 */
public class DisplayPanel extends javax.swing.JPanel implements MouseListener {

    //initialize variables
    Timer t;
    
    Simulation sim = new Simulation();
    boolean filled = false;
    int generate = -1;
    int xClick, yClick;
    int rowClick, colClick;
    
    /**
     * Creates new form DisplayPanel
     */
    public DisplayPanel() {
        initComponents();
        addMouseListener(this);
        
        //update every 100 milliseconds
        t = new Timer(100, new TimerTick());
        
    }
    
    /**
     * When the start button is pressed, start the simulation
     */
    public void start() {
        
        //start the timer
        t.start();
        
        if (generate == -1) {
            //reset all grids to blank
            sim.resetGrid(sim.grid);
            sim.resetGrid(sim.newGrid);

            //generate initial verison of the grid
            sim.generateNew(sim.grid, sim.numPredator, sim.numPrey);
            filled = true;
        }
        
        repaint();
    }
    
    /**
     * when the stop button is pressed, stop and reset the simulation grid
     */
    public void reset() {
        //stop the timer
        t.stop();
        
        filled = false;
        repaint();
    }
    
    /**
     * at every timer tick, the grid is updated
     * every time step button is pressed, the grid is also updated
     */
    public void update() {
        //update the grid and repaint
        sim.updateGrid(sim.grid, sim.newGrid);
        repaint();
    }
    
    /**
     * generate common patterns through a rotational system
     * pressing the buttons more time would generate different patters
     * the number of predator and prey are not necessarily the same as a completely randomized grid
     */
    public void generate() {
        
        //reset all grid
        sim.resetGrid(sim.grid);
        sim.resetGrid(sim.newGrid);
        
        //generate the grid according to the previous grid to create a rotational system
        if (generate == 0) {
            sim.generateA(sim.grid, sim.numPredator, sim.numPrey);
        } else if (generate == 1) {
            sim.generateB(sim.grid, sim.numPredator, sim.numPrey);
        } else if (generate == 2) {
            sim.generateC(sim.grid, sim.numPredator, sim.numPrey);
        } else if (generate == 3) {
            sim.generateD(sim.grid, sim.numPredator, sim.numPrey);
        }
        
        //repaint it to the screen
        filled = true;
        repaint();
    }
    
    /**
     * using the slider, the user can change the speed at which the simulation runs at/updates
     * @param speed     interval (in milliseconds) at which the timer ticks
     */
    public void setSpeed(int speed) {
        
        //allow users to adjust speed with the slider
        t.setDelay(speed);
    }
    
    /**
     * every time the mouse is clicked, update the grid
     * allows users to manually edit each spot on the grid
     * every time they click, depending on the previous element in that grid, it will update accordingly
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        
        //change the grid that gets clicked
        xClick = e.getX();
        yClick = e.getY();
        
        colClick = xClick/25;
        rowClick = yClick/25;
        
        //works on a rotational system
        //if it was blank, add a prey
        //on the second click, add a predator
        //on the third click, return to a blank spot
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
        
        //draw grid lines
        for (int i=1;i<sim.grid.length;i++) {
            g.drawLine(i*25, 0, i*25, getHeight());
        }
        
        for (int i=1;i<sim.grid[0].length;i++) {
            g.drawLine(0, i*25, getWidth(), i*25);
        }
        
        //if the simulation started, draw in the predator and prey in the appropriate locations
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
    
    private class TimerTick implements ActionListener {

        //on each timer tick, update the grid
        public void actionPerformed(ActionEvent ae) {
            update();
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import utils.CardSwitcher;

/**
 *
 * @author emmabian
 */
public class Panel extends javax.swing.JPanel {

    CardSwitcher switcher = null;
    Timer t = new Timer(100, new Panel.TimerTick());
    
    boolean running = false;
    boolean paused = false;
    
    /**
     * Creates new form Panel
     */
    public Panel(CardSwitcher p) {
        initComponents();
        
        switcher = p;
        t.start();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        startStopButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        speedSlider = new javax.swing.JSlider();
        predatorLabel = new javax.swing.JLabel();
        preyLabel = new javax.swing.JLabel();
        predatorCount = new javax.swing.JLabel();
        preyCount = new javax.swing.JLabel();
        displayPanel1 = new simulation.DisplayPanel();

        jPanel1.setBackground(new java.awt.Color(169, 169, 169));

        startStopButton.setText("Start");
        startStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStopButtonActionPerformed(evt);
            }
        });

        stepButton.setText("Step");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("Pause");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });

        predatorLabel.setText("Predator Count:");

        preyLabel.setText("Prey Count:");

        predatorCount.setText("0");

        preyCount.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(startStopButton)
                        .addGap(18, 18, 18)
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(pauseButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stepButton)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(predatorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(predatorCount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(preyLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(preyCount)
                        .addGap(102, 102, 102))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startStopButton)
                    .addComponent(stepButton)
                    .addComponent(pauseButton)
                    .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(predatorLabel)
                    .addComponent(preyLabel)
                    .addComponent(predatorCount)
                    .addComponent(preyCount))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout displayPanel1Layout = new javax.swing.GroupLayout(displayPanel1);
        displayPanel1.setLayout(displayPanel1Layout);
        displayPanel1Layout.setHorizontalGroup(
            displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        displayPanel1Layout.setVerticalGroup(
            displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(displayPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void startStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStopButtonActionPerformed
        if (!running) {
            //if it was not running before, start the program and set the button to "stop"
            displayPanel1.start();
            startStopButton.setText("Stop");
            running = true;
        } else if (running) {
            //if it was running before, reset the program to its starting position
            displayPanel1.reset();
            startStopButton.setText("Start");
            running = false;
            //sets predator and prey counter to zero
            resetCount();
        }
    }//GEN-LAST:event_startStopButtonActionPerformed

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        //update the grid by one step
        displayPanel1.update();
    }//GEN-LAST:event_stepButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        if (!paused) {
            //if the program was running, stop the timer so the frame freezes
            displayPanel1.t.stop();
            pauseButton.setText("Resume");
            paused = true;
        } else if (paused) {
            //if the program was paused, resume
            displayPanel1.t.start();
            pauseButton.setText("Pause");
            paused = false;
        }
        
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedSliderStateChanged
        //set the speed to 250 minus 2 times the slider value (0 to 100)
        displayPanel1.setSpeed(250 - 2*speedSlider.getValue());
    }//GEN-LAST:event_speedSliderStateChanged
    
    private void resetCount() {
        preyCount.setText("0");
        predatorCount.setText("0");
    }
    
    private void updateCount() {
        
        //find the number of predator and prey and update the counter display
        int numPrey = findNumPrey();
        int numPredator = findNumPredator();
        
        preyCount.setText("" + numPrey);
        predatorCount.setText("" + numPredator);
    }
    
    private int findNumPrey() {
        int count = 0;
        int[][] grid = displayPanel1.sim.grid;
        
        //loop through the grid and return the number of prey
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                if (grid[i][j] == -1) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private int findNumPredator() {
        int count = 0;
        int[][] grid = displayPanel1.sim.grid;
        
        //loop through the grid and return the number of predator
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                if (grid[i][j] > 0) {
                    count++;
                }
            }
        }

        return count;
    }
    
    private class TimerTick implements ActionListener {

        //at every timer tick, update the predator and prey counter display
        public void actionPerformed(ActionEvent ae) {
            updateCount();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private simulation.DisplayPanel displayPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton pauseButton;
    private javax.swing.JLabel predatorCount;
    private javax.swing.JLabel predatorLabel;
    private javax.swing.JLabel preyCount;
    private javax.swing.JLabel preyLabel;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JButton startStopButton;
    private javax.swing.JButton stepButton;
    // End of variables declaration//GEN-END:variables
}

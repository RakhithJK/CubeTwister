/*
 * @(#)PreferencesCachesPanel.java
 * Copyright (c) 2010 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */
package ch.randelshofer.cubetwister;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PreferencesCachesPanel.
 *
 * @author Werner Randelshofer
 * @version 1.0 2010-08-12 Created.
 */
public class PreferencesCachesPanel extends javax.swing.JPanel {
    private final static long serialVersionUID = 1L;

    /** Creates new form PreferencesCachesPanel */
    public PreferencesCachesPanel() {
        initComponents();
            File tableDir = new File(
                    System.getProperty("user.home")
                    + File.separatorChar + "Library"
                    + File.separatorChar + "Caches"
                    + File.separatorChar + "ch.randelshofer.cubetwister"
                    + File.separatorChar + "RubiksCube");
        try {
            tableDir = tableDir.getCanonicalFile();
        } catch (IOException ex) {
            // nothing to do.
        }

        cachePathField.setText(tableDir.toString());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cachePathField = new javax.swing.JTextField();

        jLabel1.setText("Folder for cache files");

        cachePathField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cachePathField, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cachePathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cachePathField;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}

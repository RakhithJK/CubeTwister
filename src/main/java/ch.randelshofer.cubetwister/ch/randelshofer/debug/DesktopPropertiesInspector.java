/* @(#)UIDefaultsInspector.java
 * Copyright (c) 2001 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.debug;

//import ch.randelshofer.gui.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Displays the current desktop properties of java.awt.Toolkit.
 *
 * @author  Werner Randelshofer
 */
public class DesktopPropertiesInspector extends javax.swing.JPanel {
    private final static long serialVersionUID = 1L;
    
    /** Creates new form UIDefaultsInspector */
    public DesktopPropertiesInspector() {
        initComponents();
        
        table.setModel(new DesktopPropertiesTableModel());
        UIDefaultsCellRenderer r = new UIDefaultsCellRenderer();
        r.setFont(new Font("Dialog", Font.PLAIN, 22));
        table.getColumnModel().getColumn(0).setCellRenderer(r);
        table.getColumnModel().getColumn(1).setCellRenderer(r);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { public void run() {

        JFrame f = new JFrame("Desktop Properties Inspector");
        f.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent evt) { System.exit(0); } } );
        f.getContentPane().add(new DesktopPropertiesInspector());
        f.setSize(400, 300);
        f.setVisible(true);
        }});
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        scrollPane.setFont(new java.awt.Font("Dialog", 0, 11));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPane.setViewportView(table);

        add(scrollPane, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
    
}

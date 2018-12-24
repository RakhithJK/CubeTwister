/* @(#)SystemPropertiesInspector.java
 * Copyright (c) 2001 Werner Randelshofer, Switzerland. MIT License.
 */


package ch.randelshofer.debug;

import java.awt.*;
import java.awt.event.*;
import java.security.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * Displays the current system properties.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 * <br>1.0 2002-05-10 Created.
 */
public class SystemPropertiesInspector extends javax.swing.JPanel {
    private final static long serialVersionUID = 1L;
    private static class PropertiesTableModel extends AbstractTableModel {
    private final static long serialVersionUID = 1L;
        private String[][] data;
        private final static String[] columnNames = { "Key", "Value" };
        
        public PropertiesTableModel(Properties p) {
            data = new String[p.size()][2];
            int i = 0;
            for (Map.Entry<Object, Object> entry : p.entrySet()) {
                data[i][0] = (String) entry.getKey();
                data[i++][1] = (String) entry.getValue();
            }
            
            Arrays.sort(data, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    return  o1[0].compareTo(o2[0]);
                }
            });
            
        }
        
        public int getColumnCount() {
            return 2;
        }
        
        public int getRowCount() {
            return data.length;
        }
        
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
        
        public String getColumnName(int col) {
            return columnNames[col];
        }
        
    }
    
    /** Creates a new instance. */
    public SystemPropertiesInspector() {
        initComponents();
        Properties p;
        try {
            p = System.getProperties();
        } catch (AccessControlException e) {
            p = new Properties();
            p.put("AccessControlException", e.getMessage());
        }
        PropertiesTableModel m = new PropertiesTableModel(p);
        table.setModel(m);
table.getTableHeader().setFont(new Font("Lucida Grande", Font.PLAIN, 11));        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { public void run() {

        JFrame f = new JFrame("System Properties Inspector");
        f.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent evt) { System.exit(0); } } );
        f.getContentPane().add(new SystemPropertiesInspector());
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
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        table.setPreferredScrollableViewportSize(new java.awt.Dimension(300, 250));
        scrollPane.setViewportView(table);

        add(scrollPane, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
    
}

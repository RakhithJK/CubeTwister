/* @(#)ClipboardInspector.java
 * Copyright (c) 2001 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.debug;

import java.io.*;

import ch.randelshofer.binary.BinaryPanel;
import ch.randelshofer.binary.ByteArrayBinaryModel;
import ch.randelshofer.gui.SwingWorker;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
/**
 *
 * @author  Werner Randelshofer
 */
public class ClipboardInspector extends javax.swing.JPanel implements ListSelectionListener {
    private final static long serialVersionUID = 1L;
    private DefaultListModel listModel;
    private Transferable transferable;

    /** Creates new form ClipboardInspector */
    public ClipboardInspector() {
        initComponents();
        listModel = new DefaultListModel();
        list.setModel(listModel);
        
        list.addListSelectionListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel4 = new javax.swing.JPanel();
        readClipboardButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        dataFlavorsScrollPane = new javax.swing.JScrollPane();
        list = new javax.swing.JList();
        dataFlavorsLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        objectLabel = new javax.swing.JLabel();
        objectScrollPane = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        readClipboardButton.setText("Read Clipboard");
        readClipboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readClipboard(evt);
            }
        });

        jPanel4.add(readClipboardButton);

        saveButton.setText("Save...");
        saveButton.setEnabled(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save(evt);
            }
        });

        jPanel4.add(saveButton);

        add(jPanel4, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setOneTouchExpandable(true);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(150, 100));
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));
        dataFlavorsScrollPane.setPreferredSize(new java.awt.Dimension(100, 100));
        list.setFont(new java.awt.Font("Dialog", 0, 11));
        dataFlavorsScrollPane.setViewportView(list);

        jPanel2.add(dataFlavorsScrollPane, java.awt.BorderLayout.CENTER);

        dataFlavorsLabel.setText("Data Flavors");
        dataFlavorsLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        jPanel2.add(dataFlavorsLabel, java.awt.BorderLayout.NORTH);

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(0, 0));
        objectLabel.setText("Transfer Data");
        objectLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        jPanel3.add(objectLabel, java.awt.BorderLayout.NORTH);

        objectScrollPane.setPreferredSize(new java.awt.Dimension(100, 100));
        objectScrollPane.setViewportView(jPanel5);

        jPanel3.add(objectScrollPane, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel3);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    private JFileChooser fileChooser;
    private void save(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save
        if (fileChooser == null) fileChooser = new JFileChooser();
        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal != JFileChooser.APPROVE_OPTION) return;
        
        final File file = fileChooser.getSelectedFile(); 
        
        new SwingWorker() {
            public Object construct() {
                
                Object value = list.getSelectedValue();
                if (value instanceof FlavorItem) {
                    DataFlavor flavor = ((FlavorItem) value).getFlavor();

                    OutputStream out = null;
                    try {
                        out = new FileOutputStream(file);
                        
                        Object data = transferable.getTransferData(flavor);
                        if (data instanceof InputStream) {
                            InputStream in = (InputStream) data;
                            byte[] buf = new byte[512];
                            int len;
                            while (-1 != (len = in.read(buf, 0, 512))) {
                                out.write(buf, 0, len);
                            }
                            in.close();
                        } else if (data instanceof Reader) {
                            Reader r = (Reader) data;
                            Writer w = new OutputStreamWriter(out);
                            char[] cbuf = new char[512];
                            int len;
                            while (-1 != (len = r.read(cbuf, 0, 512))) {
                                w.write(cbuf, 0, len);
                            }
                            w.close();
                            r.close();
                        } else {
                            return new Exception("can't write this flavor:"+data.getClass());
                        }

                    } catch (Throwable e) {
                        return e;
                    } finally {
                        if (out != null) try {out.close();} catch (IOException e) {}
                    }
                }
                return null;
            }
            public void finished() {
                Object value = getValue();
                if (value instanceof Throwable) {
                    JOptionPane.showMessageDialog(ClipboardInspector.this, ((Throwable) value).getMessage(), "Save failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.start();

    }//GEN-LAST:event_save

    private void readClipboard(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readClipboard
        JPanel p = new JPanel();
        objectScrollPane.setViewportView(p);
        listModel.clear();
        transferable = getToolkit().getSystemClipboard().getContents(this);
        if (transferable != null) {
            DataFlavor[] df = transferable.getTransferDataFlavors();
            for (int i=0; i < df.length; i++) 
                listModel.addElement(new FlavorItem(df[i]));
        } else {
            listModel.addElement("Clipboard is Empty");
        }
        
    }//GEN-LAST:event_readClipboard

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { public void run() {

        JFrame f = new JFrame("Clipboard Inspector");
        f.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent evt) { System.exit(0); } } );
        f.getContentPane().add(new ClipboardInspector());
        f.pack();
        f.setVisible(true);
        }});
    }

    /**
     * Called whenever the value of the selection changes.
     * @param evt the event that characterizes the change.
     */
    public void valueChanged(final ListSelectionEvent evt) {
        saveButton.setEnabled(list.getSelectedIndices().length == 1);
        
        new SwingWorker() {
            public Object construct() {
                Object value = list.getSelectedValue();
                if (value instanceof FlavorItem) {
                    DataFlavor flavor = ((FlavorItem) value).getFlavor();

                    try {
                        Object data = transferable.getTransferData(flavor);
                        if (data instanceof Image) {
                            JLabel l = new JLabel(new ImageIcon((Image) data));
                            l.setHorizontalAlignment(JLabel.CENTER);
                            return l;
                       } if (flavor.isFlavorSerializedObjectType()) {
                            JTextArea l = new JTextArea(data.toString());
                            l.setFont(new Font("Dialog", Font.PLAIN, 11));
                            l.setEditable(false);
                            return l;
                        } else if (flavor.isFlavorJavaFileListType()) {
                            JTextArea l = new JTextArea(data.toString());
                            l.setFont(new Font("Dialog", Font.PLAIN, 11));
                            l.setEditable(false);
                            return l;
                        } else if (flavor.isFlavorRemoteObjectType()) {
                            JTextArea l = new JTextArea(data.toString());
                            l.setFont(new Font("Dialog", Font.PLAIN, 11));
                            l.setEditable(false);
                            return l;
                        } else if (data instanceof String) {
                            JTextArea l = new JTextArea((String) data);
                            l.setFont(new Font("Dialog", Font.PLAIN, 11));
                            l.setEditable(false);
                            return l;
                        } else if (data instanceof InputStream) {
                            /*
                            JTextPane l = new JTextPane();
                            l.setFont(new Font("Dialog", Font.PLAIN, 11));
                            l.read((InputStream) transferable.getTransferData(flavor), flavor.getMimeType());
                            return l;
                             */
                            InputStream in = (InputStream) data;
                            ByteArrayOutputStream out = new ByteArrayOutputStream();
                            byte[] buf = new byte[50];
                            int len;
                            while (-1 != (len = in.read(buf, 0, 50))) {
                                out.write(buf, 0, len);
                            }
                            in.close();
                            out.close();
                            BinaryPanel l = new BinaryPanel();
                            l.setFont(new Font("Monospaced", Font.PLAIN, 10));
                            l.setModel(new ByteArrayBinaryModel(out.toByteArray()));
                            return l;
                        } else if (data instanceof Reader) {
                            Reader r = (Reader) data;
                            StringWriter w = new StringWriter();
                            char[] cbuf = new char[50];
                            int len;
                            while (-1 != (len = r.read(cbuf, 0, 50))) {
                                w.write(cbuf, 0, len);
                            }
                            w.close();
                            r.close();
                            JTextArea l = new JTextArea(w.toString());
                            l.setFont(new Font("Dialog", Font.PLAIN, 11));
                            return l;
                        } else {
                            JTextArea l = new JTextArea(data.toString());
                            l.setFont(new Font("Dialog", Font.PLAIN, 11));
                            l.setEditable(false);
                            return l;
                        }

                    } catch (Throwable e) {
                        e.printStackTrace();
                        JTextArea l = new JTextArea("Error:\n   "+e.toString()+"\nRepresentation Class:\n   "+flavor.getRepresentationClass().toString());
                        l.setEditable(false);
                        l.setFont(new Font("Dialog", Font.PLAIN, 11));
                        l.setForeground(Color.red);
                        return l;
                    }

                } else {
                    JPanel p = new JPanel();
                    return p;

                }
            }
            public void finished() {
                Object value = getValue();
                if (value instanceof Component) {
                    objectScrollPane.setViewportView((Component) value);
                    objectScrollPane.validate();
                    objectScrollPane.repaint();
                }
            }
        }.start();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton readClipboardButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JScrollPane dataFlavorsScrollPane;
    private javax.swing.JList list;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel objectLabel;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel dataFlavorsLabel;
    private javax.swing.JScrollPane objectScrollPane;
    // End of variables declaration//GEN-END:variables

    private static class FlavorItem {
        private DataFlavor dataFlavor;
        public FlavorItem(DataFlavor df) {
            this.dataFlavor = df;
        }
        public String toString() {
            return dataFlavor.getHumanPresentableName();
        }
        public DataFlavor getFlavor() {
            return dataFlavor;
        }
    }
}

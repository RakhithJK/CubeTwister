/*
 * @(#)InfoView.java  1.0.2  2004-02-23
 * Copyright (c) 2001 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */
package ch.randelshofer.cubetwister.doc;

import ch.randelshofer.gui.event.*;
import ch.randelshofer.gui.*;
import ch.randelshofer.rubik.*;
import ch.randelshofer.rubik.parser.*;
import ch.randelshofer.undo.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 * Info view for notes, author and creation date of a document element. 
 *
 * @author  Werner Randelshofer
 * @version 1.0.2 2004-02-23 Method updateEnabled updates now the enabled state 
 * of all child components.
 * <br>1.0.1 2003-08-09 Line wrapping activated.
 * <br>1.0 2001-07-19 Created.
 */
public class InfoView extends javax.swing.JPanel implements Undoable, EntityView {
    private final static long serialVersionUID = 1L;
    private ResourceBundleUtil labels;
    private InfoModel model;

    /** Creates new form CubeScriptView */
    public InfoView() {
        init();
        setModel(null);
    }

    private void init() {
        labels = ResourceBundleUtil.getBundle("ch.randelshofer.cubetwister.doc.Labels");
        initComponents();

        Font applicationFont = Fonts.getApplicationFont();
        authorLabel.setFont(applicationFont);
        authorTextField.setFont(applicationFont);
        dateLabel.setFont(applicationFont);
        dateTextField.setFont(applicationFont);
        descriptionTextArea.setFont(applicationFont);

        if ("Windows".equals(UIManager.getLookAndFeel().getID())) {
            setBorder(new EmptyBorder(6,6,8,7));
        }
    }

    /**
     * Adds an UndoableEditListener.
     */
    public void addUndoableEditListener(UndoableEditListener listener) {
    }

    /**
     * Removes an UndoableEditListener.
     */
    public void removeUndoableEditListener(UndoableEditListener listener) {
    }

    public void setModel(InfoModel s) {
        model = s;
        if (model == null) {
            descriptionTextArea.setDocument(new PlainDocument());
            authorTextField.setDocument(new PlainDocument());
            dateTextField.setDocument(new PlainDocument());
        } else {
            descriptionTextArea.setDocument(model.getDescriptionDocument());
            authorTextField.setDocument(model.getAuthorDocument());
            dateTextField.setDocument(model.getDateDocument());
        }
        updateEnabled();
    }

    public void setEnabled(boolean b) {
        super.setEnabled(b);
        updateEnabled();
    }

    public void updateEnabled() {
        boolean b = model != null && isEnabled();

        java.awt.Component[] c = getComponents();
        for (int i = 0; i < c.length; i++) {
            c[i].setEnabled(b);
        }
        descriptionTextArea.setEnabled(b);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        prefixSuffixButtonGroup = new javax.swing.ButtonGroup();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        authorLabel = new javax.swing.JLabel();
        authorTextField = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        descriptionScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        descriptionTextArea.setColumns(2);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionScrollPane.setViewportView(descriptionTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(descriptionScrollPane, gridBagConstraints);

        authorLabel.setLabelFor(authorTextField);
        authorLabel.setText(labels.getString("author")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        add(authorLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 0);
        add(authorTextField, gridBagConstraints);

        dateLabel.setLabelFor(dateTextField);
        dateLabel.setText(labels.getString("date")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 6, 0);
        add(dateLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 12, 6, 0);
        add(dateTextField, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public JComponent getViewComponent() {
        return this;
    }

    public void setModel(EntityModel newValue) {
        setModel((InfoModel) newValue);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorLabel;
    private javax.swing.JTextField authorTextField;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.ButtonGroup prefixSuffixButtonGroup;
    // End of variables declaration//GEN-END:variables
}

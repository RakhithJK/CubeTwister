/*
/*
 * @(#)CubeOptionsView.java  3.0  2008-12-23
 * Copyright (c) 2006 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */

package ch.randelshofer.cubetwister.doc;

import ch.randelshofer.undo.*;

import java.util.prefs.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
/**
 * CubeOptionsView.
 *
 * @author  Werner Randelshofer
 * @version 3.0 2008-12-23 Moved CubeStickersImageView into CubeStickersView.
 * <br>2.0 2008-04-28 Added CubeAnimationView. Reworked lazy generation
 * of views. 
 * <br>1.0 January 31, 2006 Created.
 */
public class CubeOptionsView extends JPanel implements EntityView, Undoable {
    private final static long serialVersionUID = 1L;
    private CubeModel model;
    private Preferences prefs;
    
    /** Creates new form. */
    public CubeOptionsView() {
        prefs = Preferences.userNodeForPackage(getClass());

        initComponents();
        tabbedPane.putClientProperty("Quaqua.TabbedPane.shortenTabs", Boolean.FALSE);
        tabbedPane.putClientProperty("Quaqua.TabbedPane.contentBorderPainted", Boolean.FALSE);
        int selectedTab = prefs.getInt("CubeOptionsView.selectedTab", 0);
        tabbedPane.setSelectedIndex(Math.max(0, Math.min(tabbedPane.getTabCount() - 1, selectedTab)));
        
        animationView.setViewClassName("ch.randelshofer.cubetwister.doc.CubeAnimationView");
        dimensionsView.setViewClassName("ch.randelshofer.cubetwister.doc.CubeDimensionsView");
        backgroundView.setViewClassName("ch.randelshofer.cubetwister.doc.CubeBackgroundView");
         tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
             prefs.putInt("CubeOptionsView.selectedTab", tabbedPane.getSelectedIndex());
            }
        });
        if ("Windows".equals(UIManager.getLookAndFeel().getID())) {
            setBorder(new EmptyBorder(6,6,8,7));
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        animationView = new ch.randelshofer.cubetwister.doc.LazyEntityView();
        dimensionsView = new ch.randelshofer.cubetwister.doc.LazyEntityView();
        backgroundView = new ch.randelshofer.cubetwister.doc.LazyEntityView();

        tabbedPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addTab("Animation", animationView);
        tabbedPane.addTab("Dimensions", dimensionsView);
        tabbedPane.addTab("Background", backgroundView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setModel(EntityModel newValue) {
     if (model != null) {
     } 
     model = (CubeModel) newValue;
     if (model != null) {
     } 
          animationView.setModel(model);
          dimensionsView.setModel(model);
         backgroundView.setModel(model);
    }

    public JComponent getViewComponent() {
        return this;
    }

    public void addUndoableEditListener(UndoableEditListener l) {
    }

    public void removeUndoableEditListener(UndoableEditListener l) {
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ch.randelshofer.cubetwister.doc.LazyEntityView animationView;
    private ch.randelshofer.cubetwister.doc.LazyEntityView backgroundView;
    private ch.randelshofer.cubetwister.doc.LazyEntityView dimensionsView;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
    
}

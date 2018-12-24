/* @(#)CubeAnimationView.java
 * Copyright (c) 2001 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.cubetwister.doc;

import java.awt.*;
import javax.swing.*;
import ch.randelshofer.gui.*;
import ch.randelshofer.beans.*;

/**
 * CubeAnimationView is used to present the "Options &gt; Animation" page of
 * CubeView.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class CubeAnimationView extends AbstractEntityView {
    private final static long serialVersionUID = 1L;

    private CubeModel model;
    private BoundedRangeModelBeanAdapter durationModel;

    /**
     * Creates new form CubeAnimationView
     */
    public CubeAnimationView() {
        initComponents2();

        scrollPane.getViewport().setOpaque(false);

        Font f = Fonts.getSmallDialogFont();
        for (int i = 0, n = panel.getComponentCount(); i < n; i++) {
            panel.getComponent(i).setFont(f);
        }

        durationModel = new BoundedRangeModelBeanAdapter();
        durationModel.setPropertyName(CubeModel.TWIST_DURATION_PROPERTY);
        durationModel.setRangeProperties(400, 0, 0, 2000, false);
        durationModel.setIsAdjustingSupported(true);
        durationSlider.setModel(durationModel);
        durationField.setBoundedRangeModel(durationModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        durationLabel = new javax.swing.JLabel();
        durationField = new ch.randelshofer.gui.IntegerTextField();
        defaultValuesButton = new javax.swing.JButton();
        durationSlider = new javax.swing.JSlider();

        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        durationLabel.setText("Twist Duration:");

        durationField.setColumns(3);

        defaultValuesButton.setText("Set Default Values");
        defaultValuesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setDefaultValues(evt);
            }
        });

        durationSlider.setMajorTickSpacing(1000);
        durationSlider.setMinorTickSpacing(250);
        durationSlider.setPaintTicks(true);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(durationSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(durationLabel)
                            .addGap(104, 104, 104)
                            .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(defaultValuesButton))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(durationLabel)
                    .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(durationSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(256, 256, 256)
                .addComponent(defaultValuesButton)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        scrollPane.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initComponents2() {
        scrollPane = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        durationLabel = new javax.swing.JLabel();
        durationField = new ch.randelshofer.gui.IntegerTextField();
        defaultValuesButton = new javax.swing.JButton();
        durationSlider = new javax.swing.JSlider();

        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        durationLabel.setText("Twist Duration:");
        durationField.setColumns(3);

        defaultValuesButton.setText("Set Default Values");
        defaultValuesButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setDefaultValues(evt);
            }
        });

        durationSlider.setMajorTickSpacing(1000);
        durationSlider.setMinorTickSpacing(250);
        durationSlider.setPaintTicks(true);

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER, true)//
                .addGroup(panelLayout.createSequentialGroup().addContainerGap()//
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER, true)//
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER, true)//
                .addComponent(durationSlider, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE))//
                .addGroup(panelLayout.createSequentialGroup()//
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, true)//
                .addComponent(durationLabel, GroupLayout.Alignment.LEADING))//
                .addGap(GroupLayout.PREFERRED_SIZE, 0, Integer.MAX_VALUE)//
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)//
                .addComponent(durationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))//
                .addComponent(defaultValuesButton))//
                .addContainerGap()));
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)//
                .addGroup(panelLayout.createSequentialGroup()//
                .addContainerGap()//
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)//
                .addComponent(durationLabel)//
                .addComponent(durationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))//
                .addGap(0)//
                .addComponent(durationSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)//
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)//
                .addComponent(defaultValuesButton)//
                .addContainerGap()));
        scrollPane.setViewportView(panel);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING, true)//
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING, true)//
                .addGroup(layout.createSequentialGroup()//
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)));
    }

    private void setDefaultValues(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setDefaultValues
        if (model != null) {
            model.setTwistDuration(400);
        }
    }//GEN-LAST:event_setDefaultValues

    @Override
    public void setModel(EntityModel newValue) {
        if (model != null) {
        }
        model = (CubeModel) newValue;
        if (model != null) {
        }
        durationModel.setBean(model);
    }

    @Override
    public JComponent getViewComponent() {
        return this;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton defaultValuesButton;
    private ch.randelshofer.gui.IntegerTextField durationField;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JSlider durationSlider;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}

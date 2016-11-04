/*
 * @(#)CubeDimensionsView.java  1.0  2001-01-31
 * Copyright (c) 2001 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */
package ch.randelshofer.cubetwister.doc;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import javax.swing.*;
import ch.randelshofer.gui.*;
import ch.randelshofer.beans.*;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jhotdraw.gui.JLifeFormattedTextField;
import org.jhotdraw.text.JavaNumberFormatter;

/**
 * CubeDimensionsView is used to present the "Options &gt; Dimensions" page of
 * CubeView.
 *
 * @author  Werner Randelshofer
 * @version 1.0 2001-08-02
 */
public class CubeDimensionsView extends AbstractEntityView {
    private final static long serialVersionUID = 1L;

    private CubeModel model;
    private BoundedRangeModelBeanAdapter alphaModel;
    private BoundedRangeModelBeanAdapter betaModel;
    private BoundedRangeModelBeanAdapter scaleModel;
    private BoundedRangeModelBeanAdapter explodeModel;

    private static class FieldHandler implements ChangeListener, PropertyChangeListener {

        private JLifeFormattedTextField field;
        private BoundedRangeModel model;
        private int isAdjusting;

        public FieldHandler(JLifeFormattedTextField field, BoundedRangeModel model) {
            this.field = field;
            this.model = model;
            field.addPropertyChangeListener(this);
            model.addChangeListener(this);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            if (isAdjusting++ == 0) {
                if (model.getValue() != ((Number) field.getValue()).intValue()) {
                    field.setValue(model.getValue());
                }
            }
            isAdjusting--;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (isAdjusting++ == 0) {
                if (model.getValue() != ((Number) field.getValue()).intValue()) {
                    model.setValue(((Number) field.getValue()).intValue());
                }
            }
            isAdjusting--;
        }
    }

    /**
     * Creates new form CubeDimensionsView
     */
    public CubeDimensionsView() {
        initComponents();

        scrollPane.getViewport().setOpaque(false);

        Font f = Fonts.getSmallDialogFont();
        for (int i = 0, n = panel.getComponentCount(); i < n; i++) {
            panel.getComponent(i).setFont(f);
        }

        alphaModel = new BoundedRangeModelBeanAdapter();
        alphaModel.setPropertyName(CubeModel.INT_ALPHA_PROPERTY);
        alphaModel.setRangeProperties(0, 0, -90, 90, false);
        alphaModel.setIsAdjustingSupported(true);
        alphaSlider.setModel(alphaModel);
        alphaField.setFormatterFactory(
                JavaNumberFormatter.createFormatterFactory(alphaModel.getMinimum(), alphaModel.getMaximum(), 1));
        alphaField.setValue(alphaModel.getValue());
        new FieldHandler(alphaField, alphaModel);

        betaModel = new BoundedRangeModelBeanAdapter();
        betaModel.setPropertyName(CubeModel.INT_BETA_PROPERTY);
        betaModel.setRangeProperties(0, 0, -90, 90, false);
        betaModel.setIsAdjustingSupported(true);
        betaSlider.setModel(betaModel);
        betaField.setFormatterFactory(
                JavaNumberFormatter.createFormatterFactory(betaModel.getMinimum(), betaModel.getMaximum(), 1));
        betaField.setValue(betaModel.getValue());
        new FieldHandler(betaField, betaModel);

        scaleModel = new BoundedRangeModelBeanAdapter();
        scaleModel.setPropertyName(CubeModel.INT_SCALE_PROPERTY);
        scaleModel.setRangeProperties(100, 0, 0, 200, false);
        scaleModel.setIsAdjustingSupported(true);
        scaleSlider.setModel(scaleModel);
        scaleField.setFormatterFactory(
                JavaNumberFormatter.createFormatterFactory(scaleModel.getMinimum(), scaleModel.getMaximum(), 1));
        scaleField.setValue(scaleModel.getValue());
        new FieldHandler(scaleField, scaleModel);

        explodeModel = new BoundedRangeModelBeanAdapter();
        explodeModel.setPropertyName(CubeModel.INT_EXPLODE_PROPERTY);
        explodeModel.setRangeProperties(0, 0, 0, 200, false);
        explodeModel.setIsAdjustingSupported(true);
        explodeSlider.setModel(explodeModel);
        explodeField.setFormatterFactory(
                JavaNumberFormatter.createFormatterFactory(explodeModel.getMinimum(), explodeModel.getMaximum(), 1));
        explodeField.setValue(explodeModel.getValue());
        new FieldHandler(explodeField, explodeModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrollPane = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        alphaLabel = new javax.swing.JLabel();
        alphaField = new org.jhotdraw.gui.JLifeFormattedTextField();
        alphaSlider = new javax.swing.JSlider();
        betaLabel = new javax.swing.JLabel();
        betaField = new org.jhotdraw.gui.JLifeFormattedTextField();
        betaSlider = new javax.swing.JSlider();
        scaleLabel = new javax.swing.JLabel();
        scaleField = new org.jhotdraw.gui.JLifeFormattedTextField();
        scaleSlider = new javax.swing.JSlider();
        explodeLabel = new javax.swing.JLabel();
        explodeField = new org.jhotdraw.gui.JLifeFormattedTextField();
        explodeSlider = new javax.swing.JSlider();
        defaultValuesButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 20, 20, 20));
        panel.setLayout(new java.awt.GridBagLayout());

        alphaLabel.setText("Alpha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        panel.add(alphaLabel, gridBagConstraints);

        alphaField.setColumns(3);
        alphaField.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_TRAILING;
        panel.add(alphaField, gridBagConstraints);

        alphaSlider.setMajorTickSpacing(90);
        alphaSlider.setMinorTickSpacing(45);
        alphaSlider.setPaintTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        panel.add(alphaSlider, gridBagConstraints);

        betaLabel.setText("Beta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        panel.add(betaLabel, gridBagConstraints);

        betaField.setColumns(3);
        betaField.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_TRAILING;
        panel.add(betaField, gridBagConstraints);

        betaSlider.setMajorTickSpacing(90);
        betaSlider.setMinorTickSpacing(45);
        betaSlider.setPaintTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        panel.add(betaSlider, gridBagConstraints);

        scaleLabel.setText("Scale:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        panel.add(scaleLabel, gridBagConstraints);

        scaleField.setColumns(3);
        scaleField.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_TRAILING;
        panel.add(scaleField, gridBagConstraints);

        scaleSlider.setMajorTickSpacing(100);
        scaleSlider.setMinorTickSpacing(25);
        scaleSlider.setPaintTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        panel.add(scaleSlider, gridBagConstraints);

        explodeLabel.setText("Explode:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        panel.add(explodeLabel, gridBagConstraints);

        explodeField.setColumns(3);
        explodeField.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_TRAILING;
        panel.add(explodeField, gridBagConstraints);

        explodeSlider.setMajorTickSpacing(100);
        explodeSlider.setMinorTickSpacing(25);
        explodeSlider.setPaintTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        panel.add(explodeSlider, gridBagConstraints);

        defaultValuesButton.setText("Set Default Values");
        defaultValuesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setDefaultValues(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panel.add(defaultValuesButton, gridBagConstraints);

        scrollPane.setViewportView(panel);

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void setDefaultValues(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setDefaultValues
        if (model != null) {
            model.setIntAlpha(-25);
            model.setIntBeta(45);
            model.setIntScale(100);
            model.setIntExplode(0);
        }
    }//GEN-LAST:event_setDefaultValues

    @Override
    public void setModel(EntityModel newValue) {
        model = (CubeModel) newValue;
            alphaModel.setBean(model);
            betaModel.setBean(model);
            scaleModel.setBean(model);
            explodeModel.setBean(model);
    }

    @Override
    public JComponent getViewComponent() {
        return this;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jhotdraw.gui.JLifeFormattedTextField alphaField;
    private javax.swing.JLabel alphaLabel;
    private javax.swing.JSlider alphaSlider;
    private org.jhotdraw.gui.JLifeFormattedTextField betaField;
    private javax.swing.JLabel betaLabel;
    private javax.swing.JSlider betaSlider;
    private javax.swing.JButton defaultValuesButton;
    private org.jhotdraw.gui.JLifeFormattedTextField explodeField;
    private javax.swing.JLabel explodeLabel;
    private javax.swing.JSlider explodeSlider;
    private javax.swing.JPanel panel;
    private org.jhotdraw.gui.JLifeFormattedTextField scaleField;
    private javax.swing.JLabel scaleLabel;
    private javax.swing.JSlider scaleSlider;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}

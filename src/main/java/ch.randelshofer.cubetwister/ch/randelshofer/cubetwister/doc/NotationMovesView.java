/*
 * @(#)NotationMovesView.java
 * CubeTwister. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.cubetwister.doc;

import ch.randelshofer.gui.table.DefaultCellEditor2;
import org.jhotdraw.annotation.Nonnull;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * NotationMovesView.
 *
 * @author Werner Randelshofer
 */
public class NotationMovesView extends JPanel implements EntityView {
    private final static long serialVersionUID = 1L;

    private NotationMovesTableModel tableModel;

    /** Creates a new instance. */
    public NotationMovesView() {
        initComponents();

        table.putClientProperty("Quaqua.Table.style", "striped");

        tableModel = new NotationMovesTableModel();
        table.setModel(tableModel);

        DefaultTableCellRenderer renderer;
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(renderer);

        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT);
        renderer.setBorder(new EmptyBorder(0, 2, 0, 2));
        table.getColumnModel().getColumn(2).setCellRenderer(renderer);
        if ("Windows".equals(UIManager.getLookAndFeel().getID())) {
            scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        }

        DefaultCellEditor2 editor = new DefaultCellEditor2(new JTextField());
        // Edit with 1 click
        editor.setClickCountToStart(1);
        table.setDefaultEditor(String.class, editor);
    }

    @Nonnull
    @Override
    public JComponent getViewComponent() {
        return this;
    }

    /**
     * Removes an UndoableEditListener.
     */
    @Override
    public void removeUndoableEditListener(UndoableEditListener l) {
    }

    /**
     * Adds an UndoableEditListener.
     */
    public void addUndoableEditListener(UndoableEditListener l) {
    }

    public void setModel(EntityModel newValue) {
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }
        setModel((NotationModel) newValue);
    }

    public void setModel(NotationModel newValue) {
        tableModel.setModel(newValue);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        table = new ch.randelshofer.gui.MutableJTable();

        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private ch.randelshofer.gui.MutableJTable table;
    // End of variables declaration//GEN-END:variables
}

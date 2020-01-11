/* @(#)AbstractCubeIdx3DPlayerApplet.java
 * Copyright (c) 2007 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.rubik.player;

import base64.Base64;
import ch.randelshofer.rubik.Cube3DCanvas;
import ch.randelshofer.rubik.Cube3DCanvasIdx3D;
import ch.randelshofer.rubik.JCubeCanvasIdx3D;
import ch.randelshofer.rubik.cube3d.Cube3D;
import ch.randelshofer.rubik.parser.ScriptPlayer;
import idx3d.idx3d_JCanvas;
import idx3d.idx3d_RenderPipeline;
import org.jhotdraw.annotation.Nonnull;
import org.jhotdraw.annotation.Nullable;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
/**
 * AbstractCubeIdx3DPlayerApplet.
 *
 * @author Werner Randelshofer
 */
public abstract class AbstractCubeIdx3DPlayerApplet extends AbstractPlayerApplet {
    private static idx3d_RenderPipeline sharedPipeline;

    @Override
    protected void configurePlayer(@Nonnull ScriptPlayer p) {
        if (sharedPipeline == null) {
            sharedPipeline = new idx3d_RenderPipeline(null, 0, 0);
        }
        Cube3DCanvasIdx3D canvas = (Cube3DCanvasIdx3D) p.getCanvas();
        canvas.setSharedRenderPipeline(sharedPipeline);
    }

    @Nonnull
    @Override
    final protected Cube3DCanvas createRearCanvas() {
        Cube3D cube3D = frontCanvas.getCube3D();
        Cube3DCanvas c3d = new JCubeCanvasIdx3D();
        ((JComponent) c3d.getVisualComponent()).setOpaque(false);
        c3d.setCube3D(cube3D);
        c3d.setLock(cube3D.getCube());
        c3d.setCamera("Rear");
        ((idx3d_JCanvas) c3d.getVisualComponent()).setMinFPS(30);
        return c3d;
    }

    @Nullable
    @Override
    protected Image decodeBase64Image(@Nonnull String base64) {
        try {
            byte[] bytes = Base64.decode(base64);
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException ex) {
            System.err.println("Warning: Can't decode base64 encoded image.");
            ex.printStackTrace();
            return null;
        }
    }

    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}

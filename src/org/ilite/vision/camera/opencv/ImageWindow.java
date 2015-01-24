package org.ilite.vision.camera.opencv;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageWindow {
    
    private Set<IRenderable>mRenderables = new CopyOnWriteArraySet<IRenderable>();
    
    private JPanel mImagePanel = new JPanel() {
	
	protected void paintComponent(java.awt.Graphics g) {
	    super.paintComponent(g);
	    
	    if(mCurrentFrame != null) {
		g.drawImage(mCurrentFrame, 0, 0, getWidth(), getHeight(), null);
	    }
	    
	    for(IRenderable renderables2 : mRenderables ){
	    	renderables2.paint(g);
	    }
	    
	    //TODO MAR: How do you draw renderables?
	    
	};
	
    };
    private BufferedImage mCurrentFrame = null;
    private JFrame mFrame;
    public ImageWindow(BufferedImage pImage) {

	mFrame = new JFrame();
	mCurrentFrame = pImage;
	if(mCurrentFrame != null) {
	    mImagePanel.setPreferredSize(new Dimension(mCurrentFrame.getWidth(), mCurrentFrame.getHeight()));
	}
	mFrame.setContentPane(mImagePanel);
	mFrame.pack();
	mFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void updateImage(BufferedImage pImage) {
	mCurrentFrame = pImage;
	if(mCurrentFrame != null) {
	    mImagePanel.setPreferredSize(new Dimension(mCurrentFrame.getWidth(), mCurrentFrame.getHeight()));
	}
	
	mFrame.revalidate();
	mFrame.pack();

	mImagePanel.repaint();
    }

    public void show() {
	mFrame.setVisible(true);
    }

}
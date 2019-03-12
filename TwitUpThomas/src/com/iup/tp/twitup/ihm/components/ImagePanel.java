package com.iup.tp.twitup.ihm.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 666L;

	protected File mFile;

	protected BufferedImage mImage;

	public ImagePanel(File file, Dimension dimension) {
		this.mFile = file;

		try {
			this.load(dimension);
		} catch (Throwable t) {
			mImage = new BufferedImage(1, 1, 1);
			t.printStackTrace();
		}
	}

	protected void load(Dimension dimension) {
		try {
			mImage = ImageIO.read(mFile);

			// Resize de l'image
			if (mImage.getWidth() != dimension.getWidth()
					|| mImage.getHeight() != dimension.getHeight()) {
				mImage = this.resizeImage(mImage, dimension);
			}

			this.setSize(new Dimension(mImage.getWidth(), mImage.getHeight()));
			this.setPreferredSize(new Dimension(mImage.getWidth(), mImage
					.getHeight()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(mImage, 0, 0, mImage.getWidth(), mImage.getHeight(), null);
	}

	public BufferedImage resizeImage(BufferedImage original, Dimension dimension) {

		double widthFactor = (double) dimension.getWidth()
				/ original.getWidth();
		double heightFactor = (double) dimension.getHeight()
				/ original.getHeight();

		double factor = Math.min(widthFactor, heightFactor);
		int newWidth = (int) (factor * original.getWidth());
		int newHeight = (int) (factor * original.getHeight());

		BufferedImage newImage = new BufferedImage(newWidth, newHeight,
				original.getType());

		Graphics2D g = newImage.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(original, 0, 0, newWidth, newHeight, null);
		g.dispose();

		return newImage;
	}
}

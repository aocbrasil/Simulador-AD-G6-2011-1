package simulator.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CriadorGraficoComparativo {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String disciplina = args[0];
		String variavel = args[1];
		String ro = args[2];
		double tamanho1 = Double.parseDouble(args[3].replace(",", "."));
		double tamanho2 = Double.parseDouble(args[4].replace(",", "."));
		double ic = Double.parseDouble(args[5].replace(",", "."));
		
		double razao = tamanho2 / tamanho1;
		
		String nome = "grafico_"+disciplina+"_"+ro+"_"+variavel;
		int width = 55;
		int height = 400;
		int larguraColuna = 20;
		int alturaBarra1 = 300;
		int distanciaBarras = 5;
		int yBarra1 = 50;
		
		int alturaBarra2 = (int)(alturaBarra1 * razao);
		int alturaIC = (int)(alturaBarra2 * (ic / tamanho2))+1;
		
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bi.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.red);
		graphics.fillRect(distanciaBarras, yBarra1, larguraColuna, alturaBarra1);

		int yBarra2 = yBarra1 + alturaBarra1 - alturaBarra2;
		
		graphics.setColor(Color.blue);
		graphics.fillRect(distanciaBarras*2+larguraColuna, yBarra2, larguraColuna, alturaBarra2);
		
		graphics.setColor(Color.green);
		graphics.fillRect(distanciaBarras*2+larguraColuna, yBarra2+1, larguraColuna, alturaIC);

		graphics.setColor(Color.black);
		graphics.fillRect(distanciaBarras*2+larguraColuna, yBarra2, larguraColuna, 1);

		graphics.setColor(Color.green);
		graphics.fillRect(distanciaBarras*2+larguraColuna, yBarra2-alturaIC, larguraColuna, alturaIC);

		
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		graphics.setColor(Color.black);
		g2d.drawString(variavel, 5, 370);
		
		graphics.setColor(Color.white);
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.PI/2.0, width/2.0, height/2.0);
		g2d.setTransform(at);
		
		g2d.drawString(String.valueOf(tamanho1), width/2+height/2 - 340, height/2-8);
		g2d.drawString(String.valueOf(tamanho2), width/2+height/2 - 340, height/2+17);
	    
//	    distanciaBarras+larguraColuna
//	    yBarra1+alturaBarra1
	    
		ImageIO.write(bi, "png", new File(""+nome+".png"));
//		Runtime.getRuntime().exec("/WINDOWS/system32/mspaint.exe "+nome+".png");
	}
}
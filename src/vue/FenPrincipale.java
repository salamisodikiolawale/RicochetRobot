package vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FenPrincipale extends JFrame{
	
	public FenPrincipale() {
		
		this.setTitle("Taquin");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(500, 500));
		Container cp = this.getContentPane();
		cp.setLayout(null);
		
	    JLabel jTitre = new JLabel("TAQUIN");
	    jTitre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 100));
	    jTitre.setBounds(40, 150, 400, 100);
	    cp.add(jTitre);
		
	    BarDesMenu menuBar = new BarDesMenu();
	    this.setResizable(false);
        this.setJMenuBar(menuBar);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}

}

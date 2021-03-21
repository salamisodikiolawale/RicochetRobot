package vue;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.MaquetteImg;
import controller.MaquetteSansImg;
import model.Taquin;
import utilitaire.IEcouteurModel;


public class BarDesMenu extends JMenuBar{

	public BarDesMenu() {
		creerMenuBar();
	}
	
	public void creerMenuBar() {
		
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JMenu choix = new JMenu("Choix du Jeu");
        JMenu img = new JMenu("Avec Image");
        JMenu sansImg = new JMenu("Sans Image");
       
        choix.add(img);
        choix.add(sansImg);
        this.add(choix);
       
        
        JMenuItem deuxFoisDeux = new JMenuItem("   2 x 2   ");
        deuxFoisDeux.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(2, 2,null);
				MaquetteImg m= new MaquetteImg(taquin);
				Fenetre f = new Fenetre(taquin,m);
				f.setVisible(true);
				
			}
		});
        img.add(deuxFoisDeux);
        
        JMenuItem troisFoisTrois = new JMenuItem("   3 x 3   ");
        troisFoisTrois.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(3, 3,null);
				MaquetteImg m= new MaquetteImg(taquin);
				Fenetre f = new Fenetre(taquin,m);
				f.setVisible(true);
				
			}
		});
        img.add(troisFoisTrois);
        
        JMenuItem quatreFoisQuatre = new JMenuItem("   4 x 4   ");
        quatreFoisQuatre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(4, 4,null);
				MaquetteImg m= new MaquetteImg(taquin);
				Fenetre f = new Fenetre(taquin,m);
				f.setVisible(true);
				
			}
		});
        img.add(quatreFoisQuatre);
        
        JMenuItem cinqFoisCinq = new JMenuItem("    5 x 5    ");
        cinqFoisCinq.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(5, 5,null);
				MaquetteImg m= new MaquetteImg(taquin);
				Fenetre f = new Fenetre(taquin,m);
				f.setVisible(true);
				
			}
		});
        img.add(cinqFoisCinq);
        
        JMenuItem sixFoisSix = new JMenuItem("    6 x 6    ");
        sixFoisSix.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(6, 6,null);
				MaquetteImg m= new MaquetteImg(taquin);
				Fenetre f = new Fenetre(taquin,m);
				f.setVisible(true);
				
			}
		});
        img.add(sixFoisSix);
        
        JMenuItem septFoisSept = new JMenuItem("    7 x 7    ");
        septFoisSept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(7, 7,null);
				MaquetteImg m= new MaquetteImg(taquin);
				Fenetre f = new Fenetre(taquin,m);
				f.setVisible(true);
				
			}
		});
        img.add(septFoisSept);
        
        JMenuItem huitFoisHuit = new JMenuItem("    8 x 8    ");
        huitFoisHuit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(8, 8,null);
				MaquetteImg m= new MaquetteImg(taquin);
				Fenetre f = new Fenetre(taquin,m);
				f.setVisible(true);
				
			}
		});
        img.add(huitFoisHuit);
        
        //menuItemSansImage
        JMenuItem deuxFoisDeuxSimg = new JMenuItem("   2 x 2   ");
        deuxFoisDeuxSimg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(2, 2);
				MaquetteSansImg m= new MaquetteSansImg(taquin);
				Fenetre f = new Fenetre(taquin);
				f.setVisible(true);
				
			}
		});
        sansImg.add(deuxFoisDeuxSimg);
        
        JMenuItem troisFoisTroiSimg = new JMenuItem("   3 x 3   ");
        troisFoisTroiSimg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(3, 3);
				MaquetteSansImg m= new MaquetteSansImg(taquin);
				Fenetre f = new Fenetre(taquin);
				f.setVisible(true);
				
			}
		});
        sansImg.add(troisFoisTroiSimg);
        
        JMenuItem quatreFoisQuatreSimg = new JMenuItem("   4 x 4   ");
        quatreFoisQuatreSimg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(4, 4);
				MaquetteSansImg m= new MaquetteSansImg(taquin);
				Fenetre f = new Fenetre(taquin);
				f.setVisible(true);
				
			}
		});
        sansImg.add(quatreFoisQuatreSimg);
        
        JMenuItem cinqFoisCinqSimg = new JMenuItem("    5 x 5    ");
        cinqFoisCinqSimg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(5, 5);
				MaquetteSansImg m= new MaquetteSansImg(taquin);
				Fenetre f = new Fenetre(taquin);
				f.setVisible(true);
				
			}
		});
        sansImg.add(cinqFoisCinqSimg);
        
        JMenuItem sixFoisSixSimg = new JMenuItem("    6 x 6    ");
        sixFoisSixSimg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(6, 6);
				MaquetteSansImg m= new MaquetteSansImg(taquin);
				Fenetre f = new Fenetre(taquin);
				f.setVisible(true);
				
			}
		});
        sansImg.add(sixFoisSixSimg);
        
        JMenuItem septFoisSeptSimg = new JMenuItem("    7 x 7    ");
        septFoisSeptSimg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(7, 7);
				MaquetteSansImg m= new MaquetteSansImg(taquin);
				Fenetre f = new Fenetre(taquin);
				f.setVisible(true);
				
			}
		});
        sansImg.add(septFoisSeptSimg);
        
        JMenuItem huitFoisHuitSimg = new JMenuItem("    8 x 8    ");
        huitFoisHuitSimg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Taquin taquin= new Taquin(8, 8);
				MaquetteSansImg m= new MaquetteSansImg(taquin);
				Fenetre f = new Fenetre(taquin);
				f.setVisible(true);
				
			}
		});
        sansImg.add(huitFoisHuitSimg);
       
	}
	
	
	
}

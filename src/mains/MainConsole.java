package mains;

import java.io.Console;
import java.util.Scanner;

import model.Taquin;

public class MainConsole {

	public static void main(String[] args) {
	
		Taquin taquin = new Taquin(3, 3);
		taquin.directionConsole();
		taquin.getGame();
		taquin.deplacementConsole();
		
	}
}

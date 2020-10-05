package com.objis.demoswing;

//import java.util.*;

public class Grille1 {
    
    private int nbRow;
    private int nbColumn;
    private char [][] grille;

    public Grille1(int nbRow, int nbColumn) {
        this.nbRow = nbRow;
        this.nbColumn = nbColumn;
        grille = new char[nbRow][nbColumn];
    }

    public void creationGrille ( int nbRow, int nbColumn){
        
        for (int i=0; i<nbRow; i++){
            for (int j=0; j<nbColumn; j++){
                
                grille[i][j]=' ';
            }
        }
    }

    public void displayGrille(){
        for (int i=0; i<nbRow; i++){
            for (int j=0; j<nbColumn; j++){
                
                System.out.print(" | " + grille[i][j]);
            }
            System.out.println(" | ");
        }
        System.out.println();
    }

    public void placement (int x, int y, char t){
        creationGrille(nbRow, nbColumn);
        if (x<0 || y<0 || x>nbRow || y>nbColumn){
            System.out.println("Erreur");
        }
        if (grille[x][y] ==' '){
            grille[x-1][y-1]=t;

        } 
        else{
            System.out.println("Cette zone n'est pas vide");
        }
    }

    




}

package com.thecorner.altruistapp;

import Entidades.Menus;
import java.util.Scanner;

public class Altruistapp {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        

        
        Menus menu = new Menus(scanner);
        while (true) {
                menu.mostrarMenuPrincipal();
        }     
    }
}
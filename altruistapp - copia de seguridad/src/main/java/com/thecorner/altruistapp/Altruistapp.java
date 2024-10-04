package com.thecorner.altruistapp;

import Entidades.Donacion;
import Entidades.Menus;
import java.util.Scanner;

public class Altruistapp {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        
        Donacion donacion = new Donacion();
        
        donacion.setIdOfreceDonacion(1);
        donacion.setIdAceptaDonacion(2);
        donacion.setEstado(Donacion.estadoSolicitado);
        
        donacion.aceptarSolicitud();
        String estadoDespuesDeAceptar = donacion.obtenerEstado();
        
        donacion.marcarComoDepositada();
        String estadoDespuesDeDepositar = donacion.obtenerEstado();
        
        
        Menus menu = new Menus(scanner);
        while (true) {
            menu.mostrarMenuPrincipal();
        }     
    }
}
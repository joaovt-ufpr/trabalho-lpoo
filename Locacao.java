/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhofinaljava;

import java.util.Calendar;

/**
 *
 * @author renan
 */
public class Locacao {
    private int dias;
    private double valor;
    private Calendar data;
    Cliente cliente;
    
    public Locacao (int dias, double valor, Calendar data, Cliente cliente){
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
    }

    public int getDias() {
        return dias;
    }

    public double getValor() {
        return valor;
    }

    public Calendar getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    
}

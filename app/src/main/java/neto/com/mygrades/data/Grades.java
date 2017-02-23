package neto.com.mygrades.data;

import java.io.Serializable;

/**
 * Created by Neto on 22/02/2017.
 */

public class Grades implements Serializable {

    private int id;
    private String nome;
    private double p1;
    private double p2;
    private double edad;

    public Grades (int id, String nome, double p1, double p2, double edad) {
        this.id = id;
        this.nome = nome;
        this.p1 = p1;
        this.p2 = p2;
        this.edad = edad;
    }

    public Grades (String nome, double p1, double p2, double edad) {
        this.nome = nome;
        this.p1 = p1;
        this.p2 = p2;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }
}

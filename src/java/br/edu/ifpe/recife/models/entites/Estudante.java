/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.models.entites;

/**
 *
 * @author raulv
 */
public class Estudante {
    
    private int codigo;
    private String nome;
    private String email;
    private String senha;
    private int anoEntrada;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAnoEntrada(int anoEntrada) {
        this.anoEntrada = anoEntrada;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getAnoEntrada() {
        return anoEntrada;
    }
    
    
}

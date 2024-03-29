/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.models.repositories;

import br.edu.ifpe.recife.models.entites.Estudante;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulv
 */
public class EstudanteRepository {
    
    private static List<Estudante> estudante;
    
    static{
        estudante = new ArrayList<>();
        
        
            Estudante e = new Estudante();
        e.setCodigo(1);
        e.setNome("RaulEstudanteDedicado");
        e.setEmail("RaulMuadib@gmail.com");
        e.setSenha("123456");
        e.setAnoEntrada(2022);
        
        estudante.add(e);
        
        e = new Estudante();
              
        e.setCodigo(2);
        e.setNome("Jorge");
        e.setEmail("JorgePitbull@gmail.com");
        e.setSenha("1234");
        e.setAnoEntrada(1850);
        
        estudante.add(e);
        
        
    }
    
    public static void create(Estudante e){
        EstudanteRepository.estudante.add(e);
    }
    
    public static void update(Estudante e){
        
        for(Estudante eAux: estudante){
            
            if(eAux.getCodigo() == e.getCodigo()){
                eAux.setNome(e.getNome());
                eAux.setAnoEntrada(e.getAnoEntrada());
                eAux.setEmail(e.getEmail());
                eAux.setSenha(e.getSenha());
                
                return;
            }
            
        }
        
    }
    
    public static Estudante read(int codigo){
        
        for(Estudante eAux: estudante){
            if(eAux.getCodigo()==codigo){
                return eAux;
            }
        }
        return null;
    }
    
    public static void delete(int codigo){
        
        for(int i = 0; i < estudante.size();i++){
            
            if(estudante.get(i).getCodigo()==codigo){
                
                estudante.remove(i);
              
                return;
            }
            
        }
        
    }
    
    public static List<Estudante> readAll(){
        return estudante;
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.models.repositories;

import br.edu.ifpe.recife.models.entites.Cadeira;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulv
 */
public class CadeiraRepository {
  
    private static List<Cadeira> cadeira;
    
    static{
        cadeira = new ArrayList<>();
        
        
         Cadeira c = new Cadeira();
        c.setCodigo(1);
        c.setNome("Requisitos");
        c.setAno(2023);
        c.setSemestre(2);
        c.setDescricao("Documento de requisitos");
        
        cadeira.add(c);
        
        c = new Cadeira();
              
        c.setCodigo(2);
        c.setNome("Web2");
        c.setAno(2024);
        c.setSemestre(1);
        c.setDescricao("Projetos em java");
        
        cadeira.add(c);
        
        
    }
    
    public static void create(Cadeira c){
        CadeiraRepository.cadeira.add(c);
    }
    
    public static void update(Cadeira c){
        
        for(Cadeira cAux: cadeira){
            
            if(cAux.getCodigo() == c.getCodigo()){
                cAux.setNome(c.getNome());
                cAux.setAno(c.getAno());
                cAux.setSemestre(c.getSemestre());
                cAux.setDescricao(c.getDescricao());
                
                return;
            }
            
        }
        
    }
    
    public static Cadeira read(int codigo){
        
        for(Cadeira cAux: cadeira){
            if(cAux.getCodigo()==codigo){
                return cAux;
            }
        }
        return null;
    }
    
    public static void delete(int codigo){
        
        for(int i = 0; i < cadeira.size();i++){
            
            if(cadeira.get(i).getCodigo()==codigo){
                
                cadeira.remove(i);
                
                return;
            }
            
        }
        
    }
    
    public static List<Cadeira> readAll(){
        return cadeira;
    }    
}

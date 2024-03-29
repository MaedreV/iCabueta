/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.models.repositories;

import br.edu.ifpe.recife.models.entites.Professor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulv
 */
public class ProfessorRepository {
    private static List<Professor> professor;
    
    static{
        professor = new ArrayList<>();
        
         Professor p = new Professor();
   
         p.setCodigo(1);
         p.setNome("Eduardo Perigoso");
         p.setEmail("EduardoIfpe@gmail.com");
         p.setSenha("12345");
        
        professor.add(p);
        
        p = new Professor();
        
         p.setCodigo(2);
         p.setNome("Ramide");
         p.setEmail("RamideIfpe@gmail.com");
         p.setSenha("12347");
        
        
        professor.add(p);
    }
    
    public static void create(Professor p){
        ProfessorRepository.professor.add(p);
    }
    
    public static void update(Professor p){
        
        for(Professor prAux: professor){
            
            if(prAux.getCodigo() == p.getCodigo()){
                prAux.setNome(p.getNome());
                prAux.setEmail(p.getEmail());
                prAux.setSenha(p.getSenha());
                
                return;
            }
            
        }
        
    }
    
    public static Professor read(int codigo){
        
        for(Professor prAux: professor){
            if(prAux.getCodigo()==codigo){
                return prAux;
            }
        }
        return null;
    }
    
    public static void delete(int codigo){
        
        for(int i = 0; i < professor.size();i++){
            
            if(professor.get(i).getCodigo()==codigo){
                
                professor.remove(i);
                
                return;
            }
            
        }
        
    }
    
    public static List<Professor> readAll(){
        return professor;
    }    
}


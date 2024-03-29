/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.models.repositories;

import br.edu.ifpe.recife.models.entites.MetodoFila;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulv
 */
public class FilaRepository {
 
    private static List<MetodoFila> fila;
    
    static{
        fila = new ArrayList<>();
        
        
         MetodoFila f = new MetodoFila();
        f.setCodigo(1);
        f.setDescricaoCurta("Espelhos");
        f.setDescricaoLonga("Usa espelhos no teto para ver o computador de outros alunos");
        
        
        fila.add(f);
        
        f = new MetodoFila();
      f.setCodigo(2);
        f.setDescricaoCurta("Dinheiro");
        f.setDescricaoLonga("Suborna o monitor para fazer vista grossa");
        
        fila.add(f);
        
    }
    
    public static void create(MetodoFila f){
        FilaRepository.fila.add(f);
    }
    
    public static void update(MetodoFila f){
        
        for(MetodoFila fAux: fila){
            
            if(fAux.getCodigo() == f.getCodigo()){
                fAux.setDescricaoCurta(f.getDescricaoCurta());
                fAux.setDescricaoLonga(f.getDescricaoLonga());
              
                return;
            }
            
        }
        
    }
    
    public static MetodoFila read(int codigo){
        
        for(MetodoFila fAux: fila){
            if(fAux.getCodigo()==codigo){
                return fAux;
            }
        }
        return null;
    }
    
    public static void delete(int codigo){
        
        for(int i = 0; i < fila.size();i++){
            
            if(fila.get(i).getCodigo()==codigo){
                
                fila.remove(i);
               
                return;
            }
            
        }
        
    }
    
    public static List<MetodoFila> readAll(){
        return fila;
    }    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.models.repositories;

import br.edu.ifpe.recife.models.entites.Denuncia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulv
 */
public class DenunciaRepository {
  
    private static List<Denuncia> denuncia;
    
    static{
        denuncia = new ArrayList<>();
    }
    
    public static void create(Denuncia d){
        DenunciaRepository.denuncia.add(d);
    }
    
    public static void update(Denuncia d){
        
        for(Denuncia dAux: denuncia){
            
            if(dAux.getCodigo() == d.getCodigo()){
                dAux.setData(d.getData());
                dAux.setTurno(d.getTurno());
                dAux.setDescricaoDoOcorrido(d.getDescricaoDoOcorrido());
                
                
                return;
            }
            
        }
        
    }
    
    public static Denuncia read(int codigo){
        
        for(Denuncia dAux: denuncia){
            if(dAux.getCodigo()==codigo){
                return dAux;
            }
        }
        return null;
    }
    
    public static void delete(int codigo){
        
        for(int i = 0; i < denuncia.size();i++){
            
            if(denuncia.get(i).getCodigo()==codigo){
                
                denuncia.remove(i);
               
                return;
            }
            
        }
        
    }
    
    public static List<Denuncia> readAll(){
        return denuncia;
    }    
}

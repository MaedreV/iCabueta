package br.edu.ifpe.recife.controllers.tagHandler;

//import br.edu.ifpe.recife.models.entites.Cadeira;
import br.edu.ifpe.recife.models.entites.Estudante;
import br.edu.ifpe.recife.models.repositories.CadeiraRepository;
import br.edu.ifpe.recife.models.repositories.EstudanteRepository;
import br.edu.ifpe.recife.models.repositories.FilaRepository;
import br.edu.ifpe.recife.models.repositories.ProfessorRepository;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;
/**
 *
 * @author raulv
 */
public class CarregaTag extends TagSupport { //"melhor" que o simple tag

    private String entidade;
    private String var;
    private String escopo;
    private String codigo;

    
    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int doStartTag() throws JspException {
        Object resultado = null;

        if ("estudante".equalsIgnoreCase(entidade)) {
            if (codigo != null) {
                int codigoInt = Integer.parseInt(codigo);
                resultado = EstudanteRepository.read(codigoInt);
            } else {
                resultado = EstudanteRepository.readAll();
            }
        } else if ("cadeira".equalsIgnoreCase(entidade)) {
            if (codigo != null) {
                int codigoInt = Integer.parseInt(codigo);
                resultado = CadeiraRepository.read(codigoInt);
            } else {
                resultado = CadeiraRepository.readAll();
            }
        }else if ("metodoFila".equalsIgnoreCase(entidade)) {
            if (codigo != null) {
                int codigoInt = Integer.parseInt(codigo);
                resultado = FilaRepository.read(codigoInt);
            } else {
                resultado = FilaRepository.readAll();
            }
        }
else if ("professor".equalsIgnoreCase(entidade)) {
            if (codigo != null) {
                int codigoInt = Integer.parseInt(codigo);
                resultado = ProfessorRepository.read(codigoInt);
            } else {
                resultado = ProfessorRepository.readAll();
            }
        }


        if (resultado != null) {
            switch (escopo.toLowerCase()) {
                case "pagina":
                    pageContext.setAttribute(var, resultado);
                    break;
                case "requisicao":
                    pageContext.getRequest().setAttribute(var, resultado);
                    break;
                case "sessao":
                    pageContext.getSession().setAttribute(var, resultado);
                    break;
                case "aplicacao":
                    pageContext.getServletContext().setAttribute(var, resultado);
                    break;
                default:
                    throw new JspException("Escopo tal tal: " + escopo);
            }
        }

        return SKIP_BODY;
    }
}
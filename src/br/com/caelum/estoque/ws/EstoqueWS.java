package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.*;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public class EstoqueWS {

    private ItemDao dao = new ItemDao();

    @WebMethod(operationName = "todosOsItens")
    @WebResult(name = "itens")
    public ListaItens getAllItens() {
        System.out.println("Retornando todos os itens.");
        ListaItens listaItens = new ListaItens(dao.todosItens());
        return listaItens;
    }

    @WebMethod(operationName = "TodosOsItensDoFiltro", action = "TodosOsItensDoFiltro")
    @RequestWrapper(localName = "listaItensDoFiltro")
    @ResponseWrapper(localName = "itens")
    @WebResult(name = "item")
    public List<Item> getItensByFilter(@WebParam(name = "filtros") Filtros filtros) {
        System.out.println("Retornando todos os itens.");
        List<Filtro> itensFiltros = filtros.getLista();
        return dao.todosItens(itensFiltros);
    }

    @WebMethod(operationName = "CadastrarItem")
    @WebResult(name = "item")
    public Item cadastrarItem(@WebParam(name = "tokenUsuario", header = true) TokenUsuario tokenUsuario,
                              @WebParam(name = "item") Item item) throws AutorizacaoException {
        boolean ehValido = new TokenDao().ehValido(tokenUsuario);
        if (!ehValido) {
            throw new AutorizacaoException("Autorizacao Falhou.");
        }
        new ItemValidador(item).validate();
        System.out.println("Cadastrando um novo item " + item + ". token " + tokenUsuario);
        dao.cadastrar(item);
        return item;
    }

}

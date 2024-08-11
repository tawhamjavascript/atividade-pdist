package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejbs.UsuarioService;
import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/usuario.do"})
public class UsuarioServlet extends javax.servlet.http.HttpServlet {

    @EJB(lookup="java:module/usuarioService")
    private UsuarioService usuarioService;

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String operacao = request.getParameter("oper");
        PrintWriter resposta = response.getWriter();
        switch (operacao) {
            case "1": {
                long id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String mensagem = request.getParameter("mensagem");
                usuarioService.inserir(id, nome, mensagem);
            }
            case "2": {
                int i = 0;
                List<Mensagem> mensagens = usuarioService.listarMensagens();
                for(Usuario usuario: usuarioService.listar()){
                    resposta.println(usuario.getNome() + " - " + mensagens.get(i).getMensagem());
                    i++;
                }
                break;
            }
        }
    }
}

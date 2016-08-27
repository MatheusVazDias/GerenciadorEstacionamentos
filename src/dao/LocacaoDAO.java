/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Locacao;



/**
 *
 * @author KillerCoffeBR
 */
public class LocacaoDAO {
    Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
     public Boolean inserir (Locacao locacao)
    {
        Boolean retorno;
        //Monta o sql de insert da tabela
        String sql = "INSERT INTO locacao(cpf, vaga, entrada)" + "VALUES (?, ?, ?)";
        
        //Prepara a execução do meu sql
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        {
            //insere os parâmetros
//            pst.setString(1, cliente.getCpf()); //esse 1 é a ordem dos parâmetros
//            pst.setString(2, cliente.getNome());
//            pst.setString(3, cliente.getRg());
//            pst.setString(4, cliente.getTelefone());
              
              pst.setString(1, locacao.getCpf());
              pst.setInt(2, locacao.getVaga());
              pst.setTimestamp(3, locacao.getEntrada());

            pst.executeUpdate();
            retorno = true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            retorno = false;
        }
        
        return retorno;
    }
    
    public List<Locacao> listar()
    {
        List <Locacao> lista = new ArrayList<Locacao>();
        String sql = "SELECT * FROM locacao";
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        { 
            //Executo o sql e jogo em um resultset
            ResultSet res = pst.executeQuery();
            //Enquanto tiver registro eu vou relacionar com minha classe Jogador e adicionar na lista
            while (res.next())
            {
                Locacao locacao = new Locacao();
                //automovel.setLogin(res.getString("login"));
                //automovel.setSenha(res.getString("senha"));
                //automovel.setEmail(res.getString("email"));
                locacao.setCpf(res.getString("cpf"));
                locacao.setVaga(res.getInt("vaga"));
                locacao.setId(res.getInt("id"));
                locacao.setEntrada(res.getTimestamp("entrada"));
                locacao.setSaida(res.getTimestamp("saida"));
                lista.add(locacao);
            }
        }
        
        catch (SQLException ex) 
        {
            Logger.getLogger(AutomovelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public Boolean remover (Locacao locacao)
    {
        Boolean retorno;
        
        String sql = "DELETE FROM locacao WHERE cpf = ?";
        
        //Prepara a execução do meu sql
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        {
            pst.setString(1, locacao.getCpf());
            pst.executeUpdate();
            retorno = true;
        }
        
        catch (Exception ex)
        {
            retorno = false;         
        }
        
        return retorno;
    }
    
    public Boolean atualizar (Locacao locacao)
    {
        Boolean retorno;
        
        String sql = "UPDATE LOCACAO SET CPF = ?, VAGA = ?, ID = ?, ENTRADA = ?, SAIDA = ? WHERE CPF = ?";
        
        
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        {
            //pst.setString(1, jogador.getSenha());
            //pst.setString(2, jogador.getEmail());
            //pst.setString(3, jogador.getLogin());
            
                pst.setString(1, locacao.getCpf());
              pst.setInt(2, locacao.getVaga());
              pst.setInt(3, locacao.getId());
              pst.setTimestamp(4, locacao.getEntrada());
              pst.setTimestamp(5, locacao.getSaida());
           
            
            
            pst.executeUpdate();
            
            retorno = true;
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
            retorno = false;
        }
        
        return retorno;
    }
}

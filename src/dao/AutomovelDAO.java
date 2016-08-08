/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Automovel;
/**
 *
 * @author KillerCoffeBR
 */
public class AutomovelDAO {
    
    
    public Boolean inserir (Automovel automovel)
    {
        Boolean retorno;
        //Monta o sql de insert da tabela
        String sql = "INSERT INTO automovel(placa, cpf, modelo, cor, ano)" + "VALUES (?, ?, ?, ?, ?)";
        
        //Prepara a execução do meu sql
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        {
            //insere os parâmetros
            pst.setString(1, automovel.getPlaca()); //esse 1 é a ordem dos parâmetros
            pst.setString(2, automovel.getCpf());
            pst.setString(3, automovel.getModelo());
            pst.setString(4, automovel.getCor());
            pst.setInt(5, automovel.getAno());
            //pst.setInt(6, automovel.getId());
            
            //executa o sql no banco de dados
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
    
    public List<Automovel> listar()
    {
        List <Automovel> lista = new ArrayList<Automovel>();
        String sql = "SELECT * FROM automovel";
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        { 
            //Executo o sql e jogo em um resultset
            ResultSet res = pst.executeQuery();
            //Enquanto tiver registro eu vou relacionar com minha classe Jogador e adicionar na lista
            while (res.next())
            {
                Automovel automovel = new Automovel();
                //automovel.setLogin(res.getString("login"));
                //automovel.setSenha(res.getString("senha"));
                //automovel.setEmail(res.getString("email"));
                automovel.setPlaca(res.getString("placa"));
                automovel.setCpf(res.getString("cpf"));
                automovel.setModelo(res.getString("modelo"));
                automovel.setCor(res.getString("cor"));
                automovel.setAno(res.getInt("ano"));
                automovel.setId(res.getInt("id"));
                lista.add(automovel);
            }
        }
        
        catch (SQLException ex) 
        {
            Logger.getLogger(AutomovelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public Boolean remover (Automovel automovel)
    {
        Boolean retorno;
        
        String sql = "DELETE FROM automovel WHERE cpf = ?";
        
        //Prepara a execução do meu sql
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        {
            pst.setString(1, automovel.getCpf());
            pst.executeUpdate();
            retorno = true;
        }
        
        catch (Exception ex)
        {
            retorno = false;         
        }
        
        return retorno;
    }
    
    public Boolean atualizar (Automovel automovel)
    {
        Boolean retorno;
        
        String sql = "UPDATE AUTOMOVEL SET PLACA = ?, CPF = ?, MODELO = ?, COR = ?, ANO = ? WHERE CPF = ?";
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        {
            //pst.setString(1, jogador.getSenha());
            //pst.setString(2, jogador.getEmail());
            //pst.setString(3, jogador.getLogin());
            
            pst.setString(1, automovel.getPlaca()); //esse 1 é a ordem dos parâmetros
            pst.setString(2, automovel.getCpf());
            pst.setString(3, automovel.getModelo());
            pst.setString(4, automovel.getCor());
            pst.setInt(5, automovel.getAno());
            
            
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
    
//    public Jogador login (Jogador jogador)
//    {
//        Jogador retorno = null;
//        String sql = "SELECT * FROM jogador WHERE login = ? and senha = ?";
//        
//        PreparedStatement pst = Conexão.getPreparedStatement(sql);
//        
//        try 
//        {
//            pst.setString(1, jogador.getLogin());
//            pst.setString(2, jogador.getSenha());
//            
//            ResultSet res = pst.executeQuery();
//            
//            //se tiver um jogador com login e senha igual ao informado
//            //preenche todos os campos
//            
//            if (res.next())
//            {
//                retorno = new Jogador();
//                retorno.setEmail(res.getString("email"));
//                retorno.setLogin(res.getString("login"));
//                retorno.setSenha(res.getString("senha"));
//            }
//        } 
//        catch (Exception e) 
//        {
//            
//        }
//        
//        return retorno;
//    }
}

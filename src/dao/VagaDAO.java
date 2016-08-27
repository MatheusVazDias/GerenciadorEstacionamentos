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
import modelo.Locacao;
import modelo.Vaga;

/**
 *
 * @author KillerCoffeBR
 */
public class VagaDAO {
    public Boolean atualizar (Vaga vaga)
    {
        Boolean retorno;
        
        String sql = "UPDATE LOCACAO SET STATUS = ? WHERE id = ?";
        
        
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        {
            //pst.setString(1, jogador.getSenha());
            //pst.setString(2, jogador.getEmail());
            //pst.setString(3, jogador.getLogin());
            
                pst.setBoolean(1, vaga.isStatus());
                
            
            
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
    public List<Vaga> listar()
    {
        List <Vaga> lista = new ArrayList<Vaga>();
        String sql = "SELECT * FROM vaga order by id";
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        { 
            //Executo o sql e jogo em um resultset
            ResultSet res = pst.executeQuery();
            //Enquanto tiver registro eu vou relacionar com minha classe Jogador e adicionar na lista
            while (res.next())
            {
                Vaga vaga = new Vaga();
                //automovel.setLogin(res.getString("login"));
                //automovel.setSenha(res.getString("senha"));
                //automovel.setEmail(res.getString("email"));
                vaga.setId(res.getInt("id"));
                vaga.setStatus(res.getBoolean("status"));
                
                lista.add(vaga);
            }
        }
        
        catch (SQLException ex) 
        {
            Logger.getLogger(AutomovelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}

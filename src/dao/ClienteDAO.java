package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Cliente;
/**
 *
 * @author KillerCoffeBR
 */
public class ClienteDAO {
    
    public Boolean inserir (Cliente cliente)
    {
        Boolean retorno;
        List <Cliente> lista = new ArrayList<Cliente>();
        //Monta o sql de insert da tabela
        String sql = "INSERT INTO cliente(cpf, nome, rg, telefone, bonusacumulado)" + "VALUES (?, ?, ?, ?, 0.0)";
        
        //Prepara a execução do meu sql
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        {
            //insere os parâmetros
            pst.setString(1, cliente.getCpf()); //esse 1 é a ordem dos parâmetros
            pst.setString(2, cliente.getNome());
            pst.setString(3, cliente.getRg());
            pst.setString(4, cliente.getTelefone());
            

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
    
    public List<Cliente> listar()
    {
        List <Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente";
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
                      
        try 
        { 
            //Executo o sql e jogo em um resultset
           ResultSet res = pst.executeQuery();
            //Enquanto tiver registro eu vou relacionar com minha classe Jogador e adicionar na lista
            while (res.next())
            {
                
                Cliente cliente = new Cliente();
                
                //cliente.setBonusacumulado(res.getDouble("bonus"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setRg(res.getString("rg"));
                cliente.setTelefone(res.getString("telefone"));
                lista.add(cliente);
            }
        }
        
        catch (SQLException ex) 
        {
            Logger.getLogger(AutomovelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public Boolean remover (Cliente cliente)
    {
        Boolean retorno;
        
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        
        //Prepara a execução do meu sql
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        {
            pst.setString(1, cliente.getCpf());
            pst.executeUpdate();
            retorno = true;
        }
        
        catch (Exception ex)
        {
            retorno = false;         
        }
        
        return retorno;
    }
    
    public Boolean atualizar (Cliente cliente)
    {
        Boolean retorno;
        
        String sql = "UPDATE CLIENTE SET CPF = ?, NOME = ?, RG = ?, TELEFONE = ?, BONUSACUMULADO = ? WHERE CPF = ?";
        
        
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try 
        {
            //pst.setString(1, jogador.getSenha());
            //pst.setString(2, jogador.getEmail());
            //pst.setString(3, jogador.getLogin());
            
            pst.setString(1, cliente.getCpf()); //esse 1 é a ordem dos parâmetros
            pst.setString(2, cliente.getNome());
            pst.setString(3, cliente.getRg());
            pst.setString(4, cliente.getTelefone());
            pst.setDouble(5, cliente.getBonusacumulado());
            pst.setInt(6, cliente.getId());
           
            
            
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

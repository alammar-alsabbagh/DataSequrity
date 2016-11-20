/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Model.Account;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author omarsabbagh
 */
public class AccountsTableModel extends AbstractTableModel{
    
    private ArrayList<Account> accounts ;
    private String[] columns;
    
    
    public AccountsTableModel(ArrayList<Account> accounts){
        super();
        this.accounts = accounts;
        columns = new String[]{"Account id","Client id","Account Balance"};
    }

    @Override
   public int getColumnCount() {
    return columns.length ;
  }

    @Override
    public int getRowCount() {
    return accounts.size();
    }
    
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      Account account = accounts.get(rowIndex);
      switch (columnIndex) {
      case 0:
        return account.getAccount_id();
      case 1:
        return account.getAccount_client_id();
      case 2:
        return account.getAccount_balance();
      }
        return null;
    }
 
    
}

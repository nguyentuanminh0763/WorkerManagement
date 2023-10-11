/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.History;

/**
 *
 * @author Nguyen Quang Hau
 */
public class HistoryDao {
    private static HistoryDao instance;

    public static HistoryDao getInstace(){
        if(instance == null){
            synchronized (WorkerDao.class) {
                if(instance == null){
                     instance = new HistoryDao();
                }
            }
        }
        return instance;
    }

    public void add(History history, List<History> hisList)throws Exception{
        hisList.add(history);
    }

    
    
}

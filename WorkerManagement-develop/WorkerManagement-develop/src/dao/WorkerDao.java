/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Worker;

/**
 *
 * @author Nguyen Quang Hau
 */
public class WorkerDao {
    private static WorkerDao instance;

    public static WorkerDao getInstace(){
        if(instance == null){
            synchronized (WorkerDao.class) {
                if(instance == null){
                     instance = new WorkerDao();
                }
            }
        }
        return instance;
    }
    
    public void add(Worker worker, List<Worker> list) throws Exception{
        list.add(worker);
    } 

    public Worker selectWorkerById(String id, List<Worker> list) {
        for (Worker worker : list) {
            if(worker.getId().equals(id)) return worker;
        }
        
        return null;
    }
     
    
    
}

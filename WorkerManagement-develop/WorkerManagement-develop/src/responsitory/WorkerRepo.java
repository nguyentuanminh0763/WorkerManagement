/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import common.Validation;
import dao.HistoryDao;
import dao.WorkerDao;
import java.util.ArrayList;
import java.util.List;
import model.History;
import model.Worker;

/**
 *
 * @author Nguyen Quang Hau
 */
public class WorkerRepo implements IWorkerRepo {

    private List<Worker> list;
    private Validation val;
    private List<History> hisList;

    public WorkerRepo() {
        list = new ArrayList<>();
        val = new Validation();
        hisList = new ArrayList<>();
    }

    @Override
    public void add() {
        try {
            Worker worker = new Worker();
            boolean existed = false;

            do {
                worker.setId(val.getString("Enter id(Wxxxx): ", "^W\\d{4}$"));
                if (isIdExisting(worker.getId())) {
                    System.out.println("The id is existing. Try again.");
                    existed = true;
                } else {
                    existed = false;
                }
            } while (existed);

            worker.setName(val.getString("Enter name: "));
            worker.setAge(val.getIntData("Enter age: ", 18, 50));
            worker.setSalary(val.getDoubleData("Enter salary: ", 0.0001));
            worker.setWorkLocation(val.getString("Enter work location: "));

            WorkerDao.getInstace().add(worker, list);
            System.out.println("Added");
        } catch (Exception e) {
            System.err.println("Adding Error");
        }
    }

    @Override
    public void computeSalary(String status) {
        try {
            Worker worker = new Worker();
            double salary = 0;
            double amountOfMoney = 0;
            boolean existed = false;

            do {
                worker.setId(val.getString("Enter id(Wxxxx): ", "^W\\d{4}$"));
                if (isIdExisting(worker.getId())) {
                    existed = true;
                } else {
                    System.out.println("The id does not exist. Try again.");
                    existed = false;
                }
            } while (!existed);

            worker = WorkerDao.getInstace().selectWorkerById(worker.getId(), list);
            System.out.println("Worker: " + worker);
            amountOfMoney = val.getDoubleData("Enter a mount of money: ", 0.0001);

            if (status.equalsIgnoreCase("Up")) {
                salary = (worker.getSalary() + amountOfMoney);
            } else {
                if (amountOfMoney > worker.getSalary()) {
                    System.err.println("ALERT: According to work humanitarian, so the company decide to set the salary equal 0");
                    salary = 0f;
                } else {
                    salary = (worker.getSalary() - amountOfMoney);
                }
            }

            History history = new History();
            history.setWorker(new Worker(worker.getId(), worker.getName(), worker.getAge(), salary, worker.getWorkLocation()));
            history.setStatus(status);
            history.setDate(val.getFormatedDate());
            HistoryDao.getInstace().add(history, hisList);

            System.out.println("Updated");
        } catch (Exception e) {
            System.err.println("Updating error");
        }
    }

    public boolean isIdExisting(String id) {
        for (Worker worker : list) {
            if (worker.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void display() {
        System.out.println("=========Display Information Salary===========");
        System.out.printf("%-6s%-15s%-6s%-10s%-7s%-18s\n", "Id", "Name", "Age", "Salary", "Status", "Date");
        for (History history : hisList) {
            Worker worker = history.getWorker();
            System.out.printf("%-6s%-15s%-6s%-10s%-7s%-15s\n", worker.getId(), worker.getName(), worker.getAge(), worker.getSalary(), history.getStatus(), history.getDateStr());
        }
    }

}

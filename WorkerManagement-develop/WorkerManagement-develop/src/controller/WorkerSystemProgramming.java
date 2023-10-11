/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import responsitory.WorkerRepo;
import view.Menu;

/**
 *
 * @author Nguyen Quang Hau
 */
public class WorkerSystemProgramming extends Menu<String> {
    private WorkerRepo workerRepo;

    public WorkerSystemProgramming() {
        super("WORKER MANAGEMENT", new String[]{"Add Worker", "Up Salary", "Down Salary", "Display Information Salary","Exit"});
        workerRepo = new WorkerRepo();
    }

    @Override
    public void excute(int n) {
        switch (n) {
            case 1:
                workerRepo.add();
                break;
            case 2:
                workerRepo.computeSalary("Up");
                break;
            case 3:
                workerRepo.computeSalary("Down");
                break;
            case 4:
                workerRepo.display();
                break;
        }
    }

}

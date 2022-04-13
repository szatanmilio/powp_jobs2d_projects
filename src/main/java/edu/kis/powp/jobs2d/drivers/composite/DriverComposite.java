package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.LinkedList;
import java.util.List;

public class DriverComposite implements IDriverComposite {

    private final List<Job2dDriver> job2dDriverList = new LinkedList<>();

    @Override
    public void setPosition(int i, int i1) {
        job2dDriverList.forEach(job2dDriver -> job2dDriver.setPosition(i, i1));
    }

    @Override
    public void operateTo(int i, int i1) {
        job2dDriverList.forEach(job2dDriver -> job2dDriver.operateTo(i, i1));
    }

    @Override
    public void add(Job2dDriver driver) {
        job2dDriverList.add(driver);
    }

    @Override
    public void remove(Job2dDriver driver) {
        job2dDriverList.remove(driver);
    }
}

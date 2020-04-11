package aoss.assignment.a2.drivers;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 11.04.2020 */

import aoss.assignment.a2.drivers.run.MainDriver;
import aoss.assignment.a2.drivers.run.RunDriver;

public class DriverClass {

    public static void main(String[] args) {

        RunDriver runDriver = new MainDriver(args);
        runDriver.start();
    }
}

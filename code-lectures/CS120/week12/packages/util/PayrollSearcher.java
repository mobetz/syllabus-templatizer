
package util;

import data.model.Employee;
import data.model.Job;

import java.util.List;
import java.util.ArrayList;

public class PayrollSearcher {

    private List<Employee> employees;


    public PayrollSearcher(List<Employee> loaded_employees) {
        this.employees = loaded_employees;
    }

    public  List<Employee> findEmployeesByDept(String dept) {
        List<Employee> dept_matches = new ArrayList<>();

        for ( Employee next : this.employees ) {
            if ( next.getPayForDepartment(dept) > 0 ) {
                dept_matches.add(next);
            }
        }

        return dept_matches;

    }


    public  List<Employee> findEmployeesByJobTitle(String job) {
        List<Employee> matches = new ArrayList<>();

        for ( Employee next : this.employees ) {
            if ( next.worksInJob(job) ) {
                matches.add(next);
            }
        }

        return matches;

    }


    public double totalSalaryForDept(String dept) {
        double total_for_dept = 0;

        List<Employee> employees_in_dept = this.findEmployeesByDept(dept);

        for ( Employee employee : employees_in_dept ) {
            total_for_dept = total_for_dept + employee.getPayForDepartment(dept);
        }

        return total_for_dept;
    }


    public double avgSalaryForJob(String job) {

        double total_for_job = 0;
        double num_positions = 0;

        List<Employee> people_in_job = this.findEmployeesByJobTitle(job);

        for ( Employee next_guy : people_in_job ) {
            for ( Job position_they_hold : next_guy.getJobMatches(job)) {
                total_for_job = total_for_job + position_they_hold.getPay();
                num_positions = num_positions + 1;
            }
        }

        return total_for_job / num_positions;
    }


    public  List<Employee> findEmployeesByJobAndDept(String job, String dept) {
        List<Employee> matches = new ArrayList<>();

        for ( Employee next : this.employees ) {
            if ( next.worksInJob(job) && next.getPayForDepartment(dept) > 0 ) {
                matches.add(next);
            }
        }

        return matches;

    }


}
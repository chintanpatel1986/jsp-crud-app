package com.codecamp.app.repository;

import com.codecamp.app.model.Employee;
import com.codecamp.app.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public boolean insertEmployee(Employee employee) {
        boolean result = false;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_employee(first_name, middle_name, last_name, address, email, mobile, birth_date, user_name, password, department_id)values (?,?,?,?,?,?,?,?,?,?)"))
        {
                pstmt.setString(1, employee.getFirstName());
                pstmt.setString(2, employee.getMiddleName());
                pstmt.setString(3, employee.getLastName());
                pstmt.setString(4, employee.getAddress());
                pstmt.setString(5, employee.getEmail());
                pstmt.setLong(6, employee.getMobile());
                pstmt.setDate(7,new Date(employee.getBirthDate().getTime()));
                pstmt.setString(8, employee.getUserName());
                pstmt.setString(9, employee.getPassword());
                pstmt.setInt(10, employee.getDepartmentId());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<Employee> getAllEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select e.*, d.department_id, d.department_name from tbl_department d, tbl_employee e where e.department_id=d.department_id");
             ResultSet rs = pstmt.executeQuery())
        {
                    if (rs != null) {
                        while (rs.next()) {
                            employee = new Employee();
                            employee.setEmployeeId(rs.getInt("employee_id"));
                            employee.setFirstName(rs.getString("first_name"));
                            employee.setMiddleName(rs.getString("middle_name"));
                            employee.setLastName(rs.getString("last_name"));
                            employee.setAddress(rs.getString("address"));
                            employee.setEmail(rs.getString("email"));
                            employee.setMobile(rs.getLong("mobile"));
                            employee.setBirthDate(rs.getDate("birth_date"));
                            employee.setUserName(rs.getString("user_name"));
                            employee.setPassword(rs.getString("password"));
                            employee.setDepartmentId(rs.getInt("department_id"));
                            employee.setDepartmentName(rs.getString("department_name"));
                            employeeList.add(employee);
                        }
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }


    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;
        ResultSet rs = null;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select e.*, d.department_id, d.department_name from tbl_department d, tbl_employee e where e.department_id=d.department_id and employee_id=?"))
        {
                    pstmt.setInt(1, employeeId);
                    rs = pstmt.executeQuery();
                    if (rs != null) {
                        if (rs.next()) {
                            employee = new Employee();
                            employee.setEmployeeId(rs.getInt("employee_id"));
                            employee.setFirstName(rs.getString("first_name"));
                            employee.setMiddleName(rs.getString("middle_name"));
                            employee.setLastName(rs.getString("last_name"));
                            employee.setAddress(rs.getString("address"));
                            employee.setEmail(rs.getString("email"));
                            employee.setMobile(rs.getLong("mobile"));
                            employee.setBirthDate(rs.getDate("birth_date"));
                            employee.setUserName(rs.getString("user_name"));
                            employee.setPassword(rs.getString("password"));
                            employee.setDepartmentId(rs.getInt("department_id"));
                            employee.setDepartmentName(rs.getString("department_name"));
                        }
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return employee;
    }


    public boolean updateEmployee(Employee employee) {
        boolean result = false;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("update tbl_employee set first_name=?,middle_name=?,last_name=?,address=?,email=?,mobile=?,birth_date=?,user_name=?,password=?,department_id=? where employee_id=?"))
        {
                pstmt.setString(1, employee.getFirstName());
                pstmt.setString(2, employee.getMiddleName());
                pstmt.setString(3, employee.getLastName());
                pstmt.setString(4, employee.getAddress());
                pstmt.setString(5, employee.getEmail());
                pstmt.setLong(6, employee.getMobile());
                pstmt.setDate(7, new Date(employee.getBirthDate().getTime()));
                pstmt.setString(8, employee.getUserName());
                pstmt.setString(9, employee.getPassword());
                pstmt.setInt(10, employee.getDepartmentId());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean deleteEmployee(int employeeId) {
        boolean result = false;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("delete from tbl_employee where employee_id=?"))
        {
                pstmt.setInt(1, employeeId);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

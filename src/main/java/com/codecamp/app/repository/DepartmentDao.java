package com.codecamp.app.repository;

import com.codecamp.app.model.Department;
import com.codecamp.app.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {

    public boolean insertDepartment(Department department) {
        boolean result = false;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_department(department_name)values (?)"))
        {
                pstmt.setString(1, department.getDepartmentName());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<Department> getAllDepartmentList() {
        List<Department> departmentList = new ArrayList<>();
        Department department;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select * from tbl_department");
        ResultSet rs = pstmt.executeQuery())
        {
                if (rs != null) {
                    while (rs.next()) {
                        department = new Department();
                        department.setDepartmentId(rs.getInt("department_id"));
                        department.setDepartmentName(rs.getString("department_name"));
                        departmentList.add(department);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }


    public Department getDepartmentById(int departmentId) {
        Department department = null;
        ResultSet rs = null;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select * from tbl_department where department_id=?"))
        {
                pstmt.setInt(1, departmentId);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        department = new Department();
                        department.setDepartmentId(rs.getInt("department_id"));
                        department.setDepartmentName(rs.getString("department_name"));
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
        return department;
    }


    public boolean updateDepartment(Department department) {
        boolean result = false;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("update tbl_department set department_name=? where department_id=?"))
        {
                pstmt.setString(1, department.getDepartmentName());
                pstmt.setInt(2, department.getDepartmentId());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean deleteDepartment(int departmentId) {
        boolean result = false;
        ResultSet rs = null;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("delete from tbl_department where department_id=?"))
        {
                pstmt.setInt(1, departmentId);
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

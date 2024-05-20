package com.codecamp.app.controller;

import com.codecamp.app.model.Department;
import com.codecamp.app.repository.DepartmentDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DepartmentController", value = "/DepartmentController")
public class DepartmentController extends HttpServlet {
    private static final String INSERT_OR_UPDATE = "department.jsp";
    private static final String DEPARTMENT_LIST = "department.jsp";
    private static final String ERROR = "error.jsp";
    private final DepartmentDao departmentDao;

    public DepartmentController() {
        departmentDao = new DepartmentDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward;
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            forward = DEPARTMENT_LIST;
            int departmentId = Integer.parseInt(req.getParameter("departmentId"));
            boolean isDeleted = departmentDao.deleteDepartment(departmentId);
                if (isDeleted) {
                    req.setAttribute("departmentList",departmentDao.getAllDepartmentList());
                } else {
                    req.getRequestDispatcher(ERROR).include(req, resp);
                }
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_UPDATE;
            int departmentId = Integer.parseInt(req.getParameter("departmentId"));
            Department department = departmentDao.getDepartmentById(departmentId);
            req.setAttribute("department", department);
            req.setAttribute("departmentList", departmentDao.getAllDepartmentList());
        } else {
            forward = INSERT_OR_UPDATE;
            req.setAttribute("departmentList", departmentDao.getAllDepartmentList());
        }
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Department department = new Department();
        department.setDepartmentName(req.getParameter("departmentName"));

        String departmentId = req.getParameter("departmentId");

        if (departmentId == null || departmentId.isEmpty()) {
            boolean isInserted = departmentDao.insertDepartment(department);
                if (isInserted) {
                    RequestDispatcher view = req.getRequestDispatcher(DEPARTMENT_LIST);
                    req.setAttribute("departmentList", departmentDao.getAllDepartmentList());
                    view.forward(req, resp);
                } else {
                    req.getRequestDispatcher(ERROR).include(req, resp);
                }
        } else {
            department.setDepartmentId(Integer.parseInt(departmentId));
            boolean isUpdated = departmentDao.updateDepartment(department);
            if (isUpdated) {
                RequestDispatcher view = req.getRequestDispatcher(DEPARTMENT_LIST);
                req.setAttribute("departmentList", departmentDao.getAllDepartmentList());
                view.forward(req, resp);
            } else {
                req.getRequestDispatcher(ERROR).include(req, resp);
            }
        }
    }
}

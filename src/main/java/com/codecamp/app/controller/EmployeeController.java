package com.codecamp.app.controller;

import com.codecamp.app.model.Employee;
import com.codecamp.app.repository.EmployeeDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "EmployeeController", value = "/EmployeeController")
public class EmployeeController extends HttpServlet {
    private static final String INSERT_OR_UPDATE = "employee.jsp";
    private static final String EMPLOYEE_LIST = "employee.jsp";
    private static final String ERROR = "error.jsp";
    private final EmployeeDao employeeDao;

    public EmployeeController() {
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward;
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            forward = EMPLOYEE_LIST;
            int employeeId = Integer.parseInt(req.getParameter("employeeId"));
            boolean isDeleted = employeeDao.deleteEmployee(employeeId);
                if (isDeleted) {
                    req.setAttribute("employeeList",employeeDao.getAllEmployeeList());
                } else {
                    req.getRequestDispatcher(ERROR).include(req, resp);
                }
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_UPDATE;
            int employeeId = Integer.parseInt(req.getParameter("employeeId"));
            Employee employee = employeeDao.getEmployeeById(employeeId);
            req.setAttribute("employee", employee);
            req.setAttribute("employeeList", employeeDao.getAllEmployeeList());
        } else {
            forward = INSERT_OR_UPDATE;
            req.setAttribute("employeeList", employeeDao.getAllEmployeeList());
        }
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee employee = new Employee();
        employee.setFirstName(req.getParameter("firstName"));
        employee.setMiddleName(req.getParameter("middleName"));
        employee.setLastName(req.getParameter("lastName"));
        employee.setAddress(req.getParameter("address"));
        employee.setEmail(req.getParameter("email"));
        employee.setMobile(Long.parseLong(req.getParameter("mobile")));
        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employee.setBirthDate(birthDate);
        employee.setUserName(req.getParameter("userName"));
        employee.setPassword(req.getParameter("password"));

        String employeeId = req.getParameter("employeeId");

        if (employeeId == null || employeeId.isEmpty()) {
            boolean isInserted = employeeDao.insertEmployee(employee);
                if (isInserted) {
                    RequestDispatcher view = req.getRequestDispatcher(EMPLOYEE_LIST);
                    req.setAttribute("employeeList", employeeDao.getAllEmployeeList());
                    view.forward(req, resp);
                } else {
                     req.getRequestDispatcher(ERROR).include(req, resp);
                }
        } else {
            employee.setEmployeeId(Integer.parseInt(employeeId));
            boolean isUpdated = employeeDao.updateEmployee(employee);
                if (isUpdated) {
                    RequestDispatcher view = req.getRequestDispatcher(EMPLOYEE_LIST);
                    req.setAttribute("employeeList", employeeDao.getAllEmployeeList());
                    view.forward(req, resp);
                } else {
                    req.getRequestDispatcher(ERROR).include(req, resp);
                }
        }
    }
}

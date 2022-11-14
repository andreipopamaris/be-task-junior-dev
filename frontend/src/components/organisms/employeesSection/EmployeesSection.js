import { Card, CardContent, Grid, IconButton, Typography } from "@mui/material";
import AddCircleIcon from '@mui/icons-material/AddCircle';
import React from "react";
import Employee from "../../molecules/employee/Employee";
import './employeesSection.scss'

export default function EmployeesSection(props) {
    return (
        <Grid item md={4} lg={3} id="employeesSection">
            <Card id="employeesSectionCard">
                <CardContent className="sectionCardContent">
                    <Grid container id="employeesContainer">
                        <Grid item xs={12} id="employeesTitle">
                            <Typography variant="h5">Employees</Typography>
                            <IconButton aria-label="create task" size="large" onClick={props.handleOpenEmployeeModal}>
                                <AddCircleIcon fontSize="inherit" className="createIcon" />
                            </IconButton>
                        </Grid>
                        <Grid item xs={12} id="employeesContent">
                            <Grid spacing={1} className="employeesGrid" container>

                                {props.employees.map(employee => {
                                    return (
                                        <Employee
                                            key={employee.id}
                                            selectedEmployee={props.selectedEmployee}
                                            setSelectedEmployee={props.setSelectedEmployee}
                                            employee={employee}
                                            reloadHome={props.reloadHome}
                                            handleOpenEmployeeModal={props.handleOpenEmployeeModal}
                                            handleCloseEmployeeModal={props.handleCloseEmployeeModal}
                                            employeeName={props.employeeName}
                                            setEmployeeName={props.setEmployeeName}
                                            updateEmployee={props.updateEmployee}
                                            setUpdateEmployee={props.setUpdateEmployee}
                                            employeeId={props.employeeId}
                                            setEmployeeId={props.setEmployeeId}
                                        />
                                    )
                                })}
                            </Grid>

                        </Grid>

                    </Grid>
                </CardContent>
            </Card>
        </Grid>
    )
}
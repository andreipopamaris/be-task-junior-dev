import { Card, CardContent, Grid, IconButton, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import AddCircleIcon from '@mui/icons-material/AddCircle';

import dayjs from 'dayjs';

import axios from "axios";
import Task from "../../molecules/task/Task";
import TaskModal from "../../organisms/taskModal/TaskModal";

import Conf from '../../../conf/conf.json'
import EmployeeModal from "../../organisms/employeeModal/EmployeeModal";
import ApplicationTitle from "../../atoms/applicationTitle/ApplicationTitle";
import EmployeesSection from "../../organisms/employeesSection/EmployeesSection";

export default function Home(props) {

    // Tasks & Employees
    const [tasks, setTasks] = useState([]);
    const [employees, setEmployees] = useState([]);
   
    // Task modal
    const [openTaskModal, setOpenTaskModal] = useState(false);
    const [taskAssignee, setTaskAssignee] = useState(0);
    const [taskDescription, setTaskDescription] = useState("");
    const [taskDueDate, setTaskDueDate] = useState(dayjs(Date.now()));
    const [taskId, setTaskId] = useState(0);
    const [updateTask, setUpdateTask] = useState(false);

    const handleOpenTaskModal = () => {
        setOpenTaskModal(true);
    }

    const handleCloseTaskModal = () => {
        setTaskAssignee(0);
        setTaskDescription("");
        setTaskDueDate(dayjs(Date.now()));
        setUpdateTask(false);
        setTaskId(0);
        setOpenTaskModal(false);
    }

    // Employee modal
    const [openEmployeeModal, setOpenEmployeeModal] = useState(false);
    const [employeeName, setEmployeeName] = useState("");
    const [employeeId, setEmployeeId] = useState(0);
    const [updateEmployee, setUpdateEmployee] = useState(false);


    const handleOpenEmployeeModal = () => {
        setOpenEmployeeModal(true);
    }

    const handleCloseEmployeeModal = () => {
        setEmployeeName("")
        setEmployeeId(0)
        setUpdateEmployee(false);
        setOpenEmployeeModal(false);
    }

    const [selectedEmployee, setSelectedEmployee] = useState(0);

    const reloadHome = async () => {
        axios.get(`${Conf.api.base}${Conf.api.endpoints.employees.getAllEmplyees}`,)
        .then(res => {
            setEmployees(res.data)
            axios.get(`${Conf.api.base}${Conf.api.endpoints.tasks.getAllTasks}`,)
                .then(res => {
                    setTasks(res.data)
                })
                .catch(err => {
                    console.log(err);
                    alert(err)
                })
        })
        .catch(err => {
            console.log(err);
            alert(err)
        })
    }

    useEffect(() => {
        reloadHome()
    }, [selectedEmployee]);

    return (
        <Grid container style={{ height: '100vh', boxSizing: 'border-box', padding: 20 }}>
            <ApplicationTitle 
                title={"Amaris Task Manager"}
            />
            <EmployeesSection 
                 selectedEmployee={selectedEmployee}
                 setSelectedEmployee={setSelectedEmployee}
                 reloadHome={reloadHome}
                 handleOpenEmployeeModal={handleOpenEmployeeModal}
                 handleCloseEmployeeModal={handleCloseEmployeeModal}
                 employeeName={employeeName}
                 setEmployeeName={setEmployeeName}
                 updateEmployee={updateEmployee}
                 setUpdateEmployee={setUpdateEmployee}
                 employeeId={employeeId}
                 setEmployeeId={setEmployeeId}
                 employees={employees}
            />
            <Grid item md={8} lg={9} style={{ padding: "10px", height: "90%" }} id="tasksSection">
                <Card style={{ height: "100%" }}>
                    <CardContent style={{ height: "100%" }}>
                        <Grid container style={{ height: '100%', boxSizing: 'border-box', padding: 20 }} id="tasksContainer">
                            <Grid item xs={12} style={{ height: "10%", display: "flex", justifyContent: "space-between", alignItems: "center" }} id="tasksTitle">
                                <Typography variant="h5">Tasks</Typography>
                                <IconButton aria-label="create task" size="large" onClick={handleOpenTaskModal}>
                                    <AddCircleIcon fontSize="inherit" style={{ color: 'green' }} />
                                </IconButton>
                            </Grid>
                            <Grid item xs={12} style={{ height: "90%" }} id="tasksContent">
                                <Grid spacing={1} container style={{maxHeight:"100%",overflowY:"scroll", paddingBottom: "20px", paddingRight:"10px"}}>

                                    {tasks.map(task => {
                                        return (
                                           <Task 
                                                key={task.id}
                                                task={task} 
                                                employees={employees} 
                                                selectedEmployee={selectedEmployee} 
                                                reloadHome={reloadHome}
                                                taskAssignee={taskAssignee}
                                                setTaskAssignee={setTaskAssignee}
                                                taskDescription={taskDescription}
                                                setTaskDescription={setTaskDescription}
                                                taskDueDate={taskDueDate}
                                                setTaskDueDate={setTaskDueDate}
                                                updateTask={updateTask}
                                                setUpdateTask={setUpdateTask}
                                                handleOpenTaskModal={handleOpenTaskModal}
                                                taskId={taskId}
                                                setTaskId={setTaskId}
                                               
                                            />
                                        )
                                    })}
                                </Grid>

                            </Grid>
                        </Grid>
                    </CardContent>
                </Card>
            </Grid>

            <TaskModal 
                openTaskModal={openTaskModal} 
                handleCloseTaskModal={handleCloseTaskModal}
                employees={employees}
                setEmployees={setEmployees}
                setTasks={setTasks}
                reloadHome={reloadHome}
                taskAssignee={taskAssignee}
                setTaskAssignee={setTaskAssignee}
                taskDescription={taskDescription}
                setTaskDescription={setTaskDescription}
                taskDueDate={taskDueDate}
                setTaskDueDate={setTaskDueDate}
                updateTask={updateTask}
                taskId={taskId}
                
            />

            <EmployeeModal 
                openEmployeeModal={openEmployeeModal} 
                handleOpenEmployeeModal={handleOpenEmployeeModal}
                handleCloseEmployeeModal={handleCloseEmployeeModal}
                reloadHome={reloadHome}
                employeeName={employeeName}
                setEmployeeName={setEmployeeName}
                updateEmployee={updateEmployee}
                employeeId={employeeId}
            />

        </Grid>
    )
}
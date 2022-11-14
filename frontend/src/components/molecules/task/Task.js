import { ButtonGroup, Card, CardContent, Grid, IconButton, Typography } from "@mui/material";
import EditRoundedIcon from '@mui/icons-material/EditRounded';
import DeleteRoundedIcon from '@mui/icons-material/DeleteRounded';
import React from "react";
import {localDateToDate, localDateTimeToDate, findAssigneeName} from '../../../utils/utils.js'
import axios from "axios";
import Conf from '../../../conf/conf.json'



export default function Task(props) {

    const deleteTask = () => {
        axios.delete(`${Conf.api.base}${Conf.api.endpoints.tasks.deleteTask}${props.task.id}`,)
        .then(res => {
            console.log(res.data)
            switch(res.status) {
                case 200:
                    alert(res.data)
                    props.reloadHome();
                break;
                case 500: 
                    alert(res.data)
                break;
                default:
                    alert(res.data)
            }
        })
        .catch(err => {
            console.log(err);
            alert(err)
        })
    }

    const editTask = (e) => {
        props.setTaskAssignee(props.task.assignee !== null ? props.task.assignee : 0);
        props.setTaskDescription(props.task.description);
        props.setTaskDueDate(localDateToDate(props.task.dueDate));
        props.setUpdateTask(true);
        props.setTaskId(props.task.id);
        props.handleOpenTaskModal();
    }
    
    return(
        <Grid item xs={6} style={{height:"fit-content"}}>
        <Card style={{backgroundColor: props.task.assignee === props.selectedEmployee ? '#006c004a' : 'white' }}>
            <CardContent style={{ display: "flex", flexDirection: "column" }}>
                <Typography variant="h6">Task {props.task.id}</Typography>
                <Typography variant="p"><b>Description:</b> {props.task.description}</Typography>
                <Typography variant="p"><b>Due date:</b> {localDateToDate(props.task.dueDate)}</Typography>
                <Typography variant="p"><b>Creation date:</b> {localDateTimeToDate(props.task.creationDate)}</Typography>
                <Typography variant="p"><b>Last update:</b> {props.task.lastUpdate != null ? localDateTimeToDate(props.task.lastUpdate) : "No updates."}</Typography>
                <Typography variant="p"><b>Assignee:</b> {findAssigneeName(props.employees, props.task.assignee)}</Typography>
                <ButtonGroup variant="text" aria-label="outlined primary button group">
                    <IconButton aria-label="create task" size="large" onClick={editTask}>
                        <EditRoundedIcon fontSize="inherit" style={{ color: 'orange' }} />
                    </IconButton>
                    <IconButton aria-label="create task" size="large" onClick={deleteTask}>
                        <DeleteRoundedIcon fontSize="inherit" style={{ color: 'red' }} />
                    </IconButton>
                </ButtonGroup>
            </CardContent>
        </Card>
    </Grid>
    )
}
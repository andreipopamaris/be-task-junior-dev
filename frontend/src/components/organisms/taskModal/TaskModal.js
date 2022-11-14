import { Button, FormControl, InputLabel, MenuItem, Modal, Select, TextField, Typography } from "@mui/material";
import { Box } from "@mui/system";
import axios from "axios";
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import React from "react";
import dateFormat from 'dateformat';

import Conf from '../../../conf/conf.json'

export default function TaskModal(props) {
    

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'white',
        border: '2px solid #000',
        boxShadow: 24,
        pt: 2,
        px: 4,
        pb: 3,
        display: 'flex',
        flexDirection: 'column',
        gap: '20px'
    };

    // Form dialog inputs
    

    const handleTaskAssigneeChange = (e) => {
        props.setTaskAssignee(e.target.value);
    }

    const handleTaskDescriptionChange = (e) => {
        props.setTaskDescription(e.target.value);
    }

    const handleTaskDueDateChange = (e) => {
        console.log(e)
        props.setTaskDueDate(e.$d);
    }

    const createTask = () => {
        let data = {
            description: props.taskDescription,
            dueDate: dateFormat(props.taskDueDate, "yyyy-mm-dd"),
            creationDate: new Date(Date.now() - ((new Date()).getTimezoneOffset() * 60000)).toISOString()
            
        }
        if(props.taskAssignee !== 0) {
            data.assignee = props.taskAssignee
        }
        console.log(data)
        
        axios.post(`${Conf.api.base}${Conf.api.endpoints.tasks.createTask}`,data)
        .then(res => {
            switch(res.status) {
                case 201:
                   props.reloadHome();
                   props.handleCloseTaskModal();
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

    const updateTask = () => {
        let data = {
            id: props.taskId,
            description: props.taskDescription,
            dueDate: dateFormat(props.taskDueDate, "yyyy-mm-dd"),
            lastUpdate: new Date(Date.now() - ((new Date()).getTimezoneOffset() * 60000)).toISOString()
        }

        if(props.taskAssignee !== 0) {
            data.assignee = props.taskAssignee
        }
        console.log(data)

        axios.put(`${Conf.api.base}${Conf.api.endpoints.tasks.updateTask}${data.id}`,data)
        .then(res => {
            switch(res.status) {
                case 200:
                    props.reloadHome();
                    props.handleCloseTaskModal();
                    alert(`Updated task with id ${props.taskId}.`)
                break;
                case 201:
                    props.reloadHome();
                    props.handleCloseTaskModal();
                    alert(`Created new task, because there was no task with id ${props.taskId}.`)
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

    return(
        <Modal
                open={props.openTaskModal}
                onClose={props.handleCloseTaskModal}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        {props.updateTask ? "Update task" : "Create task"}
                    </Typography>
                    <FormControl fullWidth>
                        <InputLabel id="demo-simple-select-label">Assegnee</InputLabel>
                        <Select
                            labelId="demo-simple-select-label"
                            id="demo-simple-select"
                            value={props.taskAssignee}
                            label="Assegnee"
                            onChange={handleTaskAssigneeChange}
                        >
                            <MenuItem value={0} style={{ height: 30 }}></MenuItem>
                            {props.employees.map(employee => {
                                return (
                                    <MenuItem value={employee.id} key={employee.id}>{employee.name}</MenuItem>
                                )
                            })}
                        </Select>
                    </FormControl>
                    <TextField id="standard-basic" label="Description" variant="outlined" value={props.taskDescription} onChange={handleTaskDescriptionChange} />
                    <LocalizationProvider dateAdapter={AdapterDayjs}>

                        <DesktopDatePicker
                            label="Date desktop"
                            inputFormat="YYYY-MM-DD"
                            value={props.taskDueDate}
                            onChange={handleTaskDueDateChange}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>
                    <Button variant="contained" onClick={props.updateTask ? updateTask : createTask}>Save</Button>
                </Box>
            </Modal>
    )
}
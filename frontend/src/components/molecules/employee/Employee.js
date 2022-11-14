import { ButtonGroup, Card, CardContent, Grid, IconButton, Typography } from "@mui/material";
import EditRoundedIcon from '@mui/icons-material/EditRounded';
import DeleteRoundedIcon from '@mui/icons-material/DeleteRounded';
import React from "react";
import axios from "axios";
import Conf from '../../../conf/conf.json'


export default function Employee(props) {
    const handleEmplClick = (e) => {
        console.log(e.target)
        if(!e.target.className.includes("editEmployee") && !e.target.className.includes("deleteEmployee") ) {
            if (props.selectedEmployee === props.employee.id) 
                props.setSelectedEmployee(0)
            else 
                props.setSelectedEmployee(props.employee.id)
        }
    }

    const deleteEmployee = (e) => {
        console.log(e.target)
        e.preventDefault();
        axios.delete(`${Conf.api.base}${Conf.api.endpoints.employees.deleteEmployee}${props.employee.id}`,)
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

    const editEmployee = (e) => {
        console.log(e.target)
        props.setEmployeeName(props.employee.name)
        props.setEmployeeId(props.employee.id)
        props.setUpdateEmployee(true);
        props.handleOpenEmployeeModal();
    }

    return(
        <Grid item xs={12} onClick={handleEmplClick} style={{cursor:'pointer'}}>
            <Card style={{backgroundColor: props.employee.id === props.selectedEmployee ? '#006c004a' : 'white' }}>
                <CardContent style={{display:"flex", justifyContent:"space-between", alignItems:"center"}}>
                    <Typography variant="body1">{props.employee.name}</Typography>
                    
                    <ButtonGroup variant="text" aria-label="outlined primary button group">
                        <IconButton aria-label="create task" size="large" onClick={editEmployee}>
                            <EditRoundedIcon fontSize="inherit" style={{ color: 'orange' }}   className="editEmployee" />
                        </IconButton>
                        <IconButton aria-label="create task" size="large" onClick={deleteEmployee}>
                            <DeleteRoundedIcon fontSize="inherit" style={{ color: 'red' }} className="deleteEmployee" />
                        </IconButton>
                    </ButtonGroup>
                </CardContent>
            </Card>
        </Grid>
    )
}
import { Button, Modal, TextField, Typography } from "@mui/material";
import { Box } from "@mui/system";
import axios from "axios";
import React from "react";
import Conf from '../../../conf/conf.json'

export default function EmployeeModal(props) {
    

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

    const handleEmployeeNameChange = (e) => {
        props.setEmployeeName(e.target.value);
    }

    const createEmployee = () => {
        let data = {
            name: props.employeeName,
            
        }
        console.log(data)

        axios.post(`${Conf.api.base}${Conf.api.endpoints.employees.createEmployee}`,data)
        .then(res => {
            switch(res.status) {
                case 201:
                   props.reloadHome();
                   props.handleCloseEmployeeModal();
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

    const updateEmployee = () => {
        let data = {
            name: props.employeeName,
            id: props.employeeId
            
        }
        console.log(data)

        axios.put(`${Conf.api.base}${Conf.api.endpoints.employees.updateEmployee}${data.id}`,data)
        .then(res => {
            switch(res.status) {
                case 200:
                    props.reloadHome();
                    props.handleCloseEmployeeModal();
                    alert(`Updated employee with id ${props.employeeId}.`)
                break;
                case 201:
                    props.reloadHome();
                    props.handleCloseEmployeeModal();
                    alert(`Created new emplyoee, because there was no employee with id ${props.employeeId}.`)
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
                open={props.openEmployeeModal}
                onClose={props.handleCloseEmployeeModal}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        {props.updateEmployee ? "Update employee" : "Create employee"}
                    </Typography>
                    <TextField id="standard-basic" label="Name" variant="outlined" value={props.employeeName} onChange={handleEmployeeNameChange} />
                    <Button variant="contained" onClick={props.updateEmployee ? updateEmployee : createEmployee}>Save</Button>
                </Box>
            </Modal>
    )
}
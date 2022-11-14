import { Grid, Typography } from "@mui/material";
import React from "react";

import './applicationTitle.scss'

export default function ApplicationTitle(props) {
    return(
        <Grid item xs={12} className="applicationTitle">
            <Typography variant="h3">{props.title}</Typography>
        </Grid>
    )
}
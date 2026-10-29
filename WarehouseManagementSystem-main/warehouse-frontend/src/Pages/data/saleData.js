import { useEffect, useState } from "react";
import { listSales } from "../services/salesService"; 

let labels = [];
const GetSales = () => {
    useEffect(() => {
    console.log("Getting sales...");
    listSales().then((res) => {
        console.log(res.data);
        labels = res.data;
    }).catch((err) => console.log(err))}, []);
}

export const SaleData = {
    datasets: [{
        axis: 'x',
        label: "Sale income",
        data: [23, 21]
    }]
}




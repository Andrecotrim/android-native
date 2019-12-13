const fs = require("fs");
const util = require('util');
/**
  ServerVip 1.1
  Author: Andre Cotrim
 */
module.exports = {

    /* 
    GET GenerateToken
    POST SendMoney
    GET GetTransfers
    */
    generateToken (req, res) {
        console.log ("GTk:", req.body, req.query);
        return res.json("1d40d305-c836-43a2-b4db-acc56bcc1393");
    },
    
    sendMoney (req, res) {
        console.log ("SMY:")
        return res.json("true");
    },
    
    getTransfers (req, res) {
        console.log ("GTFS:")
        // var d = new Date();
        // var nameFile = `/COLETAS_${d.getUTCFullYear()}${("0" + (d.getMonth() + 1 )).slice(-2)}${("0" + d.getDate()).slice(-2)}.txt`;
        return res.json([{"Id": 0,"ClienteId": 10,"Valor": 24,"Token": "1d40d305-c836-43a2-b4db-acc56bcc1393", "Data": "2016-08-02T14:25:37.55"}]);        
    }
}
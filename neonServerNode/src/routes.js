const express = require("express");

const routes = express.Router();

const NeonController = require('./controllers/NeonController');

//routes:
routes.get('/', (req, res) => { return res.send('Server ON!'); });
routes.post('/GenerateToken', NeonController.generateToken); 
routes.post('/SendMoney', NeonController.sendMoney); 
routes.get('/GetTransfers', NeonController.getTransfers);  

module.exports = routes;
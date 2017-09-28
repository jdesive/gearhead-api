# GearHead
Backend REST API to store data for the GearHead frontend.

## Endpoints 

### Car Controller
* .../cars/all - GET - List all cars (Pagination)
* .../cars/id/{id} - GET - Get car by id
* .../cars/vin/{vin} - GET - Get car by vin
* .../cars - POST - Add a car
* .../cars/{id}/maintenance - POST - Add a maintenance record to the car
* .../cars/{id}/maintenance/all - GET - List all maintenance records by car id (Pagination)

### MaintenanceRecord Controller
* .../maintenance/all - GET - List all maintenance records (Pagination)
* .../maintenance/{date} - GET - List all maintenance records on a specific date (Pagination)

## Entity Models

### Car
```json
{
  "make": "Toyota",
  "model": "Avalon",
  "color": "Black",
  "vin": 23456567435754,
  "miles": 167900,
  "oilType": "Mobile 1 5w30",
  "oilAmount": 6.5,
  "oilFilterType": "",
  "tirePressure": 35,
  "maintenanceRecords": []
}
```
### MaintenanceRecord
```json
{
  "timestamp": "MM-dd-yyyy",
  "oldValue": "",
  "newValue": "",
  "fieldname": "",
  "notes": ""
}
```
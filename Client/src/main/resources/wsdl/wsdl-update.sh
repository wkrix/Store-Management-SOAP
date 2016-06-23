#!/bin/bash

#server
WS_HOST=http://localhost:8080

#WSDLs
PRODUCTS_WSDL=$WS_HOST/ws/products?wsdl
STOCKMOVEMENTS_WSDL=$WS_HOST/ws/stockmovements?wsdl
WAREHOUSES_WSDL=$WS_HOST/ws/warehouses?wsdl


echo -e "\n--- downloading WSDLs ---\n"

wget -O - $PRODUCTS_WSDL > "products.wsdl"
wget -O - $STOCKMOVEMENTS_WSDL > "stockmovements.wsdl"
wget -O - $WAREHOUSES_WSDL > "warehouses.wsdl"

echo -e "\n--- READY ---\n"

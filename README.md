# blueLamp
blog origine medium link https://medium.com/mindorks/bluetooth-low-energy-3656ac323c4e

Bluetooth low energy is one of many wireless technologies in our world it is used in almost every project in IoT and smart home we can find it anywhere (beacons, smart watch,heart rate sensor ..) but before talking about how we can use it in our projects with raspberry we need to know some theory about it so lets start.

# what is BLE ?

ble started as part of bluetooth 4.0 core specification, it is originally designed by nokia as wibree before being adopted by Bluetooth Special Interes Group .

# specification of BLE:

a complete single mode BLE device is divided into three parts Application Host and Controller

## Application:

is the highest layer and it is responsible for containing the logic

## Host

includes the flowing layer :

    Generic Access Profile (GAP)
    Generic Attribute Profile (GATT)
    Logic Link Control and Adaptation Protocol (L2CAP)
    Attribute Protocol (ATT)
    Security Manager (SM)
    Hos Control Interface (HCI),host side

## Controller :

includes:

    Hos Control Interface (HCI),controller side
    Link Layer (LL)
    Physical Layer(PHY)

the different roles that BLE device can take are :

# Advertiser (Broadcaster):

a device sending advertising information (name , MAC address)

# Scanner:

a device scanning for advertising information.

# Central:

a device that initiates a connection and manges later.

# peripheral :

a device that accept a connexion request and follows a master timing.

these roles can be grouped into tow pairs advertiser and scanner and central and peripheral

![image4](https://user-images.githubusercontent.com/38364385/45872832-e893c380-bd88-11e8-8b2f-49811b5bc144.jpeg)

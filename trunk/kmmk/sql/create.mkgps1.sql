/*
 * ER/Studio 8.0 SQL Code Generation
 * Company :      fff
 * Project :      GPS.DM1
 * Author :       ttt
 *
 * Date Created : Thursday, April 01, 2010 23:17:55
 * Target DBMS : Microsoft SQL Server 2005
 */

USE master
go
CREATE DATABASE mkgps1
go
USE mkgps1
go
/* 
 * TABLE: f_runingLog 
 */

CREATE TABLE f_runingLog(
    ID                    bigint              IDENTITY(1,1),
    ticketNo              varchar(30)         NULL,
    startDate             datetime            NULL,
    endDate               datetime            NULL,
    driver                varchar(20)         NULL,
    escorterID            varchar(20)         NULL,
    goodsName             varchar(30)         NULL,
    shipPrice             double precision    NULL,
    loadWeight            double precision    NULL,
    unloadWeight          double precision    NULL,
    startDisRecord        double precision    NULL,
    endDisRecord          double precision    NULL,
    loadSite              varchar(100)        NULL,
    unloadSite            varchar(100)        NULL,
    billTo                varchar(50)         NULL,
    totalCost             double precision    NULL,
    paymentMethod         varchar(20)         NULL,
    paymentReceiveDate    datetime            NULL,
    state                 smallint            NULL,
    comment               varchar(200)        NULL,
    vehicleID             int                 NULL,
    operator              varchar(20)         NULL,
    governmentRecordNo    varchar(50)         NULL,
    CONSTRAINT PK71 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('f_runingLog') IS NOT NULL
    PRINT '<<< CREATED TABLE f_runingLog >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_runingLog >>>'
go

/* 
 * TABLE: f_runingLog 
 */

ALTER TABLE f_runingLog ADD CONSTRAINT Refvehicle93 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go



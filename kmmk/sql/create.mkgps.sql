/*
 * ER/Studio 8.0 SQL Code Generation
 * Company :      fff
 * Project :      GPS.DM1
 * Author :       ttt
 *
 * Date Created : Monday, August 24, 2009 23:55:26
 * Target DBMS : Microsoft SQL Server 2005
 */

USE master
go
CREATE DATABASE mkgps
go
USE mkgps
go
/* 
 * TABLE: alertHistory 
 */

CREATE TABLE alertHistory(
    alertID        bigint          IDENTITY(1,1),
    tag            smallint        NULL,
    vehicleID      int             NULL,
    occurDate      datetime        NULL,
    acctime        datetime        NULL,
    accUser        int             NULL,
    description    varchar(100)    NULL,
    AlertTypeID    int             NULL,
    CONSTRAINT PK50 PRIMARY KEY NONCLUSTERED (alertID)
)
go



IF OBJECT_ID('alertHistory') IS NOT NULL
    PRINT '<<< CREATED TABLE alertHistory >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE alertHistory >>>'
go

/* 
 * TABLE: AlertTypeDic 
 */

CREATE TABLE AlertTypeDic(
    AlertTypeID      int             IDENTITY(1,1),
    AlertTypeName    varchar(50)     NULL,
    description      varchar(200)    NULL,
    imagePath        varchar(200)    NULL,
    voicePath        varchar(200)    NULL,
    CONSTRAINT PK37 PRIMARY KEY NONCLUSTERED (AlertTypeID)
)
go



IF OBJECT_ID('AlertTypeDic') IS NOT NULL
    PRINT '<<< CREATED TABLE AlertTypeDic >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE AlertTypeDic >>>'
go

/* 
 * TABLE: driver 
 */

CREATE TABLE driver(
    driverID              int            IDENTITY(1,1),
    name                  varchar(50)    NOT NULL,
    drivingLicenceType    smallint       NULL,
    tel                   varchar(20)    NULL,
    annualCheckState      smallint       NULL,
    certificateNumber     varchar(50)    NULL,
    certificateDue        datetime       NULL,
    dangerousCertLevel    varchar(10)    NULL,
    dangerousCertDue      datetime       NULL,
    driverState           smallint       NULL,
    ownerID               int            NULL,
    CONSTRAINT PK14 PRIMARY KEY NONCLUSTERED (driverID)
)
go



IF OBJECT_ID('driver') IS NOT NULL
    PRINT '<<< CREATED TABLE driver >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE driver >>>'
go

/* 
 * TABLE: escorter 
 */

CREATE TABLE escorter(
    escorterID           int            IDENTITY(1,1),
    tel                  varchar(20)    NULL,
    name                 varchar(50)    NULL,
    certificateNumber    varchar(50)    NULL,
    certificateDue       datetime       NULL,
    escorterState        smallint       NULL,
    ownerID              int            NULL,
    CONSTRAINT PK15 PRIMARY KEY NONCLUSTERED (escorterID)
)
go



IF OBJECT_ID('escorter') IS NOT NULL
    PRINT '<<< CREATED TABLE escorter >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE escorter >>>'
go

/* 
 * TABLE: gpsDeviceInstallation 
 */

CREATE TABLE gpsDeviceInstallation(
    ID              int                 IDENTITY(1,1),
    vehicleID       int                 NULL,
    installState    smallint            NULL,
    installDate     datetime            NULL,
    deviceID        varchar(50)         NULL,
    deviceType      varchar(50)         NULL,
    commPotocol     varchar(50)         NULL,
    costs           double precision    NULL,
    CONSTRAINT PK42 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('gpsDeviceInstallation') IS NOT NULL
    PRINT '<<< CREATED TABLE gpsDeviceInstallation >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE gpsDeviceInstallation >>>'
go

/* 
 * TABLE: GPSFee 
 */

CREATE TABLE GPSFee(
    feeId          bigint              IDENTITY(1,1),
    vehicleID      int                 NULL,
    receiveDate    datetime            NULL,
    feeType        smallint            NULL,
    dueDate        datetime            NULL,
    memo           varchar(250)        NULL,
    operatorID     int                 NULL,
    money          double precision    NULL,
    CONSTRAINT PK53 PRIMARY KEY NONCLUSTERED (feeId)
)
go



IF OBJECT_ID('GPSFee') IS NOT NULL
    PRINT '<<< CREATED TABLE GPSFee >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE GPSFee >>>'
go

/* 
 * TABLE: hourlyTrack 
 */

CREATE TABLE hourlyTrack(
    ID             bigint              IDENTITY(1,1),
    vehicleID      int                 NOT NULL,
    recieveTime    datetime            NULL,
    latValue       double precision    NULL,
    longValue      double precision    NULL,
    tag            smallint            NULL,
    CONSTRAINT PK26 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('hourlyTrack') IS NOT NULL
    PRINT '<<< CREATED TABLE hourlyTrack >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE hourlyTrack >>>'
go

/* 
 * TABLE: hourlyTrack_HIS 
 */

CREATE TABLE hourlyTrack_HIS(
    ID             bigint              IDENTITY(1,1),
    vehicleID      int                 NOT NULL,
    recieveTime    datetime            NULL,
    latValue       double precision    NULL,
    longValue      double precision    NULL,
    tag            smallint            NULL,
    CONSTRAINT PK27 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('hourlyTrack_HIS') IS NOT NULL
    PRINT '<<< CREATED TABLE hourlyTrack_HIS >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE hourlyTrack_HIS >>>'
go

/* 
 * TABLE: organization 
 */

CREATE TABLE organization(
    organizationID       int             IDENTITY(1,1),
    organizationState    smallint        NULL,
    name                 varchar(50)     NULL,
    description          varchar(100)    NULL,
    creationDate         datetime        NULL,
    CONSTRAINT PK17 PRIMARY KEY NONCLUSTERED (organizationID)
)
go



IF OBJECT_ID('organization') IS NOT NULL
    PRINT '<<< CREATED TABLE organization >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE organization >>>'
go

/* 
 * TABLE: privateRules 
 */

CREATE TABLE privateRules(
    ruleID         int            IDENTITY(1,1),
    ruleName       varchar(30)    NULL,
    intParam1      int            NULL,
    timeParam      datetime       NULL,
    opType         smallint       NOT NULL,
    ruleType       smallint       NOT NULL,
    ruleState      smallint       NOT NULL,
    AlertTypeID    int            NULL,
    taskID         int            NULL,
    CONSTRAINT PK31_1 PRIMARY KEY NONCLUSTERED (ruleID)
)
go



IF OBJECT_ID('privateRules') IS NOT NULL
    PRINT '<<< CREATED TABLE privateRules >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE privateRules >>>'
go

/* 
 * TABLE: realtimeTrack 
 */

CREATE TABLE realtimeTrack(
    ID             bigint              IDENTITY(1,1),
    vehicleID      int                 NOT NULL,
    recieveTime    datetime            NULL,
    latValue       double precision    NULL,
    longValue      double precision    NULL,
    tag            smallint            NULL,
    CONSTRAINT PK7 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('realtimeTrack') IS NOT NULL
    PRINT '<<< CREATED TABLE realtimeTrack >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE realtimeTrack >>>'
go

/* 
 * TABLE: realtimeTrack_HIS 
 */

CREATE TABLE realtimeTrack_HIS(
    ID             bigint              IDENTITY(1,1),
    vehicleID      int                 NOT NULL,
    recieveTime    datetime            NULL,
    latValue       double precision    NULL,
    longValue      double precision    NULL,
    tag            smallint            NULL,
    CONSTRAINT PK23 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('realtimeTrack_HIS') IS NOT NULL
    PRINT '<<< CREATED TABLE realtimeTrack_HIS >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE realtimeTrack_HIS >>>'
go

/* 
 * TABLE: region 
 */

CREATE TABLE region(
    regionID        int                 IDENTITY(1,1),
    name            varchar(50)         NULL,
    description     varchar(100)        NULL,
    centralLong     double precision    NULL,
    centralLat      double precision    NULL,
    radius          float               NULL,
    edgeLong        double precision    NULL,
    edgeLat         double precision    NULL,
    figurType       smallint            NULL,
    regionTypeID    smallint            NULL,
    CONSTRAINT PK32 PRIMARY KEY NONCLUSTERED (regionID)
)
go



IF OBJECT_ID('region') IS NOT NULL
    PRINT '<<< CREATED TABLE region >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE region >>>'
go

/* 
 * TABLE: regionTypeDic 
 */

CREATE TABLE regionTypeDic(
    regionTypeID      smallint        IDENTITY(1,1),
    regionTypeName    varchar(100)    NULL,
    description       varchar(200)    NULL,
    stateTag          smallint        NULL,
    CONSTRAINT PK35 PRIMARY KEY NONCLUSTERED (regionTypeID)
)
go



IF OBJECT_ID('regionTypeDic') IS NOT NULL
    PRINT '<<< CREATED TABLE regionTypeDic >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE regionTypeDic >>>'
go

/* 
 * TABLE: role 
 */

CREATE TABLE role(
    roleName       varchar(50)     NULL,
    roleID         int             IDENTITY(1,1),
    description    varchar(100)    NULL,
    CONSTRAINT PK19 PRIMARY KEY NONCLUSTERED (roleID)
)
go



IF OBJECT_ID('role') IS NOT NULL
    PRINT '<<< CREATED TABLE role >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE role >>>'
go

/* 
 * TABLE: rules 
 */

CREATE TABLE rules(
    ruleID         int            IDENTITY(1,1),
    ruleName       varchar(30)    NULL,
    intParam1      int            NULL,
    timeParam      datetime       NULL,
    opType         smallint       NOT NULL,
    ruleType       smallint       NOT NULL,
    ruleState      smallint       NOT NULL,
    AlertTypeID    int            NULL,
    CONSTRAINT PK31 PRIMARY KEY NONCLUSTERED (ruleID)
)
go



IF OBJECT_ID('rules') IS NOT NULL
    PRINT '<<< CREATED TABLE rules >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE rules >>>'
go

/* 
 * TABLE: Segment 
 */

CREATE TABLE Segment(
    segmentID        int                 IDENTITY(1,1),
    segName          varchar(50)         NULL,
    segType          smallint            NULL,
    createTime       datetime            NULL,
    state            smallint            NULL,
    description      varchar(200)        NULL,
    startDetialID    bigint              NULL,
    endDetailID      bigint              NULL,
    speedLimit       double precision    NULL,
    CONSTRAINT PK44 PRIMARY KEY NONCLUSTERED (segmentID)
)
go



IF OBJECT_ID('Segment') IS NOT NULL
    PRINT '<<< CREATED TABLE Segment >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE Segment >>>'
go

/* 
 * TABLE: SegmentDetail 
 */

CREATE TABLE SegmentDetail(
    segDetailID    bigint              IDENTITY(1,1),
    longValue      double precision    NULL,
    latValue       double precision    NULL,
    tag            tinyint             NULL,
    segmentID      int                 NOT NULL,
    CONSTRAINT PK45 PRIMARY KEY NONCLUSTERED (segDetailID)
)
go



IF OBJECT_ID('SegmentDetail') IS NOT NULL
    PRINT '<<< CREATED TABLE SegmentDetail >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE SegmentDetail >>>'
go

/* 
 * TABLE: segmentRules 
 */

CREATE TABLE segmentRules(
    segRuleID        bigint      IDENTITY(1,1),
    taskSegID        int         NULL,
    privateRuleID    int         NULL,
    pubRuleID        int         NULL,
    stateTag         smallint    NULL,
    CONSTRAINT PK54 PRIMARY KEY NONCLUSTERED (segRuleID)
)
go



IF OBJECT_ID('segmentRules') IS NOT NULL
    PRINT '<<< CREATED TABLE segmentRules >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE segmentRules >>>'
go

/* 
 * TABLE: stateHelper 
 */

CREATE TABLE stateHelper(
    lastMessage        datetime    NULL,
    lastUpdate         datetime    NULL,
    lastUpdateType     tinyint     NULL,
    lastStopTime       datetime    NULL,
    startRuningTime    datetime    NULL,
    vehicleID          int         NOT NULL,
    CONSTRAINT PK2 PRIMARY KEY NONCLUSTERED (vehicleID)
)
go



IF OBJECT_ID('stateHelper') IS NOT NULL
    PRINT '<<< CREATED TABLE stateHelper >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE stateHelper >>>'
go

/* 
 * TABLE: task 
 */

CREATE TABLE task(
    taskID               int                 IDENTITY(1,1),
    taskName             varchar(50)         NULL,
    vehicleID            int                 NOT NULL,
    planedStartDate      datetime            NULL,
    planedEndDate        datetime            NULL,
    actualStartTime      datetime            NULL,
    actualFinishTime     datetime            NULL,
    taskDescription      varchar(500)        NULL,
    startPositionLong    double precision    NULL,
    startPositionLat     double precision    NULL,
    endPositionLong      double precision    NULL,
    endPositionLat       double precision    NULL,
    comments             varchar(200)        NULL,
    taskState            smallint            NULL,
    startPositionName    varchar(50)         NULL,
    endPositionName      varchar(50)         NULL,
    assignerID           int                 NULL,
    assignDate           datetime            NULL,
    lastModify           datetime            NULL,
    configTag            smallint            NULL,
    CONSTRAINT PK22 PRIMARY KEY NONCLUSTERED (taskID)
)
go



IF OBJECT_ID('task') IS NOT NULL
    PRINT '<<< CREATED TABLE task >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE task >>>'
go

/* 
 * TABLE: taskDriver 
 */

CREATE TABLE taskDriver(
    ID          int    IDENTITY(1,1),
    driverID    int    NOT NULL,
    taskID      int    NULL,
    CONSTRAINT PK6 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('taskDriver') IS NOT NULL
    PRINT '<<< CREATED TABLE taskDriver >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE taskDriver >>>'
go

/* 
 * TABLE: taskEscorter 
 */

CREATE TABLE taskEscorter(
    ID            int    IDENTITY(1,1),
    taskID        int    NULL,
    escorterID    int    NULL,
    CONSTRAINT PK33 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('taskEscorter') IS NOT NULL
    PRINT '<<< CREATED TABLE taskEscorter >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE taskEscorter >>>'
go

/* 
 * TABLE: taskRealtimeTrack 
 */

CREATE TABLE taskRealtimeTrack(
    ID             bigint              IDENTITY(1,1),
    vehicleID      int                 NOT NULL,
    recieveTime    datetime            NULL,
    latValue       double precision    NULL,
    longValue      double precision    NULL,
    tag            smallint            NULL,
    CONSTRAINT PK24 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('taskRealtimeTrack') IS NOT NULL
    PRINT '<<< CREATED TABLE taskRealtimeTrack >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE taskRealtimeTrack >>>'
go

/* 
 * TABLE: taskRealtimeTrack_HIS 
 */

CREATE TABLE taskRealtimeTrack_HIS(
    ID             bigint              IDENTITY(1,1),
    vehicleID      int                 NOT NULL,
    recieveTime    datetime            NULL,
    latValue       double precision    NULL,
    longValue      double precision    NULL,
    tag            smallint            NULL,
    CONSTRAINT PK25 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('taskRealtimeTrack_HIS') IS NOT NULL
    PRINT '<<< CREATED TABLE taskRealtimeTrack_HIS >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE taskRealtimeTrack_HIS >>>'
go

/* 
 * TABLE: taskRule 
 */

CREATE TABLE taskRule(
    ID        int    IDENTITY(1,1),
    taskID    int    NOT NULL,
    ruleID    int    NOT NULL,
    CONSTRAINT PK30 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('taskRule') IS NOT NULL
    PRINT '<<< CREATED TABLE taskRule >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE taskRule >>>'
go

/* 
 * TABLE: taskSegment 
 */

CREATE TABLE taskSegment(
    taskSegID     int                 IDENTITY(1,1),
    taskID        int                 NOT NULL,
    segmentID     int                 NOT NULL,
    orderNo       int                 NULL,
    speedLimit    double precision    NULL,
    CONSTRAINT PK48 PRIMARY KEY NONCLUSTERED (taskSegID)
)
go



IF OBJECT_ID('taskSegment') IS NOT NULL
    PRINT '<<< CREATED TABLE taskSegment >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE taskSegment >>>'
go

/* 
 * TABLE: tenMinTrack 
 */

CREATE TABLE tenMinTrack(
    ID             bigint              IDENTITY(1,1),
    vehicleID      int                 NULL,
    recieveTime    datetime            NULL,
    latValue       double precision    NULL,
    longValue      double precision    NULL,
    tag            smallint            NULL,
    CONSTRAINT PK28 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('tenMinTrack') IS NOT NULL
    PRINT '<<< CREATED TABLE tenMinTrack >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE tenMinTrack >>>'
go

/* 
 * TABLE: tenMinTrack_HIS 
 */

CREATE TABLE tenMinTrack_HIS(
    ID             bigint              IDENTITY(1,1),
    vehicleID      int                 NOT NULL,
    recieveTime    datetime            NULL,
    latValue       double precision    NULL,
    longValue      double precision    NULL,
    tag            smallint            NULL,
    CONSTRAINT PK29 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('tenMinTrack_HIS') IS NOT NULL
    PRINT '<<< CREATED TABLE tenMinTrack_HIS >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE tenMinTrack_HIS >>>'
go

/* 
 * TABLE: userRole 
 */

CREATE TABLE userRole(
    ID        int    IDENTITY(1,1),
    userID    int    NOT NULL,
    roleID    int    NOT NULL,
    CONSTRAINT PK20 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('userRole') IS NOT NULL
    PRINT '<<< CREATED TABLE userRole >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE userRole >>>'
go

/* 
 * TABLE: users 
 */

CREATE TABLE users(
    userID            int             IDENTITY(1,1),
    loginName         varchar(20)     NULL,
    passwd            varchar(30)     NULL,
    realName          varchar(50)     NULL,
    description       varchar(100)    NULL,
    registerDate      datetime        NULL,
    lastLoginDate     datetime        NULL,
    lastLoginIP       varchar(64)     NULL,
    userState         smallint        NULL,
    organizationID    int             NULL,
    CONSTRAINT PK16 PRIMARY KEY NONCLUSTERED (userID)
)
go



IF OBJECT_ID('users') IS NOT NULL
    PRINT '<<< CREATED TABLE users >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE users >>>'
go

/* 
 * TABLE: vehicle 
 */

CREATE TABLE vehicle(
    vehicleID             int             IDENTITY(1,1),
    deviceID              varchar(30)     NULL,
    licensPadNumber       varchar(10)     NULL,
    internalNumber        varchar(30)     NULL,
    engineNumber          varchar(100)    NULL,
    frameNumber           varchar(100)    NULL,
    modelNumber           varchar(100)    NULL,
    capability            float           NULL,
    registerDate          datetime        NULL,
    approvalDate          datetime        NULL,
    annualCheckState      smallint        NULL,
    secondMaintainDate    datetime        NULL,
    assetBaseValue        int             NULL,
    userID                int             NULL,
    simCardNo             varchar(20)     NULL,
    vehicleState          smallint        NULL,
    vehicleTypeID         smallint        NULL,
    serviceExpireDate     datetime        NULL,
    CONSTRAINT PK3 PRIMARY KEY NONCLUSTERED (vehicleID)
)
go



IF OBJECT_ID('vehicle') IS NOT NULL
    PRINT '<<< CREATED TABLE vehicle >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE vehicle >>>'
go

/* 
 * TABLE: vehicleStatus 
 */

CREATE TABLE vehicleStatus(
    currentLong        double precision    NULL,
    currentLat         double precision    NULL,
    licensPadNumber    varchar(10)         NULL,
    isRunning          tinyint             NULL,
    isOnline           tinyint             NULL,
    isAskHelp          tinyint             NULL,
    limitAreaAlarm     tinyint             NULL,
    overSpeed          tinyint             NULL,
    taskID             int                 NULL,
    tireDrive          tinyint             NULL,
    currentSpeed       double precision    NULL,
    vehicleID          int                 NOT NULL,
    CONSTRAINT PK1 PRIMARY KEY NONCLUSTERED (vehicleID)
)
go



IF OBJECT_ID('vehicleStatus') IS NOT NULL
    PRINT '<<< CREATED TABLE vehicleStatus >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE vehicleStatus >>>'
go

/* 
 * TABLE: vehicleTypeDic 
 */

CREATE TABLE vehicleTypeDic(
    vehicleTypeID      smallint        IDENTITY(1,1),
    vehicleTypeName    varchar(50)     NULL,
    description        varchar(200)    NULL,
    stateTag           smallint        NULL,
    CONSTRAINT PK36 PRIMARY KEY NONCLUSTERED (vehicleTypeID)
)
go



IF OBJECT_ID('vehicleTypeDic') IS NOT NULL
    PRINT '<<< CREATED TABLE vehicleTypeDic >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE vehicleTypeDic >>>'
go

/* 
 * INDEX: PK 
 */

CREATE UNIQUE INDEX PK ON driver(driverID)
go
IF EXISTS (SELECT * FROM sys.indexes WHERE object_id=OBJECT_ID('driver') AND name='PK')
    PRINT '<<< CREATED INDEX driver.PK >>>'
ELSE
    PRINT '<<< FAILED CREATING INDEX driver.PK >>>'
go

/* 
 * INDEX: PK 
 */

CREATE UNIQUE INDEX PK ON escorter(escorterID)
go
IF EXISTS (SELECT * FROM sys.indexes WHERE object_id=OBJECT_ID('escorter') AND name='PK')
    PRINT '<<< CREATED INDEX escorter.PK >>>'
ELSE
    PRINT '<<< FAILED CREATING INDEX escorter.PK >>>'
go

/* 
 * INDEX: PK 
 */

CREATE UNIQUE INDEX PK ON organization(organizationID)
go
IF EXISTS (SELECT * FROM sys.indexes WHERE object_id=OBJECT_ID('organization') AND name='PK')
    PRINT '<<< CREATED INDEX organization.PK >>>'
ELSE
    PRINT '<<< FAILED CREATING INDEX organization.PK >>>'
go

/* 
 * INDEX: PK 
 */

CREATE UNIQUE INDEX PK ON role(roleID)
go
IF EXISTS (SELECT * FROM sys.indexes WHERE object_id=OBJECT_ID('role') AND name='PK')
    PRINT '<<< CREATED INDEX role.PK >>>'
ELSE
    PRINT '<<< FAILED CREATING INDEX role.PK >>>'
go

/* 
 * INDEX: PK 
 */

CREATE UNIQUE INDEX PK ON rules(ruleID)
go
IF EXISTS (SELECT * FROM sys.indexes WHERE object_id=OBJECT_ID('rules') AND name='PK')
    PRINT '<<< CREATED INDEX rules.PK >>>'
ELSE
    PRINT '<<< FAILED CREATING INDEX rules.PK >>>'
go

/* 
 * INDEX: PK 
 */

CREATE UNIQUE INDEX PK ON task(taskID)
go
IF EXISTS (SELECT * FROM sys.indexes WHERE object_id=OBJECT_ID('task') AND name='PK')
    PRINT '<<< CREATED INDEX task.PK >>>'
ELSE
    PRINT '<<< FAILED CREATING INDEX task.PK >>>'
go

/* 
 * INDEX: PK 
 */

CREATE UNIQUE INDEX PK ON users(userID)
go
IF EXISTS (SELECT * FROM sys.indexes WHERE object_id=OBJECT_ID('users') AND name='PK')
    PRINT '<<< CREATED INDEX users.PK >>>'
ELSE
    PRINT '<<< FAILED CREATING INDEX users.PK >>>'
go

/* 
 * INDEX: PK 
 */

CREATE UNIQUE INDEX PK ON vehicle(vehicleID)
go
IF EXISTS (SELECT * FROM sys.indexes WHERE object_id=OBJECT_ID('vehicle') AND name='PK')
    PRINT '<<< CREATED INDEX vehicle.PK >>>'
ELSE
    PRINT '<<< FAILED CREATING INDEX vehicle.PK >>>'
go

/* 
 * TABLE: alertHistory 
 */

ALTER TABLE alertHistory ADD CONSTRAINT RefAlertTypeDic64 
    FOREIGN KEY (AlertTypeID)
    REFERENCES AlertTypeDic(AlertTypeID)
go


/* 
 * TABLE: driver 
 */

ALTER TABLE driver ADD CONSTRAINT Refusers67 
    FOREIGN KEY (ownerID)
    REFERENCES users(userID)
go


/* 
 * TABLE: escorter 
 */

ALTER TABLE escorter ADD CONSTRAINT Refusers68 
    FOREIGN KEY (ownerID)
    REFERENCES users(userID)
go


/* 
 * TABLE: GPSFee 
 */

ALTER TABLE GPSFee ADD CONSTRAINT Refvehicle65 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go

ALTER TABLE GPSFee ADD CONSTRAINT Refusers66 
    FOREIGN KEY (operatorID)
    REFERENCES users(userID)
go


/* 
 * TABLE: privateRules 
 */

ALTER TABLE privateRules ADD CONSTRAINT RefAlertTypeDic61 
    FOREIGN KEY (AlertTypeID)
    REFERENCES AlertTypeDic(AlertTypeID)
go

ALTER TABLE privateRules ADD CONSTRAINT Reftask63 
    FOREIGN KEY (taskID)
    REFERENCES task(taskID)
go


/* 
 * TABLE: region 
 */

ALTER TABLE region ADD CONSTRAINT RefregionTypeDic57 
    FOREIGN KEY (regionTypeID)
    REFERENCES regionTypeDic(regionTypeID)
go


/* 
 * TABLE: rules 
 */

ALTER TABLE rules ADD CONSTRAINT RefAlertTypeDic58 
    FOREIGN KEY (AlertTypeID)
    REFERENCES AlertTypeDic(AlertTypeID)
go


/* 
 * TABLE: SegmentDetail 
 */

ALTER TABLE SegmentDetail ADD CONSTRAINT RefSegment51 
    FOREIGN KEY (segmentID)
    REFERENCES Segment(segmentID)
go


/* 
 * TABLE: segmentRules 
 */

ALTER TABLE segmentRules ADD CONSTRAINT ReftaskSegment70 
    FOREIGN KEY (taskSegID)
    REFERENCES taskSegment(taskSegID)
go

ALTER TABLE segmentRules ADD CONSTRAINT RefprivateRules71 
    FOREIGN KEY (privateRuleID)
    REFERENCES privateRules(ruleID)
go

ALTER TABLE segmentRules ADD CONSTRAINT Refrules73 
    FOREIGN KEY (pubRuleID)
    REFERENCES rules(ruleID)
go


/* 
 * TABLE: stateHelper 
 */

ALTER TABLE stateHelper ADD CONSTRAINT Refvehicle36 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: task 
 */

ALTER TABLE task ADD CONSTRAINT Refusers60 
    FOREIGN KEY (assignerID)
    REFERENCES users(userID)
go

ALTER TABLE task ADD CONSTRAINT Refvehicle27 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: taskDriver 
 */

ALTER TABLE taskDriver ADD CONSTRAINT Refdriver38 
    FOREIGN KEY (driverID)
    REFERENCES driver(driverID)
go

ALTER TABLE taskDriver ADD CONSTRAINT Reftask42 
    FOREIGN KEY (taskID)
    REFERENCES task(taskID)
go


/* 
 * TABLE: taskEscorter 
 */

ALTER TABLE taskEscorter ADD CONSTRAINT Reftask43 
    FOREIGN KEY (taskID)
    REFERENCES task(taskID)
go

ALTER TABLE taskEscorter ADD CONSTRAINT Refescorter44 
    FOREIGN KEY (escorterID)
    REFERENCES escorter(escorterID)
go


/* 
 * TABLE: taskRule 
 */

ALTER TABLE taskRule ADD CONSTRAINT Reftask29 
    FOREIGN KEY (taskID)
    REFERENCES task(taskID)
go

ALTER TABLE taskRule ADD CONSTRAINT Refrules30 
    FOREIGN KEY (ruleID)
    REFERENCES rules(ruleID)
go


/* 
 * TABLE: taskSegment 
 */

ALTER TABLE taskSegment ADD CONSTRAINT Reftask52 
    FOREIGN KEY (taskID)
    REFERENCES task(taskID)
go

ALTER TABLE taskSegment ADD CONSTRAINT RefSegment53 
    FOREIGN KEY (segmentID)
    REFERENCES Segment(segmentID)
go


/* 
 * TABLE: userRole 
 */

ALTER TABLE userRole ADD CONSTRAINT Refusers23 
    FOREIGN KEY (userID)
    REFERENCES users(userID)
go

ALTER TABLE userRole ADD CONSTRAINT Refrole24 
    FOREIGN KEY (roleID)
    REFERENCES role(roleID)
go


/* 
 * TABLE: users 
 */

ALTER TABLE users ADD CONSTRAINT Reforganization69 
    FOREIGN KEY (organizationID)
    REFERENCES organization(organizationID)
go


/* 
 * TABLE: vehicle 
 */

ALTER TABLE vehicle ADD CONSTRAINT Refusers41 
    FOREIGN KEY (userID)
    REFERENCES users(userID)
go

ALTER TABLE vehicle ADD CONSTRAINT RefvehicleTypeDic56 
    FOREIGN KEY (vehicleTypeID)
    REFERENCES vehicleTypeDic(vehicleTypeID)
go


/* 
 * TABLE: vehicleStatus 
 */

ALTER TABLE vehicleStatus ADD CONSTRAINT Refvehicle35 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go



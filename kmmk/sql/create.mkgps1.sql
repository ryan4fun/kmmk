/*
 * ER/Studio 8.0 SQL Code Generation
 * Company :      fff
 * Project :      GPS.DM1
 * Author :       ttt
 *
 * Date Created : Tuesday, July 27, 2010 23:00:27
 * Target DBMS : Microsoft SQL Server 2005
 */

USE master
go
CREATE DATABASE mkgps1
go
USE mkgps1
go
/* 
 * TABLE: alertHistory 
 */

CREATE TABLE alertHistory(
    alertID        bigint              IDENTITY(1,1),
    tag            smallint            NULL,
    vehicleID      int                 NULL,
    occurDate      datetime            NULL,
    acctime        datetime            NULL,
    accUser        int                 NULL,
    description    varchar(100)        NULL,
    accComment     varchar(200)        NULL,
    AlertTypeID    int                 NULL,
    longVal        double precision    NULL,
    latVal         double precision    NULL,
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
 * TABLE: f_expense_log 
 */

CREATE TABLE f_expense_log(
    id           bigint              IDENTITY(1,1),
    category1    char(10)            NULL,
    category2    char(10)            NULL,
    type         smallint            NULL,
    amount       double precision    NULL,
    yearMonth    varchar(10)         NULL,
    comment1     varchar(100)        NULL,
    comment2     varchar(200)        NULL,
    vehicleID    int                 NULL,
    CONSTRAINT PK76 PRIMARY KEY NONCLUSTERED (id)
)
go



IF OBJECT_ID('f_expense_log') IS NOT NULL
    PRINT '<<< CREATED TABLE f_expense_log >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_expense_log >>>'
go

/* 
 * TABLE: f_gasfee 
 */

CREATE TABLE f_gasfee(
    ID             bigint              IDENTITY(1,1),
    occurDate      datetime            NULL,
    deposit        double precision    NULL,
    refill         double precision    NULL,
    refillMoney    double precision    NULL,
    balance        double precision    NULL,
    comment        varchar(100)        NULL,
    vehicleID      int                 NULL,
    operator       varchar(20)         NULL,
    feeType        char(10)            NULL,
    yearMonth      char(10)            NULL,
    CONSTRAINT PK72 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('f_gasfee') IS NOT NULL
    PRINT '<<< CREATED TABLE f_gasfee >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_gasfee >>>'
go

/* 
 * TABLE: f_maintain 
 */

CREATE TABLE f_maintain(
    ID              bigint              IDENTITY(1,1),
    maintainDate    datetime            NULL,
    category        varchar(50)         NULL,
    subCategory     varchar(50)         NULL,
    cost            double precision    NULL,
    quantity        int                 NULL,
    handler         varchar(20)         NULL,
    vehicleID       int                 NULL,
    comment         varchar(200)        NULL,
    studio          varchar(100)        NULL,
    operator        varchar(20)         NULL,
    feeType         smallint            NULL,
    yearMonth       char(10)            NULL,
    CONSTRAINT PK70 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('f_maintain') IS NOT NULL
    PRINT '<<< CREATED TABLE f_maintain >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_maintain >>>'
go

/* 
 * TABLE: f_material_keep_log 
 */

CREATE TABLE f_material_keep_log(
    ID            bigint          IDENTITY(1,1),
    materialId    bigint          NULL,
    keeper        varchar(25)     NULL,
    occurDate     datetime        NULL,
    comment       varchar(200)    NULL,
    operator      varchar(20)     NULL,
    CONSTRAINT PK66 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('f_material_keep_log') IS NOT NULL
    PRINT '<<< CREATED TABLE f_material_keep_log >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_material_keep_log >>>'
go

/* 
 * TABLE: f_monthly_report 
 */

CREATE TABLE f_monthly_report(
    id           int                 IDENTITY(1,1),
    yearMonth    varchar(10)         NULL,
    income       double precision    NULL,
    costs        double precision    NULL,
    note1        varchar(200)        NULL,
    note2        varchar(200)        NULL,
    note3        varchar(200)        NULL,
    note4        varchar(200)        NULL,
    note5        varchar(200)        NULL,
    vehicleID    int                 NULL,
    CONSTRAINT PK77 PRIMARY KEY NONCLUSTERED (id)
)
go



IF OBJECT_ID('f_monthly_report') IS NOT NULL
    PRINT '<<< CREATED TABLE f_monthly_report >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_monthly_report >>>'
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
    planedDistance        double precision    NULL,
    actualDistance        double precision    NULL,
    planedGas             double precision    NULL,
    actualGas             double precision    NULL,
    GasByCash             double precision    NULL,
    GasByCard             double precision    NULL,
    gasByCashCost         double precision    NULL,
    gasByCardCost         double precision    NULL,
    planedRoadFee         double precision    NULL,
    actualRoadFee         double precision    NULL,
    managementFee         double precision    NULL,
    overLimitFee          double precision    NULL,
    yearMonth             varchar(10)         NULL,
    CONSTRAINT PK71 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('f_runingLog') IS NOT NULL
    PRINT '<<< CREATED TABLE f_runingLog >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_runingLog >>>'
go

/* 
 * TABLE: f_tools 
 */

CREATE TABLE f_tools(
    toolId            bigint          IDENTITY(1,1),
    toolName          varchar(30)     NULL,
    lastKeeper        varchar(30)     NULL,
    lastChangeDate    datetime        NULL,
    vehicleID         int             NULL,
    state             smallint        NULL,
    comment           varchar(200)    NULL,
    CONSTRAINT PK68 PRIMARY KEY NONCLUSTERED (toolId)
)
go



IF OBJECT_ID('f_tools') IS NOT NULL
    PRINT '<<< CREATED TABLE f_tools >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_tools >>>'
go

/* 
 * TABLE: f_tools_keep_log 
 */

CREATE TABLE f_tools_keep_log(
    ID           bigint          IDENTITY(1,1),
    keeper       varchar(20)     NULL,
    occurDate    datetime        NULL,
    toolId       bigint          NULL,
    comment      varchar(200)    NULL,
    operator     varchar(20)     NULL,
    CONSTRAINT PK67 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('f_tools_keep_log') IS NOT NULL
    PRINT '<<< CREATED TABLE f_tools_keep_log >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_tools_keep_log >>>'
go

/* 
 * TABLE: f_tyres 
 */

CREATE TABLE f_tyres(
    tyreId                bigint              IDENTITY(1,1),
    tyreNo                varchar(30)         NULL,
    tyreName              varchar(30)         NULL,
    vehicleID             int                 NULL,
    state                 smallint            NULL,
    comment               varchar(200)        NULL,
    installDate           datetime            NULL,
    installDistanceRec    double precision    NULL,
    usedPeriod            double precision    NULL,
    disposeDate           datetime            NULL,
    disposeDistanceRec    double precision    NULL,
    usedDistance          double precision    NULL,
    price                 double precision    NULL,
    operator              varchar(20)         NULL,
    yearMonth             char(10)            NULL,
    CONSTRAINT PK68_1 PRIMARY KEY NONCLUSTERED (tyreId)
)
go



IF OBJECT_ID('f_tyres') IS NOT NULL
    PRINT '<<< CREATED TABLE f_tyres >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_tyres >>>'
go

/* 
 * TABLE: f_user 
 */

CREATE TABLE f_user(
    userID            int             IDENTITY(1,1),
    loginName         varchar(20)     NULL,
    passwd            varchar(30)     NULL,
    realName          varchar(50)     NULL,
    description       varchar(100)    NULL,
    registerDate      datetime        NULL,
    lastLoginDate     datetime        NULL,
    lastLoginIP       varchar(64)     NULL,
    userState         smallint        NULL,
    tel               varchar(20)     NULL,
    organizationID    int             NULL,
    CONSTRAINT PK16_1 PRIMARY KEY NONCLUSTERED (userID)
)
go



IF OBJECT_ID('f_user') IS NOT NULL
    PRINT '<<< CREATED TABLE f_user >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_user >>>'
go

/* 
 * TABLE: f_vehicle_basic 
 */

CREATE TABLE f_vehicle_basic(
    ID               bigint              IDENTITY(1,1),
    vehicleID        int                 NULL,
    feeName          varchar(25)         NULL,
    feeState         smallint            NULL,
    feeExpireDate    datetime            NULL,
    amount           double precision    NULL,
    comment          varchar(100)        NULL,
    CONSTRAINT PK64 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('f_vehicle_basic') IS NOT NULL
    PRINT '<<< CREATED TABLE f_vehicle_basic >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_vehicle_basic >>>'
go

/* 
 * TABLE: f_vehicle_material 
 */

CREATE TABLE f_vehicle_material(
    materialId        bigint          IDENTITY(1,1),
    name              varchar(50)     NULL,
    lastKeeper        varchar(25)     NULL,
    lastChangeDate    datetime        NULL,
    vehicleID         int             NULL,
    state             smallint        NULL,
    comment           varchar(200)    NULL,
    CONSTRAINT PK65 PRIMARY KEY NONCLUSTERED (materialId)
)
go



IF OBJECT_ID('f_vehicle_material') IS NOT NULL
    PRINT '<<< CREATED TABLE f_vehicle_material >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE f_vehicle_material >>>'
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
    speed          double precision    NULL,
    direction      smallint            NULL,
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
    speed          double precision    NULL,
    direction      smallint            NULL,
    CONSTRAINT PK27 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('hourlyTrack_HIS') IS NOT NULL
    PRINT '<<< CREATED TABLE hourlyTrack_HIS >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE hourlyTrack_HIS >>>'
go

/* 
 * TABLE: malfunctionDeviceLog 
 */

CREATE TABLE malfunctionDeviceLog(
    entryID       bigint          IDENTITY(1,1),
    occurDate     datetime        NULL,
    errorType     smallint        NULL,
    comment       varchar(100)    NULL,
    msgDetail     varchar(200)    NULL,
    entryState    smallint        NULL,
    vehicleID     int             NULL,
    CONSTRAINT PK55 PRIMARY KEY NONCLUSTERED (entryID)
)
go



IF OBJECT_ID('malfunctionDeviceLog') IS NOT NULL
    PRINT '<<< CREATED TABLE malfunctionDeviceLog >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE malfunctionDeviceLog >>>'
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
 * TABLE: pictureStore 
 */

CREATE TABLE pictureStore(
    storageId         int             NOT NULL,
    picData           image           NULL,
    comment           varchar(100)    NULL,
    lastModifyDate    datetime        NULL,
    lastModifyBy      int             NULL,
    CONSTRAINT PK61 PRIMARY KEY NONCLUSTERED (storageId)
)
go



IF OBJECT_ID('pictureStore') IS NOT NULL
    PRINT '<<< CREATED TABLE pictureStore >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE pictureStore >>>'
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
 * TABLE: qualifiedCoordArea 
 */

CREATE TABLE qualifiedCoordArea(
    ID               int                 NOT NULL,
    northEastLong    double precision    NULL,
    northEastLat     double precision    NULL,
    southWestLong    double precision    NULL,
    southWestLat     double precision    NULL,
    CONSTRAINT PK56 PRIMARY KEY NONCLUSTERED (ID)
)
go



IF OBJECT_ID('qualifiedCoordArea') IS NOT NULL
    PRINT '<<< CREATED TABLE qualifiedCoordArea >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE qualifiedCoordArea >>>'
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
    speed          double precision    NULL,
    direction      smallint            NULL,
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
    speed          double precision    NULL,
    direction      smallint            NULL,
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
    regionID          int                 IDENTITY(1,1),
    name              varchar(50)         NULL,
    description       varchar(100)        NULL,
    centralLong       double precision    NULL,
    centralLat        double precision    NULL,
    radius            float               NULL,
    edgeLong          double precision    NULL,
    edgeLat           double precision    NULL,
    figurType         smallint            NULL,
    regionTypeID      smallint            NULL,
    organizationID    int                 NULL,
    CONSTRAINT PK32 PRIMARY KEY NONCLUSTERED (regionID)
)
go



IF OBJECT_ID('region') IS NOT NULL
    PRINT '<<< CREATED TABLE region >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE region >>>'
go

/* 
 * TABLE: regionPoints 
 */

CREATE TABLE regionPoints(
    pointId     int                 IDENTITY(1,1),
    longVal     double precision    NULL,
    latVal      double precision    NULL,
    regionID    int                 NULL,
    CONSTRAINT PK73 PRIMARY KEY NONCLUSTERED (pointId)
)
go



IF OBJECT_ID('regionPoints') IS NOT NULL
    PRINT '<<< CREATED TABLE regionPoints >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE regionPoints >>>'
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
    segmentID         int                 IDENTITY(1,1),
    segName           varchar(50)         NULL,
    segType           smallint            NULL,
    createTime        datetime            NULL,
    state             smallint            NULL,
    description       varchar(200)        NULL,
    startDetialID     bigint              NULL,
    endDetailID       bigint              NULL,
    speedLimit        double precision    NULL,
    organizationID    int                 NULL,
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
 * TABLE: SystemSettings 
 */

CREATE TABLE SystemSettings(
    settingId      int                 IDENTITY(1,1),
    name           varchar(20)         NULL,
    description    varchar(100)        NULL,
    value          double precision    NULL,
    CONSTRAINT PK62 PRIMARY KEY NONCLUSTERED (settingId)
)
go



IF OBJECT_ID('SystemSettings') IS NOT NULL
    PRINT '<<< CREATED TABLE SystemSettings >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE SystemSettings >>>'
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
    speed          double precision    NULL,
    direction      smallint            NULL,
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
    speed          double precision    NULL,
    direction      char(10)            NULL,
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
    speed          double precision    NULL,
    direction      smallint            NULL,
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
    speed          double precision    NULL,
    direction      smallint            NULL,
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
    tel               varchar(20)     NULL,
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
    msgIntervel           smallint        NULL,
    monitLevel            smallint        NULL,
    speedLimitation       int             NULL,
    imgPath1              varchar(100)    NULL,
    imgPath2              varchar(100)    NULL,
    imgPath3              varchar(100)    NULL,
    CONSTRAINT PK3 PRIMARY KEY NONCLUSTERED (vehicleID)
)
go



IF OBJECT_ID('vehicle') IS NOT NULL
    PRINT '<<< CREATED TABLE vehicle >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE vehicle >>>'
go

/* 
 * TABLE: vehicleAssociations 
 */

CREATE TABLE vehicleAssociations(
    assoId       int         IDENTITY(1,1),
    roleType     smallint    NULL,
    targetId     int         NULL,
    vehicleID    int         NULL,
    CONSTRAINT PK57 PRIMARY KEY NONCLUSTERED (assoId)
)
go



IF OBJECT_ID('vehicleAssociations') IS NOT NULL
    PRINT '<<< CREATED TABLE vehicleAssociations >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE vehicleAssociations >>>'
go

/* 
 * TABLE: vehiclePic 
 */

CREATE TABLE vehiclePic(
    vpId                int    IDENTITY(1,1),
    vehiclePicTypeID    int    NULL,
    vehicleID           int    NULL,
    storageId           int    NULL,
    CONSTRAINT PK58 PRIMARY KEY NONCLUSTERED (vpId)
)
go



IF OBJECT_ID('vehiclePic') IS NOT NULL
    PRINT '<<< CREATED TABLE vehiclePic >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE vehiclePic >>>'
go

/* 
 * TABLE: vehiclePicTypeDic 
 */

CREATE TABLE vehiclePicTypeDic(
    vehiclePicTypeID      int             NOT NULL,
    vehiclePicTypeName    varchar(25)     NULL,
    description           varchar(100)    NULL,
    stateTag              smallint        NULL,
    CONSTRAINT PK60 PRIMARY KEY NONCLUSTERED (vehiclePicTypeID)
)
go



IF OBJECT_ID('vehiclePicTypeDic') IS NOT NULL
    PRINT '<<< CREATED TABLE vehiclePicTypeDic >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE vehiclePicTypeDic >>>'
go

/* 
 * TABLE: vehicleRule 
 */

CREATE TABLE vehicleRule(
    id           int         IDENTITY(1,1),
    tag          smallint    NULL,
    ruleID       int         NULL,
    vehicleID    int         NULL,
    CONSTRAINT PK74 PRIMARY KEY NONCLUSTERED (id)
)
go



IF OBJECT_ID('vehicleRule') IS NOT NULL
    PRINT '<<< CREATED TABLE vehicleRule >>>'
ELSE
    PRINT '<<< FAILED CREATING TABLE vehicleRule >>>'
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
    speedLimitation    int             NULL,
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

ALTER TABLE alertHistory ADD CONSTRAINT Refvehicle102 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go

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
 * TABLE: f_expense_log 
 */

ALTER TABLE f_expense_log ADD CONSTRAINT Refvehicle107 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: f_gasfee 
 */

ALTER TABLE f_gasfee ADD CONSTRAINT Refvehicle94 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: f_maintain 
 */

ALTER TABLE f_maintain ADD CONSTRAINT Refvehicle90 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: f_material_keep_log 
 */

ALTER TABLE f_material_keep_log ADD CONSTRAINT Reff_vehicle_material84 
    FOREIGN KEY (materialId)
    REFERENCES f_vehicle_material(materialId)
go


/* 
 * TABLE: f_monthly_report 
 */

ALTER TABLE f_monthly_report ADD CONSTRAINT Refvehicle108 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: f_runingLog 
 */

ALTER TABLE f_runingLog ADD CONSTRAINT Refvehicle93 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: f_tools 
 */

ALTER TABLE f_tools ADD CONSTRAINT Refvehicle88 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: f_tools_keep_log 
 */

ALTER TABLE f_tools_keep_log ADD CONSTRAINT Reff_tools86 
    FOREIGN KEY (toolId)
    REFERENCES f_tools(toolId)
go


/* 
 * TABLE: f_tyres 
 */

ALTER TABLE f_tyres ADD CONSTRAINT Refvehicle89 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: f_user 
 */

ALTER TABLE f_user ADD CONSTRAINT Reforganization106 
    FOREIGN KEY (organizationID)
    REFERENCES organization(organizationID)
go


/* 
 * TABLE: f_vehicle_basic 
 */

ALTER TABLE f_vehicle_basic ADD CONSTRAINT Refvehicle81 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: f_vehicle_material 
 */

ALTER TABLE f_vehicle_material ADD CONSTRAINT Refvehicle82 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
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
 * TABLE: malfunctionDeviceLog 
 */

ALTER TABLE malfunctionDeviceLog ADD CONSTRAINT Refvehicle74 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
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

ALTER TABLE region ADD CONSTRAINT Reforganization101 
    FOREIGN KEY (organizationID)
    REFERENCES organization(organizationID)
go

ALTER TABLE region ADD CONSTRAINT RefregionTypeDic57 
    FOREIGN KEY (regionTypeID)
    REFERENCES regionTypeDic(regionTypeID)
go


/* 
 * TABLE: regionPoints 
 */

ALTER TABLE regionPoints ADD CONSTRAINT Refregion95 
    FOREIGN KEY (regionID)
    REFERENCES region(regionID)
go


/* 
 * TABLE: rules 
 */

ALTER TABLE rules ADD CONSTRAINT RefAlertTypeDic58 
    FOREIGN KEY (AlertTypeID)
    REFERENCES AlertTypeDic(AlertTypeID)
go


/* 
 * TABLE: Segment 
 */

ALTER TABLE Segment ADD CONSTRAINT Reforganization103 
    FOREIGN KEY (organizationID)
    REFERENCES organization(organizationID)
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

ALTER TABLE task ADD CONSTRAINT Refvehicle27 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go

ALTER TABLE task ADD CONSTRAINT Refusers60 
    FOREIGN KEY (assignerID)
    REFERENCES users(userID)
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
 * TABLE: vehicleAssociations 
 */

ALTER TABLE vehicleAssociations ADD CONSTRAINT Refvehicle75 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: vehiclePic 
 */

ALTER TABLE vehiclePic ADD CONSTRAINT RefvehiclePicTypeDic76 
    FOREIGN KEY (vehiclePicTypeID)
    REFERENCES vehiclePicTypeDic(vehiclePicTypeID)
go

ALTER TABLE vehiclePic ADD CONSTRAINT Refvehicle77 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go

ALTER TABLE vehiclePic ADD CONSTRAINT RefpictureStore78 
    FOREIGN KEY (storageId)
    REFERENCES pictureStore(storageId)
go


/* 
 * TABLE: vehicleRule 
 */

ALTER TABLE vehicleRule ADD CONSTRAINT Refrules97 
    FOREIGN KEY (ruleID)
    REFERENCES rules(ruleID)
go

ALTER TABLE vehicleRule ADD CONSTRAINT Refvehicle98 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go


/* 
 * TABLE: vehicleStatus 
 */

ALTER TABLE vehicleStatus ADD CONSTRAINT Refvehicle35 
    FOREIGN KEY (vehicleID)
    REFERENCES vehicle(vehicleID)
go



USE [mkgps1]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[RefAlertTypeDic64]') AND parent_object_id = OBJECT_ID(N'[dbo].[alertHistory]'))
ALTER TABLE [dbo].[alertHistory] DROP CONSTRAINT [RefAlertTypeDic64]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle102]') AND parent_object_id = OBJECT_ID(N'[dbo].[alertHistory]'))
ALTER TABLE [dbo].[alertHistory] DROP CONSTRAINT [Refvehicle102]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refusers67]') AND parent_object_id = OBJECT_ID(N'[dbo].[driver]'))
ALTER TABLE [dbo].[driver] DROP CONSTRAINT [Refusers67]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refusers68]') AND parent_object_id = OBJECT_ID(N'[dbo].[escorter]'))
ALTER TABLE [dbo].[escorter] DROP CONSTRAINT [Refusers68]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle107]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_expense_log]'))
ALTER TABLE [dbo].[f_expense_log] DROP CONSTRAINT [Refvehicle107]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle94]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_gasfee]'))
ALTER TABLE [dbo].[f_gasfee] DROP CONSTRAINT [Refvehicle94]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle90]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_maintain]'))
ALTER TABLE [dbo].[f_maintain] DROP CONSTRAINT [Refvehicle90]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reff_vehicle_material84]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_material_keep_log]'))
ALTER TABLE [dbo].[f_material_keep_log] DROP CONSTRAINT [Reff_vehicle_material84]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle108]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_monthly_report]'))
ALTER TABLE [dbo].[f_monthly_report] DROP CONSTRAINT [Refvehicle108]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle93]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_runingLog]'))
ALTER TABLE [dbo].[f_runingLog] DROP CONSTRAINT [Refvehicle93]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle88]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_tools]'))
ALTER TABLE [dbo].[f_tools] DROP CONSTRAINT [Refvehicle88]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reff_tools86]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_tools_keep_log]'))
ALTER TABLE [dbo].[f_tools_keep_log] DROP CONSTRAINT [Reff_tools86]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle89]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_tyres]'))
ALTER TABLE [dbo].[f_tyres] DROP CONSTRAINT [Refvehicle89]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reforganization106]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_user]'))
ALTER TABLE [dbo].[f_user] DROP CONSTRAINT [Reforganization106]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle81]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_vehicle_basic]'))
ALTER TABLE [dbo].[f_vehicle_basic] DROP CONSTRAINT [Refvehicle81]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle82]') AND parent_object_id = OBJECT_ID(N'[dbo].[f_vehicle_material]'))
ALTER TABLE [dbo].[f_vehicle_material] DROP CONSTRAINT [Refvehicle82]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refusers66]') AND parent_object_id = OBJECT_ID(N'[dbo].[GPSFee]'))
ALTER TABLE [dbo].[GPSFee] DROP CONSTRAINT [Refusers66]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle65]') AND parent_object_id = OBJECT_ID(N'[dbo].[GPSFee]'))
ALTER TABLE [dbo].[GPSFee] DROP CONSTRAINT [Refvehicle65]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle74]') AND parent_object_id = OBJECT_ID(N'[dbo].[malfunctionDeviceLog]'))
ALTER TABLE [dbo].[malfunctionDeviceLog] DROP CONSTRAINT [Refvehicle74]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[RefAlertTypeDic61]') AND parent_object_id = OBJECT_ID(N'[dbo].[privateRules]'))
ALTER TABLE [dbo].[privateRules] DROP CONSTRAINT [RefAlertTypeDic61]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reftask63]') AND parent_object_id = OBJECT_ID(N'[dbo].[privateRules]'))
ALTER TABLE [dbo].[privateRules] DROP CONSTRAINT [Reftask63]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reforganization101]') AND parent_object_id = OBJECT_ID(N'[dbo].[region]'))
ALTER TABLE [dbo].[region] DROP CONSTRAINT [Reforganization101]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[RefregionTypeDic57]') AND parent_object_id = OBJECT_ID(N'[dbo].[region]'))
ALTER TABLE [dbo].[region] DROP CONSTRAINT [RefregionTypeDic57]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refregion95]') AND parent_object_id = OBJECT_ID(N'[dbo].[regionPoints]'))
ALTER TABLE [dbo].[regionPoints] DROP CONSTRAINT [Refregion95]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[RefAlertTypeDic58]') AND parent_object_id = OBJECT_ID(N'[dbo].[rules]'))
ALTER TABLE [dbo].[rules] DROP CONSTRAINT [RefAlertTypeDic58]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reforganization103]') AND parent_object_id = OBJECT_ID(N'[dbo].[Segment]'))
ALTER TABLE [dbo].[Segment] DROP CONSTRAINT [Reforganization103]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[RefSegment51]') AND parent_object_id = OBJECT_ID(N'[dbo].[SegmentDetail]'))
ALTER TABLE [dbo].[SegmentDetail] DROP CONSTRAINT [RefSegment51]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[RefprivateRules71]') AND parent_object_id = OBJECT_ID(N'[dbo].[segmentRules]'))
ALTER TABLE [dbo].[segmentRules] DROP CONSTRAINT [RefprivateRules71]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refrules73]') AND parent_object_id = OBJECT_ID(N'[dbo].[segmentRules]'))
ALTER TABLE [dbo].[segmentRules] DROP CONSTRAINT [Refrules73]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[ReftaskSegment70]') AND parent_object_id = OBJECT_ID(N'[dbo].[segmentRules]'))
ALTER TABLE [dbo].[segmentRules] DROP CONSTRAINT [ReftaskSegment70]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle36]') AND parent_object_id = OBJECT_ID(N'[dbo].[stateHelper]'))
ALTER TABLE [dbo].[stateHelper] DROP CONSTRAINT [Refvehicle36]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refusers60]') AND parent_object_id = OBJECT_ID(N'[dbo].[task]'))
ALTER TABLE [dbo].[task] DROP CONSTRAINT [Refusers60]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle27]') AND parent_object_id = OBJECT_ID(N'[dbo].[task]'))
ALTER TABLE [dbo].[task] DROP CONSTRAINT [Refvehicle27]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refdriver38]') AND parent_object_id = OBJECT_ID(N'[dbo].[taskDriver]'))
ALTER TABLE [dbo].[taskDriver] DROP CONSTRAINT [Refdriver38]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reftask42]') AND parent_object_id = OBJECT_ID(N'[dbo].[taskDriver]'))
ALTER TABLE [dbo].[taskDriver] DROP CONSTRAINT [Reftask42]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refescorter44]') AND parent_object_id = OBJECT_ID(N'[dbo].[taskEscorter]'))
ALTER TABLE [dbo].[taskEscorter] DROP CONSTRAINT [Refescorter44]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reftask43]') AND parent_object_id = OBJECT_ID(N'[dbo].[taskEscorter]'))
ALTER TABLE [dbo].[taskEscorter] DROP CONSTRAINT [Reftask43]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refrules30]') AND parent_object_id = OBJECT_ID(N'[dbo].[taskRule]'))
ALTER TABLE [dbo].[taskRule] DROP CONSTRAINT [Refrules30]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reftask29]') AND parent_object_id = OBJECT_ID(N'[dbo].[taskRule]'))
ALTER TABLE [dbo].[taskRule] DROP CONSTRAINT [Reftask29]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[RefSegment53]') AND parent_object_id = OBJECT_ID(N'[dbo].[taskSegment]'))
ALTER TABLE [dbo].[taskSegment] DROP CONSTRAINT [RefSegment53]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reftask52]') AND parent_object_id = OBJECT_ID(N'[dbo].[taskSegment]'))
ALTER TABLE [dbo].[taskSegment] DROP CONSTRAINT [Reftask52]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refrole24]') AND parent_object_id = OBJECT_ID(N'[dbo].[userRole]'))
ALTER TABLE [dbo].[userRole] DROP CONSTRAINT [Refrole24]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refusers23]') AND parent_object_id = OBJECT_ID(N'[dbo].[userRole]'))
ALTER TABLE [dbo].[userRole] DROP CONSTRAINT [Refusers23]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Reforganization69]') AND parent_object_id = OBJECT_ID(N'[dbo].[users]'))
ALTER TABLE [dbo].[users] DROP CONSTRAINT [Reforganization69]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refusers41]') AND parent_object_id = OBJECT_ID(N'[dbo].[vehicle]'))
ALTER TABLE [dbo].[vehicle] DROP CONSTRAINT [Refusers41]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[RefvehicleTypeDic56]') AND parent_object_id = OBJECT_ID(N'[dbo].[vehicle]'))
ALTER TABLE [dbo].[vehicle] DROP CONSTRAINT [RefvehicleTypeDic56]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle75]') AND parent_object_id = OBJECT_ID(N'[dbo].[vehicleAssociations]'))
ALTER TABLE [dbo].[vehicleAssociations] DROP CONSTRAINT [Refvehicle75]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[RefpictureStore78]') AND parent_object_id = OBJECT_ID(N'[dbo].[vehiclePic]'))
ALTER TABLE [dbo].[vehiclePic] DROP CONSTRAINT [RefpictureStore78]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle77]') AND parent_object_id = OBJECT_ID(N'[dbo].[vehiclePic]'))
ALTER TABLE [dbo].[vehiclePic] DROP CONSTRAINT [Refvehicle77]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[RefvehiclePicTypeDic76]') AND parent_object_id = OBJECT_ID(N'[dbo].[vehiclePic]'))
ALTER TABLE [dbo].[vehiclePic] DROP CONSTRAINT [RefvehiclePicTypeDic76]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refrules97]') AND parent_object_id = OBJECT_ID(N'[dbo].[vehicleRule]'))
ALTER TABLE [dbo].[vehicleRule] DROP CONSTRAINT [Refrules97]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle98]') AND parent_object_id = OBJECT_ID(N'[dbo].[vehicleRule]'))
ALTER TABLE [dbo].[vehicleRule] DROP CONSTRAINT [Refvehicle98]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[Refvehicle35]') AND parent_object_id = OBJECT_ID(N'[dbo].[vehicleStatus]'))
ALTER TABLE [dbo].[vehicleStatus] DROP CONSTRAINT [Refvehicle35]
GO
USE [mkgps1]
GO
/****** 对象:  Table [dbo].[alertHistory]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[alertHistory]') AND type in (N'U'))
DROP TABLE [dbo].[alertHistory]
GO
/****** 对象:  Table [dbo].[AlertTypeDic]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[AlertTypeDic]') AND type in (N'U'))
DROP TABLE [dbo].[AlertTypeDic]
GO
/****** 对象:  Table [dbo].[driver]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[driver]') AND type in (N'U'))
DROP TABLE [dbo].[driver]
GO
/****** 对象:  Table [dbo].[escorter]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[escorter]') AND type in (N'U'))
DROP TABLE [dbo].[escorter]
GO
/****** 对象:  Table [dbo].[f_expense_log]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_expense_log]') AND type in (N'U'))
DROP TABLE [dbo].[f_expense_log]
GO
/****** 对象:  Table [dbo].[f_gasfee]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_gasfee]') AND type in (N'U'))
DROP TABLE [dbo].[f_gasfee]
GO
/****** 对象:  Table [dbo].[f_maintain]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_maintain]') AND type in (N'U'))
DROP TABLE [dbo].[f_maintain]
GO
/****** 对象:  Table [dbo].[f_material_keep_log]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_material_keep_log]') AND type in (N'U'))
DROP TABLE [dbo].[f_material_keep_log]
GO
/****** 对象:  Table [dbo].[f_monthly_report]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_monthly_report]') AND type in (N'U'))
DROP TABLE [dbo].[f_monthly_report]
GO
/****** 对象:  Table [dbo].[f_runingLog]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_runingLog]') AND type in (N'U'))
DROP TABLE [dbo].[f_runingLog]
GO
/****** 对象:  Table [dbo].[f_tools]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_tools]') AND type in (N'U'))
DROP TABLE [dbo].[f_tools]
GO
/****** 对象:  Table [dbo].[f_tools_keep_log]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_tools_keep_log]') AND type in (N'U'))
DROP TABLE [dbo].[f_tools_keep_log]
GO
/****** 对象:  Table [dbo].[f_tyres]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_tyres]') AND type in (N'U'))
DROP TABLE [dbo].[f_tyres]
GO
/****** 对象:  Table [dbo].[f_user]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_user]') AND type in (N'U'))
DROP TABLE [dbo].[f_user]
GO
/****** 对象:  Table [dbo].[f_vehicle_basic]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_vehicle_basic]') AND type in (N'U'))
DROP TABLE [dbo].[f_vehicle_basic]
GO
/****** 对象:  Table [dbo].[f_vehicle_material]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[f_vehicle_material]') AND type in (N'U'))
DROP TABLE [dbo].[f_vehicle_material]
GO
/****** 对象:  Table [dbo].[gpsDeviceInstallation]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[gpsDeviceInstallation]') AND type in (N'U'))
DROP TABLE [dbo].[gpsDeviceInstallation]
GO
/****** 对象:  Table [dbo].[GPSFee]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GPSFee]') AND type in (N'U'))
DROP TABLE [dbo].[GPSFee]
GO
/****** 对象:  Table [dbo].[hourlyTrack]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[hourlyTrack]') AND type in (N'U'))
DROP TABLE [dbo].[hourlyTrack]
GO
/****** 对象:  Table [dbo].[hourlyTrack_HIS]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[hourlyTrack_HIS]') AND type in (N'U'))
DROP TABLE [dbo].[hourlyTrack_HIS]
GO
/****** 对象:  Table [dbo].[malfunctionDeviceLog]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[malfunctionDeviceLog]') AND type in (N'U'))
DROP TABLE [dbo].[malfunctionDeviceLog]
GO
/****** 对象:  Table [dbo].[organization]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[organization]') AND type in (N'U'))
DROP TABLE [dbo].[organization]
GO
/****** 对象:  Table [dbo].[pictureStore]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pictureStore]') AND type in (N'U'))
DROP TABLE [dbo].[pictureStore]
GO
/****** 对象:  Table [dbo].[privateRules]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[privateRules]') AND type in (N'U'))
DROP TABLE [dbo].[privateRules]
GO
/****** 对象:  Table [dbo].[qualifiedCoordArea]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[qualifiedCoordArea]') AND type in (N'U'))
DROP TABLE [dbo].[qualifiedCoordArea]
GO
/****** 对象:  Table [dbo].[realtimeTrack]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[realtimeTrack]') AND type in (N'U'))
DROP TABLE [dbo].[realtimeTrack]
GO
/****** 对象:  Table [dbo].[realtimeTrack_HIS]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[realtimeTrack_HIS]') AND type in (N'U'))
DROP TABLE [dbo].[realtimeTrack_HIS]
GO
/****** 对象:  Table [dbo].[region]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[region]') AND type in (N'U'))
DROP TABLE [dbo].[region]
GO
/****** 对象:  Table [dbo].[regionPoints]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[regionPoints]') AND type in (N'U'))
DROP TABLE [dbo].[regionPoints]
GO
/****** 对象:  Table [dbo].[regionTypeDic]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[regionTypeDic]') AND type in (N'U'))
DROP TABLE [dbo].[regionTypeDic]
GO
/****** 对象:  Table [dbo].[role]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[role]') AND type in (N'U'))
DROP TABLE [dbo].[role]
GO
/****** 对象:  Table [dbo].[rules]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[rules]') AND type in (N'U'))
DROP TABLE [dbo].[rules]
GO
/****** 对象:  Table [dbo].[Segment]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Segment]') AND type in (N'U'))
DROP TABLE [dbo].[Segment]
GO
/****** 对象:  Table [dbo].[SegmentDetail]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SegmentDetail]') AND type in (N'U'))
DROP TABLE [dbo].[SegmentDetail]
GO
/****** 对象:  Table [dbo].[segmentRules]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[segmentRules]') AND type in (N'U'))
DROP TABLE [dbo].[segmentRules]
GO
/****** 对象:  Table [dbo].[stateHelper]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[stateHelper]') AND type in (N'U'))
DROP TABLE [dbo].[stateHelper]
GO
/****** 对象:  Table [dbo].[SystemSettings]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SystemSettings]') AND type in (N'U'))
DROP TABLE [dbo].[SystemSettings]
GO
/****** 对象:  Table [dbo].[task]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[task]') AND type in (N'U'))
DROP TABLE [dbo].[task]
GO
/****** 对象:  Table [dbo].[taskDriver]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[taskDriver]') AND type in (N'U'))
DROP TABLE [dbo].[taskDriver]
GO
/****** 对象:  Table [dbo].[taskEscorter]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[taskEscorter]') AND type in (N'U'))
DROP TABLE [dbo].[taskEscorter]
GO
/****** 对象:  Table [dbo].[taskRealtimeTrack]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[taskRealtimeTrack]') AND type in (N'U'))
DROP TABLE [dbo].[taskRealtimeTrack]
GO
/****** 对象:  Table [dbo].[taskRealtimeTrack_HIS]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[taskRealtimeTrack_HIS]') AND type in (N'U'))
DROP TABLE [dbo].[taskRealtimeTrack_HIS]
GO
/****** 对象:  Table [dbo].[taskRule]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[taskRule]') AND type in (N'U'))
DROP TABLE [dbo].[taskRule]
GO
/****** 对象:  Table [dbo].[taskSegment]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[taskSegment]') AND type in (N'U'))
DROP TABLE [dbo].[taskSegment]
GO
/****** 对象:  Table [dbo].[tenMinTrack]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tenMinTrack]') AND type in (N'U'))
DROP TABLE [dbo].[tenMinTrack]
GO
/****** 对象:  Table [dbo].[tenMinTrack_HIS]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tenMinTrack_HIS]') AND type in (N'U'))
DROP TABLE [dbo].[tenMinTrack_HIS]
GO
/****** 对象:  Table [dbo].[userRole]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[userRole]') AND type in (N'U'))
DROP TABLE [dbo].[userRole]
GO
/****** 对象:  Table [dbo].[users]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[users]') AND type in (N'U'))
DROP TABLE [dbo].[users]
GO
/****** 对象:  Table [dbo].[vehicle]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[vehicle]') AND type in (N'U'))
DROP TABLE [dbo].[vehicle]
GO
/****** 对象:  Table [dbo].[vehicleAssociations]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[vehicleAssociations]') AND type in (N'U'))
DROP TABLE [dbo].[vehicleAssociations]
GO
/****** 对象:  Table [dbo].[vehiclePic]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[vehiclePic]') AND type in (N'U'))
DROP TABLE [dbo].[vehiclePic]
GO
/****** 对象:  Table [dbo].[vehiclePicTypeDic]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[vehiclePicTypeDic]') AND type in (N'U'))
DROP TABLE [dbo].[vehiclePicTypeDic]
GO
/****** 对象:  Table [dbo].[vehicleRule]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[vehicleRule]') AND type in (N'U'))
DROP TABLE [dbo].[vehicleRule]
GO
/****** 对象:  Table [dbo].[vehicleStatus]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[vehicleStatus]') AND type in (N'U'))
DROP TABLE [dbo].[vehicleStatus]
GO
/****** 对象:  Table [dbo].[vehicleTypeDic]    脚本日期: 04/11/2010 20:04:35 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[vehicleTypeDic]') AND type in (N'U'))
DROP TABLE [dbo].[vehicleTypeDic]
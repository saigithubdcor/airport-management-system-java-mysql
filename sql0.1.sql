-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema AMS
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AMS
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS AMS DEFAULT CHARACTER SET utf8 ;
USE AMS ;

-- -----------------------------------------------------
-- Table AMS.Airline
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Airline (
  Airline_ID INT NOT NULL,
  Airline_Name VARCHAR(45) NOT NULL,
  PRIMARY KEY (Airline_ID))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Gate
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Gate (
  Gate_ID INT NOT NULL,
  Gate_Number INT NULL,
  PRIMARY KEY (Gate_ID))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Air_Traffic_Control_System
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Air_Traffic_Control_System (
  ATC_ID INT NOT NULL,
  Communication_Systems VARCHAR(45) NOT NULL,
  Weather_Integration VARCHAR(45) NOT NULL,
  Emergency_Alert_System VARCHAR(45) NOT NULL,
  Flight_ID INT NOT NULL,
  PRIMARY KEY (ATC_ID))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Flight_Employees
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Flight_Employees (
  Flight_Employee_ID INT NOT NULL,
  Flight_Employee_Name VARCHAR(45) NULL,
  Position VARCHAR(45) NULL,
  PRIMARY KEY (Flight_Employee_ID))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Flight
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Flight (
  Flight_ID INT NOT NULL,
  Flight_Number INT NOT NULL,
  Equipment_ID VARCHAR(45) NOT NULL,
  Arrival_time VARCHAR(45) NOT NULL,
  Departure_time VARCHAR(45) NOT NULL,
  Airline_ID INT NOT NULL,
  Gate_ID INT NOT NULL,
  -- Flight_Employee_ID INT NOT NULL,
  Service_ID INT NOT NULL,
  ATC_ID INT NOT NULL,
  Flight_Employee_ID INT NOT NULL,
  PRIMARY KEY (Flight_ID, ATC_ID, Flight_Employee_ID),
  INDEX fk_Flight_Airline1_idx (Airline_ID ASC) VISIBLE,
  INDEX fk_Flight_Gate1_idx (Gate_ID ASC) VISIBLE,
  INDEX fk_Flight_Air_Traffic_Control_System1_idx (ATC_ID ASC) VISIBLE,
  INDEX fk_Flight_Flight_Employees1_idx (Flight_Employee_ID ASC) VISIBLE,
  CONSTRAINT fk_Flight_Airline1
    FOREIGN KEY (Airline_ID)
    REFERENCES AMS.Airline (Airline_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Flight_Gate1
    FOREIGN KEY (Gate_ID)
    REFERENCES AMS.Gate (Gate_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Flight_Air_Traffic_Control_System1
    FOREIGN KEY (ATC_ID)
    REFERENCES AMS.Air_Traffic_Control_System (ATC_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Flight_Flight_Employees1
    FOREIGN KEY (Flight_Employee_ID)
    REFERENCES AMS.Flight_Employees (Flight_Employee_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Passenger
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Passenger (
  Passenger_ID INT NOT NULL,
  Passenger_Name VARCHAR(45) NOT NULL,
  Ticket_Number INT NOT NULL,
  Flight_ID INT NOT NULL,
  PRIMARY KEY (Passenger_ID, Flight_ID),
  INDEX fk_Passenger_Flight1_idx (Flight_ID ASC) VISIBLE,
  CONSTRAINT fk_Passenger_Flight1
    FOREIGN KEY (Flight_ID)
    REFERENCES AMS.Flight (Flight_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Employee
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Employee (
  Employee_ID INT NOT NULL,
  Employee_Name VARCHAR(45) NULL,
  Position VARCHAR(45) NULL,
  Department VARCHAR(45) NULL,
  PRIMARY KEY (Employee_ID))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Baggage
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Baggage (
  Baggage_ID INT NOT NULL,
  Passenger_ID INT NULL,
  Flight_ID INT NULL,
  Baggage_Tag_Number INT NULL,
  PRIMARY KEY (Baggage_ID),
  INDEX fk_Baggage_Flight1_idx (Flight_ID ASC) VISIBLE,
  CONSTRAINT fk_Baggage_Flight1
    FOREIGN KEY (Flight_ID)
    REFERENCES AMS.Flight (Flight_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Revenue
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Revenue (
  Rev_ID INT NOT NULL,
  Revenue_Type VARCHAR(45) NULL,
  Rev_Collected INT NULL,
  PRIMARY KEY (Rev_ID))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Parking
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Parking (
  Vehicle_No INT NOT NULL,
  Entry_Time VARCHAR(45) NOT NULL,
  Exit_Time VARCHAR(45) NOT NULL,
  Rev_ID INT NOT NULL,
  Rev_Collected INT NOT NULL,
  PRIMARY KEY (Vehicle_No),
  INDEX fk_Parking_Revenue1_idx (Rev_ID ASC) VISIBLE,
  CONSTRAINT fk_Parking_Revenue1
    FOREIGN KEY (Rev_ID)
    REFERENCES AMS.Revenue (Rev_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Ground_Handling_Equipment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Ground_Handling_Equipment (
  Equipment_ID INT NOT NULL,
  Equipment_Type VARCHAR(45) NOT NULL,
  PRIMARY KEY (Equipment_ID))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Ground_Handling_Service:
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Ground_Handling_Service (
  Service_ID INT NOT NULL,
  Service_Type VARCHAR(45) NOT NULL,
  PRIMARY KEY (Service_ID))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Restricted_Area
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Restricted_Area (
  Area_ID INT NOT NULL,
  Area_Type VARCHAR(45) NULL,
  PRIMARY KEY (Area_ID))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Security_System
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Security_System (
  Security_ID INT NOT NULL,
  Security_Type VARCHAR(45) NOT NULL,
  Belt_Count VARCHAR(45) NOT NULL,
  Area_ID INT NOT NULL,
  PRIMARY KEY (Security_ID, Area_ID),
  INDEX fk_Security_System_Restricted_Area1_idx (Area_ID ASC) VISIBLE,
  CONSTRAINT fk_Security_System_Restricted_Area1
    FOREIGN KEY (Area_ID)
    REFERENCES AMS.Restricted_Area (Area_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Tickets
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Tickets (
  Ticket_ID INT NOT NULL,
  Passenger_ID INT NOT NULL,
  Flight_ID VARCHAR(45) NOT NULL,
  PRIMARY KEY (Ticket_ID),
  INDEX fk_Tickets_Passenger1_idx (Passenger_ID ASC) VISIBLE,
  CONSTRAINT fk_Tickets_Passenger1
    FOREIGN KEY (Passenger_ID)
    REFERENCES AMS.Passenger (Passenger_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Operates
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Operates (
  Flight_ID INT NOT NULL,
  Service_ID INT NOT NULL,
  PRIMARY KEY (Flight_ID, Service_ID),
  INDEX fk_Flight_has_Ground_Handling_Service_Ground_Handling_Serv_idx (Service_ID ASC) VISIBLE,
  INDEX fk_Flight_has_Ground_Handling_Service_Flight1_idx (Flight_ID ASC) VISIBLE,
  CONSTRAINT fk_Flight_has_Ground_Handling_Service_Flight1
    FOREIGN KEY (Flight_ID)
    REFERENCES AMS.Flight (Flight_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Flight_has_Ground_Handling_Service_Ground_Handling_Servic1
    FOREIGN KEY (Service_ID)
    REFERENCES AMS.Ground_Handling_Service (Service_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table AMS.Uses
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS AMS.Uses (
  Flight_ID INT NOT NULL,
  Equipment_ID INT NOT NULL,
  PRIMARY KEY (Flight_ID, Equipment_ID),
  INDEX fk_Flight_has_Ground_Handling_Equipment_Ground_Handling_Equ_idx (Equipment_ID ASC) VISIBLE,
  INDEX fk_Flight_has_Ground_Handling_Equipment_Flight1_idx (Flight_ID ASC) VISIBLE,
  CONSTRAINT fk_Flight_has_Ground_Handling_Equipment_Flight1
    FOREIGN KEY (Flight_ID)
    REFERENCES AMS.Flight (Flight_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Flight_has_Ground_Handling_Equipment_Ground_Handling_Equip1
    FOREIGN KEY (Equipment_ID)
    REFERENCES AMS.Ground_Handling_Equipment (Equipment_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
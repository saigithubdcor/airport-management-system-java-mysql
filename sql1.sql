-- Airline
INSERT INTO Airline (Airline_ID, Airline_Name) VALUES
(1, 'Delta Airlines'),
(2, 'United Airlines'),
(3, 'American Airlines');


-- Gate
INSERT INTO AMS.Gate (Gate_ID, Gate_Number) VALUES
(101, 1),
(102, 2),
(103, 3),
(104, 4),
(105, 5),
(106, 6),
(107, 7),
(108, 8),
(109, 9),
(110, 10);

-- Air_Traffic_Control_System
INSERT INTO AMS.Air_Traffic_Control_System (ATC_ID, Communication_Systems, Weather_Integration, Emergency_Alert_System, Flight_ID) VALUES
(1, 'VHF', 'Yes', 'Yes', 1001),
(2, 'RADAR', 'No', 'Yes', 1002),
(3, 'VOR', 'Yes', 'No', 1003),
(4, 'Radar', 'Yes', 'Yes', 1004),
(5, 'VOR', 'Yes', 'Yes', 1005),
(6, 'RADAR', 'Yes', 'No', 1006),
(7, 'VHF', 'Yes', 'Yes', 1007),
(8, 'RADAR', 'No', 'Yes', 1008),
(9, 'VOR', 'Yes', 'No', 1009),
(10, 'Radar', 'Yes', 'Yes', 1010);

INSERT INTO AMS.Flight_Employees (Flight_Employee_ID, Flight_Employee_Name, Position) VALUES
(1, 'John Smith', 'Pilot'),
(2, 'Emily Johnson', 'Flight Attendant'),
(3, 'Michael Brown', 'Ground Crew'),
(4, 'Laura Smith', 'Co-pilot'),
(5, 'David Johnson', 'Flight Attendant'),
(6, 'Jessica Wilson', 'Ground Crew'),
(7, 'Christopher Lee', 'Pilot'),
(8, 'Sophia Clark', 'Flight Attendant'),
(9, 'Andrew Miller', 'Ground Crew'),
(10, 'Olivia Taylor', 'Pilot');

-- Flight
INSERT INTO AMS.Flight (Flight_ID, Flight_Number, Equipment_ID, Departure_time, Arrival_time, Airline_ID, Gate_ID, Flight_Employee_ID, Service_ID, ATC_ID) VALUES
(1001, 123, 'E001', '10:00', '12:00', 1, 101, 1, 1, 1),
(1002, 456, 'E002', '12:00', '14:00', 2, 102, 2, 2, 2),
(1003, 789, 'E003', '14:00', '16:00', 3, 103, 3, 3, 3),
(1004, 101, 'E004', '16:00', '18:00', 3, 104, 4, 4, 4),
(1005, 102, 'E005', '18:00', '20:00', 1, 105, 5, 5, 5),
(1006, 103, 'E006', '20:00', '22:00', 2, 106, 6, 6, 6),
(1007, 104, 'E007', '22:00', '23:59', 1, 107, 7, 7, 7),
(1008, 105, 'E008', '10:00', '12:00', 3, 108, 8, 8, 8),
(1009, 106, 'E009', '12:00', '14:00', 2, 109, 9, 9, 9),
(1010, 107, 'E010', '14:00', '16:00', 1, 110, 10, 10, 10);

-- Passenger
INSERT INTO AMS.Passenger (Passenger_ID, Passenger_Name, Ticket_Number, Flight_ID) VALUES
(1, 'Alice Johnson', 123456, 1001),
(2, 'Bob Smith', 789012, 1002),
(3, 'Emily Brown', 345678, 1003),
(4, 'Daniel White', 901234, 1004),
(5, 'Sophia Miller', 567890, 1005),
(6, 'Ethan Wilson', 432109, 1006),
(7, 'Liam Thomas', 876543, 1007),
(8, 'Olivia Clark', 210987, 1008),
(9, 'Noah Taylor', 654321, 1009),
(10, 'Ava Lee', 987654, 1010);

-- Employee
INSERT INTO AMS.Employee (Employee_ID, Employee_Name, Position, Department) VALUES
(1, 'Jane Doe', 'Manager', 'Operations'),
(2, 'David Lee', 'Supervisor', 'Security'),
(3, 'Chris Evans', 'Technician', 'Maintenance'),
(4, 'Rebecca Taylor', 'Supervisor', 'Operations'),
(5, 'Andrew Thomas', 'Technician', 'Security'),
(6, 'Olivia Clark', 'Crew Chief', 'Maintenance'),
(7, 'Matthew Brown', 'Manager', 'Operations'),
(8, 'Sophia Garcia', 'Supervisor', 'Security'),
(9, 'Daniel Martinez', 'Technician', 'Maintenance'),
(10, 'Emma Hernandez', 'Crew Chief', 'Operations');

-- Baggage
INSERT INTO AMS.Baggage (Baggage_ID, Passenger_ID, Flight_ID, Baggage_Tag_Number) VALUES
(1, 1, 1001, 1234),
(2, 2, 1002, 5678),
(3, 3, 1003, 91011),
(4, 4, 1004, 121314),
(5, 5, 1005, 151617),
(6, 6, 1006, 181920),
(7, 7, 1007, 212223),
(8, 8, 1008, 242526),
(9, 9, 1009, 272829),
(10, 10, 1010, 303132);

-- Revenue
INSERT INTO AMS.Revenue (Rev_ID, Revenue_Type, Rev_Collected) VALUES
(1, 'Ticket Sales', 10000),
(2, 'Parking Fees', 5000),
(3, 'Baggage Fees', 2000),
(4, 'In-flight Sales', 3000),
(5, 'Cargo Handling', 7000),
(6, 'Aircraft Maintenance', 6000),
(7, 'Lounge Access', 1500),
(8, 'Onboard Entertainment', 2500),
(9, 'Flight Charter', 8000),
(10, 'Security Services', 4000);

-- Parking
INSERT INTO AMS.Parking (Vehicle_No, Entry_Time, Exit_Time, Rev_ID, Rev_Collected) VALUES
(101, '10:00', '12:00', 2, 20),
(102, '12:00', '14:00', 3, 30),
(103, '14:00', '16:00', 1, 25),
(104, '16:00', '18:00', 4, 35),
(105, '18:00', '20:00', 5, 45),
(106, '20:00', '22:00', 6, 55),
(107, '22:00', '23:00', 7, 15),
(108, '10:00', '12:00', 8, 40),
(109, '12:00', '14:00', 9, 50),
(110, '14:00', '16:00', 10, 60);

-- Flight_IDGround_Handling_Equipment
INSERT INTO AMS.Ground_Handling_Equipment (Equipment_ID, Equipment_Type) VALUES
(1, 'Tug'),
(2, 'Belt Loader'),
(3, 'Aircraft Stairs'),
(4, 'Cargo Loader'),
(5, 'Passenger Stairs'),
(6, 'Baggage Cart'),
(7, 'Fuel Truck'),
(8, 'Catering Truck'),
(9, 'Deicing Vehicle'),
(10, 'Pushback Tractor');

-- Ground_Handling_Service:
INSERT INTO AMS.Ground_Handling_Service (Service_ID, Service_Type) VALUES
(1, 'Baggage Handling'),
(2, 'Aircraft Cleaning'),
(3, 'Catering'),
(4, 'Aircraft Refueling'),
(5, 'Cabin Cleaning'),
(6, 'Crew Transportation'),
(7, 'Deicing Service'),
(8, 'Lavatory Service'),
(9, 'Water Service'),
(10, 'Cargo Handling');

-- Restricted_Area
INSERT INTO AMS.Restricted_Area (Area_ID, Area_Type) VALUES
(1, 'Security Zone'),
(2, 'Maintenance Area'),
(3, 'Control Tower'),
(4, 'Hangar'),
(5, 'Apron'),
(6, 'Runway'),
(7, 'Taxiway'),
(8, 'Aircraft Stand'),
(9, 'Fueling Area'),
(10, 'Baggage Sorting Area');

-- Security_System
INSERT INTO AMS.Security_System (Security_ID, Security_Type, Belt_Count, Area_ID) VALUES
(1, 'CCTV', '10', 1),
(2, 'Access Control', '5', 2),
(3, 'Intrusion Detection', '8', 3),
(4, 'Surveillance Camera', '15', 4),
(5, 'Access Control System', '8', 5),
(6, 'Intrusion Detection System', '10', 6),
(7, 'Security Patrol', '12', 7),
(8, 'Biometric Access Control', '6', 8),
(9, 'Perimeter Fence', '20', 9),
(10, 'X-ray Scanner', '4', 10);

-- Tickets
INSERT INTO AMS.Tickets (Ticket_ID, Passenger_ID, Flight_ID) VALUES
(1, 1, 1001),
(2, 2, 1002),
(3, 3, 1003),
(4, 4, 1004),
(5, 5, 1005),
(6, 6, 1006),
(7, 7, 1007),
(8, 8, 1008),
(9, 9, 1009),
(10, 10, 1010);

-- Operates
INSERT INTO AMS.Operates (Flight_ID, Service_ID) VALUES
(1009, 9),
(1010, 10),
(1004, 1),
(1005, 2),
(1006, 3),
(1007, 4),
(1008, 5),
(1009, 6),
(1010, 7);

INSERT INTO AMS.Uses (Flight_ID, Equipment_ID) VALUES
(1001, 1),
(1002, 2),
(1003, 3),
(1004, 4),
(1005, 5),
(1006, 6),
(1007, 7),
(1008, 8),
(1009, 9),
(1010, 10);

-- Ground_Handling_Service
INSERT INTO AMS.Ground_Handling_Service (Service_ID, Service_Type) VALUES
(1, 'Baggage Handling'),
(2, 'Aircraft Cleaning'),
(3, 'Catering'),
(4, 'Aircraft Refueling'),
(5, 'Cabin Cleaning'),
(6, 'Crew Transportation'),
(7, 'Deicing Service'),
(8, 'Lavatory Service'),
(9, 'Water Service'),
(10, 'Cargo Handling');




select * from passenger;
select * from baggage;

SELECT 
    f.Flight_ID, 
    f.Flight_Number,
    COUNT(t.Passenger_ID) AS Booked_Passengers
FROM 
    Flight f
JOIN 
    Tickets t ON f.Flight_ID = t.Flight_ID
GROUP BY 
    f.Flight_ID, f.Flight_Number
HAVING 
    COUNT(t.Passenger_ID)>3;

SELECT 
        FE.Flight_Employee_Name AS Pilot_Name,
        COUNT(F.Flight_ID) AS Flights_Flown_In_Week
    FROM 
        flight_employees FE
     JOIN 
        flight F ON FE.flight_employee_ID = F.Flight_Employee_ID
    WHERE 
        FE.Flight_Employee_ID = 1
        AND FE.Position = 'Pilot'
         
    GROUP BY 
        FE.Flight_Employee_Name;
        
SELECT 
    A.Airline_Name,
    AVG(PassengerCount) AS Avg_Passengers_Per_Flight
FROM 
    Airline A
LEFT JOIN (
    SELECT 
        F.Airline_ID,
        COUNT(DISTINCT T.Passenger_ID) AS PassengerCount
    FROM 
        Flight F
    LEFT JOIN 
        Tickets T ON F.Flight_ID = T.Flight_ID
    GROUP BY 
        F.Airline_ID, F.Flight_ID
) AS PassengerCounts ON A.Airline_ID = PassengerCounts.Airline_ID
GROUP BY 
    A.Airline_Name;

INSERT INTO AMS.Flight (Flight_ID, Flight_Number, Equipment_ID, Departure_time, Arrival_time, Airline_ID, Gate_ID, Flight_Employee_ID, Service_ID, ATC_ID) VALUES
(1012, 123, 'E001', '10:00', '12:00', 1, 101, 1, 1, 1);
-- Parking (continued)

select * from flight_employees;
select * from flight;

-- Ground_Handling_Service: (continued)

SELECT 
    COUNT(DISTINCT P.Passenger_ID) AS Total_Passengers
FROM 
    Passenger P
JOIN 
    Tickets T ON P.Passenger_ID = T.Passenger_ID
JOIN 
    Flight F ON T.Flight_ID = F.Flight_ID
WHERE 
    F.Departure_time BETWEEN '10:00' AND '14:00';

DELIMITER //

CREATE PROCEDURE CountPassengersBetweenTimes(IN arrival_time VARCHAR(45), IN departure_time VARCHAR(45))
BEGIN
    SELECT 
        COUNT(DISTINCT P.Passenger_ID) AS Total_Passengers
    FROM 
        Passenger P
    JOIN 
        Tickets T ON P.Passenger_ID = T.Passenger_ID
    JOIN 
        Flight F ON T.Flight_ID = F.Flight_ID
    WHERE 
        F.Departure_time BETWEEN arrival_time AND departure_time;
END //

DELIMITER ;


SELECT 
    A.Airline_Name,
    AVG(PassengerCount) AS Avg_Passengers_Per_Flight
FROM 
    Airline A
LEFT JOIN (
    SELECT 
        F.Airline_ID,
        COUNT(DISTINCT T.Passenger_ID) AS PassengerCount
    FROM 
        Flight F
    LEFT JOIN 
        Tickets T ON F.Flight_ID = T.Flight_ID
    GROUP BY 
        F.Airline_ID, F.Flight_ID
) AS PassengerCounts ON A.Airline_ID = PassengerCounts.Airline_ID
GROUP BY 
    A.Airline_Name;





DELIMITER //

CREATE PROCEDURE GetFlightsForPilotInWeek(
    IN input_employee_id INT
)
BEGIN
    SELECT 
        FE.Flight_Employee_Name AS Pilot_Name,
        COUNT(F.Flight_ID) AS Flights_Flown_In_Week
    FROM 
        Flight_Employees FE
    INNER JOIN 
        Flight F ON FE.Flight_Employee_ID = F.Flight_Employee_ID
    WHERE 
        FE.Flight_Employee_ID = input_employee_id
        AND FE.Position = 'Pilot'
        AND YEARWEEK(F.Departure_Time) = YEARWEEK(NOW())  -- Adjust the date as needed
    GROUP BY 
        FE.Flight_Employee_Name;
END //

DELIMITER ;





SELECT f.Flight_ID, f.Flight_Number, f.Arrival_time, f.Departure_time, a.Airline_Name
FROM Flight f
LEFT JOIN Airline a ON f.Airline_ID = a.Airline_ID;

SELECT f.Flight_Number, COUNT(p.Passenger_ID) AS Passenger_Count
FROM Flight f
LEFT JOIN Passenger p ON f.Flight_ID = p.Flight_ID
GROUP BY f.Flight_Number;

SELECT SUM(Rev_Collected) AS Total_Ticket_Revenue
FROM Revenue
WHERE Revenue_Type = 'Ticket Sales';

SELECT p.Passenger_Name, b.Baggage_Tag_Number
FROM Passenger p
LEFT JOIN Baggage b ON p.Passenger_ID = b.Passenger_ID;

SELECT f.Flight_Number, g.Gate_Number
FROM Flight f
JOIN Gate g ON f.Gate_ID = g.Gate_ID;

SELECT HOUR(p.Entry_Time) AS Hour, SUM(p.Rev_Collected) AS Total_Revenue
FROM Parking p
JOIN Revenue r ON p.Rev_ID = r.Rev_ID
WHERE r.Revenue_Type = 'Parking Fees'
GROUP BY HOUR(p.Entry_Time);

SELECT f.Flight_ID, f.Flight_Number, COUNT(t.Passenger_ID) AS Booked_Passengers
FROM Flight f
JOIN Tickets t ON f.Flight_ID = t.Flight_ID
GROUP BY f.Flight_ID, f.Flight_Number
ORDER BY Booked_Passengers DESC
LIMIT 1;

SELECT AVG(baggage_count) AS Avg_Baggage_Per_Passenger
FROM (
    SELECT p.Passenger_ID, COUNT(b.Baggage_ID) AS baggage_count
    FROM Passenger p
    LEFT JOIN Baggage b ON p.Passenger_ID = b.Passenger_ID
    GROUP BY p.Passenger_ID
) AS baggage_counts 
LIMIT 1;

SELECT COUNT(DISTINCT p.Passenger_ID) AS Total_Passengers_With_Baggage
FROM Passenger p
JOIN Baggage b ON p.Passenger_ID = b.Passenger_ID;

SELECT DISTINCT p.Passenger_ID, p.Passenger_Name
FROM Passenger p
WHERE p.Passenger_ID IN (
    SELECT Passenger_ID
    FROM Baggage
    GROUP BY Passenger_ID
    HAVING COUNT(*) > 1
);


SELECT 
    f.Flight_ID, 
    f.Flight_Number,
    COUNT(t.Passenger_ID) AS Booked_Passengers
FROM 
    Flight f
JOIN 
    Tickets t ON f.Flight_ID = t.Flight_ID
GROUP BY 
    f.Flight_ID, f.Flight_Number
HAVING 
    COUNT(t.Passenger_ID) > 3;
    

SELECT fe.Flight_Employee_Name, COUNT(f.Flight_ID) AS Flights_Worked
FROM Flight_Employees fe
JOIN Flight f ON fe.Flight_Employee_ID = f.Flight_Employee_ID
GROUP BY fe.Flight_Employee_Name
ORDER BY Flights_Worked DESC
LIMIT 5;

SELECT 
    COUNT(DISTINCT P.Passenger_ID) AS Total_Passengers
FROM 
    Passenger P
JOIN 
    Tickets T ON P.Passenger_ID = T.Passenger_ID
JOIN 
    Flight F ON T.Flight_ID = F.Flight_ID
WHERE 
    F.Departure_time BETWEEN '10:00' AND '14:00';
    
    
SELECT
a.Airline_Name,
COUNT(DISTINCT p.Passenger_ID) AS Total_Passengers
FROM
Passenger p
JOIN
Flight f ON p.Flight_ID = f.Flight_ID
JOIN
Airline a ON f.Airline_ID = a.Airline_ID
GROUP BY
a.Airline_Name;


SELECT 
    g.Gate_ID, 
    g.Gate_Number,
    COUNT(f.Flight_ID) AS Flight_Count
FROM 
    Gate g
JOIN 
    Flight f ON g.Gate_ID = f.Gate_ID
GROUP BY 
    g.Gate_ID
ORDER BY 
    Flight_Count DESC
LIMIT 1;

select * from air_traffic_control_system;
select * from airline;
select * from baggage;
select * from employee;
select * from flight;
select * from flight_employees;
select * from gate;
select * from ground_handling_equipment;
select * from ground_handling_service;
select * from operates;
select * from parking;
select * from passenger;
select * from restricted_area;
select * from revenue;
select * from security_system;
select * from tickets;
select * from uses;

SELECT f.Flight_ID, f.Flight_Number, f.Arrival_time, f.Departure_time, a.Airline_Name FROM Flight f LEFT JOIN Airline a ON f.Airline_ID = a.Airline_ID; 
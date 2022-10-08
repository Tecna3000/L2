alter session set nls_date_format='dd/mm/yyyy hh24:mi';

/*Question 1:*/

DROP TABLE Villes purge; 

DROP TABLE Bus purge; 


DROP TABLE Trajets purge; 

DROP TABLE Etapes purge; 

DROP TABLE Tarifs purge; 
DROP TABLE Clients purge; 

DROP TABLE Reservations purge;  


CREATE TABLE Villes (NomV VARCHAR(58) PRIMARY KEY NOT NULL);

CREATE TABLE Bus(
                NumB VARCHAR(10)PRIMARY KEY NOT NULL, 
                CapaciteB INT NOT NULL,
                Check (CapaciteB>0)
                );

CREATE TABLE Trajets (
                      NumT VARCHAR(10) PRIMARY KEY NOT NULL,
                      NumB VARCHAR (10)NOT NULL ,
                      CONSTRAINT Numbus FOREIGN KEY( NumB)REFERENCES BUS(NumB) 
                      );
                      
CREATE TABLE Etapes (
                    NumT VARCHAR(10) NOT NULL,
                    NomV VARCHAR(58) NOT NULL,
                    DateA Date NOT NULL,
                    DateD Date NOT NULL,
                    CHECK ((DateD-DateA)*24*60 >=5),
                    
                    CONSTRAINT NumTrajet FOREIGN KEY (NumT) REFERENCES Trajets(NumT), 
                    CONSTRAINT NomVille FOREIGN KEY(NomV) REFERENCES Villes,
                    CONSTRAINT Numtnumv PRIMARY KEY (NumT, NomV)

                    );

CREATE TABLE Tarifs(
                    VilleD VARCHAR (58)NOT NULL,
                    VilleA VARCHAR (58)NOT NULL,
                    Prix float NOT NULL,
                    CONSTRAINT VDEP FOREIGN KEY (VilleD)REFERENCES Villes,
                    CONSTRAINT VDAR FOREIGN KEY (VilleA) References Villes,
                    Constraint VILLEAVILLED PRIMARY KEY(VilleD, VilleA)
                    );

CREATE TABLE Clients(

                    NumC INT PRIMARY KEY NOT NULL,
                    NomC CHAR(30) NOT NULL
                
                        );
                            
CREATE TABLE Reservations ( 

                            NumR INT PRIMARY KEY NOT NULL, 

                            NumC INT NOT NULL, 

        
                            NumT VARCHAR(10) NOT NULL, 

                            VilleD VARCHAR(58) NOT NULL, 

                            VilleA VARCHAR(58) NOT NULL, 

                            NbPlaces INT NOT NULL, 

                          

                            CONSTRAINT NumCl FOREIGN KEY (NumC) REFERENCES Clients(NumC),  

                            CONSTRAINT TRAJETD  FOREIGN KEY (NumT, VilleD) REFERENCES Etapes (NumT,NomV), 

                            CONSTRAINT TRAJETA  FOREIGN KEY (NumT, VilleA) REFERENCES Etapes (NumT,NomV), 

                            CONSTRAINT TARIFTR FOREIGN KEY (VilleD, VilleA) REFERENCES Tarifs (VilleD,VilleA) 

                            ); 
                            
                            
    INSERT INTO Villes values ('Rome') ;
    INSERT INTO Villes values ('Marseille') ;
    INSERT INTO Villes values ('Berlin') ;
    INSERT INTO Villes values ('Madrid') ;
    INSERT INTO Villes values ('Bruxelles') ;
    INSERT INTO Bus values ('L255',66) ;
    INSERT INTO Bus values ('L256',66) ;
    INSERT INTO Bus values ('L257',97) ;
    INSERT INTO Trajets values ('255','L255') ;
    INSERT INTO Trajets values ('256','L256') ;
    INSERT INTO Trajets values ('257','L257') ;
    INSERT INTO Trajets values ('257','L257') ;
    INSERT INTO Etapes values ('257','Marseille', '17/11/2021 08:00','17/11/2021 08:10' );
    INSERT INTO Etapes values ('257','Rome', '17/11/2021 10:00','17/11/2021 10:10' );
    INSERT INTO Etapes values ('257','Madrid', '17/11/2021 12:00','17/11/2021 13:00' );
    INSERT INTO Etapes values ('256','Berlin', '20/12/2021 12:00','20/12/2021 12:30' );
    INSERT INTO Etapes values ('256','Bruxelles', '20/12/2021 20:00','20/12/2021 23:30' );
    INSERT INTO Etapes values ('255','Madrid', '01/07/2022 08:00','07/07/2022 13:00' );
    INSERT INTO Tarifs values ('Marseille','Rome', 35 );
    INSERT INTO Tarifs values ('Rome','Madrid', 61 );
    INSERT INTO Tarifs values ('Marseille','Bruxelles', 46 );
    INSERT INTO Tarifs values ('Berlin','Bruxelles', 20 );
    INSERT INTO Clients values ('17' ,'Meriem' );
    INSERT INTO Clients values ('8','Clément' );
    INSERT INTO Clients values ('7','Lina' );
    INSERT INTO Clients values ('4','Juliette' );
    INSERT INTO Clients values ('5','Federico' );
    INSERT INTO Clients values ('12','Clara' );
    INSERT INTO Reservations values(155, 12,'257','Marseille','Rome',1);
    INSERT INTO Reservations values(15, 12,'257','Rome','Madrid',1);
    INSERT INTO Reservations values(3, 7,'256','Berlin','Bruxelles',6);
    
    /*Question 3*/
    
1)
CREATE VIEW ReservationsAvecHorraires 

 AS Select R.*, Ar.DateA , Dp.DateD
 From Reservations R,(Select DateD,R.NumT,R.NumR From Etapes E ,Reservations R where E.NumT = R.NumT and R.VilleD=E.NomV) Dp,
 (Select DateA, R.NumR ,R.NumT From Etapes E ,Reservations R where E.NumT = R.NumT and VilleA = NomV) Ar
 Where Dp.NumR = Ar.NumR  and Dp.NumR = R.NumR and R.NumT = Dp.NumR ;

2)
Create view NbPlacesReserveesEtapes 
  AS Select E.NumT,E.NomV , sum(case when ((Rh.DateD <= E.DateD) And (E.DateD < Rh.DateA)) Then Rh.NbPLaces END) AS nmbrplace
  from Etapes E, ReservationsAvecHorraires Rh   
  Where Rh.NumT=E.NumT and Rh.DateD <= E.DateD AND E.DateD < Rh.DateA
  Group by E.NumT, E.NomV;
  
  Select * From NbPlacesReserveesEtapes;

3)
Create view Liasons
    AS SELECT T.NumT ,VilleD, VilleA
    From Trajets T, Etape E
    Where E.NumT =T.NumT and VilleA, VilleD in (Trajets T) and 
           
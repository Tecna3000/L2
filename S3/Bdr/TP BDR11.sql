alter session set nls_date_format='dd/mm/yyyy hh24:mi';

SELECT NumVol
FROM VOLS
    WHERE VilleD = 'Paris';
     
SELECT Distinct NumPil
 FROM VOLS
  WHERE VilleD <>'Paris' OR VilleA <> 'Marseille' ;

SELECT NumVol 
 FROM VOLS 
  WHERE 24*(DateA - DateD) BETWEEN 2 AND 4;
    
    
SELECT NumAv, NomAv
FROM AVIONS
WHERE AVIONS.NomAV NOT LIKE '%a%'OR AVIONS.NomAv NOT LIKE '%A%';

SELECT NomPil , (to_char( SYSDATE , 'yyyy')  - NaisPil) 
FROM PILOTES
WHERE VillePil ='Nice' AND (to_char( SYSDATE , 'yyyy')  - NaisPil) >35;

SELECT DISTINCT NumVol
FROM VOLS
WHERE NumAv != 101 AND NumAV != 404;
    

SELECT NumPil , NVL(VillePil ,'Ville inconnue') 
From PILOTES ;

Select NumVol, VilleD, DateD
From Vols
Where VilleA = 'Marseille'
Order by to_char( DateD, 'dd/mm/yyy') ,to_char( DateD, 'HH') Desc ;

Select DISTINCT NVL (SUBSTR(NomAv,0, INSTR(NomAv, ' ')-1), NomAv) As NomAv
FROM AVIONS;

SELECT AVG((to_char( SYSDATE , 'yyyy')  - NaisPil))
FROM PILOTES ;

SELECT count(Distinct VilleA)
FROM VOLS;
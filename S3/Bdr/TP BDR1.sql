
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

---I) Gestion des ensembles d’attributs
--1)

Create Table EnsemblesAttributs ( NumEnsAtt INT PRIMARY KEY);


Create TABLE EnsembleContientAttribut ( NumEnsAtt  ,NomAtt VARCHAR(10), 

Constraint Att Primary KEY (NumEnsAtt,NomAtt), 

CONSTRAINT NumAttribut FOREIGN KEY (NumEnsAtt) References EnsemblesAttributs(NUmEnsAtt) On Delete Cascade); 

Create Sequence NumEnsAtt;
-----------------------------------------------------------------------------
--2)
Create or Replace Function CreerEnsAttVide return number

AS var number(38);

Begin insert into EnsemblesAttributs values(NumEnsAtt.NextVal) returning NumEnsAtt into var;
return var;
end;
/

Variable Num number;
Begin :Num := CreerEnsAttVide; end;
/
Print Num;

------------------------------------------------
------------------------------------------------

Create Or Replace Procedure AjouterAtt( p_NomAtt varchar, p_NumEnsAtt number)
As 
Begin insert into  EnsembleContientAttribut(NomAtt,NumEnsAtt)
values(p_NomAtt , p_NumEnsAtt );
end;

/

Execute AjouterAtt('A',1);
Execute AjouterAtt('C',1);

 ----------------------------------------------------------------------
 ----------------------------------------------------------------------
 
Create or Replace Function CreerEnsAtt(p_ChaineAtt varchar) return number

As
Debut number(38);
pos number(38);
NumEns integer;
Begin 
NumEns:= creerEnsAttVide;
debut :=1;
Pos := instr(p_chaineatt,',',debut);

if  p_ChaineAtt is null Then  return NumEns; End If;
While pos>0
loop
    AjouterAtt (Substr (p_chaineatt, debut, pos-debut),NumEns);
    debut := pos +1;
    pos  := instr(p_chaineatt,',',debut);
end loop;
AjouterAtt(SUBSTR(p_chaineatt, debut),NumEns);
return NumEns;
End;
/
Variable Num number;
Begin :Num :=CreerEnsAtt('e') ; end;
/
Print Num;
Select NomAtt from EnsembleContientAttribut Where NumEnsAtt=:Num;
/*pour revenir à l'état de départ
Rollback*/

---------------------------------------------------------------------------
---------------------------------------------------------------------------
Create or Replace Function EnsAtt2Chaine(p_NumEnsAtt number) return varchar
As listatt Varchar(20);
 Begin
 listAtt := '';
      For r in (select NomAtt FROM EnsembleContientAttribut Where  p_NumEnsAtt = NumEnsAtt )
       Loop
         listAtt:= listAtt|| ','|| r.NomAtt;
       End loop;
     return Substr(listAtt,2);
End;
/

Select EnsAtt2Chaine(141) from dual;

---------------------------------------------------------------------------
---------------------------------------------------------------------------
 Create or Replace Function EstElement(p_NomAtt varchar, p_NumEnsAtt number) return integer
 As
  Begin 
   For r in (select NomAtt FROM EnsembleContientAttribut Where p_NomAtt = NomAtt and  p_NumEnsAtt = NumEnsAtt )
      Loop
        return 1;
      End loop;
      return 0;

 End;
/
Select EstElement('A',202) from dual;
-------------------------------------------------------------------------
-------------------------------------------------------------------------
 
Create or Replace Function EstInclus(p_NumEnsAtt_1 number, p_NumEnsAtt_2 number) return integer
AS Begin 
For r in (select NomAtt FROM EnsembleContientAttribut Where p_NumEnsAtt_1 = NumEnsAtt and NomAtt not in (Select NomAtt From EnsemblecontientATTRIBUT Where NumEnsAtt = p_NumEnsAtt_2 ))
loop  
    return 0;
end loop; 
return 1;
End;
/

Select EstInclus(1,242) from dual;
  
  -----------------------------------------------------------
  -----------------------------------------------------------
  
Create or Replace Function  EstEgal(p_NumEnsAtt_1 number, p_NumEnsAtt_2 number) return integer
AS Begin 
For r in (select NomAtt FROM EnsembleContientAttribut Where p_NumEnsAtt_1 = NumEnsAtt and NomAtt not in (Select NomAtt From EnsemblecontientATTRIBUT Where NumEnsAtt = p_NumEnsAtt_2 )
OR p_NumEnsAtt_2 = NumEnsAtt and NomAtt not in (Select NomAtt From EnsemblecontientATTRIBUT Where NumEnsAtt = p_NumEnsAtt_1))
loop  
    return 0;
end loop; 
return 1;
End;
/
Select EstEgal(242,1) from dual;

-------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------

Create or Replace Function UnionAtt(p_NumEnsAtt1 number, p_NumEnsAtt2 number) return number
 AS NumEnsAtt_1U2 NUMBER;
 Begin
    NumEnsAtt_1U2 := CreerEnsAttVide; 
    for r in
       (select NomAtt FROM EnsembleContientAttribut Where  p_NumEnsAtt1 = NumEnsAtt 
          Union
       select NomAtt FROM EnsembleContientAttribut Where  p_NumEnsAtt2 = NumEnsAtt )
       loop
           Ajouteratt(r.nomatt, NumEnsAtt_1U2);
      end loop;  
  return NumEnsAtt_1U2;                 
  
END;
/

Variable Num number
Begin :Num := UnionAtt(1,243); 
end;
/
SELECT * FROM EnsembleContientAttribut;

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

Create Or Replace Function IntersectionAtt (p_NumEnsAtt1 number, p_NumEnsAtt2 number) return number
 AS NumEnsAtt_1X2 NUMBER;
 Begin
    NumEnsAtt_1X2 := CreerEnsAttVide; 
    for r in
       (select NomAtt FROM EnsembleContientAttribut Where  p_NumEnsAtt1 = NumEnsAtt 
          INTERSECT
       select NomAtt FROM EnsembleContientAttribut Where  p_NumEnsAtt2 = NumEnsAtt )
       loop
           Ajouteratt(r.nomatt, NumEnsAtt_1X2);
      end loop;  
  return NumEnsAtt_1X2;                 
  
END;
/
Variable Num number
Begin :Num := IntersectionAtt(1,243); 
end;
/
SELECT * FROM EnsembleContientAttribut;

-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
Create or Replace Function SoustractionAtt(p_NumEnsAtt1 number, p_NumEnsAtt2 number) return number
 AS NumEnsAtt_1_2 NUMBER;
 Begin
    NumEnsAtt_1_2 := CreerEnsAttVide; 
    for r in
       (select NomAtt FROM EnsembleContientAttribut Where  p_NumEnsAtt1 = NumEnsAtt 
          minus
       select NomAtt FROM EnsembleContientAttribut Where  p_NumEnsAtt2 = NumEnsAtt )
       loop
           Ajouteratt(r.nomatt, NumEnsAtt_1_2);
      end loop;  
  return NumEnsAtt_1_2;                 
  
END;
/
Variable Num number
Begin :Num := SoustractionAtt(1,243); 
end;
/
SELECT * FROM EnsembleContientAttribut;

------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------

Create or Replace Function CopieAtt(p_NumEnsAtt number) return number
 AS NumEnscopy NUMBER;
 Begin
    NumEnscopy := CreerEnsAttVide; 
    for r in
       (select NomAtt FROM EnsembleContientAttribut Where numEnsAtt  = p_NumEnsAtt  )
       loop
           Ajouteratt(r.nomatt, NumEnscopy);
      end loop;  
  return NumEnscopy;                 
 b
END;
/
Variable Num number
Begin :Num := copieAtt(1); 
end;
/
SELECT * FROM EnsembleContientAttribut;

-------------------------------------------------------------------------

-----II) Gestion des DF (dépendances fonctionnelles)

--1)
Create table DFS ( NumDF Number (10) Primary Key ,
NumEnsGauche number(10) NOt NUll,
NUmEnsDroite number (10) Not NUll,
Constraint NumEns_GD UNIQUE(NumEnsGauche, NumEnsDroite),
Constraint numg Foreign Key (NumEnsGauche)   References  EnsemblesAttributs (NumEnsAtt),
Constraint numd Foreign Key (NumEnsDroite ) References EnsemblesAttributs ( NumEnsAtt ));

Create Sequence NumDF;

--2)
Create or REplace Function CreerDF(p_ChaineAtt varchar) return number
AS num number;
pos integer;
NumEnsDroit integer;
NumENsGauche integer;
ChaineEnsDroit VARCHAR(10);
ChaineEnsGauche Varchar(10);
Begin
    pos := INSTR(p_chaineAtt,'->',1);
    ChaineEnsGauche:= substr(p_chaineAtt,1,pos-1);
    ChaineEnsDroit:= substr(p_chaineAtt,pos+2);
    NumEnsGauche :=creerEnsAtt(ChaineEnsGauche);
    NumEnsDroit:=creerEnsAtt(ChaineEnsdroit);
    insert INTO DFs Values(NumDF.NextVal,NumEnsGauche ,NumEnsDroit);
 return NumDf.currval;
End;
/
Variable Num number
Begin :Num := CreerDF('B,C->D,E,F');
end;
/
SELECT :Num, EnsAtt2chaine(NumEnsGauche), EnsAtt2chaine(NumEnsDroite) from Dfs Where NumDF =: Num;

----------------------------------------------------------------------------------
------------------------------------------------------------------------------------

Create or Replace Function DF2Chaine(p_NumDF number) return varchar
as 
    chaineEnsGauche varchar(10);
    ChaineEnsDroit Varchar (10);
    Chaine2 Varchar(20);
    Type DF_t is record (NumEnsGauche integer, NumEnsDroite integer);
    ensrecord Df_t;
    begin
         Select NumEnsGauche , NumEnsDroite into ensrecord
         from DFS where NumDF =p_NumDf ;
         
         chaineEnsGauche := EnsAtt2chaine(ensrecord.NumEnsGauche);
         ChaineEnsDroit := EnsAtt2chaine(ensrecord.NUmEnsDroite);
         chaine2 := concat(chaineEnsGauche,'->');
         Chaine2 := Concat (chaine2,chaineEnsDroit);
    return chaine2;
 End; 
 /
Variable chaine varchar2
Begin :chaine:=  DF2Chaine(1);
end;
/
print chaine;

-----------------------------------------------------------------
-------------------------------------------------------------------
Create or Replace FUnction EstTriviale(p_NumDF Number) return integer
as 
    Type DF_t is record (NumEnsGauche integer, NumEnsDroite integer);
    ensrecord Df_t;
begin 
  Select NumEnsGauche , NumEnsDroite into ensrecord
         from DFS where NumDF =p_NumDf ;
    if estinclus(ensrecord.NumEnsdroite,ensrecord.NumensGauche)=1 then
      return 1;
    end if;
    return 0;
End;
/
Variable EstT  number
Begin :EstT:=  EstTriviale(17);
end;
/
print EstT;

------------------------------------------------------------------
-------------------------------------------------------------------

Create or Replace Function EstPlusForte(p_NumDF1 Number, p_NumDF2 Number) return integer
as

    Type DF_t is record (NumEnsGauche integer, NumEnsDroite integer);
    ensrecord1 Df_t;
    ensrecord2 DF_t;
begin 
  Select NumEnsGauche , NumEnsDroite into ensrecord1
         from DFS where NumDF =p_NumDf1 ;
   Select NumEnsGauche , NumEnsDroite into ensrecord2
         from DFS where NumDF =p_NumDf2 ;
    if estinclus(ensrecord2.NumEnsdroite,ensrecord1.NumensDroite)=1 and estinclus(ensrecord1.NumEnsgauche,ensrecord2.NumensGauche)=1 then
      return 1;
    end if;
    return 0;
End; 
/
Variable Epf  number
Begin :Epf:= EstPlusForte(19,17);
end;
/
print Epf;
----------------------------------------------------------------------
-------------------------------------------------------------------------
------------III) Gestion des ensembles de DF
--1)
Create Table EnsemblesDFs (NumEnsDF Int Primary Key);

Create Table EnsembleContientDF ( NumEnsDF number(10) ,NumDF number(10), 
Constraint Attdf Primary KEY (NumEnsDF,NumDF) ,
CONSTRAINT Numed FOREIGN KEY (NumEnsDF) References EnsemblesDFs (NumEnsDF)on delete cascade,
CONSTRAINT NumDfs FOREIGN KEY (NumDF) References DFs (NumDF));

Create Sequence NumEnsDF;
------------------------------------------------------------------
---------------------------------------------------------------------
--2)
Create or Replace function CreerEnsDFVide return number
as
Begin insert into EnsemblesDFS values(NumEnsDF.NextVal);
return NumEnsDF.currval;
end;
/
----------------------------------------------------------------------------
---------------------------------------------------------------------------

Create or Replace Procedure AjouterDF ( p_NumDF number, p_NumEnsDF number )
AS
Begin
Insert into EnsembleContientDF values( p_NumEnsDf, p_NumDf);
End;
/
-------------------------------------------------------------------------
-------------------------------------------------------------------------

Create or Replace Function CreerEnsDF(p_ChaineDF varchar) return number
As
Debut number(38);
pos number(38);
Numens integer;
Begin 
NumEns:= CreerEnsDFVide;
debut :=1;
Pos := instr(p_chaineDF,';',debut);

if  p_ChaineDF is null Then  return NumEns; End If;
While pos>0
loop
    AjouterDF (creerDF(Substr (p_chaineDF, debut, pos-debut)),NumEns);
    debut := pos +1;
    pos  := instr(p_chaineDF,';',debut);
end loop;
AjouterDF(creerDF(SUBSTR(p_chaineDF, debut)),NumEns);
return NumEns;
End;
/
Variable Num number;
Begin :Num :=CreerEnsDF('A->B;B->C') ; end;
/
Print Num;

-----------------------------------------------------------------------
------------------------------------------------------------------------
CReate or Replace Function EnsDF2Chaine(p_NumEnsDF number) return varchar
As
listDf Varchar(20);
Begin
 listDF := '';
      For r in (select NumDf FROM EnsembleContientDF Where  p_NumEnsDF = NumEnsDF )
       Loop
         listDF:= listDF|| ';'|| Df2chaine(r.NuMDF);
       End loop ;
     return Substr(listDF,2);
End;
/

Select EnsDF2Chaine(4) from dual;

-----------------------------------------------------------------------
------------------------------------------------------------------------
--3)
Create or Replace Function EnsDF2EnsAtt(p_NumEnsDF number) return number
AS
NumEns2 number;
ens varchar (2000);
Type DF_t is record (NumEnsGauche number(10), NumEnsDroit number(10));
ensrecord DF_t;

Begin 
 for r in (SELECT NumEnsGauche, NumEnsDroite into ensrecord FROM DFS WHERE NumDF = numEns2)
    loop
        ens:=ensAtt2Chaine(r.NumEnsGauche)|| ',' || ensAtt2Chaine(r.NumEnsDroite)|| ',';
      End loop;
      NUmEns2 := creerEnsAtt(ens);
 return NumEns2;
End;
/
Variable num  number
Begin :num:= EnsDF2EnsAtt(4);
end;
/
print num;

-----------------------------------------------------------------------
------------------------------------------------------------------------

Create or Replace Function CopieEnsDF(p_NumEnsDF number) return number
As numdfcopy number;
ensdf varchar (2000);
Type DF_t is record (NumEnsGauche number(5), NumEnsDroit number(5));
ensrecord  DF_t;
Begin
 for r in (SELECT NumEnsGauche, NumEnsDroite into ensrecord FROM DFS WHERE NumDF = numdfcopy)
   loop 
        ensdf := ensAtt2Chaine(r.NumEnsGauche) || '->' || ensAtt2Chaine(r.NumEnsDroite) || ',';
   end loop; 
  
    numdfcopy :=creerEnsDF(ensdf);
    return numdfcopy;
end;
/
Variable num  number
Begin :num:=  CopieEnsDF(4);
end;
/
print num;

-----------------------------------------------------------------------
------------------------------------------------------------------------

---IV- Gestion des schémas relationnels

CREATE TABLE Schemas(
    NumSchema number (10) PRIMARY KEY, NumEnsAtt number(10), NumEnsDF number(10),
    CONSTRAINT NumEnsAtt FOREIGN KEY(NumEnsAtt) REFERENCES EnsemblesAttributs(NumEnsAtt),
    CONSTRAINT NumEnsDF FOREIGN KEY(NumEnsDF) REFERENCES EnsemblesDFs(NumEnsDF));

Create sequence NumSchema;

---------------------------------------------------------------
----------------------------------------------------------------
--2)

-- fetourne sous forme de chaine les attributs de df passé en paramètre
create or replace function chaineattdf(p_ChaineDF varchar) return varchar 
AS

chaine varchar(20);
gauche varchar(20);
droit varchar(20);
debut number;
pos number;

Begin
    chaine :='';
    debut:=1;
    pos:=INSTR(p_ChaineDF,'->',debut);
    gauche:=SUBSTR(p_ChaineDF, debut, pos-debut);
    droit:=SUBSTR(p_ChaineDF, pos+2, length(p_ChaineDF)-pos-1);
    chaine := gauche ||','|| droit;
    return chaine ;
end;
/

-- retourne sous forme de chaine les attributs d'un ensemble de df passé en paramètre
create or replace function ChaineEnsDf2chaine(p_ChaineEnsDF varchar) return varchar 
AS
debut number;
pos number;
df varchar(20);
chaine varchar(20);

Begin
    debut:=1;
    pos:=INSTR(p_ChaineEnsDF,';',debut);
    chaine := '';
    while pos!=0 loop
        df:=SUBSTR(p_ChaineEnsDF, debut, pos-debut);
        chaine:= chaine || chaineattdf(df);    
        debut:=pos + 1;
        pos:=INSTR(p_ChaineEnsDF, ';', debut);
        if pos!=0 then
            chaine :=chaine || ',';
        end if;
    end loop;
    df:=SUBSTR(p_ChaineEnsDF, debut, length(p_ChaineEnsDF)-debut+1);
    chaine:= chaine || ','||chaineattdf(df);
 
    return chaine;
end;
/


-- retourne 1 si les attributs du premier ensemble sont dans le deuxième; si non return 0
create or replace function contientens(chaine1 varchar, chaine2 varchar) return integer
AS

debut number;
pos number;
att varchar(20);
Begin
    debut:=1;
    pos :=INSTR(chaine1, ',', debut);
    
    while pos!=0 loop
        att:=SUBSTR(chaine1, debut, pos-debut);
        
        if INSTR(chaine2, att, 1)=0 then
            return 0;
       end if;    
       debut:=pos+1;
       pos:=INSTR(chaine1, ',', debut);
    end loop;
    att:=SUBSTR(chaine1, debut, length(chaine1)-debut+1);
    
    if INSTR(chaine2, att, 1)=0  then
        return 0;
    else
        return 1;
    end if; 
end;
/

create or replace function creerSchema(p_ChaineEnsAtt varchar, p_ChaineEnsDF varchar) return number 
As
num Number;
the_exception Exception;
numEnsAtt number;
numEnsDF number;
Begin
    if contientens(ChaineEnsDf2chaine(p_ChaineEnsAtt) , p_ChaineEnsAtt) =1 then
        numEnsAtt:= creerEnsAtt(p_ChaineEnsAtt);
        numEnsDF:= creerEnsDF(p_ChaineEnsDF);
        INSERT INTO Schemas values(NumSchema.NextVal, numEnsAtt, numEnsDF)
        returning NumSchema into num;
        return num;
    else 
       raise the_exception; 
    End if;
    EXCEPTION 
        When the_exception then
        dbms_output.put_line('Shema ne peut etre cree');
    return num;
end;
/

Variable Num number;
Begin :Num := CreerSchema('A,B,C,D','A->B;B->C') ; end;
/
Print Num;
-----------------------------------------------------
--3)
Select EnsAtt2Chaine(NumEnsAtt), ensDF2Chaine(NumEnsDF)
From Schemas
Where NumSchema=3

---------------------------------------------------------------------
--V Gestion des clefs des schémas relationnels
--1)
CREATE TABLE EnsemblesClefs( 
    NumEnsClef Number(10) PRIMARY KEY,
    NumSchema Number(10) UNIQUE,
    CONSTRAINT Num_shema FOREIGN KEY(NumSchema) REFERENCES Schemas(NumSchema) ON DELETE CASCADE);

CREATE TABLE EnsembleContientClef(
    NumEnsClef Number(10) ,
    NumClef Number(10),
    CONSTRAINT num1 PRIMARY KEY(NumEnsClef, NumClef),
    CONSTRAINT num2 FOREIGN KEY(NumEnsClef) REFERENCES EnsemblesClefs(NumEnsClef) ON DELETE CASCADE,
    CONSTRAINT num3 FOREIGN KEY(NumClef) REFERENCES EnsemblesAttributs(NumEnsAtt));
    
CREATE SEQUENCE NumEnsClef;
----------------------------------------------------------
--2)

Create or Replace Function EnsClef2Chaine(p_NumEnsClef number) return varchar 
AS
listclefs varchar(2000);
Begin
  listclefs := '';
  For r in ( select NumClef from EnsembleContientClef where NumEnsClef = p_NumEnsClef)
    loop
       listclefs :='{' || ensAtt2Chaine(r.NumClef) ||'}' ||',';
    end loop;

    return listclefs;
end;
/

-----------------------------------------------------------------
-----------------------------------------------------------------
Create or Replace Function creerEnsClefVide(p_NumSchema number) return number 
AS
num Number;
Begin
    INSERT INTO EnsemblesClefs values(NumEnsClef.NextVal, p_NumSchema)
    returning NumEnsClef into num;
    return num;
end;
/
Variable Num number;
Begin :Num := CreerEnsClefVide(1); end;
/
Print Num;
-------------------------------------------------------------------
-------------------------------------------------------------------
Create Or Replace Procedure ajouterClef(p_NumClef number, p_NumEnsClef number)
AS
Begin
    INSERT INTO EnsembleContientClef Values(p_NumEnsClef, p_NumClef);
End;
/

Execute AjouterClef(1,2);

-----------------------------------------------------------------------
-------VI- GEstion des structures relationnelles

--1)
CREATE TABLE Structures(NumStructure NUMBER(10) PRIMARY KEY);

CREATE TABLE StructureContientSchema(
    NumStructure Number(10),
    NumSchema Number(10),
    CONSTRAINT nums1 PRIMARY KEY(NumStructure, NumSchema),
    CONSTRAINT nums2 FOREIGN KEY(NumStructure) REFERENCES Structures(NumStructure) ON DELETE CASCADE,
    CONSTRAINT nums3 FOREIGN KEY(NumSChema) REFERENCES Schemas(NumSchema));

---------------------------------------------------------
----------------------------------------------------------
---2)
Create Or Replace Function creerStructureVide return number
AS
num Number;
Begin
    INSERT INTO Structures Values(NumStructure.NextVal)
    returning NumStructure into num;
    return num;
End;
/
Variable Num number;
Begin :Num := creerStructureVide; end;
/
Print Num;

-----------------------------------------------------
-----------------------------------------------------
Create Or Replace Procedure ajouterSChema(p_NumSChema number, p_NumStructure number)
AS
Begin
    INSERT INTO StructureContientSChema Values(p_NumStructure, p_NumSchema);  
End;
/
Execute ajouterSChema(1,1);
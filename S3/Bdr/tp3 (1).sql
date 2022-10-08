
SET SERVEROUT ON;
--1 


--I-Gestion des ensembles d'attributs

CREATE TABLE EnsemblesAttributs(
    NumEnsAtt NUMBER(5) CONSTRAINT PK_Ens PRIMARY KEY
);

CREATE TABLE EnsembleContientAttribut(
  NumEnsAtt NUMBER(5),
  NomAtt VARCHAR2(20) NULL,
  CONSTRAINT PK_EnsContAtt PRIMARY KEY(NumEnsAtt, NomAtt),
  CONSTRAINT FK_EnsContAtt FOREIGN KEY(NumEnsAtt) REFERENCES EnsemblesAttributs(NumEnsAtt) ON DELETE CASCADE
);

CREATE SEQUENCE NumEnsAtt;

--2
-- fonction creerEnsAttVide crée un ensemble vide d'attribut
create or replace function creerEnsAttVide return number is
var number;
begin
   INSERT INTO EnsemblesAttributs Values(NumEnsAtt.NextVal)
   returning NumEnsAtt into var;
   return var;
end;
/

--procedure ajouterAtt permet d'ajouter un attribut à un ensemble
create or replace procedure ajouterAtt(p_NomAtt varchar, p_NumEnsAtt number) is
begin
    INSERT INTO EnsembleContientAttribut Values(p_NumEnsAtt, p_NomAtt);
end;
/

--EXECUTE AjouterAtt('A',1);

/*fonction creerEnsAtt permet de créer un ensemble d'attribut à partir de la liste 
  d'attribut passé sous forme de chaine de caractère en argument
*/
create or replace function creerEnsAtt(p_ChaineAtt varchar) return number is
num number:=-1;
posVirgul number;
posDebut number;
nomAtt varchar(20);
longueur number;
begin
     if p_ChaineAtt is not null then
         posDebut:=1;
         posVirgul:=INSTR(p_ChaineAtt,',',posDebut);
         num:=creerEnsAttVide;      
         while posVirgul!=0
            loop
                longueur:=(posVirgul-posDebut);
                nomAtt:=SUBSTR(p_ChaineAtt,posDebut,longueur);
                ajouterAtt(nomAtt,num);
                posDebut:=posVirgul+1;
                posVirgul:=INSTR(p_ChaineAtt,',',posDebut);
         end loop;
         longueur:=(length(p_ChaineAtt)-posDebut+1);
         nomAtt:=SUBSTR(p_ChaineAtt,posDebut,longueur);
         ajouterAtt(nomAtt,num);
     end if;
    
    return num;
end;
/

/*fonction EnsAtt2Chaine retourne la liste des attributs sous forme de chaine de caractère
d'un ensemble d'attribut dont le numero est passé en argument*/
create or replace function ensAtt2Chaine(p_NumEnsAtt number) return varchar is
attrib varchar2(20);
resultat varchar2(2000);
cursor cur is SELECT NomAtt FROM EnsembleContientAttribut WHERE NumEnsAtt=p_NumEnsAtt;
begin
    open cur;
    FETCH cur INTO attrib;
    while cur%Found
    loop     
        resultat:=concat(resultat,attrib);
        FETCH cur INTO attrib;
        if cur%Found then
            resultat:=concat(resultat,',');
        end if;
    end loop;   
    close cur;
    return resultat;
end;
/


-- 3.1 fonction EstElement
create or replace function estElement(p_NomAtt varchar, p_NumEnsAtt number) return integer is
attrib varchar2(20);  
cursor cur is SELECT NomAtt FROM EnsembleContientAttribut WHERE NumEnsAtt=p_NumEnsAtt;
begin
    open cur;
    FETCH cur INTO attrib;
    while cur%Found
    loop     
        if p_nomAtt=attrib then
            return 1;
        end if;
        FETCH cur INTO attrib;
    end loop;   
    close cur;
    return 0;
end;
/

-- fonction EstInclus
create or replace function estInclus(p_NumEnsAtt_1 number, p_NumEnsAtt_2 number) return integer is
attrib varchar2(20);  
cursor cur is SELECT NomAtt FROM EnsembleContientAttribut WHERE NumEnsAtt=p_NumEnsAtt_1;
begin
    open cur;
    FETCH cur INTO attrib;
    while cur%Found
    loop     
        if estElement(attrib, p_NumEnsAtt_2)=0 then
            return 0;
        end if;
        FETCH cur INTO attrib;
    end loop;   
    close cur;
    return 1;
end;
/

-- fonction estEgal
create or replace function estEgal(p_NumEnsAtt_1 number, p_NumEnsAtt_2 number) return integer is
begin
    if estInclus(p_NumEnsAtt_1, p_NumEnsAtt_2)=1 and estInclus(p_NumEnsAtt_2, p_NumEnsAtt_1)=1 then
        return 1;
    else
        return 0;
    end if;
end;
/

--fonction uinonAtt
create or replace function unionAtt(p_NumEnsAtt_1 number, p_NumEnsAtt_2 number) return number is
chaine1 varchar2(2000);
chaine2 varchar2(2000);
resultat varchar2(2000);
num number;
begin
    chaine1:=ensAtt2Chaine(p_NumEnsAtt_1);
    chaine2:=ensAtt2Chaine(p_NumEnsAtt_2);
    resultat:=concat(chaine1,',');
    resultat:=concat(resultat,chaine2);
    num:=creerEnsAtt(resultat);
    return num;
end;
/

-- fonction intersectionAtt
create or replace function intersectionAtt(p_NumEnsAtt_1 number, p_NumEnsAtt_2 number) return number is
attrib varchar2(20);  
chaine varchar2(2000);
num number;
cursor cur is SELECT NomAtt FROM EnsembleContientAttribut WHERE NumEnsAtt=p_NumEnsAtt_1;
begin
    open cur;
    FETCH cur INTO attrib;
    while cur%Found
        loop     
            if estElement(attrib, p_NumEnsAtt_2)=1 then
                chaine:=concat(chaine, attrib);
                chaine:=concat(chaine,',');
            end if;
            FETCH cur INTO attrib;
    end loop;   
    close cur;
    chaine:=TRIM(TRAILING ',' FROM chaine);
    num:=creerEnsAtt(chaine);
    return num;
end;
/

-- fonction soustraction
create or replace function soustractionAtt(p_NumEnsAtt_1 number, p_NumEnsAtt_2 number) return number is 
    chaine varchar2(2000);
    num number;
    taille1 number;
    taille2 number;
    cursor cur1 is SELECT NomAtt FROM EnsembleContientAttribut WHERE NumEnsAtt=p_NumEnsAtt_1;
    cursor cur2 is SELECT NomAtt FROM EnsembleContientAttribut WHERE NumEnsAtt=p_NumEnsAtt_2;
begin
    SELECT count(*) INTO taille1 FROM EnsembleContientAttribut WHERE NumEnsAtt=p_NumEnsAtt_1;
    SELECT count(*) INTO taille2 FROM EnsembleContientAttribut WHERE NumEnsAtt=p_NumEnsAtt_2;
    
    if taille1>taille2 then
        for attrib in cur1 loop     
            if estElement(attrib.NomAtt, p_NumEnsAtt_2)=0 then
                chaine:=concat(chaine, attrib.NomAtt);
                chaine:=concat(chaine,',');
            end if;
        end loop;
    else
        for attrib in cur2 loop       
            if estElement(attrib.NomAtt, p_NumEnsAtt_1)=0 then
                chaine:=concat(chaine, attrib.NomAtt);
                chaine:=concat(chaine,',');
            end if;
        end loop;   
    end if;
    chaine:=TRIM(TRAILING ',' FROM chaine);
    num:=creerEnsAtt(chaine);
    return num;
end;
/

--fonction copieAtt
create or replace function copieAtt(p_NumEnsAtt number) return number is
    chaine varchar(2000);
    num number;
begin
    chaine:=ensAtt2Chaine(p_NumEnsAtt);
    num:=creerEnsAtt(chaine);
    return num;
end;
/
-- Test
declare
  x number; 
begin
  x:= copieAtt(27);
  dbms_output.put_line(x);
end;
/


--II-Gestion des DF

-- 1- Création de la table DFS 
CREATE TABLE DFs(
    NumDf NUMBER(5) CONSTRAINT PK_Dfs PRIMARY KEY,
    NumEnsGauche NUMBER(5) NOT NULL,
    NumEnsDroit NUMBER(5) NOT NULL,
    CONSTRAINT Dfs_unicity UNIQUE(NumEnsGauche,NumEnsDroit),
    CONSTRAINT FK1_DFS FOREIGN KEY(NumEnsgauche) REFERENCES EnsemblesAttributs(NumEnsAtt),
    CONSTRAINT FK2_DFS FOREIGN KEY(NumEnsDroit) REFERENCES EnsemblesAttributs(NumEnsAtt) 
);

CREATE SEQUENCE NumDf;

-- 2- Création des fonctions
create or replace function creerDF(p_ChaineAtt varchar) return number is
    posFleche number;
    longueur_gauche number;
    longueur_droite number;
    sousChaine_gauche varchar(2000);
    sousChaine_droite varchar(2000);
    num1 number;
    num2 number;
    num number:=-1;
begin
    if p_ChaineAtt is not null then 
    
        posFleche:=INSTR(p_ChaineAtt,'->',1);
        longueur_gauche:=(posFleche-1);
        sousChaine_gauche:=SUBSTR(p_ChaineAtt, 1, longueur_gauche);
        longueur_droite:=length(p_ChaineAtt)-(posFleche+2);
        sousChaine_droite:=SUBSTR(p_ChaineAtt, (posFleche+2), longueur_droite+1);
        
        if sousChaine_gauche is not null and sousChaine_droite is not null then    
            num1:=creerEnsAtt(sousChaine_gauche);
            num2:=creerEnsAtt(sousChaine_droite);
            INSERT INTO DFs Values(NumDF.NextVal, num1,num2)
            returning NumDf into num;
        end if;
        
        if sousChaine_gauche is null and sousChaine_droite is null then
            num1:=creerEnsAttVide;
            num2:=creerEnsAttVide;
            INSERT INTO DFs Values(NumDF.NextVal, num1,num2)
            returning NumDf into num;
        end if;
        
        if sousChaine_gauche is not null and sousChaine_droite is null then
            num1:=creerEnsAtt(sousChaine_gauche);
            num2:=creerEnsAttVide;
            INSERT INTO DFs Values(NumDF.NextVal, num1,num2)
            returning NumDf into num;
        end if; 
        
    end if;
    
    return num;
end;
/

-- Fonction DF2Chaine retourne sous forme de chaine de cractère la dependance fonctionnelle dont le numero est passé en argument
create or replace function df2Chaine(p_NumDF number) return varchar is 
     chaine_gauche varchar(2000);
     chaine_droite varchar(2000);
     resultat varchar(2000);
     Type DF_t is record (NumEnsGauche number(5), NumEnsDroit number(5));
     enreg DF_t;
begin
   SELECT NumEnsGauche, NumEnsDroit INTO enreg 
   FROM DFs
   WHERE NumDf=p_NumDF;
   chaine_gauche:=ensAtt2Chaine(enreg.NumEnsGauche);
   chaine_droite:=ensAtt2Chaine(enreg.NumEnsDroit);
   resultat:=concat(chaine_gauche,'->');
   resultat:=concat(resultat,chaine_droite);
   
   return resultat;
end;
/

-- 3- fonctions de traitement des DF
create or replace function estTriviale(p_NumDF Number) return integer is 
    Type DF_t is record (NumEnsGauche number(5), NumEnsDroit number(5));
    enreg DF_t;
    res integer;
begin
   SELECT NumEnsGauche, NumEnsDroit INTO enreg 
   FROM DFs
   WHERE NumDf=p_NumDF;
   res:=ESTINCLUS(enreg.NumEnsDroit, enreg.NumEnsGauche);
 
   return res;
end;
/

create or replace function estPlusForte(p_NumDF1 Number, p_NumDF2 Number) return integer is 
    Type DF_t is record (NumEnsGauche number(5), NumEnsDroit number(5));
    enreg1 DF_t;
    enreg2 DF_t;

begin
   SELECT NumEnsGauche, NumEnsDroit INTO enreg1
   FROM DFs
   WHERE NumDf=p_NumDF1;
   SELECT NumEnsGauche, NumEnsDroit INTO enreg2
   FROM DFs
   WHERE NumDf=p_NumDF2;
   
   if ESTINCLUS(enreg2.NumEnsDroit, enreg1.NumEnsDroit)=1 and ESTINCLUS(enreg1.NumEnsGauche, enreg2.NumEnsGauche)=1 then
        return 1;
   end if;
   
   return 0;
end;
/


-- III- Gestion des ensembles de DF

-- 1- Création des tables

CREATE TABLE EnsemblesDFs(
    NumEnsDF Number(5) PRIMARY KEY
);

CREATE TABLE EnsembleContientDF(
    NumEnsDF Number(5),
    NumDF Number(5),
    CONSTRAINT PK_EnsembleContientDF PRIMARY KEY (NumEnsDF,NumDF),
    CONSTRAINT FK1_EnsembleContientDF FOREIGN KEY (NumEnsDF) REFERENCES EnsemblesDFS(NumEnsDF) ON DELETE CASCADE,
    CONSTRAINT FK2_EnsembleContientDF FOREIGN KEY (NumDF) REFERENCES DFs(NumDF)
);


CREATE SEQUENCE NumEnsDF;


-- 2- création des fonctions

create or replace function creerEnsDFVide return number is
    x number;
begin
    INSERT INTO EnsemblesDFs values(NumEnsDF.NextVal)
    returning NumEnsDF into x;
    return x;
end;
/

create or replace procedure ajouterDF(p_NumDF number, p_NumEnsDF number) is

begin
    INSERT INTO ENSEMBLECONTIENTDF values(p_NumEnsDF, p_NumDF);
end;
/

-- fonction creerEnsDF crée un ensemble de dependance fonctionnelle à partir de la liste des dependances spécifiés en argument sous forme de chaine de cractère
create or replace function creerEnsDF(p_ChaineDF varchar) return number is
posSeparateur number;
posDebut number;
longueur number;
chaine varchar(2000);
res number:=-1;
num number;

begin
    if p_ChaineDF is not null then 
        posDebut:=1;
        posSeparateur:=INSTR(p_ChaineDF, ';', posDebut);
        res:=creerEnsDFVide;
        While posSeparateur !=0 loop
            longueur:=(posSeparateur-posDebut);
            chaine:=SUBSTR(p_ChaineDF, posDebut, longueur);
            num:=creerDF(chaine);
            ajouterDF(num, res);
            posDebut:=posSeparateur+1;
            posSeparateur:=INSTR(p_ChaineDF, ';', posDebut);
        end loop;
        longueur:=(length(p_ChaineDF)-posDebut+1);
        chaine:=SUBSTR(p_ChaineDF,posDebut,longueur);
        num:=creerDF(chaine);
        ajouterDF(num,res);
    end if;
    return res;
end;
/

-- requête pour afficher un ensembe de DF
SELECT e2.NumDF, NumEnsGauche, NumEnsDroit
FROM ENSEMBLESDFS e1, DFS d, ENSEMBLECONTIENTDF e2
WHERE e1.NumEnsDF=7 and  e1.NumEnsDF=e2.NumEnsDF and e2.NumDF=d.NumDF;


-- fonction ensDF2Chaine retourne sous forme de chaine de caractère les dépendances fonctionnelles de l'ensemble de dépendance fonctionnelle passé en argument
create or replace function ensDF2Chaine(p_NumEnsDF number) return varchar is
CURSOR cur is SELECT NumDF FROM ENSEMBLECONTIENTDF WHERE NumEnsDF=p_NumEnsDF;
numDF2 number;
chaine varchar(2000);
resultat varchar(2000);

begin
    open cur;
    FETCH cur INTO numDF2;
    while cur%Found loop     
        chaine:=df2Chaine(NumDF2);
        resultat:=concat(resultat,chaine);
        FETCH cur INTO numDF2;
        if cur%Found then
            resultat:=concat(resultat,';');
        end if;   
    end loop;   
    close cur;
    return resultat; 
end;
/

-- 3- fonctions de traitements sur les ensembles de DFs

-- fonction créer un ensemble d'attributs d'un ensemble des dépendances fonctionnelles
create or replace function EnsDF2EnsAtt(p_NumEnsDF number) return number is
x number;
chaine varchar(2000);
resultat varchar(2000);
CURSOR cur is SELECT NumDF FROM ENSEMBLECONTIENTDF WHERE NumEnsDF=p_NumEnsDF;
Type DF_t is record (NumEnsGauche number(5), NumEnsDroit number(5));
enreg DF_t;
numDF2 number;

begin
    open cur;
    FETCH cur INTO numDF2;
    while cur%Found loop
        SELECT NumEnsGauche, NumEnsDroit into enreg
        FROM DFS
        WHERE NumDF=numDF2;
        chaine:=concat(ensAtt2Chaine(enreg.NumEnsGauche), ',');
        chaine:=concat(chaine,ensAtt2Chaine(enreg.NumEnsDroit));
        resultat:=concat(resultat,chaine);
        FETCH cur INTO numDF2;
        if cur%Found then
            resultat:=concat(resultat,',');
        end if;
    end loop;
    x:=creerEnsAtt(resultat);
   return x;
end;
/

-- copieEnsDF fait une copie complete d'un enesmble de dependance fonctionnelle
create or replace function copieEnsDF(p_NumEnsDF number) return number is
numDF2 number;
CURSOR cur is SELECT NumDF FROM ENSEMBLECONTIENTDF WHERE NumEnsDF=p_NumEnsDF;
Type DF_t is record (NumEnsGauche number(5), NumEnsDroit number(5));
enreg DF_t;
attributs_gauche varchar(2000);
attributs_droit varchar(2000);
resultat1 varchar(2000);
resultat2 varchar(2000);
x number;

begin
    open cur;
    FETCH cur INTO numDF2;
    while cur%Found loop
        SELECT NumEnsGauche, NumEnsDroit into enreg
        FROM DFS
        WHERE NumDF=numDF2;
        attributs_gauche:=ensAtt2Chaine(enreg.NumEnsGauche);
        attributs_droit:=ensAtt2Chaine(enreg.NumEnsDroit);
        resultat1:=concat(attributs_gauche, '->');
        resultat1:=concat(resultat1, attributs_droit);
        resultat2:=concat(resultat2, resultat1);
        FETCH cur INTO numDF2;
        if cur%Found then
            resultat2:=concat(resultat2,';');
        end if;
    end loop;    
    x:=creerEnsDF(resultat2);
    return x;
end;
/


-- IV- Gestion des schémas relationnels

-- 1- Création des tables

CREATE TABLE Schemas(
    NumSchema NUMBER(5) CONSTRAINT PK_Schemas PRIMARY KEY,
    NumEnsAtt NuMBER(5),
    NumEnsDF NUMBER(5),
    CONSTRAINT FK1_schemas FOREIGN KEY(NumEnsAtt) REFERENCES EnsemblesAttributs(NumEnsAtt),
    CONSTRAINT FK2_schemas FOREIGN KEY(NumEnsDF) REFERENCES EnsemblesDFs(NumEnsDF)
);

CREATE SEQUENCE NumSchema;

-- 2- création des fonctions

-- fonction getAttribDF retourne sous forme de chaine les attributs de la dependance fonctionnelle passé en paramètre
create or replace function getAttribDF(p_ChaineDF varchar) return varchar is

resultat varchar(2000);
gauche varchar(2000);
droit varchar(2000);
debut number;
posFleche number;

begin
    debut:=1;
    posFleche:=INSTR(p_ChaineDF,'->',debut);
    gauche:=SUBSTR(p_ChaineDF, debut, posFleche-debut);
    droit:=SUBSTR(p_ChaineDF, posFleche+2, length(p_ChaineDF)-posFleche-1);
    resultat:=concat(gauche,',');
    resultat:=concat(resultat,droit);
    return resultat;
end;
/

-- fonction getAttribEnsDF retourne sous forme de chaine les attributs d'un ensemble de dépendance fonctionnelle apssé en paramètre
create or replace function getAttribEnsDF(p_ChaineEnsDF varchar) return varchar is

debut number;
posPointVirgule number;
df varchar(2000);
resultat varchar(2000);

begin
    debut:=1;
    posPointVirgule:=INSTR(p_ChaineEnsDF,';',debut);
    
    while posPointVirgule!=0 loop
        df:=SUBSTR(p_ChaineEnsDF, debut, posPointVirgule-debut);
        resultat:=concat(resultat, getAttribDF(df));    
        debut:=posPointVirgule+1;
        posPointVirgule:=INSTR(p_ChaineEnsDF, ';', debut);
        if posPointVirgule!=0 then
            resultat:=concat(resultat, ',');
        end if;
    end loop;
    df:=SUBSTR(p_ChaineEnsDF, debut, length(p_ChaineEnsDF)-debut+1);
    resultat:=concat(resultat, ',');
    resultat:=concat(resultat, getAttribDF(df));
    return resultat;
end;
/


-- fonction estContenu return 1 si les attributs du premier ensemble sont dans le deuxième; si non return 0
create or replace function estContenu(chaine1 varchar, chaine2 varchar) return integer is

debut number;
posV number;
attrib varchar(2000);

begin
    debut:=1;
    posV:=INSTR(chaine1, ',', debut);
    
    while posV!=0 loop
        attrib:=SUBSTR(chaine1, debut, posV-debut);
        
        if INSTR(chaine2, attrib, 1)=0 then
            return 0;
       end if;    
       debut:=posV+1;
       posV:=INSTR(chaine1, ',', debut);
    end loop;
    attrib:=SUBSTR(chaine1, debut, length(chaine1)-debut+1);
    
    if INSTR(chaine2, attrib, 1)=0  then
        return 0;
    else
        return 1;
    end if; 
end;
/
 -- fonction permettant de creer un schema à partir d'un ensemble d'attributs et d'un ensemble de dépendance fonctionnelle
create or replace function creerSchema(p_ChaineEnsAtt varchar, p_ChaineEnsDF varchar) return number is

x number:=-1;
numEnsAtt number;
numEnsDF number;
attributs varchar(2000);
v_except EXCEPTION;

begin
    attributs:=GETATTRIBENSDF(p_ChaineEnsDF); 
    
    if estContenu(attributs, p_chaineEnsAtt)=1 then 
        numEnsAtt:=creerEnsAtt(p_ChaineEnsAtt);
        numEnsDF:=creerEnsDF(p_ChaineEnsDF);
        INSERT INTO Schemas values(NumSchema.NextVal, numEnsAtt, numEnsDF)
        returning NumSchema into x;
        return x;
    else 
       RAISE v_except; 
    end if;
    
    EXCEPTION 
        when v_except then
        dbms_output.put_line('Imossible de créer ce schémas ');
    return x;
end;
/

-- 3- Requête pour afficher un schemas
SELECT ensAtt2Chaine(NumEnsAtt), ensDF2Chaine(NumEnsDF)
FROM Schemas
WHERE NumSchema=1;


-- V Gestion des clefs des schémas relationnels

CREATE TABLE EnsemblesClefs(
    NumEnsClef NUMBER(5) PRIMARY KEY,
    NumSchema NUMBER(5) UNIQUE NULL,
    CONSTRAINT FK_EnsemblesClefs FOREIGN KEY(NumSchema) REFERENCES Schemas(NumSchema) ON DELETE CASCADE
);

CREATE TABLE EnsembleContientClef(
    NumEnsClef NUMBER(5) ,
    NumClef NUMBER(5),
    CONSTRAINT PK_EnsContientClef PRIMARY KEY(NumEnsClef, NumClef),
    CONSTRAINT FK1_EnsContientClef FOREIGN KEY(NumEnsClef) REFERENCES EnsemblesClefs(NumEnsClef) ON DELETE CASCADE,
    CONSTRAINT FK2_EnsContientClef FOREIGN KEY(NumClef) REFERENCES EnsemblesAttributs(NumEnsAtt)
);

DROP TABLE EnsemblesClefs;
DROP TABLE EnsembleContientClef;


CREATE SEQUENCE NumEnsClef;


-- 2- écriture des fonctions 

create or replace function EnsClef2Chaine(p_NumEnsClef number) return varchar is

CURSOR cur IS SELECT NumClef
FROM EnsembleContientClef e
WHERE e.NumEnsClef=p_NumEnsClef;

resultat varchar(2000);
chaine varchar(2000);

begin
    for temp in cur loop
       chaine:='{';
       resultat:=concat(resultat, chaine);
       resultat:=concat(resultat, ensAtt2Chaine(temp.NumClef));
       resultat:=concat(resultat, '}');
       resultat:=concat(resultat, ',');
    end loop;
    resultat:=TRIM(TRAILING ',' FROM resultat);
    return resultat;
end;
/

create or replace function creerEnsClefVide(p_NumSchema number) return number is
x number;
begin
    INSERT INTO EnsemblesClefs values(NumEnsClef.NextVal, p_NumSchema)
    returning NumEnsClef into x;
    return x;
end;
/

create or replace procedure ajouterClef(p_NumClef number, p_NumEnsClef number) is 

begin
    INSERT INTO ENSEMBLECONTIENTCLEF Values(p_NumEnsClef, p_NumClef);
end;
/

-- 4- Test des méthodes et de la structure relationnelle créer

SELECT ensAtt2Chaine(s.NumEnsAtt) as ensAtt, ensDF2Chaine(s.NumEnsDF) as ensDFS, ENSCLEF2CHAINE(NumEnsClef) as ensClefs
FROM EnsemblesClefs d, Schemas s
WHERE NumEnsClef=1 and  d.NumSchema=s.NumSchema;

--VI- GEstion des structures relationnelles

-- 1- crétion des tables

CREATE TABLE Structures(
    NumStructure NUMBER(5) CONSTRAINT PK_Structures PRIMARY KEY
);

CREATE TABLE StructureContientSchema(
    NumStructure NUMBER(5),
    NumSchema NUMBER(5),
    CONSTRAINT PK_StructureContientSchema PRIMARY KEY(NumStructure, NumSchema),
    CONSTRAINT FK1_StructureContientSchema FOREIGN KEY(NumStructure) REFERENCES Structures(NumStructure) ON DELETE CASCADE,
    CONSTRAINT FK2_StructureCOntientSchema FOREIGN KEY(NumSChema) REFERENCES Schemas(NumSchema)
);

CREATE SEQUENCE NumStructure;

-- 2- création des fonctions

create or replace function creerStructureVide return number is
x number;
begin
    INSERT INTO Structures Values(NumStructure.NextVal)
    returning NumStructure into x;
    return x;
end;
/

create or replace procedure ajouterSChema(p_NumSChema number, p_NumStructure number) is 

begin
    INSERT INTO StructureContientSChema Values(p_NumStructure, p_NumSchema);  
end;
/






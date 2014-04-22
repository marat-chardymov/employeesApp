insert into COUNTRIES (NAME)
select 'Country ' || to_char(rownum)
from dual
connect by level <= 10000;

insert into CITIES (COUNTRY_FK , NAME)
select 
  round(dbms_random.value(46, 10045)),
  'City ' || to_char(rownum)
from dual
connect by level <= 10000;

insert into ADDRESSES (CITY_FK , CONTENT)
select 
  round(dbms_random.value(140014, 150014)),
  'Address ' || to_char(rownum)
from dual
connect by level <= 10000;

insert into EMPLOYEES (ADDRESS_FK , FIRST_NAME,LAST_NAME)
select 
  rownum,
  'FIRST_NAME ' || to_char(rownum),
  'LAST_NAME' || to_char(rownum)
from dual
connect by level <= 10000;

insert into COMPANIES (NAME)
select 
  'COMPANY ' || to_char(rownum)
from dual
connect by level <= 10000;

insert into COMPANIES_EMPLOYEES (COMPANY_ID,EMPLOYEE_ID)
select 
  round(dbms_random.value(1, 10001)),
  round(dbms_random.value(10002, 20002))
from dual
connect by level <= 10000;

insert into COMPANIES_ADDRESSES(COMPANY_ID,ADDRESS_ID)
select 
  round(dbms_random.value(1, 10001)),
  round(dbms_random.value(1, 10001))
from dual
connect by level <= 5000;

insert into OFFICES (COMPANY_ID,ADDRESS_ID)
select 
  round(dbms_random.value(1, 10001)),
  round(dbms_random.value(1, 10001))
from dual
connect by level <= 5000;

insert into WORKPLACES (EMPLOYEE_ID,OFFICE_ID,POSITION)
select 
  round(dbms_random.value(10002, 20002)),
  round(dbms_random.value(10002, 20002)),
  'position ' || to_char(rownum)
from dual
connect by level <= 5000;

/*
 1. 동적 쿼리를 사용하는 저장 프로시저 작성하세요.  ( 5분 )
     ㄱ. 커서변수 사용
     ㄴ. open ~ for 문 사용
     ㄷ. 프로시저 안에서  출력까지 하세요.
     ㄹ. deptno 를 파라미터로 해서 emp 테이블의
         deptno, ename, hiredate 를 출력하는 저장 프로시저 작성.
*/


1)
CREATE OR REPLACE PROCEDURE p_emp
IS
    CURSOR vcur IS (SELECT * FROM emp); -- WHERE deptno = pdeptno
    vrowemp emp%ROWTYPE;
BEGIN
    OPEN vcur;
    
    LOOP
        FETCH vcur INTO vrowemp;
        EXIT WHEN vcur%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(vrowemp.deptno || ', ' || vrowemp.ename || ', ' || vrowemp.hiredate);
        
    END LOOP;
    
    CLOSE vcur;
-- EXCEPTION
END;

EXEC P_EMP;


2) SYS_REFCURSOR로 커서 파라미터 선언(커서를 매개변수로 받겠다)
CREATE OR REPLACE PROCEDURE p_emp_cur
(
    pcursor SYS_REFCURSOR
)
IS
    vdeptno emp.deptno%TYPE;
    vename emp.ename%TYPE;
    vhiredate emp.hiredate%TYPE;
BEGIN
    LOOP
        FETCH pcursor INTO vdeptno, vename, vhiredate;
        EXIT WHEN pcursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(vdeptno || ', ' || vename || ', ' || vhiredate);
    END LOOP;
END;


3) 커서변수 선언
CREATE OR REPLACE PROCEDURE p_emp_print
IS    
    vcursor SYS_REFCURSOR;
BEGIN
    
    OPEN vcursor FOR SELECT deptno, ename, hiredate FROM emp;
    p_emp_cur(vcursor);
    CLOSE vcursor;
-- EXCEPTION
END;

EXEC P_EMP_PRINT;

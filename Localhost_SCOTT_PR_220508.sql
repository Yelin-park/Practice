/*
 1. 동적 쿼리를 사용하는 저장 프로시저 작성하세요.  ( 5분 )
     ㄱ. 커서변수 사용
     ㄴ. open ~ for 문 사용
     ㄷ. 프로시저 안에서  출력까지 하세요.
     ㄹ. deptno 를 파라미터로 해서 emp 테이블의
         deptno, ename, hiredate 를 출력하는 저장 프로시저 작성.
*/

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


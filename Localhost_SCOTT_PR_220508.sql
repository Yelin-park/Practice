/*
 1. ���� ������ ����ϴ� ���� ���ν��� �ۼ��ϼ���.  ( 5�� )
     ��. Ŀ������ ���
     ��. open ~ for �� ���
     ��. ���ν��� �ȿ���  ��±��� �ϼ���.
     ��. deptno �� �Ķ���ͷ� �ؼ� emp ���̺���
         deptno, ename, hiredate �� ����ϴ� ���� ���ν��� �ۼ�.
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


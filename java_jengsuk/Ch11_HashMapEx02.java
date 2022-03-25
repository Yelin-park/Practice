package java_jengsuk;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Ch11_HashMapEx02 {

	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put("김씨", new Integer(80));
		map.put("이씨", new Integer(80));
		map.put("박씨", new Integer(90));
		map.put("고씨", new Integer(100));
		
		Set s = map.entrySet();
		Iterator ir = s.iterator();
		while (ir.hasNext()) {
			Entry e = (Entry) ir.next();
			System.out.println("이름 : " + e.getKey() + ", 점수 : " + e.getValue());
		} // while
		
		s = map.keySet();
		System.out.println("참가자 명단 : " + s );
		
		// 총점, 평균, 최고점수, 최저점수
		Collection c = map.values();
		ir = c.iterator();
		
		int total = 0;
		while (ir.hasNext()) {
			Integer score = (Integer) ir.next();
			total += score;
		}
		
		System.out.println("총점 : " + total);
		System.out.println("평균 : " + (float)total/c.size());
		System.out.println("최고점수 : " + Collections.max(c));
		System.out.println("최저점수 : " + Collections.min(c));
		
	} // main

} // class

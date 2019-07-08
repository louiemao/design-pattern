package com.louie.study.dp.factorymethod.example6;

public class A2 extends A1 {
	protected C1 createC1() {
		//������ѡ�����ʵ�֣�����������
		return new C2();
	}
}

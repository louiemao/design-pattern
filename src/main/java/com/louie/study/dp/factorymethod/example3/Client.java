package com.louie.study.dp.factorymethod.example3;

public class Client {
	public static void main(String[] args) {
		//������Ҫʹ�õ�Creator����
		ExportOperate operate = new ExportDBOperate();
		//����������ݵĹ��ܷ���
		operate.export("��������");
	}
}

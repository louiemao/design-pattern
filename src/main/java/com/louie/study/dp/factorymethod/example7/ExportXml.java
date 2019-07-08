package com.louie.study.dp.factorymethod.example7;
/**
 * ������xml�ļ��Ķ���
 */
public class ExportXml implements ExportFileApi{
	public boolean export(String data) {
		//��ʾ��һ��
		System.out.println("��������"+data+"��XML�ļ�");
		return true;
	}
}

package com.louie.study.dp.factorymethod.example3;
/**
 * ����Ĵ�����ʵ�ֶ���ʵ�ִ������������ݿⱸ���ļ���ʽ�Ķ���
 */
public class ExportDBOperate extends ExportOperate{
	protected ExportFileApi factoryMethod() {
		//�������������ݿⱸ���ļ���ʽ�Ķ���
		return new ExportDB();
	}
}

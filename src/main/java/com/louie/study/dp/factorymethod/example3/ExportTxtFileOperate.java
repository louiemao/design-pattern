package com.louie.study.dp.factorymethod.example3;
/**
 * ����Ĵ�����ʵ�ֶ���ʵ�ִ����������ı��ļ���ʽ�Ķ���
 */
public class ExportTxtFileOperate extends ExportOperate{

	protected ExportFileApi factoryMethod() {
		//�����������ı��ļ���ʽ�Ķ���
		return new ExportTxtFile();
	}

}

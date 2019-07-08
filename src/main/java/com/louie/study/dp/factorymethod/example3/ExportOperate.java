package com.louie.study.dp.factorymethod.example3;
/**
 * ʵ�ֵ������ݵ�ҵ���ܶ���
 */
public abstract class ExportOperate {
	/**
	 * �����ļ�
	 * @param data ��Ҫ���������
	 * @return �Ƿ�ɹ������ļ�
	 */
	public boolean export(String data){
		//ʹ�ù�������
		ExportFileApi api = factoryMethod();
		return api.export(data);
	}
	/**
	 * ���������������������ļ�����Ľӿڶ���
	 * @return �������ļ�����Ľӿڶ���
	 */
	protected abstract ExportFileApi factoryMethod();
}

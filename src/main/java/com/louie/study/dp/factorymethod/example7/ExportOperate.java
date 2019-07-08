package com.louie.study.dp.factorymethod.example7;
/**
 * ʵ�ֵ������ݵ�ҵ���ܶ���
 */
public class ExportOperate {
	/**
	 * �����ļ�
	 * @param type �û�ѡ��ĵ�������
	 * @param data ��Ҫ���������
	 * @return �Ƿ�ɹ������ļ�
	 */
	public boolean export(int type,String data){
		//ʹ�ù�������
		ExportFileApi api = factoryMethod(type);
		return api.export(data);
	}
	/**
	 * ���������������������ļ�����Ľӿڶ���
	 * @param type �û�ѡ��ĵ�������
	 * @return �������ļ�����Ľӿڶ���
	 */
	protected ExportFileApi factoryMethod(int type){
		ExportFileApi api = null;
		//����������ѡ�񾿾�Ҫ������һ�ֵ����ļ�����
		if(type==1){
			api = new ExportTxtFile();
		}else if(type==2){
			api = new ExportDB();
		}
		return api;
	}
}
